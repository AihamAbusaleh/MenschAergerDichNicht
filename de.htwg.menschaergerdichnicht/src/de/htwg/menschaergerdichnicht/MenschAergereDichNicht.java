package de.htwg.menschaergerdichnicht;

import java.util.Scanner;

import de.htwg.menschaergerdichnicht.controller.Controller;
import de.htwg.menschaergerdichnicht.view.GUI;
import de.htwg.menschaergerdichnicht.view.TUI;

public class MenschAergereDichNicht {

	private static Scanner sc;

	private MenschAergereDichNicht() {
	}

	public static void main(String[] args) {
		sc = new Scanner(System.in);
		Controller c = new Controller();

		TUI tui = new TUI(c);
		@SuppressWarnings("unused")
		GUI gui = new GUI(c);
		do {
			while (!tui.noInput())
				;
			tui.handleInput(sc.next());

		} while (true);

	}

}
