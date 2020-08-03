package evevtListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JScrollPane;

public class bottomScrollEvent implements MouseListener{
	JScrollPane ScrollPane;
	public bottomScrollEvent(JScrollPane scrollPane) {
		ScrollPane=scrollPane;
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		ScrollPane.getVerticalScrollBar().setValue(ScrollPane.getVerticalScrollBar().getMaximum());
	}

}
