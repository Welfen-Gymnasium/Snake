package onl.deepspace.snake;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import onl.deepspace.mysql.Login;
import onl.deepspace.snake.event.SnakeCollideEvent;
import onl.deepspace.snake.frame.Highscore;
import onl.deepspace.snake.frame.Spielfeld;
import onl.deepspace.snake.frame.Stats;
import onl.deepspace.snake.powerup.ItemCreator;
import onl.deepspace.snake.powerup.ItemHandler;
import onl.deepspace.snake.ressources.Sounds;
import onl.deepspace.stats.Updater;

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
