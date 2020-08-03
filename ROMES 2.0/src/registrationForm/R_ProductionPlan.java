package registrationForm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.hibernate.Session;

import Dao.ReceivedOrderDAO;
import entity.ReceivedOrderProduct;
import entity.Received_order_history;
import eventListener.R_ProductionPlanEvent;
import factory.colorFactory;
import functions.method;
import hibernate.hibernate;
import layoutSetting.basicBorderPanel;
import layoutSetting.basicBtn;
import layoutSetting.basicFrame;
import layoutSetting.basicPanel;
import layoutSetting.basicTextArea;
import layoutSetting.miniTable;
import message.errorMessage;

@SuppressWarnings("serial")
public class R_ProductionPlan extends basicFrame{
	miniTable miniTable;
	basicPanel pageEndPanel;
	R_ProductionPlan frame;
	public static void main(String[] args) {
		new R_ProductionPlan("");
	}
	public R_ProductionPlan(String title) {
		super(title);
		frame=this;
		String [] col = {"id","title","�����","�ֹ� ��ü","�ֹ� ��¥","�Ϸ� ����","��� ����"};
		miniTable= new miniTable(col);
		//NorthPanel
		basicBorderPanel northPanel = new basicBorderPanel(colorFactory.GRAY,1);
		northPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
		basicBtn submit = new basicBtn("����");
		northPanel.add(submit);
		
		//CenterPanel
		basicPanel centerPanel = new basicPanel();
		centerPanel.setLayout(new BorderLayout());
		centerPanel.setBorder(new CompoundBorder(new TitledBorder(new LineBorder(colorFactory.GRAY,1),"���� ���"),new EmptyBorder(3,3,3,3)));
		centerPanel.add(miniTable,BorderLayout.CENTER);
		//PageEndPanel
		pageEndPanel = new basicPanel();
		pageEndPanel.setLayout(new BoxLayout(pageEndPanel, BoxLayout.Y_AXIS));
		pageEndPanel.add(new JLabel("���õ� �׸��� �����ϴ�."));
		JScrollPane pageEndPane = new JScrollPane(pageEndPanel);
		pageEndPane.setBackground(pageEndPanel.getBackground());
		pageEndPane.setBorder(new CompoundBorder(new TitledBorder(new LineBorder(colorFactory.GRAY,1),"�� ����"),new EmptyBorder(3,3,3,3)));
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
		miniTable.table.addMouseListener(new R_ProductionPlanEvent(frame));
	}
	public miniTable getMiniTable() {
		return miniTable;
	}
	public void setMiniTable(miniTable miniTable) {
		this.miniTable = miniTable;
	}
	public basicPanel getPageEndPanel() {
		return pageEndPanel;
	}
	public void setPageEndPanel(basicPanel pageEndPanel) {
		this.pageEndPanel = pageEndPanel;
	}
	void StartData() {
		try (Session session = hibernate.factory.openSession()){
			hibernate.transaction = session.beginTransaction();
			ArrayList<Received_order_history> datas = (ArrayList<Received_order_history>) session.createCriteria(Received_order_history.class).list();
			for(Received_order_history data : datas) {
				if(data.getOrdered_company()!=null)
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