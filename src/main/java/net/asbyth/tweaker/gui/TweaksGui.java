package net.asbyth.tweaker.gui;

import net.asbyth.tweaker.config.Settings;
import net.asbyth.tweaker.gui.helper.AbstractButton;
import net.asbyth.tweaker.gui.helper.AbstractGui;
import net.minecraft.client.gui.GuiButton;

public class TweaksGui extends AbstractGui {

    @Override
    public void initGui() {
        buttonList.add(new AbstractButton(0, getCenter() - 155, getRowPos(1), 150, 20, getSuffix(Settings.NAUSEAEFFECT, "Nausea Effect")));
        buttonList.add(new AbstractButton(1, getCenter() + 5, getRowPos(1), 150, 20, getSuffix(Settings.RESPAWNBUTTON, "Respawn Button")));
        buttonList.add(new AbstractButton(2, getCenter() - 155, getRowPos(2), 150, 20, getSuffix(Settings.ARMPOSITION, "Arm Position")));
        buttonList.add(new AbstractButton(3, getCenter() + 5, getRowPos(2), 150, 20, getSuffix(Settings.INSTANTWORLDSWITCHING, "Instant World Switching")));
        buttonList.add(new AbstractButton(4, getCenter() + 5, getRowPos(3), 150, 20, getSuffix(Settings.WINDOWEDFULLSCREEN, "Windowed Fullscreen")));
        buttonList.add(new AbstractButton(5, getCenter() - 155, getRowPos(3), 150, 20, getSuffix(Settings.MOUSEDELAYFIX, "Mouse Delay Fix")));
        buttonList.add(new AbstractButton(6, getCenter() + 5, getRowPos(4), 150, 20, getSuffix(Settings.NOCLOSEMYCHAT, "NoCloseMyChat")));
        buttonList.add(new AbstractButton(7, getCenter() - 155, getRowPos(4), 150, 20, getSuffix(Settings.SCOREBOARDCRASH, "Scoreboard Crash")));
        buttonList.add(new AbstractButton(8, getCenter() + 5, getRowPos(5), 150, 20, getSuffix(Settings.ASYNCSCREENSHOTS, "Async Screenshots")));
        buttonList.add(new AbstractButton(9, getCenter() - 155, getRowPos(5), 150, 20, getSuffix(Settings.FULLBRIGHT, "Fullbright")));
        buttonList.add(new AbstractButton(10, getCenter() - 155, getRowPos(6), 150, 20, getSuffix(Settings.INVENTORYPOTIONPOSITION, "Potion Effect Position")));
        buttonList.add(new AbstractButton(11, getCenter() + 5, getRowPos(6), 150, 20, getSuffix(Settings.VOIDFLICKERFIX, "Void Flicker Fix")));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);

        drawCenteredString(fontRendererObj, "Tweaker (2.0)", getCenter(), getRowPos(0), 16777215);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 0:
                Settings.NAUSEAEFFECT = !Settings.NAUSEAEFFECT;
                button.displayString = getSuffix(Settings.NAUSEAEFFECT, "Nausea Effect");
                break;

            case 1:
                Settings.RESPAWNBUTTON = !Settings.RESPAWNBUTTON;
                button.displayString = getSuffix(Settings.RESPAWNBUTTON, "Respawn Button");
                break;

            case 2:
                Settings.ARMPOSITION = !Settings.ARMPOSITION;
                button.displayString = getSuffix(Settings.ARMPOSITION, "Arm Position");
                break;

            case 3:
                Settings.INSTANTWORLDSWITCHING = !Settings.INSTANTWORLDSWITCHING;
                button.displayString = getSuffix(Settings.INSTANTWORLDSWITCHING, "Instant World Switching");
                break;

            case 4:
                Settings.WINDOWEDFULLSCREEN = !Settings.WINDOWEDFULLSCREEN;
                button.displayString = getSuffix(Settings.WINDOWEDFULLSCREEN, "Windowed Fullscreen");
                break;

            case 5:
                Settings.MOUSEDELAYFIX = !Settings.MOUSEDELAYFIX;
                button.displayString = getSuffix(Settings.MOUSEDELAYFIX, "Mouse Delay Fix");
                break;

            case 6:
                Settings.NOCLOSEMYCHAT = !Settings.NOCLOSEMYCHAT;
                button.displayString = getSuffix(Settings.NOCLOSEMYCHAT, "NoCloseMyChat");
                break;

            case 7:
                Settings.SCOREBOARDCRASH = !Settings.SCOREBOARDCRASH;
                button.displayString = getSuffix(Settings.SCOREBOARDCRASH, "Scoreboard Crash");
                break;

            case 8:
                Settings.ASYNCSCREENSHOTS = !Settings.ASYNCSCREENSHOTS;
                button.displayString = getSuffix(Settings.ASYNCSCREENSHOTS, "Async Screenshots");
                break;

            case 9:
                Settings.FULLBRIGHT = !Settings.FULLBRIGHT;
                button.displayString = getSuffix(Settings.FULLBRIGHT, "Fullbright");
                break;

            case 10:
                Settings.INVENTORYPOTIONPOSITION = !Settings.INVENTORYPOTIONPOSITION;
                button.displayString = getSuffix(Settings.INVENTORYPOTIONPOSITION, "Potion Effect Position");
                break;

            case 11:
                Settings.VOIDFLICKERFIX = !Settings.VOIDFLICKERFIX;
                button.displayString = getSuffix(Settings.VOIDFLICKERFIX, "Void Flicker Fix");
                break;
        }
    }

    @Override
    protected String getButtonTooltip(int buttonId) {
        switch (buttonId) {
            case 0:
                return "Removes the Portal Effect when clearing the Nausea potion effect.";

            case 1:
                return "Fixes the Respawn button in the menu when you die and toggle fullscreen._p _cRecommended";

            case 2:
                return "Fixes your arm position when you mount an entity and toggle perspective.";

            case 3:
                return "Removes the dirt screen when you switch worlds.";

            case 4:
                return "Makes your game borderless so you can move your mouse to another monitor while fullscreened.";

            case 5:
                return "Removes the Mouse Delay introduced in 1.8 that can cause some of your hits to miss._p _cRecommended";

            case 6:
                return "Stops Hypixel (and other servers) from taking you out of chat when a game like Skywars starts.";

            case 7:
                return "Fixes a crash with the scoreboard when logging into a server._p _cRecommended";

            case 8:
                return "Removes the freezing from screenshots.";

            case 9:
                return "Removes all lighting updates and makes the world seem as if there was no darkness.";

            case 10:
                return "Moves potion effects in the inventory to the left so the inventory is perfectly centered.";

            case 11:
                return "Sets the sky height to 0.0 as if it was a Superflat world, effectively removing the black part below y63.";

            default:
                return null;
        }
    }
}
