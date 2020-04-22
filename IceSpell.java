/*
* IceSpell - a subclass of Spell. Override the useTool method of the Spell class. 
*/
public class IceSpell extends Spell {
	/*
	* Constructor - calls the superclass Spell's constructor and pass in the inputs. 
	*/
	public IceSpell(String name, int cost, int min_level, int effect, int mana) {
		super(name, cost, min_level, effect, mana);
	}
	/*
	* No-arg constructor - calls the superclass's no arg constructor 
	*/
	public IceSpell() {
		super();
	}
	/*
	* Override useTool in Spell - call Spell's useTool method which examines if
	* the hero has enough mana to successfully cast this spell and cast it. Then 
	* reduces a damage range of the monster by 10%. 
	*/
	public String useTool(Hero hero, Monster m) {
		String str = super.useTool(hero, m);
		if (hero.getMana() > mana_cost) {
			str = str + " and reduces Monster <" + m.toString() + ">'s damage range by 10%";
			m.freezeDamage();
		}
		
		return str;
	}

}