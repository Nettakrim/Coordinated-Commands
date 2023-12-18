package com.nettakrim.coordinated_commands.mixin;

import com.nettakrim.coordinated_commands.CommandBlockPositionAccessor;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.AbstractCommandBlockScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Mixin(AbstractCommandBlockScreen.class)
public class AbstractCommandBlockScreenMixin extends Screen {
	@Shadow
	protected TextFieldWidget consoleCommandTextField;

	protected AbstractCommandBlockScreenMixin(Text title) {
		super(title);
	}

	@Inject(at = @At("HEAD"), method = "init")
	private void init(CallbackInfo info) {
		if (!(this instanceof CommandBlockPositionAccessor)) {
			return;
		}

		addDrawableChild(ButtonWidget.builder(Text.literal("~"), this::toggleCoordinates).position(this.width / 2 + 160, this.height / 4 + 120 + 12).size(20, 20).build());
	}

	@Unique
	private static final Pattern pattern = Pattern.compile("(~(\\d|\\.|-)*( |$)|(\\d|\\.|-)+( |$)){3}");

	@Unique
	private void toggleCoordinates(ButtonWidget buttonWidget) {
		if (!(this instanceof CommandBlockPositionAccessor positionAccessor)) {
			return;
		}

		String text = consoleCommandTextField.getText();

		BlockPos pos = positionAccessor.coordinatedCommands$getPosition();
		boolean isConvertToAbsolute = text.contains("~");

		Matcher matcher = pattern.matcher(text);
		while (matcher.find()) {
			String s = matcher.group(0);
			String n = isConvertToAbsolute ? convertToAbsoulte(s, pos) : convertToRelative(s, pos);
			if (s.endsWith(" ")) n+=" ";
			text = text.replace(s, n);
		}

		consoleCommandTextField.setText(text);
	}

	@Unique
	private String convertToAbsoulte(String s, BlockPos pos) {
		String[] parts = s.split(" ");
		s = convertToAbsoulte(parts[0], pos.getX())+" "+
			convertToAbsoulte(parts[1], pos.getY())+" "+
			convertToAbsoulte(parts[2], pos.getZ());
		return s;
	}

	@Unique
	private String convertToAbsoulte(String s, int value) {
		if (s.charAt(0) != '~') {
			return s;
		}
		float f = s.length() == 1 ? 0f : Float.parseFloat(s.substring(1));
		return toString(f+value, false);
	}

	@Unique
	private String convertToRelative(String s, BlockPos pos) {
		String[] parts = s.split(" ");
		s = convertToRelative(parts[0], pos.getX())+" "+
			convertToRelative(parts[1], pos.getY())+" "+
			convertToRelative(parts[2], pos.getZ());
		return s;
	}

	@Unique
	private String convertToRelative(String s, int value) {
		if (s.charAt(0) == '~') {
			return s;
		}
		float f = Float.parseFloat(s);
		return "~"+(toString(f-value, true));
	}

	@Unique
	private String toString(float f, boolean relative) {
		if (relative && f == 0) return "";
		int i = MathHelper.floor(f);
		return (f == i) ? Integer.toString(i) : Float.toString(f);
	}
}