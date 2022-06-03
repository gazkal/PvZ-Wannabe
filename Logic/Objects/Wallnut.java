package tp.pr3.Logic.Objects;


public class Wallnut extends Plant{
	
	public Wallnut ()
	{
		super("WALLNUT",10,true,50, 0);
	}
	
	public String getInfo() {
		return "[W]allnut: " + "Cost: " + getCost() + " suncoins" + " Harm: " + 0;
	}
	public String getReleaseInfo() {
		return "W" + "[" + getHp() + "]";
	}
	public String getDebugInfo() {
		return "W" + "[" + "l:"+ getHp()+",x:"+getPosRow()+",y:"+ getPosCol() +",t:"+0 +"]";
	}
	public String externalise() {
		return "W" + ":" + getHp()+ ":" +getPosRow()+ ":" + getPosCol() + ":" + 0;
	}

	@Override
	public void update() {
		//Nothing needed here
		// TODO Auto-generated method stub
		
	}
	@Override
	public Plant parse(String name) {
		if (name.equals(this.name.substring(0, 1)) || this.name.equals(name))
			return new Wallnut();
		
		return null;
	}

}
