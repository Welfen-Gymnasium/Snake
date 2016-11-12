package de.sese7.snake.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.JPanel;

import de.sese7.snake.main.Main;
import de.sese7.snake.powerup.ItemCreator;
import de.sese7.snake.powerup.ItemHandler;
import de.sese7.snake.powerup.ItemPowerups;
import de.sese7.snake.ressources.Block;
import de.sese7.snake.ressources.Images;
import de.sese7.snake.snake.Expander;
import de.sese7.snake.snake.Movement;
import de.sese7.snake.snake.Schlange;

@SuppressWarnings("serial")
public class Spielfeld extends JPanel{
	
	public Schlange sn;
	public Graphics gr;
	//private Background bg;
	public static int currHeadX, currHeadY;
	public static int sfw, sfh;
	public static boolean pause;
	
	public static Color bgcolor;
	
	public static JLabel lose, lose2;
	//public static JPanel bggrass;
	//private static Block bgblocks[];
	
	public Spielfeld(String title){
		//super(title);
		
		regVar();
		
		setLayout(null);
			
		regGraphics();
		Images.regImg();
		
		//generateGrass();
		
		regDefaultSnake();			
		
		ItemCreator.createSnakeBlock();
		this.add(ItemCreator.snakeExpander);
		
		ItemCreator.createPowerupBlock();
		this.add(ItemCreator.powerUp);		
		
		Movement.setTimer();
			
		ItemHandler.setTimer();
		ItemPowerups.inverted = false;
		
		//Debug
		ItemCreator.snakeExpander.repaint();
		ItemCreator.powerUp.repaint();
		
	}
	@Override
	protected void paintComponent(Graphics g){ 
		super.paintComponent(g); 	
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(bgcolor);
		g2.fillRect(0, 0, sfw, sfh);
		
	} 
	
	/*private void generateGrass(){
		Random r = new Random();
		int rand;
		for(int x=0; x<sfw; x += 20){
			for(int y=0; y<sfh; y += 20){
				
				rand = r.nextInt(20);
				
				switch(rand){
					case 0: bggrass.add(ItemCreator.createGrassBlock(x, y)); break;
					default: bggrass.add(ItemCreator.createPlainGrassBlock(x, y)); break;
				}
			}
		}
	}*/
	
	private void regDefaultSnake(){
		int s = 10;

		int x = currHeadX;
		int y = currHeadY;
	
		for(int i = 0; i < s; i++){	
			JPanel bp = Schlange.regNewBodyPart(i, x, y);
			
			x = x - 20;
			
			this.add(bp);
		}
	}
	
	private void regVar(){
		bgcolor = new Color(222,222,222);
		sfh = 600;
		sfw = 600;
		currHeadX = 280;
		currHeadY = 280;
		pause = true;
		ItemCreator.obstacles = 0;
		ItemCreator.obstacle = new Block[3600];
		ItemHandler.nextPupTime = 0;
		Schlange.registeredBodyParts = 0;
		Schlange.body = new Block[3600];
		Expander.counter = new int[3600];
		Expander.colorer = new int[3600];
		Movement.timeout = 200;
		Movement.oldmove = new Point[3600];
		Movement.curxmove = 1;
		Movement.curymove = 0;
		Movement.allowMovement = true;
		Movement.acceptTurn = true;
	}
	
	public static void pause(){
		
		pause = ((pause) ? false : true);
		
		 if(!pause){
			 lose.setText("Verloren");
			 lose.setFont(new Font("Orbitron", Font.BOLD, 95));
			 lose.setLocation(30, 150);
			 lose.setVisible(false);
			 lose2.setVisible(false);
			 lose.setForeground(Color.RED);
			 Movement.allowMovement = true;
		 }
		 else if(Movement.allowMovement){
			 Movement.allowMovement = false;
			 lose.setText("Pause");
			 lose.setForeground(Color.BLUE);
			 lose.setLocation(120, 150);
			 lose.setVisible(true);			 
		 }
		 
	}

	public void setColor(Color color) {
	        bgcolor = color;
	        Main.sf.repaint();
	}
	
	public void regGraphics() {
		
		/*bggrass = new JPanel();
		bggrass.setBounds(0, 0, sfw, sfh);
		bggrass.setVisible(true);
		bggrass.setLayout(null);
		this.add(bggrass);*/
		
		lose = new JLabel("Verloren");
		lose.setFont(new Font("Orbitron", Font.BOLD, 85));
		lose.setBounds(30, 150, 550, 160);
		lose.setVisible(false);
		lose.setForeground(Color.RED);
		this.add(lose);
		
		lose2 = new JLabel("Press Enter to restart / Escape to quit");
		lose2.setFont(new Font("Orbitron", Font.BOLD, 22));
		lose2.setBounds(60, 250, 600, 160);
		lose2.setVisible(false);
		lose2.setForeground(Color.BLACK);
		this.add(lose2);
		
	}
	
}
