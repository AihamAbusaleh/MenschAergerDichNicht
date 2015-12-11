package de.htwg.menschaergerdichnicht.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.menschaergerdichnicht.controller.Controller;
import de.htwg.menschaergerdichnicht.model.GameField;
import de.htwg.menschaergerdichnicht.model.Player;
import de.htwg.util.command.CommandManager;

public class ControllerTest {

	private Controller controller;

	@Before
	public void setUp() throws Exception {
		controller = new Controller();
	}

	@Test
	public void testdice() {
		controller.dice();
	}

	@Test
	public void testmove() {
		controller.dice();
		if (!controller.isFieldEmpty()) {
			if (controller.getTokenColor(0) == 'R') {
				assertTrue(controller.move(30));
				assertFalse(controller.move(20));
			}

			if (controller.getTokenColor(1) == 'B') {
				assertTrue(controller.move(0));
				assertFalse(controller.move(20));
			}

			if (controller.getTokenColor(2) == 'G') {
				assertTrue(controller.move(10));
				assertFalse(controller.move(20));
			}

			if (controller.getTokenColor(3) == 'S') {
				assertTrue(controller.move(20));
				assertFalse(controller.move(10));
			}
		}
	}

	@Test
	public void testmoveStart() {
		controller.dice();
		if (!controller.isFieldEmpty())
			assertFalse(controller.moveStart());
		

	}

	@Test
	public void testgetTokenColor() {
		assertEquals('x', controller.getTokenColor(0));
	}

	@Test
	public void testsetNextPlayer() {
		controller.dice();
			if (controller.getTokenColor(0) == 'R') {
				controller.setNextPlayer();
				assertEquals('B', controller.getTokenColor(1));

			}

			if (controller.getTokenColor(1) == 'B') {
				controller.setNextPlayer();
				assertEquals('G', controller.getTokenColor(2));
			}

			if (controller.getTokenColor(2) == 'G') {
				controller.setNextPlayer();
				assertEquals('S', controller.getTokenColor(3));
			}

			if (controller.getTokenColor(3) == 'S') {
				controller.setNextPlayer();
				assertEquals('R', controller.getTokenColor(0));
			}

		
	}

	@Test
	public void testgetTokenColorBlock() {
		controller.dice();
		if (controller.isFieldEmpty())
			assertEquals('R', controller.getTokenColorBlock(0, 0));
	}

	@Test
	public void testgetTokenColorHouse() {
		assertEquals(' ', controller.getTokenColorHouse(0, 0));
	}
	
	@Test
	public void testMoveSeveralSteps() {
		controller.dice(6);
		controller.move(30);
		assertEquals(1,CommandManager.undo.size());
		controller.dice(2);
		controller.move(30);
		assertEquals(2,CommandManager.undo.size());
		
		controller.dice(6);
		controller.move(30);
		assertEquals(3,CommandManager.undo.size());
		GameField field = (GameField) CommandManager.undo.pop();
		assertEquals("R",field.color(0, 30) );
	}

//	@Test
//	public void testgetOutOfBlock() {
//		if (controller.getTokenColor(0) == 'R') {
//			if (controller.moveStart()) {
//				assertTrue(controller.getOutOfBlock());
//				assertFalse(controller.getOutOfBlock());
//			}
//		}
//	}

}