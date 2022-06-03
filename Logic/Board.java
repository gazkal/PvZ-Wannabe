package tp.pr3.Logic;

import tp.pr3.Logic.Objects.GameObject;
import tp.pr3.Logic.Objects.GameObjectList;
import tp.pr3.Logic.Objects.Plant;
import tp.pr3.Logic.Objects.Position;
import tp.pr3.Logic.Objects.Zombie;

public class Board{
	private int dim_x, dim_y;
	private GameObjectList gLP;
	private GameObjectList gLZ;
	public Board (int x, int y)
	{
		this.dim_x = x;
		this.dim_y = y;
		this.gLP = new GameObjectList();
		this.gLZ = new GameObjectList();
	}
	
	
	//METODOS DE LISTAS
	public int getPlant(Position p) {
		return gLP.getObject(p);
	}
	
	public int getZombie(Position p) {
		return gLZ.getObject(p);
	}
	
	public boolean isPosZFull(Position zombieAction) {
		return gLZ.isPosFull(zombieAction);
	}
	
	public boolean isPosPFull(Position zombieAction) {
		return gLP.isPosFull(zombieAction);
	}
	
	public void addP (Plant obj) {
		gLP.add(obj);
	}
	
	public void addZ (Zombie obj) {
		gLZ.add(obj);
	}
	
	public int getClosestObjectCol(Position bullet) { //Bullet shoots zombie
		return gLZ.getClosestObjectCol(bullet);
	}
	
	public void objectGetDamagedP(int target, int dmg) {
		gLP.objectGetDamaged(target, dmg);
	}
	
	public void objectGetDamagedZ(Position bullet, int dmg) {
		gLZ.objectGetDamaged(bullet, dmg);
	}
	
	public void updateObjectP() {
		gLP.updateObject();
	}
	
	public void updateObjectZ() {
		gLZ.updateObject();
	}
	
	public String plantsToStringR(int aux)
	{
		return gLP.toStringR(aux);
	}
	
	public String zombiesToStringR(int aux)
	{
		return gLZ.toStringR(aux);
	}
	public String plantsToStringD(int aux)
	{
		return gLP.toStringD(aux);
	}
	
	public String zombiesToStringD(int aux)
	{
		return gLZ.toStringD(aux);
	}
	
	public String storeP(int aux)
	{
		return gLP.externalise(aux);
	}
	public String storeZ(int aux) 
	{
		return gLZ.externalise(aux);
	}
	/*public void removeFromListZ (Position p) {
		gLZ.removeFromList(p);
	}
	
	public void removeFromListP (Position p) {
		gLP.removeFromList(p);
	}*/

	//SETTERS, GETTERS
	public int getDim_x() {
		return dim_x;
	}

	public void setDim_x(int dim_x) {
		this.dim_x = dim_x;
	}

	public int getDim_y() {
		return dim_y;
	}

	public void setDim_y(int dim_y) {
		this.dim_y = dim_y;
	}
	
	public int gLPcont () {
		return this.gLP.getCont();
	}
	
	public int gLZcont() {
		return this.gLZ.getCont();
	}

	public void eliminateObject(GameObject go) {
		if(!gLZ.eliminateObjet(go))
			gLP.eliminateObjet(go);
		
	}


	public void increaseCurrentZombie() {
		this.gLZ.increaseCurrentZombie();
	}
	
}
