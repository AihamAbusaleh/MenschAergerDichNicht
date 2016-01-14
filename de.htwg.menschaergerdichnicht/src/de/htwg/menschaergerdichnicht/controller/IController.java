package de.htwg.menschaergerdichnicht.controller;


public interface IController {

	/*
	 * This function controlls the move option from each token. It checks when
	 * the player can move out of his house or a player can move his token on
	 * the gamefield.
	 */

	boolean move(int idx);

	/*
	 * This function check if the token of a player can move out of his
	 * startblock.
	 */
	boolean getOutOfBlock();

	/*
	 * This function return the stonecolor of a player. The stone is on the
	 * gamefield.
	 */
	char getTokenColor(int idx);

	/*
	 * This function return the color of the player.
	 */
	char getTokenColorBlock(int player, int idx);

	/*
	 * This function return the color of house.
	 */
	char getTokenColorHouse(int player, int idx);

	/*
	 * This function return true if no stone from currentPlayer on the field
	 */
	boolean isFieldEmpty();

	/*
	 * This function check if the stone from currentPlayer can leave the block
	 */
	void stonCanOut();
	
	void undo();
 	void createSteps();

}
