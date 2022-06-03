package tp.pr3.Controller.Command.SubCommands;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import tp.pr3.Controller.Command.Command;
import tp.pr3.Exceptions.CommandExecuteException;
import tp.pr3.Exceptions.CommandParserException;
import tp.pr3.Logic.Game;
import tp.pr3.Util.MyStringUtils;

public class SaveCommand extends Command {
	private File fileToSave;
	
	public SaveCommand(String filename) {
		super("SAVE", "[S]ave <filename>", "Saves game in filename.dat");
		fileToSave = new File(filename + ".DAT");
	}
	public SaveCommand() {
		super("SAVE", "[S]ave <filename>", "Saves game in filename.dat");
	}
	public boolean execute(Game game) throws CommandExecuteException
	{
		boolean ok = false;
		try (BufferedWriter output = new BufferedWriter(new FileWriter(fileToSave))){	
		output.write("Plants Vs Zombies v3.0");
		game.store(output);
		ok=true;
		System.out.println(
				"Game successfully saved in file " + fileToSave.getName() + "; Use the load command to reload it");
		}
		catch (IOException ex) {
			throw new CommandExecuteException(ex.getMessage()); 
		}
		return ok;
	}
	
	public Command parse(String[] commandWords) throws CommandParserException {
		if(!commandWords[0].equals(commandName) && !commandWords[0].equals(commandName.substring(0, 1)))
			return null;
		else if(commandWords.length==1)
			throw new CommandParserException("Incorrect number of arguments for Load command: [S]ave <filename.dat>");
		else if(!MyStringUtils.isValidFilename(commandWords[1]) )
			 throw new CommandParserException("Couldn´t load the file: filename not valid");

		SaveCommand c = new SaveCommand(commandWords[1]);
		return c;
	}
}
