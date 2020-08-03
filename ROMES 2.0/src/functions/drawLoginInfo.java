package functions;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

import org.hibernate.Session;

import Login.LoginPanel;
import entity.Login;
import eventListener.logOutEvent;
import hibernate.hibernate;
import image.url;
import layoutSetting.basicIconBtn;
import layoutSetting.basicPanel;

@SuppressWarnings("serial")
public class drawLoginInfo extends basicPanel{
	JLabel title;
	String name="±èÇüÁø";
	String position = "¿¬±¸¿ø";
	String loginId="Admin";
	public drawLoginInfo() {
		title = new JLabel("¾È³çÇÏ¼¼¿ä, "+name+" "+position+"´Ô("+loginId+"). ");
		title.setHorizontalAlignment(JLabel.RIGHT);
		
		basicIconBtn btn = new basicIconBtn(url.LOGOUT, 20, 20);
		btn.addActionListener(new logOutEvent());
		btn.setClean(false, false, true);
		btn.setPreferredSize(new Dimension(25, 25));
		btn.setToolTipText("Logout");
		
		this.setLayout(new FlowLayout(FlowLayout.TRAILING));
		this.setBorder(new EmptyBorder(0,0,0,5));
		this.add(title);
		this.add(btn);
		this.setPreferredSize(new Dimension(200,27));
	}
	public void refresh() {
		try (Session session = hibernate.factory.openSession()){
			hibernate.transaction=session.beginTransaction();
			Login login = session.get(Login.class, LoginPanel.ID);
			name= login.getName();
			position=login.getPosition();
			loginId=login.getLoginId();
			hibernate.transaction.commit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "·Î±×ÀÎ Á¤º¸ Ç¥Çö Error", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		title.setText("¾È³çÇÏ¼¼¿ä, "+name+" "+position+"´Ô("+loginId+"). ");
	}
}
