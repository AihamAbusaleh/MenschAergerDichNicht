package de.htwg.menschaergerdichnicht.memento;

import de.htwg.menschaergerdichnicht.model.GameField;

public class Memento {
	GameField gamefield;

	public Memento(GameField gamefield ) {
		this.gamefield =  gamefield;
	}

	public GameField getMySavedState() {
		return gamefield;
	}

}
