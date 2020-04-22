import java.util.ArrayList;
import java.util.InputMismatchException;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;

/*
* QuestGame - a class that embodies the rules and playings of the Quest. 
*/
public class QuestGame {
	private Player player;
	private MonsOnBoard monsters; 
	//board 
	private QuestMap board;
	//colors 
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_RESET = "\u001B[0m";
	/*
	* No-arg Constructor - three lanes. call the printHello() method to print the intro message,
	* and calls the constructor of TeamHero, MonsOnBoard, QuestMap objects 
	*/
	public QuestGame() {
		printHello(); 
		Display("hello.txt");
		player = new Player();
		monsters = new MonsOnBoard();
		board = new QuestMap(8, 8);
	}
	/*
	* Constructor that tkaes in an integer for the number of lanes intended. 
	*/
	public QuestGame(int numLane) {
		printHello(); 
		Display("hello.txt");
		player = new Player(numLane);
		monsters = new MonsOnBoard(numLane);
		board = new QuestMap(8, (3*numLane-1));
	}
	/*
	* Play - Each round the heros need to take an action. An action is either 
	* attack/spell/change weapon/armor/potion or move or teleport or go back to Nexus
	* or using the market. Invalid moves does not count. displaying hero info and map 
	* does not count as a move. 
	*/
	public void Play() {
		Scanner scan = new Scanner(System.in);
		boolean quit = false;
		boolean monster_win = false;
		boolean hero_win = false;
		int round = 1;
		String move = "wasd";
		TeamHero heros = player.getHeros();
		while ((!quit) && (!monster_win) && (!hero_win)) {
			board.printMap(heros, monsters);
			System.out.println("All Monsters & Current Location: ");
			System.out.println(ANSI_BLUE+monsters.toString()+ANSI_RESET);
			System.out.println( "+~~~~+~~~~~~~+~~~~~+~~~~  Monsters' turn (either attacks or move forward) ~~~~+~~~~~~~+~~~~~+~~");
			if ((round%5) == 0) {
				System.out.println("Monsters Spawn.....");
				monsters.spawn(Math.min(10, heros.highestLevel())); //Monster level is bouned by 10. 
				board.printMap(heros, monsters);
			}
			monster_win = monsters.executeRound(heros, board);
			board.printMap(heros, monsters);
			System.out.println("All Monsters & Current Location: "+ANSI_RESET);
			System.out.println(ANSI_BLUE+monsters.toString()+ANSI_RESET);
			System.out.println("~~~~+~~~~~+~~~~+~~~~~~~+~~~~~+~~~~" + player.toString() + ", your heroes's turn ~~~~+~~~~~~~+~~~~~+~~~~+~~~");
			for (int i = 0; i< heros.getHeros().length; i++) {
				Hero hero = heros.getHeros()[i];
				System.out.println(hero.prepHero()); //Increase mana & hp by 10%
				boolean acted = false;
				while ((!quit) && (!acted) && (hero.alive())) {
					System.out.println("༾( ˊ ˅ ˋ )༿❤︎༾( ˊ ˅ ˋ )༿ The " + (i+1) + "th Hero <" + ANSI_RED + hero.toString() + ANSI_RESET + "> Position (" + hero.getRow() + ", " + hero.getCol() + ") your action for this round (Unfulfilled/Invalid requests don't count): ");
					String next = scan.next().toLowerCase();
					if (move.contains(next)) {
						LaneMonster laneMons= monsters.getLaneMonster(hero.getLane()-1);
						if ((!laneMons.scanMove(hero)) && next.equals("w")) {
							System.out.println(ANSI_RED + "(Invalid request) Can't move pass neighboring monsters"+ ANSI_RESET);
						} else {
							acted = board.move_Useraccess(heros, hero, next); 
							if (acted) {
								board.printMap(heros, monsters);
							}
						}
					} else if (next.equals("teleport") || next.equals("t")) {
						board.teleport(heros, hero, monsters); 
						board.printMap(heros, monsters);
						acted = true;
					} else if (next.equals("back") || next.equals("b")) {
						if (hero.getRow() == board.heroNexusIndex()){
							System.out.println(ANSI_RED +"(Invalid request) You are at the Nexus!" + ANSI_RESET);
						} else {
							board.goBack(hero, heros);
							board.printMap(heros, monsters);
							acted = true;
						}
					} else if (next.equals("change") || next.equals("potion")) {
						acted = hero.Inventory(next);
					} else if (next.equals("q")) {
						System.out.println(ANSI_BLUE+"======== ヾ(￣▽￣)Bye~Bye~ ======== Thanks for Playing ♪ ========" +ANSI_RESET);
						quit = true;
					} else if (next.equals("market")) {
						board.useMarket(hero); //"the process of selling/buying does not count as an action"
					} else if (next.equals("map")) {
						board.printMap(heros, monsters); 
					} else if (next.equals("i")) {
						//display the info of all heroes when not during a fight.
						System.out.println(ANSI_BLUE+heros.toString(true)+ANSI_RESET);
					} else if (next.equals("attack") || (next.equals("spell"))) {
						if ((next.equals("spell")) && (hero.getEquip(next).isEmpty())) {
							System.out.println(ANSI_RED + "(Invalid request) Don't have any spell in inventory bag!"+ ANSI_RESET);
						} else {
							if (monsters.getLaneMonster(hero.getLane()-1).scan(hero).size() > 0) {
								//if there are monsters in the hero's attack range. 
								heros.executeRound(monsters, hero, next);
								acted = true;
							} else {
								System.out.println(ANSI_RED +"(Invalid request) No monsters in attack range!"+ ANSI_RESET);
							}
						}
					} else {
						System.out.println(ANSI_RED + "Invalid input!"+ANSI_RESET);
					}
				} 
				if (board.heroCheckWin(hero)) {
					hero_win = true;
				}
				if (hero_win) {
					System.out.println("======== Congratulations! " + player.toString() +  " Heros win! ========");
					break;
				} 
			}
			//End of this round 
			round++;
			if ((!hero_win) && monster_win) {
				System.out.println("================ Monsters win! ================");
			}
		}
		//End of this game
		System.out.println("Game Ends. Do you wish to play another round? (Y/y)");
		if (scan.next().equalsIgnoreCase("y")) {
			anotherRound(hero_win);
		}
	}

	/*
	* anotherRound - helper method that resets the attributes player, monsters, and board
	* and start another game of Quest. 
	*/
	private void anotherRound(boolean hasWon) {
		if (hasWon) {
			System.out.println("Good job on the last Round!");
		} else {
			System.out.println("Keep the spirits high, better luck this time!");
		}
		player = new Player();
		monsters = new MonsOnBoard();
		board = new QuestMap(8, 8);
		Play();
	}

	/*
	* Display - helper function that reads and prints from the specified file filename
	*/
	public static void Display(String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line = br.readLine();
			while (line != null) {
				System.out.println(ANSI_BLUE+ line + ANSI_RESET);
				line = br.readLine();
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	* getInteger - helper function that gets an integer from user within a specific bound. 
	*/
	public static int getInteger(int lowerBound, int higherBound) {
		Scanner scan = new Scanner(System.in);
		int in = 0;
		boolean notInt = true;
		do {
			notInt = true;
			System.out.println("Please enter an valid integer in the range " + lowerBound + " to " + higherBound + ":");
			String input = scan.next();
			try {
				in = Integer.parseInt(input);
				notInt = false;
			} catch (NumberFormatException e) {
				System.out.println(ANSI_RED+"Your input is not an integer! ( ´•︵•` ) New number: "+ANSI_RESET);
			}
		} while (notInt || in < lowerBound || in > higherBound);
		return in;
	}

	public void printHello() {
		System.out.print(ANSI_BLUE);
		System.out.println("		╭╮  ╭☆╭—————╮ ╭╮     ╭—————╮ ╭—————╮"); 
		System.out.println("		││  │││ ╭———★ ││     │╭———╮│ │╭———╮│" );
		System.out.println("		│╰——╯│| ╰——╮  ││      ││   ││ ││   ││");
		System.out.println("		│╭——╮││ ╭——╯  ││      ││   ││ ││   ││ ");
		System.out.println("		││  │││ ╰———╮ │╰———╮  │╰———╯│ │╰———╯│ ");
		System.out.println(" 		╰★  ╰╯╰—————╯ ☆————╯  ★—————╯ ☆—————╯ "+ ANSI_RESET);
	}
	

}