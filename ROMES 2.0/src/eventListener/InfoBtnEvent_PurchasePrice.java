package eventListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import Dao.PurchaseDAO;
import entity.Company;
import entity.Product;
import entity.PurchasePrice;
import layoutSetting.basicTabbedPane;
import layoutSetting.miniTable;
import registrationForm.R_purchasePrice;

public class InfoBtnEvent_PurchasePrice implements ActionListener{
	basicTabbedPane TabbedPane;
	miniTable MiniTable;
	int KEYCOLUMN;
	boolean IsByCompany;
	public InfoBtnEvent_PurchasePrice(basicTabbedPane tabbedPane,miniTable miniTable,int keyColumn,boolean isByCompany) {
		TabbedPane=tabbedPane;
		MiniTable=miniTable;
		KEYCOLUMN=keyColumn;
		IsByCompany=isByCompany;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(IsByCompany==true) {
			drawInfo("bycompany");
		}else if(IsByCompany==false) {
			drawInfo("byproduct");
		}
	}
	private void drawInfo(String category) {
		if(category.equals("bycompany")) {
			int KEY=(int) MiniTable.table.getValueAt(MiniTable.table.getSelectedRow(), KEYCOLUMN);
			PurchasePrice purchasePrice = new PurchaseDAO().findByPkey(KEY);
			Company company = purchasePrice.getOrdering_company();
			R_purchasePrice infoPanel = new R_purchasePrice(TabbedPane);
			infoPanel.setCompanyType();
			setInfoTab(infoPanel);
			TabbedPane.addCancelableTab("거래처 : "+company.getCompanyName(), infoPanel);
			infoPanel.setCompanykey(company.getId());
			infoPanel.name.setText(company.getCompanyName());
			infoPanel.textField.setText(company.getCompanyClassification());
			infoPanel.textField_1.setText(company.getCEO_Name());
			infoPanel.textField_2.setText(company.getBusinessNumber());
			infoPanel.textField_3.setText(company.getEmail());
			infoPanel.textField_4.setText(company.getAddress());
			
			ArrayList<PurchasePrice> purchasePrices = new PurchaseDAO().findByValue("ordering_company", purchasePrice.getOrdering_company());
			for(PurchasePrice tableData : purchasePrices) {
				Product p =tableData.getPurchaseProduct();
				infoPanel.miniTable.model.addRow(new Object[] {tableData.getId(),p.getName(),"",p.getId(),tableData.getPrice(),""});
			}
		}else if(category.equals("byproduct")) {
			int KEY=(int) MiniTable.table.getValueAt(MiniTable.table.getSelectedRow(), KEYCOLUMN);
			PurchasePrice purchasePrice = new PurchaseDAO().findByPkey(KEY);
			Product product = purchasePrice.getPurchaseProduct();
			R_purchasePrice infoPanel = new R_purchasePrice(TabbedPane);
			infoPanel.setProductType();
			setInfoTab(infoPanel);
			TabbedPane.addCancelableTab("매입품 : "+product.getName(), infoPanel);
			infoPanel.setProductkey(product.getId());
			infoPanel.name.setText(product.getName());
			infoPanel.textField.setText(product.getCommonInfo().getProductType());
			infoPanel.textField_1.setText(product.getCommonInfo().getLocation());
			infoPanel.imageLabel.setImageFromDB(product.getImage());
			
			ArrayList<PurchasePrice> purchasePrices = new PurchaseDAO().findByValue("purchaseProduct", purchasePrice.getPurchaseProduct());
			for(PurchasePrice tableData : purchasePrices) {
				Company c =tableData.getOrdering_company();
				infoPanel.miniTable.model.addRow(new Object[] {tableData.getId(),c.getCompanyName(),"",c.getId(),tableData.getPrice(),""});
				}
		}
	}
	void setInfoTab(R_purchasePrice infoPanel) {
		infoPanel.removeEvents();
		infoPanel.buttonPanel.leftBtn.setText("수정");
		infoPanel.companyRadio.setVisible(false);
		infoPanel.productRadio.setVisible(false);
		infoPanel.miniTable.table.setEnabled(false);
		infoPanel.addRowLabel.setVisible(false);
	}

}
