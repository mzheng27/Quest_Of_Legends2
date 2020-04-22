import java.util.Scanner;
/*
* Player - a class that represent the Player of game. 
*/
public class Player {
	//fields of Player
	private TeamHero heros;
	private String name;
	/*
	 * No-arg constructor - calls the no-arg constructor of Name and TeamHero 
	 */
	public Player() {
		Scanner scan = new Scanner(System.in);
		System.out.print("Player's name: ");
		name = scan.next();
		heros = new TeamHero();	
	}
	public Player(int numLane) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Player's name: ");
		name = scan.next();
		heros = new TeamHero(numLane);
	}
	public TeamHero getHeros() {
		return heros;
	}
	/*
	* toString() - returns a String representation of the Player with index, name and level. 
	*/
	public String toString(){
		String str = "";
		str = str + "Player " + name; 
		return str;
	}
}