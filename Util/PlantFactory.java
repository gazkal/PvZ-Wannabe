package tp.pr3.Util;

import tp.pr3.Exceptions.FileContentsException;
import tp.pr3.Logic.Objects.Cherrybomb;
import tp.pr3.Logic.Objects.Peashooter;
import tp.pr3.Logic.Objects.Plant;
import tp.pr3.Logic.Objects.Position;
import tp.pr3.Logic.Objects.Sunflower;
import tp.pr3.Logic.Objects.Wallnut;

public class PlantFactory {
	private static Plant[] availablePlants = {
			new Peashooter(),
			new Sunflower(),
			new Wallnut(),
			new Cherrybomb()
	};
	public static Plant getPlant (String name) {
	
		for(Plant p: availablePlants) {
			p = p.parse(name);
			if(p!= null)
				return p;
		}
		return null;
	}
	public static String infoAvailablePlants() {
		String info="";
		for(Plant p : availablePlants) {
			info += p.getInfo() + "\n";
		}
		return info;
	}
	public static Plant parsePlant (String[] s) throws FileContentsException 
	{
		Plant p = null;
		Position ps = new Position (0,0);
		if (s[0].equals("P"))
		{	p = new Peashooter (); 
		if(Integer.parseInt(s[1]) > availablePlants[0].getHp())throw new FileContentsException("Peashooter with too many HP");
		else if(Integer.parseInt(s[1]) <= 0)throw new FileContentsException("Peashooter with too few HP");
		else if(Integer.parseInt(s[2])<0 || Integer.parseInt(s[2])>3 || Integer.parseInt(s[3])<0 || Integer.parseInt(s[3])>6)throw new FileContentsException("Peashooter with invalid  position");
		else if(Integer.parseInt(s[4])<0 || Integer.parseInt(s[4])> availablePlants[0].getShoot_freq())throw new FileContentsException("Peashooter with invalid  shoot frequency");
		p.setHp(Integer.parseInt(s[1])); p.setShoot_freq(Integer.parseInt(s[4]));
		ps.setRow(Integer.parseInt(s[2])); ps.setColumn(Integer.parseInt(s[3])); p.setPos(ps);}
		
		else if (s[0].equals("S"))
		{	p = new Sunflower ();
		if(Integer.parseInt(s[1]) > availablePlants[1].getHp())throw new FileContentsException("Sunflower with too many HP");
		else if(Integer.parseInt(s[1]) <= 0)throw new FileContentsException("Sunflower with too few HP");
		else if(Integer.parseInt(s[2])<0 || Integer.parseInt(s[2])>3 || Integer.parseInt(s[3])<0 || Integer.parseInt(s[3])>6)throw new FileContentsException("Sunflower with invalid  position");
		else if(Integer.parseInt(s[4])<0 || Integer.parseInt(s[4])> availablePlants[1].getShoot_freq())throw new FileContentsException("Sunflower with invalid  shoot frequency");
		p.setHp(Integer.parseInt(s[1])); p.setShoot_freq(Integer.parseInt(s[4]));
		ps.setRow(Integer.parseInt(s[2])); ps.setColumn(Integer.parseInt(s[3])); p.setPos(ps);}
		
		else if (s[0].equals("N"))
		{	p = new Wallnut(); 
		if(Integer.parseInt(s[1]) > availablePlants[2].getHp())throw new FileContentsException("Wallnut with too many HP");
		else if(Integer.parseInt(s[1]) <= 0)throw new FileContentsException("Wallnut with too few HP");
		else if(Integer.parseInt(s[2])<0 || Integer.parseInt(s[2])>3 || Integer.parseInt(s[3])<0 || Integer.parseInt(s[3])>6)throw new FileContentsException("Wallnut with invalid  position");
		else if(Integer.parseInt(s[4])<0 || Integer.parseInt(s[4])> availablePlants[2].getShoot_freq())throw new FileContentsException("Wallnut with invalid  shoot frequency");
		p.setHp(Integer.parseInt(s[1])); p.setShoot_freq(Integer.parseInt(s[4]));
		ps.setRow(Integer.parseInt(s[2])); ps.setColumn(Integer.parseInt(s[3])); p.setPos(ps);}
		
		else if (s[0].equals("C"))
		{	p = new Cherrybomb(); 
		if(Integer.parseInt(s[1]) > availablePlants[3].getHp())throw new FileContentsException("Cherrybomb with too many HP");
		else if(Integer.parseInt(s[1]) <= 0)throw new FileContentsException("Cherrybomb with too few HP");
		else if(Integer.parseInt(s[2])<0 || Integer.parseInt(s[2])>3 || Integer.parseInt(s[3])<0 || Integer.parseInt(s[3])>6)throw new FileContentsException("Cherrybomb with invalid  position");
		else if(Integer.parseInt(s[4])<0 || Integer.parseInt(s[4])> availablePlants[3].getShoot_freq())throw new FileContentsException("Cherrybomb with invalid  shoot frequency");
		p.setHp(Integer.parseInt(s[1])); p.setShoot_freq(Integer.parseInt(s[4]));
		ps.setRow(Integer.parseInt(s[2])); ps.setColumn(Integer.parseInt(s[3])); p.setPos(ps);}
		
		return p;
	}
}
