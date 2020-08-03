package eventListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.hibernate.Session;

import Dao.ReceivedOrderDAO;
import entity.Product;
import entity.ReceivedOrderProduct;
import entity.Received_order_history;
import hibernate.hibernate;
import layoutSetting.miniTable;
import message.successMessage;
import pages.ReceivedOrderManagement;
import registrationForm.R_receivedOrder;

public class updateOrderEvent implements ActionListener{
	int Key;
	R_receivedOrder OrderRegistration;
	ReceivedOrderManagement OrderManagement;
	public updateOrderEvent(int Key,R_receivedOrder orderRegistration) {
		OrderRegistration=orderRegistration;
		OrderManagement=(ReceivedOrderManagement) orderRegistration.getTabbedPane();
		this.Key=Key;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try (Session session = hibernate.factory.openSession()){
			hibernate.transaction = session.beginTransaction();
			//update
			//orderRegisteration companyid --> orderid  changeMethod
			Received_order_history data = session.get(Received_order_history.class, Key);
			OrderRegistration.updateData(data, session);
			//save order model
			updateOrder_product(data, OrderRegistration.getMinitable(),session);
			if(data.getOrdered_company()==null) {
				JOptionPane.showMessageDialog(null, "주문 업체를 선택하세요.","Null Company",JOptionPane.WARNING_MESSAGE);
			}else if (data.getProducts().size()==0) {
				JOptionPane.showMessageDialog(null, "제품을 선택하세요.","Null Product",JOptionPane.WARNING_MESSAGE);
			}else {
				session.update(data);
				OrderManagement.remove(OrderRegistration);
				new successMessage();
			}
			hibernate.transaction.commit();
		} catch (Exception error) {
			error.printStackTrace();
		}
		OrderManagement.refresh();
	}
	void updateOrder_product(Received_order_history order, miniTable miniTable,Session session) {
		int loopNum =0;
		for(int i=0;i<miniTable.table.getRowCount();i++) {
			boolean isProductId=true;
			ReceivedOrderProduct ROproduct;
			try {
				int ROProductid=(int) miniTable.table.getValueAt(i,0);
				ROproduct = session.get(ReceivedOrderProduct.class, ROProductid);
			} catch (ClassCastException e) {
				System.out.println("ClassCastException");
				ROproduct=new ReceivedOrderProduct();
				ROproduct.setLotNo(new ReceivedOrderDAO().lotGeneraterInTransaction(loopNum));
				loopNum++;
			}
			ROproduct.setOrder(order);
			try {ROproduct.setProduct(session.get(Product.class,(int) miniTable.table.getValueAt(i, 3)));
			} catch (ClassCastException e) {
				System.out.println("ClassCastException");
				isProductId=false;
			}
			ROproduct.setSize((String) miniTable.table.getValueAt(i, 5));
			ROproduct.setUnit((String) miniTable.table.getValueAt(i, 6));
			ROproduct.setQuantity((String) miniTable.table.getValueAt(i, 7));
			ROproduct.setPrice((String) miniTable.table.getValueAt(i, 8));
			ROproduct.setRemarks((String) miniTable.table.getValueAt(i, 9));
			if(isProductId) {
				session.save(ROproduct);
				order.addProduct(ROproduct);
			}
		}
	}
}
