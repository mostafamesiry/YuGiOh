package eg.edu.guc.yugioh.board.player;
import eg.edu.guc.yugioh.board.Board;
import eg.edu.guc.yugioh.cards.*;
import eg.edu.guc.yugioh.cards.spells.*;
import eg.edu.guc.yugioh.exceptions.*;


import java.io.IOException;
import java.util.*;

public class Field {
	private Phase phase;
	private ArrayList<MonsterCard> monstersArea;
	private ArrayList<SpellCard> spellArea;
	private Deck deck;
	private ArrayList<Card> hand;
	private ArrayList<Card> graveyard;


	public Field() throws Exception
	{
		this.deck=new Deck();
		this.monstersArea=new ArrayList();
		this.spellArea=new ArrayList();
		this.hand=new ArrayList();
		this.graveyard=new ArrayList();
		phase=Phase.MAIN1;
	}


	public void addMonsterToField(MonsterCard monster, Mode m, boolean isHidden)
	{
		if(monstersArea.size()==5) //check for enough slots
			return;

		else
		{
			monster.setMode(m);
			monster.setHidden(isHidden);
			monster.setLocation(Location.FIELD);
			monstersArea.add(monster);
			hand.remove(monster);
			
		}
	}


	public void addMonsterToField(MonsterCard monster, Mode m, ArrayList<MonsterCard> sacrifices)
	{
		

			int size = sacrifices.size();
			for(int i = 0; i<size ; i++)
			{
				if(!(monstersArea.contains(sacrifices.get(i))))
					return;
			}
			int lvl = monster.getLevel();
			if(((lvl>6)&&(size!=2))||((lvl>=5)&&(lvl<=6)&&(size!=1))||(lvl<=4))
				return;
			else
			{
				if(lvl >6)
					size = 2;
				else
					size = 1;
				for(int i = 0; i<size ; i++)
				{
					MonsterCard tmp=sacrifices.get(i);
					tmp.setLocation(Location.GRAVEYARD);
					monstersArea.remove(tmp);
					graveyard.add(tmp);

				}
				addMonsterToField(monster, m, false);
		}
	}


	public void removeMonsterToGraveyard(MonsterCard monster)
	{
		if(monstersArea.contains(monster))
		{
			monster.setLocation(Location.GRAVEYARD);
			graveyard.add(monster);
			monstersArea.remove(monster);
		}

	}


	public void removeMonsterToGraveyard(ArrayList<MonsterCard> monsters)
	{
		for(int i = 0; i<monsters.size();i++)
			removeMonsterToGraveyard(monsters.get(i));

	}


	public void addSpellToField(SpellCard action,MonsterCard monster, boolean hidden)
	{
		if(spellArea.size()==5)
			return;
		else
		{
			action.setLocation(Location.FIELD);
			action.setHidden(hidden);
			spellArea.add(action);
			hand.remove(action);
			if(hidden==false)
			{
				activateSetSpell(action, monster);
			}
		}
	}


	public void activateSetSpell(SpellCard action, MonsterCard monster)
	{
		if(action.getLocation()==Location.FIELD)
		{
			action.action(monster);
			action.setLocation(Location.GRAVEYARD);
			spellArea.remove(action);
			graveyard.add(action);
		}
	}


	public void removeSpellToGraveyard(SpellCard sp)
	{
		if(this.getSpellArea().contains(sp))
		{
			graveyard.add(sp);
			sp.setLocation(Location.GRAVEYARD);	
			spellArea.remove(sp);
		}	
	}


	public void removeSpellToGraveyard(ArrayList<SpellCard> spells)
	{
		for(int i = 0; i<spells.size();i++)
			removeSpellToGraveyard(spells.get(i));
	}

	public void addCardToHand()
	{
		if(this.getDeck().getDeck().size()!=0){
		Card x=deck.drawOneCard();
		x.setLocation(Location.HAND);
		hand.add(x);
		}
		else
		 Card.getBoard().setWinner(Card.getBoard().getOpponentPlayer());

	}

	public void addNCardsToHand(int n)
	{
		ArrayList<Card> tmp=new ArrayList();
		tmp = deck.drawNCards(n);
		for(int i=0;i<tmp.size();i++)
		{
			tmp.get(i).setLocation(Location.HAND);
			hand.add(tmp.get(i));
		}
	}

	public Phase getPhase() {
		return phase;
	}

	public void setPhase(Phase phase) {
		this.phase = phase;
	}

	public ArrayList<MonsterCard> getMonstersArea() {
		return monstersArea;
	}

	public ArrayList<SpellCard> getSpellArea() {
		return spellArea;
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public ArrayList<Card> getGraveyard() {
		return graveyard;
	}

	public Deck getDeck() {
		return deck;
	}

	public static void main(String[] args) throws Exception {
		Board board = new Board();
		Player p1 = new Player("Yugi");
		Player p2 = new Player("Kaiba");
		board.startGame(p1, p2);

		MonsterCard vorseRaider = new MonsterCard("Vorse Raider",
				"A warrior beast", 4, 1900, 1200);

		vorseRaider.setLocation(Location.FIELD);
		board.getActivePlayer().getField().getMonstersArea().add(vorseRaider);

		MonsterCard geminiElf = new MonsterCard("Gemini Elf", "The twins", 4,
				1900, 900);

		board.getActivePlayer().getField().getHand().add(geminiElf);
		geminiElf.setLocation(Location.HAND);

		ArrayList<MonsterCard> l = new ArrayList<MonsterCard>();
		l.add(vorseRaider);

		board.getActivePlayer().summonMonster(geminiElf, l);
	}
}
