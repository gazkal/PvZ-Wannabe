
package tp.pr3;

import java.util.Random;

import tp.pr3.Controller.*;
import tp.pr3.Logic.*;
import tp.pr3.Logic.GamePrinter.GamePrinterInter;
import tp.pr3.Logic.GamePrinter.ReleasePrinter;

public class PlantsVsZombies {

	static final int filas=4, colum=8;
	public static void main(String[] args) {
		try {
		args[0]  = args[0].trim();
		String diff = args[0];
		long seed = new Random().nextInt(1000);
		if (args.length == 2)
		{
			args[1] = args[1].trim();
			seed = Long.parseLong(args[1]);
		}
		Random rand = new Random (seed);
		Level l = Level.parse(diff);
		GamePrinterInter gP = new ReleasePrinter(filas,colum);
		Game game = new Game(filas, colum, l, rand, seed,gP);
		Controller controller = new Controller(game); 
		controller.run();
		}
		catch( NumberFormatException ex){
			System.out.println("Seed used is invalid: Please introduce a number below 1000");
		}

	}

}
