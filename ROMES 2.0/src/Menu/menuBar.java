package Menu;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import eventListener.exitEvent;
import eventListener.logOutEvent;
import eventListener.newOrderEvent;
import factory.colorFactory;
import factory.fontFactory;
import mainFrame.mainFrame;

@SuppressWarnings("serial")
public class menuBar extends JMenuBar{
	
	public menuBar() {
		JMenu file = new JMenu("����");
		setFont(file);
		JMenuItem newOrder =new JMenuItem("���� �����");
		file.add(newOrder);
		file.add(new JMenuItem("����"));
		file.add(new JMenuItem("����"));
		file.add(new JMenuItem("�ٸ� �̸����� ����"));
		file.add(new JMenuItem("Send to DB"));
		JMenuItem logout =new JMenuItem("�α׾ƿ�");
		logout.addActionListener(new logOutEvent());
		file.add(logout);
		JMenuItem exit =new JMenuItem("�ý��� ����");
		exit.addActionListener(new exitEvent());
		exit.setMnemonic(KeyEvent.VK_F2);
		file.add(exit);

		JMenu document = new JMenu("����");
		setFont(document);
		document.add(new JMenuItem("���� �����"));
		document.add(new JMenuItem("����"));
		document.add(new JMenuItem("�ٸ� �̸����� ����"));
		document.add(new JMenuItem("Print"));
		JMenu email = new JMenu("E-mail �߼�");
		email.add(new JMenuItem("������"));
		email.add(new JMenuItem("���ּ�"));
		email.add(new JMenuItem("�����"));
		document.add(email);
		
		
		JMenu reprot = new JMenu("����");
		setFont(reprot);
		reprot.add(new JMenuItem("���� �����"));
		reprot.add(new JMenuItem("����"));
		reprot.add(new JMenuItem("�ٸ� �̸����� ����"));
		reprot.add(new JMenuItem("Print"));

		this.add(file);
		this.add(document);
		this.add(reprot);
		this.setBackground(new Color(222, 233, 255));
	}
	void setFont(Component component) {
		component.setFont(new Font(fontFactory.BASIC_FONT, Font.PLAIN, 15));
	}
}
