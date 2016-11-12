package de.sese7.snake.snake;

import javax.swing.JPanel;

import de.sese7.snake.ressources.Block;


public class Schlange {
	
	public static int registeredBodyParts;
	public static Block[] body;
	
	public static JPanel regNewBodyPart(int i, int x, int y){
		
		body[i] = new Block(100);
		JPanel bp = body[i];
		bp.setBounds(x, y, 20, 20);
		bp.setVisible(true);
		registeredBodyParts++;
		
		return(bp);
		
	}
	
	public static void reset() {
		int x = 280;
		for(int i = 0; i < registeredBodyParts; i++){
			if(i < 10){
				body[i].setLocation(x, 280);
				
				if(i != 0 && i != 9){
					body[i].changeImage(3);
				}
				else if(i == 0){
					body[i].changeImage(13);
				}
				else{
					body[i].changeImage(11);
				}
				
				x = x - 20;
			}
			else{
				//Debug
				body[i].setVisible(false);
				//body[i].repaint();				
				body[i] = null;
				
			}
		}
		registeredBodyParts = 10;		
	}	

	public static void updateBlocks(int i) {
		
		int u = ((i == 0) ? 1 : i);
		
		if(body[u].getX() == body[u - 1].getX()){
			body[i].changeImage(2);
		}
		else if(body[u].getY() == body[u - 1].getY()){
			body[i].changeImage(3);
		}
		
		if(i != registeredBodyParts - 1 && i != 0){

			if(body[u].getY() < body[u - 1].getY()){	//geht nach unten	
				if(body[i].getX() < body[i + 1].getX()){ // kommt von rechts
					body[i].changeImage(6);
				}
				else if(body[i].getX() > body[i + 1].getX()){ //kommt von links
					body[i].changeImage(7);
				}
			}
			else if(body[u].getY() > body[u - 1].getY()){	//geht nach oben
				if(body[i].getX() < body[i + 1].getX()){ // kommt von rechts
					body[i].changeImage(4);
				}
				else if(body[i].getX() > body[i + 1].getX()){ //kommt von links
					body[i].changeImage(5);
				}
			}
			else if(body[u].getX() < body[u - 1].getX()){	//geht nach rechts	
				if(body[i].getY() < body[i + 1].getY()){	// kommt von unten
					body[i].changeImage(6);
				}
				else if(body[i].getY() > body[i + 1].getY()){ //kommt von oben
					body[i].changeImage(4);
				}
			}
			else if(body[u].getX() > body[u - 1].getX()){		
				if(body[i].getY() < body[i + 1].getY()){
					body[i].changeImage(7);
				}
				else if(body[i].getY() > body[i + 1].getY()){
					body[i].changeImage(5);
				}
			}
		
		}
		else{
			if(i == 0){
				if(body[i].getX() > body[i + 1].getX()){
					body[i].changeImage(13);
				}
				else if(body[i].getX() < body[i + 1].getX()){
					body[i].changeImage(15);
				}
				else if(body[i].getY() > body[i + 1].getY()){
					body[i].changeImage(14);
				}
				else if(body[i].getY() < body[i + 1].getY()){
					body[i].changeImage(12);
				}
			}
			else{
				if(body[i].getX() > body[i - 1].getX()){
					body[i].changeImage(9);
				}
				else if(body[i].getX() < body[i - 1].getX()){
					body[i].changeImage(11);
				}
				else if(body[i].getY() > body[i - 1].getY()){
					body[i].changeImage(10);
				}
				else if(body[i].getY() < body[i - 1].getY()){
					body[i].changeImage(8);
				}				
			}
			
		}
		
	}
	
}
