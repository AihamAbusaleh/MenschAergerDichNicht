package de.htwg.menschaergerdichnicht.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.htwg.menschaergerdichnicht.controller.Controller;
import de.htwg.menschaergerdichnicht.model.Player;
import de.htwg.util.observer.IObserver;

@SuppressWarnings("serial")
public class GUI implements IObserver {

	private JFrame frame;
	// private JTextField outputPlayers;
	// private JPanel wurfelPanel;
	private Controller c;
	private JPanel container;

	public GUI(Controller c) {
		this.c = c;
		c.registerObserver(this);

		frame = new JFrame("Mensch ärgere dich nicht");
		this.container = new SpielFeldGUI();
		// this.wurfelPanel = new JPanel();
		// wurfelPanel.setLayout(new GridLayout(2, 2));

		// this.outputPlayers = new JTextField();

		frame.getContentPane().setLayout(new BorderLayout());
		// frame.getContentPane().add(wurfelPanel, BorderLayout.NORTH);
		frame.getContentPane().add(container);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 700);
		frame.setVisible(true);
		frame.setResizable(false);

	}

	private class SpielFeldGUI extends JPanel implements MouseListener {
		private byte[][] feld = null;
		private MyPoint[] figurenPositionen = null;
		private MyPoint[] stoneBlock0 = null;
		private MyPoint[] stoneBlock1 = null;
		private MyPoint[] stoneBlock2 = null;
		private MyPoint[] stoneBlock3 = null;
		private MyPoint[] stoneHause0 = null;
		private MyPoint[] stoneHause1 = null;
		private MyPoint[] stoneHause2 = null;
		private MyPoint[] stoneHause3 = null;

		MyPoint clickedPoint = null;

		public SpielFeldGUI() {
			this.setBackground(Color.WHITE);

			this.feld = new byte[][] { 
					{ 'R', 'R', '0', '0', 'x', 'x', 'x', '0', '0', 'B', 'B' },
					{ 'R', 'R', '0', '0', 'x', 'B', 'x', '0', '0', 'B', 'B' },
					{ '0', '0', '0', '0', 'x', 'B', 'x', '0', '0', '0', '0' },
					{ '0', '0', '0', '0', 'x', 'B', 'x', '0', '0', '0', '0' },
					{ 'x', 'x', 'x', 'x', 'x', 'B', 'x', 'x', 'x', 'x', 'x' },
					{ 'x', 'R', 'R', 'R', 'R', '0', 'G', 'G', 'G', 'G', 'x' },
					{ 'x', 'x', 'x', 'x', 'x', 'P', 'x', 'x', 'x', 'x', 'x' },
					{ '0', '0', '0', '0', 'x', 'P', 'x', '0', '0', '0', '0' },
					{ '0', '0', '0', '0', 'x', 'P', 'x', '0', '0', '0', '0' },
					{ 'P', 'P', '0', '0', 'x', 'P', 'x', '0', '0', 'G', 'G' },
					{ 'P', 'P', '0', '0', 'x', 'x', 'x', '0', '0', 'G', 'G' } };

			this.stoneBlock0 = new MyPoint[] { new MyPoint(0, 0, 100), new MyPoint(1, 0, 100), new MyPoint(0, 1, 100),
					new MyPoint(1, 1, 100) };
			this.stoneBlock1 = new MyPoint[] { new MyPoint(0, 9, 200), new MyPoint(1, 9, 200), new MyPoint(0, 10, 200),
					new MyPoint(1, 10, 200) };
			this.stoneBlock2 = new MyPoint[] { new MyPoint(9, 9, 400), new MyPoint(10, 9, 400), new MyPoint(9, 10, 400),
					new MyPoint(10, 10, 400) };
			this.stoneBlock3 = new MyPoint[] { new MyPoint(9, 0, 300), new MyPoint(10, 0, 300), new MyPoint(9, 1, 300),
					new MyPoint(10, 1, 300) };

			this.stoneHause0 = new MyPoint[] { new MyPoint(5, 1, 1000), new MyPoint(5, 2, 1000), // rot
					new MyPoint(5, 3, 1000), new MyPoint(5, 4, 1000) };
			this.stoneHause1 = new MyPoint[] { new MyPoint(1, 5, 2000), new MyPoint(2, 5, 2000), // blau
					new MyPoint(3, 5, 2000), new MyPoint(4, 5, 2000) };
			this.stoneHause2 = new MyPoint[] { new MyPoint(5, 6, 3000), new MyPoint(5, 7, 3000), // gelb
					new MyPoint(5, 8, 3000), new MyPoint(5, 9, 3000) };
			this.stoneHause3 = new MyPoint[] { new MyPoint(6, 5, 4000), new MyPoint(7, 5, 4000), // pink
					new MyPoint(8, 5, 4000), new MyPoint(9, 5, 4000) };

			this.figurenPositionen = new MyPoint[] { new MyPoint(4, 0, 30), new MyPoint(4, 1, 31),
					new MyPoint(4, 2, 32), new MyPoint(4, 3, 33), new MyPoint(4, 4, 34), new MyPoint(3, 4, 35),
					new MyPoint(2, 4, 36), new MyPoint(1, 4, 37), new MyPoint(0, 4, 38), new MyPoint(0, 5, 39),
					new MyPoint(0, 6, 0), new MyPoint(1, 6, 1), new MyPoint(2, 6, 2), new MyPoint(3, 6, 3),
					new MyPoint(4, 6, 4), new MyPoint(4, 7, 5), new MyPoint(4, 8, 6), new MyPoint(4, 9, 7),
					new MyPoint(4, 10, 8), new MyPoint(5, 10, 9), new MyPoint(6, 10, 10), new MyPoint(6, 9, 11),
					new MyPoint(6, 8, 12), new MyPoint(6, 7, 13), new MyPoint(6, 6, 14), new MyPoint(7, 6, 15),
					new MyPoint(8, 6, 16), new MyPoint(9, 6, 17), new MyPoint(10, 6, 18), new MyPoint(10, 5, 19),
					new MyPoint(10, 4, 20), new MyPoint(9, 4, 21), new MyPoint(8, 4, 22), new MyPoint(7, 4, 23),
					new MyPoint(6, 4, 24), new MyPoint(6, 3, 25), new MyPoint(6, 2, 26), new MyPoint(6, 1, 27),
					new MyPoint(6, 0, 28), new MyPoint(5, 0, 29) };

			addMouseListener(this);
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			int laenge = this.getWidth() / 11;
			Graphics2D g2 = (Graphics2D) g;
			Color tmpColor = g2.getColor();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			for (int i = 0; i < this.feld.length; i++) {
				for (int j = 0; j < feld[i].length; j++) {
					Color currentColor = myFeldColor(feld[i][j]);
					if (currentColor == null)
						continue;
					g2.setColor(currentColor);
					g2.fillOval(j * laenge + laenge / 10, i * laenge + laenge / 10, laenge - laenge / 5,
							laenge - laenge / 5);
					g2.setColor(Color.BLACK.darker().darker());
					g2.drawOval(j * laenge + laenge / 10, i * laenge + laenge / 10, laenge - laenge / 5,
							laenge - laenge / 5);
				}
			}

			MyPoint position = new MyPoint();
			int indexofcklickedpoint;
			if (clickedPoint != null) {
				for (int i = 0; i < this.figurenPositionen.length; i++) {
					if (clickedPoint.x == this.figurenPositionen[i].x
							&& clickedPoint.y == this.figurenPositionen[i].y) {
						clickedPoint.setIdx(this.figurenPositionen[i].getIdx());
						indexofcklickedpoint = clickedPoint.getIdx();
						if(!c.rounded(indexofcklickedpoint)){
						position.x = figurenPositionen[(i + c.dice()) % 40].y;
						position.y = figurenPositionen[(i + c.dice()) % 40].x;
						}
						// if (c.playerNr() == 0) {
						// round = ((indexofcklickedpoint + c.dice()) - 30) %
						// 40;
						// position.x = stoneHause0[round % 4].y;
						// position.y = stoneHause0[round % 4].x;
						// }

						break;
					}

				}

				position.x = position.x * laenge;
				position.y = position.y * laenge;
				Color stoneColor = Color.WHITE;
				if (c.playerNr() == 0) {
					stoneColor = Color.RED;
				}
				if (c.playerNr() == 1) {
					stoneColor = Color.BLUE;
				}
				if (c.playerNr() == 2) {
					stoneColor = Color.YELLOW;
				}
				if (c.playerNr() == 3) {
					stoneColor = Color.PINK;
				}
				g2.setColor(stoneColor.darker());
				g2.fillOval(position.x + laenge / 4, position.y + laenge / 4, laenge / 2, laenge / 2);
				g2.setColor(stoneColor.darker().darker());
				g2.fillOval(position.x + laenge / 3, position.y + laenge / 3, laenge / 3, laenge / 3);
			}
			int red = -1;
			int blue = -1;
			int yellow = -1;
			int pink = -1;
			int red1 = -1;
			int blue1 = -1;
			int yellow1 = -1;
			int pink1 = -1;

			for (int player = 0; player < 4; player++) {
				for (int block = 0; block < 4; block++) {
					Color stoneColor = Color.WHITE;
					char tokenColorInBlock = c.getTokenColorBlock(player, block);
					char tokenColorInHouse = c.getTokenColorHouse(player, block);
					if (tokenColorInHouse != ' ') {

						switch (tokenColorInHouse) {
						case 'R':
							red1++;
							stoneColor = Color.RED;
							position.x = this.stoneHause0[red1].y;
							position.y = this.stoneHause0[red1].x;

							break;
						case 'B':
							blue1++;
							stoneColor = Color.BLUE;
							position.x = this.stoneHause1[blue1].y;
							position.y = this.stoneHause1[blue1].x;

							break;
						case 'G':
							stoneColor = Color.YELLOW;

							break;
						case 'P':
							stoneColor = Color.PINK;

							break;

						}

						position.x = position.x * laenge;
						position.y = position.y * laenge;
						g2.setColor(stoneColor.darker());
						g2.fillOval(position.x + laenge / 4, position.y + laenge / 4, laenge / 2, laenge / 2);
						g2.setColor(stoneColor.darker().darker());
						g2.fillOval(position.x + laenge / 3, position.y + laenge / 3, laenge / 3, laenge / 3);
					}

					if (tokenColorInBlock != ' ') {
						switch (tokenColorInBlock) {
						case 'R':
							red++;
							stoneColor = Color.RED;
							position.x = stoneBlock0[red].y;
							position.y = stoneBlock0[red].x;
							break;
						case 'B':
							blue++;
							stoneColor = Color.BLUE;
							position.x = stoneBlock1[blue].y;
							position.y = stoneBlock1[blue].x;
							break;
						case 'G':
							yellow++;
							stoneColor = Color.YELLOW;
							position.x = stoneBlock2[yellow].y;
							position.y = stoneBlock2[yellow].x;
							break;
						case 'P':
							pink++;
							stoneColor = Color.PINK;
							position.x = stoneBlock3[pink].y;
							position.y = stoneBlock3[pink].x;
							break;
						default:
							stoneColor = Color.WHITE;
						}
						position.x = position.x * laenge;
						position.y = position.y * laenge;
						g2.setColor(stoneColor.darker());
						g2.fillOval(position.x + laenge / 4, position.y + laenge / 4, laenge / 2, laenge / 2);
						g2.setColor(stoneColor.darker().darker());
						g2.fillOval(position.x + laenge / 3, position.y + laenge / 3, laenge / 3, laenge / 3);
					} else {
					//	for (int j = 0; j < 4; j++) {

							for (int i = 0; i < figurenPositionen.length; i++) {
								if (c.isStoneHere(figurenPositionen[i].getIdx()) != -1) {
									if (c.isStoneHere(figurenPositionen[i].getIdx()) == 0) {
										stoneColor = Color.RED;
									}
									if (c.isStoneHere(figurenPositionen[i].getIdx()) == 1) {
										stoneColor = Color.BLUE;
									}
									if (c.isStoneHere(figurenPositionen[i].getIdx()) == 2) {
										stoneColor = Color.YELLOW;
									}
									if (c.isStoneHere(figurenPositionen[i].getIdx()) == 3) {
										stoneColor = Color.PINK;
									}
									position.x = figurenPositionen[i].y;
									position.y = figurenPositionen[i].x;
									position.x = position.x * laenge;
									position.y = position.y * laenge;
									g2.setColor(stoneColor.darker());
									g2.fillOval(position.x + laenge / 4, position.y + laenge / 4, laenge / 2,
											laenge / 2);
									g2.setColor(stoneColor.darker().darker());
									g2.fillOval(position.x + laenge / 3, position.y + laenge / 3, laenge / 3,
											laenge / 3);
								}

							}
						//}

					}

				}

			}

			g2.setColor(tmpColor);

		}

		private Color myFeldColor(int i) {
			Color currentColor = null;
			switch (i) {
			case '0':

				break;
			case 'R':
				currentColor = Color.RED;
				break;
			case 'B':
				currentColor = Color.BLUE;
				break;
			case 'G':
				currentColor = Color.YELLOW;
				break;
			case 'P':
				currentColor = Color.PINK;
				break;
			case 'x':
				currentColor = Color.WHITE;
				break;

			default:
				currentColor = Color.WHITE;
				break;
			}

			return currentColor;

		}

		@Override
		public void mouseClicked(MouseEvent e) {

			if (!c.isFieldEmpty()) {

				for (int i = 0; i < this.figurenPositionen.length; i++) {

					int size = this.getWidth();
					clickedPoint = new MyPoint(e.getY() * 11 / size, e.getX() * 11 / size);
					int l = e.getY() * 11 / size;
					int m = e.getX() * 11 / size;

					if (l == this.figurenPositionen[i].x && m == this.figurenPositionen[i].y) {
						clickedPoint.setIdx(this.figurenPositionen[i].getIdx());
						if (c.moveStart() && c.move((this.figurenPositionen[clickedPoint.getIdx()].idx + 10) % 40))
							repaint();
						break;
					}
				}

			}

		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {

		}

		@Override
		public void mousePressed(MouseEvent e) {

		}

		@Override
		public void mouseReleased(MouseEvent e) {

		}

	}

	@Override
	public void update(Player currentPlayer, boolean gameEnded) {
		if (c.moveStart())
			this.frame.repaint();

		if (c.dice() != 6)
			c.setNextPlayer();

		this.frame.repaint();

		// if(currentPlayer.getIdx() == 0 && c.dice() != 6){
		//
		// c.setNextPlayer();
		// }
		//
		//
		//
		// c.dice();
		// this.frame.repaint();

	}

	@Override
	public void showDice(Player currentplayer, int dice) {
		// c.dice();

	}

}