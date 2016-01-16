package de.htwg.util.observer;

import de.htwg.menschaergerdichnicht.model.Player;

public interface IObserver {
	/**
	 * update function in the observers
	 * 
	 * @param currentPlayer
	 *            the current player,
	 * @param gameEnded
	 *            the state of the game(ended or not)
	 *
	 */
	void update(Player currentPlayer, boolean gameEnded);

	/**
	 * to display who has thrown dice, and what a number
	 * 
	 * @param currentplayer
	 * @param dice
	 */
	void showDice(Player currentplayer, int dice);

}