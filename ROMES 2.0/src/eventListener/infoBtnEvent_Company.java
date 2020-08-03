package eventListener;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import entity.Company;
import entity.Company_manager;
import factory.ImgFactory;
import factory.componentFactory;
import factory.instanceofFactory;
import factory.miniTableFactory;
import factory.nameFactory;
import factory.stringFactory;
import functions.managerProfile;
import functions.method;
import hibernate.hibernate;
import layoutSetting.ImageLabel;
import layoutSetting.basicTabbedPane;
import layoutSetting.basicTextArea;
import layoutSetting.miniTable;
import message.errorMessage;
import pages.companyManagement;
import pages.purchasePriceManagement;
import registrationForm.R_company;
import registrationForm.R_purchasePrice;
import registrationForm.R_receivedOrder;

public class infoBtnEvent_Company implements ActionListener {
	//JTextField TextField;
	basicTabbedPane TabbedPane;
	R_company R_company;
	int CompanyKey;
	Container Container;
	miniTable MiniTable;
	int KEYCOLUMN;
	public infoBtnEvent_Company(basicTabbedPane tabbedPane,Container container) {
		TabbedPane=tabbedPane;
		Container=container;
	}
	public infoBtnEvent_Company(basicTabbedPane tabbedPane,miniTable miniTable,int keycolumn) {
		TabbedPane=tabbedPane;
		MiniTable=miniTable;
		KEYCOLUMN=keycolumn;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("EVENT : COMPANY INFO");
		if(Container!=null) {
			if(Container instanceof R_receivedOrder) {
				CompanyKey=((R_receivedOrder) Container).returnCompanyKey();
			}else if(Container instanceof R_purchasePrice) {
				CompanyKey=((R_purchasePrice) Container).returnCompanyKey();
			}
		}else if(MiniTable!=null) {
			try {CompanyKey=(int)MiniTable.table.getValueAt(MiniTable.table.getSelectedRow(), KEYCOLUMN);
			} catch (Exception error) {
				error.printStackTrace();
				JOptionPane.showMessageDialog(null, "주문 업체를 선택해주세요.","Message",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		paintCompanyInfo();
	}

	void paintCompanyInfo() {
		try (Session session = hibernate.factory.openSession()) {
			hibernate.transaction = session.beginTransaction();
			
			if(CompanyKey==0)
				JOptionPane.showMessageDialog(null, "주문 업체를 선택해주세요.","Message",JOptionPane.INFORMATION_MESSAGE);
			else {
				Company company = session.get(Company.class, CompanyKey);
				//create info frame
				R_company = new R_company(TabbedPane);
				//setUpdateBtn
				setInfoTab(R_company.doubleButtonPanel.leftBtn);
				//paint company info
				importCompanyInfo(company, R_company.leftBox);
				importCompanyInfo(company, R_company.rightBox);
				importCompanyInfo(company, R_company.titlePanel);
				importCompanyManager(company,R_company.profilePanel);
				importCompanyImage(company);
				
				TabbedPane.addCancelableTab(R_company.companyName.getText(), R_company);
			}
			hibernate.transaction.commit();
		} catch (Exception e) {
			new errorMessage("load");
			e.printStackTrace();
		}
	}
	void importCompanyImage(Company company){
		if(company.getImage() !=null) {
			R_company.getImageLabel().setImageFromDB(company.getImage());
			R_company.getImageLabel().setImageSize(R_company.imageWidth, R_company.imageHeight);
		}
	}
	void importCompanyInfo(Company company,Container container) {
		System.out.println("Loading company info..");
		for(Component component : container.getComponents()) {
			if(component instanceof JPanel) {
				for(Component insideComponent : ((JPanel) component).getComponents()) {
					if(insideComponent instanceof JTextField
							|| insideComponent instanceof JComboBox) {
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
	void importCompanyManager(Company company,Container container) {
		System.out.println("Manager : "+company.getManagers().size());
		for(Company_manager manager:company.getManagers()) {
			managerProfile userProfile=new managerProfile();
			if(manager.getImage()!=null) {
				System.out.println("get manager image");
				userProfile.getImageLabel().setImageFromDB(manager.getImage());
				userProfile.getImageLabel().setImageSize(userProfile.imageWidth, userProfile.imageHeight);
				userProfile.setImageByte(manager.getImage());
			}
			userProfile.USERNAME=manager.getName();
			userProfile.POSITION=manager.getPosition();
			userProfile.PHONE=manager.getPhone();
			userProfile.EMAIL=manager.getEmail();
			userProfile.MEMO=manager.getRemarks();
			userProfile.refreshLabel();
			createProfile(userProfile);
			System.out.println("import Manager Info");
		}
	}
	void setInfoTab(JButton button) {
		button.setText("Update");
		for(ActionListener actionListener:button.getActionListeners()) {
			button.removeActionListener(actionListener);
		}
		button.addActionListener(new updateCompanyEvent(CompanyKey,R_company,TabbedPane));
	}

	void importData(Company company, Component Component) {
		if(Component.getName()==null) {
			
		}else {
			switch (Component.getName()) {
			case nameFactory.COMPANY_NAME_N:
				((JLabel) Component).setText(company.getCompanyName());
				break;
			case nameFactory.COMPANY_CLASSIFICATION_N:
				((JComboBox)Component).setSelectedItem(company.getCompanyClassification());
				break;
			case nameFactory.COMPANY_CEONAME_TN:
				((JTextField) Component).setText(company.getCEO_Name());
				break;
			case nameFactory.COMPANY_BUSINESSNUMBER_TN:
				((JTextField) Component).setText(company.getBusinessNumber());
				break;
			case nameFactory.COMPANY_INDUSTRYTYPE_TN:
				((JTextField) Component).setText(company.getIndustryType());
				break;
			case nameFactory.COMPANY_INDUSTRYTYPE_DETAIL_TN:
				((JTextField) Component).setText(company.getIndustryType_detail());
				break;
			case nameFactory.COMPANY_FAX_TN:
				((JTextField) Component).setText(company.getFax());
				break;
			case nameFactory.COMPANY_ADDRESS_TN:
				((JTextField) Component).setText(company.getAddress());
				break;
			case nameFactory.COMPANY_EMAIL_TN:
				((JTextField) Component).setText(company.getEmail());
				break;
			case nameFactory.COMPANY_PHONE_TN:
				((JTextField) Component).setText(company.getPhone());
				break;
			case nameFactory.COMPANY_ACCOUNTNUMBER_TN:
				((JTextField) Component).setText(company.getAccountNumber());
				break;
			case nameFactory.COMPANY_REMARKS_TN:
				((JTextArea) Component).setText(company.getRemarks());
				break;
			case "null":
				break;
			default:
				break;
			}
		}
	}
	void createProfile(managerProfile userProfile) {
		System.out.println("createProfile Info");
		userProfile.delBtn.addActionListener(new deleteProfileEvent(R_company.profilePanel, userProfile));
		R_company.profilePanel.add(userProfile);
		method.refreshComponent(R_company.profilePanel);
	}
}
