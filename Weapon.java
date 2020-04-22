/*
* Weapon - a subclass that extends Tool for tools in the Quest market 
* which are specified to be Weaponry (has damage effect). 
*/
public class Weapon extends Tool {
	//although not used in Quest, hands is still specified
	private int hands; 
	/*
	* Constructor - first calls the superclass' constructor then sets the field hands to 
	* the corresponding input.
	*/
	public Weapon(String name, int cost, int min_level, int effect, int hands) {
		super(name, cost, min_level, effect);
		this.hands = hands; 
	}
	/*
	* No-arg constructor - calls the superclass's no arg constructor then
	* sets hands to 0. 
	*/
	public Weapon() {
		super();
		hands = 0;
	}
	/*
	* useTool - Override the useTool method in Tool and changes the String 
	* value returned.Return a String description of who causes how much damage to whom.
	* damage = (weapon damage + strength) * 0.2;
	*/
	public String useTool (Hero hero, Monster m) {
		int damage = (int) ((effect + hero.getStrength())*0.2) - m.getDefense();
		if (damage < 0) {
			damage = 0;
		}
		m.resetHp(m.getHp() - damage);
		String str = "Hero <" + hero.toString() + "> causes " + damage + " damage to Monster <" + m.toString() + "> with " + toString();
		return str;
	}

}