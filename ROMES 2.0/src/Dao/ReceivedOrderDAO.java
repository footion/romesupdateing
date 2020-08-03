package Dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import org.hibernate.Session;

import entity.Product;
import entity.ReceivedOrderProduct;
import entity.Received_order_history;
import hibernate.hibernate;
import message.errorMessage;

public class ReceivedOrderDAO {
	public void saveReceivedOrder(Received_order_history ReceivedOrder) {
		try (Session session=hibernate.factory.openSession()){
			hibernate.transaction=session.beginTransaction();
			session.save(ReceivedOrder);
			hibernate.transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			new errorMessage("save");
		}
	}
	public ArrayList<Received_order_history> getReceivedOrders() {
		try(Session session=hibernate.factory.openSession()){
			ArrayList<Received_order_history> list = (ArrayList<Received_order_history>) session.createCriteria(Received_order_history.class).list();
			return list;
		}
	}
	public ArrayList<ReceivedOrderProduct> getReceivedOrderProducts() {
		try(Session session=hibernate.factory.openSession()){
			ArrayList<ReceivedOrderProduct> list = (ArrayList<ReceivedOrderProduct>) session.createCriteria(ReceivedOrderProduct.class).list();
			ArrayList<ReceivedOrderProduct> clearUpList = new ArrayList<>();
			for(ReceivedOrderProduct product : list) {
				if(!clearUpList.contains(product)) {
					clearUpList.add(product);
				}
			}
			return clearUpList;
		}
	}
	
	public void updateReceivedOrder(Received_order_history ReceivedOrder) {
		try (Session session=hibernate.factory.openSession()){
			hibernate.transaction=session.beginTransaction();
			session.update(ReceivedOrder);
			hibernate.transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			new errorMessage("update");
		}
	}
	public void deleteReceivedOrder(Received_order_history ReceivedOrder) {
		try (Session session=hibernate.factory.openSession()){
			hibernate.transaction=session.beginTransaction();
			session.delete(ReceivedOrder);
			hibernate.transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			new errorMessage("delete");
		}
	}
	public Received_order_history findByPkey(int PKey) {
		Received_order_history ReceivedOrder = null;
		try (Session session=hibernate.factory.openSession()){
			hibernate.transaction=session.beginTransaction();
			ReceivedOrder = session.get(Received_order_history.class, PKey);
			hibernate.transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			new errorMessage("load");
		}
		if(ReceivedOrder==null) {
			JOptionPane.showMessageDialog(null, "Failed to find a data","null",JOptionPane.ERROR_MESSAGE);
		}
		return ReceivedOrder;
	}
	//제품별 Lot생성
	public String lotGenerater() {
		String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String count;
		String code="47";
		ArrayList<ReceivedOrderProduct> orderProducts =getReceivedOrderProducts();
		int countOrder=0;
		for(ReceivedOrderProduct product :orderProducts) {
			if(product.getLotNo()!=null&&product.getLotNo().substring(0,8).equals(date)) {
				System.out.println(countOrder);
				countOrder++;
			}
		}
		count = String.format("%04d", countOrder+1);
		return date+count+code;
	}
	public String lotGeneraterInTransaction(int loopNum) {
		String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String count;
		String code="47";
		ArrayList<ReceivedOrderProduct> orderProducts =getReceivedOrderProducts();
		int countOrder=0;
		for(ReceivedOrderProduct product :orderProducts) {
			if(product.getLotNo()!=null&&product.getLotNo().substring(0,8).equals(date)) {
				countOrder++;
				System.out.println(product.getLotNo());
				System.out.println(countOrder);
			}
		}
		count = String.format("%04d", countOrder+1+loopNum);
		return date+count+code;
	}
	public void setPlanStep(Received_order_history order) {
		try (Session session = hibernate.factory.openSession()){
			hibernate.transaction=session.beginTransaction();
			order.setProductPlan(true);
			session.update(order);
			hibernate.transaction.commit();
		} catch (Exception e) {
			new errorMessage("setPlanStep");
		}
	}
	public void setOrderStep(Received_order_history order) {
		try (Session session = hibernate.factory.openSession()){
			hibernate.transaction=session.beginTransaction();
			order.setProductOrder(true);
			session.update(order);
			hibernate.transaction.commit();
		} catch (Exception e) {
			new errorMessage("setOrderStep");
		}
	}
	public void setReleaseStep(Received_order_history order) {
		try (Session session = hibernate.factory.openSession()){
			hibernate.transaction=session.beginTransaction();
			order.setProductRelease(true);
			session.update(order);
			hibernate.transaction.commit();
		} catch (Exception e) {
			new errorMessage("setReleaseStep");
		}
	}
	public void setShipmentStep(Received_order_history order) {
		try (Session session = hibernate.factory.openSession()){
			hibernate.transaction=session.beginTransaction();
			order.setProductShipment(true);
			session.update(order);
			hibernate.transaction.commit();
		} catch (Exception e) {
			new errorMessage("setShipmentStep");
		}
	}
}
