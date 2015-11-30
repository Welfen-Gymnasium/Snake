package de.sese7.snake.frame;

import java.awt.Point;

import de.sese7.snake.graphics.Block;
import de.sese7.snake.main.Main;
import de.sese7.snake.snake.Schlange;

public class ItemCreator {
	
	public static Block snakeExpander, powerUp, obstacle[];
	public static int obstacles;
		
	public static void createSnakeBlock(){
		
		Point loc = randomPoint();
		loc.setLocation(loc.getX() + 5, loc.getY() + 5);
		
		snakeExpander = new Block(0);
		snakeExpander.setBounds((int) loc.getX(), (int) loc.getY(), 10, 10);
		snakeExpander.setVisible(true);
	}
	
	public static void moveSnakeBlock(){
		Point loc = randomPoint();
		loc.setLocation(loc.getX() + 5, loc.getY() + 5);
		
		snakeExpander.setLocation(loc);
		
	}
	
	public static void createPowerupBlock(){
		
		Point loc = randomPoint();
		loc.setLocation(loc.getX() + 5, loc.getY() + 5);
		
		powerUp = new Block(1);
		powerUp.setLocation(loc);
		powerUp.setSize(10, 10);
		powerUp.setVisible(true);
	}
	
	public static void movePowerupBlock(){
		Point loc = randomPoint();
		loc.setLocation(loc.getX() + 5, loc.getY() + 5);

		powerUp.setLocation(loc);
		
	}
	
	public static void spawnObstacle(){
		Point loc = randomPoint();
		
		obstacles++;
		int x = obstacles - 1;
		
		obstacle[x] = new Block(16);
		obstacle[x].setLocation(loc);
		obstacle[x].setSize(20, 20);
		obstacle[x].setVisible(true);
		
		Main.sf.add(obstacle[x]);
		obstacle[x].repaint();
	}

	private static Point randomPoint() {
		
		int randx = ((int)( Math.random() * Spielfeld.sfw + 1)) / 20;
		int px = 20 * randx;
		
		int randy = ((int)( Math.random() * Spielfeld.sfh + 1)) / 20;
		int py = 20 * randy;
		
		Point p = new Point(px, py);
		
		for(int i = 0; i < Schlange.registeredBodyParts; i++){
			
			if(new Point(Schlange.body[i].getX(), Schlange.body[i].getY()).equals(p)){
				return randomPoint();
			}			
		}
		
		for(int i = 0; i < ItemCreator.obstacles; i++){
			
			if(ItemCreator.obstacle[i].equals(p)){
				return randomPoint();
			}			
		}

		return p;
	}
	
}
