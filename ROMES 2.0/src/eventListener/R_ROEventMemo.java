//package eventListener;
//
//import java.awt.Component;
//import java.awt.Container;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import javax.swing.JComboBox;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JTextArea;
//import javax.swing.JTextField;
//
//import org.hibernate.Session;
//
//import Dao.ProductDAO;
//import Dao.ReceivedOrderDAO;
//import entity.Received_order_history;
//import entity.ReceivedOrderProduct;
//import entity.Product;
//import entity.Company;
//import factory.nameFactory;
//import functions.datePickerBar;
//import hibernate.hibernate;
//import layoutSetting.basicTextArea;
//import layoutSetting.miniTable;
//import message.errorMessage;
//import message.successMessage;
//import pages.ReceivedOrderManagement;
//import registrationForm.R_receivedOrder;
//
//public class R_ReceivedOrderEvent implements ActionListener{
//	R_receivedOrder OrderRegistration;
//	Container LeftBox;
//	Container RightBox;
//	Container TitlePanel;
//	miniTable miniTable;
//	ReceivedOrderManagement OrderManagement;
//	
//	public R_ReceivedOrderEvent(R_receivedOrder orderRegistration) {
//		OrderRegistration=orderRegistration;
//		LeftBox = orderRegistration.leftBox;
//		RightBox = orderRegistration.rightBox;
//		TitlePanel = orderRegistration.northPanel;
//		miniTable = orderRegistration.minitable;
//	}
//	public R_ReceivedOrderEvent(R_receivedOrder orderRegistration,ReceivedOrderManagement orderManagement) {
//		OrderRegistration=orderRegistration;
//		LeftBox = orderRegistration.leftBox;
//		RightBox = orderRegistration.rightBox;
//		TitlePanel = orderRegistration.northPanel;
//		miniTable = orderRegistration.minitable;
//		OrderManagement=orderManagement;
//	}
//	@Override
//	public void actionPerformed(ActionEvent arg0) {
//		System.out.println("order Event :");
//		try (Session session = hibernate.factory.openSession()){
//			System.out.println("start session");
//			hibernate.transaction = session.beginTransaction();
//			System.out.println("Left components : "+LeftBox.getComponentCount());
//			System.out.println("Right components : "+RightBox.getComponentCount());
//			
//			Received_order_history order = new Received_order_history();
//			//save Order
//			saveOrder(order, TitlePanel);
//			saveOrder(order, LeftBox);
//			saveOrder(order, RightBox);
//			//save order model
//			saveOrder_Product(order, miniTable,session);
//			if(order.getOrdered_company()==null) {
//				JOptionPane.showMessageDialog(null, "주문 업체를 선택하세요.","Null Company",JOptionPane.WARNING_MESSAGE);
//			}else if (order.getProducts().size()==0) {
//				JOptionPane.showMessageDialog(null, "제품을 선택하세요.","Null Product",JOptionPane.WARNING_MESSAGE);
//			}else {
//				session.save(order);
//				OrderManagement.remove(OrderRegistration);
//				new successMessage();
//			}
//			
//			hibernate.transaction.commit();
//			//수주 등록후 테이블 갱신
//			if(OrderManagement!=null) {
//				OrderManagement.refresh();
//			}
//		} catch (Exception e) {
//			new errorMessage("save");
//			e.printStackTrace();
//		}
//		
//	}
//	void saveOrder_Product(Received_order_history order, miniTable miniTable,Session session) {
//		for(int i=0;i<miniTable.table.getRowCount();i++) {
//			boolean isProductId=true;
//			ReceivedOrderProduct product = new ReceivedOrderProduct();
//			product.setOrder(order);
//			try {
//				product.setProduct(session.get(Product.class,(int) miniTable.table.getValueAt(i, 3)));
//			} catch (ClassCastException e) {
//				isProductId=false;
//			}
//			product.setSize((String) miniTable.table.getValueAt(i, 5));
//			product.setUnit((String) miniTable.table.getValueAt(i, 6));
//			product.setQuantity((String) miniTable.table.getValueAt(i, 7));
//			product.setPrice((String) miniTable.table.getValueAt(i, 8));
//			product.setRemarks((String) miniTable.table.getValueAt(i, 9));
//			product.setLotNo(new ReceivedOrderDAO().lotGeneraterInTransaction(i));
//			if(isProductId) {
//				order.addProduct(product);
//				session.save(product);
//			}
//		}
//	}
//	void saveOrder(Received_order_history order,Container container) {
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
