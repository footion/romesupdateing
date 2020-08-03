package evevtListener;

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
import registrationFrame.companyRegistration;
import registrationFrame.orderRegistration;
import selectFrame.selectFrame;

public class miniTableEvent_Order implements MouseListener,KeyListener{
	miniTable miniTable;
	selectFrame SelectFrame;
	public miniTableEvent_Order(miniTable minitable) {
		miniTable=minitable;
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
	public void mousePressed(MouseEvent arg0) {
		//refresh totalPrice
		orderRegistration.totalPrice.refreshPrice();
		//call selectFrame
		if(miniTable.table.getSelectedColumn()==0) {
			//createSelectFrame
			SelectFrame=createModelSelectFrame();
			//addActionListener
			SelectFrame.okBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					miniTable.table.setValueAt(SelectFrame.dataList.getSelectedValue(),miniTable.table.getSelectedRow(), 0);
					SelectFrame.dispose();
				}
			});
			SelectFrame.enumTypePanel.button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					enumTypeEvent();
				}
			});
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			orderRegistration.totalPrice.refreshPrice();
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
	selectFrame createModelSelectFrame() {
		String dataTest [] = new String[30];
		for(int i=0;i<30;i++) {
			dataTest[i]="model"+Integer.toString(i+1);
		}
		String title="Model Representative";
		String type ="model";
		String[] listData=dataTest;
		selectFrame selectFrame=new selectFrame(title, type, listData);
		selectFrame.enumTypePanel.textField.addKeyListener(selectFrameEvent_miniTable());
		selectFrame.dataList.addKeyListener(selectFrameEvent_miniTable());
		return selectFrame;
	}
	void enumTypeEvent() {
		boolean confirmData=false;
		ListModel model = SelectFrame.dataList.getModel();
		for(int i=0; i< model.getSize();i++) {
			String data = (String) model.getElementAt(i);
			if(data.equals(SelectFrame.enumTypePanel.textField.getText())) {
				confirmData=true;
				miniTable.table.setValueAt(SelectFrame.enumTypePanel.textField.getText(),miniTable.table.getSelectedRow(), 0);
				SelectFrame.dispose();
				break;
			}
		}
		if(confirmData==false) {
			noneData();
		}
	}
	void noneData() {
		int confirm = JOptionPane.showConfirmDialog(null, "등록되어 있지 않은 "+stringFactory.TYPE_MODEL+"입니다. 등록하시겠습니까 ?","등록되어 있지 않음",JOptionPane.YES_NO_OPTION);
		if(confirm==0) {
			SelectFrame.dispose();
		}
	}
	KeyListener selectFrameEvent_miniTable() {
		KeyListener keyListener = new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Press Key");
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					System.out.println("Press Enter");
					if(e.getSource()==SelectFrame.dataList) {
						System.out.println("select data");
						miniTable.table.setValueAt(SelectFrame.dataList.getSelectedValue(),miniTable.table.getSelectedRow(), 0);
						SelectFrame.dispose();
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
