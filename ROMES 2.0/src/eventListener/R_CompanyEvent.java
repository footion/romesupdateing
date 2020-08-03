package eventListener;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.poi.ss.formula.functions.T;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import entity.Company;
import entity.Company_manager;
import factory.ImgFactory;
import factory.nameFactory;
import factory.stringFactory;
import functions.managerProfile;
import hibernate.config;
import hibernate.hibernate;
import layoutSetting.basicTabbedPane;
import layoutSetting.basicTextArea;
import layoutSetting.ImageLabel_HJ;
import message.errorMessage;
import message.successMessage;
import pages.companyManagement;
import registrationForm.R_company;

public class R_CompanyEvent implements ActionListener{
	Container LeftBox;
	Container RightBox;
	Container TitlePanel;
	Container profilePanel;
	R_company RegistrationFrame;
	basicTabbedPane TabbedPane;
	public R_CompanyEvent(R_company registrationFrame, basicTabbedPane tabbedPane) {
		LeftBox=registrationFrame.leftBox;
		RightBox=registrationFrame.rightBox;
		TitlePanel = registrationFrame.titlePanel;
		profilePanel=registrationFrame.profilePanel;
		RegistrationFrame=registrationFrame;
		TabbedPane=tabbedPane;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("company Event :");
		try (Session session = hibernate.factory.openSession()){
			System.out.println("start session");
			hibernate.transaction = session.beginTransaction();
			System.out.println("Left components : "+LeftBox.getComponentCount());
			System.out.println("Right components : "+RightBox.getComponentCount());
			Company company = new Company();
			//save Company
			saveCompanyTitle(company, TitlePanel);
			saveCompany(company, LeftBox);
			saveCompany(company, RightBox);
			saveCompanyManager(company, profilePanel,session);
			saveCompanyImage(company);
			session.save(company);
			TabbedPane.remove(RegistrationFrame);
			new successMessage();
			hibernate.transaction.commit();
			
			if(TabbedPane instanceof companyManagement)
				((companyManagement) TabbedPane).refresh();
			
		} catch (Exception e2) {
			new errorMessage("save");
			e2.printStackTrace();
		}
	}
	void saveCompanyImage(Company company){
		ImageLabel_HJ imageLabel=RegistrationFrame.getImageLabel();
		if(imageLabel.getImgPath()!=null) {
			company.setImage(ImgFactory.ImgToBytes(imageLabel.getImgPath()));	
		}
	}
	void saveCompany(Company company,Container container) {
		for(Component component : container.getComponents()) {
			if(component instanceof JPanel) {
				for(Component insideComponent : ((JPanel) component).getComponents()) {
					if(insideComponent instanceof JTextField
							|| insideComponent instanceof JComboBox) {
						System.out.println(insideComponent.getName());
						setEntity(company, insideComponent);
					}else if(insideComponent instanceof basicTextArea) {
						for(Component basicTextAreaCom:((basicTextArea) insideComponent).getViewport().getComponents()) {
							if(basicTextAreaCom instanceof JTextArea) {
								System.out.println(basicTextAreaCom.getName());
								setEntity(company, basicTextAreaCom);
							}
						}
					}
				}
			}
		}
	}
	void saveCompanyManager(Company company,Container container,Session session) {
		for(Component component : container.getComponents()) {
				//managerData
				if(component instanceof managerProfile) {
					System.out.println("find managerPanel");
					Company_manager manager = new Company_manager();
					if(((managerProfile)component).getImageLabel().getImgPath()!=null)
						manager.setImage(ImgFactory.ImgToBytes(((managerProfile)component).getImageLabel().getImgPath()));
					manager.setName(((managerProfile)component).USERNAME);
					manager.setPosition(((managerProfile)component).POSITION);
					manager.setPhone(((managerProfile)component).PHONE);
					manager.setEmail(((managerProfile)component).EMAIL);
					manager.setRemarks(((managerProfile)component).MEMO);
					manager.setCompany(company);
					company.addManager(manager);
					session.save(manager);
				}
		}
	}
	void saveCompanyTitle(Company company,Container titlePanel) {
		for(Component component : titlePanel.getComponents()) {
			if(component instanceof JLabel) {
				System.out.println(component.getName());
				setEntity(company, component);
			}
		}
	}
	void setEntity(Company company, Component Component) {
		if(Component.getName()==null) {
			
		}else {
			switch (Component.getName()) {
			case nameFactory.COMPANY_NAME_N:
				company.setCompanyName(((JLabel)Component).getText());
				break;
			case nameFactory.COMPANY_CLASSIFICATION_N:
				company.setCompanyClassification((String) ((JComboBox)Component).getSelectedItem());
				break;
			case nameFactory.COMPANY_CEONAME_TN:
				company.setCEO_Name(((JTextField)Component).getText());
				break;
			case nameFactory.COMPANY_BUSINESSNUMBER_TN:
				company.setBusinessNumber(((JTextField)Component).getText());
				break;
			case nameFactory.COMPANY_INDUSTRYTYPE_TN:
				company.setIndustryType(((JTextField)Component).getText());
				break;
			case nameFactory.COMPANY_INDUSTRYTYPE_DETAIL_TN:
				company.setIndustryType_detail(((JTextField)Component).getText());
				break;
			case nameFactory.COMPANY_FAX_TN:
				company.setFax(((JTextField)Component).getText());
				break;
			case nameFactory.COMPANY_ADDRESS_TN:
				company.setAddress(((JTextField)Component).getText());
				break;
			case nameFactory.COMPANY_EMAIL_TN:
				company.setEmail(((JTextField)Component).getText());
				break;
			case nameFactory.COMPANY_PHONE_TN:
				company.setPhone(((JTextField)Component).getText());
				break;
			case nameFactory.COMPANY_ACCOUNTNUMBER_TN:
				company.setAccountNumber(((JTextField)Component).getText());
				break;
			case nameFactory.COMPANY_REMARKS_TN:
				company.setRemarks(((JTextArea)Component).getText());
				break;
			default:
				break;
			}
		}
	}
}
