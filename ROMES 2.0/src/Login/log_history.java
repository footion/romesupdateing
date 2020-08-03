package Login;

import java.awt.BorderLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Session;
import Login.LoginPanel;
import entity.Loghistory;
import hibernate.hibernate;
import layoutSetting.basicPanel;

public class log_history extends basicPanel{
	static JTable table;
	public static DefaultTableModel model;
	public static String log_executefunction = null;
	public log_history() {
		// TODO Auto-generated constructor stub
		String [] col = {"ID","날짜","시간","이름","활동 내역"};
		table = new JTable();
		model = new DefaultTableModel(col,0);
		table.setModel(model);
		JScrollPane pane = new JScrollPane(table);
		
		this.setLayout(new BorderLayout());
		this.add(pane,BorderLayout.CENTER);
		Auto_delete();
		StartData();
	}
	public static void refresh() {
		model.setRowCount(0);
		StartData();
	}
	public static void StartData() {
		// TODO Auto-generated method stub
		try (Session session = hibernate.factory.openSession()) {
			hibernate.transaction = session.beginTransaction();
			model = (DefaultTableModel) table.getModel();
			ArrayList<Loghistory> log = (ArrayList<Loghistory>) session
					.createCriteria(Loghistory.class).list();
			Collections.sort(log, new DateDown_TimeUp_Compare());
			for (Loghistory data : log) {
				model.addRow(new Object[] {data.getId(),data.getDate(),TimeFomater(data.getTime()),data.getName(),data.getExecutefunction()});
			}
			hibernate.transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	static String TimeFomater(String string) {
		if(string!=null&&string.contains(":")) {
			SimpleDateFormat origin = new SimpleDateFormat("HH:mm:ss");
			SimpleDateFormat newformat = new SimpleDateFormat("a hh:mm:ss");
			String timeformat="";
			try {
				Date date=origin.parse(string);
				timeformat = newformat.format(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return timeformat;
		}else {
			return string;
		}
	}
	public static void save_log(String name) {
		try (Session session = hibernate.factory.openSession()) {
			hibernate.transaction = session.beginTransaction();
			Loghistory log_history = new Loghistory();
			log_history.setDate();
			log_history.setTime();
			log_history.setName(LoginPanel.name);
			log_history.setExecutefunction(name);
			session.save(log_history);
			hibernate.transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	static class DateDown_TimeUp_Compare implements Comparator<Loghistory> {

		@Override
		public int compare(Loghistory arg0, Loghistory arg1) {
			// TODO Auto-generated method stub
			int a;
			if ((arg1.getDate() == null)){
				a = -1;
			}else if((arg0.getDate() == null)) {
				a=1;
			}
			else {
				a = arg1.getDate().compareTo(arg0.getDate());
				if(a==0) {
					if ((arg1.getTime() == null)) {
						a = 1;
					}else if ((arg0.getTime() == null)) {
						a=-1;
					}
					else {
						a = arg1.getTime().compareTo(arg0.getTime());
					}
				}
			}
			return a;
		}
	}
	void Auto_delete() {
		try (Session session = hibernate.factory.openSession()) {
			hibernate.transaction = session.beginTransaction();
			ArrayList<Loghistory> log = (ArrayList<Loghistory>) session
					.createCriteria(Loghistory.class).list();
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy");
			String day=format.format(date);
			for (Loghistory data : log) {
				if(!day.equals(data.getDate().split("-")[0])) {
					System.out.println(data.getDate().split("-")[1]);
					session.delete(data);
				}
			}
			hibernate.transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
