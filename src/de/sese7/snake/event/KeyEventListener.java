package de.sese7.snake.event;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import de.sese7.snake.main.Main;
import de.sese7.snake.powerup.ItemHandler;
import de.sese7.snake.powerup.ItemPowerups;
import de.sese7.snake.snake.Movement;

public class KeyEventListener implements KeyListener{
	
	private int window, lastmove;
	
	public KeyEventListener(int w){
		lastmove = 37;
		window = w;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(window == 1){
			
			//System.out.println(e.getKeyCode());
			
			if(Movement.acceptTurn == true){
				switch(e.getKeyCode()){
					case 37: if(lastmove != 39 && lastmove != 37){if(!ItemPowerups.inverted){Movement.curxmove = -1; Movement.curymove = 0;}else{Movement.curxmove = 1; Movement.curymove = 0;}} break;
					case 38: if(lastmove != 40 && lastmove != 38){if(!ItemPowerups.inverted){Movement.curxmove = 0; Movement.curymove = -1;}else{Movement.curxmove = 0; Movement.curymove = 1;}} break;
					case 39: if(lastmove != 37 && lastmove != 39){if(!ItemPowerups.inverted){Movement.curxmove = 1; Movement.curymove = 0;}else{Movement.curxmove = -1; Movement.curymove = 0;}} break;
					case 40: if(lastmove != 38 && lastmove != 40){if(!ItemPowerups.inverted){Movement.curxmove = 0; Movement.curymove = 1;}else{Movement.curxmove = 0; Movement.curymove = -1;}} break;
					default:
				}
				Movement.acceptTurn = false;
				lastmove = e.getKeyCode();	
			}
			
			switch(e.getKeyCode()){
			case 10: Main.restart(); break;
			case 27: System.exit(0); break;
			case 106: ItemHandler.nextPupTime = 1; break;
			case 107: Movement.t.setDelay(75); break;
			case 97: ItemPowerups.speed(); break;
			case 98: ItemPowerups.slow(); break;
			case 99: ItemPowerups.lsd(); break;
			case 100: ItemPowerups.invert(); break;
			case 101: ItemPowerups.obstacle(); break;
			}
		}	
		
				
	}
	

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}
}