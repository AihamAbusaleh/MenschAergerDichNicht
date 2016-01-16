package de.htwg.util.command;

import java.util.LinkedList;

public class CommandManager {

	public LinkedList<IDoUndoCommand> undo = new LinkedList<IDoUndoCommand>();

	public void doCommand(IDoUndoCommand newCommand) {
		IDoUndoCommand temp = new SaveSteps(newCommand.getGamefield());
		temp.doMyCommand();
		undo.push(temp);
	}

	public void undoCommand() {
		if (!undo.isEmpty()) {
			IDoUndoCommand top = undo.pop();
			top.undoMyStep();
		}
	}

	public void reset() {
		undo.clear();
	}

}
