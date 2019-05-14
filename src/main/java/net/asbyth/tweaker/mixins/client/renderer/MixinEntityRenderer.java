package net.asbyth.tweaker.mixins.client.renderer;

import net.asbyth.tweaker.config.Settings;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = EntityRenderer.class, priority = 1500)
public class MixinEntityRenderer {

    @Redirect(method = "orientCamera", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/WorldClient;rayTraceBlocks(Lnet/minecraft/util/Vec3;Lnet/minecraft/util/Vec3;)Lnet/minecraft/util/MovingObjectPosition;"), expect = 0)
    private MovingObjectPosition rayTraceBlocks(WorldClient world, Vec3 from, Vec3 to) {
        if (Settings.RAYTRACEBLOCKS) {
            return world.rayTraceBlocks(from, to, false, true, true);
        } else {
            return world.rayTraceBlocks(from, to, false, false, false);
        }
    }
}
