package eventListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.ListModel;

import factory.stringFactory;
import layoutSetting.miniTable;
import selectFrame.selectFrame;

public class miniTableEvent_R_PurchasePrice implements MouseListener{
	miniTable miniTable;
	int EVENTCOLUMN=1;
	int PRODUCT_ID_COL =3;
	selectFrame SelectFrame;
	boolean ISBYCOMPANY;
	public miniTableEvent_R_PurchasePrice(miniTable minitable, boolean isByCompany) {
		miniTable=minitable;
		ISBYCOMPANY=isByCompany;
	}
	void Event_List() {
		miniTable.table.setValueAt(SelectFrame.NameList.getSelectedValue(),miniTable.table.getSelectedRow(), EVENTCOLUMN);
		SelectFrame.saveDatakey(SelectFrame.KeyList[SelectFrame.NameList.getSelectedIndex()]);
		miniTable.table.setValueAt(SelectFrame.returnKey(),miniTable.table.getSelectedRow(), PRODUCT_ID_COL);
		SelectFrame.dispose();
	}
	void Event_Enum() {
		miniTable.table.setValueAt(SelectFrame.enumTypePanel.textField.getText(),miniTable.table.getSelectedRow(), EVENTCOLUMN);
		miniTable.table.setValueAt(SelectFrame.returnKey(),miniTable.table.getSelectedRow(), PRODUCT_ID_COL);
		SelectFrame.dispose();
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		//call selectFrame
		if(miniTable.table.getSelectedColumn()==EVENTCOLUMN) {
			//createSelectFrame
			SelectFrame=createModelSelectFrame();
			//addActionListener
			SelectFrame.okBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					Event_List();
				}
			});
			SelectFrame.enumTypePanel.button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					enumTypeEvent();
				}
			});
		}
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
	selectFrame createModelSelectFrame() {
		selectFrame selectFrame=new selectFrame();
		if(ISBYCOMPANY==false) {
			selectFrame.setCompanyType();
			selectFrame.enumTypePanel.textField.addKeyListener(selectFrameEvent_miniTable());
			selectFrame.NameList.addKeyListener(selectFrameEvent_miniTable());
		}else if(ISBYCOMPANY==true) {
			selectFrame.setProductType();
			selectFrame.enumTypePanel.textField.addKeyListener(selectFrameEvent_miniTable());
			selectFrame.NameList.addKeyListener(selectFrameEvent_miniTable());
		}
		return selectFrame;
	}
	void enumTypeEvent() {
		boolean confirmData=false;
		ListModel model = SelectFrame.NameList.getModel();
		for(int i=0; i< model.getSize();i++) {
			String data = (String) model.getElementAt(i);
			if(data.equals(SelectFrame.enumTypePanel.textField.getText())) {
				confirmData=true;
				SelectFrame.saveDatakey(SelectFrame.KeyList[i]);
				Event_Enum();
				break;
			}
		}
		if(confirmData==false) {
			noneData();
		}
	}
	void noneData() {
		int confirm = JOptionPane.showConfirmDialog(null, "등록되어 있지 않은 "+stringFactory.TYPE_PRODUCT+"입니다. 등록하시겠습니까 ?","등록되어 있지 않음",JOptionPane.YES_NO_OPTION);
		if(confirm==0) {
			JOptionPane.showMessageDialog(null, "제품 등록 tab 예정");
			SelectFrame.dispose();
		}
	}
	KeyListener selectFrameEvent_miniTable() {
		KeyListener keyListener = new KeyListener() {
			@Override
			public void keyTyped(KeyEvent arg0) {
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println("Press Key");
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					System.out.println("Press Enter");
					if(e.getSource()==SelectFrame.NameList) {
						System.out.println("select data");
						Event_List();
					}
					if(e.getSource()==SelectFrame.enumTypePanel.textField) {
						enumTypeEvent();
					}
				}
			}
		};
		return keyListener;
	}}
