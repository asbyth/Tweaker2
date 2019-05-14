package net.asbyth.tweaker.mixins.client.entity;

import com.mojang.authlib.GameProfile;
import net.asbyth.tweaker.config.Settings;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.potion.Potion;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EntityPlayerSP.class)
public class MixinEntityPlayerSP extends AbstractClientPlayer {

    @Shadow public float prevTimeInPortal;
    @Shadow public float timeInPortal;

    public MixinEntityPlayerSP(World worldIn, GameProfile playerProfile) {
        super(worldIn, playerProfile);
    }

    /**
     * @author asbyth
     * @reason fixes the nether portal effect when clearing nausea potion effect
     */
    @Override
    public void removePotionEffectClient(int potionId) {
        if (potionId == Potion.confusion.id) {
            if (Settings.NAUSEAEFFECT) {
                prevTimeInPortal = 0.0F;
                timeInPortal = 0.0F;
            }
        }

        removePotionEffect(potionId);
    }
}
