/*
 * GameTool<T> - an Generic Interface that describes how GameTool is used, switch on and off
 * and its many other effects. 
 */
public interface GameTool<T>{
	/*
	* getEffect - Accessor method that return the effect/damage caused by the tool
	*/
	int getEffect();
	/*
	* toString() method which output the name of the tool 
	*/
	String toString();
	/*
	 * toString(boolean x) - returns a String representation of the Tool with its
	 * name followed by effect. 
	 */
	String toString(boolean x);
	/*
	* switchOn - method which describes the process of changing to 
	* this tool (which is checked to be in the hero's bag). 
	* Return true if the process if completed and false if the 
	* hero can't switch to this tool. 
	*/
	boolean switchOn(T character);

	boolean switchOff(T character);

	String useTool (T character, Monster m);
}