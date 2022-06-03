package tp.pr3.Controller.Command.SubCommands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import tp.pr3.Exceptions.*;
import tp.pr3.Controller.Command.Command;
import tp.pr3.Logic.Game;


import tp.pr3.Util.MyStringUtils;

public class LoadCommand extends Command {
	private File fileToLoad;
	private String header = "Plants Vs Zombies v3.0";
	
	public LoadCommand(String filename) {
		super("LOAD", "[L]oad <filename>", "Loads game from filename.dat");
		fileToLoad = new File(filename);
	}
	public LoadCommand() {
		super("LOAD", "[L]oad <filename>", "Loads game from filename.dat");
	}
	public boolean execute(Game game)throws CommandExecuteException
	{	
		boolean ok=false;
		Game backup = null;
		try (BufferedReader input = new BufferedReader(new FileReader(fileToLoad))) {
			backup = (Game) game.clone();
			String line;
			line = input.readLine();
			if (!line.equals(header))
				throw new CommandExecuteException("Invalid format");
			line = input.readLine();
			game.load(input);
			ok = true;
		}
		catch( Exception ex) {
			game.restore(backup);
			throw new CommandExecuteException("Bad file: " + ex.getMessage());
		}
		return ok;
		
		
	}
	
	public Command parse(String[] commandWords)throws CommandParserException {
		if(!commandWords[0].equals(commandName) && !commandWords[0].equals(commandName.substring(0, 1)))
			return null;
		String completename = commandWords[1] + ".DAT";
		if(commandWords.length==1)
			throw new CommandParserException("Incorrect number of arguments for Load command: [L]oad <filename.DAT>");
		else if(!MyStringUtils.isValidFilename(completename))
			 throw new CommandParserException("Couldn´t load the file: filename not valid");
		else if(!MyStringUtils.fileExists(completename))
			 throw new CommandParserException("Couldn´t load the file: file does not exist");
		else if(!MyStringUtils.isReadable(completename))
			throw new CommandParserException("Couldn´t load the file: file is not readable");
		LoadCommand c = new LoadCommand(completename);
		return c;
	}
}
