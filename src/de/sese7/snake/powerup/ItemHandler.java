package de.sese7.snake.powerup;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

import de.sese7.snake.frame.ItemCreator;
import de.sese7.stats.Stats;

public class ItemHandler {
	
	public static int nextPupTime;
	public static Timer t;
	
	public static void setTimer(){
		
		t = new Timer( 1000, new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				
				nextPupTime--;
				if(nextPupTime == 0){

					ItemCreator.movePowerupBlock();
					t.stop();
				}
				
			}

		});
	}
	
	public static int newTime(){
		
		Random rand = new Random();
		
		nextPupTime = rand.nextInt((20 - 5) + 1) + 5;
		int ret = nextPupTime;
		return ret; 
		
	}
	

	public static void pupHit() {
		
		ItemCreator.powerUp.setLocation(new Point(-50, -50));
		Random rand = new Random();
		int pup = rand.nextInt(5) + 1;
		
		switch(pup){
			case 1: ItemPowerups.speed(); Stats.points = Stats.points + 10; break;
			case 2: ItemPowerups.slow(); Stats.points = Stats.points + 5; break;
			case 3: ItemPowerups.lsd(); Stats.points = Stats.points + 15; break;
			case 4: ItemPowerups.invert(); Stats.points = Stats.points + 20; break;
			case 5: ItemPowerups.obstacle(); Stats.points = Stats.points + 10; break;
		} 
		
	}
}
