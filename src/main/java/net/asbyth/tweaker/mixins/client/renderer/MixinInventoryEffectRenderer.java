package net.asbyth.tweaker.mixins.client.renderer;

import net.asbyth.tweaker.config.Settings;
import net.asbyth.tweaker.mixins.client.gui.inventory.IMixinGuiContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.inventory.Container;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(InventoryEffectRenderer.class)
public abstract class MixinInventoryEffectRenderer extends GuiContainer {

    public MixinInventoryEffectRenderer(Container inventorySlotsIn) {
        super(inventorySlotsIn);
    }

    /**
     * @author asbyth
     * @reason center inventory when potion effect is applied
     */
    @Overwrite
    protected void updateActivePotionEffects() {
        ((IMixinInventoryEffectRenderer) this).setHasActivePotionEffects(!Minecraft.getMinecraft().thePlayer.getActivePotionEffects().isEmpty());
        if (!Minecraft.getMinecraft().thePlayer.getActivePotionEffects().isEmpty()) {
            if (Settings.INVENTORYPOTIONPOSITION) {
                ((IMixinGuiContainer) this).setGuiLeft((width - xSize) / 2);
            } else {
                ((IMixinGuiContainer) this).setGuiLeft(160 + (width - xSize - 200) / 2);
            }
        }
    }
}
