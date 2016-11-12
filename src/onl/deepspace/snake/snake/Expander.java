package onl.deepspace.snake;

import onl.deepspace.snake.frame.Stats;
import onl.deepspace.snake.main.Main;

public class Expander {

	public static int futureExpansions;
	public static int counter[], colorer[];
	
	public static void checkExpand(){
		
		for(int i = 0; i < futureExpansions; i++){
			
			if(counter[i] == 1){				
				futureExpansions--;
				for(int j = 0; j < futureExpansions; j++){
					counter[j] = counter[j + 1];
				}
				
				int oldmovex = (int) Movement.oldmove[Schlange.registeredBodyParts - 1].getX();
				int oldmovey = (int) Movement.oldmove[Schlange.registeredBodyParts - 1].getY();
				
				Main.sf.add(
						Schlange.regNewBodyPart(Schlange.registeredBodyParts, oldmovex, oldmovey)
				);
				
				if(Movement.t.getDelay() > 125){
					Stats.speed++;
					Movement.t.setDelay(Movement.t.getDelay() - 1);
				}
			}
			else{
				counter[i]--;
			}
			
		}
		
	}

}
