package eg.edu.guc.yugioh.gui;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.text.html.HTML;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.MonsterCard;

public abstract class CardButton extends JButton{
	public CardButton(String s)
	{
		super(s);
	}
	public CardButton(){
		super();
	}

	public CardButton(ImageIcon img)
	{
		super(img);
		this.setOpaque(false);
	}
	public abstract Card getCard();
	
	


}
