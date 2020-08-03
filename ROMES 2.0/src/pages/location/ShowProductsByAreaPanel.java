package pages.location;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import gui.ColoredPanel;
import pages.product.ProductEntityWithImg;
import pages.product.showData;
import pages.product.showProductEvnet;

public class ShowProductsByAreaPanel extends ColoredPanel {

	private String area;



	public ShowProductsByAreaPanel(JPanel superPanel,String area) {
		this.setPreferredSize(new Dimension(800, 800));
		this.area=area;
		this.setBorder(new LineBorder(new Color(128, 128, 128), 1, true));
		this.setLayout(new BorderLayout());
		addingProducts();
		superPanel.add(this, BorderLayout.CENTER);
		superPanel.revalidate();
	}

	private void addingProducts() {
		Component component = gettingComponents();
		if (component != null)
			this.add(component, BorderLayout.CENTER);

	}

	private Component gettingComponents() {
		ArrayList<showData> testDatas = TestingDataSetForGui();
		final int countEntityOfLongPanel = 4;
		final int heightOfLongPanel = 100;
		// 데이터 갯수/countEntityOfLongPanel = 긴 페널의 갯수
		final int longSizePanelSize = testDatas.size() / countEntityOfLongPanel;
		final int mainPanelSize = heightOfLongPanel * longSizePanelSize;

		ColoredPanel mainPanel = new ColoredPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setPreferredSize(new Dimension(0, mainPanelSize));
		ColoredPanel products = new ColoredPanel();
		products.setLayout(new GridLayout(0, countEntityOfLongPanel, 10, 10));

		for (int col = 0; col < longSizePanelSize + 1; col++) {
			for (int row = 0; row < countEntityOfLongPanel; row++) {
				if (row + col * countEntityOfLongPanel >= testDatas.size())
					break;
				showData data = testDatas.get(row + col * countEntityOfLongPanel);
				ProductEntityWithImg entity = new ProductEntityWithImg(data.key, data.icon, data.productName,
						data.productPrice);
				
				products.add(entity);
			}
		}
		mainPanel.add(products, BorderLayout.NORTH);
		JScrollPane scrollableTextArea = new JScrollPane(mainPanel);
		scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		return scrollableTextArea;
	}

	

	ArrayList<showData> TestingDataSetForGui() {
		
		ArrayList<showData> testDatasA01 = new ArrayList<showData>(
				Arrays.asList(new showData(1028, new ImageIcon("./resources/grid.png"), "나무 못", "100"),
						new showData(1034, new ImageIcon("./resources/grid.png"), "나무인형", "2000"),
						new showData(1036, new ImageIcon("./resources/grid.png"), "prodiuct3", "price3")));
		ArrayList<showData> testDatasA02 = new ArrayList<showData>(
				Arrays.asList(new showData(1028, new ImageIcon("./resources/grid.png"), "prodiuct1", "price1"),
						new showData(1034, new ImageIcon("./resources/grid.png"), "prodiuct2", "price2")));
		switch (area) {
		case "A-01":
			return testDatasA01;
		case "A-02":
			return testDatasA02;

		default:
			return testDatasA01;
			
		}
		
		
	}

}
