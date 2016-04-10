package eg.edu.guc.yugioh.gui;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.spells.SpellCard;

public class SpellButton extends CardButton{
	private SpellCard spell;
	public SpellButton(Card sp,String s)
	{
		super(s);
		this.setOpaque(false);
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
		this.spell=(SpellCard) sp;
		this.setPreferredSize(new Dimension(125, 115));
		if(Card.getBoard().getActivePlayer().getField().getSpellArea().contains(sp))
		{
			this.setToolTipText(spell.getName()+", "+spell.getDescription());
		}
	}
	public SpellButton(Card sp)
	{
		super(sp.getName());
		this.setOpaque(false);
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
		this.spell=(SpellCard) sp;
		this.setPreferredSize(new Dimension(125, 130));
		this.setToolTipText(spell.getName()+", "+spell.getDescription());


	}
	public SpellButton(ImageIcon img, Card sp)
	{
		super(img);	
		this.setOpaque(false);
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
		this.spell=(SpellCard) sp;
		if(Card.getBoard().getActivePlayer().getField().getSpellArea().contains(sp)||Card.getBoard().getActivePlayer().getField().getHand().contains(sp))
		{
			this.setToolTipText(spell.getName()+", "+spell.getDescription());
		}
	}
	public SpellCard getCard() {
		return spell;
	}
	public void setCard(SpellCard spell) {
		this.spell = spell;
	}



}
