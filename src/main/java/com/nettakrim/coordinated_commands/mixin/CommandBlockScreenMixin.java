package com.nettakrim.coordinated_commands.mixin;

import com.nettakrim.coordinated_commands.CommandBlockPositionAccessor;
import net.minecraft.block.entity.CommandBlockBlockEntity;
import net.minecraft.client.gui.screen.ingame.CommandBlockScreen;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(CommandBlockScreen.class)
public class CommandBlockScreenMixin implements CommandBlockPositionAccessor {
    @Final
    @Shadow
    private
    CommandBlockBlockEntity blockEntity;

    @Override
    public BlockPos coordinatedCommands$getPosition() {
        return blockEntity.getPos();
    }
}
