package tp.pr3.Controller.Command.SubCommands;

import tp.pr3.Controller.Command.Command;
import tp.pr3.Logic.Game;

public class NoneCommand extends Command {

	public NoneCommand() {
		super("", "[None]", "Skips cycle.");
	}

	@Override
	public boolean execute(Game game) {
		game.update();
		return true;
	}

	@Override
	public Command parse(String[] commandWords) {
	
		if(!commandWords[0].equals(commandName))
			return null;
		
		return this;
	}


}
