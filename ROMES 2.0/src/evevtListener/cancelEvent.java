package evevtListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import factory.stringFactory;
import gui.ColoredPanel;

public class cancelEvent implements ActionListener{
	ColoredPanel  coloredPanel = new ColoredPanel();
	private JFrame superFrame;
	
	public cancelEvent(ColoredPanel coloredPanel) {
		// TODO Auto-generated constructor stub
		coloredPanel=coloredPanel;
	}
	public cancelEvent(JFrame frame) {
		// TODO Auto-generated constructor stub
		this.superFrame=frame;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton button = (JButton) e.getSource();
		final String name = button.getName();
		if(name.equals(stringFactory.BUTTON_NAME_CANCLE))
			superFrame.dispose();
	}
	
}
