package net.asbyth.tweaker.mixins.util;

import net.asbyth.tweaker.config.Settings;
import net.asbyth.tweaker.implementation.ScreenshotHelperHook;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ScreenShotHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.File;
import java.nio.IntBuffer;

@Mixin(ScreenShotHelper.class)
public abstract class MixinScreenshotHelper {

    @Shadow private static IntBuffer pixelBuffer;
    @Shadow private static int[] pixelValues;

    /**
     * @author asbyth
     * @reason remove the lagspike when taking a screenshot
     */
    @Inject(method = "saveScreenshot(Ljava/io/File;Ljava/lang/String;IILnet/minecraft/client/shader/Framebuffer;)Lnet/minecraft/util/IChatComponent;", at = @At("HEAD"), cancellable = true)
    private static void saveScreenshot(File gameDirectory, String screenshotName, int width, int height, Framebuffer buffer, CallbackInfoReturnable<IChatComponent> cir) {
        if (Settings.ASYNCSCREENSHOTS) {
//            cir.setReturnValue(ScreenshotHelperHook.saveScreenshot(gameDirectory, screenshotName, width, height, buffer, pixelBuffer, pixelValues));
        }
    }
}
