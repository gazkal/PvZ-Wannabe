package tp.pr3.Controller.Command;

import tp.pr3.Logic.Game;
import tp.pr3.Exceptions.*;

public abstract class Command {
	private String helpText;
	private String helpInfo;
	protected final String commandName;
	
	public Command(String commandText, String commandTextMsg, String helpTextMsg){
		commandName = commandText;
		helpText = commandTextMsg;
		helpInfo = helpTextMsg;
	}

	public abstract boolean execute(Game game)throws CommandExecuteException;
	public abstract Command parse(String[] commandWords)throws CommandParserException;
	public String helpText(){return " " + helpText + ": " + helpInfo;}
	
}
