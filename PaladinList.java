/*
 * PaladinList - a class that represents all Paladins (a type of Hero) in the Quest 
 * It provides the method create and getHeros as described in the QuestList interface.
 */
public class PaladinList implements QuestList<Paladin>{
	private static Paladin[] paladins;
	/*
	 * constructor - calls the constructor of Paladin and creates all Paladin objects
	 * represents them through an array. 
	 */
	public PaladinList() {
		paladins = new Paladin[4];
		paladins[0] = new Paladin("Miller_Dawnward", 300, 750, 650, 700, 2500, 7);
		paladins[1] = new Paladin("Marian_Nightbreaker", 300, 750, 700, 700, 2500, 7);
		paladins[2] = new Paladin("Jacque_Dawnward ", 250, 700, 600, 350, 2500, 4);
		paladins[3] = new Paladin("Tirell_Lumis", 100, 650, 500, 400, 2500, 5);
	}
	
	/*
	 * create - takes in a Hero object and creates and return Paladin object
	 * with the same statistics (name, money, skills etc). 
	 */
	public Paladin create(Hero p) {
		Paladin x = new Paladin(p.getName(), p.getMana(), p.getStrength(), p.getAgi(), p.getDex(), p.getMoney(), p.getExp());
		return x;
	}
	/*
	 * getHeros - returns an array of a Paladins which represents all the Paladins in the Quest. 
	 */
	public Paladin[] getHeros() {
		return paladins;
	}
}