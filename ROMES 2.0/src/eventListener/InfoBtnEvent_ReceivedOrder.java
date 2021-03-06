package eventListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTable;

import org.hibernate.Session;

import entity.ProductionPlan;
import entity.ReceivedOrderProduct;
import entity.Received_order_history;
import hibernate.hibernate;
import layoutSetting.basicTabbedPane;
import pages.ProductionPlanManagement;
import pages.ReceivedOrderManagement;
import registrationForm.R_receivedOrder;

public class InfoBtnEvent_ReceivedOrder implements ActionListener{
	R_receivedOrder OrderRegistration;
	ReceivedOrderManagement OrderManagement;
	String order_id;
	Received_order_history data;
	ProductionPlanManagement planManagement;
	public InfoBtnEvent_ReceivedOrder(basicTabbedPane tabbedPane) {
		if(tabbedPane instanceof ReceivedOrderManagement)
			OrderManagement =(ReceivedOrderManagement) tabbedPane;
		else if(tabbedPane instanceof ProductionPlanManagement)
			planManagement=(ProductionPlanManagement) tabbedPane;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("click infoBtn");
		paintOrderInfo();
	}
	void paintOrderInfo() {
		try (Session session=hibernate.factory.openSession()){
			hibernate.transaction=session.beginTransaction();
			JTable table=null;
			if(OrderManagement!=null) {
				table = OrderManagement.miniTable.table;
				order_id = Integer.toString((int) table.getValueAt(table.getSelectedRow(), 0));
				
			}
			else if(planManagement != null) {
				table =planManagement.getMiniTable().table;
				ProductionPlan plan =session.get(ProductionPlan.class, (int) table.getValueAt(table.getSelectedRow(), 0));
				order_id=Integer.toString(plan.getReceivedOrder().getId());
			}
			data = session.get(Received_order_history.class, Integer.parseInt(order_id));
			OrderRegistration = new R_receivedOrder(OrderManagement);
			OrderRegistration.setCompanykey(data.getOrdered_company().getId());
			//importData
			OrderRegistration.setOrderData(data);
			//addTabbedPane
			if(OrderManagement!=null)
				OrderManagement.addCancelableTab(OrderRegistration.getTitleText().getText(), OrderRegistration);
			else if(planManagement != null)
				planManagement.addCancelableTab(OrderRegistration.getTitleText().getText(), OrderRegistration);
			paintProduct(data);
			//changeBtn
			setUpdateForm(OrderRegistration.getSaveBtn());
			//refreshTotalPrice
			OrderRegistration.totalPrice.refreshPrice();
			
			hibernate.transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	void paintProduct(Received_order_history data) {
		for(ReceivedOrderProduct product : data.getProducts()) {
			OrderRegistration.getMinitable().model.addRow(new Object[] {product.getId(),product.getProduct().getName()
					,"",product.getProduct().getId(),product.getLotNo(),product.getSize(),product.getUnit(),product.getQuantity(),product.getPrice()
					,product.getRemarks(),""});
		}
	}
	void setUpdateForm(JButton button) {
		button.setText("Update");
		for(ActionListener actionListener:button.getActionListeners()) {
			button.removeActionListener(actionListener);
		}
		button.addActionListener(new updateOrderEvent(Integer.parseInt(order_id),OrderRegistration));
	}
}
