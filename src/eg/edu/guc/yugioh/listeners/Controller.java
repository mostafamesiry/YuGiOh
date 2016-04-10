package eg.edu.guc.yugioh.listeners;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import eg.edu.guc.yugioh.board.Board;
import eg.edu.guc.yugioh.board.player.Phase;
import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.Mode;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.ChangeOfHeart;
import eg.edu.guc.yugioh.cards.spells.MagePower;
import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.exceptions.DefenseMonsterAttackException;
import eg.edu.guc.yugioh.exceptions.MonsterMultipleAttackException;
import eg.edu.guc.yugioh.exceptions.MultipleMonsterAdditionException;
import eg.edu.guc.yugioh.exceptions.NoMonsterSpaceException;
import eg.edu.guc.yugioh.exceptions.NoSpellSpaceException;
import eg.edu.guc.yugioh.exceptions.WrongPhaseException;
import eg.edu.guc.yugioh.gui.CardButton;
import eg.edu.guc.yugioh.gui.FieldMonsterPop;
import eg.edu.guc.yugioh.gui.GUI;
import eg.edu.guc.yugioh.gui.HandMonsterPop;
import eg.edu.guc.yugioh.gui.HandSpellPop;
import eg.edu.guc.yugioh.gui.MonsterButton;
import eg.edu.guc.yugioh.gui.SpellButton;

public class Controller implements ActionListener{
	private GUI gui;
	private Board board;
	private HandMonsterPop popM=null;
	private HandSpellPop popS=null;
	private FieldMonsterPop fpopM = null;
	private JFrame newgame;
	private JButton startbutton;
	private Player player1;
	private Player player2;
	private boolean changeofheart;
	private boolean magepower;
	private boolean sacrificeset;
	private boolean sacrificesummon;
	private boolean monsterattack;
	private ArrayList<MonsterCard> sacrifices;
	private Card tmp;
	public Controller(Board b, GUI g)
	{
		gui=g;
		board=b;
		gui.getStartbutton().addActionListener(this);
	}
	public void startGame(String p1, String p2) throws Exception
	{	
		player1=new Player(p1);
		player2=new Player(p2);
		board.startGame(player1,player2);
		gui.changegui(board.getActivePlayer(),board.getOpponentPlayer(), board.getActivePlayer().getField().getPhase(),this);
		gui.validate();
		gui.getContentPane().validate();
		gui.repaint();

	}

	public void actionPerformed(ActionEvent e) {
		gui.validate();
		try{
			if(e.getSource()==startbutton)
			{
				board=new Board();
				gui.setVisible(false);
				gui=new GUI();
				gui.getStartbutton().addActionListener(this);
				newgame.setVisible(false);
			}
			else if(Card.getBoard().getWinner()!=null)
			{
				//--------------------------------------new game---------------------------------------------------
				JOptionPane.showMessageDialog(null,"Game ended, winner is "+Card.getBoard().getWinner().getName());
				newgame=new JFrame();
				newgame.setSize(250,100);
				newgame.setLayout(new GridLayout(2,1));
				newgame.setVisible(true);
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				newgame.setLocation(dim.width/2-newgame.getSize().width/2, dim.height/2-newgame.getSize().height/2);
				newgame.add(new JLabel("Start a new game"));
				startbutton=new JButton("Start new game");
				startbutton.addActionListener(this);
				newgame.add(startbutton);	
			}


			else if(changeofheart)
			{
				//--------------------------------------change of heart mode---------------------------------------------------
				if(e.getSource() instanceof MonsterButton && board.getOpponentPlayer().getField().getMonstersArea().contains(((CardButton) e.getSource()).getCard()))
				{
					board.getActivePlayer().activateSpell((SpellCard)tmp, (MonsterCard)((CardButton) e.getSource()).getCard());
					changeofheart=false;
					tmp=null;
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Choose an enemy monster");
				}
			}
			else if(magepower)
			{
				//--------------------------------------mage power mode---------------------------------------------------
				if(e.getSource() instanceof MonsterButton && board.getActivePlayer().getField().getMonstersArea().contains(((CardButton) e.getSource()).getCard()))
				{
					board.getActivePlayer().activateSpell((SpellCard)tmp, (MonsterCard)((CardButton) e.getSource()).getCard());
					magepower=false;
					tmp=null;
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Choose a monster in your field");
				}
			}
			else if(sacrificeset)
			{
				//--------------------------------------setting a monster with sacrifice---------------------------------------------------
				int lvl=((MonsterCard) tmp).getLevel();
				if(e.getSource() instanceof MonsterButton 
						&& board.getActivePlayer().getField().getMonstersArea().contains(((CardButton) e.getSource()).getCard()))
				{
					if(lvl>=7)
					{
						if(!(sacrifices.contains(((CardButton) e.getSource()).getCard()))){
							sacrifices.add((MonsterCard)((CardButton) e.getSource()).getCard());
							if(sacrifices.size()==1)
							{
								JOptionPane.showMessageDialog(null,"Choose Second Sacrifice");
							}
							if(sacrifices.size()==2){
								board.getActivePlayer().setMonster((MonsterCard)tmp,sacrifices);
								sacrificeset=false;
								tmp=null;
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null,"Card already chosen");
						}

					}

					else 
					{

						sacrifices.add((MonsterCard)((CardButton) e.getSource()).getCard());
						board.getActivePlayer().setMonster((MonsterCard)tmp,sacrifices);
						sacrificeset=false;
						tmp=null;


					}


				}
				else
				{
					JOptionPane.showMessageDialog(null,"Please choose a monster in your field");
				}

			}

			else if(sacrificesummon)
			{

				//--------------------------------------summoning a monster with sacrifice---------------------------------------------------
				int lvl=((MonsterCard) tmp).getLevel();
				if(e.getSource() instanceof MonsterButton 
						&& board.getActivePlayer().getField().getMonstersArea().contains(((CardButton) e.getSource()).getCard()))
				{
					if(lvl>=7)
					{
						if(!(sacrifices.contains(((CardButton) e.getSource()).getCard()))){
							sacrifices.add((MonsterCard)((CardButton) e.getSource()).getCard());
							if(sacrifices.size()==1)
							{
								JOptionPane.showMessageDialog(null,"Choose Second Sacrifice");
							}
							if(sacrifices.size()==2){
								board.getActivePlayer().summonMonster((MonsterCard)tmp,sacrifices);
								sacrificesummon=false;
								tmp=null;
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null,"Card already chosen");
						}

					}

					else 
					{

						sacrifices.add((MonsterCard)((CardButton) e.getSource()).getCard());
						board.getActivePlayer().summonMonster((MonsterCard)tmp,sacrifices);
						sacrificesummon=false;
						tmp=null;


					}


				}
				else
				{
					JOptionPane.showMessageDialog(null,"Please choose a monster in your field");
				}

			}
			else if(monsterattack)
			{
				if(e.getSource() instanceof MonsterButton && board.getOpponentPlayer().getField().getMonstersArea().contains(((CardButton) e.getSource()).getCard()))
				{
					board.getActivePlayer().declareAttack((MonsterCard) tmp,((MonsterButton) e.getSource()).getCard());
					monsterattack=false;
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Choose an enemy monster");
				}
			}

			else if(e.getSource()==gui.getStartbutton())
			{
				//--------------------------------------Start button---------------------------------------------------
				if(empty(gui.getTextfield1().getText()) ||empty(gui.getTextfield2().getText()) )
				{
					JOptionPane.showMessageDialog(null,"Please insert Players' Names");
				}
				else
				{
					try {
						this.startGame(gui.getTextfield1().getText(), gui.getTextfield2().getText());
						gui.validate();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null,"File Not found");
					}

				}
			}
			else if (e.getSource() instanceof CardButton){


				if(((CardButton) e.getSource()).getCard().getLocation()==Location.HAND)
				{
					//--------------------------------------Card actions in hand---------------------------------------------------
					if(!(Card.getBoard().getOpponentPlayer().getField().getHand().contains(((CardButton)e.getSource()).getCard())))
					{
						if(e.getSource() instanceof MonsterButton)

						{
							popM = new HandMonsterPop(this,(CardButton) e.getSource());

						}
						else 
						{
							popS = new HandSpellPop(this, (CardButton)e.getSource());
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Opponent Hand");
					}

				}
				else if(((CardButton) e.getSource()).getCard().getLocation()==Location.FIELD)
				{
					//--------------------------------------card actions in field---------------------------------------------------
					if(e.getSource() instanceof SpellButton)
					{
						SpellCard sp=((SpellButton) e.getSource()).getCard();
						if(!(Card.getBoard().getOpponentPlayer().getField().getSpellArea().contains(sp)))
						{

							if(!(sp instanceof ChangeOfHeart) && !(sp instanceof MagePower))
							{
								Card.getBoard().getActivePlayer().activateSpell(sp,null);
							}
							else
							{

								if(sp instanceof ChangeOfHeart)
								{
									if(board.getOpponentPlayer().getField().getMonstersArea().size()!=0)
									{
										JOptionPane.showMessageDialog(null,"Choose a monster to change his heart");
										changeofheart=true;
										tmp=sp;
									}
									else
									{
										JOptionPane.showMessageDialog(null,"Opponent has no monsters on field");
									}
								}
								else if(sp instanceof MagePower)
								{

									if(board.getActivePlayer().getField().getMonstersArea().size()!=0)
									{
										JOptionPane.showMessageDialog(null,"Choose a monster to increase power");
										magepower=true;
										tmp=sp;
									}
									else
									{
										JOptionPane.showMessageDialog(null,"You have no monsters on field");
									}
								}



							}
						}
						else
						{
							JOptionPane.showMessageDialog(null,"Cant activate enemy Spell Card");
						}
					}
					else
					{
						if(  !(Card.getBoard().getOpponentPlayer().getField().getMonstersArea().contains( ( (CardButton)e.getSource() ).getCard()))  )
						{
							fpopM=new FieldMonsterPop(this,(CardButton)e.getSource());
						}
						else
						{
							JOptionPane.showMessageDialog(null,"Cant activate enemy Monster Card");
						}

					}


				}

			}
			//--------------------------------------Center panel buttons---------------------------------------------------
			else if(e.getSource()==gui.getCenterpanel().getEndturn())
			{
				Card.getBoard().getActivePlayer().endTurn();

			}
			else if(e.getSource()==gui.getCenterpanel().getEndphase())
			{
				Card.getBoard().getActivePlayer().endPhase();

			}

			//--------------------------------------actions of popups---------------------------------------------------
			else if (popM!=null && e.getSource() == popM.getSet())
			{
				if(!board.getActivePlayer().isSummon())
				{
					int lvl = popM.getMonsterbutton().getCard().getLevel();
					if(lvl<=4)
					{

						Card.getBoard().getActivePlayer().setMonster(popM.getMonsterbutton().getCard());


					}
					else if(lvl>4)
					{
						if((lvl>=7 && board.getActivePlayer().getField().getMonstersArea().size()>=2)||(lvl<7 && lvl<=6 && board.getActivePlayer().getField().getMonstersArea().size()>=1))
						{
							if(!(board.getActivePlayer().getField().getPhase().equals(Phase.BATTLE)))
							{
								JOptionPane.showMessageDialog(null,"Choose sacrifices");
								sacrificeset=true;
								tmp=popM.getMonsterbutton().getCard();
								sacrifices=new ArrayList<MonsterCard>();
							}
							else
							{
								throw new WrongPhaseException();
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null,"Not enough sacrifices");
						}

					}
				}
				else
				{
					throw new MultipleMonsterAdditionException();
				}

				popM.setVisible(false);
			}
			else if (popS!=null && e.getSource() == popS.getSet())
			{

				popS.setVisible(false);
				Card.getBoard().getActivePlayer().setSpell(popS.getSpellbutton().getCard());


			}
			else if (popM!=null && e.getSource() == popM.getSummon())
			{
				int lvl = popM.getMonsterbutton().getCard().getLevel();
				if(!board.getActivePlayer().isSummon())
				{


					if(lvl<=4)
					{
						Card.getBoard().getActivePlayer().summonMonster(popM.getMonsterbutton().getCard());
					}
					else if(lvl>4)
					{
						if((lvl>=7 && board.getActivePlayer().getField().getMonstersArea().size()>=2)
								||(lvl<7 && lvl<=6 && board.getActivePlayer().getField().getMonstersArea().size()>=1))
						{
							if(!(board.getActivePlayer().getField().getPhase().equals(Phase.BATTLE)))
							{
								JOptionPane.showMessageDialog(null,"Choose sacrifices");
								sacrificesummon=true;
								tmp=popM.getMonsterbutton().getCard();
								sacrifices=new ArrayList<MonsterCard>();
							}
							else
							{
								throw new WrongPhaseException();
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null,"Not enough sacrifices");
						}
					}

				}
				else
				{
					throw new MultipleMonsterAdditionException();
				}
				popM.setVisible(false);
			}
			else if (popS!=null && e.getSource() == popS.getActivate())
			{
				SpellCard sp=popS.getSpellbutton().getCard();
				if(!(sp instanceof ChangeOfHeart) && !(sp instanceof MagePower))
				{
					Card.getBoard().getActivePlayer().activateSpell(sp,null);
				}
				else
				{
					if(sp instanceof ChangeOfHeart)
					{
						if(board.getOpponentPlayer().getField().getMonstersArea().size()!=0)
						{
							JOptionPane.showMessageDialog(null,"Choose a monster to change his heart");
							changeofheart=true;
							tmp=sp;
						}
						else
						{
							JOptionPane.showMessageDialog(null,"Opponent has no monsters on field");
						}
					}
					else if(sp instanceof MagePower)
					{
						if(board.getActivePlayer().getField().getMonstersArea().size()!=0)
						{
							JOptionPane.showMessageDialog(null,"Choose a monster to increase power");
							magepower=true;
							tmp=sp;
						}
						else
						{
							JOptionPane.showMessageDialog(null,"You have no monsters on field");
						}
					}
				}
				popS.setVisible(false);
			}
			else if (fpopM!=null && e.getSource() == fpopM.getSwitchmode())
			{
				if(fpopM.getMonsterbutton().getCard().isModeSwitch())
					JOptionPane.showMessageDialog(null,"Cant switch mode twice");
				Card.getBoard().getActivePlayer().switchMonsterMode(fpopM.getMonsterbutton().getCard());
				fpopM.setVisible(false);
			}
			else if (fpopM!=null && e.getSource() == fpopM.getDeclareattack())
			{
				//TODO
				if(Card.getBoard().getOpponentPlayer().getField().getMonstersArea().size()==0)
				{
					board.getActivePlayer().declareAttack(fpopM.getMonsterbutton().getCard());
				}
				else
				{
					if(board.getActivePlayer().getField().getPhase().equals(Phase.BATTLE)){
						if(!fpopM.getMonsterbutton().getCard().attacked())
						{
							if(fpopM.getMonsterbutton().getCard().getMode()==Mode.ATTACK){
								JOptionPane.showMessageDialog(null,"Choose an enemy monster");
								tmp=fpopM.getMonsterbutton().getCard();
								monsterattack=true;}
							else
							{
								fpopM.setVisible(false);
								throw new DefenseMonsterAttackException();
							}
						}
						else
						{
							fpopM.setVisible(false);
							throw new MonsterMultipleAttackException();
						}
					}
					else{
						fpopM.setVisible(false);
						throw new WrongPhaseException();
					}
				}
				fpopM.setVisible(false);
			}
			if(e.getSource()!=startbutton && e.getSource()!=gui.getStartbutton())
				gui.changegui(board.getActivePlayer(),board.getOpponentPlayer(), board.getActivePlayer().getField().getPhase(),this);

		}
		catch(Exception ex)
		{
			if(ex instanceof NullPointerException)
				JOptionPane.showMessageDialog(null,"Empty Slot");
			if(ex instanceof MultipleMonsterAdditionException)
			{
				popM.setVisible(false);
				JOptionPane.showMessageDialog(null,"Cannot Add two monsters in same turn");
			}
			if(ex instanceof NoMonsterSpaceException)
			{
				JOptionPane.showMessageDialog(null,"No Monster Space");
				popM.setVisible(false);
			}
			if(ex instanceof NoSpellSpaceException)
			{
				if(popS!=null)
				popS.setVisible(false);
				JOptionPane.showMessageDialog(null,"No Spell Space");
			}
			if(ex instanceof WrongPhaseException)
			{
				JOptionPane.showMessageDialog(null,"Wrong Phase");
				if(fpopM!=null)
					fpopM.setVisible(false);
				if(popM!=null)
					popM.setVisible(false);																		
			}
			if(ex instanceof MonsterMultipleAttackException)
				JOptionPane.showMessageDialog(null,"Can't attack twice with same monster");
			if(ex instanceof DefenseMonsterAttackException)
				JOptionPane.showMessageDialog(null,"Can't attack with monster in DEFENSE mode");
		
		}
	}




	public static boolean empty(String s){
		if(s.equals(""))
			return true;
		else if(s.charAt(0)==' '){
			return empty(s.substring(1));
		}
		else
			return false;
	}


}
