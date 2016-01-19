package de.htwg.util.command;

import de.htwg.menschaergerdichnicht.model.GameField;

public interface IDoUndoCommand {
	/**
	 * to save the current gamefield
	 * 
	 * @return the current gamefield
	 */
	public void doMyCommand();

	/**
	 * to undo the last saved gamefield
	 * 
	 * @return the saved gamefield
	 */
	public GameField undoMyStep();
 

}
