package de.htwg.menschaergerdichnicht.controller;

import de.htwg.menschaergerdichnicht.model.GameField;
import de.htwg.menschaergerdichnicht.model.Player;
import de.htwg.menschaergerdichnicht.observer.Observable;
import de.htwg.menschaergerdichnicht.observer.IObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Controller extends Observable implements IController {

	private GameField gamefield;
	private Player player0, player1, player2, player3, currentplayer;
	private Random r;
	private int dice;
	private final List<IObserver> observers = new ArrayList<IObserver>();

	public Controller() {
		gamefield = new GameField();
		r = new Random();
		player0 = new Player(0, "Rudolf", 'R');
		player1 = new Player(1, "Bastian", 'B');
		player2 = new Player(2, "Gert", 'G');
		player3 = new Player(3, "Sascha", 'S');

		currentplayer = player0;
		dice();
	}

	public boolean moveStart() {
		// Raus kommen
		if (gamefield.isStartFree(currentplayer.getIdx()) && gamefield.isStoneInBlock(currentplayer.getIdx())
				&& dice == 6) {
			int enemy = gamefield.enemyInStart(currentplayer.getIdx(), currentplayer.getColor());

			if (enemy != currentplayer.getIdx() && enemy >= 0)
				gamefield.setStoneBackInBlock(enemy);

			gamefield.getStoneOutOfBlock(currentplayer.getIdx());
			updateObservers();
			return true;
		}

		// Man kann nicht fahren
		if (gamefield.stoneOnGamefield(currentplayer.getIdx()) == 0) {
			updateObservers();
			return true;
		}

		return false;
	}

	public boolean move(int idx) {
		// Falscher Stein
		if (!gamefield.color(currentplayer.getIdx(), idx)) {
			return false;
		}

		// Steine ins Haus bringen
		if (gamefield.isRounded(currentplayer.getIdx(), idx, dice)) {
			if (!gamefield.setStoneInHouse(currentplayer.getIdx(), idx + dice))
				return false;
			gamefield.setStone(idx, 'x');
			updateObservers();
			return true;
		}

		// Steine schlagen
		int player = gamefield.whichPlayerOnIdx(idx + dice, currentplayer.getColor());

		if (player == currentplayer.getIdx()) {
			System.out.println("coz my stone is on this idx, can not move there, choose another stone to move\n");
			return false;
		}
		if (player >= 0)
			gamefield.setStoneBackInBlock(player);

		// normales Fahren
		gamefield.setStone(idx, 'x');
		gamefield.setStone(idx + dice, currentplayer.getColor());
		updateObservers();

		return true;
	}

	public boolean getOutOfBlock() {
		return gamefield.getStoneOutOfBlock(currentplayer.getIdx());
	}

	void dice() {
		// dice = r.nextInt(6) + 1;
		dice = 6;
	}

	public boolean isGameEnded() {
		return gamefield.isGameEnded();
	}

	public void setNextPlayer() {
		if (currentplayer == player0)
			currentplayer = player1;
		else if (currentplayer == player1)
			currentplayer = player2;
		else if (currentplayer == player2)
			currentplayer = player3;
		else if (currentplayer == player3)
			currentplayer = player0;
	}

	@Override
	public void registerObserver(IObserver observer) {
		observers.add(observer);
	}

	@Override
	public void unregisterObserver(IObserver observer) {
		observers.remove(observer);
	}

	@Override
	public void updateObservers() {

		for (IObserver observer : observers) {
			observer.update(currentplayer, this.isGameEnded());
		}

		if (dice != 6)
			setNextPlayer();
		dice();

		for (IObserver observer : observers) {
			observer.showDice(currentplayer, this.dice);
		}

		if (isGameEnded())
			System.exit(0);
	}

	public char getTokenColor(int idx) {
		return gamefield.getStoneColor(idx);
	}

	public char getTokenColorBlock(int player, int idx) {
		return gamefield.getStoneColorBlock(player, idx);
	}

	public char getTokenColorHouse(int player, int idx) {
		return gamefield.getStoneColorHouse(player, idx);
	}

}
