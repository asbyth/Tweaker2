package net.asbyth.tweaker.mixins.entity;

import net.asbyth.tweaker.config.Settings;
import net.asbyth.tweaker.implementation.EntityLivingBaseHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityLivingBase.class)
public abstract class MixinEntityLivingBase extends MixinEntity {

    private EntityLivingBaseHelper helper = new EntityLivingBaseHelper((EntityLivingBase) (Object) this);

    @Inject(method = "getLook", at = @At("HEAD"), cancellable = true)
    private void fixMouseDelay(float partialTicks, CallbackInfoReturnable<Vec3> cir) {
        if (Settings.MOUSEDELAYFIX) {
            helper.getLook(cir, super.getLook(partialTicks));
        }
    }
}
