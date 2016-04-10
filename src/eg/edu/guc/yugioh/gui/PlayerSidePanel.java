package eg.edu.guc.yugioh.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import eg.edu.guc.yugioh.board.player.Player;

public class PlayerSidePanel extends JPanel {
	JLabel deck;
	JLabel graveyard;
	JLabel name;
	JLabel lifepoints;
	public PlayerSidePanel(Player player)
	{
		super();
		this.setOpaque(false);
		this.setPreferredSize(new Dimension(350, 230));
		this.setLayout(new GridLayout(2,3));
		ImageIcon cardback=new ImageIcon("Card Back.png");
		deck=new JLabel(cardback);
		deck.setOpaque(false);
		deck.setText(player.getField().getDeck().getDeck().size()+"");
		deck.setFont(new Font(""+deck.getFont(), Font.PLAIN, 22));
		deck.setPreferredSize(getMaximumSize());
		deck.setForeground(Color.RED);
		
		if(player.getField().getGraveyard().size()==0)
			graveyard=new JLabel("Grave Yard");
		else
		{
			int i=player.getField().getGraveyard().size()-1;
			ImageIcon img=new ImageIcon(player.getField().getGraveyard().get(i).getName()+".png");
			graveyard=new JLabel(img);
			graveyard.setToolTipText(player.getField().getGraveyard().get(i).getName());
		}
		graveyard.setOpaque(false);
		graveyard.setForeground(Color.RED);
		
		name=new JLabel(player.getName());
		name.setFont(new Font(""+name.getFont(), Font.PLAIN, 22));
		name.setOpaque(false);
		name.setForeground(Color.RED);
		lifepoints=new JLabel(""+player.getLifePoints());
		lifepoints.setFont(new Font(""+name.getFont(), Font.PLAIN, 22));
		lifepoints.setOpaque(false);
		lifepoints.setForeground(Color.RED);
		this.add(deck);
		this.add(new JLabel(""));
		this.add(name);
		this.add(graveyard);
		this.add(new JLabel(""));
		this.add(lifepoints);
		validate();
		repaint();
		revalidate();


	}
	public JLabel getDeck() {
		return deck;
	}
	public void setDeck(int n) {
		deck.setText("Deck"+" "+n);
	}
	public JLabel getGraveyard() {
		return graveyard;
	}
	public void setGraveyard(String s) {
		graveyard.setText(s);
	}

	public JLabel getLifepoints() {
		return lifepoints;
	}
	public void setLifepoints(JLabel lifepoints) {
		this.lifepoints = lifepoints;
	}

}
