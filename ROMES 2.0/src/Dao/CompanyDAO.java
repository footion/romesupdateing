package Dao;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.hibernate.Session;

import entity.Company;
import entity.PurchasePrice;
import hibernate.hibernate;
import message.errorMessage;

public class CompanyDAO {
	public void saveCompany(Company company) {
		try (Session session=hibernate.factory.openSession()){
			hibernate.transaction=session.beginTransaction();
			session.save(company);
			hibernate.transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			new errorMessage("save");
		}
	}
	public ArrayList<Company> getCompanies() {
		try(Session session=hibernate.factory.openSession()){
			ArrayList<Company> list = (ArrayList<Company>) session.createCriteria(Company.class).list();
			return list;
		}
	}
	
	public void updateCompany(Company company) {
		try (Session session=hibernate.factory.openSession()){
			hibernate.transaction=session.beginTransaction();
			session.update(company);
			hibernate.transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			new errorMessage("update");
		}
	}
	public void deleteCompany(Company company) {
		try (Session session=hibernate.factory.openSession()){
			hibernate.transaction=session.beginTransaction();
			session.delete(company);
			hibernate.transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			new errorMessage("delete");
		}
	}
	public Company findByPkey(int PKey) {
		Company company = null;
		try (Session session=hibernate.factory.openSession()){
			hibernate.transaction=session.beginTransaction();
			company = session.get(Company.class, PKey);
			hibernate.transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			new errorMessage("load");
		}
		if(company==null) {
			JOptionPane.showMessageDialog(null, "Failed to find a data","null",JOptionPane.ERROR_MESSAGE);
		}
		return company;
	}
}
