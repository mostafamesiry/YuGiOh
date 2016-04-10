package eg.edu.guc.yugioh.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Mode;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.listeners.Controller;

public class PlayerCards extends JPanel{
	MonsterButton[] monsterlist=new MonsterButton[5];
	SpellButton[] spelllist=new SpellButton[5];
	Player player;
	Controller controller;

	public PlayerCards(Player player, Controller controller)
	{
		super();
		this.setOpaque(false);
		this.setPreferredSize(new Dimension(500, 230));
		this.setLayout(new GridLayout(2,5));
		this.player=player;
		this.controller=controller;
		if(Card.getBoard().getActivePlayer()==player){
			addMonsters();
			addSpells();
		}
		else
		{
			addSpells();
			addMonsters();
		}
		validate();
		repaint();
		revalidate();

	}



	public void addSpells()
	{
		int i;
		int s=player.getField().getSpellArea().size();
		for(i=0;i<s;i++)
		{
			if(player.getField().getSpellArea().get(i).isHidden()){
				ImageIcon img=new ImageIcon("Card Back.png");
				spelllist[i]=new SpellButton(img, player.getField().getSpellArea().get(i));
			}
			else
			{
				spelllist[i]=new SpellButton(player.getField().getSpellArea().get(i));
			}

			spelllist[i].addActionListener(controller);
			spelllist[i].setOpaque(false);
			spelllist[i].setForeground(Color.BLACK);
			this.add(spelllist[i]);

		}
		for(int j=i;i<5;i++)
		{
			spelllist[j]=new SpellButton(null, "Empty");
			spelllist[j].addActionListener(controller);
			spelllist[j].setOpaque(false);
			spelllist[j].setForeground(Color.GRAY);
			this.add(spelllist[j]);
		}
	}
	public void addMonsters()
	{
		String side="";
		int i;
		int s=player.getField().getMonstersArea().size();
		for(i=0;i<s;i++)
		{
			
			if(player.getField().getMonstersArea().get(i).getMode().equals(Mode.DEFENSE))
			{
				side="s";
			}
			else
			{
				side="";
			}
			if(player.getField().getMonstersArea().get(i).isHidden()){
				ImageIcon img=new ImageIcon("Card Back"+side+".png");
				monsterlist[i]=new MonsterButton(img, player.getField().getMonstersArea().get(i));}
			else
			{
				ImageIcon img=new ImageIcon(player.getField().getMonstersArea().get(i).getName()+side+".png");
				monsterlist[i]=new MonsterButton(img, player.getField().getMonstersArea().get(i));
			}
			monsterlist[i].addActionListener(controller);
			monsterlist[i].setOpaque(false);
			if(monsterlist[i].getCard().getMode().equals(Mode.ATTACK))		
				monsterlist[i].setForeground(Color.RED);
			else
				monsterlist[i].setForeground(Color.BLUE);
			this.add(monsterlist[i]);

		}
		for(int j=i;i<5;i++)
		{
			monsterlist[j]=new MonsterButton(null, "Empty");
			monsterlist[j].addActionListener(controller);
			monsterlist[j].setOpaque(false);
			monsterlist[j].setForeground(Color.GRAY);
			this.add(monsterlist[j]);
		}
	}

	public MonsterButton[] getMonsterlist() {
		return monsterlist;
	}

	public SpellButton[] getSpelllist() {
		return spelllist;
	}

}
