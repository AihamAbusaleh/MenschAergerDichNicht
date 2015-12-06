package de.htwg.menschaergerdichnicht.command;

import de.htwg.menschaergerdichnicht.model.GameField;

public interface IDoUndoCommand {
	public GameField doCommand();
	public void undoCommand();
	public void redoCommand();


}
