package eg.edu.guc.yugioh.cards.spells;
import eg.edu.guc.yugioh.cards.*;
import eg.edu.guc.yugioh.cards.MonsterCard;

public class MonsterReborn extends SpellCard{

	public MonsterReborn(String name, String description) {
		super(name, description);
	}

	@Override
	public void action(MonsterCard monster){	
		int na = this.getBoard().getActivePlayer().getField().getGraveyard().size();
		int no = this.getBoard().getOpponentPlayer().getField().getGraveyard().size();
		MonsterCard max = new MonsterCard("","",0,0,0);
		int gy=0;
		for(int i=0;i<na;i++){
			if(this.getBoard().getActivePlayer().getField().getGraveyard().get(i).getClass().equals(MonsterCard.class)){
				MonsterCard m = new MonsterCard(null, null, 0, 0, 0);
				m = (MonsterCard) this.getBoard().getActivePlayer().getField().getGraveyard().get(i);
				if(m.getAttackPoints()>max.getAttackPoints())
				{
					max = m;
					gy = 0;
				}	
			}		

		}
		for(int i=0;i<no;i++){
			if(this.getBoard().getOpponentPlayer().getField().getGraveyard().get(i).getClass().equals(MonsterCard.class)){
				MonsterCard m = new MonsterCard(null, null, 0, 0, 0);
				m = (MonsterCard) this.getBoard().getOpponentPlayer().getField().getGraveyard().get(i);
				if(m.getAttackPoints()>max.getAttackPoints())
				{
					max = m;
					gy = 1;
				}
			}	

		}
		if(max.getName().equals(""))
		{
			return;
		}
		max.setLocation(Location.FIELD);
		this.getBoard().getActivePlayer().getField().getMonstersArea().add(max);
		if(gy==0)
			this.getBoard().getActivePlayer().getField().getGraveyard().remove(max);
		else
			this.getBoard().getOpponentPlayer().getField().getGraveyard().remove(max);
	}

}
