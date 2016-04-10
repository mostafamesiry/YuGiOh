package eg.edu.guc.yugioh.gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.*;

import eg.edu.guc.yugioh.board.player.Phase;
import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.listeners.Controller;

public class GUI extends JFrame{
	JButton startbutton;
	JTextField textfield1;
	JTextField textfield2;
	PlayerField playerfield1;
	PlayerField playerfield2;
	CenterPanel centerpanel;
	public GUI()
	{
		super();
		setSize(250,100);
		this.setLayout(new BorderLayout());
		this.setVisible(true);
		this.setTitle("Login");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		JPanel players=new JPanel();
		players.setLayout(new GridLayout(2,2));
		players.setSize(250,70);
		JLabel Player1Name = new JLabel("Player 1");
		JLabel Player2Name = new JLabel("Player 2");
		textfield1 = new JTextField();
		textfield2 = new JTextField();
		startbutton = new JButton("Start");
		startbutton.setSize(250,30);
		players.add(Player1Name);
		players.add(Player2Name);
		players.add(textfield1);
		players.add(textfield2);
		this.add(players, BorderLayout.NORTH);
		this.add(startbutton, BorderLayout.CENTER);
		validate();
		repaint();
		revalidate();
		getContentPane().validate();
		getContentPane().repaint();
		getContentPane().revalidate();
	}
	public JButton getStartbutton() {
		return startbutton;
	}
	public void setStartgame(JButton startgame) {
		this.startbutton = startgame;
	}
	public JTextField getTextfield1() {
		return textfield1;
	}
	public void setTextfield1(JTextField textfield1) {
		this.textfield1 = textfield1;
	}
	public JTextField getTextfield2() {
		return textfield2;
	}
	public void setTextfield2(JTextField textfield2) {
		this.textfield2 = textfield2;
	}
	public void setStartbutton(JButton startbutton) {
		this.startbutton = startbutton;
	}
	public void changegui(Player p1,Player p2, Phase phase, Controller controller) 
	{
		getContentPane().removeAll();
		setSize(900,750);
		setContentPane(new JLabel(new ImageIcon("Background.jpg")));
		this.getContentPane().setBackground(Color.RED);
		setTitle("YuGiOH!");
		this.getContentPane().setLayout(new BorderLayout());
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		playerfield1=new PlayerField(p1, controller);
		this.add(playerfield1, BorderLayout.SOUTH);
		centerpanel = new CenterPanel(p1, p2, phase, controller);
		this.add(centerpanel, BorderLayout.CENTER);
		playerfield2=new PlayerField(p2, controller);
		this.add(playerfield2, BorderLayout.NORTH);
		validate();
		repaint();
		revalidate();
		getContentPane().validate();
		getContentPane().repaint();
		getContentPane().revalidate();

	}
	public PlayerField getPlayerfield1() {
		return playerfield1;
	}
	public void setPlayerfield1(PlayerField playerfield1) {
		this.playerfield1 = playerfield1;
	}
	public PlayerField getPlayerfield2() {
		return playerfield2;
	}
	public void setPlayerfield2(PlayerField playerfield2) {
		this.playerfield2 = playerfield2;
	}
	public CenterPanel getCenterpanel() {
		return centerpanel;
	}
	public void setCenterpanel(CenterPanel centerpanel) {
		this.centerpanel = centerpanel;
	}

}
