package functions;

import java.awt.Component;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class method {
	
	public static void refreshComponent(Component component) {
		component.setSize(component.getWidth()+1,component.getHeight()+1);
		component.setSize(component.getWidth()-1,component.getHeight()-1);
	}
	public static Component findOutComponent(Container container,String componentName) {
		Component component = null;
		for(Component com:container.getComponents()) {
			if(com.getName()!=null&&com.getName().equals(componentName)) {
				component=com;
			}
		}
		return component;
	}
}
