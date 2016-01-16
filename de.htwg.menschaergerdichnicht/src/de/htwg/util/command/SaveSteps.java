package de.htwg.util.command;

import de.htwg.menschaergerdichnicht.model.GameField;

public class SaveSteps implements IDoUndoCommand {
	private GameField gamefield;

	@Override
	public GameField getGamefield() {
		return gamefield;
	}

	public SaveSteps(GameField gamefield) { // addCommand

		this.gamefield = gamefield;
	}

	@Override
	public GameField doMyCommand() { // doCommand
		return this.gamefield;

	}

	@Override
	public GameField undoMyStep() {
		return gamefield;
	}

}
