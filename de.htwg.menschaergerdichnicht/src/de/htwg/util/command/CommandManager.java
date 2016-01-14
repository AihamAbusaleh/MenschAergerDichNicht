package de.htwg.util.command;

import java.util.Deque;
import java.util.LinkedList;

import de.htwg.menschaergerdichnicht.controller.SaveSteps;
import de.htwg.menschaergerdichnicht.model.GameField;

public class CommandManager {

	public LinkedList<IDoUndoCommand> undo = new LinkedList<IDoUndoCommand>();
 
		
 
  
	 
	 
	public void doCommand(IDoUndoCommand newCommand) {
 		newCommand.doMyCommand();
		undo.push(newCommand);
  	}
 
	public void undoCommand() {
		if (!undo.isEmpty()) {
			IDoUndoCommand	top = undo.pop();
			top.undoMyStep();
 		}
	}

	 
	 
	public void reset() {
		undo.clear();
	}

}
