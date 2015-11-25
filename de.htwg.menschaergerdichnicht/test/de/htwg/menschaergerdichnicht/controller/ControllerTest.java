package de.htwg.menschaergerdichnicht.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.menschaergerdichnicht.controller.Controller;
import de.htwg.menschaergerdichnicht.model.Player;

public class ControllerTest {

	private Controller controller;

	@Before
	public void setUp()throws Exception {
		controller = new Controller();
	}

	@Test
	public void testdice() {
		controller.dice();
	}

	@Test
	public void testmove() {
		controller.dice();
		if (controller.moveStart()) {
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

		} else {
			assertFalse(controller.moveStart());

		}
	}

	@Test
	public void testmoveStart() {
		controller.dice();
		if (controller.moveStart()) {
			assertTrue(controller.moveStart());
		} else {
			assertFalse(controller.moveStart());
		}

	}

	@Test
	public void testgetTokenColor() {
		assertEquals('x', controller.getTokenColor(0));
	}

	@Test
	public void testgetTokenColorBlock() {
		assertEquals('R', controller.getTokenColorBlock(0, 0));
	}

	@Test
	public void testgetTokenColorHouse() {
		assertEquals(' ', controller.getTokenColorHouse(0, 0));
	}

	@Test
	public void testgetOutOfBlock() {
		assertTrue(controller.getOutOfBlock());
		assertFalse(controller.getOutOfBlock());
	}

}