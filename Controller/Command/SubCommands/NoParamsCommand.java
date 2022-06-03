package tp.pr3.Controller.Command.SubCommands;

import tp.pr3.Controller.Command.Command;
import tp.pr3.Exceptions.*;

public abstract class NoParamsCommand extends Command {

	public NoParamsCommand(String commandText, String commandTextMsg, String helpTextMsg) {
		super(commandText, commandTextMsg, helpTextMsg);
	}

	public Command parse(String[] commandWords)throws CommandParserException{
		
		if(!commandWords[0].equals(commandName) && !commandWords[0].equals(commandName.substring(0, 1)))
			return null;
		
		if(commandWords.length!=1) {
			throw new CommandParserException( commandName + " command has no arguments");
		}

		
		return this;
	}

}
