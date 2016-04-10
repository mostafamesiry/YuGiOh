package eg.edu.guc.yugioh.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import eg.edu.guc.yugioh.listeners.Controller;

public class HandSpellPop extends JFrame{
	private JButton activateButton;
	private JButton setButton;
	private SpellButton spellbutton;

	public HandSpellPop(Controller cont, CardButton b)
	{
		super();
		setSize(250,100);
		this.setLayout(new GridLayout(2,2));
		this.setVisible(true);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		JLabel popupMsg = new JLabel("   Would you like to");
		this.add(popupMsg);
		this.add(new JLabel("set or activate!?"));
		
		activateButton = new JButton("Activate");
		activateButton.setSize(50,50);
		activateButton.addActionListener(cont);
		setButton = new JButton("Set");
		setButton.setSize(50,50);
		setButton.addActionListener(cont);
		this.add(activateButton);
		this.add(setButton);
		spellbutton = (SpellButton) b;
		validate();
	}

	public JButton getActivate() {
		return activateButton;
	}

	public JButton getActivateButton() {
		return activateButton;
	}

	public JButton getSet() {
		return setButton;
	}

	public SpellButton getSpellbutton() {
		return spellbutton;
	}

	

	
	
}
