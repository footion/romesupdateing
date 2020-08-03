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
import registrationForm.R_company;
import registrationForm.R_receivedOrder;
import selectFrame.selectFrame;

public class miniTableEvent_R_ReceivedOrder implements MouseListener,KeyListener{
	miniTable miniTable;
	selectFrame SelectFrame;
	int EVENTCOLUMN=1;
	int PRODUCT_ID_COL=3;
	int QUANTITYCOL=7;
	int PRICECOL=8;
	public miniTableEvent_R_ReceivedOrder(miniTable minitable) {
		miniTable=minitable;
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
	public void mouseClicked(MouseEvent e) {
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
	}
	@Override
	public void mousePressed(MouseEvent e) {
		//refresh totalPrice
		R_receivedOrder.totalPrice.refreshPrice();
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
	public void mouseReleased(MouseEvent e) {
		if(e.getClickCount()==1) {
			System.out.println("doeble click");
			if(miniTable.table.getSelectedColumn()==QUANTITYCOL
					||miniTable.table.getSelectedColumn()==PRICECOL) {
				System.out.println("column true");
				if(miniTable.table.getValueAt(miniTable.table.getSelectedRow(), miniTable.table.getSelectedColumn()).equals("0")) {
					System.out.println("value : 0");
					miniTable.table.setValueAt("", miniTable.table.getSelectedRow(), miniTable.table.getSelectedColumn());
				}
			}
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		R_receivedOrder.totalPrice.refreshPrice();
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
	}
	selectFrame createModelSelectFrame() {
		selectFrame selectFrame=new selectFrame();
		selectFrame.setProductType();
		selectFrame.enumTypePanel.textField.addKeyListener(selectFrameEvent_miniTable());
		selectFrame.NameList.addKeyListener(selectFrameEvent_miniTable());
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
		int confirm = JOptionPane.showConfirmDialog(null, "��ϵǾ� ���� ���� "+stringFactory.TYPE_PRODUCT+"�Դϴ�. ����Ͻðڽ��ϱ� ?","��ϵǾ� ���� ����",JOptionPane.YES_NO_OPTION);
		if(confirm==0) {
			JOptionPane.showMessageDialog(null, "��ǰ ��� tab ����");
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
	}
}