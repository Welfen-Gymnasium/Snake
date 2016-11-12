package onl.deepspace.stats;

import onl.deepspace.snake.Expander;
import onl.deepspace.snake.Movement;
import onl.deepspace.snake.Schlange;
import onl.deepspace.snake.frame.Stats;
import onl.deepspace.snake.main.Main;
import onl.deepspace.snake.powerup.ItemCreator;
import onl.deepspace.snake.powerup.ItemHandler;
import onl.deepspace.snake.powerup.ItemPowerups;

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
