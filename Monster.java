import java.util.Random;
/*
* Monster - a subclass that extends Fighter. Monsters in Quest has 
* name,level, damage, defense, and dodge chance. 
*/
public class Monster extends Fighter implements Comparable<Monster>{
	private int damage;
	/*
	* Constructor - sets the name, level and hp according to inputs. 
	* initiaze dodge and defense to be 0.  
	*/
	public Monster(String name, int l, int dam, int def, int dod) {
		super(name, 1, 100);
		damage = dam;
		//Modify the protected fields of the superclass
		resetDef(def);
		dodge = dod;
	}
	/*
	* No-arg constructor - call the superclass's no-arg constructor and
	* sets all fields to either 0 or null.  
	*/
	public Monster() {
		super();
		damage = 0;
	}
	/*
	* freezeDamage - mutator method, causes the damage skill to deteriorate 
	* by 10%.
	*/
	public void freezeDamage() {
		damage = (int) (damage * 0.9);
	}
	/*
	* deDodge - mutator method, causes the dodge skill to deteriorate 
	* by 10%.
	*/
	public void deDodge() {
		dodge = (int) (dodge * 0.9);
	}
	public int getDamage() {
		return damage;
	}
	/*
	* attack - allows the monster to attack a hero, 
	* Returns true if the hero's dead, otherwise hero regains hp and mana and return false. 
	*/
	public boolean attack(Hero hero) {
		Random ran = new Random();
		boolean heroFailDodge = (hero.getDodge() < ran.nextInt(100));
		boolean heroDead = false;
		if (alive() && hero.alive() && heroFailDodge) {
			int harm = damage - hero.getDefense();
			//in case when hero's defense is larger than monster's damage
			if (harm < 0) {
				harm = 0;
			}
			hero.resetHp(hero.getHp() - harm);
			System.out.println("#!∷∶∵ Monster <" + toString() + "> [Lane" + getLane() +"] dealt " + harm + " damage to Hero <" + hero.toString() + ">∷∶∵!#");
		} else if (!heroFailDodge) {
			System.out.println("Hero <" + hero.getName() + "> dodged attack from " + " Monster <" + getName() +">");
		}
		if (!hero.alive()) {
			heroDead = true;
			System.out.println(QuestGame.ANSI_RED+"∑(っ°Д°;)っ ~ Hero " + hero.toString() + " FAINTED _(´ཀ`」 ∠)_"+QuestGame.ANSI_RESET);
		} 
		return heroDead;
	}

	/*
	* getStat() - override the getStat() method in the Fighter class. 
	* Returns a String representation of Monster's statistics during a fight.  
	*/
	public String getStat() {
		String str = super.getStat();
		str = str + " Danamge: " + damage + " Dodge: " + dodge + " Defense:  " + getDefense();
		return str;
	}
	public int compareTo(Monster other) {
		return (other.getRow() - this.getRow());
	}
}