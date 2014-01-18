package net.keplercore.common.command;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandNotFoundException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.SyntaxErrorException;
import net.minecraft.util.ChatComponentTranslation;

public class ParentCommand extends CommandBase
{
	private final String commandName;
	private final Map<String, CommandBase> childCommands = new HashMap<String, CommandBase>();

	public ParentCommand(String commandName)
	{
		this.commandName = commandName;
	}

	@Override
	public String getCommandName()
	{
		return commandName;
	}

	@Override
	public String getCommandUsage(ICommandSender sender)
	{
		String retVal = "";

		for (Map.Entry<String, CommandBase> childCommand : childCommands.entrySet())
		{
			retVal += "/" + commandName + " " + childCommand.getValue().getCommandUsage(sender) + "\n";
		}

		return retVal;
	}

	@Override
	public void processCommand(ICommandSender sender, String[] strings)
	{
		if (strings.length == 0)
		{
			throw new SyntaxErrorException(getCommandUsage(sender));
		}

		CommandBase command = childCommands.get(strings[0]);

		if (command == null)
		{
			throw new CommandNotFoundException();
		}
		else if (command.canCommandSenderUseCommand(sender))
		{
			command.processCommand(sender, Arrays.copyOfRange(strings, 1, strings.length));
		}
		else
		{
			ChatComponentTranslation message = new ChatComponentTranslation("commands.keplercore.noPermission");
			sender.func_145747_a(message);
		}
	}

	@Override
	public List<?> addTabCompletionOptions(ICommandSender sender, String[] strings)
	{
		if (strings.length == 1)
		{
			return getListOfStringsMatchingLastWord(strings, childCommands.keySet().toArray(new String[childCommands.keySet().size()]));
		}

		CommandBase command = childCommands.get(strings[0]);

		if (command != null)
		{
			return command.addTabCompletionOptions(sender, Arrays.copyOfRange(strings, 1, strings.length));
		}
		return null;
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender)
	{
		return true;
	}

	@Override
	public int compareTo(Object o)
	{
		return super.compareTo((ICommand) o);
	}

	public boolean registerChildCommand(CommandBase command)
	{
		if (childCommands.containsKey(command.getCommandName()))
		{
			return false;
		}
		else
		{
			childCommands.put(command.getCommandName(), command);
			return true;
		}
	}
}
