/*
* ToolBag - a class of linked-list Bag for representing a hero's equipment bag, provides method
* to access and mutate the contents of the bag, request an abstract method to process the tools 
* in this bag in some specific way. 
*/

public abstract class ToolBag implements Bag<Tool> {
	//Inner class 
	private class Node {
		private Tool item;
		private Node next;
		private Node(Tool tool, Node n) {
			item = tool;
			next = n;
		}
	}
	protected String bagName;
	private Node head;
	private int numItems;

	public ToolBag() {
		head = new Node(new Tool(), null);
		numItems = 0;
		bagName = "";
	}
	/*
	* add(Tool item) - adding an item to the bag 
	*/
	public boolean add(Tool item) {
		Node itemAdd = new Node(item, null);
		itemAdd.next = head.next;
		head.next = itemAdd;
		numItems++;
		return true;
	}
	public boolean isEmpty() {
		return (numItems == 0);
	}
	/*
	* remove(String item_name) - removing an item to the bag 
	*/
	public boolean remove(String item_name) {
		boolean result = false;
		Node next = head.next;
		Node prev = head;
		while(next != null) {
			Tool tool = next.item;
			next = next.next;
			if (tool.equals(item_name)) {
				prev.next = prev.next.next;
				numItems--;
				result = true;
			} else {
				prev = prev.next;
			}
		}
		return result;
	}
	
	/*
	* contains(String item_name) - check is the bag contains a tool of the specific
	* name, return true if it does. 
	*/
	public boolean contains(String item_name) {
		boolean result = false;
		Node iter = head.next;
		while (iter != null) {
			Tool tool = iter.item;
			iter = iter.next;
			if (tool.equals(item_name)) {
				result = true;
			}
		}
		return result;
	}
	/*
	* getTool(String name) - find and returns the specific tool is it is in bag, else
	* return a tool with name null. 
	*/
	public Tool getTool(String name) {
		Tool find = new Tool();
		Node iter = head.next;
		while (iter != null) {
			Tool tool = iter.item;
			iter = iter.next;
			if (tool.equals(name)) {
				find = tool;
			}
		}
		return find;
	}
	/*
	* toString() - return the items in the Bag with their effects 
	*/
	public String toString() {
		String str = "{";
		Node iter = head.next;
		while (iter != null) {
			str = str + "<" + iter.item.toString(true) + ">";
			if (iter.next != null) {
                str = str + ", ";
            }
			iter = iter.next;
        }
        str = str + "}";
        return str;
	}
	
	/*
	* equals(String name) - compares a string with this Bag's name. Returns true
	* is either one contains the other, not case sensitive. 
	*/
	public boolean equals(String name) {
		String lowercase = name.toLowerCase();
		String bn = bagName.toLowerCase();
		return (lowercase.contains(bn) || bn.contains(lowercase));
	}

	/*
	* getName - returns the bag's name
	*/
	public String getName() {
		return bagName;
	}

	/*
	 * processTool - process the tools in the bag in some way. 
	 */
	public abstract boolean processTool(String name, Hero hero);
	
}