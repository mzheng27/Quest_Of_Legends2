/*
* Armor - a subclass that extends Tool to represents tools in the 
* Quest Market which are specified to be Armory (has damage reduction effect).
*/
public class Armor extends Tool {
	/*
	* Constructor - effect refers to damage reduction effect. Calls the superclass's
	* constructor and resets the field isarmor to true. 
	*/
	public Armor (String name, int cost, int min_level, int effect) {
		super(name, cost, min_level, effect);
		isArmor = true;
	}
	/*
	* No-arg constructor - calls the superclass's no arg constructor then 
	* sets isarmor to true. 
	*/
	public Armor () {
		super();
		isArmor = true;
	}
	/*
	* switchOn -  Call mutator method incDef of the Hero class to set the defense status. 
	* Always return true. 
	*/
	public boolean switchOn(Hero hero) {
		hero.resetDef(hero.getDefense() + effect);
		System.out.println(QuestGame.ANSI_RED + "Hero " + hero.toString() + " switched on Armor " + name + QuestGame.ANSI_RESET);
		return true;
	}

	/*
	* useTool - override the useTool in Tool class. 
	* Return a String representation of the effect of the armor. 
	*/
	public String useTool(Hero hero, Monster m) {
		String str = "Armor " + name + "blocked " + effect + "damage.";  
		return str;
	}
	/*
	* switchOff - remove the armor cause the defense level of the hero 
	* to drop back to its original level. Always returns true. 
	*/
	public boolean switchOff(Hero hero) {
		hero.resetDef(hero.getDefense()-effect);
		System.out.println(hero.toString() + " switched off Armor " + name);
		return true;
	}
}