package onl.deepspace.snake.ressources;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import onl.deepspace.snake.frame.Spielfeld;


public class Sounds {
	
	private static Clip eat;
	public static Clip collect;
	private static Clip lsd;
	private static AudioInputStream inputStream;
	private static FloatControl gainControl;
	
	public static void getSounds(){
		try{
			eat = getClip("/sound/collect/eat.wav");
			collect = getClip("/sound/collect/collect.wav");
			lsd = getClip("/sound/LSD.wav");
		} catch(LineUnavailableException | UnsupportedAudioFileException| IOException e){
			e.printStackTrace();
		}
	}
	
	private static Clip getClip(String loc) throws LineUnavailableException, UnsupportedAudioFileException, IOException{
		Clip c = AudioSystem.getClip();
		inputStream = AudioSystem.getAudioInputStream(Spielfeld.class.getResource(loc));
		c.open(inputStream);
		gainControl = (FloatControl) c.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(+6.0f);
		c.setMicrosecondPosition(0);
		return c;
	}
	
	public static synchronized void playSound(final String url) {
		    switch(url){
		    	case "collect/collect.wav": collect.start(); collect.setMicrosecondPosition(0); break;
		    	case "collect/eat.wav": eat.start(); eat.setMicrosecondPosition(0); break;
		    	case "LSD.wav": lsd.start(); lsd.setMicrosecondPosition(0); break;
		    }
	}
	
}