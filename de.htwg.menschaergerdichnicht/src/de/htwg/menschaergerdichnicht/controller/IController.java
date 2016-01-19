package de.htwg.menschaergerdichnicht.controller;

public interface IController {

	boolean moveStart();

	/**
	 * This function check if the stone from currentPlayer can leave the block
	 */
	void stoneCanOut();

	/**
	 * This function return true if no stone from currentPlayer on the field
	 */
	boolean isFieldEmpty();

	/**
	 * This function controlls the move option from each token. It checks when
	 * the player can move out of his house or a player can move his token on
	 * the gamefield.
	 * 
	 * @param int
	 *            idx is the position of the stone
	 */

	boolean move(int idx);

	/**
	 * This function check if the token of a player can move out of his
	 * startblock.
	 */
	boolean getOutOfBlock();

	/**
	 * This function simulate a normel dice. It returns a int number between 1
	 * and 6.
	 */
	int dice();

	/**
	 * This function checks if the game is ended. It is ended when one of the
	 * player get all his four stones in the house. It returns true when one of
	 * the players finish and win the game.
	 */
	boolean isGameEnded();

	/**
	 * This function set the next State to the next Player.
	 */
	void setNextPlayer();

	/**
	 * This function return the stonecolor of a player. The stone is on the
	 * gamefield.
	 * 
	 * @param idx
	 *            the index of current player
	 */
	char getTokenColor(int idx);

	/**
	 * This function return the color of the player.
	 * 
	 * @param player
	 *            is the current one
	 * @param idx
	 *            is the index of the block from 0, 1,2,3
	 */
	char getTokenColorBlock(int player, int idx);

	/**
	 * This function return the color of house.
	 * 
	 * @param player
	 *            is the current one
	 * @param idx
	 *            is the index of the house from 0, 1,2,3
	 */
	char getTokenColorHouse(int player, int idx);

	/**
	 * This function calls the command pattern for an undo function. It also
	 * update the gamefiled with the observer pattern
	 */
	void undo();

	/**
	 * This function create steps by saving them. So the player is able to undo
	 * a turn
	 */
	void createSteps();

	/**
	 * This function checks when a player did a full round on the gamefield with
	 * his token.
	 * 
	 * @param idx
	 *            is the index on the field, where the player is
	 */
	boolean rounded(int idx);

	/**
	 * This function give the current player and its thrown dice
	 */
	String wurfeln();

}
