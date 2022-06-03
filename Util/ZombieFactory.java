package tp.pr3.Util;

import tp.pr3.Exceptions.FileContentsException;
import tp.pr3.Logic.Objects.BucketheadZombie;
import tp.pr3.Logic.Objects.CommonZombie;
import tp.pr3.Logic.Objects.Position;
import tp.pr3.Logic.Objects.SportyZombie;
import tp.pr3.Logic.Objects.Zombie;

public class ZombieFactory {
	public static Zombie getZombie (String name) {
		//instantiate to default values
		Zombie z = null;
		if (name.equals("Z")) //CommonZombie
			z = new CommonZombie ();
		else if (name.equals("W"))
			z = new BucketheadZombie ();
		else if (name.equals("X"))
			z = new SportyZombie ();
		return z;
	}
	
	public static Zombie parseZombie (String[] s) throws FileContentsException {
		Zombie z = null;
		Position ps = new Position (0,0);
		if (s[0].equals("Z"))
		{	z = new CommonZombie ();
		if(Integer.parseInt(s[1]) > 5)throw new FileContentsException("Zombie with too many HP");
		else if(Integer.parseInt(s[1]) <= 0)throw new FileContentsException("Zombie with too few HP");
		else if(Integer.parseInt(s[2])<0 || Integer.parseInt(s[2])>3 || Integer.parseInt(s[3])<0 || Integer.parseInt(s[3])>7)throw new FileContentsException("Zombie with invalid  position");
		else if(Integer.parseInt(s[4])<0 || Integer.parseInt(s[4])> 2)throw new FileContentsException("Zombie with invalid  shoot frequency");
		z.setHp(Integer.parseInt(s[1])); z.setMoveCounter(Integer.parseInt(s[4]));
		ps.setRow(Integer.parseInt(s[2])); ps.setColumn(Integer.parseInt(s[3])); z.setPos(ps);}
		
		else if (s[0].equals("W"))
		{	z = new BucketheadZombie ();
		if(Integer.parseInt(s[1]) > 8)throw new FileContentsException("BucketHead Zombie with too many HP");
		else if(Integer.parseInt(s[1]) <= 0)throw new FileContentsException("BucketHead Zombie with too few HP");
		else if(Integer.parseInt(s[2])<0 || Integer.parseInt(s[2])>3 || Integer.parseInt(s[3])<0 || Integer.parseInt(s[3])>7)throw new FileContentsException("BucketHead Zombie with invalid  position");
		else if(Integer.parseInt(s[4])<0 || Integer.parseInt(s[4])> 4)throw new FileContentsException("BucketHead Zombie with invalid  shoot frequency");
		z.setHp(Integer.parseInt(s[1])); z.setMoveCounter(Integer.parseInt(s[4]));
		ps.setRow(Integer.parseInt(s[2])); ps.setColumn(Integer.parseInt(s[3])); z.setPos(ps);}
		
		else if (s[0].equals("X"))
		{	z = new SportyZombie(); 
		if(Integer.parseInt(s[1]) > 2)throw new FileContentsException("Sporty Zombie with too many HP");
		else if(Integer.parseInt(s[1]) <= 0)throw new FileContentsException("Sporty Zombie with too few HP");
		else if(Integer.parseInt(s[2])<0 || Integer.parseInt(s[2])>3 || Integer.parseInt(s[3])<0 || Integer.parseInt(s[3])>7)throw new FileContentsException("Sporty Zombie with invalid  position");
		else if(Integer.parseInt(s[4])<0 || Integer.parseInt(s[4])> 1)throw new FileContentsException("Sporty Zombie with invalid  shoot frequency");
		z.setHp(Integer.parseInt(s[1])); z.setMoveCounter(Integer.parseInt(s[4]));
		ps.setRow(Integer.parseInt(s[2])); ps.setColumn(Integer.parseInt(s[3])); z.setPos(ps);}
		
		return z;
	}
	
}
