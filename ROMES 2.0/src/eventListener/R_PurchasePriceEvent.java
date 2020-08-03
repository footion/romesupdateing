package eventListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import Dao.CompanyDAO;
import Dao.ProductDAO;
import Dao.PurchaseDAO;
import entity.PurchasePrice;
import factory.nameFactory;
import pages.purchasePriceManagement;
import registrationForm.R_purchasePrice;

public class R_PurchasePriceEvent implements ActionListener{
	R_purchasePrice purchaseframe;
	purchasePriceManagement PriceManagement;
	int PRICECOLUMN=4;
	int TABLEKEYCOLUMN=3;
	public R_PurchasePriceEvent(purchasePriceManagement priceManagement,R_purchasePrice R_purchaseFrame) {
		purchaseframe=R_purchaseFrame;
		PriceManagement=priceManagement;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		PurchaseDAO purchasedao = new PurchaseDAO();
		ProductDAO productDAO = new ProductDAO();
		CompanyDAO companydao= new CompanyDAO();
		
		if(((JButton)e.getSource()).getName().equals(nameFactory.PURCHASEPRICE_BYCOMPANY_BN)) {
			if(purchaseframe.returnCompanyKey()==0) {
				JOptionPane.showMessageDialog(null, "거래처를 등록해주세요.","Warning",JOptionPane.WARNING_MESSAGE);
			}else if(purchaseframe.miniTable.model.getRowCount()==0) {
				JOptionPane.showMessageDialog(null, "제품을 추가해주세요.","Warning",JOptionPane.WARNING_MESSAGE);
			}else {
				for(int i=0;i<purchaseframe.miniTable.model.getRowCount();i++) {
					PurchasePrice purchasePrice = new PurchasePrice();
					purchasePrice.setOrdering_company(companydao.findByPkey(purchaseframe.returnCompanyKey()));
					System.out.println(purchaseframe.returnCompanyKey());
					purchasePrice.setPrice((String) purchaseframe.miniTable.table.getValueAt(i, PRICECOLUMN));
					purchasePrice.setPurchaseProduct(productDAO.findByPkey((int) purchaseframe.miniTable.table.getValueAt(i, TABLEKEYCOLUMN)));
					purchasedao.savePurchase(purchasePrice);
				}
			}
			PriceManagement.refresh();
			PriceManagement.remove(purchaseframe);
		}else if(((JButton)e.getSource()).getName().equals(nameFactory.PURCHASEPRICE_BYPRODUCT_BN)) {
			if(purchaseframe.returnProductKey()==0) {
				JOptionPane.showMessageDialog(null, "제품을 등록해주세요.","Warning",JOptionPane.WARNING_MESSAGE);
			}else if(purchaseframe.miniTable.model.getRowCount()==0) {
				JOptionPane.showMessageDialog(null, "거래처를 추가해주세요.","Warning",JOptionPane.WARNING_MESSAGE);
			}else {
				for(int i=0;i<purchaseframe.miniTable.model.getRowCount();i++) {
					PurchasePrice purchasePrice = new PurchasePrice();
					purchasePrice.setPurchaseProduct(productDAO.findByPkey(purchaseframe.returnProductKey()));
					purchasePrice.setOrdering_company(companydao.findByPkey((int) purchaseframe.miniTable.table.getValueAt(i, TABLEKEYCOLUMN)));
					purchasePrice.setPrice((String) purchaseframe.miniTable.table.getValueAt(i, PRICECOLUMN));
					purchasedao.savePurchase(purchasePrice);
				}
			}
			PriceManagement.refresh();
			PriceManagement.remove(purchaseframe);
		}
	}

}
