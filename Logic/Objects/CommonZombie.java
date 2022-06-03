package tp.pr3.Logic.Objects;


final public class CommonZombie extends Zombie {
	
	public CommonZombie ()
	{
		super(5, true, 2, 2);
	}

	public String getInfo() {
		return "P" + "[" + getHp() + "]";
	}
	public String getReleaseInfo() {
		return "Z" + "[" + getHp() + "]";
	}
	public String getDebugInfo() {
		return "Z" + "[" + "l:"+ getHp()+",x:"+getPosRow()+",y:"+ getPosCol() +",t:"+moveCounter +"]";
	}
	public String externalise() {
		return "Z" + ":" + getHp()+ ":" +getPosRow()+ ":" + getPosCol() + ":" + getMoveCounter();
	}
	
}
