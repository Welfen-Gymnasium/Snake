package de.sese7.snake.snake;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import de.sese7.mysql.Login;
import de.sese7.snake.event.SnakeCollideEvent;
import de.sese7.snake.frame.Highscore;
import de.sese7.snake.frame.Spielfeld;
import de.sese7.snake.frame.Stats;
import de.sese7.snake.powerup.ItemCreator;
import de.sese7.snake.powerup.ItemHandler;
import de.sese7.snake.ressources.Sounds;
import de.sese7.stats.Updater;

public class Movement {
	
	public static int curxmove, curymove, timeout;
	public static Point oldmove[];
	public static boolean acceptTurn, allowMovement;
	public static Timer t;
	
	public static void setTimer(){
		t = new Timer( timeout, new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				
			  if(allowMovement){
				  move();
				  Expander.checkExpand();
				  Updater.update();
			  }
			  else if(!Updater.win && !Spielfeld.pause){
				  t.stop();

				  if(Login.user != 0){
					  Highscore.insertResults();
				  }
				  
				  Spielfeld.lose.setVisible(true);
				  Spielfeld.lose2.setVisible(true);
			  }			  
			  else if(!Spielfeld.pause){
				  t.stop();
				  Spielfeld.lose.setText("Gewonnen");
				  Spielfeld.lose.setForeground(Color.GREEN);
				  Spielfeld.lose.setLocation(50, 150);
				  Spielfeld.lose.setVisible(true);
				  Spielfeld.lose2.setVisible(true);
				  
			  }
			}
		});
	}
	
	private static void move(){
		
		if(SnakeCollideEvent.checkOutOfField()){
			return;
		}
		if(SnakeCollideEvent.checkObstacleCollision()){
			return;
		}
		
		int lenght = Schlange.registeredBodyParts;
		int xmove, ymove;

		switch(curxmove){
			case 1: xmove = 20; break;
			case -1: xmove = -20; break;
			default: xmove = 0;
		}
		
		switch(curymove){
			case 1: ymove = 20; break;
			case -1: ymove = -20; break;
			default: ymove = 0;
		}
		
		for(int i = 0; i < lenght; i++){
			
			oldmove[i] = new Point(Schlange.body[i].getX(), Schlange.body[i].getY());
			
			if(i == 0){
				
				int newx = Schlange.body[i].getX() + xmove;
				int newy = Schlange.body[i].getY() + ymove;
				
				Schlange.body[i].setLocation(newx, newy);

				Spielfeld.currHeadX = newx;
				Spielfeld.currHeadY = newy;
			}
			else{
				Schlange.body[i].setLocation(oldmove[i - 1]);		
			}
						
		}
		for(int i = 0; i < lenght; i++){
			Schlange.updateBlocks(i);
		}
		
		if(SnakeCollideEvent.checkItemCollision(ItemCreator.snakeExpander)){
			Sounds.playSound("collect/eat.wav");
			Stats.food++;
			Stats.points = Stats.points + 5;
			ItemCreator.moveSnakeBlock();
			Expander.futureExpansions++;
			Expander.counter[Expander.futureExpansions - 1] = Schlange.registeredBodyParts;
		}
		if(SnakeCollideEvent.checkItemCollision(ItemCreator.powerUp)){
			Sounds.playSound("collect/collect.wav");
			ItemHandler.pupHit();
		}
		SnakeCollideEvent.checkBodyBlockCollision();
		acceptTurn = true;
		
	}
		
}
