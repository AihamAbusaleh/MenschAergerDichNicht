package de.htwg.menschaergerdichnicht.state;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.menschaergerdichnicht.model.Player;
 
public class StatePlayer0Test {

	private StatePlayer0 state0;
	private Player player;
 
	@Before
	public void setUp() {
		state0 = new StatePlayer0();
		player = new Player(0, "RED", 'R');
 	}

	@Test
	public void testCurrentPlayer() {
		assertEquals('R', player.getColor()); 
		assertNotEquals('B', player.getColor() );
 		player.equals(state0.currentPlayer(player)); 
		  

 	}
	@Test
	public void testToString() {
     assertEquals("RED", state0.toString()); 
     assertNotEquals("BLUE", state0.toString()); 

 	}
}
