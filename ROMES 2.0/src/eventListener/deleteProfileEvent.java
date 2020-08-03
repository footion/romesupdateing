package eventListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import functions.method;

public class deleteProfileEvent implements ActionListener{
	JPanel ParentsPanel;
	JPanel UserProfile;
	public deleteProfileEvent(JPanel parentsPanel , JPanel userProfile) {
		ParentsPanel=parentsPanel;
		UserProfile=userProfile;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		ParentsPanel.remove(UserProfile);
		method.refreshComponent(ParentsPanel);
	}
}
