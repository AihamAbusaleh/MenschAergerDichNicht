package de.htwg.menschaergerdichnicht.state;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.menschaergerdichnicht.model.Player;
 
public class StatePlayer3Test {

	private StatePlayer3 state3;
	private Player player;
 
	@Before
	public void setUp() {
		state3 = new StatePlayer3();
		player = new Player(3, "Pink", 'P');
 	}

	@Test
	public void testCurrentPlayer() {
		assertEquals('P', player.getColor()); 
		assertNotEquals('B', player.getColor() );
 		player.equals(state3.currentPlayer(player)); 
		  

 	}
	@Test
	public void testToString() {
     assertEquals("Pink", state3.toString()); 
     assertNotEquals("Blau", state3.toString()); 

 	}
}
