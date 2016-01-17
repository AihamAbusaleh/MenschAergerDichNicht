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
	private static final String ICENTER = "|";
	private static final String IRIGHT = "    |";
	private static final String ILEFT =  "|        ";
	private static final String MIN ="-";
	
	private static final String ORIGHT =  "|     O |";
	private static final String OLEFT = "| O     |" ;
	private static final String OLEFTRIGHT =  "| O   O |";
	private static final String OCENTER = "|   O   |" ;



	public TUI(Controller c) {
		this.c = c;

		c.registerObserver(this);
	}
	public void print(String tui){
		LOGGER.info(tui);
	}

	public void display() {
		 print("                                        ");
		 print(" " + c.getTokenColorBlock(0, 0) + " " + c.getTokenColorBlock(0, 1) + IRIGHT
				+ c.getTokenColor(38) + MIN + c.getTokenColor(39) + "-" + c.getTokenColor(0) + ILEFT
				+ c.getTokenColorBlock(1, 0) + " " + c.getTokenColorBlock(1, 1) + " ");
	   	 print("						|38-39-0|        ");
		 print(" " + c.getTokenColorBlock(0, 2) + " " + c.getTokenColorBlock(0, 3) + IRIGHT
				+ c.getTokenColor(37) + ICENTER + c.getTokenColorHouse(1, 0) + ICENTER + c.getTokenColor(1) + ILEFT
				+ c.getTokenColorBlock(1, 2) + " " + c.getTokenColorBlock(1, 3) + " ");
		 print("						|37    1|        ");
		 print("        |" + c.getTokenColor(36) + ICENTER + c.getTokenColorHouse(1, 1) + ICENTER + c.getTokenColor(2)
				+ ILEFT);
		 print("						|36    2|        ");
		 print(" _______|" + c.getTokenColor(35) + ICENTER + c.getTokenColorHouse(1, 2) + ICENTER + c.getTokenColor(3)
				+ "|_______ ");
		 print("				    ____________|35    3|____________");
		 print(ICENTER + c.getTokenColor(30) + MIN + c.getTokenColor(31) + MIN + c.getTokenColor(32) + MIN
				+ c.getTokenColor(33) + MIN + c.getTokenColor(34) + ICENTER + c.getTokenColorHouse(1, 3) + ICENTER
				+ c.getTokenColor(4) + MIN + c.getTokenColor(5) + MIN + c.getTokenColor(6) + MIN + c.getTokenColor(7)
				+ "-" + c.getTokenColor(8) + ICENTER);
		 print("				    |30-31-32-33-34    04-05-06-07-08|");
		 print(ICENTER + c.getTokenColor(29) + " " + c.getTokenColorHouse(0, 0) + " " + c.getTokenColorHouse(0, 1)
				+ " " + c.getTokenColorHouse(0, 2) + " " + c.getTokenColorHouse(0, 3) + "   "
				+ c.getTokenColorHouse(2, 0) + " " + c.getTokenColorHouse(2, 1) + " " + c.getTokenColorHouse(2, 2) + " "
				+ c.getTokenColorHouse(2, 3) + " " + c.getTokenColor(9) + ICENTER);
		 print("				    |29                            09|");
		 print(ICENTER + c.getTokenColor(28) + MIN + c.getTokenColor(27) + MIN + c.getTokenColor(26) + MIN
				+ c.getTokenColor(25) + MIN + c.getTokenColor(24) + ICENTER + c.getTokenColorHouse(3, 3) + ICENTER
				+ c.getTokenColor(14) + MIN + c.getTokenColor(13) + MIN + c.getTokenColor(12) + MIN
				+ c.getTokenColor(11) + MIN + c.getTokenColor(10) + ICENTER);
		 print("			            |28-27-26-25-24    14-13-12-11-10|");
		 print("        |" + c.getTokenColor(23) + ICENTER + c.getTokenColorHouse(3, 2) + ICENTER
				+ c.getTokenColor(15) + ILEFT);
		 print("						|23    15| ");
		 print("        |" + c.getTokenColor(22) + ICENTER + c.getTokenColorHouse(3, 1) + ICENTER
				+ c.getTokenColor(16) + ILEFT);
		 print("		                	        |22    16|        ");
		 print(" " + c.getTokenColorBlock(3, 0) + " " + c.getTokenColorBlock(3, 1) + IRIGHT
				+ c.getTokenColor(21) + ICENTER + c.getTokenColorHouse(3, 0) + "|" + c.getTokenColor(17) + ILEFT
				+ c.getTokenColorBlock(2, 0) + " " + c.getTokenColorBlock(2, 1) + " ");
		 print("		                 	        |21    17|        ");
		 print(" " + c.getTokenColorBlock(3, 2) + " " + c.getTokenColorBlock(3, 3) + IRIGHT
				+ c.getTokenColor(20) + MIN + c.getTokenColor(19) + MIN + c.getTokenColor(18) + ILEFT
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

			if ("u".equals(token)) {
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
 			print(OCENTER);
			print(EDGE);

 			break;

		case 2:
			print(ORIGHT);
			print(EDGE);
			print(OLEFT);
 			break;
		case 3:
			print(ORIGHT);
			print(OCENTER);
			print(OLEFT);
 			break;
		case 4:
			print(OLEFTRIGHT);
			print(EDGE);
			print(OLEFTRIGHT);
 			break;
		case 5:
 			print(OLEFTRIGHT);
			print(OCENTER);
			print(OLEFTRIGHT);
 			break;
		case 6:
 			print(OLEFTRIGHT);
			print(OLEFTRIGHT);
			print(OLEFTRIGHT);
 			break;
		}
	}

}