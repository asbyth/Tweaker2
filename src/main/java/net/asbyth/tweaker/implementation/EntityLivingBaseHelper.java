package net.asbyth.tweaker.implementation;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.Vec3;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public class EntityLivingBaseHelper {

    private EntityLivingBase parent;

    public EntityLivingBaseHelper(EntityLivingBase parent) {
        this.parent = parent;
    }

    public void getLook(CallbackInfoReturnable<Vec3> cir, Vec3 look) {
        EntityLivingBase base = parent;

        if (base instanceof EntityPlayerSP) {
            cir.setReturnValue(look);
        }
    }
}
