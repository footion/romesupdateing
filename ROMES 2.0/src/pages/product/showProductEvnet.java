package pages.product;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import gui.HeadPanel;
import gui.MESCardLayout;
import mainFrame.lodingFrame;
import mainFrame.mainFrame;

public class showProductEvnet implements MouseListener {
	int productKey;
	public showProductEvnet(int productKey) {
		this.productKey = productKey;
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println(productKey);
		CardLayout card = mainFrame.card;
		Container container = mainFrame.Container;
		mainFrame.title.setText("제품상세보기");
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				lodingFrame loding = new lodingFrame();
				try {
					container.add(new FormPage(productKey),"ProductInfoShow");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				card.show(container, "ProductInfoShow");
				loding.dispose();
			}
		});
		thread.start();
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	

}
