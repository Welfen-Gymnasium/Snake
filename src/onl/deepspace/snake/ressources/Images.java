package onl.deepspace.snake.ressources;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import onl.deepspace.snake.frame.Spielfeld;

public class Images {
	
	public static BufferedImage uo, rl, ro, ru, lo, lu, swu, swl, swr, swo, her, hel, heo, heu, def, greenBlock, orangeBlock, obstacle, grass, grassz;
	
	public static void regImg(){

		try {
			
			greenBlock = ImageIO.read(Spielfeld.class.getResourceAsStream("/img/blocks/greenBlock.png"));
			orangeBlock = ImageIO.read(Spielfeld.class.getResourceAsStream("/img/blocks/orangeBlock.png"));
			
			uo = ImageIO.read(Spielfeld.class.getResourceAsStream("/img/snake/unten_oben.png"));
			rl = ImageIO.read(Spielfeld.class.getResourceAsStream("/img/snake/rechts_links.png"));
			
			ro = ImageIO.read(Spielfeld.class.getResourceAsStream("/img/snake/corners/oben_rechts.png"));
			ru = ImageIO.read(Spielfeld.class.getResourceAsStream("/img/snake/corners/unten_rechts.png")); 
			lo = ImageIO.read(Spielfeld.class.getResourceAsStream("/img/snake/corners/oben_links.png"));
			lu = ImageIO.read(Spielfeld.class.getResourceAsStream("/img/snake/corners/links_unten.png"));
			
			swl = ImageIO.read(Spielfeld.class.getResourceAsStream("/img/snake/end/end_l.png"));
			swu = ImageIO.read(Spielfeld.class.getResourceAsStream("/img/snake/end/end_u.png"));
			swo = ImageIO.read(Spielfeld.class.getResourceAsStream("/img/snake/end/end_o.png"));
			swr = ImageIO.read(Spielfeld.class.getResourceAsStream("/img/snake/end/end_r.png"));
			
			hel = ImageIO.read(Spielfeld.class.getResourceAsStream("/img/snake/head/head_l.png"));
			heu = ImageIO.read(Spielfeld.class.getResourceAsStream("/img/snake/head/head_u.png"));
			heo = ImageIO.read(Spielfeld.class.getResourceAsStream("/img/snake/head/head_o.png"));
			her = ImageIO.read(Spielfeld.class.getResourceAsStream("/img/snake/head/head_r.png"));
			
			def = ImageIO.read(Spielfeld.class.getResourceAsStream("/img/blocks/default.png"));
			
			grass = ImageIO.read(Spielfeld.class.getResourceAsStream("/img/blocks/grass.png"));
			grassz = ImageIO.read(Spielfeld.class.getResourceAsStream("/img/blocks/grass_z.png"));
			
			obstacle = ImageIO.read(Spielfeld.class.getResourceAsStream("/img/blocks/obstacle.png"));
					
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e){
			e.printStackTrace();
		}
	}
	
}
