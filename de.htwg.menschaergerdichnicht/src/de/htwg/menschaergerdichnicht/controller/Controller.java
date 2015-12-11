package de.htwg.menschaergerdichnicht.controller;

import de.htwg.menschaergerdichnicht.model.GameField;
import de.htwg.menschaergerdichnicht.model.Player;
import de.htwg.menschaergerdichnicht.observer.Observable;
import de.htwg.menschaergerdichnicht.state.IState;
import de.htwg.menschaergerdichnicht.state.StatePlayer0;
import de.htwg.menschaergerdichnicht.state.StatePlayer1;
import de.htwg.menschaergerdichnicht.state.StatePlayer2;
import de.htwg.menschaergerdichnicht.state.StatePlayer3;
import de.htwg.util.command.CommandManager;
import de.htwg.menschaergerdichnicht.observer.IObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Controller extends Observable implements IController {

	private GameField gamefield;
	private Player currentplayer = new Player();
	private CommandManager manager = new CommandManager();
	// private CreateCommand create = new CreateCommand(gamefield);
	private IState state;
	private Random r;
	private int dice;
	private final List<IObserver> observers = new ArrayList<IObserver>();

	public Controller() {
		gamefield = new GameField();

		 createCommand();
		r = new Random();
		state = new StatePlayer0();
		dice();
	}

	public boolean moveStart() {
		currentplayer = state.currentPlayer(currentplayer.setcurrentplayer());

		// Raus kommen
		stonCanOut();

		// Man kann nicht fahren wenn true, nextPlayer ist dran
		if (isFieldEmpty()) {

			return false;
		}
		updateObservers();
		createCommand();
		return true;
	}

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

	public boolean isFieldEmpty() {
		if (gamefield.stoneOnGamefield(currentplayer.getIdx()) == 0) {
			updateObservers();
			return true;
		}
		return false;
	}

	public boolean move(int idx) {
		// Falscher Stein
		if (!gamefield.color(currentplayer.getIdx(), idx)) {
			System.out.println("wrong idx! no match");
			
			return false;
		}

		// Steine ins Haus bringen
		if (gamefield.isRounded(currentplayer.getIdx(), idx, dice)) {
			if (!gamefield.setStoneInHouse(currentplayer.getIdx(), idx + dice))
				return false;
			gamefield.setStone(idx, 'x');
			updateObservers();
			// createCommand();

			return true;
		}

		// Steine schlagen
		int player = gamefield.whichPlayerOnIdx(idx + dice, currentplayer.getColor());

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
		//createCommand();
		return true;
	}

	public boolean getOutOfBlock() {
		return gamefield.getStoneOutOfBlock(currentplayer.getIdx());
	}

	void dice() {
	    dice = r.nextInt(6) + 1;
	}
	
	void dice(int dice) {
		dice = 6;
	}

	public boolean isGameEnded() {
		return gamefield.isGameEnded();
	}

	public void setNextPlayer() {

		if (currentplayer.getState().toString() == "Player0")
			state = new StatePlayer1();
		else if (currentplayer.getState().toString() == "Player1")
			state = new StatePlayer2();
		else if (currentplayer.getState().toString() == "Player2")
			state = new StatePlayer3();
		else {
			state = new StatePlayer0();
		}
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

		if (dice != 6)
			setNextPlayer();

		

		for (IObserver observer : observers) {
			observer.update(currentplayer, this.isGameEnded());
			if (isGameEnded()) {
				unregisterObserver(observer);
				System.exit(0);
			}
		}
		for (IObserver observer : observers) {
			observer.showDice(currentplayer, this.dice);
		}

		dice();
	//	createCommand();
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

	// @Override
	public void undo() {
		manager.undoCommand();
	//	updateObservers();
	}

	// @Override
	public void redo() {
		manager.redoCommand();
		updateObservers();
	}

	// @Override
	public void createCommand() {
		// CommandManager manager = new CommandManager();

		manager.doCommand(new CreateCommand(gamefield));

	}

}
