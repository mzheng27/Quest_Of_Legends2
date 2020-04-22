/*
 * Paladin - a subclass of Hero which represents the Paladins in Quest. Override
 * the method levelUp in Hero
 */
public class Paladin extends Hero {
	/*
	 * Constructor - call Hero's constructor
	 */
	public Paladin(String name, int mana, int str, int ag, int dex, int money, int exp) {
		super(name, mana, str, ag, dex, money, exp);
	}
	/*
	 * No-arg constructor - call Hero's no-arg constructor
	 */
	public Paladin() {
		super();
	}
	/*
	* levelUp - Override the levelUp method inherits from the Hero class. 
	* Call the levelUp in superclass to enhances the skills and mana of the hero 
	* if the hero reaches 10*current_level of experience. Also reset the HP = 100 * new level. 
	* Then enhances the special skills if its time to levelUp. Return true if the 
	* hero has leveled up and false if otherwise. 
	*/
	public boolean levelUp() {
		boolean result = super.levelUp();
		if (result) {
			dex = (int) (dex * 1.05);
			strength = (int) (strength * 1.05);
		}
		return result;
		
	}
}