# Quest_Of_Legends2
(Uses some color encodings that does not behaves properly on Windows it you're using cmd or powershell)
Originates from the codes of Quest_Of_Legends1 with some variation in the plaing rules and improvements on design. 

>> Design: 

QuestGame: similar to Quest 1, however with changes in the Play method because each round of the game is carried out in a different way. (1) adds a new method, anotherRound, because it gives the Player option for continue playing. (2) Adds an attribute monsters (MonsOnBoard object) because monsters are no longer created at a roll of dice when hero enters a cell, they exist throughout the game. 

	QuestGame - (has a) QuestMap: similar to Quest 1, but with additional functionality added for part 2: 
	(1) methods for teleport, go back etc. 
	(2) Add an attribute intTypes ([CellTypes] object) because the types of cells grow, and it is not good practice to have hard-code integer types. Now can only adds/assign a type indirectly by the methods of CellTypes, this provides better encapsulation. 
	(3) Add attributes to symbolize the winning condition, and market. Together with CellType, they provides more extendability. For instance, in case for checking the winning conditions and checking if a cell is a market, no longer need to compares hard-code types but compares the outputs of the assignType method in CellTypes and the Type method in QuestCell. This way the winning conditions and location of the market can be modidified by changing the value of attributes winConHero, winConMons and market.   

	QuestGame - (has a) Player: quite different from Quest 1. Removes all functionality that allows the Player to move around the board. Also change the inner class Name to a String representing player's name because it simplifies the design without losing any functionality.  
			Player- (has a) TeamHero: similar to Quest 1. Aside from the no-arg constructor, add a contractor to make the number of heroes in this team scalable by taking in an integer input for the scale. 

	QuestGame - (has a) MonsOnBoard: a new class added to represent an ArrayList of LaneMonsters with functionality that allows lanes to be added (if in theory the game were to be scaled up and made larger). ArrayList gives us the ability to increment on the number of lanes.
			MonsOnBoard - (has a) LaneMonster: a new class that represents all monsters in a lane and provides relevant methods such as spawning such monsters. This class gives a smaller unit of Monsters with some defined behaviors, with this class as a foundation it is easier to scale the number of lanes and  supervise the activity of a lane. Because ArrayList implements the Collections interface, together with the compareTo method implemented in Monster, so to sort the list of Monsters according to their position.


______________________________________________________________________________________________________________
<Interface> QuestCell: more concise than Quest 1, methods such as useCell() is removed, adds method canMoveTo() so QuestMap can easily check if a slot is accessible by calling this method on any of its cell, also provides method toString(), so we can call the toString() on every slot on the board to print the board. 
  
	- (implements) PlainAccessible: this originates the class CommonZone in Quest 1 (which is removed from Quest 2). Also remove all the functionality possible uses of this cell (such as the process of starting a fight) from this class to the TeamHero and MonsOnBoard classes to build a clearer inheritance structure s.t. each part has a simple/straight forward responsibility. 
			- (extends PlainAccessible) MonNexus: new class to make the game extendable because in future circumstances, MonNexus may has other characteristics which can be specified in this class.    
			- (extends PlainAccessible) HeroNexus: new class. Market is a private attribute of HeroNexus to provides good encapsulation, s.t. the market can only be accessed through HeroNexus. 
			- (extends PlainAccessible) <Abstract>: StatBoostingCell (implements <interface> BoostableCell): a completely new class which functions as a superclass to the new cell types (Bush, Koulou, Cave) which boosts a Hero's stats. We design this abstract class as parent of the stat boosting cells to provide better inheritance. StatBoostingCell specifies two abstract method enter and exit to describes how the statistics are boosted/diminished when entering or existing the cell. 
					- (extends StatBoostingCell)  Bush: new class added to the inheritance hierarchy
					- (extends StatBoostingCell)  Cave: new class added to the inheritance hierarchy
					- (extends StatBoostingCell)  Koulou: new class added to the inheritance hierarchy
	- (implements) NonAccessible: similar as Minglan's Quest 1, with varied methods as required in QuestCell. Adds implementation canMoveTo always returns false. This gives better inheritance and encapsulation because in Quest 1, it necessary to check the cell's type each time before determine the slot is accessible. 
  
(has a) Marker: those classes that inherits from PlainAccessible and NonAccessible has a Marker object as attribute. Exactly same as in MInglan's Quest 1. We decides to keep this because we think this gives more extendability and better encapsulation to the cell. For instance, if in the future the symbol/marker of the cell changes, we can indirectly changes it with methods provided in Marker. 

	
______________________________________________________________________________________________________________
<Interface> Fight: same as Minglan's implementation in Quest 1. We decides to keep this because it is very important to the inheritance hierarchy, since it describes the behaviors of fighting characters (such as whether they are alive, their current statistics etc). 
  
	-(implements) <Abstract> Fighter: similar to Minglan's Quest 1. Additional attributes row and col and related mutator/accessor methods is implemented because Fighter is movable on Board. This also makes the class more extendable because Fighting character is mostly likely to move in some way in the fighting process. 
		- (extends Fighter) Monster: almost identical to Minglan's implementation of the same file from part 1, with new attributes and methods related to row and col, inherited from the Fighter class. Monster now implements Comparable<Monster> and a compareTo method that compares two Monster by their row number. This makes the class more scalable because now Monster can be sorted in a Collection of Monsters. 

We adds the three children classes of Monsters so the implementation can be extended in many ways in the future by having these different types of monsters  behaving differently. Although they don't have any additional attributes and features, by adding methods to them in the future, the code becomes more reusable.  

				- (extends Monster): Dragon: new class, as explained above. 
				- (extends Monster): Spirit: new class, as explained above. 
				- (extends Monster): Exoskeleton: new class, as explained above. 
				
		- (extends Fighter) Hero: Very different from Minglan's Quest 1. In this new design the hero has three inventories bags: SpellBag, PotionBag, ArmorWeapon Bag. These bags provides better encapsulation because they provides method to process the specific type of tools that the bag has. This changes reduces a code duplication. For instance, it allows the attack method in Hero class to handle both the spell casting and attack request by calling the getTool on the specific bag. Also, the Inventory method can handles both requests of drinking potion and switching armor/weapon by calling processTool on the specific bag. 

We keep these three children classes below because they are good practice of extendability and inheritance. Since they give different implementations of how a hero behaves (s.t. level up) etc. 

				- (extends Hero) Paladin: same as Minglan's implementation of the same file from part 1
				- (extends Hero) Sorcerer: same as Minglan's implementation of the same file from part 1
				- (extends Hero) Warrior: same as Minglan's implementation of the same file from part 1


______________________________________________________________________________________________________________
<Interface> Bag: same as Minglan's implementation of the same file from part 1. We keeps it because it specifies how a ToolBag behaves. 
  
	- (implements) <Abstract> ToolBag: similar to the LLBag in Minglan's Quest 1. We renames it to increase the readability s.t. users can get the usage of this class easily. Also adds an equals methods that compares this Bag's name with some input String. We do so because it increases the scalability of the code, since now we can find a specific ToolBag in a collection of them. Adds an abstract method processTool that process the tools in the bag in some way to build the inheritance hierarchy s.t. when creating a subclass of ToolBag, the subclass will provides a mutator method on the tools in the bag. 
		- (extends ToolBag) SpellBag: a completely new class that allows for easier management of a Hero's inventory (which contains their Spells) 
		- (extends ToolBag) ArmorWeaponBag: a completely new class that allows for easier management of the entire inventory (by splitting up the different types of Tools). Implements the new method processTool to switch on/off a armor or weapon, by designing mutator methods like this, the private data/tools in the bag is better encapsulated. 
		- (extends ToolBag) PotionBag: a completely new class that allows for easier management of a Hero's inventory (which contains their Potions), by designing mutator methods like this, the private Tools/potions in the bag is better encapsulated.
	

______________________________________________________________________________________________________________
<Interface> Sellable: a new interface added to  describes behaviors of items that can be sold and bought. We separates the GameTool<T> interface in Minglan's Quest 1 to two interfaces, and Sellable focuses only on the tool's trading characteristics. We decides to provides abstraction and extendability because an Object may be Sellable but is not 
a GameTool. 
  
<Interface> GameTool: as described above, some of the characteristics/behaviors specified in GameTool in Quest 1 (Minglan's) is moved to Sellable to give more precise abstraction, and increases code reusability because if an Object is only GameTool and not Sellable we don't need to implement another interface to describes its behaviors. 
  
	- (implements Sellable, GameTool) Tool: similar to Minglan's implementation Quest with an additional method to check the type of a Tool, we considered changing Tool to be an abstract class but decide not to because Tool's implementation of useTool() calculates the hero's damage without any extra effect from a special Tool. This provides a base case for the Hero's 		attack scenarios, and by creating a Tool object and calling on its useTool, we reduces duplicate codes to analyze whether the Hero is using a tool or not. 
		- (extends Tool) Weapon: similar to Minglan's implementation of the same file from part 1. We keep it this way to maintain the planned inheritance and provide good encapsulation since this class is responsible for the behaviors related to Hero's interaction (use, switch) with the Tool.
		- (extends Tool) Armor: Exactly same as Minglan's implementation of the same file from part 1, reason as in Weapon
		- (extends Tool) Potions: instead of drinkPo, switchOn now calls removePotion on a Hero object. Because the Hero object has access to its private attribute the inventories ToolBag, calling removePotion returns true if the Potion is in the Hero's bag and returns true. This allows better encapsulation and clearer logic. Also the change in name gives better readability since removePotion describes the act of removing the Potion Object from Bag, but drinkPo can be easily confused with the property of switch_on in Potion class because switch_on performs the process of drinking the potion and gaining the statistics increase. 
		- (extends Tool) Spell: same as Minglan's implementation of the same file from part 1, reason see Weapon. 
				We keep the children classes of Spell because to increase the extendability & reusability of the code.
				- (extends Spell) FireSpell: same as Minglan's implementation of the same file from part 1
				- (extends Spell) IceSpell: same as Minglan's implementation of the same file from part 1
				- (extends Spell) LightSpell: same as Minglan's implementation of the same file from part 1
			

______________________________________________________________________________________________________________
We keep these two classes so when the number of stores gets larger, the method to search for an item remains efficient by first searching for a store, if we find the corresponding store, we then search if that specific item in in store. 

- Market: almost the exact same as Minglan's implementation of the same file from part 1 with some very minor changes that don't affect the general logic behind how it works

	- <Interface> Dealer<T>: an Generic interface added to describes the behavior of Objects that sells items to some type T, to adds a level of abstraction and increase code 	readability s.t. a user can quickly understand the core methods of Store and their functionality. 
  
		- (Market has) Store (implements Dealer) : similar to Minglan's implementation of the same file from part 1. We adds a new methods equals to compares a Store name with an input String. This increases scalability because now it is possible to identify a specific store in a collection. 

 
______________________________________________________________________________________________________________
- MonsterList: same as Minglan's implementation of the same file from part 1. We keep this class to allows encapsulation of data, such that this class provides accessor method to get the list of Monsters of a specific level. We wish to make this class more scalable and extendable adding methods reading files and parsing inputs, however, we fail to resolve the case when this throws an Exception, so the list is still constructed in a hardcoded way. 

- <Interface> QuestList<T extends Hero>: same as Minglan's implementation of the same file from part 1. We keep this interface because we uses polymorphism s.t. the specific list is referred through QuestList<?> s.t. ? is a subclass of Hero. This reduces redundancy in code, for instance, in TeamHero's constructor, since the methods create and getHeros can be called on all QuestList, we implement one for loop instead of three. 

	- (implement QuestList) PaladinList: same as Minglan's implementation of the same file from part 1
	- (implement QuestList) SorcererList: same as Minglan's implementation of the same file from part 1
	- (implement QuestList) WarriorList: same as Minglan's implementation of the same file from part 1
 
- Play: same driver class used in Minglan's implementation of part 1. 
