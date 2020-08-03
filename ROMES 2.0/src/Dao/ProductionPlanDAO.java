package Dao;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.hibernate.Session;

import entity.Product;
import entity.ProductionPlan;
import hibernate.hibernate;
import message.errorMessage;

public class ProductionPlanDAO {
	public void saveProductionPlan(ProductionPlan ProductionPlan) {
		try (Session session=hibernate.factory.openSession()){
			hibernate.transaction=session.beginTransaction();
			session.save(ProductionPlan);
			hibernate.transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			new errorMessage("save");
		}
	}
	public ArrayList<ProductionPlan> getClearUpProductionPlans(){
		ArrayList<ProductionPlan> ProductionPlans = getProductionPlans();
		ArrayList<ProductionPlan> list= new ArrayList<>();
		for(ProductionPlan ProductionPlan:ProductionPlans) {
			if(!list.contains(ProductionPlan)) {
				list.add(ProductionPlan);
			}
		}
		return list;	
	}
	
	public ArrayList<ProductionPlan> getProductionPlans() {
		try(Session session=hibernate.factory.openSession()){
			ArrayList<ProductionPlan> list = (ArrayList<ProductionPlan>) session.createCriteria(ProductionPlan.class).list();
			return list;
		}
	}
	
	public void updateProductionPlan(ProductionPlan ProductionPlan) {
		try (Session session=hibernate.factory.openSession()){
			hibernate.transaction=session.beginTransaction();
			session.update(ProductionPlan);
			hibernate.transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			new errorMessage("update");
		}
	}
	public void deleteProductionPlan(ProductionPlan ProductionPlan) {
		try (Session session=hibernate.factory.openSession()){
			hibernate.transaction=session.beginTransaction();
			session.delete(ProductionPlan);
			hibernate.transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			new errorMessage("delete");
		}
	}
	public ProductionPlan findByPkey(int PKey) {
		ProductionPlan ProductionPlan = null;
		try (Session session=hibernate.factory.openSession()){
			hibernate.transaction=session.beginTransaction();
			ProductionPlan = session.get(ProductionPlan.class, PKey);
			hibernate.transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			new errorMessage("load");
		}
		if(ProductionPlan==null) {
			JOptionPane.showMessageDialog(null, "Failed to find a data","null",JOptionPane.ERROR_MESSAGE);
		}
		return ProductionPlan;
	}
}
