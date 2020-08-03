package eventListener;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.hibernate.Session;

import entity.Company;
import entity.Company_manager;
import factory.ImgFactory;
import factory.nameFactory;
import functions.managerProfile;
import hibernate.hibernate;
import layoutSetting.ImageLabel;
import layoutSetting.basicTabbedPane;
import layoutSetting.basicTextArea;
import message.errorMessage;
import message.successMessage;
import pages.companyManagement;
import registrationForm.R_company;

public class updateCompanyEvent implements ActionListener{
	int CompanyKey;
	R_company CompanyRegistration;
	basicTabbedPane basicTabbedPane;
	public updateCompanyEvent(int companyKey,R_company companyRegistration, basicTabbedPane tabbedPane) {
		CompanyKey = companyKey;
		CompanyRegistration=companyRegistration;
		basicTabbedPane=tabbedPane;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try (Session session = hibernate.factory.openSession()){
			hibernate.transaction=session.beginTransaction();
			
			Company company = session.get(Company.class, CompanyKey);
			updateCompanyTitle(company, CompanyRegistration.titlePanel);
			updateCompany(company, CompanyRegistration.leftBox);
			updateCompany(company, CompanyRegistration.rightBox);
			updateCompanyManager(company, CompanyRegistration.profilePanel,session);
			updateCompanyImage(company);
			
//			ImageLabel imageLabel=CompanyRegistration.getImageLabel();
//			session.doWork(conn -> {
//				company.setImage(ImgFactory.ImgToBytes(imageLabel.getImgPath()));
//			});
			
			session.update(company);
			basicTabbedPane.remove(CompanyRegistration);
			new successMessage();
			hibernate.transaction.commit();
			((companyManagement)basicTabbedPane).refresh();
		} catch (Exception error) {
			error.printStackTrace();
			new errorMessage("update");
		}
	}
	void updateCompanyImage(Company company){
		ImageLabel imageLabel=CompanyRegistration.getImageLabel();
		if(imageLabel.getImgPath()!=null) {
			company.setImage(ImgFactory.ImgToBytes(imageLabel.getImgPath()));
		}
	}
	void updateCompany(Company company,Container container) {
		for(Component component : container.getComponents()) {
			if(component instanceof JPanel) {
				for(Component insideComponent : ((JPanel) component).getComponents()) {
					if(insideComponent instanceof JTextField
							|| insideComponent instanceof JComboBox) {
						System.out.println(insideComponent.getName());
						updateEntity(company, insideComponent);
					}else if(insideComponent instanceof basicTextArea) {
						for(Component basicTextAreaCom:((basicTextArea) insideComponent).getViewport().getComponents()) {
							if(basicTextAreaCom instanceof JTextArea) {
								System.out.println(basicTextAreaCom.getName());
								updateEntity(company, basicTextAreaCom);
							}
						}
					}
				}
			}
		}
	}
	void updateCompanyManager(Company company,Container container,Session session) {
		for(Company_manager cm:company.getManagers()) {
			session.delete(cm);
		}
		company.getManagers().clear();
		
		for(Component component : container.getComponents()) {
				//managerData
				if(component instanceof managerProfile) {
					Company_manager manager = new Company_manager();
					if(((managerProfile)component).getImageByte()!=null) 
						manager.setImage(((managerProfile)component).getImageByte());
					if(((managerProfile)component).getImageLabel().getImgPath()!=null)
						manager.setImage(ImgFactory.ImgToBytes(((managerProfile)component).getImageLabel().getImgPath()));
					manager.setName(((managerProfile)component).USERNAME);
					manager.setPosition(((managerProfile)component).POSITION);
					manager.setPhone(((managerProfile)component).PHONE);
					manager.setEmail(((managerProfile)component).EMAIL);
					manager.setRemarks(((managerProfile)component).MEMO);
					manager.setCompany(company);
					//company.getManagers().clear();
					company.addManager(manager);
					session.save(manager);
				}
		}
	}
	void updateCompanyTitle(Company company,Container titlePanel) {
		for(Component component : titlePanel.getComponents()) {
			if(component instanceof JLabel) {
				System.out.println(component.getName());
				updateEntity(company, component);
			}
		}
	}
	void updateEntity(Company company, Component Component) {
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
