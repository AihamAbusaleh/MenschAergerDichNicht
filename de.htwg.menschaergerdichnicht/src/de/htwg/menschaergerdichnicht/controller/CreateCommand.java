package de.htwg.menschaergerdichnicht.controller;

import de.htwg.menschaergerdichnicht.command.IDoUndoCommand;
import de.htwg.menschaergerdichnicht.memento.Organisator;
import de.htwg.menschaergerdichnicht.model.GameField;

public class CreateCommand extends Organisator implements IDoUndoCommand {
	GameField gamefield;

	public CreateCommand(GameField gamefield) {

		this.gamefield = gamefield;
	}

	@Override
	public GameField doCommand() {
		set(gamefield);
		return gamefield;
	}

	@Override
	public void undoCommand() {

		restorState();
	}

	@Override
	public void redoCommand() { // save the current gamefield
		set(gamefield);

	}

}
