import java.util.Scanner;
import java.util.Random;
/*
* Hero - a subclass that extends Fighter. Since Fighter implements the Fight
* interface, Hero also inherits/implements the methods of the Fight interface. 
* Hero overrides the getStat method of the Fighter class.
*/
public class Hero extends Fighter {
	//statistics 
	private int mana;
	protected int strength;
	protected int agility;
	protected int dex;
	private int cur_exp;
	private int money;
	//equipments
	private Tool current_Use;
	private Tool armor;
	private ToolBag[] inventory;

	/*
	* Constructor - sets all attributes according to the inputted values, call the no-Arg
	* constructors of Tool, Armor and ToolBag etc. dodge = agility * 0.02
	*/
	public Hero (String name, int mana, int strength, int agility, int dexterity, int money, int exp) {
		super(name, 1, 100);
		this.mana = mana;
		this.strength = strength;
		this.agility = agility;
		dex = dexterity;
		this.money = money;
		cur_exp = exp;
		current_Use = new Tool();
		armor = new Armor();
		inventory = new ToolBag[]{new ArmorWeaponBag(), new PotionBag(), new SpellBag()};
		dodge = (int) (agility*0.02);
	}

	/* no-Arg Constructor - sets all fields to 0 or calls that attribute's no arg constructor 
	*/ 
	public Hero() {
		super();
		mana = 0;
		strength = 0;
		agility = 0;
		dex = 0;
		cur_exp = 0;
		money = 0;
		current_Use = new Tool();
		armor = new Armor();
		inventory = new ToolBag[3];
	}
	
	/* Inventory - takes care of request of changing armor/weapon and drinking potion. Call 
	* methods drinkPo and switch_tool. Returns true if the request is processed. 
	*/ 
	public boolean Inventory(String whatToDo) {
		int i = -1;
		boolean done = false;
		//analyzes the input string 
		if (whatToDo.equalsIgnoreCase("change")) {
			i = 0;
		} else if (whatToDo.equalsIgnoreCase("potion")) {
			i = 1;
		}
		Scanner scan = new Scanner(System.in);
		if (i == -1) {
			throw new IllegalArgumentException("Inputs must be potion or change");
		} else {
			boolean quit = false;
			ToolBag equip = inventory[i];
			System.out.println(QuestGame.ANSI_BLUE + equip.toString() + QuestGame.ANSI_RESET);
			System.out.println(getStat());
			do {
				System.out.println("Enter name or abbr: ");
				done = equip.processTool(scan.next(), this);
				if (!done) {
					System.out.println("Finished on this request? (Y/y)");
					if (scan.next().toLowerCase().equals("y")) {
						quit = true;
					}
				}
			} while ((!done) && (!quit));
		}
		return done;
	}
	/*
	* registerArmor - mutator method that allows the Hero to switch to this armor. 
	*/
	public void registerArmor(Tool armor) {
		this.armor = armor;
		armor.switchOn(this);
	}
	/*
	* unregisterArmor - mutator method that allows the Hero to switch off this armor. 
	*/
	public void unregisterArmor() {
		if (armor.toString() != null) {
			armor.switchOff(this);
			this.armor = new Armor();
		}
	}

	/*
	* unregisterArmor - mutator method that allows the Hero to switch to this weapon. 
	*/
	public void registerCurrent(Tool x) {
		current_Use = x;
		x.switchOn(this);
	}

	/*
	* unregisterArmor - mutator method that allows the Hero to switch off from this weapon. 
	*/
	public void unregisterCurrent() {
		if (current_Use.toString() != null) {
			current_Use.switchOff(this);
			current_Use = new Tool();
		}
	}

	/*
	* removePotion - if PotionBag contains this specific name of potion, 
	* removes the potion from PotionBag whiles returning true. 
	*/
	public boolean removePotion(String name) {
		return inventory[1].remove(name);
	} 
	
	public Tool getArmor() {
		return armor;
	}

	/*
	* StrIncease - mutator method that increase the strength of the hero by a 
	* certain amount. Expecting a positive integer input. 
	*/
	public void StrIncease(int x) {
		strength += x;
	}
	/*
	* agiIncrease - mutator method that increase the agility of the hero by a 
	* certain amount and recalculate the dodge chance of the hero. 
	* Expecting a positive integer input. 
	*/
	public void agiIncrease(int x){
		agility += x;
		dodge = (int) (agility * 0.02); 
	}
	/*
	* dexIncrease - mutator method that increase the dexterity of the hero by a 
	* certain amount. Expecting a positive integer input. 
	*/
	public void dexIncrease(int x) {
		dex += x;
	}

	/*
	* resetMana - mutator method that resets the mana of the hero by a 
	* certain amount. Expecting a positive integer input. 
	*/
	public void resetMana(int x) {
		if (x < 0) {
			x = 0;
		}
		mana = x;
	}
	/*
	* getMana - accessor method that returns an integer representing the 
	* mana of the hero. 
	*/
	public int getMana() {
		return mana;
	}
	/*
	* getDex - accessor method that returns an integer representing the 
	* dexterity of the hero. 
	*/
	public int getDex() {
		return dex;
	}
	/*
	* getAgi - accessor method that returns an integer representing the 
	* agility of the hero. 
	*/
	public int getAgi() {
		return agility;
	}
	/*
	* getStrength - accessor method that returns an integer representing the 
	* strength of the hero. 
	*/
	public int getStrength() {
		return strength; 
	}
	/*
	* getMoney - accessor method that returns an integer representing the 
	* money of the hero. 
	*/
	public int getMoney() {
		return money;
	}
	/*
	* getExp - return the current experience points
	*/
	public int getExp() {
		return cur_exp;
	}
	/*
	* getEquip - return the equipment bag according to the input string whichOne 
	*/
	public ToolBag getEquip(String bagName) {
		ToolBag find = null;
		for (ToolBag bag:inventory) {
			if (bag.equals(bagName)) {
				find = bag; 
			}
		}
		return find;
	}

	/*
	* buyEquip - used to buy an equipment after the Store makes sure that the 
	* hero's money, level and bag qualify for buying the specified equipment. 
	*/
	public void buyEquip(Tool x, String bagName) {
		money -= x.getCost();
		ToolBag bag = getEquip(bagName);
		if (bag != null) {
			bag.add(x);
		}
	}

	/*
	* inven_toString - retunrs a string representation of the Invetory bags. 
	*/
	public String inven_toString() {
		String str = "";
		for (ToolBag bag:inventory) {
			str = str + bag.toString() + "\n";
		}
		return str;
	}

	/*
	* bagNotEmpty - retunrs true if the hero has some inventory in one of the bags. 
	*/
	public boolean bagNotEmpty() {
		boolean hasInvetory = false;
		for (ToolBag bag:inventory) {
			if (!bag.isEmpty()) {
				hasInvetory = true;
				break;
			}
		}
		return hasInvetory;
	}

	/*
	* sellEquip - used to sell an equipment after the market makes sure that 
	* the equipment is one of their goods. Return true if the specified Tool is
 	* is in the hero's bag and sells the Tool accordingly. 
	*/
	public boolean sellEquip(String name, int index){
		boolean result = false;
		ToolBag equip = inventory[index];
		if (equip.contains(name)) {
			result = true;
			Tool sell = equip.getTool(name);
			money += (sell.getCost()/2);
			equip.remove(name);
			if ((armor.toString() != null) && (armor.equals(name))) {
				//If this is the armor - you will not wear an armor 
				unregisterArmor();
			} else if ((current_Use.toString() != null) && (current_Use.equals(name))){
				unregisterCurrent();
			} 
			System.out.println(QuestGame.ANSI_RED + sell.toString() + " sold!"+ QuestGame.ANSI_RESET);
		} else {
			System.out.println(QuestGame.ANSI_RED + "o_o ... The item that you specified is not in Bag " + equip.getName()+ QuestGame.ANSI_RESET);
		}
		return result;
	}
	/*
	* levelUp - helper methods that return true and enhances a hero's 
	* skills if the hero can level up 
	*/
	protected boolean levelUp() {
		boolean result = false;
		if (cur_exp > getLevel()*10) {
			result = true;
			strength = (int) (strength * 1.05);
			agility = (int) (agility * 1.05);
			dex = (int) (dex * 1.05);
			levelInc();
			resetHp(getHp() + 100); //gains 100*1 hp
			System.out.println(QuestGame.ANSI_RED + "Hero " + getName() + " levels up to " + getLevel() + QuestGame.ANSI_RESET);
			mana = (int) (mana * 1.1);
		}
		return result;
	}

	
	/*
	 * reward - if the Hero kills a Monster, win x amount of money and 2 experience
	 */
	public void reward(int x) {
		money += x;
		cur_exp += 2;
		levelUp(); //checks if it's time to level up
		System.out.println(QuestGame.ANSI_RED + "( ＾∀＾）／ Good Job on defeating a Monster! ＼( ＾∀＾） Hero " + getName() + " is rewarded"+ QuestGame.ANSI_RESET);
	}
	/*
	 * respawns - deal with the case if a hero dies and respawn 
	 */
	public void respawns() {
		money = (int) (money * 0.5);
		resetHp(getLevel() * 100);
		System.out.println(QuestGame.ANSI_RED + "Hero " + getName() + " respawns." + QuestGame.ANSI_RESET);
	}
	/*
	 * prepHero - prepares the hero before each round by increase his/her
	 * mana & hp by 10%. Returns a string description of the prep process. 
	 */
	public String prepHero() {
		resetHp((int) (getHp() *1.1));
		resetMana((int) (getMana() * 1.1));
		String str = "\nNew round for Hero <" + getName() + "> Hero regained 10% Mana & HP";
		return str;
	}
	/*
	 * equals(Hero other) - method overload. Compares a hero with another hero 
	 * and return true if they have the same name. 
	 */
	public boolean equals(Hero other) {
		boolean result = false;
		if ((other.getName() != null) && (getName() != null) && (getName().equals(other.getName()))) {
			result = true;
		}
		return result;
	}
	/*
	* getStat() - override the getStat() method in the Fighter class. 
	* Returns a String representaion of Hero's statistics 
	* and weapons and armor, used during a fight.  
	*/
	public String getStat() {
		String str = super.getStat();
		str = str + "\nMana: " + mana + " Money: "+ money + "\n";
		//if the hero is using a weapon
		if (current_Use.toString() != null) {
			str = str + "[Current Tool]: " + current_Use.toString();
		} else {
			str = str + " o_o ....Fighting with fists!  ";
		}
		if (armor.toString() != null) {
			str = str + "            [Armor]: " + armor.toString();
		} else {
			str = str + " o_o ....No armor!  ";
		}
		return str;
	}
	/*
	* getStat(boolean x) - Returns a String representaion of Hero's statistics 
	* and skills, used when not during a fight.   
	*/
	public String getStat(boolean x) {
		String str = super.getStat() + ", Strength: " + strength + ", Agility: " + agility + ", Dexterity: " + dex + "\nMana: " + mana + " Money: "+ money;
		return str;
	}
	
	/*
	 * attack_useSpell - returns true if the Monster m is killed, and false if otherwise. 
	 * String how specifies how the Hero fight (attack or spell casting)
	 */
	public boolean attack_useSpell(Monster m, String how) {
		Scanner scan = new Scanner(System.in);
		Random ran = new Random();
		boolean mFailDodge = (m.getDodge() < ran.nextInt(100));
		boolean mDead = false;
		if (alive() && m.alive() && mFailDodge) {
			if (how.equalsIgnoreCase("attack")) {
				System.out.println(QuestGame.ANSI_RED+"#!∷∶∵ "+ current_Use.useTool(this, m)+" ∷∶∵!#"+ QuestGame.ANSI_RESET);
			} else if (how.equalsIgnoreCase("spell")){
				System.out.println(QuestGame.ANSI_BLUE+inventory[2].toString() + QuestGame.ANSI_RESET);
				boolean finished = false; 
				String spell = "";
				do {
					System.out.println("Which spell to use: "); 
					spell = scan.next();
					finished = inventory[2].contains(spell);
					if (!finished) {
						System.out.println(QuestGame.ANSI_RED+ spell + " is not a valid Spell in your bag!"+ QuestGame.ANSI_RESET);
					}
				} while (!finished);
				System.out.println(QuestGame.ANSI_RED+"#!∷∶∵ "+ inventory[2].getTool(spell).useTool(this, m)+" ∷∶∵!#"+ QuestGame.ANSI_RESET);
			}
		} else if (!mFailDodge) {
			System.out.println(QuestGame.ANSI_RED+"Monster <" + m.getName() + "> dodged attack from Hero <" + getName() + ">"+ QuestGame.ANSI_RESET);
		}
		//If the monster is killed
		if (!m.alive()) {
			mDead = true;
			System.out.println(QuestGame.ANSI_RED+ "Monster " + m.toString() + " is defeated!" + QuestGame.ANSI_RESET);
		}
		return mDead;	
	}


}