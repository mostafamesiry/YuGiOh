package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.MonsterCard;

public class HeavyStorm extends HarpieFeatherDuster{

	public HeavyStorm(String name, String description) {
		super(name, description);
	}
	
	@Override
	public void action(MonsterCard monster){
		super.action(monster);
		int s=this.getBoard().getActivePlayer().getField().getSpellArea().size();
		for (int i=0;i<s;i++)
		{
			SpellCard tmp=this.getBoard().getActivePlayer().getField().getSpellArea().get(i);
			tmp.setLocation(Location.GRAVEYARD);
			this.getBoard().getActivePlayer().getField().getGraveyard().add(tmp);
		}
		this.getBoard().getActivePlayer().getField().getSpellArea().clear();
	}
	
}
