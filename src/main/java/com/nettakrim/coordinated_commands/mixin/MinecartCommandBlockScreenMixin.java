package com.nettakrim.coordinated_commands.mixin;

import com.nettakrim.coordinated_commands.CommandBlockPositionAccessor;
import net.minecraft.client.gui.screens.inventory.MinecartCommandBlockEditScreen;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.vehicle.minecart.MinecartCommandBlock;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(MinecartCommandBlockEditScreen.class)
public abstract class MinecartCommandBlockScreenMixin implements CommandBlockPositionAccessor {
    @Shadow @Final private MinecartCommandBlock minecart;

    @Override
    public BlockPos coordinatedCommands$getPosition() {
        return new BlockPos(minecart.getBlockX(), minecart.getBlockY(), minecart.getBlockZ());
    }
}
