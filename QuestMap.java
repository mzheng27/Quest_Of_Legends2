/*
* QuestMap - a class that represents the Quest's map/board and its many usage. 
*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
public class QuestMap {
	//fields 
	public QuestCell [][] cells;
	public int rowBound;
	public int colBound;
	private static CellTypes intTypes = new CellTypes();
	//Assign winning conditionas and market location to varibles to make the implementation extendable
	private static String winConHero = "monsnexus";
	private static String winConMons = "heronexus";
	private static String market = "heronexus";
	//all the cell types
	private static final HeroNexus heroNexus = new HeroNexus(intTypes);
	private static final MonNexus mNexus = new MonNexus(intTypes);
	private static final NonAccessible noAccess = new NonAccessible(intTypes);
	private static final PlainAccessible plain = new PlainAccessible(intTypes);
	private static final StatBoostingCell[] boostCells = {new Bush(intTypes), new Koulou(intTypes), new Cave(intTypes)};

	/*
	* Constructor - Expects positive integers r and c. Create 
	* a QuestMap such that all its cells implement the QuestCell interface 
	* that provides the method useCell().
	*/
	public QuestMap(int r, int c) {
		cells = new QuestCell[r][c];
		rowBound = r;
		colBound = c;
		Random random = new Random(); 
		for (int row = 0; row < rowBound; row++) {
			for (int col = 0; col < colBound; col++) {
				if (((col+1)%3) == 0) {
					cells[row][col] = noAccess;
				} else if (row == (rowBound - 1)){
					cells[row][col] = heroNexus;
				} else if (row == 0){
					cells[row][col] = mNexus;
				} else if (random.nextInt(100) < 12) {
					cells[row][col] = boostCells[0];
				} else if (random.nextInt(100) < 24) {
					cells[row][col] = boostCells[1];
				} else if (random.nextInt(100) < 36) {
					cells[row][col] = boostCells[2];
				} else {
					cells[row][col] = plain;
				}
			}
		}
	}
	
	public boolean heroCheckWin(Hero hero) {
		int hero_row = hero.getRow();
		int hero_col = hero.getCol();
		return (cells[hero_row][hero_col].type() == intTypes.assignType(winConHero));
	}

	public boolean monsCheckWin(Monster m) {
		int m_row = m.getRow();
		int m_col = m.getCol();
		return (cells[m_row][m_col].type() == intTypes.assignType(winConMons)); 
	}
	
	public int heroNexusIndex() {
		return (rowBound-1);
	}	
	/*
	 * useMarket(Hero hero) - allows the board to resolve the situation when
	 * a player wish to use the market.  
	 */
	public boolean useMarket(Hero hero) {
		boolean acted = false; 
		int r = hero.getRow();
		int c = hero.getCol();
		if (r > rowBound || c> colBound||r < 0|| c < 0) {
			System.out.println(QuestGame.ANSI_RED+"Location out of range."+QuestGame.ANSI_RESET);
		} else if (cells[r][c].type() != intTypes.assignType(market)) {
			System.out.println(QuestGame.ANSI_RED+"This cell is not a Market! "+QuestGame.ANSI_RESET);
		} else {
			heroNexus.useMarket(hero);
			acted = true;
		} 
		return acted;
	}

	/*
	 * move_Useraccess - allows the board to resolve the situation when
	 * a player wish to move in some direction. Return true if the move occurs,
	 * false if the player stays where he/she is. 
	 */
	public boolean move_Useraccess(TeamHero heros, Hero hero, String x) {
		boolean moved = false;
		int row = hero.getRow();
		int col = hero.getCol();
		if (x.equals("w")) {
			if (moveTo(hero, heros, row-1, col)) {
				exit(hero); //checks if the current location is statboosting
				hero.resetRow(hero.getRow() - 1);
				moved = true;
			}
		} else if (x.equals("s")) {
			if (moveTo(hero, heros, row+1, col)) {
				exit(hero);
				hero.resetRow(hero.getRow() + 1);
				moved = true;
			}	
		} else if (x.equals("a")) {
			if (moveTo(hero, heros, row, col-1)) {
				exit(hero);
				hero.resetCol(hero.getCol()-1);
				moved = true;
			}
		} else if (x.equals("d")) {
			if (moveTo(hero, heros, row, col+1)) {
				exit(hero);
				hero.resetCol(hero.getCol() + 1);
				moved = true;
			}
		} else {
			System.out.println(QuestGame.ANSI_RED+"╮(╯_╰)╭   Wrong inputs format   ╮(╯_╰)╭"+QuestGame.ANSI_RESET);
		} 
		if (moved == false) {
			System.out.println(QuestGame.ANSI_RED+"Can't move to that cell because it is unaccessible or out of the board!"+QuestGame.ANSI_RESET);
		} else {
			System.out.print(QuestGame.ANSI_RED+"Hero " + hero.getName() + " just moved to (" + hero.getRow() + ", " + hero.getCol() + "). "+QuestGame.ANSI_RESET);
			
		}
		return moved;
	}
	/*
	* exit - removes the Cell's effect is that cell is one of the stat boosting cell. 
	*/
	private void exit(Hero hero) {
		int row = hero.getRow();
		int col = hero.getCol();
		int type = cells[row][col].type();
		if ((type > 3) && (type < 7)) {
			boostCells[type-4].exit(hero);
		}
	} 

	/*
	 * moveTo - returns false if moving out of bound, onto an unaccessible cell, 
	 * or onto an pre-occupied cell(by another hero). 
	 */
	private boolean moveTo(Hero hero, TeamHero heros, int r, int c) {
		boolean result = true;
		if ((r<0||c<0||(r>rowBound-1) ||(c>colBound-1) || (!cells[r][c].canMoveTo()) || (heros.Occupied(r, c)))) {
			result = false;
		} else if (boostCell(r, c)) {
			boostCells[cells[r][c].type() - 4].enter(hero);
		}
		
		return result;
	}

	/*
	* goBack - allows the hero to go back to the Nexus. A Hero can always go back to the 
	* Nexus, for displaying purpose if the Nexus cell of the current column is taken, 
	* the Hero will take the adjacent cell. 
	*/
	public void goBack(Hero toGoBack, TeamHero heros) {
		int row = toGoBack.getRow();
		int col = toGoBack.getCol();
		int neigh = col - 1;
		if (col % 3== 0) {
			neigh = col + 1;
		} 
		if (boostCell(row, col)) {
			//In case exit out of a Boost Cell 
			exit(toGoBack);
		}
		if (heros.Occupied(rowBound - 1, col)) {
			toGoBack.resetCol(neigh);
		} 
		toGoBack.resetRow(rowBound-1);
		System.out.println("Hero <" + QuestGame.ANSI_RED + toGoBack.toString() +QuestGame.ANSI_RESET+ "> went back to the Nexus.");
		
	}
	
	public void teleport(TeamHero heros, Hero hero, MonsOnBoard m) {
		boolean occupied = true;
		exit(hero);
		int row = -1;
		int col = -1;
		exit(hero); //In case, moving out of a Boosting cell. 
		do {
			System.out.print("Enter a col number. ");
			col = QuestGame.getInteger(0, 7);
			if (((col+1)%3==0) || (col == hero.getCol()) || (col == (hero.getCol()-1)) || (col == (hero.getCol() + 1))) {
				System.out.println(QuestGame.ANSI_RED +"You can't teleport to an unaccessible lane or to your own lane!" + QuestGame.ANSI_RESET);
			} else {
				int lane_index = col/3;
				System.out.print(QuestGame.ANSI_RED +"Currently the front monster is at row " + m.getLaneMonster(lane_index).front()+ ". " + QuestGame.ANSI_RESET);
				System.out.print("Choose a row index (Keep in mind that You can't Teleport to behind a Monster on Board!) ");
				row = QuestGame.getInteger(m.getLaneMonster(lane_index).front(), 7);
				occupied = heros.Occupied(row, col);
			}
		} while (occupied);
		if ((row != -1) && (col != -1)) {
			hero.resetRow(row);
			hero.resetCol(col);
			if (boostCell(row, col)) {
				//In case moving onto a boosting cell. 
				int type = cells[row][col].type();
				boostCells[type-4].enter(hero);
			}
		}
	}

	public boolean boostCell(int row, int col) {
		boolean boosting = false;
		if (3 < cells[row][col].type() && cells[row][col].type() < 7) {
			boosting = true;
		}
		return boosting;
	}
	

	/*
	 * printMap - need to rewrite
	 */
	public void printMap(TeamHero heros, MonsOnBoard mons) {
		String line = "";
		for (int i = 0; i < rowBound; i++) {
			line +="----";
		}
		System.out.print("\n");
		System.out.println(line);
		for (int r = 0; r < rowBound; r++) {
			System.out.print("|");
			for (int c = 0; c < colBound; c++) {
				if ((heros.Occupied(r, c) && (mons.Occupied(r,c)))) {
					System.out.print(QuestGame.ANSI_RED + "H" + QuestGame.ANSI_RESET + QuestGame.ANSI_BLUE + "/M" + QuestGame.ANSI_RESET);
				} else if ((heros.Occupied(r, c))) {
					System.out.print(QuestGame.ANSI_RED + "# H" + QuestGame.ANSI_RESET);
				} else if (mons.Occupied(r, c)) {
					System.out.print(QuestGame.ANSI_BLUE + "/M/" + QuestGame.ANSI_RESET);
				} else {
					System.out.print(cells[r][c].toString());
				}
				System.out.print("|");
			}
			System.out.print("\n");
			System.out.println(line);
		}
		
	}
}