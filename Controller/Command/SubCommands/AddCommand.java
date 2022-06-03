package tp.pr3.Controller.Command.SubCommands;

import tp.pr3.Exceptions.*;
import tp.pr3.Controller.Command.Command;
import tp.pr3.Logic.Game;
import tp.pr3.Logic.Objects.Plant;
import tp.pr3.Util.PlantFactory;

public class AddCommand extends Command {
	
	private int x;
	private int y;
	private String plantname;
	
	public AddCommand(int x, int y, String plantname) {
		super("ADD", "[A]dd <plant> <x> <y>", "Adds a plant in position x, y.");
		this.x =x;
		this.y = y;
		this.plantname = plantname;
	}

	public AddCommand() {
		super("ADD", "[A]dd <plant> <x> <y>", "Adds a plant in position x, y.");
	}

	public boolean execute(Game game) throws CommandExecuteException{
		boolean ok=false;
		Plant plant = PlantFactory.getPlant(plantname);
		if(plant!=null) {
			if (game.InsertPlant(x, y, plant)) {
				ok=true;
				game.update();
			}
		}
		else
			throw new CommandExecuteException("Unknown plant name: " + plantname);
		return ok;
	}

	public Command parse(String[] commandWords) throws CommandParserException{
		AddCommand c = null;
			if(!commandWords[0].equals(commandName) && !commandWords[0].equals(commandName.substring(0, 1)))
				return c;
			try {
		
			if(commandWords.length != 4)
				throw new CommandParserException("Incorrect number of arguments for ADD command: [A]dd <plant> <x> <y>");
		
			c = new AddCommand(Integer.parseInt(commandWords[2]), Integer.parseInt(commandWords[3]), commandWords[1]);
		}
		catch( NumberFormatException ex){
			throw new CommandParserException("Invalid argument for add command, number expected: [A]dd <plant> <x> <y>");
		}
		return c;
		
		
	}
}
