package pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.border.EmptyBorder;

import Dao.PurchaseDAO;
import entity.PurchasePrice;
import eventListener.newPurchasePriceEvent;
import factory.miniTableFactory;
import layoutSetting.basicBorderPanel;
import layoutSetting.basicPanel;
import layoutSetting.basicTabbedPane;
import layoutSetting.doubleButtonPanel;
import layoutSetting.miniTable;

@SuppressWarnings("serial")
public class purchasePriceManagement extends basicTabbedPane{
	public miniTable miniTable;
	public doubleButtonPanel buttonPanel;
	purchasePriceManagement priceManagement;
	public purchasePriceManagement() {
		priceManagement = this;
		miniTable = new miniTableFactory().PurchasePriceManagement(this);
		basicBorderPanel northPanel = new basicBorderPanel(Color.LIGHT_GRAY,2);
		northPanel.setLayout(new BorderLayout());
		buttonPanel= new doubleButtonPanel("등록", "항목별 조회",null);
		northPanel.add(buttonPanel,BorderLayout.WEST);
		
		StartData();
		basicPanel panel = new basicPanel();
		
		panel.setLayout(new BorderLayout(0,2));
		panel.setBorder(new EmptyBorder(5,3,0,3));
		panel.add(northPanel,BorderLayout.NORTH);
		panel.add(miniTable,BorderLayout.CENTER);
		this.addTab("매입품 관리", panel);
		
		setEvent();
	}
	
	private void setEvent() {
		buttonPanel.leftBtn.addActionListener(new newPurchasePriceEvent(this));
	}
	
	private void StartData() {
		PurchaseDAO purchaseDAO = new PurchaseDAO();
		ArrayList<PurchasePrice>list =purchaseDAO.getPurchaseArray();
		for(PurchasePrice data:list) {
			if(data.getOrdering_company()!=null&&data.getPurchaseProduct()!=null) {
				miniTable.model.addRow(new Object[] {data.getId(),data.getPurchaseProduct().getName()
						,"",data.getOrdering_company().getCompanyName(),"",data.getPrice()});	
			}else {
				miniTable.model.addRow(new Object[] {data.getId(),"","","","",data.getPrice()});
			}
		}
	}
	
	public void refresh() {
		// TODO Auto-generated method stub
		miniTable.model.setRowCount(0);
		StartData();
	}
}
