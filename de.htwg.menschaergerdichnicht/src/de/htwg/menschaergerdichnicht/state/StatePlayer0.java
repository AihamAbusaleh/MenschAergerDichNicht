package de.htwg.menschaergerdichnicht.state;

import de.htwg.menschaergerdichnicht.model.Player;

public class StatePlayer0 implements IState {

	@Override
	public Player currentPlayer(Player player) {
		Player p;
		p = new Player(0, "RED", 'R');
		p.setState(this);
		player = p;
		return player;

	}

	@Override
	public String toString() {
		return "RED";
	}
}
