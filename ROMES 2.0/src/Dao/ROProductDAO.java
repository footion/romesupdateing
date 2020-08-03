package Dao;

import org.hibernate.Session;

import entity.ReceivedOrderProduct;
import entity.Received_order_history;
import hibernate.hibernate;
import message.errorMessage;

public class ROProductDAO {
	public void saveROProduct(ReceivedOrderProduct ROProduct) {
		try (Session session=hibernate.factory.openSession()){
			hibernate.transaction=session.beginTransaction();
			session.save(ROProduct);
			hibernate.transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			new errorMessage("save");
		}
	}
}
