package net.asbyth.tweaker.command;

import net.asbyth.tweaker.Tweaker;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
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
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        Tweaker.INSTANCE.gui();
    }

    @Override
    public int getRequiredPermissionLevel() {
        return -1;
    }
}
