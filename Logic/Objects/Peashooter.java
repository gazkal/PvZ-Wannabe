package tp.pr3.Logic.Objects;


final public class Peashooter extends Plant{
	
	private int dmg = 1;
	
	public Peashooter ()
	{
		super("PEASHOOTER",3, true, 50, 1);
	}

	public String getInfo() {
		return "[P]eashooter: " + "Cost: " + getCost() + " suncoins" + " Harm: " + dmg;
	}
	public String getReleaseInfo() {
		return "P" + "[" + getHp() + "]";
	}
	public String getDebugInfo() {
		return "P" + "[" + "l:"+ getHp()+",x:"+getPosRow()+",y:"+ getPosCol() +",t:"+ getShoot_freq() +"]";
	}
	public String externalise() {
		return "P" + ":" + getHp()+ ":" +getPosRow()+ ":" + getPosCol() + ":" + getShoot_freq();
	}

	
	public void update() {
		Position bullet = new Position (0,0);
		bullet.setRow(p.getRow());
		bullet.setColumn(p.getColumn());
		g.hitClosestZombie(bullet, getDmg()); 
	}
	public int getDmg() {
		return dmg;
	}

	public void setDmg(int dmg) {
		this.dmg = dmg;
	}
	@Override
	public Plant parse(String name) {
		if (name.equals(this.name.substring(0, 1)) || this.name.equals(name))
			return new Peashooter();
		
		return null;
	}
	
}
