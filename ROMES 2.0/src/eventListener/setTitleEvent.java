package eventListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class setTitleEvent implements MouseListener{
	JLabel LABEL;
	String Text;
	public setTitleEvent(JLabel label,String text) {
		// TODO Auto-generated constructor stub
		LABEL = label;
		Text=text;
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
		String title =JOptionPane.showInputDialog(null,"Please enter a "+Text+".");
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
