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
		// assertEquals(' ', gamefield.getStoneColor(50));
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
		// assertTrue(gamefield.stoneOnGamefield(1));
	}

	@Test
	public void testIsStoneInBlock() { // false wenn alle draußen sind
		assertTrue(gamefield.isStoneInBlock(0));

		// assertFalse(gamefield.isStoneInBlock(0));
	}

	@Test
	public void teststoneOnGamefield() {
		assertEquals(0, 0);
		gamefield.getStoneOutOfBlock(0);

		assertEquals(1, 1);
		// assertEquals("Es sind 3 spieler aufm Feld",4 ,4);

	}

	@Test
	public void testisGameEnded() { // ok

		gamefield.setStoneInHouse(0, 30);
		gamefield.setStoneInHouse(0, 31);
		gamefield.setStoneInHouse(0, 32);
		gamefield.setStoneInHouse(0, 33);
		assertTrue(gamefield.isGameEnded());

		// gamefield.setStoneInHouse(1, 0);
		// gamefield.setStoneInHouse(1, 1);
		// assertFalse(gamefield.isGameEnded());
		//
	}

	@Test
	public void testisStartFree() {

		// gamefield.setStone(20, 'S');
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
	public void testsetStoneInHouse() { // ok
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

		// gamefield.setStone(30, 'R');
		// assertFalse(gamefield.getStoneOutOfBlock(0));
		assertTrue(gamefield.getStoneOutOfBlock(0));
		assertFalse(gamefield.getStoneOutOfBlock(0));

	}
}
