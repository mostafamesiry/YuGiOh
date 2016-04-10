package eg.edu.guc.yugioh.cards.spells;
import java.util.ArrayList;

import eg.edu.guc.yugioh.cards.MonsterCard;

public class GracefulDice extends SpellCard{

	public GracefulDice(String name, String description) {
		super(name, description);
	}
	@Override
	public void action(MonsterCard monster){
		int inc = (int) (Math.random()* 10);inc++;inc*=100;
		int s =this.getBoard().getActivePlayer().getField().getMonstersArea().size();
		for (int i = 0; i < s; i++)
		{
			int tmpA=this.getBoard().getActivePlayer().getField().getMonstersArea().get(i).getAttackPoints();
			int tmpD=this.getBoard().getActivePlayer().getField().getMonstersArea().get(i).getDefensePoints();
			this.getBoard().getActivePlayer().getField().getMonstersArea().get(i).setAttackPoints(inc+tmpA);
			this.getBoard().getActivePlayer().getField().getMonstersArea().get(i).setDefensePoints(inc+tmpD);
		}
	}

}
