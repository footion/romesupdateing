package eventListener;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListModel;

import org.hibernate.Session;

import Dao.CompanyDAO;
import Dao.ProductDAO;
import entity.Product;
import entity.Company;
import factory.stringFactory;
import hibernate.hibernate;
import layoutSetting.basicTabbedPane;
import layoutSetting.ImageLabel_HJ;
import message.errorMessage;
import registrationForm.R_company;
import registrationForm.R_purchasePrice;
import registrationForm.R_receivedOrder;
import selectFrame.selectFrame;

public class selectFrameEvent implements ActionListener,KeyListener{
	JTextField TextField;
	selectFrame SelectFrame;
	String type;
	basicTabbedPane TabbedPane;
	Container Container;
	public selectFrameEvent(selectFrame selectFrame,JTextField textField
			,String stringFactory_type,basicTabbedPane tabbedPane,Container container) {
		TextField=textField;
		SelectFrame=selectFrame;
		type=stringFactory_type;
		TabbedPane=tabbedPane;
		Container=container;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==SelectFrame.enumTypePanel.button) {
			enumTypeEvent(type);
		}else if(e.getSource()==SelectFrame.okBtn) {
			TextField.setText((String) SelectFrame.NameList.getSelectedValue());
			SelectFrame.saveDatakey(SelectFrame.KeyList[SelectFrame.NameList.getSelectedIndex()]);
			settingKey();
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
			if(e.getSource()==SelectFrame.NameList) {
				TextField.setText((String) SelectFrame.NameList.getSelectedValue());
				SelectFrame.saveDatakey(SelectFrame.KeyList[SelectFrame.NameList.getSelectedIndex()]);
				settingKey();
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
		ListModel model = SelectFrame.NameList.getModel();
		for(int i=0; i< model.getSize();i++) {
			String data = (String) model.getElementAt(i);
			if(data.equals(SelectFrame.enumTypePanel.textField.getText())) {
				confirmData=true;
				TextField.setText(SelectFrame.enumTypePanel.textField.getText());
				SelectFrame.saveDatakey(SelectFrame.KeyList[i]);
				settingKey();
				SelectFrame.dispose();
				break;
			}
		}
		if(confirmData==false) {
			noneData(type);
		}
	}
	void noneData(String type) {
		int confirm = JOptionPane.showConfirmDialog(null, "등록되어 있지 않은 "+type+"입니다. 등록하시겠습니까 ?","등록되어 있지 않음",JOptionPane.YES_NO_OPTION);
		if(confirm==0) {
			R_company cr = new R_company(TabbedPane);
			if(type==stringFactory.TYPE_CLIENT) {
				cr.companyName.setText(SelectFrame.enumTypePanel.textField.getText());
				TabbedPane.addCancelableTab(cr.companyName.getText(), cr);
			}else if(type ==stringFactory.TYPE_PRODUCT) {
				
			}else if(type==stringFactory.TYPE_USER) {
				
			}
			SelectFrame.dispose();
		}
	}
	void settingKey() {
		if(Container instanceof R_receivedOrder) {
			if(type.equals(stringFactory.TYPE_CLIENT)) {
				((R_receivedOrder) Container).setCompanykey(SelectFrame.returnKey());	
			}
		}else if(Container instanceof R_purchasePrice) {
			if(type.equals(stringFactory.TYPE_CLIENT)) {
				((R_purchasePrice)Container).setCompanykey(SelectFrame.returnKey());
				CompanyDAO companyDAO = new CompanyDAO();
				Company company = companyDAO.findByPkey(SelectFrame.returnKey());
				((R_purchasePrice)Container).textField.setText(company.getCompanyClassification());
				((R_purchasePrice)Container).textField_1.setText(company.getCEO_Name());
				((R_purchasePrice)Container).textField_2.setText(company.getBusinessNumber());
				((R_purchasePrice)Container).textField_3.setText(company.getEmail());
				((R_purchasePrice)Container).textField_4.setText(company.getAddress());
				
			}else if(type.equals(stringFactory.TYPE_PRODUCT)) {
				((R_purchasePrice)Container).setProductkey(SelectFrame.returnKey());
				ProductDAO productDAO = new ProductDAO();
				Product product = productDAO.findByPkey(SelectFrame.returnKey());
				((R_purchasePrice)Container).textField.setText(product.getCommonInfo().getProductType());
				((R_purchasePrice)Container).textField_1.setText(product.getCommonInfo().getLocation());
				((R_purchasePrice)Container).imageLabel.setImageFromDB(product.getImage());
				((R_purchasePrice)Container).imageLabel.setImageSize(120, 90);
			}
		}
	}
}
