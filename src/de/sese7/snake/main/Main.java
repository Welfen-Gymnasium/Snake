package de.sese7.snake.main;
import javax.swing.JFrame;

import de.sese7.snake.frame.Spielfeld;
import de.sese7.snake.powerup.ItemHandler;
import de.sese7.snake.snake.Movement;
import de.sese7.stats.Stats;

public class Main {
	
	public static JFrame sf, stf;
	
	public static void main(String args[]){
		start();
	}

	private static void start() {
		sf = new Spielfeld("Spielfeld");
		stf = new Stats("Statistiken");
		
		sf.requestFocus();
	}
	
	public static void restart(){
		
		Movement.t.stop();
		ItemHandler.t.stop();
		
		sf.dispose();
		stf.dispose();
		
		start();
		
	}
	
}
