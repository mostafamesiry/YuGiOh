package eg.edu.guc.yugioh.board;
import eg.edu.guc.yugioh.board.player.*;
import eg.edu.guc.yugioh.cards.*;

public class Board {
	private Player activePlayer;
	private Player opponentPlayer;
	private Player winner;

	public Board()
	{
		Card.setBoard(this);
	}
	public void whoStarts(Player p1, Player p2)
	{
		double x= Math.random();
		if(x>0.5)
		{
			activePlayer=p1;
			opponentPlayer=p2;
		}
		else
		{
			activePlayer=p2;
			opponentPlayer=p1;
		}
	}

	public void startGame(Player p1, Player p2)
	{
		whoStarts(p1, p2);
		activePlayer.getField().addNCardsToHand(6);
		opponentPlayer.getField().addNCardsToHand(5);

	}

	public void nextPlayer()
	{
		Player tmp=activePlayer;
		activePlayer=opponentPlayer;
		opponentPlayer=tmp;
		activePlayer.getField().addCardToHand();


	}

	public Player getActivePlayer() {
		return activePlayer;
	}

	public void setActivePlayer(Player activePlayer) {
		this.activePlayer = activePlayer;
	}

	public Player getOpponentPlayer() {
		return opponentPlayer;
	}

	public void setOpponentPlayer(Player opponentPlayer) {
		this.opponentPlayer = opponentPlayer;
	}

	public Player getWinner() {
		return winner;
	}

	public void setWinner(Player winner) {
		this.winner = winner;
	}

}
