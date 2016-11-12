package onl.deepspace.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.apache.commons.codec.binary.Base64;

import onl.deepspace.snake.frame.Highscore;
import onl.deepspace.snake.highscore.LoginP;

public class Connector {

	private static String url = "jdbc:mysql://deepspace.onl:3306/";
	// String dbName = "sese7_users";
	private static String driver = "com.mysql.jdbc.Driver";
	private static String userName = "snake";
	private static String relativUser = "SXF0eDY1OUBGNDNjJQ==";
	static Statement st;
	static Connection conn;

	public static void dblogin(String database) {

		try {

			Base64 decoder = new Base64();
			byte[] decodedBytes = decoder.decode(relativUser);

			String decoded = new String(decodedBytes, "UTF-8");
			
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + database, userName,
					decoded);
			st = conn.createStatement();

			LoginP.falselogin.setText("");

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
