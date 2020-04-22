import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

/*
* TeamHero - represents a team of hero controlled by one player
* such that the number of heros ranges from 1 to 3.
*/
public class TeamHero {
	//list of  Heroes
	private Hero[] teamOfHeros;
	private int numHeros;
	//all Heroes available 
	private static QuestList<?>[] allLists = new QuestList[]{new WarriorList(), new SorcererList(), new PaladinList()};

	/*
	* Constructor - construct a TeamHero object with an array of heroes
	* as input. If the array h has length larger than 3, only takes the first
	* three elements of h. 
	*/
	public TeamHero() {
		printHeros();
		numHeros = 3;
		teamOfHeros = new Hero[numHeros];
		Scanner scan = new Scanner(System.in);
		boolean notFind = true;
		for (int i = 0; i<numHeros; i++) {
			notFind = true;
			do {
				System.out.print("Enter a hero's name or abbr for lane " + (i+1) + ":");
				String name = scan.next();
				for (QuestList<?> list: allLists) {
					for (Hero h : list.getHeros()) {
						if (h.equals(name)) {
							teamOfHeros[i] = list.create(h);
							teamOfHeros[i].resetCol(3*i);
							teamOfHeros[i].resetRow(7);
							notFind = false;
							break;
						}
					}
					if (!notFind) {
						break;
					}
				}
				if (notFind) {
					System.out.print(QuestGame.ANSI_RED + "Your Entry is not a Hero! "+ QuestGame.ANSI_RESET);
				}
			} while (notFind);
		}
	}

	/*
	* Constructor - takes in an integer for the number of hero in the team. 
	* sets numHeros to it, otherwise same as the no-arg constructor. 
	*/
	public TeamHero(int numOfHero) {
		numHeros = numOfHero;
		printHeros();
		teamOfHeros = new Hero[numHeros];
		Scanner scan = new Scanner(System.in);
		boolean notFind = true;
		for (int i = 0; i<numHeros; i++) {
			notFind = true;
			do {
				System.out.print("Enter a hero's name or abbr for lane " + (i+1) + ":");
				String name = scan.next();
				for (QuestList<?> list: allLists) {
					for (Hero h : list.getHeros()) {
						if (h.equals(name)) {
							teamOfHeros[i] = list.create(h);
							teamOfHeros[i].resetCol(3*i);
							teamOfHeros[i].resetRow(7);
							notFind = false;
							break;
						}
					}
					if (!notFind) {
						break;
					}
				}
				if (notFind) {
					System.out.print(QuestGame.ANSI_RED + "Your Entry is not a Hero! "+ QuestGame.ANSI_RESET);
				}
			} while (notFind);
		}
	}

	/*
	* printHeros - print all Heroes available for the player to choose. 
	*/
	public static void printHeros() {
		String[] filenames = {"Warriors.txt",  "Sorcerers.txt", "Paladins.txt"};
		for (String file:filenames) {
			String name = file.substring(0,  file.length()-4);
			System.out.println(QuestGame.ANSI_BLUE+ name + ": "+ QuestGame.ANSI_RESET);
			QuestGame.Display(file);
		}
	}
	
	/*
	* highestLevel - Return an integer that represents the highest level
	* of heros in this team. 
	*/
	public int highestLevel() {
		int max = 1;
		for (Hero hero:teamOfHeros) {
			if (hero.getLevel() > max) {
				max = hero.getLevel();
			}
		}
		return max;
	}

	/*
	* Occupied - Returns true if the specific position is occupied by some Hero, and 
	* false if other wise. 
	*/
	public boolean Occupied(int row, int col) {
		boolean occupied = false;
		for (Hero h: teamOfHeros) {
			if ((h.getRow() == row) && (h.getCol() == col)) {
				occupied = true; 
				break;
			}
		}
		return occupied;
	}


	/*
	* toString(boolean x) - returns the statistics of all heros belonging to this player
	* when not during a fight.
	*/
	public String toString(boolean x) {
		String str = "";
		for (int i = 0; i<numHeros; i++) {
			str = str+ (i+1) + " " +teamOfHeros[i].getStat(x) + "\n";
		}
		return str;
	}
	/*
	* toString() - returns the statistics of all heros belonging to this player
	* when during a fight.
	*/
	public String toString() {
		String str = "";
		for (int i = 0; i<numHeros; i++) {
			str = str+ (i+1)+ " " +teamOfHeros[i].getStat() + "\n";
		}
		return str;
	}
	
	/*
	* numHero - accessor method that returns the number of heros in this team. 
	*/
	public int numHero() {
		return numHeros;
	}

	/*
	* getHeros - accessor method that returns all the hero in the team. 
	*/
	public Hero[] getHeros() {
		return teamOfHeros;
	}

	/*
	* scan - returns an ArrayList of Hero is a Monster's attack range. 
	*/
	public ArrayList<Hero> scan(Monster m) {
		ArrayList<Hero> inRange = new ArrayList<Hero> ();
		int mRow = m.getRow();
		int mCol = m.getCol();
		for (Hero hero:teamOfHeros) {
			if (((m.getLane() == hero.getLane())&&(mRow == hero.getRow())) || ((mCol == hero.getCol())&&(mRow == (hero.getRow()-1)))) {
				inRange.add(hero);
			}
		}
		return inRange;
	}

	/*
	* executeRound - One round of fight by the Hero to a Monster in range selected by the user, 
	* by some method of fighting (attack or spell casting).
	*/
	public void executeRound(MonsOnBoard monsters, Hero hero, String methodOfFight) {
		LaneMonster lane = monsters.getLaneMonster(hero.getLane()-1);
		ArrayList<Monster> inRange = lane.scan(hero);
		Scanner scan = new Scanner(System.in);
		int index = 0;
		if (inRange.size() > 1) {
			System.out.println("Monsters in your attack range:");
			for (int i = 0; i < inRange.size(); i++) {
				System.out.println(QuestGame.ANSI_BLUE+ (i+1) + inRange.get(i).getStat() + "\n"+ QuestGame.ANSI_RESET);
			}
			System.out.print("Which Monster to fight with?");
			index = QuestGame.getInteger(1, inRange.size()) - 1;
		} 
		System.out.println("Hero info before fight? (Y/y)");
		if (scan.next().equalsIgnoreCase("y")) {
			System.out.println(hero.getStat());
		}
		Monster target = inRange.get(index);
		if (hero.attack_useSpell(target, methodOfFight)) {
			//if the monster is killed
			lane.monsterKilled(target.getRow(), target.getCol());
			hero.reward(target.getLevel() *100);
		}
	}
	
	
}