package de.htwg.menschaergerdichnicht.view;

import de.htwg.menschaergerdichnicht.controller.Controller;
import de.htwg.menschaergerdichnicht.model.Player;
import de.htwg.util.observer.IObserver;

public class TUI implements IObserver {

	private Controller c;

	public TUI(Controller c) {
		this.c = c;

		c.registerObserver(this);
		 display();
	}

	public void display() {
		System.out.println("                                        ");
		System.out.print(" " + c.getTokenColorBlock(0, 0) + " " + c.getTokenColorBlock(0, 1) + "    |"
				+ c.getTokenColor(38) + "-" + c.getTokenColor(39) + "-" + c.getTokenColor(0) + "|    "
				+ c.getTokenColorBlock(1, 0) + " " + c.getTokenColorBlock(1, 1) + " ");
		System.out.println("			        |38390|        ");
		System.out.print(" " + c.getTokenColorBlock(0, 2) + " " + c.getTokenColorBlock(0, 3) + "    |"
				+ c.getTokenColor(37) + "|" + c.getTokenColorHouse(1, 0) + "|" + c.getTokenColor(1) + "|    "
				+ c.getTokenColorBlock(1, 2) + " " + c.getTokenColorBlock(1, 3) + " ");
		System.out.println("			        |37  1|        ");
		System.out.print("        |" + c.getTokenColor(36) + "|" + c.getTokenColorHouse(1, 1) + "|" + c.getTokenColor(2)
				+ "|        ");
		System.out.println("			        |36  2|        ");
		System.out.print(" _______|" + c.getTokenColor(35) + "|" + c.getTokenColorHouse(1, 2) + "|" + c.getTokenColor(3)
				+ "|_______ ");
		System.out.println("             ____________|35  3|____________");
		System.out.print("|" + c.getTokenColor(30) + "_" + c.getTokenColor(31) + "_" + c.getTokenColor(32) + "_"
				+ c.getTokenColor(33) + "_" + c.getTokenColor(34) + "|" + c.getTokenColorHouse(1, 3) + "|"
				+ c.getTokenColor(4) + "_" + c.getTokenColor(5) + "_" + c.getTokenColor(6) + "_" + c.getTokenColor(7)
				+ "_" + c.getTokenColor(8) + "|");
		System.out.println("  	    |30-31-32-33-34  04-05-06-07-08|");
		System.out.print("|" + c.getTokenColor(29) + "_" + c.getTokenColorHouse(0, 0) + "_" + c.getTokenColorHouse(0, 1)
				+ "_" + c.getTokenColorHouse(0, 2) + "_" + c.getTokenColorHouse(0, 3) + "   "
				+ c.getTokenColorHouse(2, 0) + "_" + c.getTokenColorHouse(2, 1) + "_" + c.getTokenColorHouse(2, 2) + "_"
				+ c.getTokenColorHouse(2, 3) + "_" + c.getTokenColor(9) + "|");
		System.out.println("             |29                          9 |");
		System.out.print("|" + c.getTokenColor(28) + "_" + c.getTokenColor(27) + "_" + c.getTokenColor(26) + "_"
				+ c.getTokenColor(25) + "_" + c.getTokenColor(24) + "|" + c.getTokenColorHouse(3, 3) + "|"
				+ c.getTokenColor(14) + "_" + c.getTokenColor(13) + "_" + c.getTokenColor(12) + "_"
				+ c.getTokenColor(11) + "_" + c.getTokenColor(10) + "|");
		System.out.println("             |28-27-26-25-24  14-13-12-11-10|");
		System.out.print("        |" + c.getTokenColor(23) + "|" + c.getTokenColorHouse(3, 2) + "|"
				+ c.getTokenColor(15) + "|        ");
		System.out.println("                         |23  15| ");
		System.out.print("        |" + c.getTokenColor(22) + "|" + c.getTokenColorHouse(3, 1) + "|"
				+ c.getTokenColor(16) + "|        ");
		System.out.println("			        |22  16|        ");
		System.out.print(" " + c.getTokenColorBlock(3, 0) + " " + c.getTokenColorBlock(3, 1) + "    |"
				+ c.getTokenColor(21) + "|" + c.getTokenColorHouse(3, 0) + "|" + c.getTokenColor(17) + "|    "
				+ c.getTokenColorBlock(2, 0) + " " + c.getTokenColorBlock(2, 1) + " ");
		System.out.println("			        |21  17|        ");
		System.out.print(" " + c.getTokenColorBlock(3, 2) + " " + c.getTokenColorBlock(3, 3) + "    |"
				+ c.getTokenColor(20) + "-" + c.getTokenColor(19) + "-" + c.getTokenColor(18) + "|    "
				+ c.getTokenColorBlock(2, 2) + " " + c.getTokenColorBlock(2, 3) + " ");
		System.out.println("			        |201918|           \n");
		System.out.println("________________________________________________________________");
		System.out.println("________________________________________________________________");

	}

	public boolean noInput() {
		return c.moveStart();
	}

	public boolean handleInput(String token) {

		try {
			int index = Integer.parseInt(token);

			return c.move(index);

		} catch (NumberFormatException e) {

			if (token.equals("r")) {
				c.redo();
				return false;
			} else if (token.equals("u")) {
				c.undo();
				return false;
			} else {
				System.out.println("\n please type a number, r , u!");
				return false;
			}
		}

	}

	public void update(Player currentPlayer, boolean gameEnded) {

		if (gameEnded) {
			System.out.println(currentPlayer.getName() + " hat gewonnen!");

		}

		display();
	}

	public void showDice(Player currentplayer, int dice) {
		System.out.println(currentplayer.getName() + " hat eine " + dice + " gewürfelt");
	}

}