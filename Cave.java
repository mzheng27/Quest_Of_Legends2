/*
* Cave - as long as the Hero stays in this Cell, his agility = agility * 1.1. 
* However, when he leaves the cell, his agility drops back to normal. 
*/
public class Cave extends StatBoostingCell {
	/*
	* No-arg constructor - calls the no-arg constructor of StatBoostingCell, 
	* which then calls the no-arg constructor of PlainAccessible, then call Cell's. 
	*/
	public Cave() {
		super();
	}
	/*
	* Constructor - uses the inputed CellTypes Object to assign a type integer, 
	* calls the constructor of Marker, its symbol is yellow with capitalized C. 
	*/
	public Cave(CellTypes intType) {
		symbol = new Marker(StatBoostingCell.ANSI_YELLOW_BACKGROUND + " C "+ QuestGame.ANSI_RESET);
		type = intType.assignType("cave");
	}
	/*
	* enter - increases agility by 0.1. 
	*/
	public void enter(Hero hero) {
		int heroAgility = hero.getAgi();
		int boostedAgility = (int)(heroAgility * 0.1);
		hero.agiIncrease(boostedAgility);
		System.out.println("Hero " + hero.getName() + " just enters onto a Cave Cell, receives a boost on agility by 10%. Current agility: " + hero.getAgi());
	}
	/*
	* exit - brings agility back to normal.
	*/
	public void exit(Hero hero) {
		int boostedAgility = hero.getAgi();
		int originalAgility = (int)(boostedAgility / 1.1);
		int boostedAmt = boostedAgility - originalAgility;
		hero.agiIncrease(-boostedAmt);
		System.out.print("Hero " + hero.getName() + " just exits out of a Cave Cell, your agility drops back to normal. Current agility: " + hero.getAgi() + " ");
	}


}