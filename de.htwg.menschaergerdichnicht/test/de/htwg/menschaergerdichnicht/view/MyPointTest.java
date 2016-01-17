package de.htwg.menschaergerdichnicht.view;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import de.htwg.menschaergerdichnicht.controller.Controller;

public class MyPointTest {
	Controller c;
	MyPoint point;

	@Before
	public void setUp() throws Exception {
		c = new Controller();
		point = new MyPoint(4, 6, 30);

	}

	@Test
	public void testGetx() {
		assertEquals(4, point.getX());

	}

	@Test
	public void testSetIdx() {
		point.setIdx(5);
		assertNotEquals(1, point.getIdx());
		assertEquals(5, point.getIdx());

	}

	@Test
	public void testGety() {
		assertEquals(6, point.getY());

	}
}
