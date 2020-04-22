import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException; 
/*
* Market - a class that represents the Quest's tiles that are the Quest's market
* where the players may buy some tools for their heros. Market implements the interface
* QuestCell. 
*/
public class Market {
	private static String[] filenames; //the files that contains all the tools
	private Store[] stores;
	private Scanner scan;
	/*
	* Constructor - call the helper method initMarket() which returns an array 
	* of Store objects by reading files provided in filenames. 
	*/
	public Market() {
		filenames = new String[]{"Potions.txt", "Armory.txt", "Weaponry.txt", "FireSpells.txt", "IceSpells.txt", "LightningSpells.txt"};
		stores = initMarket();
	}
	/*
	* initMarket - helper method which returns an array of stores. Read from the file,
	* determine the tool's type from the file's name and create all tools of a store.
	* Then create an return a list of stores with these stores.  
	*/
	private Store[] initMarket() {
		Tool[] tools = new Tool[6];
		tools[0] = new Potions("Healing_Potion", 250, 1, 100);
		tools[1] = new Potions("Strength_Potion", 200, 1, 75);
		tools[2] = new Potions("Magic_Potion", 350, 2, 100);
		tools[3] = new Potions("Luck_Elixir", 500, 4, 65);
		tools[4] = new Potions("Mermaid_Tears", 850, 5, 100);
		tools[5] = new Potions("Ambrosia", 1000, 8, 150);
		Store s0 = new Store(tools, "Potions");
		tools = new Tool[7];
		tools[0] = new Weapon("Sword", 500, 1, 800, 1);
		tools[1] = new Weapon("Bow", 300, 2, 500, 2);
		tools[2] = new Weapon("Scythe", 1000, 6, 1100, 2);
		tools[3] = new Weapon("Axe", 500, 5, 850, 1);
		tools[4] = new Weapon("Shield", 400, 1, 300, 1);
		tools[5] = new Weapon("TSwords", 1400, 8, 1600, 2);
		tools[6] = new Weapon("Dagger", 200, 1, 400, 1);
		Store s1 = new Store(tools, "Weaponry");
		tools = new Tool[5];
		tools[0] = new Armor("Platinum_Shield", 150, 1, 200);
		tools[1] = new Armor ("Breastplate", 350, 3, 600);
		tools[2] = new Armor("Full_Body_Armor", 1000, 8, 1100);
		tools[3] = new Armor("Wizard_Shield", 1200, 10, 1500);
		tools[4] = new Armor("Speed_Boots", 550, 4, 600);
		Store s2 = new Store(tools, "Armory");
		tools = new Tool[12];
		tools[0] = new LightSpell("LightningDagger", 400, 1, 500, 150);
		tools[1] = new LightSpell("Thunder_Blast", 750, 4, 950, 400);
		tools[2] = new LightSpell("Electric_Arrows", 550, 5, 650, 200);
		tools[3] = new LightSpell("Spark_Needles", 500, 2, 600, 200); 
		tools[4] = new FireSpell("Flame_Tornado", 700, 4, 850, 300);
		tools[5] = new FireSpell("Breath_of_Fire", 350, 1, 450, 100);
		tools[6] = new FireSpell("Heat_Wave", 450, 2, 600, 150);
		tools[7] = new FireSpell("Lava_Commet", 800, 7, 1000, 550);
		tools[8] = new IceSpell("Snow_Canon", 500, 2, 650, 250);
		tools[9] = new IceSpell("Frost_Blizzard", 750, 5, 850, 350);
		tools[10] = new IceSpell("Arctic_storm", 700, 6, 800, 300);
		tools[11] = new IceSpell("Ice_Blade", 250, 1, 450, 100);
		Store s3 = new Store(tools, "Spells");
		Store[] s = new Store[]{s0, s1, s2, s3};
		return s;
	}

	/*
	* sell_Market - Return a boolean value to indicate if the hero can sell
	* this tool to the market. Returns true if the item is in the hero's bag 
	* and in market. 
	*/
	private boolean selltoMarket(Hero hero, String item_name, int index) {
		boolean result = false;
		boolean inMarket = false;
		for (Store store:stores) {
			if (store.contains(item_name)) {
				//some store has this item, item in market. 
				inMarket = true;
				result = hero.sellEquip(item_name, index);
			}
		}
		if (!inMarket) {
			System.out.println(QuestGame.ANSI_RED+"Item not in Market! Please check the correctness of your input."+QuestGame.ANSI_RESET);
		}
		return result;
	}
	/*
	 * buyFromMarket - helper method for useCell. Allows the hero to buy a tool from the market. 
	 * returns true if this transaction is completed. 
	 */
	private boolean buyFromMarket(Hero hero, String item_name, String store_name) {
		boolean result = false;
		int inStore = 0;
		for (Store store:stores) {
			if (store.equals(store_name)) {
				result = store.sell(hero, item_name);
				if (store.contains(item_name)) {
					inStore++;
				}
			}
		} 
		if (inStore == 0) {
			System.out.println(QuestGame.ANSI_RED +"Sorry! your input " + item_name + " is not find in Store " + store_name+ QuestGame.ANSI_RESET);
		}
		return result;
	}
	
	/*
	* useCell - allow the user who enter the market to buy item for a hero
	* or sell item from a hero's bag. 
	*/
	public boolean useCell(Hero customer) {
		String[] texts = {"( ＾∀＾）／ Welcome to the Quest Market ＼( ＾∀＾）", "Enter (Potion/p; Armory/a; Weaponry/w; Spell/s) for a store; or Sell for selling", "--------------------------Potions--------------------------------", "		||_・)  ARMORY (⁄ ⁄•⁄ω⁄||", "(҂‾ ▵‾)▄︻┻┳═一∵∴∷∶∵∶∵∵∵   *WEAPONRY*  ∶∴∶∵∵∷∶∵￢o(￣-￣ﾒ)", "ଘ(੭ˊ꒳ˋ)੭✧ 卍卍卍卍卍卍  *SPELLS* 卍卍卍卍卍 ✧*｡ψ(._. )>", "Please enter the name or abbr (first 3 letters) of the inventory that ", "======== ヾ(￣▽￣)Bye~Bye~ ======== See You Next Time ♪ ========"};
		String[] inputs = new String[]{"potion","armory", "weaponry", "spell"};
		String line = "----------------------------------------------------------------";
		boolean leave = false;
		scan = new Scanner(System.in);
		String user_input;
		do {
			System.out.println(QuestGame.ANSI_RED + "\nHero " + customer.getStat(true) + "\n" +   QuestGame.ANSI_RESET + QuestGame.ANSI_BLUE +customer.inven_toString() + QuestGame.ANSI_RESET);
			System.out.println(QuestGame.ANSI_BLUE + texts[0] + QuestGame.ANSI_RESET + "\n" + texts[1]);
			user_input = scan.next().toLowerCase();
			//buying from market 
			for (int i = 0; i<inputs.length; i++) {
				if (user_input.equals(inputs[i]) || (user_input.equals(inputs[i].substring(0,1)))) {
					System.out.println(QuestGame.ANSI_BLUE +texts[2+i]+ "\n" + line + QuestGame.ANSI_RESET);
					QuestGame.Display(filenames[i]);
					if (i == 3) {
						//for spell there are two more files. 
						QuestGame.Display(filenames[i+1]);
						QuestGame.Display(filenames[i+2]);						
					}
					System.out.println(texts[6] + "you wish to buy from Store " + inputs[i] +":");
					String tool = scan.next();
					if (buyFromMarket(customer, tool, inputs[i])) {
						System.out.println(QuestGame.ANSI_RED+"Purchase complete! Item bought! ᕕ(ᐛ)ᕗ"+QuestGame.ANSI_RESET);
						System.out.println(QuestGame.ANSI_BLUE + customer.inven_toString()+ QuestGame.ANSI_RESET);
					} 
				}
			}
			//deal with selling some item
			if(user_input.equals("sell")) {
				System.out.println(QuestGame.ANSI_BLUE + customer.inven_toString() + QuestGame.ANSI_RESET);
				if (!customer.bagNotEmpty()) {
					System.out.println(QuestGame.ANSI_RED + "You don't have any inventories!" + QuestGame.ANSI_RESET);
				} else {
					System.out.println("What are you selling: (1) Armor/Weapon (2) Potion (3) Spell");
					int type = QuestGame.getInteger(1, 3);
					System.out.println(texts[6] + " sell, under the type that you just specified: " );
					selltoMarket(customer, scan.next(), type-1);
				}
			} 
			//leaving or not 
			System.out.println("\n(1) Another transcation (2) Leave");
			int ans = QuestGame.getInteger(1, 2);
			if (ans == 2) {
				leave = true;
				System.out.println(QuestGame.ANSI_BLUE+texts[7]+QuestGame.ANSI_RESET);
			} 
		} while (leave == false);
		return true;
	}
	
}