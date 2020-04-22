import java.util.ArrayList;
import java.util.Arrays;
/*
* CellTypes - helps to assing the interger types to cell names. 
*/
public class CellTypes {
	private static ArrayList<Integer> intOfTypes;
	private static ArrayList<String> nameOfTypes;
	/*
	* Constructor - initiates the values of types already specified in Quest's playing. This should
	* be an empty list in terms of extendability. But in this case, we are certain of the types of cells, 
	* the default values are hard coded. 
	*/
	public CellTypes() {
		intOfTypes = new ArrayList<Integer> (Arrays.asList(-1, 1, 2, 3, 4, 5, 6, 7));
		nameOfTypes = new ArrayList<String>(Arrays.asList("none", "heronexus", "plain", "noaccess", "bush", "koulou", "cave", "monsnexus"));
	}

	/*
	* assignType - returns an integer corresponding to the Cell of typeName. 
	*/
	public int assignType(String typeName) {
		int index = 0;
		if (nameOfTypes.contains(typeName)) {
			index = nameOfTypes.indexOf(typeName); 
		}
		return intOfTypes.get(index);
	}

	/*
	* addType - add a new type to the arraylist nameOfTypes and assign it an integer in 
	* intOfTypes
	*/
	public void addType(String name) {
		intOfTypes.add(intOfTypes.size());
		nameOfTypes.add(name);
	}

	/*
	* toString - returns a string representing all the cells names and their int types. 
	*/
	public String toString() {
		String str = "";
		for (int i = 0; i< Math.min(intOfTypes.size(), nameOfTypes.size()); i++) {
			str = str + "<" + intOfTypes.get(i) + ", " + nameOfTypes.get(i) + ">; ";
		}
		return str;
	}
}