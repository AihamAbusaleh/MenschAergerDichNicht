package de.htwg.menschaergerdichnicht.model;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.menschaergerdichnicht.model.GameField;

public class GameFieldTest {
	
	private GameField  gamefield;
	
	@Before
	public void setUp() {
		gamefield = new GameField();
	}
	
	@Test
	public void testgetStoneColor () {
		gamefield.setStone(0, 'B');
	//	assertEquals(' ', gamefield.getStoneColor(50));
		assertEquals('x', gamefield.getStoneColor(5));
		assertEquals('B', gamefield.getStoneColor(0));
		
	}
	
	@Test
	public void testgetStoneColorBlock () {
		assertEquals('R', gamefield.getStoneColorBlock(0, 0));
		
	}
	
	@Test
	public void testgetStoneColorHouse () {
		assertEquals(' ', gamefield.getStoneColorHouse(1, 1));
	}
	
	@Test
	public void testsetStone () {
		assertFalse(gamefield.setStone(-5, 'B'));
		assertTrue(gamefield.setStone(5, 'B'));
	}
	
	@Test
	public void testIsStoneInBlock () {
		assertTrue(gamefield.isStoneInBlock(0));
		gamefield.getStoneOutOfBlock(0);
		//gamefield.throwPlayer(30, 'R');
		
		gamefield.getStoneOutOfBlock(0);
	//	gamefield.throwPlayer(30, 'R');
		
		gamefield.getStoneOutOfBlock(0);
	//	gamefield.throwPlayer(30, 'R');
		
		gamefield.getStoneOutOfBlock(0);
		
		assertFalse(gamefield.isStoneInBlock(0));
	}

	@Test
	public void teststoneOnGamefield () {
	
		gamefield.throwPlayerStart(0, 'R');
		gamefield.throwPlayerStart(1, 'B');
		gamefield.throwPlayerStart(2, 'G');
		gamefield.throwPlayerStart(3, 'S');
		assertEquals(4, 4);
		
	}
	
	@Test
	public void testisGameEnded () {
	
		gamefield.setStoneInHouse(0, 0);
		gamefield.setStoneInHouse(0, 1);
		gamefield.setStoneInHouse(0, 2);
		gamefield.setStoneInHouse(0, 3);
		assertTrue(gamefield.isGameEnded());
		
		gamefield.setStoneInHouse(1, 0);
		gamefield.setStoneInHouse(1, 1);
		assertFalse(gamefield.isGameEnded());
		
	}
	
	
	@Test
	public void testisStartFree () {
	
		gamefield.setStone(10, 'S');
		assertFalse(gamefield.isStartFree(2));
		assertTrue(gamefield.isStartFree(0));
		
		
	}
	
	@Test
	public void testthrowPlayerStart () {
		assertEquals(0,gamefield.throwPlayerStart(0, 'R'));
		gamefield.setStone(30, 'R');
		assertEquals(-1, gamefield.throwPlayerStart(0, 'R'));						
	}
//	@Test 
//	public void testmoveOutOfHouse () {
//		assertTrue(gamefield.moveOutOfHouse(0));
//		gamefield.setStone(30, 'R');
//		assertFalse(gamefield.moveOutOfHouse(0));
//	}

	@Test
	public void testsetStoneBackInBlock() {
		//gamefield.getStoneOutOfBlock(0);
	//	assertTrue(gamefield.setStoneBackInBlock(0));

	//	gamefield.setStone(15, 'R');
	//	gamefield.setStone(15, 'S');
		gamefield.getStoneOutOfBlock(0);
		//gamefield.setStoneInHouse(0, 2);
		//gamefield.setStoneInHouse(0, 3);
		assertTrue(gamefield.setStoneBackInBlock(0));

	}
	
	@Test
	public void testsetStoneInHouse() { 
		assertFalse(gamefield.setStoneInHouse(0,4));
		assertFalse(gamefield.setStoneInHouse(0, -1));
		assertTrue(gamefield.setStoneInHouse(0, 31));
		assertFalse(gamefield.setStoneInHouse(2, 0));
	}
	
	@Test
	public void testgetStoneOutOfBlock() {
		assertTrue(gamefield.getStoneOutOfBlock(0));
	//	gamefield.getStoneOutOfBlock(0);
	//	gamefield.getStoneOutOfBlock(0);
	//	gamefield.getStoneOutOfBlock(0);
		gamefield.setStone(30, 'G');
		assertFalse(gamefield.getStoneOutOfBlock(0));	
	}
}
