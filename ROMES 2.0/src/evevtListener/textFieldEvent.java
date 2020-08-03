package evevtListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.hibernate.Session;

import entity.Company;
import factory.stringFactory;
import hibernate.HibernateServer;
import layoutSetting.basicLabel;
import selectFrame.selectFrame;

public class textFieldEvent implements MouseListener {
	int count =0;
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println(count++);
		JTextField textField = (JTextField) e.getSource();
		if (textField.getName() == stringFactory.ORDER_REPRESENTATIVE_TN) {
			String dataTest[] = new String[30];
			for (int i = 0; i < 30; i++) {
				dataTest[i] = "user" + Integer.toString(i + 1);
			}
			String title = "Input Representative";
			String type = "user";
			String[] listData = dataTest;
			selectFrame selectFrame = new selectFrame(title, type, listData);
			// addActionListener
			selectFrame.okBtn.addActionListener(new selectFrameEvent(selectFrame, textField, stringFactory.TYPE_USER));
			selectFrame.enumTypePanel.button
					.addActionListener(new selectFrameEvent(selectFrame, textField, stringFactory.TYPE_USER));
			selectFrame.enumTypePanel.textField
					.addKeyListener(new selectFrameEvent(selectFrame, textField, stringFactory.TYPE_USER));
			selectFrame.dataList.addKeyListener(new selectFrameEvent(selectFrame, textField, stringFactory.TYPE_USER));

		} else if (textField.getName() == stringFactory.ORDER_CLIENT_TN) {
			Session session = HibernateServer.getFactoryInstance().openSession();
			ArrayList<Company> companies = (ArrayList<Company>) session.createCriteria(Company.class).list();
			String dataTest[] = new String[companies.size()];
			for (int i = 0; i < companies.size(); i++) {
				Company company = companies.get(i);
				dataTest[i] = company.getCompanyName();
			}
			String title = "Input Company";
			String type = "Company";
			String[] listData = dataTest;
			selectFrame selectFrame = new selectFrame(title, type, listData);
			// addActionListener
			selectFrame.okBtn
					.addActionListener(new selectFrameEvent(selectFrame, textField, stringFactory.TYPE_CLIENT));
			selectFrame.enumTypePanel.button
					.addActionListener(new selectFrameEvent(selectFrame, textField, stringFactory.TYPE_CLIENT));
			selectFrame.enumTypePanel.textField
					.addKeyListener(new selectFrameEvent(selectFrame, textField, stringFactory.TYPE_CLIENT));
			selectFrame.dataList
					.addKeyListener(new selectFrameEvent(selectFrame, textField, stringFactory.TYPE_CLIENT));
		}
		else if (textField.getName() =="inputLot") {
			
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
