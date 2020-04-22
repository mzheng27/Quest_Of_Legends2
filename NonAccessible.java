/*
 * NonAccessible - Non-accessible Cell with no other characteristics. 
 * implements the abstract method canMoveTo in Cell to return false. 
 */
public class NonAccessible extends Cell {
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	/*
	* no-arg Constructor - calls the no-arg constructor of Cell
	*/
	public NonAccessible() {
		super();
	}
	/*
	 * Constructor - initialize the symbol of this cell to be X
	 */
	public NonAccessible(CellTypes intType) {
		type = intType.assignType("noaccess");
		symbol = new Marker(ANSI_GREEN_BACKGROUND + "   " + QuestGame.ANSI_RESET);
	}
	/*
	* canMoveTo - return false; 
	*/
	public boolean canMoveTo() {
		return false; 
	}
}