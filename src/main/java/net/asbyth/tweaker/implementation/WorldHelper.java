package net.asbyth.tweaker.implementation;

import net.minecraft.client.Minecraft;
import net.minecraft.util.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public class WorldHelper {

    public void checkLightFor(EnumSkyBlock lightType, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (!Minecraft.getMinecraft().isIntegratedServerRunning()) {
            cir.setReturnValue(false);
        }
    }

    public void getLightFromNeighborsFor(EnumSkyBlock lightType, BlockPos pos, CallbackInfoReturnable<Integer> cir) {
        if (!Minecraft.getMinecraft().isIntegratedServerRunning()) {
            cir.setReturnValue(15);
        }
    }

    public void getLightFromNeighbors(BlockPos pos, CallbackInfoReturnable<Integer> cir) {
        if (!Minecraft.getMinecraft().isIntegratedServerRunning()) {
            cir.setReturnValue(15);
        }
    }

    public void getRawLight(BlockPos pos, EnumSkyBlock lightType, CallbackInfoReturnable<Integer> cir) {
        if (!Minecraft.getMinecraft().isIntegratedServerRunning()) {
            cir.setReturnValue(15);
        }
    }

    public void getLight(BlockPos pos, CallbackInfoReturnable<Integer> cir) {
        if (!Minecraft.getMinecraft().isIntegratedServerRunning()) {
            cir.setReturnValue(15);
        }
    }

    public void getLight(BlockPos pos, boolean checkNeighbors, CallbackInfoReturnable<Integer> cir) {
        if (!Minecraft.getMinecraft().isIntegratedServerRunning()) {
            cir.setReturnValue(15);
        }
    }
}
