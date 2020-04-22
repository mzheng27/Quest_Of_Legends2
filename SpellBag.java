/*
* SpellBag - Hold the Spells that a Hero purchased. 
*/
public class SpellBag extends ToolBag {
	/*
	* No-arg constructor - creates an empty bag with one head Node. 
	*/
	public SpellBag() {
		super();
		bagName = "Spell";
	}
	
	/*
	* processTool - SpellBag doesn't support any of the switchtool processes. 
	*/
	public boolean processTool(String name, Hero hero) {
		return false;
	}

	/*
	* toString - overrides the toString method in ToolBag to provides more details about the bag.
	*/
	public String toString() {
		String str = "Inventories in Bag " + bagName + ": " + super.toString(); 
		return str;
	}
}