/*
* Spell - an abstract subclass that extends Tool for tools in the Quest market 
* which are specified to be Spells (has damage effect & backdrops monster's 
* skills).
*/
public abstract class Spell extends Tool {
	protected int mana_cost; 
	/*
	* Constructor - first calls the superclass' constructor then sets the field 
	* mana_cost to the corresponding input. 
	*/
	public Spell(String name, int cost, int min_level, int effect, int mana) {
		super(name, cost, min_level, effect);
		mana_cost = mana;
	}
	/*
	* No-arg constructor - calls the superclass's no arg constructor then
	* sets the mana_cost to 0.
	*/
	public Spell(){
		super();
		mana_cost = 0;
	}
	
	/*
	* toString(boolean x) - input differentiate it from the inherited toString().
	* provides the additional info about mana in a String. 
	*/
	public String toString(boolean x) {
		String str = super.toString(x);
		str = str + " mana " + mana_cost;
		return str;
	}
	/*
	* useTool - return a string description of who causes how much
	* damage to whom. 
	* damage = spell_base_damage * (hero's dex/10000) + spell_base_damage. 
	*/
	public String useTool(Hero hero, Monster m) {
		int damage = 0;
		String str = ""; 
		//If the hero has enough man, hero use mana and cause damage
		if (hero.getMana() > mana_cost) {
			hero.resetMana(hero.getMana() - mana_cost);
			damage = (int) (effect + effect * (hero.getDex()/10000)) - m.getDefense();
			if (damage < 0) {
				damage = 0;
			}
			m.resetHp(m.getHp() - damage);
			str = str + "Hero <" + hero.toString() + "> causes " + damage + " damage to Monster <" + m.toString() + "> with " + toString() ;
		} else {
			str = str + QuestGame.ANSI_RED + "X﹏X Your MANA is LOWER than this Spell's benchmark! " + QuestGame.ANSI_RESET;
		}
		return str;
	}
}