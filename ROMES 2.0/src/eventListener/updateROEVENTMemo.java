//package eventListener;
//
//import java.awt.Component;
//import java.awt.Container;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.List;
//
//import javax.swing.JComboBox;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JTable;
//import javax.swing.JTextArea;
//import javax.swing.JTextField;
//
//import org.hibernate.Session;
//
//import Dao.ReceivedOrderDAO;
//import entity.ReceivedOrderProduct;
//import entity.Product;
//import entity.Company;
//import entity.Received_order_history;
//import factory.nameFactory;
//import functions.datePickerBar;
//import hibernate.hibernate;
//import layoutSetting.basicTextArea;
//import layoutSetting.miniTable;
//import message.successMessage;
//import pages.ReceivedOrderManagement;
//import registrationForm.R_receivedOrder;
//
//public class updateOrderEvent implements ActionListener{
//	int KEY;
//	Received_order_history data;
//	R_receivedOrder OrderRegistration;
//	Container LeftBox;
//	Container RightBox;
//	Container TitlePanel;
//	miniTable miniTable;
//	ReceivedOrderManagement OrderManagement;
//	public updateOrderEvent(int key,R_receivedOrder orderRegistration,ReceivedOrderManagement orderManagement) {
//		KEY=key;
//		OrderRegistration=orderRegistration;
//		LeftBox = orderRegistration.leftBox;
//		RightBox = orderRegistration.rightBox;
//		TitlePanel = orderRegistration.northPanel;
//		miniTable = orderRegistration.minitable;
//		OrderManagement=orderManagement;
//	}
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		try (Session session = hibernate.factory.openSession()){
//			hibernate.transaction = session.beginTransaction();
//
//			data = session.get(Received_order_history.class, KEY);
//			//update
//			//orderRegisteration companyid --> orderid  changeMethod
//			updateOrder(data, TitlePanel);
//			updateOrder(data, LeftBox);
//			updateOrder(data, RightBox);
//			//save order model
//			updateOrder_product(data, miniTable,session);
//			if(data.getOrdered_company()==null) {
//				JOptionPane.showMessageDialog(null, "주문 업체를 선택하세요.","Null Company",JOptionPane.WARNING_MESSAGE);
//			}else if (data.getProducts().size()==0) {
//				JOptionPane.showMessageDialog(null, "제품을 선택하세요.","Null Product",JOptionPane.WARNING_MESSAGE);
//			}else {
//				session.update(data);
//				OrderManagement.remove(OrderRegistration);
//				new successMessage();
//			}
//			hibernate.transaction.commit();
//		} catch (Exception error) {
//			error.printStackTrace();
//		}
//		OrderManagement.refresh();
//	}
//	void updateOrder(Received_order_history order,Container container) {
//		for(Component component : container.getComponents()) {
//			if(component instanceof JPanel) {
//				for(Component insideComponent : ((JPanel) component).getComponents()) {
//					if(insideComponent instanceof JTextField
//							|| insideComponent instanceof JComboBox) {
//						System.out.println(insideComponent.getName());
//						setEntity(order, insideComponent);
//					}else if(insideComponent instanceof basicTextArea) {
//						for(Component basicTextAreaCom:((basicTextArea) insideComponent).getViewport().getComponents()) {
//							if(basicTextAreaCom instanceof JTextArea) {
//								System.out.println(basicTextAreaCom.getName());
//								setEntity(order, basicTextAreaCom);
//							}
//						}
//					}
//				}
//				if(component instanceof datePickerBar) {
//					System.out.println(component.getName());
//					setEntity(order, ((datePickerBar)component).text);
//				}
//			}else if(component instanceof JLabel) {
//				System.out.println(component.getName());
//				setEntity(order, component);
//			}
//		}
//	}
//	void updateOrder_product(Received_order_history order, miniTable miniTable,Session session) {
////		for(ReceivedOrderProduct p:order.getProducts()) {
////			session.delete(p);
////		}
//		int looNum =0;
//		for(int i=0;i<miniTable.table.getRowCount();i++) {
//			boolean isProductId=true;
//			ReceivedOrderProduct ROproduct;
//			try {
//				int ROProductid=(int) miniTable.table.getValueAt(i,0);
//				ROproduct = session.get(ReceivedOrderProduct.class, ROProductid);
//			} catch (ClassCastException e) {
//				System.out.println("ClassCastException");
//				ROproduct=new ReceivedOrderProduct();
//				ROproduct.setLotNo(new ReceivedOrderDAO().lotGeneraterInTransaction(looNum));
//				looNum++;
//			}
//			ROproduct.setOrder(order);
//			try {ROproduct.setProduct(session.get(Product.class,(int) miniTable.table.getValueAt(i, 3)));
//			} catch (ClassCastException e) {
//				System.out.println("ClassCastException");
//				isProductId=false;
//			}
//			ROproduct.setSize((String) miniTable.table.getValueAt(i, 5));
//			ROproduct.setUnit((String) miniTable.table.getValueAt(i, 6));
//			ROproduct.setQuantity((String) miniTable.table.getValueAt(i, 7));
//			ROproduct.setPrice((String) miniTable.table.getValueAt(i, 8));
//			ROproduct.setRemarks((String) miniTable.table.getValueAt(i, 9));
//			if(isProductId) {
//				session.save(ROproduct);
//				order.addProduct(ROproduct);
//			}
//		}
//	}
//	void setEntity(Received_order_history order, Component Component) {
//		if(Component.getName()==null) {
//		}else {
//			switch (Component.getName()) {
//			case nameFactory.ORDER_TITLE_TN:
//				order.setTitle(((JLabel)Component).getText());
//				break;
//			case nameFactory.ORDER_CATEGORY_TN:
//				order.setCategory(((JLabel)Component).getText());
//				break;
//			case nameFactory.ORDER_REPRESENTATIVE_TN:
//				order.setManager(((JTextField)Component).getText());
//				break;
//			case nameFactory.ORDER_TYPE_TN:
//				order.setType((String) ((JComboBox)Component).getSelectedItem());
//				break;
//			case nameFactory.ORDER_CLIENT_TN:
//				Session session = hibernate.factory.openSession();
//				Company company = session.get(Company.class, OrderRegistration.returnCompanyKey());
//				order.setOrdered_company(company);
//				break;
//			case nameFactory.ORDER_TODAY_TN:
//				order.setOrder_date(((JTextField)Component).getText());
//				break;
//			case nameFactory.ORDER_DEADLINE_TN:
//				order.setDeadline(((JTextField)Component).getText());
//				break;
//			case nameFactory.ORDER_REMARKS_TN:
//				order.setRemarks(((JTextArea)Component).getText());
//				break;
//			case "null":
//				break;
//			default:
//				break;
//			}
//		}
//	}
//}
