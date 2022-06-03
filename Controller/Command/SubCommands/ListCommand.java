package tp.pr3.Controller.Command.SubCommands;

import tp.pr3.Logic.Game;
import tp.pr3.Util.PlantFactory;

public class ListCommand extends NoParamsCommand {

	public ListCommand() {
		super("LIST", "[L]ist", "Prints the list of available plants.");
	}

	@Override
	public boolean execute(Game game) {
		System.out.println(PlantFactory.infoAvailablePlants());
		return false;	
	}
}
