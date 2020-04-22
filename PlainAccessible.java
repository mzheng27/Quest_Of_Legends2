/*
* PlainAccessible - Accessible cell with no other characteristics, 
* implements the abstract method canMoveTo in Cell so it returns true. 
*/
public class PlainAccessible extends Cell {
	/*
	* No-arg constructor - calls the no-arg constructor of Cell
	*/
	public PlainAccessible() {
		super();
	}
	/*
	* Constructor - uses the inputed CellTypes Object to assign a type integer, 
	* calls the constructor of Marker. 
	*/
	public PlainAccessible(CellTypes intType) {
		type = intType.assignType("plain");
		symbol = new Marker("   ");
	} 
	/*
	* canMoveTo - returns true;
	*/
	public boolean canMoveTo() {
		return true; 
	}
}