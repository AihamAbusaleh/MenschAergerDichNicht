package de.htwg.menschaergerdichnicht.model;

public class GameField {

	private Token[] myGameField;
	private Token[][] block;
	private Token[][] house;
	private static final char[] color = { 'R', 'B', 'Y', 'P' };
	private static final int[] STARTPOSITION = { 30, 0, 10, 20 };

	private static final int POSITIONS = 40;
	private static final int PLAYERS = 4;
	private static final int HOUSESIZE = 4;
	private static final int BLOCKSIZE = 4;

	public class Token {
		private char color;

		public Token(char color) {
			this.color = color;
		}
	}

	public GameField() {
		myGameField = new Token[POSITIONS];
		block = new Token[PLAYERS][BLOCKSIZE];
		house = new Token[PLAYERS][HOUSESIZE];

		fillGameField();
		fillHouse();
		fillBlock();
	}

	private void fillBlock() {
		for (int i = 0; i < PLAYERS; i++)
			for (int k = 0; k < BLOCKSIZE; k++)
				block[i][k] = new Token(color[i]);
	}

	private void fillHouse() {
		for (int i = 0; i < PLAYERS; i++)
			for (int k = 0; k < HOUSESIZE; k++)
				house[i][k] = new Token(' ');
	}

	private void fillGameField() {
		for (int i = 0; i < POSITIONS; i++) {
			myGameField[i] = new Token('x');
		}
	}

	public char getStoneColor(int idx) {
		return myGameField[idx].color;
	}

	public char getStoneColorBlock(int player, int idx) {
		return block[player][idx].color;
	}

	public char getStoneColorHouse(int player, int idx) {
		return house[player][idx].color;
	}

	public boolean setStone(int idx, char color) {
		int newIdx;
		newIdx = idx % POSITIONS;

		if (0 > newIdx || myGameField[newIdx].color == color)
			return false;

		myGameField[newIdx].color = color;

		return true;
	}

	public int whichPlayerOnIdx(int idx) {

		int newIdx;
		newIdx = idx % POSITIONS;
		for (int i = 0; i < PLAYERS; i++)
			if (GameField.color[i] == myGameField[newIdx].color)
				return i;

		return -1;
	}

	public int enemyInStart(int player, char color) {

		if (myGameField[STARTPOSITION[player]].color != color && myGameField[STARTPOSITION[player]].color != 'x') {
			return enemyInStart(player);

		}

		return -1;
	}
	private int enemyInStart(int player){
		if (myGameField[STARTPOSITION[player]].color == 'R')
			return 0;
		if (myGameField[STARTPOSITION[player]].color == 'B')
			return 1;
		if (myGameField[STARTPOSITION[player]].color == 'Y')
			return 2;
		if (myGameField[STARTPOSITION[player]].color == 'P')
			return 3;
		return -1;
	}
	public boolean getStoneOutOfBlock(int player) {
		for (int i = 0; i < BLOCKSIZE; i++) {
			if (block[player][i].color == color[player] && myGameField[STARTPOSITION[player]].color != color[player]) {
				block[player][i].color = ' ';
				myGameField[STARTPOSITION[player]].color = color[player];
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
		int newIdx;

		newIdx = (idx - STARTPOSITION[player]) % 40;

		if (0 > newIdx || newIdx > 3 || house[player][newIdx].color == color[player]) {
			return false;
		}

		house[player][newIdx].color = color[player];
		return true;
	}

	public int stoneOnGamefield(int player) {
		int count = 0;
		for (int idx = 0; idx < POSITIONS; idx++)
			if (myGameField[idx].color == color[player])
				count++;

		return count;
	}

	public boolean isStoneInBlock(int player) {
		boolean stone = false;
		for (int i = 0; i < BLOCKSIZE; i++)
			stone = stone || block[player][i].color == color[player];
		return stone;
	}

	public boolean isGameEnded() {
		boolean win = true;
		for (int player = 0; player < PLAYERS; player++) {
			for (int idx = 0; idx < HOUSESIZE; idx++)
				win = win && house[player][idx].color == color[player];
			if (win)
				return true;
		}
		return false;
	}

	public boolean color(int player, int idx) {
		return myGameField[idx % POSITIONS].color == color[player];
	}

	public boolean isStartFree(int player) {
		return myGameField[STARTPOSITION[player]].color != color[player];
	}

	public boolean isRounded(int player, int idx, int dice) {

		if (idx < STARTPOSITION[player] && idx + dice >= STARTPOSITION[player])
			return true;

		if (player == 1 && idx + dice > 39)
			return true;

		return false;
	}
}
