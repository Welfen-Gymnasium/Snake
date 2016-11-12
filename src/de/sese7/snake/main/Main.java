package de.sese7.snake.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;

import de.sese7.mysql.Login;
import de.sese7.snake.event.KeyEventListener;
import de.sese7.snake.frame.Highscore;
import de.sese7.snake.frame.Spielfeld;
import de.sese7.snake.frame.Stats;
import de.sese7.snake.frame.Window;
import de.sese7.snake.highscore.HighscoreP;
import de.sese7.snake.powerup.ItemCreator;
import de.sese7.snake.powerup.ItemHandler;
import de.sese7.snake.powerup.ItemPowerups;
import de.sese7.snake.ressources.Sounds;
import de.sese7.snake.snake.Movement;
import de.sese7.snake.snake.Schlange;
import de.sese7.stats.Updater;

public class Main {
	
	public static String version = "1.3.0";
	
	public static JFrame mainframe;
	public static Spielfeld sf;
	public static Stats stf;
	public static Highscore highscore;
	public static JLabel loadx;
	
	public static void main(String args[]){
		try{	
			start();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		Sounds.collect.start();
	}

	public static void start() {

		mainframe = new Window();
		
		loadx = new JLabel("Loading");
		loadx.setFont(new Font("Orbitron", Font.BOLD, 20));
		loadx.setBounds(Window.wx / 2, Window.wy / 2, 200, 50);
		loadx.setVisible(false);
		loadx.setForeground(Color.BLACK);
		loadx.repaint();
		mainframe.add(loadx);

		Sounds.getSounds();
		
		regPanels();
		
		mainframe.setIconImage(Toolkit.getDefaultToolkit().getImage("src/img/snake.png"));
		
		Spielfeld.pause = false;
		Movement.t.start();
	}
		
	public static void restart(){
		Movement.t.stop();
		ItemHandler.t.stop();
		
		Movement.curxmove = 1;
		Movement.curymove = 0;
		
		KeyEventListener.lastmove = 37;
		
		Schlange.reset();
		ItemCreator.resetObstacels();
		ItemCreator.movePowerupBlock();
		ItemCreator.moveSnakeBlock();
		
		ItemHandler.nextPupTime = 0;
		Stats.food = 0;
		Stats.points = 0;
		
		Movement.allowMovement = true;
		Spielfeld.lose.setVisible(false);
		Spielfeld.lose2.setVisible(false);
		
		switch(Stats.pupname){
			case "Speed": ItemPowerups.remspeed(); ItemPowerups.t.stop(); break;
			case "Slowness": ItemPowerups.remslow(); ItemPowerups.t.stop(); break;
			case "Jawbone": ItemPowerups.remSteelJawbone(); ItemPowerups.remSteelJawbone(); break;
			case "Alcohol": ItemPowerups.reminvert(); ItemPowerups.t.stop(); break;
			case "LSD": ItemPowerups.remlsd(); ItemPowerups.t.stop(); break;
		}
		ItemPowerups.remPupTime = 0;
		
		Movement.t.setDelay(200);
		Updater.update();
		
		if(Login.user != 0){
			new Thread(new Runnable(){
				public void run(){
					HighscoreP.hsupdate();
				}
			}).start();
		}
		Movement.t.start();
		//start();
		
	}
	

	private static void regPanels(){
		
		stf = new Stats();
		sf = new Spielfeld("Spielfeld");
		highscore = new Highscore();		
		
		stf.setBounds(0, 0, 200, Spielfeld.sfh);
		stf.setVisible(true);
		
		sf.setBounds(200, 0, Spielfeld.sfw, Spielfeld.sfh);
		sf.setVisible(true);
		
		highscore.setBounds(800, 0, 250, Spielfeld.sfh);
		highscore.setVisible(true);
		
		mainframe.add(stf);
		mainframe.add(sf);
		mainframe.add(highscore);
		
		stf.repaint();
		sf.repaint();		
		highscore.repaint();		
		
	}
	
}
