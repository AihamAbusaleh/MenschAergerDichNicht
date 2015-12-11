package de.htwg.menschaergerdichnicht.state;

import de.htwg.menschaergerdichnicht.model.Player;

public interface IState {
	Player currentPlayer(Player player);
}
