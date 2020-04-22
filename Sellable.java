/*
* Sellable - an interface that describes items that can be sold and bought. 
*/
public interface Sellable {
	/*
	* getCost - Accessor method that return the cost of the tool 
	*/
	int getCost();
	/*
	* equals(String x) - compares a tool with a String. Returns true is the String
	* is equal to the Tool's name or abbr, neglecting the case of Strings.
	*/
	boolean equals(String x);
	/*
	* equals (Tool other) - compares two tools, return true is the name of the two 
	* is the same and false if otherwise. 
	*/
	boolean equals(Tool other);
}