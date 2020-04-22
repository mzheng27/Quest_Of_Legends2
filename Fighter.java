/*
* Fighter - superclass of Hero and Monster, implements the Fight interface and provides
* methods alive(), getStat(), getStat(boolean x), resetHp(int x). 
*/
public abstract class Fighter implements Fight {
	private String name;
	private String abbr; //first 4 letters of name 
	private int level;
	private int hp; 
	private int defense; 
	protected int dodge; 
	 
	protected int row_index;
	protected int col_index;
	/*
	* No-arg constructor - sets all fields to either 0 or null. Set both 
	* row and col to be -1. 
	*/
	public Fighter() {
		name = null;
		abbr = null;
		level = 0;
		hp = 0;
		defense = 0;
		dodge = 0;
		//The fighter object is not on the board yet. 
		row_index = -1;
		col_index = -1;
	}
	/*
	* Constructor - sets the name, level and hp according to inputs. 
	* initiaze dodge and defense to be 0.  
	*/
	public Fighter(String name, int level, int hp) {
		if (level > 0) {
			this.name = name;
			abbr = name.substring(0, 4);
			this.level = level;
			this.hp = hp;
		}
		defense = 0;
		dodge = 0;
		row_index = -1;
		col_index = -1;
	}

	/*
	* resetRow - expects a positive input, reset the row index. 
	*/
	public void resetRow(int row) {
		row_index = row;
	}

	/*
	* resetCol - expects a positive input, reset the col index.
	*/
	public void resetCol(int col) {
		col_index = col;
	}

	/*
	* getRow - returns an integer of the row index. 
	*/
	public int getRow() {
		return row_index;
	}

	/*
	* getCol - returns an integer of the column index. 
	*/
	public int getCol() {
		return col_index;
	}
	/*
	* getLane - returns the lane number of the Fighter. 
	*/
	public int getLane() {
		int lane = col_index/3;
		return (lane+1);
	}

	/*
	 * equals(String x) - Compares the Fighter with a String representation
	 * if the string x equals to the fighter's name or abbr, neglecting the
	 * letter's case, return true. Returns false if otherwise. 
	 */
	public boolean equals(String x) {
		boolean result = false;
		String lowerCased = x.toLowerCase();
		String name_lower = name.toLowerCase();
		String abbr_lower = abbr.toLowerCase();
		if (name_lower.equals(lowerCased) || abbr_lower.equals(lowerCased)) {
			result = true;
		}
		return result;
	}
	/*
	 * getName() - returns a String representation of the name of the fighter. 
	 */
	public String getName() {
		return name;
	}
	
	/*
	* resetHp - resets the hp to the input value. Expecting a positive input. 
	*/
	public void resetHp(int x) {
		if (x > 0) {
			hp = x;
		} else {
			hp = 0;
		}
	}
	/*
	* getHp - Accessor method which returns an integer representation of the hp value
	* of the Fighter. 
	*/
	public int getHp() {
		return hp; 
	}
	
	/*
	* getLevel - Accessor method which returns an integer representation of the level
	* of the Fighter. 
	*/	
	public int getLevel() {
		return level;
	}
	/*
	* levelInc - Increment the hero's level by 1. 
	*/
	public int levelInc() {
		level = level + 1;
		return level;
	}
	/*
	* getDefense - Accessor method which returns an integer representation of the defense
	* of the Fighter. 
	*/	
	public int getDefense() {
		return defense; 
	}
	/*
	* resetDef - Mutator method which rests the defense level of 
	* the Fighter to a certain amount;
	*/
	public void resetDef(int x) {
		if (x > 0) {
			defense = x;
		} else {
			defense = 0;
		}
	}

	/*
	* getDodge - Accessor method which returns an integer representation of the dodge
	* of the Fighter. 
	*/	
	public int getDodge() {
		return dodge;
	}

	/*
	* alive - return true if the fighter's hp value is positive and false if 
	* otherwise. 
	*/	
	public boolean alive() {
		boolean result = true;
		if (hp <= 0) {
			result = false;
		}
		return result;
	}
	/* 
     * getStat - Get the Fighter's statistics while fighting. Return
     * a String representation of the fighter's name, level, hp etc. 
     */
	public String getStat() {
		String str = ""; 
		str = str + "Name: " + name + "(Level " + level + ") HP: " + hp + ", Location: (" + row_index + ", " + col_index + ")";
		return str;
	}

	/* 
     * toString - Return a String representation of the Fighter, with name
     * and level.  
     */
	public String toString() {
		String str = ""; 
		str = str  + name + "(Level " + level + ")";
		return str;
	}

	
}
