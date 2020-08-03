//package eventListener;
//
//import java.awt.Component;
//import java.awt.Container;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;
//
//import javax.swing.JButton;
//import javax.swing.JComboBox;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JTable;
//import javax.swing.JTextArea;
//import javax.swing.JTextField;
//
//import org.hibernate.Session;
//import org.hibernate.criterion.Restrictions;
//
//import entity.Received_order_history;
//import entity.ReceivedOrderProduct;
//import entity.Company;
//import factory.nameFactory;
//import factory.stringFactory;
//import functions.datePickerBar;
//import hibernate.hibernate;
//import layoutSetting.basicTextArea;
//import pages.ReceivedOrderManagement;
//import registrationForm.R_receivedOrder;
//
//public class InfoBtnEvent_ReceivedOrder implements ActionListener{
//	R_receivedOrder OrderRegistration;
//	ReceivedOrderManagement OrderManagement;
//	String order_id;
//	public InfoBtnEvent_ReceivedOrder(ReceivedOrderManagement orderManagement) {
//		OrderManagement =orderManagement;
//	}
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		System.out.println("click infoBtn");
//		paintOrderInfo();
//	}
//	void paintOrderInfo() {
//		try (Session session=hibernate.factory.openSession()){
//			hibernate.transaction=session.beginTransaction();
//			
//			JTable table = OrderManagement.miniTable.table;
//			order_id = Integer.toString((int) table.getValueAt(table.getSelectedRow(), 0));
//			Received_order_history data = session.get(Received_order_history.class, Integer.parseInt(order_id));
//
//			OrderRegistration = new R_receivedOrder(OrderManagement);
//			OrderRegistration.setCompanykey(data.getOrdered_company().getId());
//			//importData
//			importOrderInfo(data, OrderRegistration.rightBox);
//			importOrderInfo(data, OrderRegistration.leftBox);
//			importOrderInfo(data, OrderRegistration.northPanel);
//			//addTabbedPane
//			OrderManagement.addCancelableTab(OrderRegistration.orderTitle.getText(), OrderRegistration);
//			
//			for(ReceivedOrderProduct product : data.getProducts()) {
//				OrderRegistration.minitable.model.addRow(new Object[] {product.getId(),product.getProduct().getName()
//						,"",product.getProduct().getId(),product.getLotNo(),product.getSize(),product.getUnit(),product.getQuantity(),product.getPrice()
//						,product.getRemarks(),""});
//			}
//			//changeBtn
//			setUpdateForm(OrderRegistration.doubleButtonPanel.leftBtn);
//			//refreshTotalPrice
//			OrderRegistration.totalPrice.refreshPrice();
//			
//			hibernate.transaction.commit();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	void importOrderInfo(Received_order_history order,Container container) {
//		for(Component component : container.getComponents()) {
//			if(component instanceof JPanel) {
//				for(Component insideComponent : ((JPanel) component).getComponents()) {
//					if(insideComponent instanceof JTextField
//							|| insideComponent instanceof JComboBox) {
//						System.out.println(insideComponent.getName());
//						importData(order, insideComponent);
//					}else if(insideComponent instanceof basicTextArea) {
//						for(Component basicTextAreaCom:((basicTextArea) insideComponent).getViewport().getComponents()) {
//							if(basicTextAreaCom instanceof JTextArea) {
//								System.out.println(basicTextAreaCom.getName());
//								importData(order, basicTextAreaCom);
//							}
//						}
//					}
//				}
//				if(component instanceof datePickerBar) {
//					System.out.println(component.getName());
//					importData(order, ((datePickerBar)component).text);
//				}
//			}else if(component instanceof JLabel) {
//				System.out.println(component.getName());
//				importData(order, component);
//			}
//		}
//	}
//	void setUpdateForm(JButton button) {
//		button.setText("Update");
//		for(ActionListener actionListener:button.getActionListeners()) {
//			button.removeActionListener(actionListener);
//		}
//		button.addActionListener(new updateOrderEvent(Integer.parseInt(order_id),OrderRegistration,OrderManagement));
//	}
//	void importData(Received_order_history order, Component Component) {
//		if(Component.getName()==null) {
//		}else {
//			switch (Component.getName()) {
//			case nameFactory.ORDER_TITLE_TN:
//				((JLabel)Component).setText(order.getTitle());
//				break;
//			case nameFactory.ORDER_CATEGORY_TN:
//				((JLabel)Component).setText(order.getCategory());
//				break;
//			case nameFactory.ORDER_REPRESENTATIVE_TN:
//				((JTextField)Component).setText(order.getManager());
//				break;
//			case nameFactory.ORDER_TYPE_TN:
//				((JComboBox)Component).setSelectedItem(order.getType());
//				break;
//			case nameFactory.ORDER_CLIENT_TN:
//				((JTextField)Component).setText(order.getOrdered_company().getCompanyName());
//				break;
//			case nameFactory.ORDER_TODAY_TN:
//				((JTextField)Component).setText(order.getOrder_date());
//				break;
//			case nameFactory.ORDER_DEADLINE_TN:
//				((JTextField)Component).setText(order.getDeadline());
//				break;
//			case nameFactory.ORDER_REMARKS_TN:
//				((JTextArea)Component).setText(order.getRemarks());
//				break;
//			case "null":
//				break;
//			default:
//				break;
//			}
//		}
//	}
//}
