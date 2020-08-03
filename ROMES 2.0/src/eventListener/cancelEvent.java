package eventListener;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class cancelEvent implements ActionListener{
	JFrame frame;
	JTabbedPane TabbedPane;
	Component Component;
	public cancelEvent(JFrame thisFrame) {
		frame=thisFrame;
	}
	public cancelEvent(JTabbedPane tabbedPane,Component component) {
		// TODO Auto-generated constructor stub
		TabbedPane=tabbedPane;
		Component=component;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(frame!=null) {
			System.out.println("Exit Frame");
			frame.dispose();
		}
		else if(TabbedPane != null) {
			System.out.println("remove Tab");
			TabbedPane.remove(Component);
		}
	}
	
}
