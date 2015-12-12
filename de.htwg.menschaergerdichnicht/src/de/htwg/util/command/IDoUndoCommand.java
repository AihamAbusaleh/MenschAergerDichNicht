package de.htwg.util.command;


public interface IDoUndoCommand {
	public void saveMySteps();
	public void undoMyStep();
	public void redoMyStep();


}
