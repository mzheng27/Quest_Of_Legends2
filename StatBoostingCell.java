/*
* StatBoostingCell - as name, requires two methods exit and enter 
* that describes the statistic boosting effects when the Hero enters
* and exit this Cell. 
*/
public abstract class StatBoostingCell extends PlainAccessible implements BoostableCell<Hero>{
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	/*
	* No-arg constructor - calls the no-arg constructor of PlainAccessible, 
	* which then calls the no-arg constructor of Cell. 
	*/
	public StatBoostingCell() {
		super();
	}
	/*
	* exit - describes the statistic boosting effects when the Hero leaves
	* this cell. 
	*/
	public abstract void exit(Hero hero);
	/*
	* enter - describes the statistic boosting/diminishing effects when the Hero enters
	* this cell. 
	*/
	public abstract void enter(Hero hero);
}