package de.htwg.menschaergerdichnicht.command;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.menschaergerdichnicht.controller.Controller;
import de.htwg.menschaergerdichnicht.controller.SaveSteps;
import de.htwg.menschaergerdichnicht.model.GameField;
import de.htwg.util.command.CommandManager;

public class CommandTest {

	CommandManager manager;
	SaveSteps step;
	GameField gamefield;
	Controller c;

	@Before
	public void setup() {
		manager = new CommandManager();
		c = new Controller();
		step = new SaveSteps(gamefield);
	}

	@Test
	public void testdoCommand() {
		assertEquals(0, manager.undo.size());
		manager.doCommand(step);
		assertEquals(1, manager.undo.size());
		assertNotEquals(2, manager.undo.size());
	}

	@Test
	public void testundoCommand() {
 		manager.doCommand(step);
		assertEquals(1, manager.undo.size());
		manager.undoCommand();
		assertNotEquals(2, manager.undo.size());
		assertEquals(0, manager.undo.size());

	}
	@Test
	public void testreset(){
		manager.doCommand(step);
		manager.doCommand(step);
		assertEquals(2, manager.undo.size());
		manager.reset();
		assertEquals(0, manager.undo.size());

	}
}
