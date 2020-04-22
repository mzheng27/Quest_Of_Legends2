/*
* HeroNexus - hero's Nexus, one of its attribute is the Market Object and provides 
* method useMarket to access the Market's functionalities
*/
public class HeroNexus extends PlainAccessible {
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	private final Market market = new Market();
	/*
	* No-arg constructor - calls the no-arg constructor of PlainAccessible, 
	* which then calls the no-arg constructor of Cell. 
	*/
	public HeroNexus() {
		super();
	}
	/*
	* Constructor - uses the inputed CellTypes Object to assign a type integer, 
	* calls the constructor of Marker, its symbol is a flag in blue background. 
	*/
	public HeroNexus(CellTypes intType) {
		type = intType.assignType("heronexus");
		symbol = new Marker(ANSI_BLUE_BACKGROUND + "|>~" + QuestGame.ANSI_RESET);
	}
	/*
	* useMarket - allows the Hero to use buy and sell items. 
	*/
	public void useMarket(Hero hero) {
		market.useCell(hero);
	}
}