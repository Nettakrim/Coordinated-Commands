package com.nettakrim.coordinated_commands.mixin;

import com.nettakrim.coordinated_commands.CommandBlockPositionAccessor;
import net.minecraft.block.entity.CommandBlockBlockEntity;
import net.minecraft.client.gui.screen.ingame.MinecartCommandBlockScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.vehicle.CommandBlockMinecartEntity;
import net.minecraft.network.packet.c2s.play.UpdateCommandBlockMinecartC2SPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.CommandBlockExecutor;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(MinecartCommandBlockScreen.class)
public abstract class MinecartCommandBlockScreenMixin implements CommandBlockPositionAccessor {
    @Shadow
    @Final
    private CommandBlockExecutor commandExecutor;

    @Override
    public BlockPos coordinatedCommands$getPosition() {
        if (commandExecutor instanceof CommandBlockMinecartEntity.CommandExecutor commandExecutor2) {
            Entity entity = commandExecutor2.getMinecart();
            return new BlockPos(entity.getBlockX(), entity.getBlockY(), entity.getBlockZ());
        }
        return null;
    }
}
