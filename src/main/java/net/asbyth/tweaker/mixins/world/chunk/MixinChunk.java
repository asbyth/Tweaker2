package net.asbyth.tweaker.mixins.world.chunk;

import net.asbyth.tweaker.config.Settings;
import net.minecraft.client.Minecraft;
import net.minecraft.util.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.chunk.Chunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Chunk.class)
public class MixinChunk {

    // everything here is for fullbright

    @Inject(method = "getLightFor", at = @At("HEAD"), cancellable = true)
    private void getLightFor(EnumSkyBlock lightType, BlockPos pos, CallbackInfoReturnable<Integer> cir) {
        if (Settings.FULLBRIGHT) {
            if (!Minecraft.getMinecraft().isIntegratedServerRunning()) {
                cir.setReturnValue(15);
            }
        }
    }

    @Inject(method = "getLightSubtracted", at = @At("HEAD"), cancellable = true)
    private void getLightSubtracted(BlockPos pos, int amount, CallbackInfoReturnable<Integer> cir) {
        if (Settings.FULLBRIGHT) {
            if (!Minecraft.getMinecraft().isIntegratedServerRunning()) {
                cir.setReturnValue(15);
            }
        }
    }
}
