package de.htwg.util.command;


import java.util.Stack;


public class CommandManager    {

	public static Stack<IDoUndoCommand> undo = new Stack<IDoUndoCommand>();
	private static Stack<IDoUndoCommand> redo = new Stack<IDoUndoCommand>();
	private static IDoUndoCommand top;

	public void doCommand(IDoUndoCommand newCommand) {
		newCommand.doCommand();
		undo.push(newCommand);
	}

	public void undoCommand() {
		if (!undo.isEmpty()) {
			top = undo.pop();
			top.undoCommand();
			redo.push(top);
		}
	}

	public void redoCommand() {
		if (!redo.isEmpty()) {
			top = redo.pop();
			top.redoCommand();
		}
	}

	public void reset() {
		undo.clear();
	}

	

}
