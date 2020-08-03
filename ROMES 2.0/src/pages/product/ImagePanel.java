package pages.product;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import pages.utils.ColoredPanel;

public class ImagePanel extends ColoredPanel {

	public ImagePanel(JPanel superPanel) {
		final int width = 150;
		this.setBorder(new LineBorder(new Color(128, 128, 128), 1, true));
		this.setPreferredSize(new Dimension(width, 0));
		superPanel.add(this,BorderLayout.EAST);
	}

	public ImagePanel() {
		// TODO Auto-generated constructor stub
		final int width = 150;
		this.setBorder(new LineBorder(new Color(128, 128, 128), 1, true));
		this.setPreferredSize(new Dimension(width, 0));
		JLabel inputImageLabel= new JLabel("이미지 등록");
		this.add(inputImageLabel);
	}

}
