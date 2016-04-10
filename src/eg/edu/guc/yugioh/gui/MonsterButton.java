package eg.edu.guc.yugioh.gui;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.text.html.HTML;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.MonsterCard;

public class MonsterButton extends CardButton{
	private MonsterCard monster;
	public MonsterButton(Card m,String s)
	{
		super(s);
		this.setOpaque(false);
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
		this.monster=(MonsterCard) m;
		this.validate();
		this.setPreferredSize(new Dimension(125, 115));
		if(Card.getBoard().getActivePlayer().getField().getMonstersArea().contains(m))
		{
			this.setToolTipText("Name:"+monster.getName()+"  ATK:"+ monster.getAttackPoints()+" DEF:"+monster.getDefensePoints()+" lvl"+monster.getLevel()+" Mode:"+monster.getMode());
		}
	}

	public MonsterButton(ImageIcon img,Card m)
	{
		super(img);
		this.setOpaque(false);
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
		this.monster=(MonsterCard) m;
		this.validate();
		this.setPreferredSize(new Dimension(125, 115));
		if(Card.getBoard().getActivePlayer().getField().getMonstersArea().contains(m)||Card.getBoard().getActivePlayer().getField().getHand().contains(m))
		{
			this.setToolTipText("Name:"+monster.getName()+"  ATK:"+ monster.getAttackPoints()+" DEF:"+monster.getDefensePoints()+" lvl"+monster.getLevel()+" Mode:"+monster.getMode());
		}
	}



	public MonsterCard getCard() {
		return monster;
	}

	public void setMonster(MonsterCard m) {
		this.monster = m;
	}
	
}
