package de.sese7.snake.event;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JPanel;

import de.sese7.snake.frame.Spielfeld;
import de.sese7.snake.powerup.ItemCreator;
import de.sese7.snake.powerup.ItemPowerups;
import de.sese7.snake.ressources.Sounds;
import de.sese7.snake.snake.Movement;
import de.sese7.snake.snake.Schlange;

public class SnakeCollideEvent {
	
	public static void checkBodyBlockCollision(){
		
		for(int i = 1; i < Schlange.registeredBodyParts; i++){
			if(Schlange.body[0].getLocation().equals(Schlange.body[i].getLocation())){
				Sounds.playSound("collect/eat.wav");
				Movement.allowMovement = false;
			}			
		}		
	}
	
	public static boolean checkObstacleCollision(){
		
		int xmove, ymove;
		
		switch(Movement.curxmove){
			case 1: xmove = 20; break;
			case -1: xmove = -20; break;
			default: xmove = 0;
		}
	
		switch(Movement.curymove){
			case 1: ymove = 20; break;
			case -1: ymove = -20; break;
			default: ymove = 0;
		}
		
		for(int i = 0; i < ItemCreator.obstacles; i++){
			if(new Point((int) Schlange.body[0].getLocation().getX() + xmove, (int) Schlange.body[0].getLocation().getY() + ymove).equals(ItemCreator.obstacle[i].getLocation())){
				if(ItemPowerups.steelJB){
					Sounds.playSound("collect/eat.wav");
					ItemCreator.obstacle[i].setVisible(false);
										
					for(int n = i; n < ItemCreator.obstacles - 1; n++){
						ItemCreator.obstacle[n] = ItemCreator.obstacle[n + 1];
					}
					
					ItemCreator.obstacle[ItemCreator.obstacles] = null;
					ItemCreator.obstacles--;
					ItemPowerups.remSteelJawbone();
				}
				else {
					Movement.allowMovement = false;
					return true;
				}
			}	
		}
		return false;
	}
	
	public static boolean checkItemCollision(JPanel p){
		
		Point headlt = Schlange.body[0].getLocation();
		
		if(new Rectangle(headlt, new Dimension(20, 20)).intersects(new Rectangle(p.getLocation(), new Dimension(10, 10)))){
			return true;
			
		}
		return false;
		
	}

	public static boolean checkOutOfField() {
		
		int xmove, ymove;
		
		switch(Movement.curxmove){
			case 1: xmove = 20; break;
			case -1: xmove = -20; break;
			default: xmove = 0;
		}
		
		switch(Movement.curymove){
			case 1: ymove = 20; break;
			case -1: ymove = -20; break;
			default: ymove = 0;
		}
		
		if(Schlange.body[0].getLocation().getX() + xmove >= Spielfeld.sfw || Schlange.body[0].getLocation().getX() + xmove < 0
		|| Schlange.body[0].getLocation().getY() + ymove >= Spielfeld.sfh || Schlange.body[0].getLocation().getY() + ymove < 0){
				Movement.allowMovement = false;	
				return true;
		}	
		
		return false;		
		
	}
}