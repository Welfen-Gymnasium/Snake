package de.sese7.snake.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Images {
	
	public static BufferedImage uo, rl, ro, ru, lo, lu, swu, swl, swr, swo, her, hel, heo, heu, def, greenBlock, orangeBlock, obstacle;
	
	public static void regImg(){

		try {
			greenBlock = ImageIO.read(new File("img/blocks/greenBlock.png"));
			orangeBlock = ImageIO.read(new File("img/blocks/orangeBlock.png"));
			
			uo = ImageIO.read(new File("img/snake/unten_oben.png"));
			rl = ImageIO.read(new File("img/snake/rechts_links.png"));
			
			ro = ImageIO.read(new File("img/snake/corners/oben_rechts.png"));
			ru = ImageIO.read(new File("img/snake/corners/unten_rechts.png")); 
			lo = ImageIO.read(new File("img/snake/corners/oben_links.png"));
			lu = ImageIO.read(new File("img/snake/corners/links_unten.png"));
			
			swl = ImageIO.read(new File("img/snake/end/end_l.png"));
			swu = ImageIO.read(new File("img/snake/end/end_u.png"));
			swo = ImageIO.read(new File("img/snake/end/end_o.png"));
			swr = ImageIO.read(new File("img/snake/end/end_r.png"));
			
			hel = ImageIO.read(new File("img/snake/head/head_l.png"));
			heu = ImageIO.read(new File("img/snake/head/head_u.png"));
			heo = ImageIO.read(new File("img/snake/head/head_o.png"));
			her = ImageIO.read(new File("img/snake/head/head_r.png"));
			
			def = ImageIO.read(new File("img/snake/default.png"));
			
			obstacle = ImageIO.read(new File("img/snake/obstacle.png"));
					
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
