package de.htwg.menschaergerdichnicht.view;

public class MyPoint {
	int x, y, idx;

	public MyPoint(int x, int y, int idx) {

		this.x = x;
		this.y = y;
		this.idx = idx;
	}

	public MyPoint() {
	}

	public MyPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

}
