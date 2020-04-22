
/*
 * MonsterList - a class that represents all the monsters in Quest in a 2-D array 
 * such that the first index is a monster's level. 
 */
public class MonsterList {
	private static Monster[][] monsters;
	/*
	 * constructor - create a 2-d array to represents all the mosters in which 
	 * the first index represent the level-1. 
	 */
	public MonsterList() {
		monsters = new Monster[10][3];
		monsters[0][0] = new Monster("Natsunomeryu", 1, 200, 200, 10);
		monsters[0][1] = new Monster("Aim-Haborym", 1, 240, 250, 35);
		monsters[0][2] = new Monster("BigBad-Wolf", 1, 150, 250, 15);
		monsters[1][0] = new Monster("Chrysophylax", 2, 200, 400, 20);
		monsters[1][1] = new Monster("WickedWitch", 2, 250, 350, 25);
		monsters[1][2] = new Monster("Andrealphus", 2, 300, 350, 40);
		monsters[2][0] = new Monster("Brandobaris", 3, 350, 450, 30);
		monsters[2][1] = new Monster("Andromalius", 3, 550, 450, 25);
		monsters[2][2] = new Monster("Desghidorrah", 3, 300, 400, 35);
		monsters[3][0] = new Monster("BunsenBurner", 4, 400, 500, 45);
		monsters[3][1] = new Monster("Aasterinian", 4, 400, 500, 45);
		monsters[3][2] = new Monster("Chiang-shih", 4, 700, 600, 40);
		monsters[4][0] = new Monster("FallenAngel", 5, 800, 700, 50);
		monsters[4][1] = new Monster("Kas-Ethelinh", 5, 600, 500, 60);
		monsters[4][2] = new Monster("St-Shargaas", 5, 550, 650, 55);
		monsters[5][0] = new Monster("Chronepsish", 6, 650, 750, 60);
		monsters[5][1] = new Monster("Phaarthurnax", 6, 600, 700, 60);
		monsters[5][2] = new Monster("Ereshkigall", 6, 950, 450, 35);
		monsters[6][0] = new Monster("Melchiresas", 7, 350, 150, 75);
		monsters[6][1] = new Monster("TheScaleless", 7, 700, 600, 75);
		monsters[6][2] = new Monster("Cyrrollalee", 7, 700, 800, 75);
		monsters[7][0] = new Monster("Kiaransalee", 8, 850, 950, 85);
		monsters[7][1] = new Monster("TheWeatherbe", 8, 800, 900, 80);
		monsters[7][2] = new Monster("Jormunngand", 8, 600, 900, 20);
		monsters[8][0] = new Monster("Rakkshasass", 9, 550, 600, 35);
		monsters[8][1] = new Monster("D-Maleficent", 9, 900, 950, 85);
		monsters[8][2] = new Monster("St-Yeenoghu", 9, 950, 850, 90);
		monsters[9][0] = new Monster("Merrshaullk", 10, 1000, 900, 55);
		monsters[9][1] = new Monster("Alexstraszan", 10, 1000, 900, 55);
		monsters[9][2] = new Monster("Taltecuhtli", 10, 300, 200, 50);	
	}
	/*
	 * create(Monster x) - takes in a Monster object and create a new instance 
	 * of Monster object that has all the same statistics. 
	 */
	public Monster create(Monster x) {
		Monster m = new Monster(x.getName(), x.getLevel(), x.getDamage(), x.getDefense(), x.getDodge());
		return m;
	}

	
	/*
	* getLevelMonster - This is a helper method to create the components for 
	* the hero team of a specific level. Because of our monster data's max level
	* is 10, we assume that the input is a positive integer less than or equal to 10.
	*/
	public Monster[] getLevelMonster(int level) {
		return monsters[level-1];
	}
}