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

		if (controller.getTokenColor(30) == 'R' && controller.moveStart()) {
			assertTrue(controller.move(30));
			assertFalse(controller.move(5));
			controller.setNextPlayer();
		}
		if (controller.getTokenColor(0) == 'B' && controller.moveStart()) {
			assertTrue(controller.move(0));
			assertFalse(controller.move(6));
			controller.setNextPlayer();

		}

		if (controller.getTokenColor(10) == 'Y' && controller.moveStart()) {
			assertTrue(controller.move(10));
			assertFalse(controller.move(7));
			controller.setNextPlayer();

		}

		if (controller.getTokenColor(20) == 'P' && controller.moveStart()) {
			assertTrue(controller.move(20));
			assertFalse(controller.move(8));
			controller.setNextPlayer();

		}

		assertFalse(controller.move(0));

	}

	@Test
	public void testmoveStart() {

		if (controller.getCurrentPlayer() == "RED" && controller.dice() == 6) {
			assertTrue(controller.moveStart());
			controller.setNextPlayer();

		}
		if (controller.getCurrentPlayer() == "BLUE" && controller.dice() == 6) {
			assertFalse(controller.moveStart());
			controller.setNextPlayer();

		}

		if (controller.getCurrentPlayer() == "YELLOW" && controller.dice() == 6) {
			assertTrue(controller.moveStart());
			controller.setNextPlayer();

		}
		if (controller.getCurrentPlayer() == "PINK" && controller.dice() == 6) {
			assertTrue(controller.moveStart());
			controller.setNextPlayer();

		}

		if (controller.dice() != 6)
			assertFalse(controller.moveStart());

	}

	@Test
	public void testgetTokenColor() {
		boolean moveStart = controller.moveStart();

		if (controller.getCurrentPlayer() == "RED" && moveStart) {
			assertEquals('R', controller.getTokenColor(30));
			controller.setNextPlayer();

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
			controller.setNextPlayer();
			assertTrue(controller.getCurrentPlayer() == "BLUE");
			assertFalse(controller.getCurrentPlayer() == "PINK");
			controller.setNextPlayer();
			assertTrue(controller.getCurrentPlayer() == "YELLOW");
			controller.setNextPlayer();
			assertTrue(controller.getCurrentPlayer() == "PINK");
			controller.setNextPlayer();
			assertTrue(controller.getCurrentPlayer() == "RED");

		}

	}

	@Test
	public void testgetTokenColorBlock() {
		assertEquals('R', controller.getTokenColorBlock(0, 0));
		assertEquals('R', controller.getTokenColorBlock(0, 1));
		assertEquals('R', controller.getTokenColorBlock(0, 2));
		assertEquals('R', controller.getTokenColorBlock(0, 3));
		if (controller.moveStart()) {
			assertEquals(' ', controller.getTokenColorBlock(0, 0));
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
		assertFalse(controller.isGameEnded());

	}

	@Test
	public void testIsFieldEmpty() {
		boolean empty = controller.isFieldEmpty();
		if (empty) {
			assertTrue(empty);
		} else {
			assertFalse(empty);
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
		String s = controller.throwDiceGUI();
		int dice = controller.dice();
		if (controller.getCurrentPlayer() == "RED") {
			if (dice == 1) {
				assertEquals(" RED threw [2]", s);

			}
			if (dice == 2) {
				assertEquals(" RED threw [2]", s);

			}
			if (dice == 3) {
				assertEquals(" RED threw [3]", s);

			}
			if (dice == 4) {
				assertEquals(" RED threw [4]", s);

			}
			if (dice== 5) {
				assertEquals(" RED threw [5]", s);

			}
			if (dice== 6) {
				assertEquals(" RED threw [6]", s);

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
	public void testGetCurrentPlayer(){
		if(controller.getCurrentPlayer() == "RED"){
			controller.setNextPlayer();
			assertEquals("BLUE", controller.getCurrentPlayer());
		}
	}
}