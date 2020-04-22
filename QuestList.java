/*
 *  QuestList<T extends Hero> - a generic interface for the Lists of Heros in Quest. 
 *  Ensures the Objects which implements it provides a create method and a getHeros method.
 */
public interface QuestList<T extends Hero> {
	/*
	 * create - takes in a Hero object and creates and return a subclass of Hero
	 * with the same statistics (name, money, skills etc). 
	 */
	T create(Hero h);
	/*
	 * getHeros - returns an array of a subclass of Hero which represents all the heros in the Quest. 
	 */
	T[] getHeros();
}