package de.htwg.util.command;


public interface IDoUndoCommand {
	public void doCommand();
	public void undoCommand();
	public void redoCommand();


}
