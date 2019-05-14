package net.asbyth.tweaker.implementation;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import java.io.File;
import java.nio.IntBuffer;

import static net.minecraft.util.EnumChatFormatting.*;

public class ScreenshotHelper {

    public static IChatComponent saveScreenshot(File gameDirectory, String screenshotName, int width, int height, Framebuffer framebuffer, IntBuffer pixelBuffer, int[] pixelValues) {
        File screenshotDirectory = new File(Minecraft.getMinecraft().mcDataDir, "screenshots");
        if (!screenshotDirectory.exists()) {
            screenshotDirectory.mkdir();
        }

        if (OpenGlHelper.isFramebufferEnabled()) {
            width = framebuffer.framebufferTextureWidth;
            height = framebuffer.framebufferTextureHeight;
        }

        int scale = width * height;

        if (pixelBuffer == null || pixelBuffer.capacity() < scale) {
            pixelBuffer = BufferUtils.createIntBuffer(scale);
            pixelValues = new int[scale];
        }

        GL11.glPixelStorei(3333, 1);
        GL11.glPixelStorei(3317, 1);
        pixelBuffer.clear();

        if (OpenGlHelper.isFramebufferEnabled()) {
            GlStateManager.bindTexture(framebuffer.framebufferTexture);
            GL11.glGetTexImage(3553, 0, 32993, 33639, pixelBuffer);
        } else {
            GL11.glReadPixels(0, 0, width, height, 32993, 33639, pixelBuffer);
        }

        pixelBuffer.get(pixelValues);
        new Thread(new AsyncScreenshot(width, height, pixelValues, Minecraft.getMinecraft().getFramebuffer(), screenshotDirectory)).start();
        return new ChatComponentText(BLUE + "(" + GRAY + BOLD + "Tweaker" + BLUE + ") " + GRAY + "Capturing screenshot.");
    }
}
