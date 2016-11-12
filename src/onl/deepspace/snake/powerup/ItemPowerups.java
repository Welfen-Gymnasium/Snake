package onl.deepspace.snake.powerup;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

import onl.deepspace.snake.Expander;
import onl.deepspace.snake.Movement;
import onl.deepspace.snake.Schlange;
import onl.deepspace.snake.frame.Stats;
import onl.deepspace.snake.main.Main;
import onl.deepspace.snake.ressources.Sounds;

public class ItemPowerups {
	
	public static int remPupTime;
	private static int oldmovement, r, g, b;
	public static Timer t, tLSD;
	private static Color color;
	private static Random rand;
	public static boolean inverted, adds, steelJB, secSteelJB;
	
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
		remPupTime = 7;
		Sounds.playSound("LSD.wav");
		setTimer(3);
		t.start();
		adds = true;
		r = g = b = 0;
		rand = new Random();
		
		tLSD = new Timer( 1, new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				
				/*switch(c){
					case 0: color = Color.RED; break;
					case 1: color = Color.GREEN; break;
					case 2: color = Color.YELLOW; break;
					case 3: color = Color.BLUE; break;
					case 4: color = Color.PINK; break;
					case 5: color = Color.ORANGE; break;
					case 6: color = Color.MAGENTA; break;
					case 7: color = Color.CYAN; break;
				}*/
				//while(colorchanger){
				int ira;
				if(adds){
					ira = rand.nextInt((3 - 1) + 1) + 1;
				}
				else{
					ira = rand.nextInt((6 - 4) + 1) + 4;
				}
				
				switch(ira){
					case 1: r = ((r != 255) ? r + 5 : 255); break;
					case 2: g = ((g != 255) ? g + 5 : 255); break;
					case 3: b = ((b != 255) ? b + 5 : 255); break;
					case 4: r = ((r != 0) ? r - 5 : 0); break;
					case 5: g = ((g != 0) ? g - 5 : 0); break;
					case 6: b = ((b != 0) ? b - 5 : 0); break;
				}
				
				if(r == 255 || g == 255 || b == 255){
					adds = false;
				}
				else if (r == 0 || g == 0 || b == 0){
					adds = true;
				}
				
				//System.out.println(r + " " + g + " " + b);
				color = new Color(r, g, b);
			
				Main.sf.setColor(color);
				
				//c = ((c == 7) ? 0 : c + 1);
				
			}
		});
		
		tLSD.start();
	}

	public static void invert(){
		remPupTime = 5;
		setTimer(4);
		t.start();
		Stats.pupname = "Alcohol";
		Main.sf.setColor(new Color(255, 222, 222));
		inverted = true;
		
	}
	
	public static void obstacle(){
		ItemCreator.spawnObstacle();
		Stats.pupname = "- (Obstacle)";
		ItemHandler.newTime();
		ItemHandler.t.start();
	}
	
	public static void steelJawbone(){
		if(ItemCreator.obstacles != 0){
			Stats.pupname = "Jawbone";
			steelJB = true;
			secSteelJB = true;
		} else {
			Stats.pupname = "- (Jawbone)";
			Stats.points += 10;
			ItemHandler.newTime();
			ItemHandler.t.start();
		}
	}
	//Kannst du selber weiter machen, is in Movement viel zu kompliziert und überhaupt nicht wiederverwendbar programmiert :P
	public static void superFood(){
		for(int i=0; i<3; i++){
			Stats.food++;
			Expander.futureExpansions++;
			Expander.counter[Expander.futureExpansions - 1] = Schlange.registeredBodyParts;
		}
		Stats.pupname = "- (Super Food)";
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
							case 5: remSteelJawbone(); break;
						}
					
						ItemHandler.newTime();
						ItemHandler.t.start();
						t.stop();
					}
				}
				
			}
		});		
	}
	
	public static void remspeed() {
		Stats.pupname = "-";
		Movement.t.setDelay(oldmovement);
	}
	
	public static void remslow() {
		Stats.pupname = "-";
		Movement.t.setDelay(oldmovement);
	}
	
	public static void remlsd(){
		Stats.pupname = "-";
		tLSD.stop();
		Main.sf.setColor(new Color(222, 222, 222));
	}
	
	public static void reminvert() {
		Stats.pupname = "-";
		inverted = false;
		Main.sf.setColor(new Color(222, 222, 222));
	}
	
	public static void remSteelJawbone(){
		if(secSteelJB){
			steelJawbone();
			secSteelJB = false;
		} else {
			Stats.pupname = "-";
			steelJB = false;
			ItemHandler.newTime();
			ItemHandler.t.start();
		}
	}
}
