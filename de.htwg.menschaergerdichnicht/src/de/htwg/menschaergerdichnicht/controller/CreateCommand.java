package de.htwg.menschaergerdichnicht.controller;

import de.htwg.menschaergerdichnicht.model.GameField;
import de.htwg.util.command.IDoUndoCommand;
import de.htwg.util.memento.Organisator;

public class CreateCommand extends Organisator implements IDoUndoCommand {
	GameField gamefield;

	public CreateCommand(GameField gamefield) {
		this.gamefield = gamefield;
	}

	@Override
	public void doCommand() {
		set(gamefield);
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
