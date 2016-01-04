package de.htwg.util.command;

import java.util.Deque;
import java.util.LinkedList;

import de.htwg.menschaergerdichnicht.controller.SaveSteps;

public class CommandManager {

	public Deque<IDoUndoCommand> undo = new LinkedList<IDoUndoCommand>();
	public Deque<IDoUndoCommand> redo = new LinkedList<IDoUndoCommand>();
	private IDoUndoCommand top;

	public void doCommand(IDoUndoCommand newCommand) {

		newCommand.saveMySteps();
		undo.push(newCommand);
	}

	public void undoCommand() {
		if (!undo.isEmpty()) {
			top = undo.pop();
			top.undoMyStep();
			redo.push(top);
		}
	}

	public void redoCommand() {
		if (!redo.isEmpty()) {
			top = redo.pop();
			top.redoMyStep();
		}
	}

	public void reset() {
		undo.clear();
	}

}
