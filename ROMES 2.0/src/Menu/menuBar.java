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
		JMenu file = new JMenu("파일");
		setFont(file);
		JMenuItem newOrder =new JMenuItem("새로 만들기");
		file.add(newOrder);
		file.add(new JMenuItem("열기"));
		file.add(new JMenuItem("저장"));
		file.add(new JMenuItem("다른 이름으로 저장"));
		file.add(new JMenuItem("Send to DB"));
		JMenuItem logout =new JMenuItem("로그아웃");
		logout.addActionListener(new logOutEvent());
		file.add(logout);
		JMenuItem exit =new JMenuItem("시스템 종료");
		exit.addActionListener(new exitEvent());
		exit.setMnemonic(KeyEvent.VK_F2);
		file.add(exit);

		JMenu document = new JMenu("문서");
		setFont(document);
		document.add(new JMenuItem("새로 만들기"));
		document.add(new JMenuItem("저장"));
		document.add(new JMenuItem("다른 이름으로 저장"));
		document.add(new JMenuItem("Print"));
		JMenu email = new JMenu("E-mail 발송");
		email.add(new JMenuItem("견적서"));
		email.add(new JMenuItem("발주서"));
		email.add(new JMenuItem("운송장"));
		document.add(email);
		
		
		JMenu reprot = new JMenu("보고서");
		setFont(reprot);
		reprot.add(new JMenuItem("새로 만들기"));
		reprot.add(new JMenuItem("저장"));
		reprot.add(new JMenuItem("다른 이름으로 저장"));
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
