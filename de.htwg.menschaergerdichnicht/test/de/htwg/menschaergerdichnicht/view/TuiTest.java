package de.htwg.menschaergerdichnicht.view;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import de.htwg.menschaergerdichnicht.controller.Controller;

 
 

public class TuiTest {
 
	private TUI tui;
	private Controller c;
	
	
	@Before
	public void setUp() throws Exception {
		c = new Controller();
		tui = new TUI(c);
		c.getCurrentPlayer();
		

	}
	
	@Test
	public void testnoInput(){
		 if(c.dice() == 6 ){
			 assertTrue(tui.noInput());
		 }else{
			 assertFalse(tui.noInput()); 
		 }
	}
	@Test
	public void testhandleInput(){
		 assertFalse(tui.handleInput("m")); 
		 assertFalse(tui.handleInput("u")); 
		 if(c.getCurrentPlayer() == "RED" && c.moveStart()){
			 assertTrue(tui.handleInput("30")); 
			 c.setNextPlayer();
			  
		 }
		 if(c.getCurrentPlayer() == "BLUE" && c.moveStart()){
			 assertTrue(tui.handleInput("0")); 
			 c.setNextPlayer();

		 }
		 if(c.getCurrentPlayer() == "YELLOW" && c.moveStart()){
			 assertTrue(tui.handleInput("10")); 
			 c.setNextPlayer();

		 }
		 if(c.getCurrentPlayer() == "PINK" && c.moveStart()){
			 assertTrue(tui.handleInput("20")); 
			 c.setNextPlayer();

		 }
	}
	
 
}
