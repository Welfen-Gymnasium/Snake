package de.sese7.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.apache.commons.codec.binary.Base64;

import de.sese7.snake.frame.Highscore;
import de.sese7.snake.highscore.LoginP;

public class Connector {

	private static String url = "jdbc:mysql://sese7.de:3306/";
	// String dbName = "sese7_users";
	private static String driver = "com.mysql.jdbc.Driver";
	private static String userName = "sese7";
	private static String relativUser = "alluM3Q2NSM=";
	static Statement st;
	static Connection conn;

	public static void dblogin(String database) {

		try {

			Base64 decoder = new Base64();
			byte[] decodedBytes = decoder.decode(relativUser);

			String decoded = new String(decodedBytes, "UTF-8");
			
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + database, userName,
					decoded.substring(0, 8));
			st = conn.createStatement();

			LoginP.falselogin.setText("");

			// ***********Abfrage*************

			/*
			 * ResultSet res = st.executeQuery("SELECT * FROM users");
			 * 
			 * while (res.next()) { String id = res.getString("user"); String
			 * user = res.getString("pw"); //String msg = res.getString("msg");
			 * System.out.println(id + " " + user + "\t"); }
			 * 
			 * //*********SQL Allgemein************ /* int val =
			 * st.executeUpdate
			 * ("INSERT INTO musik (titel, user, server) VALUES (1, '', 0)");
			 * if(val==1) System.out.print("Successfully inserted value");
			 * conn.close();
			 */
		} catch (Exception e) {
			LoginP.falselogin
					.setText("<html><div style=\"text-align: center;\">Cannot connect to database</div></html>");
			LoginP.falselogin.setVisible(true);
			Highscore.loginp.setVisible(true);
			Highscore.highscorep.setVisible(false);
			Login.logout();
			e.printStackTrace();
		}
	}
}
