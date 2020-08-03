package evevtListener;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListModel;

import factory.stringFactory;
import pages.company.CompanyRegistration;
import registrationFrame.companyRegistration;
import selectFrame.enumTypePanel;
import selectFrame.selectFrame;

public class selectFrameEvent implements ActionListener,KeyListener{
	JTextField TextField;
	selectFrame SelectFrame;
	String type;
	public selectFrameEvent(selectFrame selectFrame,JTextField textField,String stringFactory_type) {
		TextField=textField;
		SelectFrame=selectFrame;
		type=stringFactory_type;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==SelectFrame.enumTypePanel.button) {
			enumTypeEvent(type);
		}else if(e.getSource()==SelectFrame.okBtn) {
			TextField.setText((String) SelectFrame.dataList.getSelectedValue());
			SelectFrame.dispose();
		}
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(e.getSource()==SelectFrame.enumTypePanel.textField) {
				enumTypeEvent(type);
			}
			if(e.getSource()==SelectFrame.dataList) {
				TextField.setText((String) SelectFrame.dataList.getSelectedValue());
				SelectFrame.dispose();
			}
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	void enumTypeEvent(String type) {
		boolean confirmData=false;
		ListModel model = SelectFrame.dataList.getModel();
		for(int i=0; i< model.getSize();i++) {
			String data = (String) model.getElementAt(i);
			if(data.equals(SelectFrame.enumTypePanel.textField.getText())) {
				confirmData=true;
				TextField.setText(SelectFrame.enumTypePanel.textField.getText());
				SelectFrame.dispose();
				break;
			}
		}
		if(confirmData==false) {
			noneData(type);
		}
	}
	void noneData(String type) {
		int confirm = JOptionPane.showConfirmDialog(null, "안녕하세요 "+type+"占쌉니댐옙. 占쏙옙占쏙옙絿챨黴占쏙옙歐占� ?","占쏙옙溝퓸占� 占쏙옙占쏙옙 占쏙옙占쏙옙",JOptionPane.YES_NO_OPTION);
		if(confirm==0) {
			CompanyRegistration cr = new CompanyRegistration();
			if(type==stringFactory.TYPE_CLIENT) {
				cr.companyName.setText(SelectFrame.enumTypePanel.textField.getText());
			}else if(type ==stringFactory.TYPE_MODEL) {
				
			}else if(type==stringFactory.TYPE_USER) {
				
			}
			SelectFrame.dispose();
		}
	}
}
