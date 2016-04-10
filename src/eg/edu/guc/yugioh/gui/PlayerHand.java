package eg.edu.guc.yugioh.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.listeners.Controller;

public class PlayerHand extends JPanel{
	ArrayList<CardButton> hand;
	public PlayerHand(ArrayList<Card> handcards, Controller controller)
	{
		this.setPreferredSize(new Dimension(750, 115));
		this.setOpaque(false);
		this.setLayout(new GridLayout(1,handcards.size()));
		hand=new ArrayList<CardButton>();
		for(int i=0;i<handcards.size();i++)
		{
			if(Card.getBoard().getActivePlayer().getField().getHand().equals(handcards)){
				if(handcards.get(i) instanceof SpellCard)
				{
					ImageIcon img=new ImageIcon(handcards.get(i).getName()+".png");
					SpellButton sb = new SpellButton(img,handcards.get(i));
					sb.setOpaque(false);
					sb.addActionListener(controller);
					hand.add(sb);
				}
				else
				{
					ImageIcon img=new ImageIcon(handcards.get(i).getName()+".png");
					MonsterButton mb =new MonsterButton(img,handcards.get(i));
					mb.setOpaque(false);
					mb.addActionListener(controller);
					hand.add(mb);
				}
			}
			else
			{
				if(handcards.get(i) instanceof SpellCard)
				{
					ImageIcon img=new ImageIcon("Card Back.png");
					SpellButton sb = new SpellButton(img,handcards.get(i));
					sb.setOpaque(false);
					sb.addActionListener(controller);
					hand.add(sb);
				}
				else
				{
					ImageIcon img=new ImageIcon("Card Back.png");
					MonsterButton mb =new MonsterButton(img, handcards.get(i));
					mb.setOpaque(false);
					mb.addActionListener(controller);
					hand.add(mb);
				}
			}
		}
		for(int j=0;j<hand.size();j++)
		{
			this.add(hand.get(j));
		}


		validate();
		repaint();
		revalidate();	
	}
	public ArrayList<CardButton> getHand() {
		return hand;
	}

}
