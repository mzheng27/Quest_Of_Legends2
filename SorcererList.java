/*
 * SorcererList - a class that represents all Sorcerers (a type of Hero) in the Quest 
 * It provides the method create and getHeros as described in the QuestList interface.
 */
public class SorcererList implements QuestList<Sorcerer>{
	private static Sorcerer[] sorcerers;
	/*
	 * constructor - calls the constructor of Sorcerer and creates all Sorcerer objects
	 * represents them through an array. 
	 */
	public SorcererList() {
		sorcerers = new Sorcerer[4];
		sorcerers[0] = new Sorcerer("Garl_Glittergold", 700, 550, 600, 500, 2500, 7);
		sorcerers[1] = new Sorcerer("Rillifane_Rallathil", 1300, 750, 450, 500, 2500, 9);
		sorcerers[2] = new Sorcerer("Segojan_Earthcaller", 900, 800, 500, 650, 2500, 5);
		sorcerers[3] = new Sorcerer("Skoraeus_Stonebones", 800, 850, 600, 450, 2500, 6);
	}
	/*
	 * create - takes in a Hero object and creates and return Sorcerer object
	 * with the same statistics (name, money, skills etc). 
	 */
	public Sorcerer create (Hero s) {
		Sorcerer x = new Sorcerer(s.getName(), s.getMana(), s.getStrength(), s.getAgi(), s.getDex(), s.getMoney(), s.getExp());
		return x;
	}
	
	/*
	 * getHeros - returns an array of a Sorcerer which represents all the Sorcerers in the Quest. 
	 */
	public Sorcerer[] getHeros() {
		return sorcerers;
	}
}