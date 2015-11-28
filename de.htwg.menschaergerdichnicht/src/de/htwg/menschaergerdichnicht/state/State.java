package de.htwg.menschaergerdichnicht.state;

import de.htwg.menschaergerdichnicht.model.Player;

public interface State {
	Player currentPlayer(Player player);
}
