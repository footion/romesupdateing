package pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import org.hibernate.Session;

import Main.main;
import entity.Received_order_history;
import eventListener.cancelEvent;
import eventListener.newOrderEvent;
import eventListener.R_ReceivedOrderEvent;
import eventListener.sendEstimateEvent;
import eventListener.updateCompanyEvent;
import factory.miniTableFactory;
import functions.searchBar;
import hibernate.hibernate;
import layoutSetting.basicBorderPanel;
import layoutSetting.basicFrame;
import layoutSetting.basicPanel;
import layoutSetting.basicTabbedPane;
import layoutSetting.doubleButtonPanel;
import layoutSetting.miniTable;
import registrationForm.R_receivedOrder;

@SuppressWarnings("serial")
public class ReceivedOrderManagement extends basicTabbedPane{
	public miniTable miniTable;
	ReceivedOrderManagement orderManagement;
	doubleButtonPanel buttonPanel;
	public ReceivedOrderManagement() {
		orderManagement=this;
		basicBorderPanel northPanel = new basicBorderPanel(Color.LIGHT_GRAY,2);
		//searchBar searchBar=new searchBar(new String [] {"옵션"});
		northPanel.setLayout(new BorderLayout());
		buttonPanel = new doubleButtonPanel("등록", "견적서 발송",null);
		buttonPanel.rightBtn.setPreferredSize(new Dimension(100,27));
		buttonPanel.leftBtn.setPreferredSize(new Dimension(75,27));
		buttonPanel.leftBtn.setMnemonic(KeyEvent.VK_F1);
		northPanel.add(buttonPanel,BorderLayout.WEST);
		//northPanel.add(searchBar,BorderLayout.EAST);
		miniTable = new miniTableFactory().ReceivedOrderManagementTable(this);
		
		StartData();
		basicPanel panel = new basicPanel();
				
		panel.setLayout(new BorderLayout(0,2));
		panel.setBorder(new EmptyBorder(5,3,0,3));
		panel.add(northPanel,BorderLayout.NORTH);
		panel.add(miniTable,BorderLayout.CENTER);
		this.addTab("수주 관리", panel);
		
		setEvent();
		
	}
	void StartData() {
		try (Session session = hibernate.factory.openSession()){
			hibernate.transaction = session.beginTransaction();
			ArrayList<Received_order_history> datas = (ArrayList<Received_order_history>) session.createCriteria(Received_order_history.class).list();
			for(Received_order_history data : datas) {
				if(data.getOrdered_company()!=null) {
					miniTable.model.addRow(new Object[] {data.getId(),data.getTitle(),"",data.getManager()
							,data.getOrdered_company().getCompanyName(),data.getOrder_date(),data.getDeadline(),data.getType(),drawProgress(data),""});
					}else {
						miniTable.model.addRow(new Object[] {data.getId(),data.getTitle(),"",data.getManager()
								,"",data.getOrder_date(),data.getDeadline(),data.getType(),drawProgress(data),""});
					}
				}
			hibernate.transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void refresh() {
		miniTable.model.setRowCount(0);
		StartData();
	}
	void setEvent() {
		buttonPanel.rightBtn.addActionListener(new sendEstimateEvent(miniTable.table));
		buttonPanel.leftBtn.addActionListener(new newOrderEvent(orderManagement));
	}
	String drawProgress(Received_order_history data) {
		String progress=null;
		if(data.isProductPlan()==true) {
			progress= "생산 계획";
			if(data.isProductOrder()==true) {
				progress= "생산 지시";
				if(data.isProductRelease()==true) {
					progress="출고";
					if(data.isProductShipment()==true) {
						progress="출하";
					}
				}
			}
		}else {
			progress = "주문 접수";
		}
		return progress;
	}
}
