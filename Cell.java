/*
* Cell - has type and symbol, provides the methods type and toString 
* requested by QuestCell. 
*/
public abstract class Cell implements QuestCell {
	protected int type; 
	protected Marker symbol; 
	/*
	* No-arg constructor - type of -1, and calls the no-arg constructor
	* of Marker class. 
	*/
	public Cell() {
		type = -1;
		symbol = new Marker();
	}
	/*
	* type - returns the integer representing its type.
	*/
	public int type() {
		return type; 
	} 
	/*
	* toString - return its string representation on the board. 
	*/
	public String toString() {
		return symbol.toString();
	}

	/*
	* canMoveTo - return true if this cell is accessible. 
	*/
	public abstract boolean canMoveTo();
}