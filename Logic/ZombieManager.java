package tp.pr3.Logic;


import java.util.Random;

public class ZombieManager {
	private int zombieGenCont; //generate zombie counter
	private Random rand;
	private static int[] numbersE = {0,0,0,0,0,1,0,0,0,0}; //0.1 freq
	private static int[] numbersH = {0,0,0,0,1,0,1,0,0,0}; //0.2 freq
	private static int[] numbersI = {0,0,1,0,1,0,1,0,0,0}; //0.3 freq
	
	public ZombieManager (int n, Random rand)
	{
		this.zombieGenCont = n;
		this.rand = rand;
	}
		
	public int toAdd (int cycle, Level l)
	{
		int i = 0; //valor dentro de la posicion [value] del array
		int value = randValue(10); //valor random entre 0 y 9 usada como la posicion de acceso al array
		if (this.zombieGenCont != 0)
		{
			if (l == Level.EASY) i = numbersE[value]; 
			else if (l == Level.HARD) i = numbersH[value];
			else if (l == Level.INSANE) i = numbersI[value];
		}
		//Si i == 0, no se anade un zombie
		return i;
	}
	
	private int randValue (int value)
	{
		int r = this.rand.nextInt(value);
		return r;
	}
	
	public int getzombieGenCont () {
		return this.zombieGenCont;
	}
	
	public void setZombieGenCont(int zombieGenCont) {
		this.zombieGenCont = zombieGenCont;
	}

	public void decreaseZombieGenCont() {
		this.zombieGenCont--;
		
	}
}
