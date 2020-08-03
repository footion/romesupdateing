package Dao;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.hibernate.Session;

import entity.Product;
import hibernate.hibernate;
import message.errorMessage;

public class ProductDAO {
	public void saveProduct(Product Product) {
		try (Session session=hibernate.factory.openSession()){
			hibernate.transaction=session.beginTransaction();
			session.save(Product);
			hibernate.transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			new errorMessage("save");
		}
	}
	public ArrayList<Product> getClearUpProducts(){
		ArrayList<Product> products = getProducts();
		ArrayList<Product> list= new ArrayList<>();
		for(Product product:products) {
			if(!list.contains(product)) {
				list.add(product);
			}
		}
		return list;	
	}
	
	public ArrayList<Product> getProducts() {
		try(Session session=hibernate.factory.openSession()){
			ArrayList<Product> list = (ArrayList<Product>) session.createCriteria(Product.class).list();
			return list;
		}
	}
	
	public void updateProduct(Product Product) {
		try (Session session=hibernate.factory.openSession()){
			hibernate.transaction=session.beginTransaction();
			session.update(Product);
			hibernate.transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			new errorMessage("update");
		}
	}
	public void deleteProduct(Product Product) {
		try (Session session=hibernate.factory.openSession()){
			hibernate.transaction=session.beginTransaction();
			session.delete(Product);
			hibernate.transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			new errorMessage("delete");
		}
	}
	public Product findByPkey(int PKey) {
		Product Product = null;
		try (Session session=hibernate.factory.openSession()){
			hibernate.transaction=session.beginTransaction();
			Product = session.get(Product.class, PKey);
			hibernate.transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			new errorMessage("load");
		}
		if(Product==null) {
			JOptionPane.showMessageDialog(null, "Failed to find a data","null",JOptionPane.ERROR_MESSAGE);
		}
		return Product;
	}
}
