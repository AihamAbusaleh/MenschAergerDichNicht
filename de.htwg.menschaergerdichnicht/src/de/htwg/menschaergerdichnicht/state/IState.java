package de.htwg.menschaergerdichnicht.state;

import de.htwg.menschaergerdichnicht.model.Player;

public interface IState {
	/**
	 * this function is to set the current player
	 * 
	 * @param player
	 *            the next player
	 */
	Player currentPlayer(Player player);

}
