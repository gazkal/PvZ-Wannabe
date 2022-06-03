package tp.pr3.Controller.Command.SubCommands;

import tp.pr3.Controller.Command.Command;
import tp.pr3.Exceptions.CommandExecuteException;
import tp.pr3.Exceptions.CommandParserException;
import tp.pr3.Logic.Game;
import tp.pr3.Logic.GamePrinter.*;

public class PrintModeCommand extends Command {

	private String mode;
	protected static BoardPrinter[] availableModes = {new ReleasePrinter(), new DebugPrinter()};
	
	public PrintModeCommand(String mode) {
		super("PRINTMODE RELEASE DEBUG", "[P]rintMode <mode>", "change print mode [Release|Debug]");
		this.mode = mode;
	}
	public PrintModeCommand() {
		super("PRINTMODE RELEASE DEBUG", "[P]rintMode <mode>", "change print mode [Release|Debug]");
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException{
			boolean ok=false;
			for(GamePrinterInter bp: availableModes) {
				bp = bp.parse(mode, game);
				if(bp != null) {
					ok = true;
					game.SetGamePrinter(bp);
				}
			}
			if(!ok)
			throw new CommandExecuteException("Unknown print mode:" + mode);
			
			return ok;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParserException {
		if(!commandWords[0].equals(commandName.substring(0, 9)) && !commandWords[0].equals(commandName.substring(0, 1)))
			return null;
		if(commandWords.length != 2)
			throw new CommandParserException("Incorrect number of arguments for PRINTMODE command: [P]rintMode release|debug");
		PrintModeCommand pC = new PrintModeCommand(commandWords[1]);
		return pC;
	}

}
