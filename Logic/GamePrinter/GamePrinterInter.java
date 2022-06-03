package tp.pr3.Logic.GamePrinter;

import tp.pr3.Logic.Game;

public interface GamePrinterInter {
	public String printGame(Game g);

	public GamePrinterInter parse(String mode, Game game);
}
