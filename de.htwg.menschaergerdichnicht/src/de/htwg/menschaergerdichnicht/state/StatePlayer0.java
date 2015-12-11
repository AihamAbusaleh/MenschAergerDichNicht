package de.htwg.menschaergerdichnicht.state;

import de.htwg.menschaergerdichnicht.model.Player;

public class StatePlayer0 implements IState {
	

	@Override
	public Player currentPlayer(Player player) {

		player = new Player(0, "Rot", 'R');
		player.setState(this); 
		return player;
	
	}

	@Override
	public String toString() {
		return "Player0";
	}

}
