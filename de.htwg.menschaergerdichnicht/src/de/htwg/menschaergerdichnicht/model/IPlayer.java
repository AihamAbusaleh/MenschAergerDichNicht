package de.htwg.menschaergerdichnicht.model;


public interface IPlayer {

	/*
	 * This function return the Index of a token. The token
	 * can be placed on the gamefile, on the playerblock(startposition)
	 * or on the playerhouse (goal)
	 */
	
	int getIdx();
	
	/*
	 * This function return the name of a player.
	 */
	String getName();
	
	
	/*
	 * This function return the color of a token.
	 */
	char getColor();
	
	
	
}
