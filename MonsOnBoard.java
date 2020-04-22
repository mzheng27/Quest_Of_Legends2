import java.util.ArrayList;
import java.util.Random;
/*
* MonsOnBoard - an ArrayList of LaneMonster objects, dynamic in a way such 
* that lanes can be added. 
*/
public class MonsOnBoard {
	//all Monsters available in Quest 
	private static final MonsterList allMonsters = new MonsterList();
	private ArrayList<LaneMonster> monsters;
	private int numLanes;
	/*
	* No-arg Constructor - default numlanes is 3. Call the helper method 
	* getMonster to initialize the 3 monsters of level 1 on each lane. 
	*/
	public MonsOnBoard() {
		numLanes = 3;
		monsters = new ArrayList<LaneMonster>(); 
		Monster[] selected = getMonster(1);
		for (int i = 0; i<3; i++) {
			monsters.add(new LaneMonster(i+1, selected[i]));
		}
	}

	/*
	* Constructor - changes the default numLanes from 3 to other positive
	* integers. Uses the helper method addLane() and getMonster() set 
	* up the lanes. 
	*/
	public MonsOnBoard(int number) {
		numLanes = 0;
		if (number < 1) {
			throw new IllegalArgumentException("Number of lanes must be positive.");
		} else {
			monsters = new ArrayList<LaneMonster>();
			for (int i = 0; i<number; i++) {
				addLane();
			}
		}
	} 
	/*
	* getMonster - helper method that returns a list of Monsters randomly selected 
	* of a specific level. The length of this list depends on numLanes. 
	*/
	private Monster[] getMonster(int level) {
		Monster[] mSelected = new Monster[numLanes];
		Random random = new Random();  
		Monster[] m = allMonsters.getLevelMonster(level);
		for (int i = 0; i<numLanes; i++) {
			int ran = random.nextInt(3);
			mSelected[i] = allMonsters.create(m[ran]);
		}		
		return mSelected;
	}
	
	public String toString() {
		String str = "";
		for (int i = 0; i<numLanes; i++) {
			str = str + monsters.get(i).MonstoString() + "\n";
		}
		return str;
	}
	/*
	* addLane - increments numLane, add a Monster of level 1 randomly picked 
	*/
	private void addLane() {
		numLanes++; 
		Random random = new Random(); 
		int ran = random.nextInt(3);
		Monster toAdd = allMonsters.create(allMonsters.getLevelMonster(1)[ran]);
		monsters.add(new LaneMonster(numLanes, toAdd));
	}

	/*
	* getLane(int index) - return the LaneMonster Object that the specified lane_index. 
	*/
	public LaneMonster getLaneMonster(int index) {
		return monsters.get(index);
	}

	/*
	* spawn - spawn the monsters of a specific level at Nexus for each lane. call 
	* monsterSpawn in the LaneMonster class passing it the Monster to be spawned. 
	*/
	public void spawn(int level) {
		Monster[] toAdd = getMonster(level);
		for (int i = 0; i<numLanes; i++) {
			monsters.get(i).monsterSpawn(toAdd[i]);
		}
	}

	/*
	* executeRound - one round of action for all Monsters. call 
	* oneRound in LaneMonster of each lane. Returns true if during the
	* round Monster wins. 
	*/
	public boolean executeRound(TeamHero heros, QuestMap board) {
		boolean mWin = false;
		for (int i = 0; i<numLanes; i++) {
			if (monsters.get(i).oneRound(heros, board)) {
				//if one of the lane returns true then mWin is true
				mWin = true;
			}
		}
		return mWin;
	}

	/*
	* Occupied - returns true if this position is occupied by a Monster object 
	*/
	public boolean Occupied(int row, int col) {
		boolean occupied = false; 
		if ((col + 1)%3 != 0) {
			int lane_index = col/3;
			occupied = monsters.get(lane_index).occupied(row, col);
		}
		return occupied; 
	}
}