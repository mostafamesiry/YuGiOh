package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.cards.MonsterCard;

public class MagePower extends SpellCard{

	public MagePower(String name, String description) {
		super(name, description);
	}
	
	public void action(MonsterCard monster){
		int n = this.getBoard().getActivePlayer().getField().getSpellArea().size();
		monster.setAttackPoints(monster.getAttackPoints()+(n*500));
		monster.setDefensePoints(monster.getDefensePoints()+(n*500));
	}

}
