package de.htwg.menschaergerdichnicht;

import java.util.Scanner;

import de.htwg.menschaergerdichnicht.controller.Controller;
import de.htwg.menschaergerdichnicht.view.GUI;
import de.htwg.menschaergerdichnicht.view.TUI;

public class MenschAergereDichNicht {

	private static Scanner input;

	private MenschAergereDichNicht() {
	}

	public static void main(String[] args) {
		input = new Scanner(System.in);
		Controller c = new Controller();

		TUI tui = new TUI(c);
		new GUI(c);
		do {
			while (!tui.noInput())
				;
			tui.handleInput(input.next());

		} while (true);

	}

}
