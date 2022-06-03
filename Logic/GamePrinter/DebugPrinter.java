package tp.pr3.Logic.GamePrinter;

import tp.pr3.Logic.Game;


public class DebugPrinter extends BoardPrinter {

	private int cellsize;
	
	public DebugPrinter(int dimY) {
		super(1,dimY);
		cellsize = 17;
	}

	public DebugPrinter() {
		super("DEBUG");
	}

	public void encodeGame(Game game) { //Encode it in debug form, needs new PlantList and zombieList
		dimY=game.getLPcont() + game.getLZcont();
		board = new String[1][dimY];
		for(int i=0; i< game.getLPcont();i++) {
				board[0][i]= game.plantsToStringD(i);

		}
		for(int i=0; i < game.getLZcont();i++) {
				board[0][i+game.getLPcont()] = game.zombiesToStringD(i);
		}
							
	}
	
		@Override
		public String printGame(Game g) {
			encodeGame(g);
			return 	"Number of cycles: " + g.getCycles()	+ "\n" + "Sun coins: " + g.getSnMcurrentSun()+ "\n"+"Remaining zombies: " + g.getzMzombieGenCont() + "\n"+ "Level: " + g.getLevel() + "\n"+ "Seed: " + g.getSeed()+ "\n" + BoardtoString(cellsize);
		}
		public GamePrinterInter parse(String mode, Game g) {
			if(mode.equals(name)) {
				GamePrinterInter gP = new DebugPrinter(g.getLPcont() + g.getLZcont());
				return gP;
			}
			return null;
		}

}
