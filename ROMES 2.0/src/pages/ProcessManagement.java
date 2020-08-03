package pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.border.EmptyBorder;

import org.hibernate.Session;

import entity.ProcessMaster;
import entity.Product;
import factory.miniTableFactory;
import hibernate.hibernate;
import layoutSetting.basicBorderPanel;
import layoutSetting.basicPanel;
import layoutSetting.basicTabbedPane;
import layoutSetting.doubleButtonPanel;
import layoutSetting.miniTable;
import message.errorMessage;
import message.successMessage;

@SuppressWarnings("serial")
public class ProcessManagement extends basicTabbedPane{
	public miniTable miniTable;
	public doubleButtonPanel buttonPanel;
	public ProcessManagement() {
		miniTable = new miniTableFactory().ProcessManagement(this);
		basicBorderPanel northPanel = new basicBorderPanel(Color.LIGHT_GRAY,2);
		northPanel.setLayout(new BorderLayout());
		buttonPanel= new doubleButtonPanel("추가", "저장",null);
		northPanel.add(buttonPanel,BorderLayout.WEST);
		
		StartData();
		basicPanel panel = new basicPanel();
		
		panel.setLayout(new BorderLayout(0,2));
		panel.setBorder(new EmptyBorder(5,3,0,3));
		panel.add(northPanel,BorderLayout.NORTH);
		panel.add(miniTable,BorderLayout.CENTER);
		this.addTab("공정마스터", panel);
		
		setEvent();
		StartData();
		buttonPanel.leftBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try (Session session=hibernate.factory.openSession()){
					hibernate.transaction=session.beginTransaction();
					ProcessMaster processMaster = new ProcessMaster();
					processMaster.setCycleTime("");
					processMaster.setProduct(null);
					processMaster.setProductionLine("");
					session.save(processMaster);
					miniTable.model.addRow(new Object[] {processMaster.getId(),"","","","",""});
					hibernate.transaction.commit();
				} catch (Exception e) {
					e.printStackTrace();
					new errorMessage("add");
				}
			}
		});
		buttonPanel.rightBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try (Session session=hibernate.factory.openSession()){
					hibernate.transaction=session.beginTransaction();
					for(int i=0;i<miniTable.table.getRowCount();i++) {
						ProcessMaster processMaster = session.get(ProcessMaster.class,(int)miniTable.table.getValueAt(i, 0));
						processMaster.setProduct(session.get(Product.class, (int)miniTable.table.getValueAt(i, 2)));
						processMaster.setProductionLine((String) miniTable.table.getValueAt(i, 3));
						processMaster.setCycleTime((String) miniTable.table.getValueAt(i, 4));
						session.update(processMaster);
					}
					hibernate.transaction.commit();
					new successMessage();
				} catch (Exception e) {
					e.printStackTrace();
					new errorMessage("add");
				}
			}
		});
	}
	private void setEvent() {
		// TODO Auto-generated method stub
		
	}
	String checkingProduct(ProcessMaster processMaster) {
		if(processMaster.getProduct()==null)
			return "";
		else
			return processMaster.getProduct().getName();
	}
	private void StartData() {
		try (Session session = hibernate.factory.openSession()){
			hibernate.transaction=session.beginTransaction();
			ArrayList<ProcessMaster> list = (ArrayList<ProcessMaster>)session.createCriteria(ProcessMaster.class).list();
			ArrayList<ProcessMaster> processMasters= new ArrayList<>();
			for(ProcessMaster ProcessMaster:list) {
				if(!processMasters.contains(ProcessMaster)) {
					processMasters.add(ProcessMaster);
				}
			}
			for(ProcessMaster processMaster:processMasters) {
				miniTable.model.addRow(new Object[] {processMaster.getId(),checkingProduct(processMaster)
						,processMaster.getProduct().getId(),processMaster.getProductionLine(),processMaster.getCycleTime(),""});
			}
			hibernate.transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			new errorMessage("load");
		}
		
	}
	public void refresh() {
		miniTable.model.setRowCount(0);
		StartData();
	}
}
