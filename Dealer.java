/*
* Dealer - describes the behavior of Dealer who sells items to some type T. Provides 
* method to check the stock, and to sell an item.
*/
public interface Dealer<T> {
	//returns true if the item with this specific name is sold to the intended customer
	boolean sell(T customer, String item_name);
	//returns true if the Dealer has an item with this name in stock
	boolean contains(String item_name);
	//returns an array of current available stock 
	Object[] getStock();
}