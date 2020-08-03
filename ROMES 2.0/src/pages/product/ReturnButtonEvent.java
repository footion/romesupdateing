package pages.product;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import gui.HeadPanel;
import gui.MESCardLayout;
import mainFrame.lodingFrame;
import mainFrame.mainFrame;
import pages.location.LocationResources;
import pages.location.LocationResources.ResizeImageLabel;
import pages.utils.ColoredPanel;

public class ReturnButtonEvent implements ActionListener {

	private JPanel superPanel;

	public ReturnButtonEvent() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		CardLayout card = mainFrame.card;
		Container container = mainFrame.Container;
		String ClassName = "ProductShow";
		mainFrame.title.setText("원자재마스터");
		
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				lodingFrame loding = new lodingFrame();
				card.show(container, ClassName);
				loding.dispose();
			}
		});
		thread.start();
	}
}
