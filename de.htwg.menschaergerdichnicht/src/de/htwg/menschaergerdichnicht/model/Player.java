package de.htwg.menschaergerdichnicht.model;

import de.htwg.menschaergerdichnicht.state.IState;
import de.htwg.menschaergerdichnicht.state.StatePlayer0;

public class Player implements IPlayer, IState {

	private int idx;
	private String name;
	private char color;
	private IState state;

	public Player() {
		state = new StatePlayer0();
	}

	public Player(int idx, String name, char color) {
		this.idx = idx;
		this.name = name;
		this.color = color;

	}

	@Override
	public Player setcurrentplayer() {
		return state.currentPlayer(this);

	}

	@Override
	public int getIdx() {
		return idx;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public char getColor() {
		return color;
	}

	@Override

	public IState getState() {
		return state;
	}

	@Override

	public void setState(IState state) {
		this.state = state;
	}

	@Override
	public Player currentPlayer(Player player) {
		return player;
	}

}
