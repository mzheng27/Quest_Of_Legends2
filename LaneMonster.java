import java.util.ArrayList;
import java.util.Collections;
/*
* LaneMonster - represents monsters in a lane in an ArrayList. Describes all behvaiors of the 
* lane such as the front of the Lane, one round of execution in this lane etc. 
*/
public class LaneMonster {
	private ArrayList<Monster> mInLane;
	int first_col; //number of the first col of this lane 

	public LaneMonster(int lane, Monster m) {
		mInLane = new ArrayList<Monster> ();
		first_col = (lane-1) * 3; 
		monsterSpawn(m);
	}
	/*
	* monsterSpawn - sort the ArrayList according to Monster's row number. 
	* Adds a new Monster to the ArrayList, in a different col 
	* as the foremost monster in lane. 
	*/
	public void monsterSpawn(Monster selected) {
		selected.resetRow(0);
		selected.resetCol(first_col);
		if (mInLane.size() > 0) {
			Collections.sort(mInLane);
			if (mInLane.get(0).getCol() == first_col) {
				selected.resetCol(first_col + 1);
			}
		}
		mInLane.add(selected);
	}
	/*
	* monsterKilled - remove a monster with the specific row and col index. 
	*/
	public void monsterKilled(int r, int c) {
		for (int i = 0; i < mInLane.size(); i++) {
			Monster mons = mInLane.get(i);
			if ((mons.getRow()==r) && (mons.getCol() == c)) {
				mInLane.remove(mons);
			}
		}
	}
	/*
	* front - first sort the ArrayList according to the Monster row number (largest first), 
	* then return the row number of the first Monster in the list which should be the front. 
	*/
	public int front() {
		int front = 0;
		if (mInLane.size() > 0) {
			Collections.sort(mInLane);
			front = mInLane.get(0).getRow();
		}
		return front;
	}

	/*
	* scan - returns an ArrayList<Monster> of monsters in the attack 
	* range of this hero. 
	*/
	public ArrayList<Monster> scan(Hero hero) {
		ArrayList<Monster> inRange = new ArrayList<Monster> ();
		int hRow = hero.getRow();
		int hCol = hero.getCol();
		for (int i = 0; i<mInLane.size(); i++) {
			Monster m = mInLane.get(i);
			if (((m.getLane() == hero.getLane())&&(m.getRow() == hRow)) || ((m.getRow() == (hRow-1)) && (m.getCol() == hCol))) {
				//if the Monster is of the same row or in front of the hero
				inRange.add(m);
			}
		}
		return inRange;
	}

	/*
	* scanMove - return true if the hero is not moving pass another monsters. 
	* and false if otherwise. 
	*/
	public boolean scanMove(Hero hero) {
		boolean canMove = true;
		for (int i = 0; i<mInLane.size(); i++) {
			Monster monster = mInLane.get(i);
			if ((monster.getLane() == hero.getLane()) && (monster.getRow() == hero.getRow())) {
				canMove = false;
				break;
			}
		}
		return canMove;
	}
	/*
	* oneRound - attack a hero or move forward. Returns true if one of the 
	* Monsters in this Lane reaches the Hero's Nexus. Attack the first Hero
	* in its scanned list respawn the hero if he faints. 
	*/
	public boolean oneRound(TeamHero heros, QuestMap board) {
		boolean mWin = false;
		for (int i = 0; i<mInLane.size(); i++) {
			Monster m = mInLane.get(i);
			if (!board.monsCheckWin(m)) {
				ArrayList<Hero> heros_inRange = heros.scan(m);
				if (heros_inRange.size() > 0) {
					Hero toAttack = heros_inRange.get(0);
					if (m.attack(toAttack)) {
						//If the Hero faints
						toAttack.respawns();
						board.goBack(toAttack, heros);
					} 
				} else {
					//If no Hero in attack range. 
					m.resetRow(m.getRow() + 1);
					System.out.println(QuestGame.ANSI_RED + "∑(っ°Д°;)っ ~ Watch out! Monster " + m.getName() + " in [Lane " + m.getLane() + "] moved forward by 1 cell" + QuestGame.ANSI_RESET);
				}
			}
			if (board.monsCheckWin(m)) {
				mWin = true;
			}
		}
		return mWin;
	}

	/*
	* occupied - attack a hero or move forward. Returns true if one of the 
	* Monsters in this Lane reaches the Hero's Nexus. Attack the first Hero
	* in its scanned list. 
	*/
	public boolean occupied(int row, int col) {
		boolean occupied = false;
		for (int i=0; i< mInLane.size(); i++) {
			Monster mons = mInLane.get(i);
			if ((mons.getRow() == row) && (mons.getCol() == col)) {
				occupied = true; 
				break;
			}
		}
		return occupied;
	}

	public String MonstoString() {
		String str = "Monsters on Lane " + ((first_col/3) + 1) +": ";
		for (int i=0; i< mInLane.size(); i++) {
			Monster mons = mInLane.get(i);
			str = str + "<" + mons.getName() + "Position: (" + mons.getRow() + ", " + mons.getCol() +")>  Level: " + mons.getLevel(); 
		}
		return str;
	}

	
}