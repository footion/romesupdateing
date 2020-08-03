package functions;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import eventListener.cancelEvent;
import eventListener.deleteProfileEvent;
import eventListener.editCompanyManager;
import factory.colorFactory;
import factory.fontFactory;
import layoutSetting.basicLabel;
import registrationForm.R_companyManager;

@SuppressWarnings("serial")
public class addProfileLabel extends basicLabel implements MouseListener{
	JPanel ParentsPanel;
	static String string = "+add profile";
	
	public addProfileLabel(JPanel parentsPanel) {
		super(string);
		ParentsPanel=parentsPanel;
		this.setFont(new Font(fontFactory.ENGLISH_FONT,Font.BOLD,16));
		this.setForeground(colorFactory.ADD_LINE);
		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		this.setFont(new Font(fontFactory.ENGLISH_FONT,Font.BOLD,17));
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		this.setFont(new Font(fontFactory.ENGLISH_FONT,Font.BOLD,16));
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("Press : addProfile");
		this.setForeground(colorFactory.ADD_LINE_PRESS);
		createProfile();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		this.setForeground(colorFactory.ADD_LINE);
	}
	void createProfile() {
		managerProfile userProfile = new managerProfile();
		userProfile.delBtn.addActionListener(new deleteProfileEvent(ParentsPanel, userProfile));
		ParentsPanel.add(userProfile);
		method.refreshComponent(ParentsPanel);
		
		R_companyManager frame=new R_companyManager();
		frame.btnPanel.leftBtn.addActionListener(new editCompanyManager(frame, userProfile));
		frame.btnPanel.leftBtn.addActionListener(new cancelEvent(frame));
	}
}
