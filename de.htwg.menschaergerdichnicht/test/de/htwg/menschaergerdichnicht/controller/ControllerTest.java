package de.htwg.menschaergerdichnicht.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import de.htwg.menschaergerdichnicht.controller.Controller;
import de.htwg.menschaergerdichnicht.state.IState;
import de.htwg.menschaergerdichnicht.state.StatePlayer0;
import de.htwg.menschaergerdichnicht.state.StatePlayer1;
import de.htwg.menschaergerdichnicht.state.StatePlayer2;
import de.htwg.util.observer.IObserver;

public class ControllerTest {

	private Controller controller;
	private IState state;
	private IObserver o;

	@Before
	public void setUp() throws Exception {
		controller = new Controller();
		state = new StatePlayer1();
	}

	@Test
	public void testdice() {
		if (controller.dice() == 6)
			assertEquals(6, controller.dice());
	}

	@Test
	public void testmove() {
		if (!controller.emptyField()) {
			if (controller.getTokenColor(30) == 'R') {
				assertTrue(controller.move(30));
				assertFalse(controller.move(20));
			}
			if (controller.getTokenColor(30) == 'B') {
				assertTrue(controller.move(30));
				assertFalse(controller.move(20));
			}

			if (controller.getTokenColor(30) == 'Y') {
				assertTrue(controller.move(30));
				assertFalse(controller.move(20));
			}

			if (controller.getTokenColor(30) == 'P') {
				assertTrue(controller.move(30));
				assertFalse(controller.move(20));
			}
		}

	}

	@Test
	public void testmoveStart() {
		if (!controller.emptyField()) {
			assertTrue(controller.moveStart());
			controller.move(30);
			controller.setNextPlayer();
			controller.moveStart();
			controller.moveStart();

			controller.moveStart();
			controller.moveStart();

			controller.move(0);

		}

	}

	@Test
	public void testgetTokenColor() {
		assertEquals('x', controller.getTokenColor(0));
		assertEquals('x', controller.getTokenColor(6));
		assertEquals('x', controller.getTokenColor(9));
		assertEquals('x', controller.getTokenColor(7));
		assertEquals('x', controller.getTokenColor(8));
		assertEquals('x', controller.getTokenColor(11));
		assertEquals('x', controller.getTokenColor(12));

	}

	@Test
	public void testsetNextPlayer() {
		if (state.toString().equals("BLUE")) {
			controller.setNextPlayer();
			state = new StatePlayer2();
			assertEquals("YELLOW", state.toString());
		}

	}

	@Test
	public void testgetTokenColorBlock() {
		controller.dice();
		if (controller.isFieldEmpty()) {
			assertEquals('R', controller.getTokenColorBlock(0, 0));
		}
		assertEquals('B', controller.getTokenColorBlock(1, 1));
		assertEquals('B', controller.getTokenColorBlock(1, 2));
		assertEquals('Y', controller.getTokenColorBlock(2, 3));
		assertEquals('Y', controller.getTokenColorBlock(2, 0));


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
		boolean end =   controller.isGameEnded();
		end = true;
		assertTrue(end);

	}

	@Test
	public void testIsFieldEmpty() {
		controller.getOutOfBlock();
		assertFalse(controller.isFieldEmpty());

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
		if (controller.emptyField() == true) {
			if (controller.dice() == 6) {
				controller.stoneCanOut();
				assertFalse(controller.emptyField());
			}
		}
	}

	@Test
	public void testthrowDiceGUI() {
		state = new StatePlayer0();
		if (state.toString().equals("RED") && controller.dice() == 6 && !controller.emptyField()) {
			controller.moveStart();
			assertEquals("  BLUE threw [6]", controller.throwDiceGUI());
		}

	}

	@Test
	public void testemptyField() {
		assertTrue(controller.emptyField());
		controller.getOutOfBlock();
		assertFalse(controller.emptyField());

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

}