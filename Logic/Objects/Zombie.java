package tp.pr3.Logic.Objects;


abstract public class Zombie extends GameObject {
	protected int dmg=1;
	protected int walkfreq;
	protected int moveCounter;
	public Zombie (int hp, boolean alive,int walkfreq,int moveCounter )
	{
		super ("Zombie",hp,alive);
		this.walkfreq = walkfreq;
		this.moveCounter = moveCounter;
	}
	public void update() {
		Position ZombieAction = new Position(getPosRow(), getPosCol()-1);
		if(!zombieAttack(ZombieAction))
			ZombieMove(ZombieAction);
	}
	
	public int getDmg() {
		return dmg;
	}
	public void setDmg(int dmg) {
		this.dmg = dmg;
	}
	public int getWalkfreq() {
		return walkfreq;
	}
	public void setWalkfreq(int walkfreq) {
		this.walkfreq = walkfreq;
	}
	public int getMoveCounter() {
		return moveCounter;
	}
	public void setMoveCounter(int moveCounter) {
		this.moveCounter = moveCounter;
	}
	
	public boolean zombieAttack(Position Target) { 
		return g.attackPlant(Target, getDmg());
	}
	
	public void zombieCounterManager(Position zombieAction) { 
		if(moveCounter==0) {
			setPosCol(zombieAction.getColumn());
			moveCounter = walkfreq;
			moveCounter--;
		}
		else
			moveCounter--;
	}
	
	public void ZombieMove(Position zombieAction) {
		if(!g.gLPisPosFull(zombieAction) && !g.gLZisPosFull(zombieAction)) {
			zombieCounterManager(zombieAction);
			if(g.checkZombiesWin(getPosCol()))
				g.setGameover(true) ;
		}
	}
}
