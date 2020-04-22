/*
* ArmorWeaponBag - Hold the Armors and Weapons that a Hero purchased. Implements processTool so 
* that the Hero can switch to using a specific armor/weapon in this Bag. 
*/
public class ArmorWeaponBag extends ToolBag {
	
	/*
	* No-arg constructor - creates an empty bag with one head Node. 
	*/
	public ArmorWeaponBag() {
		super();
		bagName = "Armory/Weaponry";
	}

	/*
	* processTool - Helps a Hero to switch on a Armor or Weapon in this Bag. 
	* Returns true if the process is completed. 
	*/
	public boolean processTool(String name, Hero hero) {
		boolean result = false;
		if (isEmpty() || (!contains(name))) {
			System.out.println(QuestGame.ANSI_RED+"X﹏X You have not purchased this weapon/armor!"+QuestGame.ANSI_RESET);
		} else {
			result = true;
			Tool find = getTool(name);
			if (find.isArmor()) {
				hero.unregisterArmor();
				hero.registerArmor(find);
			} else {
				hero.registerCurrent(find);
			}
		}
		return result;
	}

	/*
	* toString - overrides the toString method in ToolBag to provides more details about the bag.
	*/
	public String toString() {
		String str = "Inventories in Bag " + bagName + ": " + super.toString(); 
		return str;
	}
	
}