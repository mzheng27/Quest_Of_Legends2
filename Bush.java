/*
* Bush - as long as the Hero stays in this Cell, his dexterity = dexterity * 1.1. 
* However, when he leaves the cell, his dexterity drops back to normal. 
*/
public class Bush extends StatBoostingCell{
	/*
	* No-arg constructor - calls the no-arg constructor of StatBoostingCell, 
	* which then calls the no-arg constructor of PlainAccessible, then call Cell's. 
	*/
	public Bush() {
		super();
	}
	/*
	* Constructor - uses the inputed CellTypes Object to assign a type integer, 
	* calls the constructor of Marker, its symbol is yellow with capitalized B. 
	*/
	public Bush(CellTypes intType){
		symbol = new Marker(StatBoostingCell.ANSI_YELLOW_BACKGROUND + " B " + QuestGame.ANSI_RESET);
		type = intType.assignType("bush");
	}
	/*
	* enter - boosts dexterity by 0.1/
	*/
	public void enter(Hero hero) {
		int heroDex = hero.getDex();
		int boostedDex = (int)(heroDex * 0.1);
		hero.dexIncrease(boostedDex);
		System.out.println("Hero " + hero.getName() + " just enters onto a Bush Cell, receives a boost on dexterity by 10%. Current Dexterity: " + hero.getDex());
	}
	/*
	* exit - brings dexterity back to normal. 
	*/
	public void exit(Hero hero) {
		int boostedDex = hero.getDex();
		int originalDex = (int)(boostedDex / 1.1); //dividing by 1.1 brings it back to the original value;
		int boostedAmt = boostedDex - originalDex;		
		hero.dexIncrease(-boostedAmt); 
		System.out.print("Hero " + hero.getName() + " just exit out of a Bush Cell, your dexterity drops back to normal. Current Dexterity: " + hero.getDex() + " ");
	}


}