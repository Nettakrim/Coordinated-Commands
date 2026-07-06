package com.nettakrim.coordinated_commands.mixin;

import com.nettakrim.coordinated_commands.CommandBlockPositionAccessor;
import net.minecraft.client.gui.screens.inventory.CommandBlockEditScreen;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.CommandBlockEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(CommandBlockEditScreen.class)
public class CommandBlockScreenMixin implements CommandBlockPositionAccessor {
    @Final
    @Shadow
    private
    CommandBlockEntity autoCommandBlock;

    @Override
    public BlockPos coordinatedCommands$getPosition() {
        return autoCommandBlock.getBlockPos();
    }
}
