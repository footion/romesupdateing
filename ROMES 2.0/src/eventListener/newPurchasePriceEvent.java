package eventListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pages.purchasePriceManagement;
import registrationForm.R_purchasePrice;

public class newPurchasePriceEvent implements ActionListener{
	purchasePriceManagement PurchasePriceManagement;
	public newPurchasePriceEvent(purchasePriceManagement purchasePriceManagement) {
		PurchasePriceManagement = purchasePriceManagement;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		R_purchasePrice purchasePrice = new R_purchasePrice(PurchasePriceManagement);
		PurchasePriceManagement.addCancelableTab("매입가 등록", purchasePrice);
		purchasePrice.buttonPanel.leftBtn.addActionListener(new R_PurchasePriceEvent(PurchasePriceManagement, purchasePrice));
	}

}
