/*
* QuestCell - an interface for the tiles/cells of the Quest's map/board
*/
public interface QuestCell {
	/*
	*  moveTo - returns true if this cell is accessible, and false if it is not
	*/
	boolean canMoveTo();
	/*
	* type - returns an integer representing the cell's type. In this implementation 
	* Market is of type 1, plain of type 2, nonaccessible of type 3, and Bush, Koulou,
	* Cave of type 4, 5, 6. 
	*/
	int type();
	
	/*
	 * toString - calls the toString() in Marker and returns a symbol of this cell 
	 * to the Map of the game.
	 */
	String toString();
}