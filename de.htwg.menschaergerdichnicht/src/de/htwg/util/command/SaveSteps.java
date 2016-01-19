package de.htwg.util.command;

import de.htwg.menschaergerdichnicht.model.GameField;

public class SaveSteps implements IDoUndoCommand {
	private GameField oldGamefield;
 	private GameField gamefield;
  
	 
	public SaveSteps(GameField  gamefield) { // addCommand
		this.gamefield =  gamefield;
	}
 
 


	@Override
	public void doMyCommand() { // doCommand
		 oldGamefield = gamefield;
 
	}


	@Override
	public GameField undoMyStep() {
		return oldGamefield;
	}


}
