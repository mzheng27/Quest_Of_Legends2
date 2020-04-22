/*
* MonNexus - monster's Nexus. 
*/
public class MonNexus extends PlainAccessible {
	/*
	* No-arg constructor - calls the no-arg constructor of PlainAccessible, 
	* which then calls the no-arg constructor of Cell. 
	*/
	public MonNexus() {
		super();
	}
	/*
	* Constructor - uses the inputed CellTypes Object to assign a type integer, 
	* calls the constructor of Marker, its symbol is a flag in red. 
	*/
	public MonNexus(CellTypes intType) {
		type = intType.assignType("monsnexus");
		symbol = new Marker(QuestGame.ANSI_RED + "|>~" + QuestGame.ANSI_RESET);
	}

}