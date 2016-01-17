package de.htwg.menschaergerdichnicht.state;

import de.htwg.menschaergerdichnicht.model.Player;

public class StatePlayer3 implements IState {
	@Override
	public Player currentPlayer(Player player) {
		Player p = new Player(3, "PINK", 'P');
 		p.setState(this);
 		return p;
	}

	@Override
	public String toString() {
		return "PINK";
	}

}
