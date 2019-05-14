package net.asbyth.tweaker.mixins.world;

import net.asbyth.tweaker.config.Settings;
import net.asbyth.tweaker.implementation.WorldHelper;
import net.minecraft.util.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraftforge.fml.relauncher.Side.CLIENT;

@Mixin(World.class)
public abstract class MixinWorld {

    @Shadow @Final public WorldProvider provider;
    private WorldHelper helper = new WorldHelper();

    /**
     * @author asbyth
     * @reason fixes the sky height being set to 63.0D, causing flickering on servers where the platform is below Y63
     */
    @SideOnly(CLIENT)
    @Overwrite
    public double getHorizon() {
        if (Settings.VOIDFLICKERFIX) {
            return 0.0D;
        } else {
            return provider.getHorizon();
        }
    }

    // everything below is for fullbright
    @Inject(method = "checkLightFor", at = @At("HEAD"), cancellable = true)
    private void checkLightFor(EnumSkyBlock lightType, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (Settings.FULLBRIGHT) {
            helper.checkLightFor(lightType, pos, cir);
        }
    }

    @Inject(method = "getLightFromNeighborsFor",at = @At("HEAD"), cancellable = true)
    private void getLightFromNeighborsFor(EnumSkyBlock type, BlockPos pos, CallbackInfoReturnable<Integer> cir) {
        if (Settings.FULLBRIGHT) {
            helper.getLightFromNeighborsFor(type, pos, cir);
        }
    }

    @Inject(method = "getLightFromNeighbors", at = @At("HEAD"), cancellable = true)
    private void getLightFromNeighbors(BlockPos pos, CallbackInfoReturnable<Integer> cir) {
        if (Settings.FULLBRIGHT) {
            helper.getLightFromNeighbors(pos, cir);
        }
    }

    @Inject(method = "getRawLight", at = @At("HEAD"), cancellable = true)
    private void getRawLight(BlockPos pos, EnumSkyBlock lightType, CallbackInfoReturnable<Integer> cir) {
        if (Settings.FULLBRIGHT) {
            helper.getRawLight(pos, lightType, cir);
        }
    }

    @Inject(method = "getLight(Lnet/minecraft/util/BlockPos;)I", at = @At("HEAD"), cancellable = true)
    private void getLight(BlockPos pos, CallbackInfoReturnable<Integer> cir) {
        if (Settings.FULLBRIGHT) {
            helper.getLight(pos, cir);
        }
    }

    @Inject(method = "getLight(Lnet/minecraft/util/BlockPos;Z)I", at = @At("HEAD"), cancellable = true)
    private void getLight(BlockPos pos, boolean checkNeighbors, CallbackInfoReturnable<Integer> cir) {
        if (Settings.FULLBRIGHT) {
            helper.getLight(pos, checkNeighbors, cir);
        }
    }
}
