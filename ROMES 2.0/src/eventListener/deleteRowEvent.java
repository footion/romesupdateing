package eventListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import entity.Received_order_history;
import entity.Company;
import entity.Company_manager;
import entity.ReceivedOrderProduct;
import entity.PurchasePrice;
import hibernate.hibernate;
import layoutSetting.miniTable;
import message.errorMessage;

public class deleteRowEvent implements ActionListener{
	DefaultTableModel Model;
	JTable Table;
	String Type = null;
	public deleteRowEvent(miniTable miniTable) {
		Model=miniTable.model;
		Table=miniTable.table;
	}
	public deleteRowEvent(miniTable miniTable,String type) {
		Model=miniTable.model;
		Table=miniTable.table;
		Type = type;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		try (Session session = hibernate.factory.openSession()){
			hibernate.transaction=session.beginTransaction();
			
			if(Type==null)
				deleteRow();
			
			else if(Type=="ORDER") {
				int n =Table.getSelectedRow();
				int order_id = (int)Table.getValueAt(n, 0);
				Received_order_history data = session.get(Received_order_history.class, order_id);
				List<ReceivedOrderProduct> models = (List<ReceivedOrderProduct>) data.getProducts();
				int confirm = confirmMSG(data.getTitle());
				if (confirm ==0) {
					session.delete(data);
					for(ReceivedOrderProduct model : models) {
						session.delete(model);
					}
					deleteRow();
				}
			}else if(Type=="COMPANY") {
				int n =Table.getSelectedRow();
				int company_id = (int)Table.getValueAt(n, 0);
				Company company = session.get(Company.class, company_id);
				List<Company_manager> managers = (List<Company_manager>) company.getManagers();
				int confirm = confirmMSG(company.getCompanyName());
				if(confirm==0) {
					for(Company_manager manager:managers) {
						session.delete(manager);
					}
					session.delete(company);
					deleteRow();
				}
			}else if(Type=="PURCHASEPRICE") {
				int n =Table.getSelectedRow();
				int purchasePrice_key = (int)Table.getValueAt(n, 0);
				PurchasePrice purchasePrice = session.get(PurchasePrice.class, purchasePrice_key);
				int confirm = confirmMSG(purchasePrice.getOrdering_company().getCompanyName()+" - "+
						purchasePrice.getPurchaseProduct().getName());
				if(confirm==0) {
					session.delete(purchasePrice);
					deleteRow();
				}
			}else if( Type=="ROPRODUCT") {
				try {
					int n =Table.getSelectedRow();
					int key = (int)Table.getValueAt(n, 0);
					ReceivedOrderProduct product = session.get(ReceivedOrderProduct.class, key);
					int confirm = confirmMSG(product.getProduct().getName());
					if(confirm==0) {
						session.delete(product);
						deleteRow();
					}
				} catch (ClassCastException e) {
					deleteRow();
				}
			}
			hibernate.transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			new errorMessage("delete");
		}
	}
	void deleteRow() {
		int n =Table.getSelectedRow();
		Model.removeRow(n);
		Table.selectAll();
		Table.clearSelection();
	}
	Integer confirmMSG(String string) {
		int confirm=JOptionPane.showConfirmDialog(null, "["+string+"]"+" 항목을 삭제하시겠습니까 ?","삭제",JOptionPane.YES_NO_OPTION);
		return confirm;
	}
}
