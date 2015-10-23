package model;

public class GameField {

	private token[] gamefield;
	private token[][] block;
	private token[][] house;
	private final char color[] = {'R','B','G','S'}; 
	private final int startposition[] = {30, 0, 10 ,20};
	
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
		for(int i = 0; i < PLAYER; i++)
			for(int k = 0; k < BLOCKSIZE; k++) 
				block[i][k] = new token(color[i]);
	}
	private void fillHouse() {
		for(int i = 0; i < PLAYER; i++)
			for(int k = 0; k < HOUSESIZE; k++) 
				house[i][k] = new token(' ');
	}
	private void fillGameField() {
		for(int i = 0; i < POSITIONS ; i++) {
			gamefield[i] = new token('x');
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
		idx = idx % POSITIONS;
		
		if(0 > idx || gamefield[idx].color == color)
			return false;
		
		gamefield[idx].color = color;
		
		return true;
	}
	
	public int throwPlayer(int idx, char color) {
		idx = idx % POSITIONS;

		for(int i = 0; i < PLAYER; i++)
			if(this.color[i] == gamefield[idx].color)
				return i;
		
		return -1;				
	}
	
	public int throwPlayerStart(int player, char color) {
		if(gamefield[startposition[player]].color != color)
			for(int i = 0; i < PLAYER; i++)
				if(gamefield[startposition[player]].color == color)
					return i;
		
		return -1;
	}
	
	public boolean getStoneOutOfBlock(int player) {
		for(int i = 0; i < BLOCKSIZE; i++) {
			if(block[player][i].color == color[player] && gamefield[startposition[player]].color != color[player]) {
				block[player][i].color = ' ';
				gamefield[startposition[player]].color = color[player];
				return true;
			}
		}
		return false;
	}
	
	public boolean setStoneBackInBlock(int player) {
		for(int i = 3; i >= 0; i--) {
			if(block[player][i].color != color[player]) {
				block[player][i].color = color[player];
				return true;
			}
		}
		return false;
	}
	
	public boolean setStoneInHouse(int player, int idx) {
		idx = (idx - startposition[player]) % 40;
		
		if(0 > idx || idx > 3 || house[player][idx].color == color[player])
			return false;
		
		house[player][idx].color = color[player];
		return true;		
	}
	
	public int stoneOnGamefield(int player) {
		int count = 0;
		for(int idx = 0; idx < POSITIONS; idx++) 
			if(gamefield[idx].color == color[player])
				count++;
		
		return count;
	}
	
	public boolean isStoneInBlock(int player) {
		boolean stone = false;
		for(int i = 0; i < BLOCKSIZE;i++)
			stone = stone || block[player][i].color == color[player];
		return stone;
	}

	public boolean isGameEnded() {
		boolean win = true;
		for(int player = 0; player < PLAYER; player++) {
			for(int idx = 0; idx < HOUSESIZE; idx++)
				win = win && house[player][idx].color == color[player];
			if(win)
				return true;
			else
				win = true;
		}
		return false;
	}

	public boolean color(int player, int idx) {
		return gamefield[idx % POSITIONS].color == color[player];
	}

	
	public boolean isStartFree(int player) {
		return gamefield[startposition[player]].color != color[player];
	}

	
	public boolean isRounded(int player, int idx, int dice) {
		
		if(idx < startposition[player] && idx + dice >= startposition[player])
			return true;
		
		if(player == 1 && idx + dice > 39)
			return true;
		
		return false;
	}
}
