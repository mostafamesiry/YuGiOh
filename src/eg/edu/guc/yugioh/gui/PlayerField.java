package eg.edu.guc.yugioh.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.listeners.Controller;

public class PlayerField extends JPanel{
	Player player;
	PlayerCards playercards;
	PlayerSidePanel playersidepanel;
	PlayerHand playerhand;
	public PlayerField(Player player, Controller controller)
	{
		super();
		this.setOpaque(false);
		this.player=player;
		setSize(900,345);
		this.setLayout(new BorderLayout());
		this.setVisible(true);
		playercards = new PlayerCards(player, controller);
		this.add(playercards,BorderLayout.WEST);
		playersidepanel = new PlayerSidePanel(player);
		this.add(playersidepanel,BorderLayout.EAST);
		playerhand = new PlayerHand(player.getField().getHand(), controller);
		if(Card.getBoard().getActivePlayer()==player)
		{
			this.add(playerhand,BorderLayout.SOUTH);
		}
		else
		{
			this.add(playerhand,BorderLayout.NORTH);
		}
		validate();
		repaint();
		revalidate();
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public PlayerCards getPlayercards() {
		return playercards;
	}
	public void setPlayercards(PlayerCards playercards) {
		this.playercards = playercards;
	}
	public PlayerSidePanel getPlayersidepanel() {
		return playersidepanel;
	}
	public void setPlayersidepanel(PlayerSidePanel playersidepanel) {
		this.playersidepanel = playersidepanel;
	}
	public PlayerHand getPlayerhand() {
		return playerhand;
	}
	public void setPlayerhand(PlayerHand playerhand) {
		this.playerhand = playerhand;
	}

}
