package de.sese7.snake.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

import de.sese7.snake.event.KeyEventListener;
import de.sese7.snake.event.MouseEventListener;

@SuppressWarnings("serial")
public class Window extends JFrame{
	
	public static int wx, wy;
	Font orbitron;
	
	public Window(){
		
		try {
		     GraphicsEnvironment ge = 
		         GraphicsEnvironment.getLocalGraphicsEnvironment();
		     ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, Spielfeld.class.getResourceAsStream("/ressources/font/Orbitron.ttf")));
		} catch (IOException|FontFormatException e) {
		}
		
		addKeyListener(new KeyEventListener());
		addMouseListener(new MouseEventListener());
		
		wx = 1050;
		wy = 600;
		
		setTitle("Snake");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		getContentPane().setBackground(new Color(222, 222, 222));
		getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		setVisible(true);
		setAlwaysOnTop(true);
		setResizable(false);
		setSize(wx, wy);
		//setPreferredSize(new Dimension(wx, wy)); //soll besser funktionieren
		setLocationRelativeTo(null);
		setLayout(null);
	}
	
}
