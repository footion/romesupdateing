package evevtListener;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import entity.Company;
import factory.stringFactory;
import hibernate.hibernate;
import layoutSetting.basicTextArea;
import registrationFrame.companyRegistration;

public class infoBtnEvent implements ActionListener {
	String Type;
	JTextField TextField;

	public infoBtnEvent(String type, JTextField textField) {
		Type = type;
		TextField = textField;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (Type.equals(stringFactory.TYPE_USER)) {

		} else if (Type.equals(stringFactory.TYPE_CLIENT)) {
			System.out.println("EVENT : COMPANY INFO");
			readCompanyInfo(TextField);
		} else if (Type.equals(stringFactory.TYPE_MODEL)) {

		}
	}

	void readCompanyInfo(JTextField textField) {
		try (Session session = hibernate.factory.openSession()) {
			hibernate.transaction = session.beginTransaction();
			ArrayList<Company> companies = (ArrayList<Company>) session.createCriteria(Company.class)
					.add(Restrictions.eq("companyName", textField.getText())).list();
			if(companies.size()==0)
				JOptionPane.showMessageDialog(null, "�ֹ� ��ü�� �������ּ���.","Message",JOptionPane.INFORMATION_MESSAGE);
			for (Company company : companies) {
				//create info frame
				CompanyRegistration companyRegistration = new CompanyRegistration();
				//setUpdateBtn
				setUpdateBtn(companyRegistration.doubleButtonPanel.leftBtn);
				//paint company info
				paintCompanyInfo(company, companyRegistration.leftBox);
				paintCompanyInfo(company, companyRegistration.rightBox);
				paintCompanyInfo(company, companyRegistration.titlePanel);
			}
			hibernate.transaction.commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Failed to load an error!","Error",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	void paintCompanyInfo(Company company,Container container) {
		System.out.println("Loading company info..");
		for(Component component : container.getComponents()) {
			if(component instanceof JPanel) {
				for(Component insideComponent : ((JPanel) component).getComponents()) {
					if(insideComponent instanceof JTextField) {
						System.out.println("Name : "+insideComponent.getName());
						importData(company, insideComponent);
					}else if(insideComponent instanceof basicTextArea) {
						for(Component basicTextAreaCom:((basicTextArea) insideComponent).getViewport().getComponents()) {
							if(basicTextAreaCom instanceof JTextArea) {
								System.out.println("Name : "+basicTextAreaCom.getName());
								importData(company, basicTextAreaCom);
							}
						}
					}
				}
			}else if(component instanceof JLabel) {
				System.out.println("Name : "+component.getName());
				importData(company, component);
			}
		}
	}
	void setUpdateBtn(JButton button) {
		button.setText("Update");
		for(ActionListener actionListener:button.getActionListeners()) {
			button.removeActionListener(actionListener);
		}
		button.addActionListener(new updateCompanyEvent());
	}

	void importData(Company company, Component Component) {
		if(Component.getName()==null) {
			
		}else {
			switch (Component.getName()) {
			case stringFactory.COMPANY_NAME_N:
				((JLabel) Component).setText(company.getCompanyName());
				break;
			case stringFactory.COMPANY_CLASSIFICATION_N:
				((JLabel) Component).setText(company.getCompanyClassification());
				break;
			case stringFactory.COMPANY_CEONAME_TN:
				((JTextField) Component).setText(company.getCEO_Name());
				break;
			case stringFactory.COMPANY_BUSINESSNUMBER_TN:
				((JTextField) Component).setText(company.getBusinessNumber());
				break;
			case stringFactory.COMPANY_INDUSTRYTYPE_TN:
				((JTextField) Component).setText(company.getIndustryType());
				break;
			case stringFactory.COMPANY_INDUSTRYTYPE_DETAIL_TN:
				((JTextField) Component).setText(company.getIndustryType_detail());
				break;
			case stringFactory.COMPANY_TAG_TN:
				((JTextField) Component).setText(company.getTag());
				break;
			case stringFactory.COMPANY_TAG_Detail_TN:
				((JTextField) Component).setText(company.getTag_detail());
				break;
			case stringFactory.COMPANY_ADDRESS_TN:
				((JTextField) Component).setText(company.getAddress());
				break;
			case stringFactory.COMPANY_EMAIL_TN:
				((JTextField) Component).setText(company.getEmail());
				break;
			case stringFactory.COMPANY_PHONE_TN:
				((JTextField) Component).setText(company.getPhone());
				break;
			case stringFactory.COMPANY_ACCOUNTNUMBER_TN:
				((JTextField) Component).setText(company.getAccountNumber());
				break;
			case stringFactory.COMPANY_REMARKS_TN:
				((JTextArea) Component).setText(company.getRemarks());
				break;
			case "null":
				break;
			default:
				break;
			}
		}
	}
}
