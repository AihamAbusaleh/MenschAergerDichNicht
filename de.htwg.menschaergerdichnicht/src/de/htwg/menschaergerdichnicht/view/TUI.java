package de.htwg.menschaergerdichnicht.view;



import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import de.htwg.menschaergerdichnicht.controller.Controller;
import de.htwg.menschaergerdichnicht.model.Player;
import de.htwg.util.observer.IObserver;

public class TUI implements IObserver {
	private static final Logger LOGGER = LogManager.getLogger(TUI.class);

	private Controller c;
	private static final String EDGE = "|       |";
	private static final String LINE = "|";

	public TUI(Controller c) {
		this.c = c;

		c.registerObserver(this);
	}
	public void print(String tui){
		LOGGER.info(tui);
	}

	public void display() {
		 print("                                        ");
		 print(" " + c.getTokenColorBlock(0, 0) + " " + c.getTokenColorBlock(0, 1) + "    |"
				+ c.getTokenColor(38) + "-" + c.getTokenColor(39) + "-" + c.getTokenColor(0) + "|    "
				+ c.getTokenColorBlock(1, 0) + " " + c.getTokenColorBlock(1, 1) + " ");
	   	 print("						|38-39-0|        ");
		 print(" " + c.getTokenColorBlock(0, 2) + " " + c.getTokenColorBlock(0, 3) + "    |"
				+ c.getTokenColor(37) + LINE + c.getTokenColorHouse(1, 0) + LINE + c.getTokenColor(1) + "|    "
				+ c.getTokenColorBlock(1, 2) + " " + c.getTokenColorBlock(1, 3) + " ");
		 print("						|37    1|        ");
		 print("        |" + c.getTokenColor(36) + LINE + c.getTokenColorHouse(1, 1) + LINE + c.getTokenColor(2)
				+ "|        ");
		 print("						|36    2|        ");
		 print(" _______|" + c.getTokenColor(35) + LINE + c.getTokenColorHouse(1, 2) + LINE + c.getTokenColor(3)
				+ "|_______ ");
		 print("				    ____________|35    3|____________");
		 print("|" + c.getTokenColor(30) + "-" + c.getTokenColor(31) + "-" + c.getTokenColor(32) + "-"
				+ c.getTokenColor(33) + "-" + c.getTokenColor(34) + LINE + c.getTokenColorHouse(1, 3) + LINE
				+ c.getTokenColor(4) + "-" + c.getTokenColor(5) + "-" + c.getTokenColor(6) + "-" + c.getTokenColor(7)
				+ "-" + c.getTokenColor(8) + LINE);
		 print("				    |30-31-32-33-34    04-05-06-07-08|");
		 print("|" + c.getTokenColor(29) + " " + c.getTokenColorHouse(0, 0) + " " + c.getTokenColorHouse(0, 1)
				+ " " + c.getTokenColorHouse(0, 2) + " " + c.getTokenColorHouse(0, 3) + "   "
				+ c.getTokenColorHouse(2, 0) + " " + c.getTokenColorHouse(2, 1) + " " + c.getTokenColorHouse(2, 2) + " "
				+ c.getTokenColorHouse(2, 3) + " " + c.getTokenColor(9) + LINE);
		 print("				    |29                            09|");
		 print("|" + c.getTokenColor(28) + "-" + c.getTokenColor(27) + "-" + c.getTokenColor(26) + "-"
				+ c.getTokenColor(25) + "-" + c.getTokenColor(24) + LINE + c.getTokenColorHouse(3, 3) + LINE
				+ c.getTokenColor(14) + "-" + c.getTokenColor(13) + "-" + c.getTokenColor(12) + "-"
				+ c.getTokenColor(11) + "-" + c.getTokenColor(10) + LINE);
		 print("			            |28-27-26-25-24    14-13-12-11-10|");
		 print("        |" + c.getTokenColor(23) + LINE + c.getTokenColorHouse(3, 2) + LINE
				+ c.getTokenColor(15) + "|        ");
		 print("						|23    15| ");
		 print("        |" + c.getTokenColor(22) + LINE + c.getTokenColorHouse(3, 1) + LINE
				+ c.getTokenColor(16) + "|        ");
		 print("		                	        |22    16|        ");
		 print(" " + c.getTokenColorBlock(3, 0) + " " + c.getTokenColorBlock(3, 1) + "    |"
				+ c.getTokenColor(21) + LINE + c.getTokenColorHouse(3, 0) + "|" + c.getTokenColor(17) + "|    "
				+ c.getTokenColorBlock(2, 0) + " " + c.getTokenColorBlock(2, 1) + " ");
		 print("		                 	        |21    17|        ");
		 print(" " + c.getTokenColorBlock(3, 2) + " " + c.getTokenColorBlock(3, 3) + "    |"
				+ c.getTokenColor(20) + "-" + c.getTokenColor(19) + "-" + c.getTokenColor(18) + "|    "
				+ c.getTokenColorBlock(2, 2) + " " + c.getTokenColorBlock(2, 3) + " ");
		 print("						|20-19-18|           \n");
		 print("________________________________________________________________");
		 print("________________________________________________________________");
 

	}

	public boolean noInput() {
		return c.moveStart();
	}

	public boolean handleInput(String token) {

		try {
			int index = Integer.parseInt(token);

			return c.move(index);

		} catch (NumberFormatException e) {

			if (token.equals("u")) {
				c.undo();
				return false;
			} else {
				 print("\n please type 'number'  Or  'u'!");
				return false;
			}
		}

	}

	@Override
	public void update(Player currentPlayer, boolean gameEnded) {

		if (gameEnded) {
			 print(currentPlayer.getName() + " hat gewonnen!");

		}

		display();
	}

	@Override
	public void showDice(Player currentplayer, int dice) {
		print(currentplayer.getName() + " threw :   " );
		switch (dice){
		case 1:
			print(EDGE);
 			print("|   O   |");
			print(EDGE);

 			break;

		case 2:
			print("|     O |");
			print(EDGE);
			print("| O     |");
 			break;
		case 3:
			print("|     O |");
			print("|   O   |");
			print("| O     |");
 			break;
		case 4:
			print("| O   O |");
			print(EDGE);
			print("| O   O |");
 			break;
		case 5:
 			print("| O   O |");
			print("|   O   |");
			print("| O   O |");
 			break;
		case 6:
 			print("| O   O |");
			print("| O   O |");
			print("| O   O |");
 			break;
		}
	}

}