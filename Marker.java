/*
 * Marker - represents the Marker of a QuestCell such as market, NonAccessible, and CommonZone. 
 */
public class Marker {
	//a string representing the cell
	String symbol; 
	/*
	 * no-arg Constructor - setting the symbol to null
	 */
	public Marker() {
		symbol = null;
	}
	/*
	 * constructor - sets the symbol to be the inputed string. 
	 */
	public Marker(String mark) {
		symbol = mark;
	}
	/*
	 * toString - returns the symbol as a string representation of the cell. 
	 */
	public String toString() {
		return symbol;
	}
}