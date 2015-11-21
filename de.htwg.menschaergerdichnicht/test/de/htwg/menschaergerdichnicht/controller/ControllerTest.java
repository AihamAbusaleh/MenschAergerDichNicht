package de.htwg.menschaergerdichnicht.controller;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.menschaergerdichnicht.controller.Controller;

public class ControllerTest {
	
	private Controller controller;
	
	@Before
	public void setUp() {
		controller = new Controller();
	}
	
	@Test
	public void testdice() {
		controller.dice();
	}
	
	@Test
	public void testmove() {
		controller.dice();
		assertTrue(controller.move(0));
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