package pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.border.EmptyBorder;

import org.hibernate.Session;

import entity.Company;
import eventListener.newCompanyEvent;
import factory.miniTableFactory;
import hibernate.hibernate;
import layoutSetting.basicBorderPanel;
import layoutSetting.basicPanel;
import layoutSetting.basicTabbedPane;
import layoutSetting.doubleButtonPanel;
import layoutSetting.miniTable;

@SuppressWarnings("serial")
public class companyManagement extends basicTabbedPane{
	public miniTable miniTable;
	public doubleButtonPanel buttonPanel;
	public companyManagement() {
		miniTable = new miniTableFactory().companyManagementTable(this);
		basicBorderPanel northPanel = new basicBorderPanel(Color.LIGHT_GRAY,2);
		northPanel.setLayout(new BorderLayout());
		buttonPanel = new doubleButtonPanel("등록", "...",null);
//		buttonPanel.rightBtn.setPreferredSize(new Dimension(100,27));
//		buttonPanel.leftBtn.setPreferredSize(new Dimension(75,27));
		buttonPanel.leftBtn.setMnemonic(KeyEvent.VK_F1);
		northPanel.add(buttonPanel,BorderLayout.WEST);
		
		StartData();
		basicPanel panel = new basicPanel();
				
		panel.setLayout(new BorderLayout(0,2));
		panel.setBorder(new EmptyBorder(5,3,0,3));
		panel.add(northPanel,BorderLayout.NORTH);
		panel.add(miniTable,BorderLayout.CENTER);
		this.addTab("거래처관리", panel);
		
		setEvent();
	}
	private void setEvent() {
		// TODO Auto-generated method stub
		buttonPanel.leftBtn.addActionListener(new newCompanyEvent(this));
	}public void refresh() {
		miniTable.model.setRowCount(0);
		StartData();
	}
	private void StartData() {
		// TODO Auto-generated method stub
		try (Session session = hibernate.factory.openSession()){
			hibernate.transaction = session.beginTransaction();
			ArrayList<Company> companies = (ArrayList<Company>)session.createCriteria(Company.class).list();
			for(Company company:companies) {
				miniTable.model.addRow(new Object[] {company.getId(),company.getCompanyClassification()
						,company.getCompanyName(),"",company.getCEO_Name(),company.getBusinessNumber()
						,company.getIndustryType(),company.getIndustryType_detail(),company.getPhone()
						,company.getFax(),""});
			}
			hibernate.transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
