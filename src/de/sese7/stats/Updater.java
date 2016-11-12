package de.sese7.stats;

import de.sese7.snake.frame.Stats;
import de.sese7.snake.main.Main;
import de.sese7.snake.powerup.ItemCreator;
import de.sese7.snake.powerup.ItemHandler;
import de.sese7.snake.powerup.ItemPowerups;
import de.sese7.snake.snake.Expander;
import de.sese7.snake.snake.Movement;
import de.sese7.snake.snake.Schlange;

public class Updater {
	
	public static boolean win;
	
	public static void update(){
		
		double speed = (double) 1000 / (double) Movement.t.getDelay();
		
		Stats.lenght.setText("Länge: " + Schlange.registeredBodyParts);
		Stats.foodl.setText("Food: " + Stats.food);
		Stats.speedl.setText("Speed: " + Math.round(speed * 100.00) / 100.00);
		Stats.nextpup.setText("Next Powerup: " + ItemHandler.nextPupTime + "s");
		Stats.poweruptype.setText("Typ: " + Stats.pupname);
		Stats.powerupremtime.setText("Time: " +ItemPowerups.remPupTime + "s");
		Stats.pointsl.setText("Points: " +Stats.points);
		Stats.obstacles.setText("Obstacles: " +ItemCreator.obstacles);
		
		if(Schlange.registeredBodyParts + Expander.futureExpansions - ItemCreator.obstacles >= 3600){
			
			Movement.allowMovement = false;
			win = true;
		}
		
		Main.mainframe.requestFocus();
		
	}
	
}
