
package eg.edu.guc.yugioh.board.player;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import eg.edu.guc.yugioh.board.Board;
import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.Mode;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.PotOfGreed;
import eg.edu.guc.yugioh.cards.spells.Raigeki;
import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.exceptions.*;

public class Player implements Duelist
{

	private String name;
	private int lifePoints;
	private Field field;
	private boolean summon;


	public Player(String name) throws Exception
	{
		this.name=name;
		this.lifePoints=8000;
		field=new Field();
		summon = false;
	}


	public int getLifePoints() {
		return lifePoints;
	}


	public void setLifePoints(int lifePoints) {
		this.lifePoints = lifePoints;
	}


	public String getName() {
		return name;
	}


	public Field getField() {
		return field;
	}

	public boolean summonMonster(MonsterCard monster)
	{
		if(MonsterCard.getBoard().getActivePlayer()==this&&Card.getBoard().getWinner()==null
				&&this.getField().getHand().contains(monster))
		{


			if(summon)
			{
				throw new MultipleMonsterAdditionException();
			}

			if(this.getField().getMonstersArea().size()==5)
			{
				throw new NoMonsterSpaceException();
			}

			if(this.getField().getPhase()==Phase.BATTLE)
			{
				throw new WrongPhaseException();
			}
			this.getField().addMonsterToField(monster, Mode.ATTACK, false);
			if(this.getField().getMonstersArea().contains(monster))
			{
				summon = true;
				return true;
			}
			else
				return false;
		}
		else return false;
	}

	public boolean summonMonster(MonsterCard monster, ArrayList<MonsterCard> sacrifices)
	{
		if(MonsterCard.getBoard().getActivePlayer()==this&&Card.getBoard().getWinner()==null)
		{
			if(summon)
			{
				throw new MultipleMonsterAdditionException();
			}
			if(this.getField().getPhase()==Phase.BATTLE)
			{
				throw new WrongPhaseException();
			}
			if(this.getField().getMonstersArea().size()==5 && (monster.getLevel()>=4) )
			{
				throw new NoMonsterSpaceException();
			}

			this.getField().addMonsterToField(monster, Mode.ATTACK, sacrifices);
			if(this.getField().getMonstersArea().contains(monster))
			{
				summon = true;
				return true;
			}
			else
				return false;
		}
		else return false;
	}

	public boolean setMonster(MonsterCard monster)
	{
		if(MonsterCard.getBoard().getActivePlayer()==this
				&&Card.getBoard().getWinner()==null
				&&monster.getLevel()<=4)
		{

			if(summon)
			{
				throw new MultipleMonsterAdditionException();
			}

			if(this.getField().getMonstersArea().size()==5)
			{
				throw new NoMonsterSpaceException();
			}

			if(this.getField().getPhase()==Phase.BATTLE)
			{
				throw new WrongPhaseException();
			}
			this.getField().addMonsterToField(monster, Mode.DEFENSE, true);
			summon = true;
			return true;
		}
		else return false;
	}
	public boolean setMonster(MonsterCard monster, ArrayList<MonsterCard> sacrifices)
	{
		if(MonsterCard.getBoard().getActivePlayer()==this&&Card.getBoard().getWinner()==null)
		{

			if(summon)
			{
				throw new MultipleMonsterAdditionException();
			}
			if(this.getField().getMonstersArea().size()==5 && (monster.getLevel()<=4) )
			{
				throw new NoMonsterSpaceException();
			}

			if(this.getField().getPhase()==Phase.BATTLE)
			{
				throw new WrongPhaseException();
			}
			int lvl = monster.getLevel();
			int size = sacrifices.size();
			if(((lvl>6)&&(size!=2))||((lvl>=5)&&(lvl<=6)&&(size!=1))||(lvl<=4))
				return false;
			this.getField().addMonsterToField(monster, Mode.DEFENSE, sacrifices);
			monster.setHidden(true);
			summon = true;
			return true;
		}
		else return false;
	}
	public boolean setSpell(SpellCard spell)
	{
		if(this.getField().getHand().contains(spell)&&MonsterCard.getBoard().getActivePlayer()==this
				&&Card.getBoard().getWinner()==null)
		{
			if(this.getField().getSpellArea().size()==5)
			{
				throw new NoSpellSpaceException();
			}
			if(this.getField().getPhase()==Phase.BATTLE)
			{
				throw new WrongPhaseException();
			}

			this.getField().addSpellToField(spell, null, true);
			return true;
		}
		else return false;
	}

	public boolean activateSpell(SpellCard spell, MonsterCard monster)
	{
		if(MonsterCard.getBoard().getActivePlayer()==this
				&&Card.getBoard().getWinner()==null)
		{
			if(this.getField().getPhase()==Phase.BATTLE)
			{
				throw new WrongPhaseException();
			}


			if(this.getField().getHand().contains(spell))
			{
				if(this.getField().getSpellArea().size()==5)
				{
					throw new NoSpellSpaceException();
				}

				this.getField().addSpellToField(spell, monster, false);
			}
			else
				this.getField().activateSetSpell(spell, monster);
			return true;
		}
		else return false;
	}

	public boolean declareAttack(MonsterCard activeMonster, MonsterCard opponentMonster)
	{

		if((MonsterCard.getBoard().getWinner()==null&&MonsterCard.getBoard().getActivePlayer()==this))
		{
			if(activeMonster.attacked())
			{
				throw new MonsterMultipleAttackException();
			}

			if(activeMonster.getMode() == Mode.DEFENSE)
			{
				throw new DefenseMonsterAttackException();
			}


			if(this.getField().getPhase()!=Phase.BATTLE)
			{
				throw new WrongPhaseException();
			}
			int active=activeMonster.getAttackPoints();
			boolean at; int opp;
			if(opponentMonster.getMode()==Mode.ATTACK)
			{	
				opp=opponentMonster.getAttackPoints();
				at=true;
			}
			else {
				opp=opponentMonster.getDefensePoints();
				at=false;
			}

			int diff=active-opp;		
			if (diff>0)
			{
				Card.getBoard().getOpponentPlayer().getField().removeMonsterToGraveyard(opponentMonster);
				if(at)
				{
					int lp=Card.getBoard().getOpponentPlayer().getLifePoints();
					Card.getBoard().getOpponentPlayer().setLifePoints(lp-diff);	
				}
			}
			else if (diff<0)
			{
				int lp=Card.getBoard().getActivePlayer().getLifePoints();
				Card.getBoard().getActivePlayer().setLifePoints(lp+diff);	
				if(at)
					Card.getBoard().getActivePlayer().getField().removeMonsterToGraveyard(activeMonster);
			}
			else
			{
				if(at)
				{
					Card.getBoard().getOpponentPlayer().getField().removeMonsterToGraveyard(opponentMonster);
					Card.getBoard().getActivePlayer().getField().removeMonsterToGraveyard(activeMonster);
					
				}

			}

			activeMonster.setAtk(true);
			opponentMonster.setHidden(false);
			this.checkWinner();
			return true;
		}

		return false;
	}

	public boolean declareAttack(MonsterCard activeMonster)
	{
		if(activeMonster.getBoard().getActivePlayer()==this&&Card.getBoard().getWinner()==null
				&&Card.getBoard().getOpponentPlayer().getField().getMonstersArea().size()==0)
		{

			if(activeMonster.attacked())
			{
				throw new MonsterMultipleAttackException();
			}

			if(activeMonster.getMode() == Mode.DEFENSE)
			{
				throw new DefenseMonsterAttackException();
			}


			if(this.getField().getPhase()!=Phase.BATTLE)
			{
				throw new WrongPhaseException();
			}
			int dmg = activeMonster.getAttackPoints();
			Card.getBoard().getOpponentPlayer().setLifePoints(Card.getBoard().getOpponentPlayer().getLifePoints()-dmg);
			this.checkWinner();
			activeMonster.setAtk(true);
			return true;
		}
		return false;
	}

	public void addCardToHand()
	{
		if(MonsterCard.getBoard().getActivePlayer()==this&&Card.getBoard().getWinner()==null)
		{

			this.getField().addCardToHand();
		}
	}
	public void addNCardsToHand(int n)
	{
		if(MonsterCard.getBoard().getActivePlayer()==this&&Card.getBoard().getWinner()==null)
		{

			this.getField().addNCardsToHand(n);
		}
	}
	public void endPhase()
	{
		if(Card.getBoard().getWinner()==null)
		{
			switch(this.getField().getPhase())
			{
			case MAIN1: 
				this.getField().setPhase(Phase.BATTLE); break;
			case BATTLE: 
				this.getField().setPhase(Phase.MAIN2); break;
			case MAIN2:
				this.getField().setPhase(Phase.MAIN1);
				this.endTurn(); break;
			}
		}
	}
	public boolean endTurn()
	{
		if(Card.getBoard().getWinner()==null)
		{
			if(Card.getBoard().getActivePlayer() == this)
			{
				Card.getBoard().nextPlayer();
				this.getField().setPhase(Phase.MAIN1);
				this.summon = false;
				int size = this.getField().getMonstersArea().size();
				for (int i = 0; i < size; i++) {
					this.getField().getMonstersArea().get(i).setAtk(false);
					this.getField().getMonstersArea().get(i).setModeSwitch(false);
				}
				return true;
			}
			return false;
		}
		return false;
	}
	public boolean switchMonsterMode(MonsterCard monster)
	{
		if(monster.getBoard().getActivePlayer() == this&&Card.getBoard().getWinner()==null
				&&getField().getMonstersArea().contains(monster)
				&&!monster.isModeSwitch())
		{
			if(this.getField().getPhase()==Phase.BATTLE)
			{
				throw new WrongPhaseException();
			}

			if(monster.getMode()==Mode.ATTACK)
			{
				monster.setMode(Mode.DEFENSE);
			}
			else
			{
				monster.setMode(Mode.ATTACK);
				monster.setHidden(false);
			}
			monster.setModeSwitch(true);
			return true;
		}
		return false;
	}

	public void checkWinner()
	{
		if(Card.getBoard().getActivePlayer().getLifePoints()<0)
			Card.getBoard().setWinner(Card.getBoard().getOpponentPlayer());
		else if (Card.getBoard().getOpponentPlayer().getLifePoints()<0)
			Card.getBoard().setWinner(Card.getBoard().getActivePlayer());
	}



	public boolean isSummon() {
		return summon;
	}
public static void main(String[] loll) throws Exception {
	Duelist x =  (Duelist) new Player("x");
	Card y =  new Raigeki("j","k");
	System.out.println(((Player)x).getName());
}

}
