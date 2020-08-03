package test;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class adasd {
	public static Configuration conf;
	public static SessionFactory factory;
	public static Transaction transaction = null;
	
	protected void setHibernate() {
		// TODO Auto-generated method stub
		conf = new Configuration();
		conf.configure("hibernate.cfg.xml");
		factory = conf.buildSessionFactory();
		transaction = null;
	}
}
