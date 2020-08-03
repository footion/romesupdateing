package registrationForm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.hibernate.Session;

import entity.Received_order_history;
import eventListener.R_ProductionPlanInfoEvent;
import eventListener.R_ProductionPlanSubmitEvent;
import factory.colorFactory;
import hibernate.hibernate;
import layoutSetting.basicBorderPanel;
import layoutSetting.basicBtn;
import layoutSetting.basicFrame;
import layoutSetting.basicPanel;
import layoutSetting.miniTable;
import message.errorMessage;
import pages.ProductionPlanManagement;

@SuppressWarnings("serial")
public class R_ProductionPlan extends basicFrame{
	miniTable miniTable;
	basicPanel pageEndPanel;
	R_ProductionPlan frame;
	ProductionPlanManagement TabbedPane;
	basicBtn submit;
	public R_ProductionPlan(ProductionPlanManagement planManagement) {
		super("Submit frame");
		frame=this;
		this.TabbedPane=planManagement;
		String [] col = {"id","title","담당자","주문 업체","주문 날짜","완료 기한","계약 유형"};
		miniTable= new miniTable(col);
		//NorthPanel
		basicBorderPanel northPanel = new basicBorderPanel(colorFactory.GRAY,1);
		northPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
		submit = new basicBtn("전송");
		northPanel.add(submit);
		
		//CenterPanel
		basicPanel centerPanel = new basicPanel();
		centerPanel.setLayout(new BorderLayout());
		centerPanel.setBorder(new CompoundBorder(new TitledBorder(new LineBorder(colorFactory.GRAY,1),"수주 목록"),new EmptyBorder(3,3,3,3)));
		centerPanel.add(miniTable,BorderLayout.CENTER);
		//PageEndPanel
		pageEndPanel = new basicPanel();
		pageEndPanel.setLayout(new BoxLayout(pageEndPanel, BoxLayout.Y_AXIS));
		pageEndPanel.add(new JLabel("선택된 항목이 없습니다."));
		JScrollPane pageEndPane = new JScrollPane(pageEndPanel);
		pageEndPane.setBackground(pageEndPanel.getBackground());
		pageEndPane.setBorder(new CompoundBorder(new TitledBorder(new LineBorder(colorFactory.GRAY,1),"상세 정보"),new EmptyBorder(3,3,3,3)));
		pageEndPane.setPreferredSize(new Dimension(0,170));
		
		basicPanel panel = new basicPanel();
		panel.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED),new EmptyBorder(8,6,5,6)));
		panel.setLayout(new BorderLayout(0,4));
		panel.add(northPanel,BorderLayout.NORTH);
		panel.add(centerPanel,BorderLayout.CENTER);
		panel.add(pageEndPane,BorderLayout.PAGE_END);
		
		StartData();
		addEvent();
		this.add(panel);
		this.setVisible(true);
		this.setSize(800,600);
		this.setLocationRelativeTo(null);
		//this.setResizable(false);
	}
	private void addEvent() {
		miniTable.table.addMouseListener(new R_ProductionPlanInfoEvent(frame));
		submit.addActionListener(new R_ProductionPlanSubmitEvent(this));
	}
	public miniTable getMiniTable() {
		return miniTable;
	}
	public basicPanel getPageEndPanel() {
		return pageEndPanel;
	}
	public ProductionPlanManagement getTabbedPane() {
		return TabbedPane;
	}
	void StartData() {
		try (Session session = hibernate.factory.openSession()){
			hibernate.transaction = session.beginTransaction();
			ArrayList<Received_order_history> datas = (ArrayList<Received_order_history>) session.createCriteria(Received_order_history.class).list();
			for(Received_order_history data : datas) {
				if(data.isProductPlan()==false)
					miniTable.model.addRow(new Object[] {data.getId(),data.getTitle(),data.getManager()
							,data.getOrdered_company().getCompanyName(),data.getOrder_date(),data.getDeadline(),data.getType()});
				}
			hibernate.transaction.commit();
		} catch (Exception e) {
			new errorMessage("load");
			e.printStackTrace();
		}
	}
}
