package onl.deepspace.snake.event;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import onl.deepspace.snake.frame.Spielfeld;
import onl.deepspace.snake.main.Main;

public class MouseEventListener implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {
		if(Spielfeld.pause){
			Spielfeld.pause();
		}

        Main.mainframe.requestFocus();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {		
	}

}
