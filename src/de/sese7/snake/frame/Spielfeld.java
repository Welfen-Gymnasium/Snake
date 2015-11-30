package de.sese7.snake.frame;
import de.sese7.snake.event.KeyEventListener;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.sese7.snake.graphics.Block;
import de.sese7.snake.graphics.Images;
import de.sese7.snake.powerup.ItemHandler;
import de.sese7.snake.powerup.ItemPowerups;
import de.sese7.snake.snake.Expander;
import de.sese7.snake.snake.Movement;
import de.sese7.snake.snake.Schlange;

@SuppressWarnings("serial")
public class Spielfeld extends JFrame{
	
	public Schlange sn;
	public Graphics gr;
	public static int currHeadX, currHeadY;
	public static int sfw, sfh;
	
	public static JLabel lose, lose2;
	public static JPanel head, tongue;
	
	public Spielfeld(String title){
		super(title);
		
		regVar();
		regWin();
			
		regGraphics();
		Images.regImg();

		sn = new Schlange();
		regDefaultSnake();
		
		ItemCreator.createSnakeBlock();
		this.add(ItemCreator.snakeExpander);
		
		ItemCreator.createPowerupBlock();
		this.add(ItemCreator.powerUp);
		
		Movement.setTimer();
		Movement.t.start();
		
		ItemHandler.setTimer();
		ItemPowerups.inverted = false;
		
		addKeyListener(new KeyEventListener(1));
		
		//Debug
		ItemCreator.snakeExpander.repaint();
		ItemCreator.powerUp.repaint();
		
	}
	
	
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
		sfh = 600;
		sfw = 600;
		currHeadX = 280;
		currHeadY = 280;
		ItemCreator.obstacles = 0;
		ItemCreator.obstacle = new Block[3600];
		ItemHandler.nextPupTime = 0;
		Expander.counter = new int[3600];
		Expander.colorer = new int[3600];
		Movement.timeout = 200;
		Movement.oldmove = new Point[3600];
		Movement.curxmove = 1;
		Movement.curymove = 0;
		Movement.allowMovement = true;
		Movement.acceptTurn = true;
	}
	
	private void regWin(){
		setTitle("Spielfeld");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		getContentPane().setBackground(new Color(222, 222, 222));
		getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		setVisible(true);	
		setAlwaysOnTop(true);
		setResizable(false);
		setSize(sfw, sfh);
		setLocationRelativeTo(null);
		setLayout(null);
	}


	public void regGraphics() {
		lose = new JLabel("Verloren");
		lose.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 95));
		lose.setBounds(110, 150, 500, 160);
		lose.setVisible(false);
		lose.setForeground(Color.RED);
		this.add(lose);
		
		lose2 = new JLabel("Press Enter to restart / Escape to quit");
		lose2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
		lose2.setBounds(75, 250, 500, 160);
		lose2.setVisible(false);
		lose2.setForeground(Color.BLACK);
		this.add(lose2);
		
	}
	
}
