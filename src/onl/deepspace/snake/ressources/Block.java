package onl.deepspace.snake.ressources;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class Block extends JPanel{
	
	private Image img;
	
	public Block(int rot){
		
		setOpaque(false);
		img = getImg(rot);
	}
	
	@Override
	protected void paintComponent(Graphics g){ 
		super.paintComponent(g); 	
		Graphics2D g2 = (Graphics2D) g;
			
		g2.drawImage(img, 0, 0, null);
		
	} 
	
	public void changeImage(int imgx) {
		
        img = getImg(imgx);
        repaint();
    }
	
	private Image getImg(int imgx){
		
		Image ret;
		
		switch(imgx){
			case 0: ret = Images.greenBlock; break;
			case 1: ret = Images.orangeBlock; break;
			
			case 2: ret = Images.uo; break;
			case 3: ret = Images.rl; break;
			
			case 4: ret = Images.ro; break;
			case 5: ret = Images.lo; break;
			case 6: ret = Images.ru; break;
			case 7: ret = Images.lu; break;
			
			case 8: ret = Images.swo; break;
			case 9: ret = Images.swr; break;
			case 10: ret = Images.swu; break;
			case 11: ret = Images.swl; break;
			
			case 12: ret = Images.heo; break;
			case 13: ret = Images.her; break;
			case 14: ret = Images.heu; break;
			case 15: ret = Images.hel; break;
			
			case 16: ret = Images.obstacle; break;

			case 17: ret = Images.grass; break;
			case 18: ret = Images.grassz; break;
			
			case 100: ret = Images.def; break;
			
			default: ret = null;
		}
		
		return ret;
	}
}
