package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.MonsterCard;

public class Raigeki extends SpellCard{

	public Raigeki(String name, String description) {
		super(name, description);
	}

	@Override
	public void action(MonsterCard monster){
		int s=this.getBoard().getOpponentPlayer().getField().getMonstersArea().size();
		for (int i=0;i<s;i++)
		{
			MonsterCard tmp=this.getBoard().getOpponentPlayer().getField().getMonstersArea().get(i);
			tmp.setLocation(Location.GRAVEYARD);
			this.getBoard().getOpponentPlayer().getField().getGraveyard().add(tmp);
		}
		this.getBoard().getOpponentPlayer().getField().getMonstersArea().clear();
	}
}
