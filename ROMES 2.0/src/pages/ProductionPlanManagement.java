package pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.EmptyBorder;

import factory.miniTableFactory;
import layoutSetting.basicBorderPanel;
import layoutSetting.basicBtn;
import layoutSetting.basicPanel;
import layoutSetting.basicTabbedPane;
import layoutSetting.miniTable;
import registrationForm.R_ProductionPlan;

@SuppressWarnings("serial")
public class ProductionPlanManagement extends basicTabbedPane{
	public miniTable miniTable;
	basicBtn register;
	basicBtn emergency;
	basicBtn simulation;
	basicBtn workorder;
	public ProductionPlanManagement() {
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
		
		miniTable.model.addRow(new Object[] {"","","","","","","","","",""});
		miniTable.model.addRow(new Object[] {"","","","","","","","","",""});
		miniTable.model.addRow(new Object[] {"","","","","","","","","",""});
		miniTable.model.addRow(new Object[] {"","","","","","","","","",""});
		
		register.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new R_ProductionPlan("Select Order");
				
			}
		});
	}
	private void setEvent() {
		// TODO Auto-generated method stub
		
	}
	private void StartData() {
		// TODO Auto-generated method stub
		
	}
	public void refresh() {
		// TODO Auto-generated method stub
		
	}
}
