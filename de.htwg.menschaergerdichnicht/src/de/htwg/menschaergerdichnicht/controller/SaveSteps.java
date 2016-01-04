package de.htwg.menschaergerdichnicht.controller;

import de.htwg.menschaergerdichnicht.model.GameField;
import de.htwg.util.command.IDoUndoCommand;
import de.htwg.util.memento.Organisator;

public class SaveSteps extends Organisator implements IDoUndoCommand {
	private GameField gamefield;

	public SaveSteps(GameField gamefield) {
		this.gamefield = gamefield;
	}

	

	@Override
	public void saveMySteps() {
		set(gamefield);
	}

	@Override
	public void undoMyStep() {
		restorMyLastStep();
	}

	@Override
	public void redoMyStep() { // save the current gamefield
		set(gamefield);

	}

}
