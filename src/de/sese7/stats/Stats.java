package de.sese7.stats;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

import de.sese7.snake.frame.ItemCreator;
import de.sese7.snake.frame.Spielfeld;
import de.sese7.snake.main.Main;
import de.sese7.snake.powerup.ItemHandler;
import de.sese7.snake.snake.Schlange;

@SuppressWarnings("serial")
public class Stats extends JFrame{
	
	public static JLabel lenght, foodl, nextpup, powerup, speedl, poweruptype, powerupremtime, pointsl, obstacles;
	public static int food, speed, points;
	public static String pupname;
	
	public Stats(String title){
		super(title);
		
		points = 0;
		food = 0;
		speed = 0;
		pupname = "-";
		
		Updater.win = false;
		
		regWin();
		
		setLabels();
	}
	
	private void setLabels() {
		JLabel header = new JLabel("Snake");
		header.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
		header.setBounds(38, 30, 200, 50);
		header.setVisible(true);
		header.setForeground(Color.WHITE);
		header.setVisible(true);
		this.add(header);
		
		pointsl = new JLabel("Points: " + points);
		pointsl.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		pointsl.setBounds(17, 100, 200, 50);
		pointsl.setVisible(true);
		pointsl.setForeground(Color.WHITE);
		pointsl.setVisible(true);
		this.add(pointsl);
		
		lenght = new JLabel("Länge: " + Schlange.registeredBodyParts);
		lenght.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		lenght.setBounds(17, 125, 200, 50);
		lenght.setVisible(true);
		lenght.setForeground(Color.WHITE);
		lenght.setVisible(true);
		this.add(lenght);
		
		foodl = new JLabel("Food: " + food);
		foodl.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		foodl.setBounds(17, 150, 200, 50);
		foodl.setVisible(true);
		foodl.setForeground(Color.WHITE);
		foodl.setVisible(true);
		this.add(foodl);
		
		speedl = new JLabel("Speed: " + speed);
		speedl.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		speedl.setBounds(17, 175, 200, 50);
		speedl.setVisible(true);
		speedl.setForeground(Color.WHITE);
		speedl.setVisible(true);
		this.add(speedl);
		
		obstacles = new JLabel("Obstacles: " + ItemCreator.obstacles);
		obstacles.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		obstacles.setBounds(17, 200, 200, 50);
		obstacles.setVisible(true);
		obstacles.setForeground(Color.WHITE);
		obstacles.setVisible(true);
		this.add(obstacles);
		
		//---------------------------------------------
		
		nextpup = new JLabel("Next Powerup: " + ItemHandler.nextPupTime + "s");
		nextpup.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		nextpup.setBounds(17, 250, 200, 50);
		nextpup.setVisible(true);
		nextpup.setForeground(Color.WHITE);
		nextpup.setVisible(true);
		this.add(nextpup);
		
		powerup = new JLabel("Powerup: ");
		powerup.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		powerup.setBounds(17, 275, 200, 50);
		powerup.setVisible(true);
		powerup.setForeground(Color.WHITE);
		powerup.setVisible(true);
		this.add(powerup);
		
		poweruptype = new JLabel("Typ: ");
		poweruptype.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		poweruptype.setBounds(27, 300, 200, 50);
		poweruptype.setVisible(true);
		poweruptype.setForeground(Color.WHITE);
		poweruptype.setVisible(true);
		this.add(poweruptype);  
		
		powerupremtime = new JLabel("Time: 0");
		powerupremtime.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		powerupremtime.setBounds(27, 325, 200, 50);
		powerupremtime.setVisible(true);
		powerupremtime.setForeground(Color.WHITE);
		powerupremtime.setVisible(true);
		this.add(powerupremtime); 
	}

	private void regWin(){
		getContentPane().setBackground(Color.BLACK);
		setTitle("Spielfeld");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setVisible(true);		
		setAlwaysOnTop(true);
		setFocusable(false);
		setResizable(false);
		setSize(200, Spielfeld.sfh);
		setLocation((int) Main.sf.getLocationOnScreen().getX() + Spielfeld.sfw + 5, (int) Main.sf.getLocationOnScreen().getY());
		setLayout(null);
	}

}
