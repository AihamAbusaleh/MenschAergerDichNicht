package de.htwg.util.command;


import java.util.Deque;
import java.util.LinkedList;


public class CommandManager    {

	public static Deque<IDoUndoCommand> undo = new LinkedList<IDoUndoCommand>();
	private static Deque<IDoUndoCommand> redo = new LinkedList<IDoUndoCommand>();
	private static IDoUndoCommand top;

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
