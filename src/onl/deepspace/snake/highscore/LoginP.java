package onl.deepspace.snake.highscore;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.ResultSet;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import onl.deepspace.mysql.Login;
import onl.deepspace.mysql.SqlExe;
import onl.deepspace.snake.frame.Highscore;
import onl.deepspace.snake.frame.Spielfeld;
import onl.deepspace.snake.main.Main;

@SuppressWarnings("serial")
public class LoginP extends JPanel{
	
	public JLabel loginlabel;
	public static JLabel falselogin;
	public JLabel register;
	private JTextField user;
	private JPasswordField passw;
	
	public LoginP(){
		setLayout(null);
		setBounds(0, 0, 250, Spielfeld.sfh);
		setVisible(false);
		registerGraphics();
	}
	
	
	@SuppressWarnings("deprecation")
	public void login(){
		
		String pw = passw.getText();	
		String fielduser = user.getText();
		String dbversion = null;
		int userid = 0;
		try{
			ResultSet rsv = SqlExe.getSelect("SELECT * FROM aaa_version", "java_schule_snake");
			rsv.next();
			dbversion = rsv.getString("vernr");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		try{
			ResultSet rsv2 = SqlExe.getSelect("SELECT * FROM users WHERE user = '" + fielduser.toLowerCase() + "'", "sese7_web");
			rsv2.next();
			userid = rsv2.getInt("id");
			fielduser = rsv2.getString("user");
		}
		catch(Exception e){
		}
		
		
		if(dbversion.equalsIgnoreCase(Main.version)){
			if(Login.login(userid, pw)){
				
				SqlExe.exeSQL("CREATE TABLE IF NOT EXISTS " + fielduser.toLowerCase() + "("
						+ "id INT NOT NULL AUTO_INCREMENT,"
						+ "PRIMARY KEY(id),"
						+ "lenght INT(5),"
						+ "points INT(10),"
						+ "food INT(5),"
						+ "obstacles INT(5)"
						+ ");", "java_schule_snake");
				
				Highscore.loginp.setVisible(false);
				HighscoreP.hsupdate();
				HighscoreP.t.start();
				Highscore.highscorep.setVisible(true);
				passw.setText("");
				user.setText("");
				
				HighscoreP.hsupdate();
			}
			else{
				String fail;
				
				if(Login.exiuser){
					fail = "s Passwort!";
				}
				else{
					fail = "r Benutzername!";
				}
				
				falselogin.setText("Falsche" + fail);
				falselogin.setVisible(true);
			}
		}
		else{
			falselogin.setText("<html><div style=\"text-align: center;\">Outdated Snake version,<br />please download the latest<br />version from our website<br />to login to scoreboard.<br />(Click)</div></html>");
			falselogin.setVisible(true);
			falselogin.addMouseListener(new MouseAdapter() {
				   @Override
				   public void mouseClicked(MouseEvent e) {
				    	if(Desktop.isDesktopSupported())
				    	{
				    	   try {
								Desktop.getDesktop().browse(new URI("http://www.data.deepspace.onl/Snake.jar"));
							} catch (IOException | URISyntaxException e1) {
								e1.printStackTrace();
							}
				    	  }
				      }
				   
				});
		}
		
		
	}
	
	@Override
	protected void paintComponent(Graphics g){ 
		super.paintComponent(g); 	
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(new Color(55, 71, 79));
		g2.fillRect(0, 0, 250, Spielfeld.sfh);
		
	}
	
	private void registerGraphics(){
		
		loginlabel = new JLabel("<html><div style=\"text-align: center;\">DeepSpace.onl<br />Highscore Userlogin</div></html>");
		loginlabel.setBounds(10, 150, 250, 100);
		loginlabel.setFont(new Font("Orbitron", Font.BOLD, 19));
		loginlabel.setVisible(true);
		loginlabel.setForeground(Color.WHITE);
		
		register = new JLabel("Registrieren");
		register.setBounds(0, 290, 250, 100);
		register.setFont(new Font("Orbitron", Font.BOLD, 15));
		register.setVisible(true);
		register.setHorizontalAlignment(JLabel.CENTER);
		register.setForeground(Color.WHITE);
		register.addMouseListener(new MouseAdapter() {
			   @Override
			   public void mouseClicked(MouseEvent e) {
			    	if(Desktop.isDesktopSupported())
			    	{
			    	   try {
			    		   Spielfeld.pause();
			    		   Desktop.getDesktop().browse(new URI("http://www.deepspace.onl/register"));
						} catch (IOException | URISyntaxException e1) {
							e1.printStackTrace();
						}
			    	  }
			      }
			   
			});
		
		falselogin = new JLabel("");
		falselogin.setBounds(0, 310, 250, 200);
		falselogin.setFont(new Font("Orbitron", Font.BOLD, 14));
		falselogin.setVisible(false);
		falselogin.setHorizontalAlignment(JLabel.CENTER);
		falselogin.setForeground(Color.RED);
		
		user = new JTextField();
		user.setBounds(20, 250, 210, 20);
		user.setVisible(true);
		user.setFont(new Font("Courier New", Font.BOLD, 15));
		user.setBorder(null);
		user.setHorizontalAlignment(JTextField.CENTER);
		
		user.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
            	if(!Spielfeld.pause){
            		Spielfeld.pause();
            		user.requestFocus();
            	}                
            }
        });
		
		user.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent evt){
				switch(evt.getKeyCode()){
					case 10: login(); break;
					case 27: System.exit(0); break;
				}
			}
		});
		
		
		passw = new JPasswordField();
		passw.setBounds(20, 275, 210, 20);
		passw.setVisible(true);
		passw.setFont(new Font("Courier New", Font.BOLD, 15));
		passw.setBorder(null);
		passw.setHorizontalAlignment(JPasswordField.CENTER);
		
		passw.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
            	if(!Spielfeld.pause){
            		Spielfeld.pause();
            		passw.requestFocus();
            	}                
            }
        });
		
		passw.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent evt){
				switch(evt.getKeyCode()){
					case 10: login(); break;
					case 27: System.exit(0); break;
				}
			}
		});
		
		
		this.add(user);
		this.add(passw);
		this.add(loginlabel);
		this.add(falselogin);
		this.add(register);
	}
}
