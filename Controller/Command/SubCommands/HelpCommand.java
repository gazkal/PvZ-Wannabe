package tp.pr3.Controller.Command.SubCommands;

import tp.pr3.Controller.Command.CommandParser;
import tp.pr3.Logic.Game;


public class HelpCommand extends NoParamsCommand {

	public HelpCommand() {
		super("HELP", "[H]elp", "Prints this help message");
	}

	@Override
	public boolean execute(Game game) {
		
		System.out.println(CommandParser.commandHelp());//Maybe printed in controller?
		return false;
	}
	
}
