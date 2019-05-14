package net.asbyth.tweaker.mixins.client;

import net.asbyth.tweaker.config.Settings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static org.spongepowered.asm.mixin.injection.At.Shift.AFTER;

@Mixin(Minecraft.class)
public abstract class MixinMinecraft {

    @Shadow long systemTime;
    @Shadow private boolean fullscreen;
    @Shadow private static Minecraft theMinecraft;
    @Shadow public int displayWidth;
    @Shadow public int displayHeight;

    /**
     * @author asbyth
     * @reason instant world switching
     */
    @Inject(method = "loadWorld(Lnet/minecraft/client/multiplayer/WorldClient;Ljava/lang/String;)V", at = @At(value = "INVOKE", target = "Ljava/lang/System;gc()V"), cancellable = true)
    private void switchWorldInstantly(WorldClient worldClientIn, String loadingMessage, CallbackInfo ci) {
        if (Settings.INSTANTWORLDSWITCHING) {
            systemTime = 0;
            ci.cancel();
        }
    }

    /**
     * @author asbyth
     * @reason windowed fullscreen
     */
    @Inject(method = "setInitialDisplayMode", at = @At("HEAD"), cancellable = true)
    private void setInitialDisplayMode(CallbackInfo ci) throws LWJGLException {
        Display.setFullscreen(false);

        if (fullscreen) {
            if (Settings.WINDOWEDFULLSCREEN) {
                System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
            } else {
                Display.setFullscreen(true);
                DisplayMode mode = Display.getDisplayMode();

                ((IMixinMinecraft) theMinecraft).setDisplayWidth(Math.max(1, mode.getWidth()));
                ((IMixinMinecraft) theMinecraft).setDisplayHeight(Math.max(1, mode.getHeight()));
            }
        } else {
            if (Settings.WINDOWEDFULLSCREEN) {
                System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
            } else {
                Display.setDisplayMode(new DisplayMode(displayWidth, displayHeight));
            }
        }

        Display.setResizable(false);
        Display.setResizable(true);

        ci.cancel();
    }

    /**
     * @author asbyth
     * @reason windowed fullscreen
     */
    @Inject(method = "toggleFullscreen", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/Display;setVSyncEnabled(Z)V", shift = AFTER))
    private void toggleFullscreen(CallbackInfo ci) throws LWJGLException {
        if (Settings.WINDOWEDFULLSCREEN) {
            if (fullscreen) {
                System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");

                Display.setDisplayMode(Display.getDesktopDisplayMode());
                Display.setLocation(0, 0);

                Display.setFullscreen(false);
            } else {
                System.setProperty("org.lwjgl.opengl.Window.undecorated", "false");
                Display.setDisplayMode(new DisplayMode(displayWidth, displayHeight));
            }
        } else {
            Display.setFullscreen(fullscreen);
            System.setProperty("org.lwjgl.opengl.Window.undecorated", "false");
        }

        Display.setResizable(false);
        Display.setResizable(true);
    }
}
