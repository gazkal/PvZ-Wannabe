package tp.pr3.Logic.Objects;


public class BucketheadZombie extends Zombie {
		
	public BucketheadZombie ()
	{
		super(8, true, 4,4);
	}

	public String getInfo() {
		return "P" + "[" + getHp() + "]";
	}
	public String getReleaseInfo() {
		return "W" + "[" + getHp() + "]";
	}
	public String getDebugInfo() {
		return "W" + "[" + "l:"+ getHp()+",x:"+getPosRow()+",y:"+ getPosCol() +",t:"+moveCounter +"]";
	}
	public String externalise() {
		return "W" + ":" + getHp()+ ":" +getPosRow()+ ":" + getPosCol() + ":" + getMoveCounter();
	}
}
