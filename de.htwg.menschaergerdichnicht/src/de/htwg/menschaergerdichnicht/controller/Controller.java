package de.htwg.menschaergerdichnicht.controller;

import de.htwg.menschaergerdichnicht.model.GameField;
import de.htwg.menschaergerdichnicht.model.Player;
import de.htwg.menschaergerdichnicht.state.IState;
import de.htwg.menschaergerdichnicht.state.StatePlayer0;
import de.htwg.menschaergerdichnicht.state.StatePlayer1;
import de.htwg.menschaergerdichnicht.state.StatePlayer2;
import de.htwg.menschaergerdichnicht.state.StatePlayer3;
import de.htwg.util.command.CommandManager;
import de.htwg.util.observer.IObserver;
import de.htwg.util.observer.Observable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Controller extends Observable implements IController {

	private GameField gamefield;
	private Player currentplayer = new Player();
	private CommandManager manager = new CommandManager();
	private IState state;
	private Random r;
	private int dice;
	private final List<IObserver> observers = new ArrayList<IObserver>();

	public Controller() {
		gamefield = new GameField();

		r = new Random();
		state = new StatePlayer0();
		dice();
	}

	@Override
	public boolean moveStart() {
		currentplayer = state.currentPlayer(currentplayer.setcurrentplayer());
		createSteps();
		// Raus kommen
		stonCanOut();

		// Man kann nicht fahren wenn true, nextPlayer ist dran
		if (isFieldEmpty()) {

			return false;
		}

		return true;
	}

	@Override
	public void stonCanOut() {

		if (gamefield.isStartFree(currentplayer.getIdx()) && gamefield.isStoneInBlock(currentplayer.getIdx())
				&& dice == 6) {
			int enemy = gamefield.enemyInStart(currentplayer.getIdx(), currentplayer.getColor());

			if (enemy != currentplayer.getIdx() && enemy >= 0)
				gamefield.setStoneBackInBlock(enemy);

			gamefield.getStoneOutOfBlock(currentplayer.getIdx());
			updateObservers();
		}

	}

	@Override
	public boolean isFieldEmpty() {
		if (gamefield.stoneOnGamefield(currentplayer.getIdx()) == 0) {
			updateObservers();

			return true;
		}
		return false;
	}

	@Override
	public boolean move(int idx) {
		// Falscher Stein
		if (!gamefield.color(currentplayer.getIdx(), idx)) {
			System.out.println("wrong idx! no match");
			return false;
		}

		// Steine ins Haus bringen
		if (gamefield.isRounded(currentplayer.getIdx(), idx, dice)) {
			if (!gamefield.setStoneInHouse(currentplayer.getIdx(), idx + dice)) {
				System.out.println("there is a stone in the field house");
				return false;
			}

			gamefield.setStone(idx, 'x');

			updateObservers();

			return true;
		}

		// Steine schlagen
		int player = gamefield.whichPlayerOnIdx(idx + dice);

		if (player == currentplayer.getIdx()) {
			System.out.println(" choose another stone to move\n");
			return false;
		}
		if (player >= 0)
			gamefield.setStoneBackInBlock(player);

		// normales Fahren
		gamefield.setStone(idx, 'x');
		gamefield.setStone(idx + dice, currentplayer.getColor());
		updateObservers();
		createSteps();
		return true;
	}

	@Override
	public boolean getOutOfBlock() {
		return gamefield.getStoneOutOfBlock(currentplayer.getIdx());
	}

	@Override
	public int dice() {
		return dice = r.nextInt(6) + 1;
	}

	@Override
	public boolean isGameEnded() {
		return gamefield.isGameEnded();
	}

	@Override
	public void setNextPlayer() {

		if (currentplayer.getState().toString() == "Rot")
			state = new StatePlayer1();
		else if (currentplayer.getState().toString() == "Blau")
			state = new StatePlayer2();
		else if (currentplayer.getState().toString() == "Gelb")
			state = new StatePlayer3();
		else {
			state = new StatePlayer0();
		}
	}

	public void registerObserver(IObserver observer) {
		observers.add(observer);
	}

	public void unregisterObserver(IObserver observer) {
		observers.remove(observer);
	}

	public void updateObservers() {

		for (IObserver observer : observers) {

			observer.update(currentplayer, this.isGameEnded());

			if (isGameEnded()) {
				unregisterObserver(observer);
				System.exit(0);
			}
			break;
		}
		if (dice != 6) {
			setNextPlayer();
			currentplayer = state.currentPlayer(currentplayer.setcurrentplayer());

		}
		dice();
		for (IObserver observer : observers) {
			observer.showDice(currentplayer, this.dice);
		}

	}

	@Override
	public String wurfeln() {
		for (IObserver observer : observers) {
			observer.showDice(currentplayer, this.dice);

		}
		return " " + currentplayer.getName() + " threw [" + this.dice + "]";

	}

	@Override
	public char getTokenColor(int idx) {
		return gamefield.getStoneColor(idx);
	}

	@Override

	public char getTokenColorBlock(int player, int idx) {
		return gamefield.getStoneColorBlock(player, idx);
	}

	@Override

	public char getTokenColorHouse(int player, int idx) {
		return gamefield.getStoneColorHouse(player, idx);
	}

	@Override

	public void undo() {
		manager.undoCommand();
		updateObservers();
	}

	@Override

	public void createSteps() {
		manager.doCommand(new SaveSteps(gamefield));

	}

	@Override
	public boolean rounded(int idx) {
		return gamefield.isRounded(currentplayer.getIdx(), idx, dice);
	}

}
