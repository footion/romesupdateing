package eventListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pages.ReceivedOrderManagement;
import registrationForm.R_receivedOrder;

public class newOrderEvent implements ActionListener{
	ReceivedOrderManagement OrderManagement;
	public newOrderEvent(ReceivedOrderManagement orderManagement) {
		OrderManagement=orderManagement;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		R_receivedOrder orderRegistration =new R_receivedOrder(OrderManagement);
		OrderManagement.addCancelableTab("new Order", orderRegistration);
//		
//		for(ActionListener actionListener:orderRegistration.doubleButtonPanel.leftBtn.getActionListeners()) {
//			orderRegistration.doubleButtonPanel.leftBtn.removeActionListener(actionListener);
//		}
//		orderRegistration.doubleButtonPanel.leftBtn.addActionListener(new R_ReceivedOrderEvent(orderRegistration, OrderManagement));
	
	}

}
