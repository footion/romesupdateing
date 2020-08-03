package Main;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

import org.hibernate.Session;

import Login.log_history;
import eventListener.newOrderEvent;
import factory.componentFactory;
import factory.instanceofFactory;
import hibernate.hibernate;
import layoutSetting.scrollFrame;
import mainFrame.mainFrame;
import pages.ReceivedOrderManagement;

@SuppressWarnings("serial")
public class main extends scrollFrame{
	ReceivedOrderManagement orderManagement =new ReceivedOrderManagement();
	public main() {
		super("Test");
		Session session = hibernate.factory.openSession();
		
		
		JMenuBar menuBar = new JMenuBar();
		JMenu a = new JMenu("File");
		JMenu b = new JMenu("Refactor");
		menuBar.add(a);
		menuBar.add(b);
		JMenuItem newOrder =new JMenuItem("New");
		newOrder.addActionListener(new newOrderEvent(orderManagement));
		a.add(newOrder);
		a.add(new JMenuItem("Open"));
		a.add(new JMenuItem("Close"));
		
		b.add(new JMenuItem("Rename"));
		
		JMenu c = new JMenu("E-mail");
		c.add(new JMenuItem("등록"));
		JMenu item = new JMenu("전송");
		item.add(new JMenuItem("견적서"));
		item.add(new JMenuItem("발주서"));
		item.add(new JMenuItem("운송장"));
		c.add(item);
		menuBar.add(c);
		
		this.setJMenuBar(menuBar);
		
		this.container.setLayout(new BorderLayout(0,20));
		this.container.add(new componentFactory().addEmptyLabel(),BorderLayout.NORTH);
		this.container.add(orderManagement,BorderLayout.CENTER);
		this.setVisible(true);
		this.setSize(1300,750);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                log_history.save_log("시스템 종료");
            }
        });
		//new main();
		new mainFrame("ROMES");
	}
}
