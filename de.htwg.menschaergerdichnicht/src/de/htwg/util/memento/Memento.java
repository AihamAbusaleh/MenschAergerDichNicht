package de.htwg.util.memento;

import de.htwg.menschaergerdichnicht.model.GameField;

public class Memento {
	GameField gamefield;

	public Memento(GameField gamefield ) {
		this.gamefield =  gamefield;
	}

	public GameField getMySavedStep() {
		return gamefield;
	}

}
