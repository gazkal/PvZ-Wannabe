package tp.pr3.Logic.Objects;

import tp.pr3.Logic.Game;

abstract public class GameObject{
	protected int hp;
	protected int counter;
	protected Position p;
	protected Game g;
	protected String name;
	
	public GameObject (String name,int hp ,boolean alive) {
		this.name = name;
		this.hp = hp;
	}
	public String getName() {
		return name;
	}
	public void objectStatus (int dmg){//estado del object con relacion con el dano que recibe
		this.hp -= dmg;
		if (this.hp <= 0){ 
			g.eliminateObject(this);
		}
	}

	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public int getPosRow() {
		return p.getRow();
	}
	
	public int getPosCol() {
		return p.getColumn();
	}
	
	public void setPos(Position p) {
		this.p = p;
	}
	public void setPosRow(int row) {
		this.p.setRow(row);
	}
	public void setPosCol(int col) {
		this.p.setColumn(col);
	}
	public void setGame(Game game) {
		this.g=game;
	}
	
	//ABSTRACT CLASSES
	abstract public void update();
	abstract public String getReleaseInfo();
	abstract public String getDebugInfo();
	abstract public String externalise();
}
