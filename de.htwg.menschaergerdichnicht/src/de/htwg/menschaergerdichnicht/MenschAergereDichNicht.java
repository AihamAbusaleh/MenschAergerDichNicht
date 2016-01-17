package de.htwg.menschaergerdichnicht;

import java.util.Scanner;

import org.apache.log4j.PropertyConfigurator;

import de.htwg.menschaergerdichnicht.controller.Controller;
import de.htwg.menschaergerdichnicht.view.GUI;
import de.htwg.menschaergerdichnicht.view.TUI;
 public class MenschAergereDichNicht {

	private static Scanner sc;

	public static void main(String[] args) {
		sc = new Scanner(System.in);
        PropertyConfigurator.configure("log4j.properties");
		Controller c = new Controller();

		TUI tui = new TUI(c);
		new GUI(c);
		do {
			while (!tui.noInput())
				;
			tui.handleInput(sc.next());

		} while (true);

	}

}
