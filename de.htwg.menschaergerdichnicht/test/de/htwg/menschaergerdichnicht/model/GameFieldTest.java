package de.htwg.menschaergerdichnicht.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.menschaergerdichnicht.model.GameField;

public class GameFieldTest {

	private GameField gamefield;

	@Before
	public void setUp() {
		gamefield = new GameField();
	}

	@Test
	public void testgetStoneColor() {
		gamefield.setStone(0, 'B');
 		assertEquals('x', gamefield.getStoneColor(5));
		assertEquals('B', gamefield.getStoneColor(0));

	}

	@Test
	public void testgetStoneColorBlock() {
		assertEquals('R', gamefield.getStoneColorBlock(0, 0));

	}

	@Test
	public void testgetStoneColorHouse() {
		assertEquals(' ', gamefield.getStoneColorHouse(1, 1));
	}

	@Test
	public void testsetStone() {
		assertFalse(gamefield.setStone(-5, 'B'));
		assertTrue(gamefield.setStone(5, 'B'));
 	}

	@Test
	public void testIsStoneInBlock() {  
		assertTrue(gamefield.isStoneInBlock(0));

 	}

	@Test
	public void teststoneOnGamefield() {
		assertEquals(0, 0);
		gamefield.getStoneOutOfBlock(0);

		assertEquals(1, 1);
 
	}

	@Test
	public void testisGameEnded() {  

		gamefield.setStoneInHouse(0, 30);
		gamefield.setStoneInHouse(0, 31);
		gamefield.setStoneInHouse(0, 32);
		gamefield.setStoneInHouse(0, 33);
		assertTrue(gamefield.isGameEnded());
 
	}

	@Test
	public void testisStartFree() {

 
		gamefield.getStoneOutOfBlock(3);
		assertFalse(gamefield.isStartFree(3));
		assertTrue(gamefield.isStartFree(0));

	}

	@Test
	public void testEnemyInStart() {
		assertEquals(-1, gamefield.enemyInStart(0, 'B'));
		gamefield.setStone(30, 'G');

		assertEquals(2, gamefield.enemyInStart(0, 'B'));
	}

	@Test
	public void testsetStoneBackInBlock() {

		assertFalse(gamefield.setStoneBackInBlock(0));
		gamefield.getStoneOutOfBlock(0);
		assertTrue(gamefield.setStoneBackInBlock(0));

	}

	@Test
	public void testsetStoneInHouse() {  
		assertFalse(gamefield.setStoneInHouse(0, 4));
		assertFalse(gamefield.setStoneInHouse(0, -1));
		assertTrue(gamefield.setStoneInHouse(0, 30));
		assertTrue(gamefield.setStoneInHouse(0, 31));
		assertTrue(gamefield.setStoneInHouse(0, 32));
		assertTrue(gamefield.setStoneInHouse(0, 33));
		assertFalse(gamefield.setStoneInHouse(0, 34));

		assertFalse(gamefield.setStoneInHouse(2, 0));
	}

	@Test
	public void testgetStoneOutOfBlock() {
 
		assertTrue(gamefield.getStoneOutOfBlock(0));
		assertFalse(gamefield.getStoneOutOfBlock(0));

	}
	
	@Test
	public void testIsRounded(){
		gamefield.setStone(27, 'R');
		assertFalse(gamefield.isRounded(0, 27, 1));
		assertTrue(gamefield.isRounded(0, 27, 6));
		
	}
	@Test
	public void testColor(){
		gamefield.setStone(20, 'R');
		assertTrue(gamefield.color(0, 20));
		assertFalse(gamefield.color(1, 20));

	}
	@Test
	public void textwhichPlayerOnIdx(){
		gamefield.setStone(5, 'R');
		assertEquals(0, gamefield.whichPlayerOnIdx(5, 'R'));
 
	}
}
