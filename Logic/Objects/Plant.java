package tp.pr3.Logic.Objects;


abstract public class Plant extends GameObject{
	
	private int cost;
	private int shoot_freq;

	public Plant (String name,int hp, boolean alive, int cost, int shoot_freq)
	{
		super (name,hp, alive);
		this.setCost(cost);
		this.shoot_freq = shoot_freq;
	}

	public int getShoot_freq() {
		return shoot_freq;
	}

	public void setShoot_freq(int shoot_freq) {
		this.shoot_freq = shoot_freq;
	}
	
	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
	//ABSTRACT CLASSES
	abstract public String getInfo();
	abstract public Plant parse(String name);
}
