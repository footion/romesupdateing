package evevtListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class changeTitleEvent implements MouseListener{
	JLabel LABEL;
	public changeTitleEvent(JLabel label) {
		// TODO Auto-generated constructor stub
		LABEL = label;
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
		String title =JOptionPane.showInputDialog(null,"Please enter a title.");
		System.out.println("Enter title : "+title);
		if(title!=null&&!title.equals("")) {
			LABEL.setText(title);
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
