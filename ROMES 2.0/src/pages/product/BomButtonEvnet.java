package pages.product;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import gui.HeadPanel;
import gui.MESCardLayout;

public class BomButtonEvnet implements ActionListener {
	int productKey;
	public BomButtonEvnet(int productKey) {
		this.productKey = productKey;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println(productKey);
		MESCardLayout.getInstance().changePage(MESCardLayout.BomShow,productKey);
		HeadPanel.getTitleLabel().setText(MESCardLayout.BomShow);
	}

	

}
