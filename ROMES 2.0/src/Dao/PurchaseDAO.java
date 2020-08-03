package Dao;

import java.awt.List;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import entity.PurchasePrice;
import hibernate.hibernate;
import message.errorMessage;

public class PurchaseDAO {
	
	public void savePurchase(PurchasePrice purchase) {
		try (Session session=hibernate.factory.openSession()){
			hibernate.transaction=session.beginTransaction();
			session.save(purchase);
			hibernate.transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			new errorMessage("save");
		}
	}
	public ArrayList<PurchasePrice> findByValue(String propertyName,Object value){
		ArrayList<PurchasePrice> purchasePrices=null;
		try (Session session=hibernate.factory.openSession()){
			hibernate.transaction=session.beginTransaction();
			purchasePrices = (ArrayList<PurchasePrice>)session.createCriteria(PurchasePrice.class)
					.add(Restrictions.eq(propertyName, value)).list();
			hibernate.transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			new errorMessage("load");
		}
		return purchasePrices;
	}
	public ArrayList<PurchasePrice> getPurchaseArray() {
		ArrayList<PurchasePrice> list = null;
		try (Session session=hibernate.factory.openSession()){
			list = (ArrayList<PurchasePrice>) session.createCriteria(PurchasePrice.class).list();
		} catch (Exception e) {
			e.printStackTrace();
			new errorMessage("read");
		}
		return list;
	}
	
	public void updatePurchase(PurchasePrice purchase) {
		try (Session session=hibernate.factory.openSession()){
			hibernate.transaction=session.beginTransaction();
			session.update(purchase);
			hibernate.transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			new errorMessage("update");
		}
	}
	public void deletePurchase(PurchasePrice purchase) {
		try (Session session=hibernate.factory.openSession()){
			hibernate.transaction=session.beginTransaction();
			session.delete(purchase);
			hibernate.transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			new errorMessage("delete");
		}
	}
	public PurchasePrice findByPkey(int PKey) {
		PurchasePrice purchase = null;
		try (Session session=hibernate.factory.openSession()){
			hibernate.transaction=session.beginTransaction();
			purchase = session.get(PurchasePrice.class, PKey);
			hibernate.transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			new errorMessage("load");
		}
		if(purchase==null) {
			JOptionPane.showMessageDialog(null, "Failed to find a data","null",JOptionPane.ERROR_MESSAGE);
		}
		return purchase;
	}
}
