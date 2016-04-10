package eg.edu.guc.yugioh.cards.spells;
import eg.edu.guc.yugioh.cards.*;

public class CardDestruction extends SpellCard{
	public CardDestruction(String n, String d)
	{
		super(n, d);
	}
	@Override
	public void action(MonsterCard card)
	{
		int AS=this.getBoard().getActivePlayer().getField().getHand().size();
		int OS=this.getBoard().getOpponentPlayer().getField().getHand().size();
		
		
		for (int i=0;i<AS;i++)
		{
			Card tmp=this.getBoard().getActivePlayer().getField().getHand().get(i);
			tmp.setLocation(Location.GRAVEYARD);
			this.getBoard().getActivePlayer().getField().getGraveyard().add(tmp);
		}
		this.getBoard().getActivePlayer().getField().getHand().clear();
		
		
		for (int i=0;i<OS;i++)
		{
			Card tmp=this.getBoard().getOpponentPlayer().getField().getHand().get(i);
			tmp.setLocation(Location.GRAVEYARD);
			this.getBoard().getOpponentPlayer().getField().getGraveyard().add(tmp);
		}
		
		this.getBoard().getOpponentPlayer().getField().getHand().clear();
		this.getBoard().getActivePlayer().getField().addNCardsToHand(AS);
		this.getBoard().getOpponentPlayer().getField().addNCardsToHand(OS);
	}
}
