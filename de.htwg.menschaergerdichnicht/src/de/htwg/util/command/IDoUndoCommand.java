package de.htwg.util.command;

import de.htwg.menschaergerdichnicht.model.GameField;

public interface IDoUndoCommand {
	public GameField doMyCommand();
	public GameField undoMyStep();
  

}
