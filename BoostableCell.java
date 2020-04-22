/*
* BoostableCell - encapsulates the functionality of a stat boosting Cell, by 
* specifying the enter and exit method which describes how the stat is boosted/diminished
* when the cell is entered onto or exit out of.  
*/
public interface BoostableCell<T> {
	/*
	* exit - called once exit out of this cell, boost/dimish the stats of T accordingly
	*/
	void exit(T character);
	/*
	* enter - called once enter upon this cell, boost/dimish the stats of T accordingly
	*/
	void enter(T character);
}