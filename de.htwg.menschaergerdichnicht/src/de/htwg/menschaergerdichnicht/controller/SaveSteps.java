package de.htwg.menschaergerdichnicht.controller;

import de.htwg.menschaergerdichnicht.model.GameField;
import de.htwg.util.command.CommandManager;
import de.htwg.util.command.IDoUndoCommand;
import de.htwg.util.memento.Organisator;

public class SaveSteps implements IDoUndoCommand {
	private GameField gamefield;
 
	
	 

	public SaveSteps(GameField gamefield) { // addCommand
		
		this.gamefield = gamefield;
	}

	@Override
	public GameField doMyCommand() {   // doCommand
		return this.gamefield;
		
	}

	@Override
	public GameField undoMyStep() {
		return gamefield;
	}

	 
	 

	 

}
