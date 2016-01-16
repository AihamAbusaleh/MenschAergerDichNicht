package de.htwg.menschaergerdichnicht.model;

public class GameField {

	private Token[] gamefield;
	private Token[][] block;
	private Token[][] house;
	private final char color[] = { 'R', 'B', 'G', 'P' };
	private final static int startposition[] = { 30, 0, 10, 20 };

	private final static int Positions = 40;
	private final static int Players = 4;
	private final static int HousSize = 4;
	private final static int BlockSize = 4;

	public class Token {
		public char color;

		public Token(char color) {
			this.color = color;
		}
	}

	public GameField() {
		gamefield = new Token[Positions];
		block = new Token[Players][BlockSize];
		house = new Token[Players][HousSize];

		fillGameField();
		fillHouse();
		fillBlock();
	}

	private void fillBlock() {
		for (int i = 0; i < Players; i++)
			for (int k = 0; k < BlockSize; k++)
				block[i][k] = new Token(color[i]);
	}

	private void fillHouse() {
		for (int i = 0; i < Players; i++)
			for (int k = 0; k < HousSize; k++)
				house[i][k] = new Token(' ');
	}

	private void fillGameField() {
		for (int i = 0; i < Positions; i++) {
			gamefield[i] = new Token('x');
		}
	}

	public char getStoneColor(int idx) {
		return gamefield[idx].color;
	}

	public char getStoneColorBlock(int player, int idx) {
		return block[player][idx].color;
	}

	public char getStoneColorHouse(int player, int idx) {
		return house[player][idx].color;
	}

	public boolean setStone(int idx, char color) {
		idx = idx % Positions;

		if (0 > idx || gamefield[idx].color == color)
			return false;

		gamefield[idx].color = color;

		return true;
	}

	public int whichPlayerOnIdx(int idx) {

		idx = idx % Positions;

		for (int i = 0; i < Players; i++)
			if (this.color[i] == gamefield[idx].color)
				return i;

		return -1;
	}

	public int enemyInStart(int player, char color) {

		if (gamefield[startposition[player]].color != color && gamefield[startposition[player]].color != 'x') {
			if (gamefield[startposition[player]].color == 'R')
				return 0;
			if (gamefield[startposition[player]].color == 'B')
				return 1;
			if (gamefield[startposition[player]].color == 'G')
				return 2;
			if (gamefield[startposition[player]].color == 'P')
				return 3;

		}

		return -1;
	}

	public boolean getStoneOutOfBlock(int player) {
		for (int i = 0; i < BlockSize; i++) {
			if (block[player][i].color == color[player] && gamefield[startposition[player]].color != color[player]) {
				block[player][i].color = ' ';
				gamefield[startposition[player]].color = color[player];
				return true;
			}
		}
		return false;
	}

	public boolean setStoneBackInBlock(int player) {
		for (int i = 3; i >= 0; i--) {
			if (block[player][i].color != color[player]) {
				block[player][i].color = color[player];
				return true;
			}
		}
		return false;
	}

	public boolean setStoneInHouse(int player, int idx) {
		idx = (idx - startposition[player]) % 40;

		if (0 > idx || idx > 3 || house[player][idx].color == color[player]) {
			return false;
		}

		house[player][idx].color = color[player];
		return true;
	}

	public int stoneOnGamefield(int player) {
		int count = 0;
		for (int idx = 0; idx < Positions; idx++)
			if (gamefield[idx].color == color[player])
				count++;

		return count;
	}

	public boolean isStoneInBlock(int player) {
		boolean stone = false;
		for (int i = 0; i < BlockSize; i++)
			stone = stone || block[player][i].color == color[player];
		return stone;
	}

	public boolean isGameEnded() {
		boolean win = true;
		for (int player = 0; player < Players; player++) {
			for (int idx = 0; idx < HousSize; idx++)
				win = win && house[player][idx].color == color[player];
			if (win)
				return true;
		}
		return false;
	}

	public boolean color(int player, int idx) {
		return gamefield[idx % Positions].color == color[player];
	}

	public boolean isStartFree(int player) {
		return gamefield[startposition[player]].color != color[player];
	}

	public boolean isRounded(int player, int idx, int dice) {

		if (idx < startposition[player] && idx + dice >= startposition[player])
			return true;

		if (player == 1 && idx + dice > 39)
			return true;

		return false;
	}
}
