package tp.pr3.Controller.Command.SubCommands;

import tp.pr3.Logic.Game;

public class ExitCommand extends NoParamsCommand {

	public ExitCommand() {
		super("EXIT", "[E]xit","Terminates the program.");
	}

	@Override
	public boolean execute(Game game) {
		game.resetGame(game.getDim_x(), game.getDim_y());
		return true;
	}

	
}
