package de.htwg.menschaergerdichnicht.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.menschaergerdichnicht.controller.Controller;
import de.htwg.util.observer.IObserver;

public class ControllerTest {

	private Controller controller;
	private IObserver o;

	@Before
	public void setUp() throws Exception {
		controller = new Controller();
		controller.getCurrentPlayer();

	}

	@Test
	public void testmove() {

		if (controller.getCurrentPlayer() == "RED" && controller.moveStart()) {
			assertTrue(controller.move(30));
			assertFalse(controller.move(5));
			controller.getCurrentPlayer();
		} else {
			assertFalse(controller.move(5));

		}
		if (controller.getCurrentPlayer() == "BLUE" && controller.moveStart()) {
			assertTrue(controller.move(0));
			assertFalse(controller.move(6));
			controller.getCurrentPlayer();

		} else {
			assertFalse(controller.move(5));

		}

		if (controller.getCurrentPlayer() == "YELLOW" && controller.moveStart()) {
			assertTrue(controller.move(10));
			assertFalse(controller.move(7));
			controller.getCurrentPlayer();

		} else {
			assertFalse(controller.move(5));

		}

		if (controller.getCurrentPlayer() == "PINK" && controller.moveStart()) {
			assertTrue(controller.move(20));
			assertFalse(controller.move(8));
			controller.getCurrentPlayer();

		} else {
			assertFalse(controller.move(5));

		}

		assertFalse(controller.move(21));

	}

	@Test
	public void testmoveStart() {

		if (controller.getCurrentPlayer() == "RED") {
			if (controller.dice() == 6) {
				assertTrue(controller.moveStart());
			} else {
				assertFalse(controller.moveStart());

			}

		}
		if (controller.getCurrentPlayer() == "BLUE") {
			if (controller.dice() == 6) {
				assertTrue(controller.moveStart());
			} else {
				assertFalse(controller.moveStart());

			}

		}

		if (controller.getCurrentPlayer() == "YELLOW") {
			if (controller.dice() == 6) {
				assertTrue(controller.moveStart());
			} else {
				assertFalse(controller.moveStart());

			}

		}
		if (controller.getCurrentPlayer() == "PINK") {
			if (controller.dice() == 6) {
				assertTrue(controller.moveStart());
			} else {
				assertFalse(controller.moveStart());

			}

		}


	}

	@Test
	public void testgetTokenColor() {
		boolean moveStart = controller.moveStart();

		if (controller.getCurrentPlayer() == "RED" && moveStart) {
			assertEquals('R', controller.getTokenColor(30));

		}

		if (!moveStart) {
			assertEquals('x', controller.getTokenColor(10));
			assertEquals('x', controller.getTokenColor(20));
			assertEquals('x', controller.getTokenColor(30));
			assertEquals('x', controller.getTokenColor(0));
		}
		assertEquals('x', controller.getTokenColor(8));
		assertEquals('x', controller.getTokenColor(11));
		assertEquals('x', controller.getTokenColor(12));

	}

	@Test
	public void testsetNextPlayer() {
		if (controller.getCurrentPlayer() == "RED") {
			assertEquals("RED", controller.getCurrentPlayer());

		}
		controller.setNextPlayer();

		if (controller.getCurrentPlayer() == "BLUE") {
			assertEquals("BLUE", controller.getCurrentPlayer());
		}

	}

	@Test
	public void testgetTokenColorBlock() {

		if (controller.moveStart()) {
			assertEquals(' ', controller.getTokenColorBlock(0, 0));
			assertEquals('R', controller.getTokenColorBlock(0, 1));
			assertEquals('R', controller.getTokenColorBlock(0, 2));
			assertEquals('R', controller.getTokenColorBlock(0, 3));
		} else {
			assertEquals('R', controller.getTokenColorBlock(0, 0));
			assertEquals('R', controller.getTokenColorBlock(0, 1));
			assertEquals('R', controller.getTokenColorBlock(0, 2));
			assertEquals('R', controller.getTokenColorBlock(0, 3));
		}
	}

	@Test
	public void testgetTokenColorHouse() {
		assertEquals(' ', controller.getTokenColorHouse(0, 0));
		assertEquals(' ', controller.getTokenColorHouse(0, 1));
		assertEquals(' ', controller.getTokenColorHouse(0, 2));
		assertEquals(' ', controller.getTokenColorHouse(0, 3));
		assertEquals(' ', controller.getTokenColorHouse(1, 3));

	}

	@Test
	public void testRounded() {

		assertFalse(controller.rounded(11));
		assertFalse(controller.rounded(12));

	}

	@Test
	public void testIsGameEnded() {
		if (controller.isGameEnded())
			assertTrue(controller.isGameEnded());
		else
			assertFalse(controller.isGameEnded());

	}

	@Test
	public void testIsFieldEmpty() {

		if (controller.isFieldEmpty()) {
			assertTrue(controller.isFieldEmpty());
		} else {
			assertFalse(controller.isFieldEmpty());
		}
	}

	@Test
	public void testDice() {
		int dice = controller.dice();
		if (dice == 5)
			assertEquals(5, dice);
		if (dice == 1)
			assertEquals(1, dice);
		if (dice == 2)
			assertEquals(2, dice);
		if (dice == 3)
			assertEquals(3, dice);
		if (dice == 4)
			assertEquals(4, dice);
		if (dice == 6)
			assertEquals(6, dice);
	}

	@Test
	public void testStoneCanOut() {
		if (controller.emptyField()) {
			if (controller.dice() == 6) {
				controller.stoneCanOut();
				assertFalse(controller.emptyField());
			}
		}
	}

	@Test
	public void testthrowDiceGUI() {
		 
		int dice = controller.dice;
		if (controller.getCurrentPlayer() == "RED") {
			if (dice == 1) {
				assertEquals(" RED threw [2]", controller.throwDiceGUI());

			}
			if (dice == 2) {
				assertEquals(" RED threw [2]", controller.throwDiceGUI());

			}
			if (dice == 3) {
				assertEquals(" RED threw [3]", controller.throwDiceGUI());

			}
			if (dice == 4) {
				assertEquals(" RED threw [4]", controller.throwDiceGUI());

			}
			if (dice == 5) {
				assertEquals(" RED threw [5]", controller.throwDiceGUI());

			}
			if (dice == 6) {
				assertEquals(" RED threw [6]", controller.throwDiceGUI());

			}
		}
	}

	@Test
	public void testemptyField() {
		if (!controller.moveStart()) {
			assertTrue(controller.emptyField());
		} else {
			controller.getOutOfBlock();
			assertFalse(controller.emptyField());
		}

	}

	@Test
	public void testgetOutOfBlock() {
		assertTrue(controller.getOutOfBlock());
	}

	@Test
	public void testRegister() {
		controller.registerObserver(o);

	}

	@Test
	public void testUnRegister() {
		controller.unregisterObserver(o);

	}

	@Test
	public void testUpdateObserver() {
		boolean e = false;
		controller.updateObservers();
		assertFalse(e);
	}

	@Test
	public void testGetCurrentPlayer() {
		if (controller.getCurrentPlayer() == "RED") {
			controller.setNextPlayer();
			assertEquals("BLUE", controller.getCurrentPlayer());
		}
	}
}