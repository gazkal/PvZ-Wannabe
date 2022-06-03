package tp.pr3.Logic.Objects;

public class GameObjectList{
	private GameObject[] gOList;
	private int cont;
	private final int MAX = 32;
	
	public GameObjectList ()
	{
		this.gOList = new GameObject[MAX];
		this.cont = 0;
	}
	
	public int getObject (Position p) //Buscar al objeto con el estado Alive
	{
		int aux = -1;
		int i = 0;
		boolean found = false;
		while (i < cont && !found)
		{
			if (this.gOList[i].getPosCol() == p.getColumn()&& this.gOList[i].getPosRow() == p.getRow())
			{
				aux = i;
				found = true;
			}
			i++;
		}
		return aux;
	}
	
	
	public boolean isPosFull(Position p)
	{
		int i = 0;
		boolean found = false;
		while (i < this.cont && !found)
		{
			if (this.gOList[i].getPosCol() == p.getColumn()&& this.gOList[i].getPosRow() == p.getRow())
			{
				found = true;
			}
			i++;
		}
		return found;
	}
	
	public void add (GameObject obj)
	{
		if (this.cont < this.MAX)
		{
			this.gOList[this.cont] = obj;
			this.cont++;
		}
	}
	
	public int getClosestObjectCol(Position bullet) { //USADO FOR ZOMBIES
		int closest=10;
		for(int i=0;i<cont;i++){
			if(gOList[i].getPosRow()==bullet.getRow() && gOList[i].getPosCol()> bullet.getColumn() && gOList[i].getPosCol()< closest)
				closest = gOList[i].getPosCol();
		}
		return closest;
	}
	
	public void objectGetDamaged(int target, int dmg)	{//
		gOList[target].objectStatus(dmg);
	}
	
	public void objectGetDamaged(Position bullet, int dmg){ //USADO POR PEASHOOTER
		int Target = getObject(bullet);
		gOList[Target].objectStatus(dmg);
	}
	
	public void updateObject()
	{	
		for (int i = 0; i < cont; i++){
				gOList[i].update();
		}
	}
	
	
	/*public void removeFromList (Position p)
	{
		int aux = getAliveObject(p);
		gOList[aux] = gOList[cont - 1];
		gOList[cont - 1].alive = false;
		this.cont--;
	}*/
	
	public String toStringR(int aux) {
		return "" + gOList[aux].getReleaseInfo();
	}
	public String toStringD(int aux) {
		return "" + gOList[aux].getDebugInfo();
	}
	public String externalise(int aux) {
		return "" + gOList[aux].externalise();
	}
	
	public int getCont() {
		return this.cont;
	}

	public boolean eliminateObjet(GameObject go) {
		boolean done=false;
		int i=0;
		while(!done && i<this.cont) {
			if(gOList[i].equals(go)) {
				gOList[i]=null;
				desplazarLista(i);
				done=true;
			}
			i++;
		}
		return done;
	}

	private void desplazarLista(int i) {
		while(i<this.cont - 1) {
			gOList[i]=gOList[i + 1];
			i++;
		}
		gOList[cont - 1]=null;
		cont--;
		
	}

	public void increaseCurrentZombie() {
		this.cont++;
		
	}
}
