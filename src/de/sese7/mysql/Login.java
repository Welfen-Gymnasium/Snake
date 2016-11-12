package de.sese7.mysql;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;

import de.sese7.snake.frame.Spielfeld;
import de.sese7.snake.main.Main;

public class Login {
	
	public static int user;
	public static String username;
	public static boolean exiuser;

	public static boolean login(int userid, String pw) {
		
		Connector.dblogin("sese7_web");
		
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			md = null;
		}
		md.update(pw.getBytes());
		byte[] digest = md.digest();
		StringBuffer sb = new StringBuffer();
		for (byte b : digest) {
			sb.append(String.format("%02x", b & 0xff));
		}

		String pass = sb.toString();
		
		ResultSet res, res2;
		
		try {
			res = Connector.st.executeQuery("SELECT * FROM users");
			res2 = SqlExe.getSelect("SELECT * FROM users_perm WHERE user = '" + userid + "'", "sese7_web");
			res2.next();
			
			exiuser = false;
			
			while(res.next()){
				int dbuser = res.getInt("id");
				String dbpass = res.getString("pw");
				String dbusername = res.getString("user");
				
				boolean allowGames = false;
				
				if(userid == dbuser){
					allowGames = res2.getBoolean("games");
					if(!allowGames){return false;}
					
					exiuser = true;
					
					if(dbpass.equals(pass)){
						username = dbusername;
						user = dbuser;
						Connector.conn.close();
						Spielfeld.pause();
						Main.mainframe.requestFocus();
						return true;
					}
					return false;
				}
				
			}
			Connector.conn.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	public static void logout() {
		user = 0;		
	}
}

