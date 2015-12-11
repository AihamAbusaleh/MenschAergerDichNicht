package de.htwg.util.memento;

import de.htwg.menschaergerdichnicht.model.GameField;

public class Organisator {
	private Memento memento;

	public void set(GameField gamefield) {
		
		this.memento = new Memento(gamefield);
	}

	public GameField restorState() {
		return memento.getMySavedState();
	}

}
