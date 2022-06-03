package tp.pr3.Logic;


public class SuncoinManager {
	static final int startSun = 50;
	private int currentSun;
	
	public SuncoinManager (int n)
	{
		this.currentSun = n;
	}
	
	public int addSun (int n) //anadir suncoins
	{
		return this.currentSun += n;
	}
	
	public boolean removeSun(int n) //quitar suncions
	{
		boolean done = false;
		int tmp = this.currentSun - n;
		if (tmp >= 0) //si el valor de tmp es >= 0 se cambia el valor del booleano
		{
			this.currentSun -= n;
			done = true;
		}
		return done;
	}
	
	public void setCurrentSun (int n) {
		this.currentSun = n;
	}
	
	public int getStartsun() {
		return startSun;
	}
	
	public int getcurrentSun() {
		return this.currentSun;
	}

	public void removeSunPs(int cost) {
		currentSun = currentSun - cost;
	}

	/*public void removeSunPS() {
		currentSun = currentSun - SunflowerOld.getCost();
	}*/

}
