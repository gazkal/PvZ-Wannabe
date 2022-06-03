package tp.pr3.Logic.GamePrinter;

import tp.pr3.Logic.Game;
import tp.pr3.Util.MyStringUtils;

public abstract class BoardPrinter implements GamePrinterInter {
	protected String name;
	protected int dimX,dimY;
	final String space = " ";
	protected String[][] board = new String[4][8];
	public BoardPrinter(int dimX, int dimY) {
		this.dimX = dimX;
		this.dimY = dimY;
	}
	
	public BoardPrinter(String name) {
		this.name=name;
	}

	public String BoardtoString(int cellsize) {
			int marginSize = 2;
			String vDelimiter = "|";
			String hDelimiter = "-";
			
			String rowDelimiter = MyStringUtils.repeat(hDelimiter, (dimY * (cellsize + 1)) - 1);
			String margin = MyStringUtils.repeat(space, marginSize);
			String lineDelimiter = String.format("%n%s%s%n", margin + space, rowDelimiter);
			
			StringBuilder str = new StringBuilder();
			
			str.append(lineDelimiter);
			
			for(int i=0; i<dimX; i++) {
					str.append(margin).append(vDelimiter);
					for (int j=0; j<dimY; j++) {
						str.append( MyStringUtils.centre(board[i][j], cellsize)).append(vDelimiter);
					}
					str.append(lineDelimiter);
			}
			return str.toString();
	}
	public abstract void encodeGame(Game g);

	public abstract GamePrinterInter parse(String mode, Game game);
}