package de.htwg.menschaergerdichnicht.state;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.menschaergerdichnicht.model.Player;
 
public class StatePlayer1Test {

	private StatePlayer1 state1;
	private Player player;
 
	@Before
	public void setUp() {
		state1 = new StatePlayer1();
		player = new Player(1, "Blau", 'B');
 	}

	@Test
	public void testCurrentPlayer() {
		assertEquals('B', player.getColor()); 
		assertNotEquals('R', player.getColor() );
 		player.equals(state1.currentPlayer(player)); 
		  

 	}
	@Test
	public void testToString() {
     assertEquals("Blau", state1.toString()); 
     assertNotEquals("Rot", state1.toString()); 

 	}
}
