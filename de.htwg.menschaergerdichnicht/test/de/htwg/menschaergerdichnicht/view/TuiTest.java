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
		

	}
	
	@Test
	public void testnoInput(){
		 if(c.dice() == 6){
			 assertTrue(tui.noInput());
		 }else{
			 assertFalse(tui.noInput()); 
		 }
	}
	@Test
	public void testhandleInput(){
		 assertFalse(tui.handleInput("m")); 
		 
		 if(c.getOutOfBlock() &&  c.getTokenColor(30) == 'R'){
			 assertTrue(tui.handleInput("30")); 
			  
		 }
		 if( c.getOutOfBlock() &&  c.getTokenColor(0) == 'B'){
			 assertTrue(tui.handleInput("0")); 
			  
		 }
		 if( c.getOutOfBlock() &&  c.getTokenColor(10) == 'Y'){
			 assertTrue(tui.handleInput("10")); 
			  
		 }
		 if(c.getOutOfBlock() &&  c.getTokenColor(20) == 'P'){
			 assertTrue(tui.handleInput("20")); 
			  
		 }
	}
	
 
}
