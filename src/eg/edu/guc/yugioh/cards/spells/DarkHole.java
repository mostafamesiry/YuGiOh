package eg.edu.guc.yugioh.cards.spells;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.MonsterCard;

public class DarkHole extends Raigeki{

	public DarkHole(String name, String description) {
		super(name, description);
	}
	@Override
	public void action(MonsterCard card){		
		super.action(card);
		int s=this.getBoard().getActivePlayer().getField().getMonstersArea().size();
		for (int i=0;i<s;i++)
		{
			MonsterCard tmp=this.getBoard().getActivePlayer().getField().getMonstersArea().get(i);
			tmp.setLocation(Location.GRAVEYARD);
			this.getBoard().getActivePlayer().getField().getGraveyard().add(tmp);
		}
		//removes Active player monsters from monsters area
		this.getBoard().getActivePlayer().getField().getMonstersArea().clear();
		
	}
	

}
