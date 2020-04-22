/*
*Tool - a class for tools that the players can buy from the Quest's market, 
* implements the GameTool<Hero> interface. 
*/
public class Tool implements GameTool<Hero>, Sellable{
	//attributes for Tool 
	protected String name;
	private String abbr; //the first 3 letters in name
	private int cost;
	private int min_level;
	protected int effect;
	protected boolean isArmor;
	public static String[] potion_names = {"Mermaid_Tears",  "Ambrosia", "Luck_Elixir", "Healing_Potion", "Strength_Potion", "Magic_Potion"};
	public static String[] weapon_names = {"Sword", "Bow", "Scythe", "Axe", "Shield", "TSwords", "Dagger"};
	
	/*
	* Constructor - takes in a tool's info to sets the fields. 
	* By defaults sets isarmor to false. 
	*/
	public Tool(String name, int cost, int min_level, int effect) {
		this.name = name;
		this.cost = cost;
		this.min_level = min_level;
		this.effect = effect;
		abbr = name.substring(0, 3);
		isArmor = false;
		potion_names = new String[]{"Mermaid_Tears",  "Ambrosia", "Luck_Elixir", "Healing_Potion", "Strength_Potion", "Magic_Potion"};
		weapon_names = new String[]{"Sword", "Bow", "Scythe", "Axe", "Shield", "TSwords", "Dagger"};
	}
	/*
	* No-arg constructor - sets all the fields to either 0 or null. 
	* Sets isarmor to false. 
	*/
	public Tool() {
		name = null; //hero is using fists
		abbr = null;
		cost = 0;
		min_level = 0;
		effect = 0;
		isArmor = false;
	}
	//Some accessor methods 

	/*
	* getAbbr - Accessor method that returns the abbreviation of the tool 
	* which is the first 4 letters in the tool's name. 
	*/
	public String getAbbr() {
		return abbr;
	}
	/*
	* getCost - Accessor method that return the cost of the tool 
	*/
	public int getCost() {
		return cost;
	}
	/*
	* getLevel - Accessor method that return the min level requirement of the tool
	*/
	public int getLevel() {
		return min_level;
	}
	/*
	* getEffect - Accessor method that return the effect/damage caused by the tool
	*/
	public int getEffect() {
		return effect;
	}

	/*
	* toString() method which output the name of the tool 
	*/
	public String toString() {
		return name;
	}
	
	/*
	 * toString(boolean x) - returns a String representation of the Tool with its
	 * name followed by effect. 
	 */
	public String toString(boolean x) {
		String str = name + "; effect " + effect + "; min-level: " + min_level;
		return str;
	}
	
	/*
	* equals(String x) - compares a tool with a String. Returns true is the String
	* is equal to the Tool's name or abbr, neglecting the case of Strings.
	*/
	public boolean equals(String x) {
		boolean result = false;
		if (toString() != null) {
			if (name.toLowerCase().equals(x.toLowerCase()) || abbr.toLowerCase().equals(x.toLowerCase())) {
				result = true;
			}
		}
		return result;
	}
	/*
	* equals (Tool other) - compares two tools, return true is the name of the two 
	* is the same and false if otherwise. 
	*/
	public boolean equals(Tool other) {
		boolean result = false;
		if ((name != null) && (other.toString() != null) && (name.equals(other.toString()))){
			result = true;
		}
		return result;
	}

	/*
	* isArmor - Accessor method which returns true if this tool is an armor
	* and returns false if otherwise. 
	*/
	public boolean isArmor() {
		return isArmor;
	} 
	
	/*
	* checkType - check if the tool_name is of type. 
	*/
	public static boolean checkType(String tool_name, String type) {
		boolean result = false;
		String[] namelist;
		if (type.equals("weapon")) {
			namelist = weapon_names;
		} else {
			namelist = potion_names;
		}
		for (String name : namelist) {
			if ((tool_name!=null) && ((name.toLowerCase().equals(tool_name)) || (name.toLowerCase().substring(0, 3).equals(tool_name)))) {
				result = true;
				break;
			}
		}
		return result;
	}



	//Some mutator methods 
	/*
	* useTool -  Returns a string description of who
	* causes how much damage to whom. 
	* Total damage = (strength + effect) * 0.2 - monster's defense.
	*/
	public String useTool(Hero hero, Monster m) {
		int damage = (int) ((effect + hero.getStrength())*0.2) - m.getDefense();
		if (damage < 0) {
			damage = 0;
		}
		m.resetHp(m.getHp() - damage);
		String str = "Hero <" +hero.toString() + "> PUNCHED and dealt " + damage + " damage to Monster <" + m.toString() + ">";
		return str;
	}

	/*
	* switchOn - switch to fisting, always return true. 
	*/
	public boolean switchOn(Hero hero) {
		System.out.println(QuestGame.ANSI_RED + "Hero <" +hero.toString() + "> chooses to use " + name + QuestGame.ANSI_RESET);
		return true;
	} 
	/*
	* switchOff - all tools are able to switched off in Quest. 
	* Always return true 
	*/
	public boolean switchOff(Hero hero) {
		System.out.println(QuestGame.ANSI_RED + "Hero <" +hero.toString() + "> is no longer using " + name + QuestGame.ANSI_RESET);
		return true;
	}
	
}