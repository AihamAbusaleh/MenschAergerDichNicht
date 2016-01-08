package de.htwg.menschaergerdichnicht.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import de.htwg.menschaergerdichnicht.controller.Controller;
import de.htwg.menschaergerdichnicht.model.Player;
import de.htwg.util.observer.IObserver;

@SuppressWarnings("serial")
public class GUI extends JFrame implements  IObserver{

	 
 	private JFrame frame;	 
 	private JPanel  wurfelPanel;
 	private JLabel  Myturn  ;
 	private Controller c;


	public GUI(Controller c) {
		this.c = c;
		c.registerObserver(this);
 		
 		frame = new JFrame("Mensch ärgere dich nicht");
     
 		this.wurfelPanel = new JPanel();
		this.Myturn = new JLabel(c.wuerfeln());
		 
		this.wurfelPanel.setBackground(Color.MAGENTA);
		 
//		this.wurfeln = new JButton("würfeln");
//		this.wurfeln.addActionListener( 
//				new ActionListener() {
//					@Override
//					public void actionPerformed(ActionEvent arg0) {
// //						int w = (int) (Math.random() * 6 + 1);
//						wurfeln.setText("Würfelzahl : " + c.dice() );
//						Myturn.setText(c.myTurn() + " ist an der Reihe" ); // TODO spielen, d.h wuerfeln blockieren 
////						while(){ TODO
////						wurfeln.setEnabled(false);
////						}
//					}						 
//				});
//		
	
	
 		this.wurfelPanel.add(Myturn);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(wurfelPanel, BorderLayout.NORTH);
 		frame.getContentPane().add(new SpielFeldGUI());
 	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
 		frame.setSize(600, 700);
		frame.setVisible(true);
 		frame.setResizable(false);
 
	   
	}

	@SuppressWarnings("serial")
	private class SpielFeldGUI extends JPanel implements MouseListener {
		private byte [][] feld = null;
		private MyPoint[] figurenPositionen = null;
 		
		public SpielFeldGUI(){
			this.setBackground(Color.WHITE);
	 
			
			this.feld = new byte[][] { 
					{ 'R', 'R', '0', '0', 'x', 'x', 'x', '0', '0', 'B', 'B' },
					{ 'R', 'R', '0', '0', 'x', ' ', 'x', '0', '0', 'B', 'B' },
					{ '0', '0', '0', '0', 'x', ' ', 'x', '0', '0', '0', '0' },
					{ '0', '0', '0', '0', 'x', ' ', 'x', '0', '0', '0', '0' },
					{ 'x', 'x', 'x', 'x', 'x', ' ', 'x', 'x', 'x', 'x', 'x' },
					{ 'x', ' ', ' ', ' ', ' ', '0', ' ', ' ', ' ', ' ', 'x' },
					{ 'x', 'x', 'x', 'x', 'x', ' ', 'x', 'x', 'x', 'x', 'x' },
					{ '0', '0', '0', '0', 'x', ' ', 'x', '0', '0', '0', '0' },
					{ '0', '0', '0', '0', 'x', ' ', 'x', '0', '0', '0', '0' },
					{ 'P', 'P', '0', '0', 'x', ' ', 'x', '0', '0', 'G', 'G' },
					{ 'P', 'P', '0', '0', 'x', 'x', 'x', '0', '0', 'G', 'G' } };
			
			this.figurenPositionen = new MyPoint[]{
					new MyPoint(4,0,30),new MyPoint(4,1,31),new MyPoint(4,2,32),new MyPoint(4,3,33),new MyPoint(4,4,34),new MyPoint(3,4,35),
					new MyPoint(2,4,36),new MyPoint(1,4,37),new MyPoint(0,4,38),new MyPoint(0,5,39),new MyPoint(0,6,0),new MyPoint(1,6,1),
					new MyPoint(2,6,2),new MyPoint(3,6,3),new MyPoint(4,6,4),new MyPoint(4,7,5),new MyPoint(4,8,6),new MyPoint(4,9,7),
					new MyPoint(4,10,8),new MyPoint(5,10,9),new MyPoint(6,10,10),new MyPoint(6,9,11),new MyPoint(6,8,12),new MyPoint(6,7,13),
					new MyPoint(6,6,14),new MyPoint(7,6,15),new MyPoint(8,6,16),new MyPoint(9,6,17),new MyPoint(10,6,18),new MyPoint(10,5,19),
					new MyPoint(10,4,20),new MyPoint(9,4,21),new MyPoint(8,4,22),new MyPoint(7,4,23),new MyPoint(6,4,24),new MyPoint(6,3,25),
					new MyPoint(6,2,26),new MyPoint(6,1,27),new MyPoint(6,0,28),new MyPoint(5,0,29),new MyPoint(5,1,0),new MyPoint(5,2,0),
					new MyPoint(5,3,0),new MyPoint(5,4,0),new MyPoint(1,5,0),new MyPoint(2,5,0),new MyPoint(3,5,0), new MyPoint(4,5,0),
					new MyPoint(5,6,0), new MyPoint(5,7,0),new MyPoint(5,8,0), new MyPoint(5,9,0),new MyPoint(6,5,0), new MyPoint(7,5,0),
					new MyPoint(8,5,0), new MyPoint(9,5,0)
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
					Color currentColor= feldColors(feld[i][j]);
					if(currentColor == null) continue;
					g2.setColor(currentColor);
 					g2.fillOval(j*laenge+laenge/10, i*laenge+laenge/10, laenge-laenge/5, laenge-laenge/5);
					g2.setColor(Color.BLACK);
					g2.drawOval(j*laenge+laenge/10, i*laenge+laenge/10, laenge-laenge/5, laenge-laenge/5);			
				}
			}
 
//			for(int i = 0; i< this.feld.length;i++){
//				MyPoint position = new MyPoint();
//				for(int j = 0; j < feld[i].length; j++){
//					//Color currentColor= feldColors(feld[i][j]);
//					if(c.getTokenColor(feld[i][j]) == 'R'){
//						
//					}
//						if(currentColor == Color.RED){
//							g2.setColor(Color.RED.darker().darker());
//							
//						}
//						
//				}
//				position.x = position.x*laenge;
//				position.y = position.y*laenge;
//				g2.fillOval(position.x+laenge/4, position.y+laenge/4, laenge/2, laenge/2);
//				g2.fillOval(position.x+laenge/3, position.y+laenge/3, laenge/3, laenge/3);
//				
//			}
//
//			for(int i  = 0 ; i< 4; i++){
//	 
//				int zaehler = 0;
//			
//				for(int figur =0 ; figur < 4; figur++){
//						MyPoint position = new MyPoint();				
//							switch(i){
//							case 0:
//								position.x = 0;
//								position.y = 0;
//							//	position.idx=000;
//								 g2.setColor(Color.RED.darker().darker());
//
//								break;
//							case 1:
//								position.x = 9;
//								position.y = 0;
//							//	position.idx=100;
//								 g2.setColor(Color.BLUE.darker().darker());
//
//
//								break;
//							case 2:
//								position.x = 9;
//								position.y = 9;
//							//	position.idx=200;
//								 g2.setColor(Color.YELLOW.darker());
//
//
//								break;
//							case 3:
//							default:
//								position.x = 0;
//								position.y = 9;
//							//	position.idx=300;
//								 g2.setColor(Color.PINK.darker());
//
//								break;
//							}
//							switch(zaehler){
//							case 3:
//								position.y++;
//								break;
//							case 1:
//								position.x++;
//								break;
//							case 2:
//								position.x++;
//								position.y++;
//								break;
//							
//							case 0:
//							default:
//								break;
//							}
//							zaehler++;
//						
//						position.x = position.x*laenge;
//						position.y = position.y*laenge;
// 						g2.fillOval(position.x+laenge/4, position.y+laenge/4, laenge/2, laenge/2);
// 						g2.fillOval(position.x+laenge/3, position.y+laenge/3, laenge/3, laenge/3);
//					
//				}
//			}	
			g2.setColor(tmpColor);	

		}

		@Override
		public void mouseClicked(MouseEvent e) {
			
  			
				int size = this.getWidth();
				MyPoint clickedPoint =  new MyPoint(e.getY()*11/size,e.getX()*11/size);
				int l = e.getY()*11/size;
				int m = e.getX()*11/size;
				
				for (int i = 0; i < this.figurenPositionen.length; i++) {					 
					if(l == this.figurenPositionen[i].x && m == this.figurenPositionen[i].y){
						
						clickedPoint.setIdx(this.figurenPositionen[i].getIdx()); 	
					 
						  if(c.moveStart()){
							  frame.repaint();
			  					if(c.myTurn().equals("Rot")  ){
 			 						c.move(this.figurenPositionen[clickedPoint.getIdx()].idx+10);
 			 						repaint();
			 					}
			 			  }

						break;
					}
				}
				
				

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

		private Color feldColors(int i) {
			Color currentColor = null;
 			switch(i){
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
			case ' ':
				currentColor = Color.BLACK;
			default:
				break;
			}
		
			return currentColor;
			
			
		}
		
	 
		@Override
		public void update(Player currentPlayer, boolean gameEnded) {
				frame.repaint();
				
			
		}
		@Override
		public void showDice(Player currentplayer, int dice) {
			// TODO Auto-generated method stub
	 
			
		}
	 
	
}