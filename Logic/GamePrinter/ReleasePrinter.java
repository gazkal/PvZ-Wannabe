package tp.pr3.Logic.GamePrinter;

import tp.pr3.Logic.Game;
import tp.pr3.Logic.Objects.Position;

public class ReleasePrinter extends BoardPrinter {
	private int cellsize;
	
	public ReleasePrinter(int dimX,int dimY) {
		super(dimX,dimY);
		cellsize = 7;
	}
	public ReleasePrinter() {
		super("RELEASE");
	}
	public void encodeGame(Game game) {
		board = new String[dimX][dimY];
		Position p;
		int aux;
		for(int i = 0; i < dimX; i++) {
			for(int j = 0; j < dimY; j++) {
				p = new Position (i, j);
				if (game.gLPisPosFull(p) && game.getLPcont() > 0) {
					aux = game.getPlant(p);
					board[i][j] = game.plantsToStringR(aux);
				}
				if (game.gLZisPosFull(p) && game.getLZcont() > 0) {
					aux = game.getZombie(p);
					board[i][j] = game.zombiesToStringR(aux);
				}
				if (!game.gLZisPosFull(p) && !game.gLPisPosFull(p)) board[i][j] = " ";
			}
		}
		
		
	}
	@Override
	public String printGame(Game g) {
		encodeGame(g);
		return "Number of cycles: " + g.getCycles()	+ "\n" + "Sun coins: " + g.getSnMcurrentSun()+ "\n"+"Remaining zombies: " + g.getzMzombieGenCont() + BoardtoString(cellsize);
	}
	public GamePrinterInter parse(String mode, Game g) {
		if(mode.equals(name)) {
			GamePrinterInter gP = new ReleasePrinter(g.getDim_x(), g.getDim_y());
			return gP;
		}
		return null;
	}
}
