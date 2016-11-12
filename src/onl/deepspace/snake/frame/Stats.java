package onl.deepspace.snake.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;
import javax.swing.JPanel;

import onl.deepspace.snake.Schlange;
import onl.deepspace.snake.powerup.ItemCreator;
import onl.deepspace.snake.powerup.ItemHandler;
import onl.deepspace.stats.Updater;

@SuppressWarnings("serial")
public class Stats extends JPanel{
	
	public static JLabel lenght, foodl, nextpup, powerup, speedl, poweruptype, powerupremtime, pointsl, obstacles;
	public static int food, speed, points;
	public static String pupname;
	
	public Stats(){

		
		points = 0;
		food = 0;
		speed = 0;
		pupname = "-";
		
		Updater.win = false;
		
		setLayout(null);
		
		setLabels();
	}
	
	@Override
	protected void paintComponent(Graphics g){ 
		super.paintComponent(g); 	
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(new Color(55, 71, 79));
		g2.fillRect(0, 0, 250, Spielfeld.sfh);
		
	} 
	
	
	private void setLabels() {
		JLabel header = new JLabel("Snake");
		header.setFont(new Font("Orbitron", Font.BOLD, 40));
		header.setBounds(23, 30, 200, 50);
		header.setVisible(true);
		header.setForeground(Color.WHITE);
		header.setVisible(true);
		this.add(header);
		
		pointsl = new JLabel("Points: " + points);
		pointsl.setFont(new Font("Orbitron", Font.BOLD, 16));
		pointsl.setBounds(15, 100, 200, 50);
		pointsl.setVisible(true);
		pointsl.setForeground(Color.WHITE);
		pointsl.setVisible(true);
		this.add(pointsl);
		
		lenght = new JLabel("Länge: " + Schlange.registeredBodyParts);
		lenght.setFont(new Font("Orbitron", Font.BOLD, 16));
		lenght.setBounds(15, 125, 200, 50);
		lenght.setVisible(true);
		lenght.setForeground(Color.WHITE);
		lenght.setVisible(true);
		this.add(lenght);
		
		foodl = new JLabel("Food: " + food);
		foodl.setFont(new Font("Orbitron", Font.BOLD, 16));
		foodl.setBounds(15, 150, 200, 50);
		foodl.setVisible(true);
		foodl.setForeground(Color.WHITE);
		foodl.setVisible(true);
		this.add(foodl);
		
		speedl = new JLabel("Speed: " + speed);
		speedl.setFont(new Font("Orbitron", Font.BOLD, 16));
		speedl.setBounds(15, 175, 200, 50);
		speedl.setVisible(true);
		speedl.setForeground(Color.WHITE);
		speedl.setVisible(true);
		this.add(speedl);
		
		obstacles = new JLabel("Obstacles: " + ItemCreator.obstacles);
		obstacles.setFont(new Font("Orbitron", Font.BOLD, 16));
		obstacles.setBounds(15, 200, 200, 50);
		obstacles.setVisible(true);
		obstacles.setForeground(Color.WHITE);
		obstacles.setVisible(true);
		this.add(obstacles);
		
		//---------------------------------------------
		
		nextpup = new JLabel("Next Powerup: " + ItemHandler.nextPupTime + "s");
		nextpup.setFont(new Font("Orbitron", Font.BOLD, 16));
		nextpup.setBounds(15, 250, 200, 50);
		nextpup.setVisible(true);
		nextpup.setForeground(Color.WHITE);
		nextpup.setVisible(true);
		this.add(nextpup);
		
		powerup = new JLabel("Powerup: ");
		powerup.setFont(new Font("Orbitron", Font.BOLD, 16));
		powerup.setBounds(15, 275, 200, 50);
		powerup.setVisible(true);
		powerup.setForeground(Color.WHITE);
		powerup.setVisible(true);
		this.add(powerup);
		
		poweruptype = new JLabel("Typ: ");
		poweruptype.setFont(new Font("Orbitron", Font.BOLD, 16));
		poweruptype.setBounds(25, 300, 200, 50);
		poweruptype.setVisible(true);
		poweruptype.setForeground(Color.WHITE);
		poweruptype.setVisible(true);
		this.add(poweruptype);  
		
		powerupremtime = new JLabel("Time: 0");
		powerupremtime.setFont(new Font("Orbitron", Font.BOLD, 16));
		powerupremtime.setBounds(25, 325, 200, 50);
		powerupremtime.setVisible(true);
		powerupremtime.setForeground(Color.WHITE);
		powerupremtime.setVisible(true);
		this.add(powerupremtime); 
	}

}
