package de.htwg.menschaergerdichnicht.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.menschaergerdichnicht.controller.Controller;

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

			if (controller.getTokenColor(2) == 'Y') {
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
		if (!controller.isFieldEmpty()) {
			controller.moveStart();
			assertTrue(controller.moveStart());
			controller.move(30);
			controller.setNextPlayer();
			controller.moveStart();
			controller.move(0);

		} 

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
			assertEquals('Y', controller.getTokenColor(2));
		}

		if (controller.getTokenColor(2) == 'Y') {
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
	public void testRounded(){
		
		assertFalse(controller.rounded(11));
	}
	@Test
	public void testIsGameEnded(){
		assertFalse(controller.isGameEnded()); 
		
	}
	@Test
	public void testIsFieldEmpty(){
		assertTrue(controller.isFieldEmpty()); 
		controller.getOutOfBlock();
		assertFalse(controller.isFieldEmpty()); 

	}
	@Test 
	public void testDice(){
		int dice = controller.dice();
		if(dice == 5)
			assertEquals(5, dice); 
	}
	
	

}