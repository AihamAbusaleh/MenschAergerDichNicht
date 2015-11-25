package de.htwg.menschaergerdichnicht.observer;

import de.htwg.menschaergerdichnicht.model.Player;

public interface IObserver {
	/**
	* updatefunction in the observers
	* @param Iplayer the current player, boolean the state of the game(ended or not)
	*
	* */
	void update(Player currentPlayer, boolean gameEnded);

	void showDice(Player currentplayer, int dice);
	

}