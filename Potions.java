/*
* Potions - a subclass that extends Tool for tools in the Quest market 
* which are specified to be Potions (can have various effects).
*/
public class Potions extends Tool {
		/*
	* Constructor - first calls the superclass' constructor to sets the fields
	*/
	public Potions(String name, int cost, int min_level, int effect) {
		super(name, cost, min_level, effect);
	}
	
	/*
	* No-arg constructor - calls the superclass's no arg constructor 
	*/
	public Potions() {
		super();
	}
	/*
	* switchOn - Returns true if the Potion is drinkable. Since the 
	* hero can drink potion when not fighting, the drinking feature is implemented
	* in switchOn. 
	*/
	public boolean switchOn(Hero hero) {
		//removeEquip returns true if the bag contains the tool 
		boolean result = false;
		if (hero.removePotion(this.name)) {
			if (name.equals("Healing_Potion") || name.equals("Mermaid_Tears")){
				hero.resetHp(hero.getHp() + effect);
			} else if (name.equals("Strength_Potion") || name.equals("Ambrosia")) {
				hero.StrIncease(effect);
			} else if (name.equals("Luck_Elixir")) {
				hero.agiIncrease(effect);
			} else if (name.equals("Magic_Potion")) {
				hero.dexIncrease(effect);
			}
			result = true;
			System.out.println(QuestGame.ANSI_RED + hero.toString() + " consumed Potion " + name+ QuestGame.ANSI_RESET);
		}
		return result;
	}
	/*
	* useTool - Since the potion is already consumed when switching to it, 
	* the method only need to return a String to describe its effect. 
	*/
	public String useTool(Hero hero, Monster m) {
		String str = hero.toString() + " consumed Potion " + name + " for the fight";
		return str;
	}	
}