package de.sese7.snake.powerup;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import de.sese7.snake.frame.ItemCreator;
import de.sese7.snake.main.Main;
import de.sese7.snake.snake.Movement;
import de.sese7.stats.Stats;

public class ItemPowerups {
	
	public static int remPupTime;
	private static int oldmovement, c;
	private static Timer t, tLSD;
	private static Color color;
	public static boolean inverted;
	
	public static void speed(){
		remPupTime = 3;
		setTimer(1);
		t.start();
		Stats.pupname = "Speed";
		
		oldmovement = Movement.t.getDelay();
		Movement.t.setDelay(75);
	}

	public static void slow(){
		remPupTime = 5;
		setTimer(2);
		t.start();
		Stats.pupname = "Slowness";
		
		oldmovement = Movement.t.getDelay();
		Movement.t.setDelay(300);
	}
	
	public static void lsd(){
		Stats.pupname = "LSD";
		remPupTime = 6;
		setTimer(3);
		t.start();
		
		tLSD = new Timer( 10, new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				
				switch(c){
					case 0: color = Color.RED; break;
					case 1: color = Color.GREEN; break;
					case 2: color = Color.YELLOW; break;
					case 3: color = Color.BLUE; break;
					case 4: color = Color.PINK; break;
					case 5: color = Color.ORANGE; break;
					case 6: color = Color.MAGENTA; break;
					case 7: color = Color.CYAN; break;
				}
				
				Main.sf.getContentPane().setBackground(color);
				
				c = ((c == 7) ? 0 : c + 1);
				
			}
		});
		
		tLSD.start();
	}

	public static void invert(){
		remPupTime = 5;
		setTimer(4);
		t.start();
		Stats.pupname = "Inverted";
		Main.sf.getContentPane().setBackground(new Color(255, 222, 222));
		inverted = true;
		
	}
	
	public static void obstacle(){
		ItemCreator.spawnObstacle();
		Stats.pupname = "- (Obstacle)";
		ItemHandler.newTime();
		ItemHandler.t.start();
	}

	private static void setTimer(final int x) {
		t = new Timer( 1000, new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				
				if(Movement.allowMovement){
					remPupTime--;
					if(remPupTime == 0){
						switch(x){
							case 1: remspeed(); break;
							case 2: remslow(); break;
							case 3: remlsd(); break;
							case 4: reminvert(); break;
						}
					
						ItemHandler.newTime();
						ItemHandler.t.start();
						t.stop();
					}
				}
				
			}
		});		
	}
	
	private static void remspeed() {
		Stats.pupname = "-";
		Movement.t.setDelay(oldmovement);
	}
	
	private static void remslow() {
		Stats.pupname = "-";
		Movement.t.setDelay(oldmovement);
	}
	
	private static void remlsd(){
		Stats.pupname = "-";
		tLSD.stop();
		Main.sf.getContentPane().setBackground(new Color(222, 222, 222));
	}
	
	private static void reminvert() {
		Stats.pupname = "-";
		inverted = false;
		Main.sf.getContentPane().setBackground(new Color(222, 222, 222));
	}
	
}
