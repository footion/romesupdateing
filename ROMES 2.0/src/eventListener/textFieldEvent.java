package eventListener;

import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.hibernate.Session;

import entity.Company;
import factory.nameFactory;
import factory.stringFactory;
import hibernate.hibernate;
import layoutSetting.basicLabel;
import layoutSetting.basicTabbedPane;
import selectFrame.selectFrame;

public class textFieldEvent implements MouseListener{
	basicTabbedPane TabbedPane;
	Container Container;
	public textFieldEvent(basicTabbedPane tabbedPane,Container container) {
		TabbedPane= tabbedPane;
		Container = container;
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
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
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		JTextField textField = new JTextField();
		textField=(JTextField) e.getSource();
		if(textField.getName()==nameFactory.ORDER_REPRESENTATIVE_TN) {
			selectFrame selectFrame=new selectFrame();
			selectFrame.setProductType();
			//addActionListener
			selectFrame.okBtn.addActionListener(new selectFrameEvent(selectFrame, textField,stringFactory.TYPE_USER,TabbedPane,Container));
			selectFrame.enumTypePanel.button.addActionListener(new selectFrameEvent(selectFrame, textField,stringFactory.TYPE_USER,TabbedPane,Container));
			selectFrame.enumTypePanel.textField.addKeyListener(new selectFrameEvent(selectFrame, textField,stringFactory.TYPE_USER,TabbedPane,Container));
			selectFrame.NameList.addKeyListener(new selectFrameEvent(selectFrame, textField,stringFactory.TYPE_USER,TabbedPane,Container));
			
		}else if(textField.getName()==nameFactory.ORDER_CLIENT_TN
				|| textField.getName()==nameFactory.PURCHASEPRICE_CLIENT_TN) {
			selectFrame selectFrame=new selectFrame();
			selectFrame.setCompanyType();
			//addActionListener
			selectFrame.okBtn.addActionListener(new selectFrameEvent(selectFrame, textField,stringFactory.TYPE_CLIENT,TabbedPane,Container));
			selectFrame.enumTypePanel.button.addActionListener(new selectFrameEvent(selectFrame, textField, stringFactory.TYPE_CLIENT,TabbedPane,Container));
			selectFrame.enumTypePanel.textField.addKeyListener(new selectFrameEvent(selectFrame, textField, stringFactory.TYPE_CLIENT,TabbedPane,Container));
			selectFrame.NameList.addKeyListener(new selectFrameEvent(selectFrame, textField,stringFactory.TYPE_CLIENT,TabbedPane,Container));
		}else if(textField.getName()==nameFactory.PURCHASEPRICE_PRODUCT_TN) {
			selectFrame selectFrame=new selectFrame();
			selectFrame.setProductType();
			//addActionListener
			selectFrame.okBtn.addActionListener(new selectFrameEvent(selectFrame, textField,stringFactory.TYPE_PRODUCT,TabbedPane,Container));
			selectFrame.enumTypePanel.button.addActionListener(new selectFrameEvent(selectFrame, textField, stringFactory.TYPE_PRODUCT,TabbedPane,Container));
			selectFrame.enumTypePanel.textField.addKeyListener(new selectFrameEvent(selectFrame, textField, stringFactory.TYPE_PRODUCT,TabbedPane,Container));
			selectFrame.NameList.addKeyListener(new selectFrameEvent(selectFrame, textField,stringFactory.TYPE_PRODUCT,TabbedPane,Container));
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}	
}