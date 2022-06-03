package tp.pr3.Controller;

import java.util.Scanner;
import tp.pr3.Exceptions.*;
import tp.pr3.Controller.Command.Command;
import tp.pr3.Controller.Command.CommandParser;
import tp.pr3.Exceptions.CommandParserException;
import tp.pr3.Logic.Game;

public class Controller {
	private Game game;
	private static Scanner sc = new Scanner (System.in);
	private String unknownCommand="Unknown command. Type 'help' to see the available commands";

	
	public Controller(Game game){
		this.game = game;
	}

	public void run() {
		drawBoard();
		while(!isGameOver()){
		continuegame();
		}
	}
	
	void continuegame() {
			System.out.print("Command > ");
			String arraycommand[] = sc.nextLine().trim().toUpperCase().split("\\s+"); 
			try {
			Command command = CommandParser.parseCommand(arraycommand);	
			if (command != null) {
				if(command.execute(game)){
					drawBoard();
				}
			}
			else
				printError(unknownCommand);
			
			}catch(CommandParserException | CommandExecuteException ex) {
				System.out.format(ex.getMessage() + " %n");
			}
			
	}
	
	public void drawBoard()
	{
		System.out.println(game.printGame());
			
	}
	public boolean isGameOver() {
		boolean gameover = false;
		if (this.game.isGameover() && game.getLZcont() == 0)
		{
			System.out.println("Player Wins!");
			gameover=true;
		}
		else if (this.game.isGameover())
		{
			System.out.println("Zombies Win");
			gameover=true;
		}
		return gameover;
		
	}
	public void giveup() {
		System.exit(0);;
	}
	public static void printError(String printModeError) {
		System.out.println(printModeError);	
	}
}

