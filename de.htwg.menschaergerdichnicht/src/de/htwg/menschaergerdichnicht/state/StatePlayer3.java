package de.htwg.menschaergerdichnicht.state;

import de.htwg.menschaergerdichnicht.model.Player;

public class StatePlayer3 implements IState {
	@Override
	public Player currentPlayer(Player player) {
		player = new Player(3, "Pink", 'P');
		
		player.setState(this);
		return player;
	}

	@Override
	public String toString() {
		return "Pink";
	}

}
