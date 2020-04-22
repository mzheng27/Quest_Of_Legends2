/*
 * WarriorList - a class that represents all Warriors (a type of Hero) in the Quest 
 * It provides the method create and getHeros as described in the QuestList interface.
 */
public class WarriorList implements QuestList<Warrior>{
	private static Warrior[] warriors;
	/*
	 * constructor - calls the constructor of Warrior and creates all Warrior objects
	 * represents them through an array. 
	 */
	public WarriorList() {
		warriors = new Warrior[4];
		warriors[0] = new Warrior("Andreas_Beowulf", 100, 900, 500, 600, 1354, 7);
		warriors[1] = new Warrior("Cathal_Ptolemaios", 600, 1000, 800, 500, 2500, 8);
		warriors[2] = new Warrior("Rainer_Oliver", 300, 900, 700, 750, 2546, 6);
		warriors[3] = new Warrior("Walther_Hildebert", 200, 750, 650, 700, 2500, 7);
	}
	/*
	 * create - takes in a Hero object and creates and return Warrior object
	 * with the same statistics (name, money, skills etc). 
	 */
	public Warrior create(Hero w) {
		Warrior x = new Warrior(w.getName(), w.getMana(), w.getStrength(), w.getAgi(), w.getDex(), w.getMoney(), w.getExp());
		return x;
	}
	/*
	 * getHeros - returns an array of a Warriors which represents all the Warriors in the Quest. 
	 */
	public Warrior[] getHeros() {
		return warriors;
	}
}