package onl.deepspace.snake.frame;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.JPanel;

import onl.deepspace.mysql.Login;
import onl.deepspace.mysql.SqlExe;
import onl.deepspace.snake.Schlange;
import onl.deepspace.snake.highscore.*;
import onl.deepspace.snake.powerup.ItemCreator;

@SuppressWarnings("serial")
public class Highscore extends JPanel{
		
	public static JPanel loginp;
	public static HighscoreP highscorep;
	
	public Highscore(){
		setLayout(null);
		
		highscorep = new HighscoreP();
		loginp = new LoginP();
		this.add(loginp);
		this.add(highscorep);
		HighscoreP.ticker();
		
		if(Login.user != 0){
			highscorep.setVisible(true);
			HighscoreP.hsupdate();
		}
	
		else{
			loginp.setVisible(true);
		}
		
	}
	
	public static void insertResults(){
		SqlExe.exeSQL("INSERT INTO " + Login.username + " (food, obstacles, lenght, points) VALUES (" 
				  + Stats.food + ", " 
				  + ItemCreator.obstacles + ", " 
				  + Schlange.registeredBodyParts + ", " 
				  + Stats.points + ");", "java_schule_snake");
		  
		  ResultSet s = SqlExe.getSelect("SELECT * FROM aaa_highscores ORDER BY points DESC", "java_schule_snake");
		  int maxpoints = 0;
		  try {
			s.next();
			maxpoints = s.getInt("points");
		  }
		  catch (SQLException e1) {
		  }
		  
		  if(maxpoints < Stats.points){
			  SqlExe.exeSQL("INSERT INTO aaa_highscores (food, obstacles, length, points, player) VALUES (" 
					  + Stats.food + ", " 
					  + ItemCreator.obstacles + ", " 
					  + Schlange.registeredBodyParts + ", " 
					  + Stats.points + ", '"
					  + Login.username + "');", "java_schule_snake");
		  }
		  
		  //*******************
		  ResultSet s2 = SqlExe.getSelect("SELECT * FROM aaa_version ", "java_schule_snake");
		  int day = 0;
		  
		  try {
			s2.next();
			day = Integer.parseInt(s2.getString("day"));
		  } catch (SQLException e2) {
		  }
		  
		  Calendar calendar = Calendar.getInstance();
		  int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR); 
		  
		  if(dayOfYear != day){
			  SqlExe.exeSQL("DELETE FROM aaa_dailyhs", "java_schule_snake");
			  SqlExe.exeSQL("UPDATE aaa_version SET day = '" + dayOfYear + "'", "java_schule_snake");
		  }
		  
		  ResultSet s3 = SqlExe.getSelect("SELECT * FROM aaa_dailyhs ORDER BY points DESC", "java_schule_snake");
		  int dhmaxpoints = 0;
		  try {
			s3.next();
			dhmaxpoints = s3.getInt("points");
		  }
		  catch (SQLException e1) {
		  }
		
		  if(dhmaxpoints < Stats.points){
			  SqlExe.exeSQL("INSERT INTO aaa_dailyhs (food, obstacles, length, points, player) VALUES (" 
					  + Stats.food + ", " 
					  + ItemCreator.obstacles + ", " 
					  + Schlange.registeredBodyParts + ", " 
					  + Stats.points + ", '"
					  + Login.username + "');", "java_schule_snake");
		  }
	  		
	}
	
	
}
