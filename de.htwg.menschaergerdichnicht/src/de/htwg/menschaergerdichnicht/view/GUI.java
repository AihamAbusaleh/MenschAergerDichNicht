package de.htwg.menschaergerdichnicht.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import de.htwg.menschaergerdichnicht.controller.Controller;
import de.htwg.menschaergerdichnicht.model.Player;
import de.htwg.util.observer.IObserver;

public class GUI extends JFrame implements  IObserver{

	 
 	private JFrame frame = null;	 
 	private SpielFeldGUI spielfeld = null;
	private JPanel  myPanel  = null;
	private JPanel  wurfelPanel  = null;
	private JButton wurfeln = null;
	private JLabel  Myturn = null;
	private Controller c;


	public GUI(Controller c) {
		this.c = c;
		c.registerObserver(this);
//		
//		this.frame = new JFrame("Mensch ärgere dich nicht");
//		this.frame.setMinimumSize(new Dimension(800, 600));
//		this.setBackground(Color.WHITE); 
//		this.setLayout(new FlowLayout());
//		this.frame.setUndecorated(false);
//		this.frame.setLocationByPlatform(true);
//        
//		this.myPanel = new JPanel();
////		Border b = BorderFactory.createTitledBorder("SpielFeld");
////		myPanel.setBorder(b);
////		myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.LINE_AXIS));		
////		 
//		this.wurfelPanel = new JPanel();
////		Border border = BorderFactory.createTitledBorder("Würfeln");
////	    wurfelPanel.setBorder(border);
////	    wurfelPanel.setLayout(new BoxLayout(wurfelPanel, BoxLayout.LINE_AXIS));		
//
//		this.Myturn = new JLabel();
//	 
//	//	wurfelPanel.setBorder(BorderFactory.createEmptyBorder(17, 17, 17, 17));
//	//	wurfelPanel.setLayout(new BoxLayout(wurfelPanel, BoxLayout.PAGE_AXIS));
//		
// 		
//		this.wurfeln = new JButton("würfeln");
//		this.wurfeln.addActionListener( 
//				new ActionListener() {
//					@Override
//					public void actionPerformed(ActionEvent arg0) {
//						int w = (int) (Math.random() * 6 + 1);
//						wurfeln.setText("Würfelzahl : " + w);
//						Myturn.setText(c.myTurn() + " ist an der Reihe" ); // TODO spielen, d.h wuerfeln blockieren 
//
//					}						 
//				});
//		
//		this.spielfeld = new SpielFeldGUI();
//		this.myPanel.add(this.spielfeld);
//		this.wurfelPanel.add(wurfeln);
//		this.wurfelPanel.add(Myturn);
//		
//		this.frame.setContentPane(this.myPanel);
//		this.frame.setContentPane(this.wurfelPanel);
//		this.frame.getContentPane().add(new SpielFeldGUI());
// 		this.frame.setResizable(false);
//		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
//		this.frame.setVisible(true);
//		this.frame.pack();  
		JFrame frame = new JFrame();
	    frame.getContentPane().add(new SpielFeldGUI());

	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(800,900);
	    frame.setVisible(true);
	}

	@SuppressWarnings("serial")
	private class SpielFeldGUI extends JPanel implements MouseListener {
		private byte [][] feld = null;
		private Point[] figurenPositionen = null;
 		
		public SpielFeldGUI(){
			this.setBackground(Color.WHITE);
			
			this.feld = new byte[][] { 
					{ 2, 2, 0, 0, 1, 1, 3, 0, 0, 3, 3 },
					{ 2, 2, 0, 0, 1, 3, 1, 0, 0, 3, 3 },
					{ 0, 0, 0, 0, 1, 3, 1, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 1, 3, 1, 0, 0, 0, 0 },
					{ 2, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1 },
					{ 1, 2, 2, 2, 2, 0, 4, 4, 4, 4, 1 },
					{ 1, 1, 1, 1, 1, 5, 1, 1, 1, 1, 4 },
					{ 0, 0, 0, 0, 1, 5, 1, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 1, 5, 1, 0, 0, 0, 0 },
					{ 5, 5, 0, 0, 1, 5, 1, 0, 0, 4, 4 },
					{ 5, 5, 0, 0, 5, 1, 1, 0, 0, 4, 4 } };
			
			this.figurenPositionen = new Point[]{
					new Point(4,0),new Point(4,1),new Point(4,2),new Point(4,3),new Point(4,4),new Point(3,4),
					new Point(2,4),new Point(1,4),new Point(0,4),new Point(0,5),new Point(0,6),new Point(1,6),
					new Point(2,6),new Point(3,6),new Point(4,6),new Point(4,7),new Point(4,8),new Point(4,9),
					new Point(4,10),new Point(5,10),new Point(6,10),new Point(6,9),new Point(6,8),new Point(6,7),
					new Point(6,6),new Point(7,6),new Point(8,6),new Point(9,6),new Point(10,6),new Point(10,5),
					new Point(10,4),new Point(9,4),new Point(8,4),new Point(7,4),new Point(6,4),new Point(6,3),
					new Point(6,2),new Point(6,1),new Point(6,0),new Point(5,0),new Point(5,1),new Point(5,2),
					new Point(5,3),new Point(5,4),new Point(1,5),new Point(2,5),new Point(3,5), new Point(4,5),
					new Point(5,6), new Point(5,7),new Point(5,8), new Point(5,9),new Point(6,5), new Point(7,5),
					new Point(8,5), new Point(9,5)
			};
			
 			this.addMouseListener(this);
 		}
		
		protected void paintComponent(Graphics g){
			super.paintComponent(g);
 			int laenge = this.getWidth()/11;
			Graphics2D g2 = (Graphics2D)g;
			Color tmpColor = g2.getColor();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			for(int i = 0; i< this.feld.length;i++){
				for(int j = 0; j < feld[i].length; j++){
					Color currentColor= berechneFarbe(feld[i][j]);
					if(currentColor == null) continue;
					g2.setColor(currentColor);
 					g2.fillOval(j*laenge+laenge/10, i*laenge+laenge/10, laenge-laenge/5, laenge-laenge/5);
					g2.setColor(Color.BLACK);
					g2.drawOval(j*laenge+laenge/10, i*laenge+laenge/10, laenge-laenge/5, laenge-laenge/5);			
				}
			}
//    			for(int i  = 0 ; i< 4; i++){
//				Color currentColor = berechneFarbe(i+2);
//				g2.setColor(currentColor.darker().darker());
//				int amStartZaehler = 0;
//				 
//					 
//				}
			}

		@Override
		public void mouseClicked(MouseEvent e) {
			
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		
		}

		private Color berechneFarbe(int i) {
			Color currentColor = null;
			switch(i){
			case 0:
				break;
			case 2:
				currentColor = Color.RED;
				break;
			case 3:
				currentColor = Color.BLUE;
				break;
			case 4:
				currentColor = Color.YELLOW;
				break;
			case 5:
				currentColor = Color.BLACK;
				break;
			case 1:
			default:
				currentColor = Color.WHITE;
				break;
			}
			return currentColor;
		}
		
	 
		@Override
		public void update(Player currentPlayer, boolean gameEnded) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void showDice(Player currentplayer, int dice) {
			// TODO Auto-generated method stub
			
		}
	
		public void repaintSpielfeld() {
			if (this.spielfeld!=null)
				this.spielfeld.repaint();
		}
	
}