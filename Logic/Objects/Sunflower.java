package tp.pr3.Logic.Objects;


final public class Sunflower extends Plant {
	
	private int addSun = 10;
	
	public Sunflower()
	{
		super("SUNFLOWER",1,true,20, 2);
	}
	
	public String getInfo() {
		return "[S]unflower: " + "Cost: " + getCost() + " suncoins" + " Harm: " + 0;
	}
	public String getReleaseInfo() {
		return "S" + "[" + getHp() + "]";
	}
	public String getDebugInfo() {
		return "S" + "[" + "l:"+ getHp()+",x:"+getPosRow()+",y:"+ getPosCol() +",t:"+getShoot_freq()+"]";
	}
	public String externalise() {
		return "S" + ":" + getHp()+ ":" +getPosRow()+ ":" + getPosCol() + ":" + getShoot_freq();
	}
	
	public void update() {
		if(getShoot_freq() == 0)
			addSun();
		setShoot_freq(getShoot_freq() - 1);
	}
	
	public void addSun() {
		g.addSun(addSun);
		setShoot_freq(2); //SHOOT_FREQ in plant is the SUN_FREQ
	}
	@Override
	public Plant parse(String name) {
		if (name.equals(this.name.substring(0, 1)) || this.name.equals(name))
			return new Sunflower();
		
		return null;
	}
}
