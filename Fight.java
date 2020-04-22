/*
 * An interface that describes the Fighting senarios, with behvaiors of hp, 
 * level, hp, alive, statistics etc. 
 */
public interface Fight {
	 /* 
     * Checks if the fighter is alive. Returns true if the hp
     * value is positive. 
     */
	boolean alive();
	/* 
     * Get the Fighter's statistics while fighting. Return
     * a String representation of the fighter's name, level, hp etc. 
     */
	String getStat();
	/*
	* Mutator method - reset the hp value by an integer value. 
	*/
	void resetHp(int x);
	/*
	* getHp - Accessor method which returns an integer representation of the hp value
	* of the Fighter. 
	*/
	int getHp();

	/*
	* getLevel - Accessor method which returns an integer representation of the level
	* of the Fighter. 
	*/	
	int getLevel();
	/*
	* levelInc - Increment the hero's level by 1. 
	*/
	int levelInc();

}