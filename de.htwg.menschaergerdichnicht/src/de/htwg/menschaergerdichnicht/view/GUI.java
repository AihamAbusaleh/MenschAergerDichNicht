package de.htwg.menschaergerdichnicht.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.htwg.menschaergerdichnicht.controller.Controller;
import de.htwg.menschaergerdichnicht.model.Player;
import de.htwg.util.observer.IObserver;

@SuppressWarnings("serial")
public class GUI implements IObserver {

	private JFrame frame;
	private JTextField output;
	private JPanel wurfelPanel;
	private Controller c;
	private JButton wurfeln;
	private JPanel container;

	public GUI(Controller c) {
		this.c = c;
		c.registerObserver(this);

		frame = new JFrame("Mensch ärgere dich nicht");
		this.container = new GamefieldGUI();
		this.wurfelPanel = new JPanel();
		wurfelPanel.setLayout(new GridLayout(1, 2));

		output = new JTextField();

		output.setEditable(false);

		this.wurfeln = new JButton("Throw Dice");
		this.wurfeln.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if ("Throw Dice".equals(e.getActionCommand())) {
					output.setText(c.wurfeln());

				}

			}
		});
		this.wurfelPanel.add(wurfeln);
		this.wurfelPanel.add(output);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(wurfelPanel, BorderLayout.NORTH);
		frame.getContentPane().add(container);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 700);
		frame.setVisible(true);
		frame.setResizable(false);

	}

	private class GamefieldGUI extends JPanel implements MouseListener {
		private byte[][] field = null;
		private MyPoint[] figurePosition = null;
		private MyPoint[] stoneBlock0 = null;
		private MyPoint[] stoneBlock1 = null;
		private MyPoint[] stoneBlock2 = null;
		private MyPoint[] stoneBlock3 = null;
		private MyPoint[] stoneHause0 = null;
		private MyPoint[] stoneHause1 = null;
		private MyPoint[] stoneHause2 = null;
		private MyPoint[] stoneHause3 = null;

		MyPoint clickedPoint = null;
		MyPoint position = null;
		Color stoneColor = Color.WHITE;

		public GamefieldGUI() {
			this.setBackground(Color.WHITE);

			this.field = new byte[][] { { 'R', 'R', '0', '0', 'x', 'x', 'x', '0', '0', 'B', 'B' },
					{ 'R', 'R', '0', '0', 'x', 'B', 'x', '0', '0', 'B', 'B' },
					{ '0', '0', '0', '0', 'x', 'B', 'x', '0', '0', '0', '0' },
					{ '0', '0', '0', '0', 'x', 'B', 'x', '0', '0', '0', '0' },
					{ 'x', 'x', 'x', 'x', 'x', 'B', 'x', 'x', 'x', 'x', 'x' },
					{ 'x', 'R', 'R', 'R', 'R', '0', 'Y', 'Y', 'Y', 'Y', 'x' },
					{ 'x', 'x', 'x', 'x', 'x', 'P', 'x', 'x', 'x', 'x', 'x' },
					{ '0', '0', '0', '0', 'x', 'P', 'x', '0', '0', '0', '0' },
					{ '0', '0', '0', '0', 'x', 'P', 'x', '0', '0', '0', '0' },
					{ 'P', 'P', '0', '0', 'x', 'P', 'x', '0', '0', 'Y', 'Y' },
					{ 'P', 'P', '0', '0', 'x', 'x', 'x', '0', '0', 'Y', 'Y' } };

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

			this.figurePosition = new MyPoint[] { new MyPoint(4, 0, 30), new MyPoint(4, 1, 31), new MyPoint(4, 2, 32),
					new MyPoint(4, 3, 33), new MyPoint(4, 4, 34), new MyPoint(3, 4, 35), new MyPoint(2, 4, 36),
					new MyPoint(1, 4, 37), new MyPoint(0, 4, 38), new MyPoint(0, 5, 39), new MyPoint(0, 6, 0),
					new MyPoint(1, 6, 1), new MyPoint(2, 6, 2), new MyPoint(3, 6, 3), new MyPoint(4, 6, 4),
					new MyPoint(4, 7, 5), new MyPoint(4, 8, 6), new MyPoint(4, 9, 7), new MyPoint(4, 10, 8),
					new MyPoint(5, 10, 9), new MyPoint(6, 10, 10), new MyPoint(6, 9, 11), new MyPoint(6, 8, 12),
					new MyPoint(6, 7, 13), new MyPoint(6, 6, 14), new MyPoint(7, 6, 15), new MyPoint(8, 6, 16),
					new MyPoint(9, 6, 17), new MyPoint(10, 6, 18), new MyPoint(10, 5, 19), new MyPoint(10, 4, 20),
					new MyPoint(9, 4, 21), new MyPoint(8, 4, 22), new MyPoint(7, 4, 23), new MyPoint(6, 4, 24),
					new MyPoint(6, 3, 25), new MyPoint(6, 2, 26), new MyPoint(6, 1, 27), new MyPoint(6, 0, 28),
					new MyPoint(5, 0, 29) };
			addMouseListener(this);

		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			int laenge = this.getWidth() / 11;
			Graphics2D g2 = (Graphics2D) g;
			Color tmpColor = g2.getColor();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			for (int i = 0; i < this.field.length; i++) {
				for (int j = 0; j < field[i].length; j++) {
					Color currentColor = myFeldColor(field[i][j]);
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

			position = new MyPoint();

			// draw stones in block and house
			for (int player = 0; player < 4; player++) {
				for (int block = 0; block < 4; block++) {

					char tokenColorInBlock = c.getTokenColorBlock(player, block);
					char tokenColorInHouse = c.getTokenColorHouse(player, block);

					if (tokenColorInHouse != ' ') {
						tokenColorHouse(tokenColorInHouse, block);
						position.x = position.x * laenge;
						position.y = position.y * laenge;
						g2.setColor(stoneColor.darker());
						g2.fillOval(position.x + laenge / 4, position.y + laenge / 4, laenge / 2, laenge / 2);
						g2.setColor(stoneColor.darker().darker());
						g2.fillOval(position.x + laenge / 3, position.y + laenge / 3, laenge / 3, laenge / 3);
					}

					if (tokenColorInBlock != ' ') {
						tokenColorBlock(tokenColorInBlock, block);
						position.x = position.x * laenge;
						position.y = position.y * laenge;
						g2.setColor(stoneColor.darker());
						g2.fillOval(position.x + laenge / 4, position.y + laenge / 4, laenge / 2, laenge / 2);
						g2.setColor(stoneColor.darker().darker());
						g2.fillOval(position.x + laenge / 3, position.y + laenge / 3, laenge / 3, laenge / 3);
					}
				}

			}
			// draw all the stones on the game field
			for (int i = 0; i < figurePosition.length; i++) {
				if (c.getTokenColor(figurePosition[i].idx) != 'x') {
					if (c.getTokenColor(figurePosition[i].idx) == 'R') {
						stoneColor = Color.RED;

					}
					if (c.getTokenColor(figurePosition[i].idx) == 'B') {
						stoneColor = Color.BLUE;

					}
					if (c.getTokenColor(figurePosition[i].idx) == 'Y') {
						stoneColor = Color.YELLOW;

					}
					if (c.getTokenColor(figurePosition[i].idx) == 'P') {
						stoneColor = Color.PINK;

					}

					position.x = figurePosition[i].y;
					position.y = figurePosition[i].x;
					position.x = position.x * laenge;
					position.y = position.y * laenge;
					g2.setColor(stoneColor.darker());
					g2.fillOval(position.x + laenge / 4, position.y + laenge / 4, laenge / 2, laenge / 2);
					g2.setColor(stoneColor.darker().darker());
					g2.fillOval(position.x + laenge / 3, position.y + laenge / 3, laenge / 3, laenge / 3);
				}

			}
			g2.setColor(tmpColor);
		}

		private void tokenColorBlock(char tokenBlock, int block) {
			switch (tokenBlock) {
			case 'R':
				stoneColor = Color.RED;
				position.x = stoneBlock0[block].y;
				position.y = stoneBlock0[block].x;
				break;
			case 'B':
				stoneColor = Color.BLUE;
				position.x = stoneBlock1[block].y;
				position.y = stoneBlock1[block].x;
				break;
			case 'Y':
				stoneColor = Color.YELLOW;
				position.x = stoneBlock2[block].y;
				position.y = stoneBlock2[block].x;
				break;
			case 'P':
				stoneColor = Color.PINK;
				position.x = stoneBlock3[block].y;
				position.y = stoneBlock3[block].x;
				break;
			default:
				break;
			}
		}

		private void tokenColorHouse(char tokenHouse, int block) {

			switch (tokenHouse) {
			case 'R':
				stoneColor = Color.RED;
				position.x = this.stoneHause0[block].y;
				position.y = this.stoneHause0[block].x;
				break;
			case 'B':
				stoneColor = Color.BLUE;
				position.x = this.stoneHause1[block].y;
				position.y = this.stoneHause1[block].x;
				break;
			case 'Y':
				stoneColor = Color.YELLOW;
				position.x = this.stoneHause2[block].y;
				position.y = this.stoneHause2[block].x;
				break;
			case 'P':
				stoneColor = Color.PINK;
				position.x = this.stoneHause3[block].y;
				position.y = this.stoneHause3[block].x;
				break;
			default:
				break;
			}

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
			case 'Y':
				currentColor = Color.YELLOW;
				break;
			case 'P':
				currentColor = Color.PINK;
				break;
			case 'x':
				currentColor = Color.WHITE;
				break;
			default:
				break;

			}

			return currentColor;

		}

		@Override
		public void mouseClicked(MouseEvent e) {
			int indexOfClickedPoint = 0;
			for (int i = 0; i < this.figurePosition.length; i++) {

				int size = this.getWidth();
				clickedPoint = new MyPoint(e.getY() * 11 / size, e.getX() * 11 / size);
				int yOfClickedPoint = e.getY() * 11 / size;
				int xOfClickedPoint = e.getX() * 11 / size;

				if (yOfClickedPoint == this.figurePosition[i].x && xOfClickedPoint == this.figurePosition[i].y) {
					clickedPoint.setIdx(this.figurePosition[i].getIdx());
					indexOfClickedPoint = clickedPoint.getIdx();

					break;
				} else {
					clickedPoint.setIdx(-1);
					indexOfClickedPoint = clickedPoint.getIdx();

				}

			}
			if (c.moveStart())
				repaint();
			if (indexOfClickedPoint != -1 && c.move(indexOfClickedPoint % 40))
				repaint();


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
		this.frame.repaint();

	}

	@Override
	public void showDice(Player currentplayer, int dice) {

	}

}