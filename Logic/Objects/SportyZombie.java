package tp.pr3.Logic.Objects;


public class SportyZombie extends Zombie{
	
	public SportyZombie ()
	{
		super(2, true, 1, 1);
	}
	
	public String getInfo() {
		return "P" + "[" + getHp() + "]";
	}
	public String getReleaseInfo() {
		return "X" + "[" + getHp() + "]";
	}
	public String getDebugInfo() {
		return "X" + "[" + "l:"+ getHp()+",x:"+getPosRow()+",y:"+ getPosCol() +",t:"+moveCounter +"]";
	}
	public String externalise() {
		return "X" + ":" + getHp()+ ":" +getPosRow()+ ":" + getPosCol() + ":" + getMoveCounter();
	}
}
