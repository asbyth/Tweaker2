package net.asbyth.tweaker.command;

import net.asbyth.tweaker.gui.TweaksGui;
import net.asbyth.tweaker.util.TickScheduler;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class TweakerCommand extends CommandBase {
    @Override
    public String getCommandName() {
        return "tweaker";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/tweaker";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        TickScheduler.INSTANCE.schedule(0, () -> Minecraft.getMinecraft().displayGuiScreen(new TweaksGui()));
    }

    @Override
    public int getRequiredPermissionLevel() {
        return -1;
    }
}
