/*
* PotionBag - Hold the Potions that a Hero purchased. 
*/
public class PotionBag extends ToolBag {
	/*
	* No-arg constructor - creates an empty bag with one head Node. 
	*/
	public PotionBag() {
		super();
		bagName = "Potions";
	}


	/*
	* processTool - Help the Hero to drink a potion in this Bag. Returns true if this process
	* is Completed. 
	*/
	public boolean processTool(String name, Hero hero) {
		boolean result = false;
		if (isEmpty() || (!contains(name))){
			System.out.println(QuestGame.ANSI_RED+"X﹏X You don't have this potion in bag!"+QuestGame.ANSI_RESET);
		} else {
			result = true;
			result = getTool(name).switchOn(hero);
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