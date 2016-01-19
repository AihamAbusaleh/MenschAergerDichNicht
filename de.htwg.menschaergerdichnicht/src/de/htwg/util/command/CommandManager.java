package de.htwg.util.command;

import java.util.LinkedList;
import java.util.List;

public class CommandManager {

	public List<IDoUndoCommand> undo = new LinkedList<IDoUndoCommand>();
	
	public void doCommand(IDoUndoCommand newCommand) {
	//	IDoUndoCommand temp = new SaveSteps(newCommand.doMyCommand1());
		newCommand.doMyCommand();

 		((LinkedList<IDoUndoCommand>) undo).push(newCommand);
	}

	public void undoCommand() {
		if (!undo.isEmpty()) {
			IDoUndoCommand top = ((LinkedList<IDoUndoCommand>) undo).pop();
			System.out.println(top); 
			top.undoMyStep();
		}
	}

	public void reset() {
		undo.clear();
	}

}
