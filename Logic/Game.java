package tp.pr3.Logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;

import tp.pr3.Exceptions.CommandExecuteException;
import tp.pr3.Exceptions.FileContentsException;
import tp.pr3.Logic.GamePrinter.GamePrinterInter;
import tp.pr3.Logic.Objects.*;
import tp.pr3.Util.MyStringUtils;
import tp.pr3.Util.PlantFactory;
import tp.pr3.Util.ZombieFactory;

public class Game implements Cloneable{
	private Level l;
	private SuncoinManager SnM;
	private ZombieManager zM;
	private int cycles;
	private boolean gameover;
	private Random zRand;
	private Random zType;
	private Board b;
	private long seed;
	private GamePrinterInter gp;

	public Game(int dim_x, int dim_y, Level l, Random rand, long seed, GamePrinterInter gp) {
		this.SnM = new SuncoinManager(50); // suncoins inicial = 50
		this.cycles = 0;
		this.gameover = false;
		this.l = l;
		this.seed = seed;
		this.gp=gp;
		this.zRand = rand;
		this.zType = rand;
		b = new Board(dim_x, dim_y);
		if (this.l == Level.EASY)
			this.zM = new ZombieManager(3, rand); // zombieGenCont (total zombies) ajustado con relacion con la
													// dificultad
		else if (this.l == Level.HARD)
			this.zM = new ZombieManager(5, rand);
		else if (this.l == Level.INSANE)
			this.zM = new ZombieManager(10, rand);
	}

	public void resetGame(int x, int y) {
		new SuncoinManager(50); // suncoins inicial = 50
		this.cycles = 0;
		this.gameover = false;
		b = new Board(x, y);
		if (this.l == Level.EASY)
			this.zM = new ZombieManager(3, this.zRand); // zombieGenCont (total zombies) ajustado con relacion con la
														// dificultad
		else if (this.l == Level.HARD)
			this.zM = new ZombieManager(5, this.zRand);
		else if (this.l == Level.INSANE)
			this.zM = new ZombieManager(10, this.zRand);
	}
	@Override
	public Game clone() throws CloneNotSupportedException {
		Game g = (Game) super.clone();
		return g;
	}
	// Cherry mechanics:
	public void areaDamage(Position p, int dmg) // for cherry
	{
		if (b.getZombie(p) != -1) {
			b.objectGetDamagedZ(p, dmg);
		}
	}

	// Peashooter mechanics:
	public void hitClosestZombie(Position bullet, int dmg) { // to board
		int CZCol = b.getClosestObjectCol(bullet);
		if (CZCol != 10) {
			bullet.setColumn(CZCol);
			b.objectGetDamagedZ(bullet, dmg);
		}
	}

	// Sunflower mechanics:
	public void addSun(int sun) {
		SnM.addSun(sun);
	}

	// Zombie mechanics:
	public void ZombieGenerator() {
		// Chequea si hace falta anadir un zombie
		String[] ztypes = { "Z", "W", "X" };
		int type = 0;
		if (zM.toAdd(this.cycles, this.l) == 1) {
			type = this.zType.nextInt(3); // 3 tipos de zombies
			Position NewZombiePos = new Position(zRand.nextInt(b.getDim_x()), b.getDim_y() - 1); // (Randfila, 7)
			while (b.getZombie(NewZombiePos) != -1 && b.gLZcont() != 0) // En el caso de que ya existe un
																					// zombie en la posicion generada,
																					// genera otro rand
			{
				NewZombiePos.setRow(zRand.nextInt(b.getDim_x()));
				NewZombiePos.setColumn(b.getDim_y() - 1);
			}
			Zombie z = ZombieFactory.getZombie(ztypes[type]);
			z.setPos(NewZombiePos);
			z.setGame(this);
			b.addZ(z);
			zM.decreaseZombieGenCont(); // decrementar el numero total de zombies
		}
	}

	public boolean attackPlant(Position target, int dmg) { // attack una planta
		int zTarget = b.getPlant(target);
		boolean succesful = false;
		if (zTarget != -1) {
			b.objectGetDamagedP(zTarget, dmg);
			succesful = true;
		}
		return succesful;
	}

	// Update y Mecanicas:
	public void update() {

		ZombieGenerator();
		// chequea cuantos objetos hay y y los actualiza
		if (b.gLPcont() > 0)
			b.updateObjectP();
		// chequea cuantos zombies hay, y los actualiza
		if (b.gLZcont() > 0)
			b.updateObjectZ();
		if (b.gLZcont() == 0 && zM.getzombieGenCont() == 0) // si todos los zombies están muertos, gameover
			gameover = true;
		this.cycles++;
	}

	public boolean InsertPlant(int x, int y, Plant p) throws CommandExecuteException{ // Insertar un Peashooter en un posicion dado, comprobando la
														// validez de la posicion en el proceso
		boolean added = false;
		Position ps = new Position(x, y);
		if (SnM.getcurrentSun() >= p.getCost() && !b.isPosPFull(ps) && !b.isPosZFull(ps) && (ps.getRow() < b.getDim_x())
				&& (ps.getColumn() < b.getDim_y() - 1) &&  ps.getRow() > -1 && ps.getColumn() > -1) {
			p.setPos(ps);
			p.setGame(this);
			b.addP(p);
			buyPlant(p.getCost());
			added = true;
		}
		else if(SnM.getcurrentSun() < p.getCost())
			throw new CommandExecuteException("Failed to add " + p.getName() + ": not enough suncoins to buy it");
		else if(b.isPosPFull(ps) || b.isPosZFull(ps)) {
			throw new CommandExecuteException("Failed to add " + p.getName() + ": " + "(" + x +", " + y + ")" + " is already occupied");	
		}
		else if(ps.getRow() > b.getDim_x() || (ps.getColumn() > b.getDim_y() - 1) || ps.getRow() < 0 || (ps.getColumn() < 0)) {
			throw new CommandExecuteException("Failed to add " + p.getName() + ": " + "(" + x +", " + y + ")" + " is an invalid position");	
		}
			
		return added;
	}

	public void eliminateObject(GameObject go) {
		b.eliminateObject(go);
	}

	public boolean checkZombiesWin(int ZmbPosCol) {
		boolean gm = false;
		if (ZmbPosCol == 0) // perdida cuando un zombie llega al column == 0
		{
			gm = true;
		}
		return gm;
	}

	public void store(BufferedWriter output) throws IOException{
		// EL TRY BUFFERED SE MUEVE AL SaveCommand CUANDO TENEMOS AL EXCEPCIONES
			output.flush();
			output.newLine();
			output.newLine();
			output.write("cycle: " + this.cycles);
			output.newLine();
			output.write("sunCoins: " + SnM.getcurrentSun());
			output.newLine();
			if (l.equals(Level.EASY))
				output.write("Level: " + "easy");
			else if (l.equals(Level.HARD))
				output.write("Level: " + "hard");
			else if (l.equals(Level.INSANE))
				output.write("Level: " + "insane");
			output.newLine();
			output.write("remZombies: " + zM.getzombieGenCont());
			output.newLine();
			output.write("plantList: ");
			for (int i = 0; i < b.gLPcont(); i++) {
				output.write(b.storeP(i) + ", ");
			}
			output.newLine();
			output.write("zombieList: ");
			for (int j = 0; j < b.gLZcont(); j++) {
				output.write(b.storeZ(j) + ", ");
			}
			output.newLine();

			
	}

	public void load(BufferedReader input) throws CommandExecuteException{
		String[] words = {"cycle", "sunCoins", "Level", "remZombies", "plantList", "zombieList" };
		String[] t = new String[8];
		String[] plantList = new String[16];
		String[] zombieList = new String[16];
		try {
		for (int i = 0; i < 16; i++) { plantList[i] = ""; zombieList[i] = ""; }
				for (int i = 0; i < 4; i++) {
					t[i] = MyStringUtils.loadLine(input, words[i], false)[0];
				}
				this.cycles = Integer.parseInt(t[0]);
				if(cycles < 0) {
					throw new FileContentsException("Bad file: negative cylces");
				}
				this.SnM.setCurrentSun(Integer.parseInt(t[1]));
				if(SnM.getcurrentSun() < 0) {
					throw new FileContentsException("Bad file: negative suncoins");
				}
				this.l = Level.parse(t[2]);
				if(l == null) {
					throw new FileContentsException("Bad file: unknown level");
				}
				this.zM.setZombieGenCont(Integer.parseInt(t[3]));
				if(zM.getzombieGenCont()<0) {
					throw new FileContentsException("Bad file: negative ZombieGenCount");
				}
				else if(zM.getzombieGenCont()>l.getNumberOfZombies()) {
					throw new FileContentsException("Bad file: ZombieGenCount greater than Level limit");
				}
				this.b = new Board (b.getDim_x(), b.getDim_y());
				plantList = MyStringUtils.loadLine(input, words[4], true);
				for (int i = 0; i < plantList.length; i++) {
					Plant p = null;
					p = PlantFactory.parsePlant(plantList[i].split(":"));
					if(p==null)
						throw new FileContentsException("Unknown plant symbol");
					Position posaux = new Position(p.getPosCol(),p.getPosRow());
					if(this.gLPisPosFull(posaux) || this.gLZisPosFull(posaux))
							throw new FileContentsException("Position is already occupied");
							
					p.setGame(this);
					b.addP(p);
				}
				zombieList = MyStringUtils.loadLine(input, words[5], true);
				for (int i = 0; i < zombieList.length; i++) {
					Zombie z = null;
					z = ZombieFactory.parseZombie(zombieList[i].split(":"));
					if(z==null)
						throw new FileContentsException("Unknown zombie symbol");
					Position posaux = new Position(z.getPosCol(),z.getPosRow());
					if(this.gLPisPosFull(posaux) || this.gLZisPosFull(posaux))
						throw new FileContentsException("Position is already occupied");
					z.setGame(this);
					b.addZ(z);
				}
		} catch (Exception e) {
			throw new CommandExecuteException(e.getMessage());
		}
	}
		
		

	public void SetGamePrinter(GamePrinterInter gp) {
		this.gp=gp;
	}
	
	public void buyPlant(int cost) {
		SnM.removeSun(cost);
	}

	public boolean isPosFull(Position zombieAction) {
		return b.isPosZFull(zombieAction) && b.isPosPFull(zombieAction);
	}

	public boolean gLPisPosFull(Position p) {
		return b.isPosPFull(p);
	}

	public boolean gLZisPosFull(Position p) {
		return b.isPosZFull(p);
	}

	public boolean isGameover() {
		return gameover;
	}

	public void incrementCycles() {
		cycles++;
	}

	// toStrings:
	public String plantsToStringR(int aux) {
		return b.plantsToStringR(aux);
	}

	public String zombiesToStringR(int aux) {
		return b.zombiesToStringR(aux);
	}

	public String plantsToStringD(int aux) {
		return b.plantsToStringD(aux);
	}

	public String zombiesToStringD(int aux) {
		return b.zombiesToStringD(aux);
	}

	// getters y setters:
	public int getCycles() {
		return this.cycles;
	}

	public Random getzRand() {
		return zRand;
	}

	public int getDim_y() {
		return b.getDim_y();
	}

	public int getDim_x() {
		return b.getDim_x();
	}

	public Level getLevel() {
		return l;
	}

	public long getSeed() {
		return this.seed;
	}

	public int getSnMcurrentSun() {
		return SnM.getcurrentSun();
	}

	public int getzMzombieGenCont() {
		// TODO Auto-generated method stub
		return zM.getzombieGenCont();
	}
	public int getLPcont() {
		return b.gLPcont();
	}

	public int getLZcont() {
		return b.gLZcont();
	}

	public int getPlant(Position p) {
		return b.getPlant(p);
	}

	public int getZombie(Position p) {
		return b.getZombie(p);
	}

	public void setGameover(boolean gameover) {
		this.gameover = gameover;
	}

	public String printGame(){
		return gp.printGame(this);
	}

	public void restore(Game backup) {
		if(backup!=null) {
		 this.l = backup.getLevel();
		 this.SnM.setCurrentSun(backup.getSnMcurrentSun());
		 zM.setZombieGenCont(backup.getzMzombieGenCont());
		 cycles = backup.getCycles();
		 b = backup.b;
		}
	}
}
