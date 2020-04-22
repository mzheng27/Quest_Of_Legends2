/*
* Store - a class that represents the Quest's Store which has
* tools that the hero can buy. 
*/
public class Store implements Dealer<Hero> {
	//fields of Store
	private Tool[] items;
	private String store_name;

	/*
	* no-arg Constructor - set items_for_sale to be an empty list of Tools.
	*/
	public Store() {
		items = null;
		store_name = "none";
	}
	/*
	* Constructor - create an store such that items_for_sale refers
	* to a list of tools that the store has. 
	*/
	public Store(Tool[] items, String name) {
		this.items = items;
		store_name = name.toLowerCase();
	}

	/*
	* equals - compares the Store's name with an entry string. If the full name contains this string, 
	* or if they have the first letter, returns true. Not case sensitive. 
	*/
	public boolean equals(String entry) {
		boolean equals = false;
		String entryname = entry.toLowerCase();
		String sn = store_name.toLowerCase();
		if ((sn.contains(entryname)) || entryname.equals(sn.substring(0, 1))) {
			equals = true;
		}
		return equals;
	}
	/*
	* sell - uses when the store sells an item to a hero. Returns true if the hero
	* bought the item successfully. 
	* The hero is also not allowed to buy a tool which is already 
	* in his/her bag and the tool is not a potion. 
	*/
	public boolean sell(Hero hero, String item_name) {
		boolean result = false;
		if (items != null){
			Tool find = new Tool();
			for (Tool item:items) {
				if (item.equals(item_name)) {
					find = item;
				}
			}
			if (find.toString() != null) {
				System.out.println(find.toString());
				if (find.getCost() > hero.getMoney() || find.getLevel() > hero.getLevel()) {
					System.out.println(QuestGame.ANSI_RED+"Purchase denied, check your spending power & level!"+QuestGame.ANSI_RESET);
				} else if (hero.getEquip(store_name).contains(item_name) && (!store_name.contains("potion"))) {
					System.out.println(QuestGame.ANSI_RED+"Duplicate purchase, item (not Potion) already in bag!"+QuestGame.ANSI_RESET);
				} else {
					result = true;
					hero.buyEquip(find, store_name);
				}
			}
		}
		return result;
	}
	/*
	* contains - returns true is the store contains a tool with the specified name
	* or abbreviation. Uses the equals(String name) method in Tool class. 
	*/
	public boolean contains(String name) {
		boolean result = false;
		if (items != null) {
			for (Tool item:items) {
				if (item.equals(name)) {
					result = true;
					break;
				}
			}
		}
		return result;
	}

	/*
	* getStock - return the stock of the store, the list of tools which
	* the store has. 
	*/
	public Tool[] getStock() {
		return items;
	}
 }