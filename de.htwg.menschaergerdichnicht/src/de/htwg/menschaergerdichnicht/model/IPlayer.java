package de.htwg.menschaergerdichnicht.model;

import de.htwg.menschaergerdichnicht.state.IState;

public interface IPlayer {
	/**
	 * This function sets the state of the currentplayer
	 */
	Player setcurrentplayer();

	/**
	 * This function returns the Index of a token. The token can be placed on
	 * the gamefile, on the playerblock(startposition) or on the playerhouse
	 * (goal)
	 */
	int getIdx();

	/**
	 * This function returns the name of a player.
	 */
	String getName();

	/**
	 * This function returns the color of a token.
	 */
	char getColor();

	/**
	 * This function returns the state
	 * 
	 */
	IState getState();

	/**
	 * This function sets the state
	 * 
	 * @param state
	 */

	void setState(IState state);

}
