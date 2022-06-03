package tp.pr3.Logic.Objects;



public class Cherrybomb extends Plant {
	
	private int dmg = 10;
	
	public Cherrybomb ()
	{
		super("CHERRYBOMB",2,true, 50, 2);
	}

	public String getInfo() {
		return "[C]herrybomb: " + "Cost: " + getCost() + " suncoins" + " Harm: " + dmg;
	}
	public String getReleaseInfo() {
		return "C" + "[" + getHp() + "]";
	}
	public String getDebugInfo() {
		return "C" + "[" + "l:"+ getHp()+",x:"+getPosRow()+",y:"+ getPosCol() +",t:"+ getShoot_freq() +"]";
	}
	public String externalise() {
		return "C" + ":" + getHp()+ ":" +getPosRow()+ ":" + getPosCol() + ":" + getShoot_freq();
	}
	@Override
	public void update() {
		if (getShoot_freq() == 0)
		{
			int i = 1;
			int j = -1;
			Position p = new Position(0, 0);
			//9 positions
			p.setRow(getPosRow()); p.setColumn(getPosCol() + i);
			g.areaDamage(p, dmg);
			p.setRow(getPosRow()); p.setColumn(getPosCol() + j);
			g.areaDamage(p, dmg);
			p.setRow(getPosRow() + i); p.setColumn(getPosCol());
			g.areaDamage(p, dmg);
			p.setRow(getPosRow() + j); p.setColumn(getPosCol());
			g.areaDamage(p, dmg);
			p.setRow(getPosRow() + j); p.setColumn(getPosCol() + i);
			g.areaDamage(p, dmg);
			p.setRow(getPosRow() + i); p.setColumn(getPosCol() + j);
			g.areaDamage(p, dmg);
			p.setRow(getPosRow() + i); p.setColumn(getPosCol() + i);
			g.areaDamage(p, dmg);
			p.setRow(getPosRow() + j); p.setColumn(getPosCol() + j);
			g.areaDamage(p, dmg);
			objectStatus(dmg); //explodes
		}
		setShoot_freq(getShoot_freq() - 1);
	}

	@Override
	public Plant parse(String name) {
		if (name.equals(this.name.substring(0, 1)) || this.name.equals(name))
			return new Cherrybomb();
		
		return null;
	}
}
