package tp.pr3.Controller.Command;

import tp.pr3.Controller.Command.SubCommands.AddCommand;
import tp.pr3.Controller.Command.SubCommands.ExitCommand;
import tp.pr3.Controller.Command.SubCommands.HelpCommand;
import tp.pr3.Controller.Command.SubCommands.ListCommand;
import tp.pr3.Controller.Command.SubCommands.LoadCommand;
import tp.pr3.Controller.Command.SubCommands.NoneCommand;
import tp.pr3.Controller.Command.SubCommands.PrintModeCommand;
import tp.pr3.Controller.Command.SubCommands.ResetCommand;
import tp.pr3.Controller.Command.SubCommands.SaveCommand;
import tp.pr3.Exceptions.CommandParserException;


public class CommandParser {
	
	private static Command[] availableCommands = {
			new AddCommand(),
			new HelpCommand(),
			new ResetCommand(),
			new ExitCommand(),
			new ListCommand(),
			new PrintModeCommand(),
			new NoneCommand(),
			new SaveCommand(),
			new LoadCommand()
			};
	
	public static Command parseCommand(String[ ] commandWords) throws CommandParserException {
		
		for(Command c: availableCommands) {
			c = c.parse(commandWords);
			if(c!= null)
				return c;
		}
		
		return null;
	}
	
	public static String commandHelp() {
		String helpstr="";
		for(Command c:availableCommands){
			helpstr += c.helpText() + "\n";
		}
		return helpstr;
	}
}
