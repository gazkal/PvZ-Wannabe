package tp.pr3.Controller.Command.SubCommands;

import tp.pr3.Logic.Game;

public class ResetCommand extends NoParamsCommand {

	public ResetCommand() {
		super("RESET", "[R]eset", "Starts a new game.");
	}

	@Override
	public boolean execute(Game game) {
		game.resetGame(game.getDim_x(), game.getDim_y());
		return true;
	}

}
