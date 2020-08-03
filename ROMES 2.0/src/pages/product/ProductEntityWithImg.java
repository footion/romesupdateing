package pages.product;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import components.imagelabel.ImageLabel;
import gui.ColoredPanel;

public class ProductEntityWithImg extends ColoredPanel {

	private int keynumber = 0;

	/**
	 * Create the panel.
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		ProductEntityWithImg entityWithImg = new ProductEntityWithImg(0, new ImageIcon("./resources/grid.png"), "물품 1","9999원");
		frame.setVisible(true);
		frame.setSize(new Dimension(150,80));
		frame.add(entityWithImg);
	}
	public ProductEntityWithImg(int keynumber, ImageIcon icon, String productName, String productPrice) {
		this.keynumber = keynumber;
		EmptyBorder emptyBorder = new EmptyBorder(10,20,10,40);
		LineBorder lineBorder = new LineBorder(Color.lightGray, 1, true);
		Border compound;
        compound = BorderFactory.createCompoundBorder(
        		lineBorder, emptyBorder);
		this.setBorder(compound);
		this.setLayout(new BorderLayout(5, 5));
		ImageLabel imgLabel=new ImageLabel(icon,keynumber+"");
		int imgLabel_width = 40,imgLabel_height=40;
		imgLabel=(ImageLabel) imgLabel.resizeLabel(imgLabel, imgLabel_width, imgLabel_height);
		this.add(imgLabel, BorderLayout.WEST);
		JPanel rightPanel = new ColoredPanel();
		rightPanel.setLayout(new BorderLayout(5, 5));
		ProductLabel ImgProductName = new ProductLabel(productName, ProductLabel.NameFontType);
		ProductLabel ImgProductPrice = new ProductLabel(productPrice, ProductLabel.priceFontType);
		rightPanel.add(ImgProductName,BorderLayout.NORTH);
		rightPanel.add(ImgProductPrice,BorderLayout.CENTER);
		this.add(rightPanel,BorderLayout.EAST);
		this.addMouseListener(new showProductEvnet(keynumber));
	}

	class ProductLabel extends JLabel {

		final Font NameFont = new Font("나눔고딕", Font.BOLD, 13);
		final Font priceFont = new Font("나눔고딕", Font.PLAIN, 10);
		final static int NameFontType = 0,priceFontType = 1;

		ProductLabel() {
			super();
		}

		ProductLabel(String string, int fontType) {
			super(string);
			switch (fontType) {
			case NameFontType:
					this.setFont(NameFont);
				break;
			case priceFontType:
					this.setFont(priceFont);
				break;

			default:
				break;
			}
		}
	}
}
