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
	public static String parseTime(int Sec) {
		int hours = Sec /3600;
		hours%=24;
		Sec%=3600;
		int minutes = Sec/60;
		int sec = Sec%60;
		return hours+":"+minutes+":"+sec;
	}
	public static int getSec(String time) {
		// TODO Auto-generated method stub
		if(time.split(":").length>2) {
			String hourString = time.split(":")[0];
			String minuteString = time.split(":")[1];
			String secString = time.split(":")[2];
			final int hours= Integer.parseInt(hourString)*3600;
			final int minutes= Integer.parseInt(minuteString)*60;
			final int sec= (int)Double.parseDouble(secString);
			return hours+minutes+sec;
		}else {
			String hourString = time.split(":")[0];
			String minuteString = time.split(":")[1];
			final int hours= Integer.parseInt(hourString)*3600;
			final int minutes= Integer.parseInt(minuteString)*60;
			return hours+minutes;
		}
	}
}
