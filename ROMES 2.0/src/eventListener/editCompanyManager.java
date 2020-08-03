package eventListener;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import factory.ImgFactory;
import factory.nameFactory;
import functions.managerProfile;
import layoutSetting.ImageLabel_HJ;
import layoutSetting.basicTextArea;
import registrationForm.R_companyManager;

public class editCompanyManager implements ActionListener{
	Container Container;
	managerProfile profile;
	ImageLabel_HJ imageLabel;
	Container leftNorth;
	Container centerBox;
//	public editCompanyManager(Container container, managerProfile managerProfile) {
//		Container = container;
//		profile=managerProfile;
//	}
	public editCompanyManager(R_companyManager R_companyManager, managerProfile managerProfile) {
		imageLabel=R_companyManager.getImageLabel();
		profile=managerProfile;
		leftNorth=R_companyManager.leftNorth;
		centerBox=R_companyManager.centerBox;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		event(profile);
		event(leftNorth);
		event(centerBox);
	}
	void event(Container Container) {
		if(Container instanceof managerProfile) {
			((managerProfile)Container).getImageLabel().setImage(ImgFactory.ImgToBytes(imageLabel.getImgPath()));
			((managerProfile)Container).getImageLabel().saveImagePath(imageLabel.getImgPath());
			((managerProfile)Container).getImageLabel().setImageSize(profile.imageWidth, profile.imageHeight);
		}
		for(Component firstComponent:Container.getComponents()) {
			importData(firstComponent);
			if(firstComponent instanceof JPanel) {
				for(Component secondComponent : ((JPanel) firstComponent).getComponents()) {
					importData(secondComponent);
					if(secondComponent instanceof basicTextArea) {
						for(Component thirdComponent: ((basicTextArea) secondComponent).getViewport().getComponents()) {
							importData(thirdComponent);
						}
					}
				}
			}
		}
	}
	void importData(Component component) {
		System.out.println("readData");
		if(component.getName()==null) {
			
		}else {
			switch (component.getName()) {
			case nameFactory.COMPANY_MANAGER_NAME_N:
				profile.USERNAME=((JLabel)component).getText();
				break;
			case nameFactory.COMPANY_MANAGER_POSITION_TN:
				profile.POSITION=((JTextField)component).getText();
				break;
			case nameFactory.COMPANY_MANAGER_TEL_TN:
				profile.PHONE=((JTextField)component).getText();
				break;
			case nameFactory.COMPANY_MANAGER_EMAIL_TN:
				profile.EMAIL=((JTextField)component).getText();
				break;
			case nameFactory.COMPANY_MANAGER_REMARKS_TN:
				profile.MEMO=((JTextArea)component).getText();
				break;
			default:
				break;
			}
			profile.refreshLabel();
		}
	}
}
