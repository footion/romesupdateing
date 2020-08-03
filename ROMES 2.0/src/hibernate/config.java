package hibernate;

import org.hibernate.cfg.Configuration;

public class config extends Configuration{
//	public static String username = "postgres";
//	public static String password = "1234";
//	public static String Database = "ROMES 2.0";
//	public static String port = "5432";
//	public static String url ="127.0.0.1";
	public static String username = "giwonjung";
	public static String password = "1";
	public static String Database = "cjproject";
	public static String port = "5432";
	public static String url ="192.168.0.22";
	public config() {
		this.configure("hibernate.cfg.xml");
		this.setProperty("hibernate.connection.username", username);
		this.setProperty("hibernate.connection.password", password);
		this.setProperty("hibernate.connection.url", "jdbc:postgresql://"+url+":"+port+"/"+Database);
		this.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
	}
}
