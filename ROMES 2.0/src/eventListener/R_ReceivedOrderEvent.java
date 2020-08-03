package eventListener;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.hibernate.Session;

import Dao.CompanyDAO;
import Dao.ProductDAO;
import Dao.ReceivedOrderDAO;
import entity.Received_order_history;
import entity.ReceivedOrderProduct;
import entity.Product;
import entity.Company;
import factory.nameFactory;
import functions.datePickerBar;
import hibernate.hibernate;
import layoutSetting.basicTextArea;
import layoutSetting.miniTable;
import message.errorMessage;
import message.successMessage;
import pages.ReceivedOrderManagement;
import registrationForm.R_receivedOrder;

public class R_ReceivedOrderEvent implements ActionListener{
	R_receivedOrder OrderRegistration;
//	Container LeftBox;
//	Container RightBox;
//	Container TitlePanel;
	miniTable miniTable;
	ReceivedOrderManagement OrderManagement;
	
	public R_ReceivedOrderEvent(R_receivedOrder r_receivedOrder) {
		OrderRegistration=r_receivedOrder;
		OrderManagement=(ReceivedOrderManagement) r_receivedOrder.getTabbedPane();
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("save Event :");
		try (Session session = hibernate.factory.openSession()){
			hibernate.transaction = session.beginTransaction();
			
			Received_order_history order = OrderRegistration.getOrderData(session);
			//save order model
			saveOrder_Product(order,OrderRegistration.getMinitable(),session);
			if(order.getOrdered_company()==null) {
				JOptionPane.showMessageDialog(null, "주문 업체를 선택하세요.","Null Company",JOptionPane.WARNING_MESSAGE);
			}else if (order.getProducts().size()==0) {
				JOptionPane.showMessageDialog(null, "제품을 선택하세요.","Null Product",JOptionPane.WARNING_MESSAGE);
			}else {
				session.save(order);
				OrderManagement.remove(OrderRegistration);
				new successMessage();
			}
			hibernate.transaction.commit();
			//수주 등록후 테이블 갱신
			OrderManagement.refresh();
		} catch (Exception e) {
			new errorMessage("save");
			e.printStackTrace();
		}
		
	}
	void saveOrder_Product(Received_order_history order, miniTable miniTable,Session session) {
		for(int i=0;i<miniTable.table.getRowCount();i++) {
			boolean isProductId=true;
			ReceivedOrderProduct product = new ReceivedOrderProduct();
			product.setOrder(order);
			try {
				product.setProduct(session.get(Product.class,(int) miniTable.table.getValueAt(i, 3)));
			} catch (Exception e) {
				isProductId=false;
			}
			product.setSize((String) miniTable.table.getValueAt(i, 5));
			product.setUnit((String) miniTable.table.getValueAt(i, 6));
			product.setQuantity((String) miniTable.table.getValueAt(i, 7));
			product.setPrice((String) miniTable.table.getValueAt(i, 8));
			product.setRemarks((String) miniTable.table.getValueAt(i, 9));
			product.setLotNo(new ReceivedOrderDAO().lotGeneraterInTransaction(i));
			if(isProductId) {
				order.addProduct(product);
				session.save(product);
			}
		}
	}
}
