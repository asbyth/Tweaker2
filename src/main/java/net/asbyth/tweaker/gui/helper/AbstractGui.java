package net.asbyth.tweaker.gui.helper;

import net.asbyth.tweaker.Tweaker;
import net.minecraft.util.EnumChatFormatting;

public class AbstractGui extends ButtonTooltip {

    protected int getRowPos(int rowNumber) {
        return 55 + rowNumber * 23;
    }

    protected int getCenter() {
        return width / 2;
    }

    protected String getSuffix(boolean option, String label) {
        return option ? (EnumChatFormatting.GREEN + label) : (EnumChatFormatting.RED + label);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void onGuiClosed() {
        Tweaker.INSTANCE.saveConfig();
    }

    @Override
    protected String getButtonTooltip(int buttonId) {
        return null;
    }
}
