package evevtListener;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
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
import factory.stringFactory;
import gui.MESCardLayout;
import hibernate.HibernateServer;

import layoutSetting.basicTextArea;
import registrationFrame.companyRegistration;
import setPostgreConfig.config;

public class registerCompanyEvent implements ActionListener{
	Container LeftBox;
	Container RightBox;
	Container TitlePanel;
	JPanel RegistrationJFrame;
	public registerCompanyEvent(JPanel registrationJPanel, Container leftBox, Container rightBox, Container titlePanel) {
		LeftBox=leftBox;
		RightBox=rightBox;
		TitlePanel = titlePanel;
		RegistrationJFrame=registrationJPanel;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("company Event :");
		try (Session session = HibernateServer.getFactoryInstance().openSession()){
			System.out.println("start session");
			Transaction tr = session.beginTransaction();
			System.out.println("Left components : "+LeftBox.getComponentCount());
			System.out.println("Right components : "+RightBox.getComponentCount());
			Company company = new Company();
			//save Company
			saveCompanyTitle(company, TitlePanel);
			saveCompany(company, LeftBox);
			saveCompany(company, RightBox);
			
			session.save(company);
			MESCardLayout.getInstance().changePage(MESCardLayout.ListProduct);
			tr.commit();
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Failed to save an error!","Error",JOptionPane.ERROR_MESSAGE);
			e2.printStackTrace();
		}
	}
	void saveCompany(Company company,Container container) {
		for(Component component : container.getComponents()) {
			if(component instanceof JPanel) {
				for(Component insideComponent : ((JPanel) component).getComponents()) {
					if(insideComponent instanceof JTextField) {
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
			case stringFactory.COMPANY_NAME_N:
				company.setCompanyName(((JLabel)Component).getText());
				break;
			case stringFactory.COMPANY_CLASSIFICATION_N:
				company.setCompanyClassification(((JLabel)Component).getText());
				break;
			case stringFactory.COMPANY_CEONAME_TN:
				company.setCEO_Name(((JTextField)Component).getText());
				break;
			case stringFactory.COMPANY_BUSINESSNUMBER_TN:
				company.setBusinessNumber(((JTextField)Component).getText());
				break;
			case stringFactory.COMPANY_INDUSTRYTYPE_TN:
				company.setIndustryType(((JTextField)Component).getText());
				break;
			case stringFactory.COMPANY_INDUSTRYTYPE_DETAIL_TN:
				company.setIndustryType_detail(((JTextField)Component).getText());
				break;
			case stringFactory.COMPANY_TAG_TN:
				company.setTag(((JTextField)Component).getText());
				break;
			case stringFactory.COMPANY_TAG_Detail_TN:
				company.setTag_detail(((JTextField)Component).getText());
				break;
			case stringFactory.COMPANY_ADDRESS_TN:
				company.setAddress(((JTextField)Component).getText());
				break;
			case stringFactory.COMPANY_EMAIL_TN:
				company.setEmail(((JTextField)Component).getText());
				break;
			case stringFactory.COMPANY_PHONE_TN:
				company.setPhone(((JTextField)Component).getText());
				break;
			case stringFactory.COMPANY_ACCOUNTNUMBER_TN:
				company.setAccountNumber(((JTextField)Component).getText());
				break;
			case stringFactory.COMPANY_REMARKS_TN:
				company.setRemarks(((JTextArea)Component).getText());
				break;
			default:
				break;
			}
		}
	}
}
