package de.sese7.snake.powerup;

import java.awt.Point;

import de.sese7.snake.frame.Spielfeld;
import de.sese7.snake.main.Main;
import de.sese7.snake.ressources.Block;
import de.sese7.snake.snake.Schlange;

public class ItemCreator {
	
	public static Block snakeExpander, powerUp, obstacle[];
	public static int obstacles;
	
	public static Block createPlainGrassBlock(int x, int y){
		Block plainGrass = new Block(17);
		plainGrass.setBounds(x, y, 20, 20);
		plainGrass.setVisible(true);
		return plainGrass;
	}
	
	public static Block createGrassBlock(int x, int y){
		Block grass = new Block(18);
		grass.setBounds(x, y, 20, 20);
		grass.setVisible(true);
		return grass;
	}
	
	public static void createSnakeBlock(){
		
		Point loc = randomPoint();
		loc.setLocation(loc.getX(), loc.getY());
		
		snakeExpander = new Block(0);
		snakeExpander.setBounds((int) loc.getX(), (int) loc.getY(), 20, 20);
		snakeExpander.setVisible(true);
	}
	
	public static void moveSnakeBlock(){
		Point loc = randomPoint();
		loc.setLocation(loc.getX(), loc.getY());
		
		snakeExpander.setLocation(loc);
		
	}
	
	public static void createPowerupBlock(){
		
		Point loc = randomPoint();
		loc.setLocation(loc.getX(), loc.getY());
		
		powerUp = new Block(1);
		powerUp.setLocation(loc);
		powerUp.setSize(20, 20);
		powerUp.setVisible(true);
	}
	
	public static void movePowerupBlock(){
		Point loc = randomPoint();
		loc.setLocation(loc.getX(), loc.getY());

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

	public static void resetObstacels() {
		for(int i = 0; i < obstacles; i++){
			obstacle[i].setVisible(false);
			obstacle[i] = null;
		}
		obstacles = 0;		
	}
	
}
