package net.asbyth.tweaker.mixins.client.gui;

import net.asbyth.tweaker.config.Settings;
import net.minecraft.client.gui.GuiGameOver;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiGameOver.class)
public class MixinGuiGameOver {

    @Shadow private int enableButtonsTimer;

    /**
     * @author asbyth
     * @reason fixes respawn button greying out when toggling fullscreen on this gui
     */
    @Inject(method = "initGui", at = @At("HEAD"))
    private void setEnableButtonsTimerHead(CallbackInfo ci) {
        if (Settings.RESPAWNBUTTON) {
            enableButtonsTimer = 0;
        }
    }
}
