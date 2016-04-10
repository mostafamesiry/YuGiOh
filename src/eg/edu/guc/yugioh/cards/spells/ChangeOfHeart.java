package eg.edu.guc.yugioh.cards.spells;
import eg.edu.guc.yugioh.cards.*;

public class ChangeOfHeart extends SpellCard{

	public ChangeOfHeart(String name, String description) {
		super(name, description);
	}
	@Override
	public void action(MonsterCard monster){
		int i = this.getBoard().getOpponentPlayer().getField().getMonstersArea().indexOf(monster);
		this.getBoard().getActivePlayer().getField().getMonstersArea().add(this.getBoard().getOpponentPlayer().getField().getMonstersArea().get(i));
		this.getBoard().getOpponentPlayer().getField().getMonstersArea().remove(monster);
	}
}
