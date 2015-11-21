package de.htwg.menschaergerdichnicht.model;



public class Player implements IPlayer {

	private int idx;
	private String name;
	private char color;
	
	public Player(int idx, String name, char color) {
		this.idx = idx;
		this.name = name;
		this.color = color;
	}
	
	public int getIdx() {
		return idx;
	}
	
	public String getName() {
		return name;
	}

	public char getColor() {
		return color;
	}

}
