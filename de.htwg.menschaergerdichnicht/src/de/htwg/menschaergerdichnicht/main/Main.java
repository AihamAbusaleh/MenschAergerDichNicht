package de.htwg.menschaergerdichnicht.main;

import java.util.Scanner;

import de.htwg.menschaergerdichnicht.controller.Controller;

import de.htwg.menschaergerdichnicht.view.TUI;

public class Main {

	private static Scanner sc;

	public static void main(String[] args) {
		sc = new Scanner(System.in);

		Controller c = new Controller();

		TUI tui = new TUI(c);
		// while(true){
		// while (!tui.noInput())
		// ;
		// while(!tui.handleInput(sc.next()))
		// ;
		// }
		do {
			while (!tui.noInput())
				;
			tui.handleInput(sc.next());
			if(!tui.handleInput(sc.next()))
				tui.handleInput(sc.next());
		} while (true);
	
	}

}
