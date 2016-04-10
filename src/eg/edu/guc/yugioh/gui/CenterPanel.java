package eg.edu.guc.yugioh.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import eg.edu.guc.yugioh.board.player.Phase;
import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.listeners.Controller;

public class CenterPanel extends JPanel{
	JLabel main1=new JLabel("MAIN1"); 
	JLabel battle=new JLabel("BATTLE"); 
	JLabel main2=new JLabel("MAIN2"); 
	Player p1;
	Player p2;
	JLabel player1; 
	JLabel player2; 
	JButton endphase;
	JButton endturn;
	public CenterPanel(Player p1, Player p2,Phase phase, Controller controller)
	{
		super();
		//this.setBackground(Color.BLACK);
		this.setOpaque(false);
		this.setPreferredSize(new Dimension(600, 40));
		this.setLayout(new GridLayout(1,8));
		switch (phase)
		{
		case MAIN1: main1.setForeground(Color.RED);this.add(main1); this.add(new JLabel());this.add(new JLabel());break;
		case BATTLE:battle.setForeground(Color.RED);this.add(new JLabel());this.add(battle); this.add(new JLabel());break;
		case MAIN2:	main2.setForeground(Color.RED); this.add(new JLabel()); this.add(new JLabel());this.add(main2);break;

		}
		
		ImageIcon endPhase=new ImageIcon("End Phase.png");
		ImageIcon endTurn=new ImageIcon("End Turn.png");
		
		endphase=new JButton(endPhase);
		endphase.setContentAreaFilled(false);
		endphase.setBorderPainted(false);
		endphase.addActionListener(controller);
		endturn=new JButton(endTurn);
		endturn.setContentAreaFilled(false);
		endturn.setBorderPainted(false);
		endturn.addActionListener(controller);
		this.add(endphase);
		this.add(new JLabel(""));
		this.add(new JLabel(""));
		this.add(endturn);
		this.add(new JLabel(""));
		player1=new JLabel(p1.getName());
		player1.setForeground(Color.RED);
		this.add(player1);
		this.add(new JLabel(""));

		validate();
		repaint();
		revalidate();


	}
	public JButton getEndphase() {
		return endphase;
	}
	public void setEndphase(JButton endphase) {
		this.endphase = endphase;
	}
	public JButton getEndturn() {
		return endturn;
	}
	public void setEndturn(JButton endturn) {
		this.endturn = endturn;
	}


}
