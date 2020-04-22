/*
* LightSpell - a subclass of Spell. Override the useTool method of the Spell class. 
*/
public class LightSpell extends Spell {
	/*
	* Constructor - calls the superclass Spell's constructor and pass in the inputs. 
	*/
	public LightSpell (String name, int cost, int min_level, int effect, int mana) {
		super(name, cost, min_level, effect, mana);
	}
	/*
	* No-arg constructor - calls the superclass's no arg constructor 
	*/
	public LightSpell() {
		super();
	}
	/*
	* Override useTool in Spell - call Spell's useTool method which examines if
	* the hero has enough mana to successfully cast this spell and cast it. Then 
	* reduces a dodge range of the monster by 10%. 
	*/
	public String useTool(Hero hero, Monster m) {
		String str = super.useTool(hero, m);
		if (hero.getMana() > mana_cost) {
			str = str + " and reduces Monster <" + m.toString() + ">'s dodge range by 10%";
			m.deDodge();
		}
		
		return str;
	}
}