package de.sese7.snake.highscore;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import de.sese7.mysql.Login;
import de.sese7.mysql.SqlExe;
import de.sese7.snake.frame.Highscore;
import de.sese7.snake.frame.Spielfeld;

@SuppressWarnings("serial")
public class HighscoreP extends JPanel{
	
	private static JLabel header, user, headings[], labels[], scores[];
	public static Timer t;
	
	public HighscoreP(){
		labels = new JLabel[50];
		scores = new JLabel[50];
		headings = new JLabel[50];
		setLayout(null);
		setBounds(0, 0, 250, Spielfeld.sfh);
		setVisible(false);
		registerGraphics();
	}
	
	@Override
	protected void paintComponent(Graphics g){ 
		super.paintComponent(g); 	
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(new Color(55, 71, 79));
		g2.fillRect(0, 0, 250, Spielfeld.sfh);
		
	}
	
	public static void ticker(){
		t = new Timer(20000, new ActionListener(){
			public void actionPerformed( ActionEvent e ) {
				new Thread(new Runnable() {
					public void run(){hsupdate();}
				}).start();
			}
		});
	}
	
	public static void hsupdate(){
		//System.out.print("Update: ");
		user.setText("User: " + firstUp(Login.username) + "  (Logout)");
		ResultSet res = SqlExe.getSelect("SELECT * FROM " + Login.username + " ORDER BY points DESC", "java_schule_snake");
		try {
			res.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for(int i = 0; i < 4; i++){
						
			int lab = 0;
			try{
				switch(i){
					case 0: lab = res.getInt("points"); break;
					case 1: lab = res.getInt("lenght"); break;
					case 2: lab = res.getInt("food"); break;
					case 3: lab = res.getInt("obstacles"); break;
					default: lab = 0;
				}
			}
			catch(Exception e){
			}
			
			scores[i].setText(String.valueOf(lab));
		}
		//**************
		ResultSet res2 = SqlExe.getSelect("SELECT * FROM " + Login.username + " ORDER BY id DESC", "java_schule_snake");
		try{
			res2.next();
			scores[4].setText(String.valueOf(res2.getInt("id")));
		}
		catch(SQLException e){
		}
		//***************
		ResultSet res3 = SqlExe.getSelect("SELECT * FROM aaa_highscores ORDER BY points DESC", "java_schule_snake");

		for(int i = 5; i < 10; i++){
						
			String lab = "empty";
			int points = 0;
			try{
				res3.next();
				try{
					lab = firstUp(res3.getString("player"));	
					points = res3.getInt("points");
				}
				catch(SQLException e){
				}
			}
			catch(Exception e){				
			}
			
			
			
			labels[i].setText(lab);
			scores[i].setText(String.valueOf(points));
		}
		
		//***************
				ResultSet res4 = SqlExe.getSelect("SELECT * FROM aaa_dailyhs ORDER BY points DESC", "java_schule_snake");

				for(int i = 10; i < 15; i++){
								
					String lab = "empty";
					int points = 0;
					try{
						res4.next();
						try{
							lab = firstUp(res4.getString("player"));	
							points = res4.getInt("points");
						}
						catch(SQLException e){
						}
					}
					catch(Exception e){				
					}
					
					
					
					labels[i].setText(lab);
					scores[i].setText(String.valueOf(points));
				}
		//System.out.println("Updated");
	}
	
	private void registerGraphics() {
		
		header = new JLabel("Highscore");
		header.setFont(new Font("Orbitron", Font.BOLD, 35));
		header.setBounds(15, 30, 220, 50);
		header.setVisible(true);
		header.setForeground(Color.WHITE);
		header.setVisible(true);
		this.add(header);
		
		user = new JLabel("User: ");
		user.setFont(new Font("Orbitron", Font.BOLD, 16));
		user.setBounds(15, 65, 220, 50);
		user.setVisible(true);
		user.setForeground(Color.WHITE);
		user.setVisible(true);
		user.addMouseListener(new MouseAdapter() {
			   @Override
			   public void mouseClicked(MouseEvent e) {
			    	Login.logout();
			    	//Highscore.loginp.passw.setText("");
			    	Highscore.highscorep.setVisible(false);
			    	Highscore.loginp.setVisible(true);
			    	t.stop();
			   }
			   
			});
		this.add(user);
		
		int y = 150;
		for(int i = 0; i < 15; i++){
			
			y = ((i == 4) ? y + 20 : y);
			y = ((i == 5) ? 320 : y); 
			y = ((i == 10) ? 475 : y); 
			
			String lab;
			switch(i){
				case 0: lab = "Points:"; break;
				case 1: lab = "Lenght:"; break;
				case 2: lab = "Food:"; break;
				case 3: lab = "Obstacles:"; break;
				case 4: lab = "Total Games:"; break;
				default: lab = "";
			}
			
			labels[i] = new JLabel(lab);
			labels[i].setBounds(40, y, 210, 25);
			labels[i].setVisible(true);
			labels[i].setForeground(Color.WHITE);
			labels[i].setFont(new Font("Orbitron", Font.BOLD, 16));
			
			this.add(labels[i]);
			
			y = y + 20;
		}
		
		y = 150;
		for(int i = 0; i < 15; i++){
			
			y = ((i == 4) ? y + 20 : y);
			y = ((i == 5) ? 320 : y); 
			y = ((i == 10) ? 475 : y); 
		
			scores[i] = new JLabel("0");
			scores[i].setBounds(180, y, 210, 25);
			scores[i].setVisible(true);
			scores[i].setForeground(Color.WHITE);
			scores[i].setFont(new Font("Orbitron", Font.BOLD, 16));
			
			this.add(scores[i]);
			
			y = y + 20;
		}
		
		y = 120;
		for(int i = 0; i < 3; i++){
			
			y = ((i == 2) ? y - 15 : y);
			
			String lab;
			switch(i){
				case 0: lab = "Personal Best:"; break;
				case 1: lab = "Global Highscore:"; break;
				case 2: lab = "Daily Highscore:"; break;
				default: lab = "";
			}
			
			headings[i] = new JLabel(lab);
			headings[i].setBounds(15, y, 250, 25);
			headings[i].setVisible(true);
			headings[i].setForeground(Color.WHITE);
			headings[i].setFont(new Font("Orbitron", Font.BOLD, 19));
			
			this.add(headings[i]);
			
			y = y + 170;
		}
	}
	private static String firstUp(String s){
		String sr = s.substring(0, 1).toUpperCase() + s.substring(1, s.length());
		return sr;
	}

}
