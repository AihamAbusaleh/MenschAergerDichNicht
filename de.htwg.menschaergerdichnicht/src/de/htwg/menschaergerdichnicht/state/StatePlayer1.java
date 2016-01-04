package de.htwg.menschaergerdichnicht.state;

import de.htwg.menschaergerdichnicht.model.Player;

public class StatePlayer1 implements IState {

	@Override
	public Player currentPlayer(Player player) {
		player = new Player(1, "Blau", 'B');

		player.setState(this);
		return player;

	}

	@Override
	public String toString() {
		return "Blau";
	}
}
