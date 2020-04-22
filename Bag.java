
/*
 * An interface for a Bag ADT.
 */
public interface Bag<T> {
    /* 
     * adds the specified item to the Bag.  Returns true on success
     * and false if there is no more room in the Bag.
     */
    boolean add(T item);  

    /* 
     * removes one occurrence of the item with the specified name(if any) from the
     * Bag.  Returns true on success and false if the specified item
     * (i.e., an object equal to item) is not in the Bag.
     */
    boolean remove(String item_name);

    /*
     * returns true if the item with the specifiedis in the Bag, and false
     * otherwise.
     */
    boolean contains(String item_name);

    /*
     * returns the Tool with a specific name if there is one
     */
    T getTool(String name);
     /*
     * returns a string representation of the bag. 
     */
    String toString();
} 
