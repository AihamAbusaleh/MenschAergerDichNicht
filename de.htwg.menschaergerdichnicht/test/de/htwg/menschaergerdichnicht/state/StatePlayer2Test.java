package de.htwg.menschaergerdichnicht.state;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.menschaergerdichnicht.model.Player;
 
public class StatePlayer2Test {

	private StatePlayer2 state2;
	private Player player;
 
	@Before
	public void setUp() {
		state2 = new StatePlayer2();
		player = new Player(2, "Gelb", 'G');
 	}

	@Test
	public void testCurrentPlayer() {
		assertEquals('G', player.getColor()); 
		assertNotEquals('B', player.getColor() );
 		player.equals(state2.currentPlayer(player)); 
		  

 	}
	@Test
	public void testToString() {
     assertEquals("Gelb", state2.toString()); 
     assertNotEquals("Blau", state2.toString()); 

 	}
}
