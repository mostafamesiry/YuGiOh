package eg.edu.guc.yugioh.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import eg.edu.guc.yugioh.listeners.Controller;

public class FieldMonsterPop extends JFrame{
	private JButton switchmode;
	private JButton declareattack;
	private MonsterButton monsterbutton;
	public FieldMonsterPop(Controller cont, CardButton b)
	{
		super();
		setSize(300,100);
		this.setLayout(new GridLayout(2,1));
		this.setVisible(true);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		this.add(new JLabel("Would you like to switch mode or attack!?"));
		JPanel bottom = new JPanel(new GridLayout(1,2));
		
		switchmode = new JButton("Switch Mode");
		switchmode.setSize(50,50);
		switchmode.addActionListener(cont);
		declareattack = new JButton("Attack");
		declareattack.setSize(50,50);
		declareattack.addActionListener(cont);
		bottom.add(switchmode);
		bottom.add(declareattack);
		this.add(bottom);
		monsterbutton=(MonsterButton) b;
		validate();
	}
	public JButton getSwitchmode() {
		return switchmode;
	}
	public JButton getDeclareattack() {
		return declareattack;
	}
	public MonsterButton getMonsterbutton() {
		return monsterbutton;
	}
	
	
}
