/*
* Koulou - as long as the Hero stays in this Cell, his strength = strength * 1.1. 
* However, when he leaves the cell, his strength drops back to normal. 
*/
public class Koulou extends StatBoostingCell{
	/*
	* No-arg constructor - calls the no-arg constructor of StatBoostingCell, 
	* which then calls the no-arg constructor of PlainAccessible, then call Cell's. 
	*/
	public Koulou() {
		super();
	}
	/*
	* Constructor - uses the inputed CellTypes Object to assign a type integer, 
	* calls the constructor of Marker, its symbol is yellow with capitalized K. 
	*/
	public Koulou(CellTypes intType){
		symbol = new Marker(StatBoostingCell.ANSI_YELLOW_BACKGROUND +" K "+ QuestGame.ANSI_RESET);
		type = intType.assignType("koulou");
	}
	/*
	* enter - increases strength by 0.1 
	*/
	public void enter(Hero hero) {
		int heroStr = hero.getStrength();
		int boostedStr = (int)(heroStr * 0.1);
		hero.StrIncease(boostedStr);
		System.out.println("Hero " + hero.getName() + " just entered onto a Koulou Cell, receiving a boost on strength by 10%. Current Strength: " + hero.getStrength());
	}
	/*
	* exit - brings strength level backs to normal.  
	*/
	public void exit(Hero hero){
		int boostedStrength = hero.getStrength();
		int originalStr = (int)(boostedStrength / 1.1);
		int boostedAmt = boostedStrength - originalStr;
		hero.StrIncease(-boostedAmt);
		System.out.print("Hero " + hero.getName() + " just exit out of a Koulou Cell, your strength drops back to normal. Current Strength: " + hero.getStrength() + " ");
	}
}