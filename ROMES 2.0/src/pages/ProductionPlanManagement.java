package pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.border.EmptyBorder;

import org.hibernate.Session;

import Dao.ProductionPlanDAO;
import entity.BomEntity;
import entity.ProductionPlan;
import entity.Received_order_history;
import factory.miniTableFactory;
import hibernate.hibernate;
import layoutSetting.basicBorderPanel;
import layoutSetting.basicBtn;
import layoutSetting.basicPanel;
import layoutSetting.basicTabbedPane;
import layoutSetting.miniTable;
import message.errorMessage;
import message.successMessage;
import registrationForm.R_ProductionPlan;

@SuppressWarnings("serial")
public class ProductionPlanManagement extends basicTabbedPane{
	private miniTable miniTable;
	basicBtn register;
	basicBtn emergency;
	basicBtn simulation;
	basicBtn workorder;
	ProductionPlanManagement planManagement;
	public ProductionPlanManagement() {
		planManagement=this;
		miniTable = new miniTableFactory().ProductionPlanManagement(this);
		basicBorderPanel northPanel = new basicBorderPanel(Color.LIGHT_GRAY,2);
		northPanel.setLayout(new BorderLayout());
		basicPanel buttonPanel= new basicPanel();
		register= new basicBtn("등록");
		emergency=new basicBtn("긴급");
		simulation = new basicBtn("검토");
		workorder = new basicBtn("작업 지시");
		buttonPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
		buttonPanel.add(register);
		buttonPanel.add(emergency);
		buttonPanel.add(simulation);
		buttonPanel.add(workorder);
		northPanel.add(buttonPanel,BorderLayout.WEST);
		
		StartData();
		
		basicPanel panel = new basicPanel();
		panel.setLayout(new BorderLayout(0,2));
		panel.setBorder(new EmptyBorder(5,3,0,3));
		panel.add(northPanel,BorderLayout.NORTH);
		panel.add(miniTable,BorderLayout.CENTER);
		
		this.addTab("생산 계획", panel);
		
		setEvent();
		StartData();
		
		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new R_ProductionPlan(planManagement);
			}
		});
	}
	private void setEvent() {
		// TODO Auto-generated method stub
		
	}
	private String checkingMaterial(ProductionPlan plan) {
		if(plan.getMaterial()==null) {
			return "";
		}else {
			return plan.getMaterial().getName();
		}
	}
	private String checkingQuantity(ProductionPlan plan) {
		if(plan.getMaterial()==null) {
			return plan.getROproduct().getQuantity();
		}else {
			for(BomEntity bom :plan.getROproduct().getProduct().getBoms()) {
				if(bom.getMeterial().equals(plan.getMaterial())) {
					int materialQuantity=bom.getCount();
					int finalQuantity=materialQuantity*(Integer.parseInt(plan.getROproduct().getQuantity()));
					return Integer.toString(finalQuantity);
				}
			}
			return "error";
		}
	}
	private void StartData() {
		try (Session session = hibernate.factory.openSession()){
			hibernate.transaction = session.beginTransaction();
			ArrayList<ProductionPlan> plans = (ArrayList<ProductionPlan>)session.createCriteria(ProductionPlan.class).list();
			for(ProductionPlan plan : plans) {
				Received_order_history RO= plan.getReceivedOrder();
				miniTable.model.addRow(new Object[] {plan.getId(),miniTable.model.getRowCount()+1
						,plan.getProductionLine(),RO.getTitle(),"",plan.getROproduct().getProduct().getName()
						,checkingMaterial(plan),plan.getROproduct().getLotNo(),checkingQuantity(plan)
						,RO.getOrder_date(),RO.getDeadline(),"","","",RO.getRemarks(),""});	
			}
			hibernate.transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			new errorMessage("load data");
		}
	}
	public void refresh() {
		miniTable.model.setRowCount(0);
		StartData();
	}
	public miniTable getMiniTable() {
		return miniTable;
	}
}
