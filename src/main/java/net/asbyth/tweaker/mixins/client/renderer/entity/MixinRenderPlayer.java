package net.asbyth.tweaker.mixins.client.renderer.entity;

import net.asbyth.tweaker.config.Settings;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderPlayer.class)
public abstract class MixinRenderPlayer {

    @Shadow public abstract ModelPlayer getMainModel();

    @Inject(method = "renderRightArm", at = @At(value = "FIELD", target = "Lnet/minecraft/client/model/ModelPlayer;isSneak:Z", ordinal = 0))
    private void fixArmPosition(AbstractClientPlayer clientPlayer, CallbackInfo ci) {
        ModelPlayer modelplayer = getMainModel();

        if (Settings.ARMPOSITION) {
            modelplayer.isRiding = modelplayer.isSneak = false;
        } else {
            modelplayer.isSneak = false;
        }
    }
}
