package de.sese7.snake.event;


import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JPanel;

import de.sese7.snake.frame.ItemCreator;
import de.sese7.snake.frame.Spielfeld;
import de.sese7.snake.snake.Movement;
import de.sese7.snake.snake.Schlange;

public class SnakeCollideEvent {
	
	public static void checkBodyBlockCollision(){
		
		for(int i = 1; i < Schlange.registeredBodyParts; i++){
			if(Schlange.body[0].getLocation().equals(Schlange.body[i].getLocation())){
				Movement.allowMovement = false;
			}			
		}		
	}
	
	public static void checkObstacleCollision(){
		
		for(int i = 0; i < ItemCreator.obstacles; i++){
			if(Schlange.body[0].getLocation().equals(ItemCreator.obstacle[i].getLocation())){
				Movement.allowMovement = false;			
			}	
		}
	}
	
	public static boolean checkItemCollision(JPanel p){
		
		Point headlt = Schlange.body[0].getLocation();
		
		if(new Rectangle(headlt, new Dimension(20, 20)).intersects(new Rectangle(p.getLocation(), new Dimension(10, 10)))){
			return true;
			
		}
		return false;
		
	}

	public static void checkOutOfField() {
		
		if(Schlange.body[0].getLocation().getX() >= Spielfeld.sfw || Schlange.body[0].getLocation().getX() < 0
		|| Schlange.body[0].getLocation().getY() >= Spielfeld.sfh || Schlange.body[0].getLocation().getY() < 0){
				Movement.allowMovement = false;		
		}				
	}
}