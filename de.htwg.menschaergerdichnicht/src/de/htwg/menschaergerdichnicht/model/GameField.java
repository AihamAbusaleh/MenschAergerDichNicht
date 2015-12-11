package de.htwg.menschaergerdichnicht.model;

public class GameField {

	private token[] gamefield;
	private token[][] block;
	private token[][] house;
	private final char color[] = { 'R', 'B', 'G', 'S' };
	private final int startposition[] = { 30, 0, 10, 20 };

	private final int POSITIONS = 40;
	private final int PLAYER = 4;
	private final int HOUSESIZE = 4;
	private final int BLOCKSIZE = 4;

	private class token {
		public char color;

		public token(char color) {
			this.color = color;
		}
	}

	public GameField() {
		gamefield = new token[POSITIONS];
		block = new token[PLAYER][BLOCKSIZE];
		house = new token[PLAYER][HOUSESIZE];

		fillGameField();
		fillHouse();
		fillBlock();
	}
	
	private void fillBlock() {
		for (int i = 0; i < PLAYER; i++)
			for (int k = 0; k < BLOCKSIZE; k++)
				block[i][k] = new token(color[i]);
	}

	private void fillHouse() {
		for (int i = 0; i < PLAYER; i++)
			for (int k = 0; k < HOUSESIZE; k++)
				house[i][k] = new token(' ');
	}

	private void fillGameField() {
		for (int i = 0; i < POSITIONS; i++) {
			gamefield[i] = new token('x');
		}
	}

	public char getStoneColor(int idx) { // getestet
		return gamefield[idx].color;
	}

	public char getStoneColorBlock(int player, int idx) { // getestet
		return block[player][idx].color;
	}

	public char getStoneColorHouse(int player, int idx) { // getestet
		return house[player][idx].color;
	}

	public boolean setStone(int idx, char color) { // getestet
		idx = idx % POSITIONS;

		if (0 > idx || gamefield[idx].color == color)
			return false;

		gamefield[idx].color = color;

		return true;
	}

	public int whichPlayerOnIdx(int idx, char color) { // which player is on the
														// idx
		idx = idx % POSITIONS;

		for (int i = 0; i < PLAYER; i++)
			if (this.color[i] == gamefield[idx].color)
				return i;

		return -1;
	}

	public int enemyInStart(int player, char color) { // getestet wer steht
														// wird// geschmi�en

			if (gamefield[startposition[player]].color != color && gamefield[startposition[player]].color != 'x'){
				if(gamefield[startposition[player]].color == 'R')
					return 0;
				if(gamefield[startposition[player]].color == 'B')
					return 1;
				if(gamefield[startposition[player]].color == 'G')
					return 2;
				if(gamefield[startposition[player]].color == 'S')
					return 3;
				
			}
	
		return -1;
	}

	public boolean getStoneOutOfBlock(int player) { // getestet
		for (int i = 0; i < BLOCKSIZE; i++) {
			if (block[player][i].color == color[player] && gamefield[startposition[player]].color != color[player]) {
				block[player][i].color = ' ';
				gamefield[startposition[player]].color = color[player];
				return true;
			}
		}
		return false;
	}

	public boolean setStoneBackInBlock(int player) { // getestet
		for (int i = 3; i >= 0; i--) {
			if (block[player][i].color != color[player]) {
				block[player][i].color = color[player];
				//color[player] = 'x';
				return true;
			}
		}
		return false;
	}

	public boolean setStoneInHouse(int player, int idx) { // getestet
		idx = (idx - startposition[player]) % 40;

		if (0 > idx || idx > 3 || house[player][idx].color == color[player]){
		//	System.out.println("hier stimmt was nicht");
			return false;}

		house[player][idx].color = color[player];
		return true; 
	}

	public int stoneOnGamefield(int player) { // getestet
		int count = 0;
		for (int idx = 0; idx < POSITIONS; idx++)
			if (gamefield[idx].color == color[player])
				count++;

		return count;
	}

	public boolean isStoneInBlock(int player) { // getestet
		boolean stone = false;
		for (int i = 0; i < BLOCKSIZE; i++)
			stone = stone || block[player][i].color == color[player];
		return stone;
	}

	public boolean isGameEnded() { // getestet
		boolean win = true;
		for (int player = 0; player < PLAYER; player++) {
			for (int idx = 0; idx < HOUSESIZE; idx++)
				win = win && house[player][idx].color == color[player];
			if (win)
				return true;
		}
		return false;
	}

	public boolean color(int player, int idx) {
		return gamefield[idx % POSITIONS].color == color[player];
	}

	public boolean isStartFree(int player) { // getestet
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
