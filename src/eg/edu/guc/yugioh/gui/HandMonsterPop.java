package eg.edu.guc.yugioh.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import eg.edu.guc.yugioh.listeners.Controller;

public class HandMonsterPop extends JFrame
{
	private JButton summonButton;
	private JButton setButton;
	private MonsterButton monsterbutton;
	public JButton getSummon() {
		return summonButton;
	}
	public JButton getSet() {
		return setButton;
	}
	
	public HandMonsterPop(Controller cont, CardButton b)
	{
		super();
		setSize(250,100);
		this.setLayout(new GridLayout(2,2));
		this.setVisible(true);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		JLabel popupMsg = new JLabel("   Would you like to");
		this.add(popupMsg);
		this.add(new JLabel("set or summon!?"));
		
		summonButton = new JButton("Summon");
		summonButton.setSize(50,50);
		summonButton.addActionListener(cont);
		setButton = new JButton("Set");
		setButton.setSize(50,50);
		setButton.addActionListener(cont);
		this.add(summonButton);
		this.add(setButton);
		monsterbutton=(MonsterButton) b;
		validate();
	}
	public MonsterButton getMonsterbutton() {
		return monsterbutton;
	}
	
}
