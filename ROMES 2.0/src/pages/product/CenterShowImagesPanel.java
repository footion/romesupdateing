package pages.product;

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

import entity.dao.ProductDAO;
import gui.ColoredPanel;

public class CenterShowImagesPanel extends ColoredPanel {

	public CenterShowImagesPanel(JPanel superPanel) {
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
		ArrayList<showData> testDatas = DataSetForGui();
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

	
	ArrayList<showData> DataSetForGui(){
		ProductDAO dao = new ProductDAO();
		ArrayList<showData> Datas = new ArrayList<showData>();
		dao.getProducts().
		forEach(e->{
			System.out.println(e);
			showData sd=new showData(e.getId(), new ImageIcon(e.getImage()), e.getName(), e.getCommonInfo().getSalePrice()+"");
			if(!Datas.contains(sd)) {
				Datas.add(sd);
			}
				
		});;
		return Datas;	
	}
	
	ArrayList<showData> TestingDataSetForGui() {
		ArrayList<showData> testDatas = new ArrayList<showData>(
				Arrays.asList(new showData(1028, new ImageIcon("./resources/grid.png"), "prodiuct1", "price1"),
						new showData(1034, new ImageIcon("./resources/grid.png"), "prodiuct2", "price2"),
						new showData(1036, new ImageIcon("./resources/grid.png"), "prodiuct3", "price3"),
						new showData(2, new ImageIcon("./resources/grid.png"), "prodiuct4", "price4"),
						new showData(3, new ImageIcon("./resources/grid.png"), "prodiuct5", "price5"),
						new showData(4, new ImageIcon("./resources/grid.png"), "prodiuct2", "price2"),
						new showData(0, new ImageIcon("./resources/grid.png"), "prodiuct3", "price3"),
						new showData(0, new ImageIcon("./resources/grid.png"), "prodiuct4", "price4"),
						new showData(0, new ImageIcon("./resources/grid.png"), "prodiuct5", "price5"),
						new showData(0, new ImageIcon("./resources/grid.png"), "prodiuct2", "price2"),
						new showData(0, new ImageIcon("./resources/grid.png"), "prodiuct3", "price3"),
						new showData(0, new ImageIcon("./resources/grid.png"), "prodiuct4", "price4"),
						new showData(0, new ImageIcon("./resources/grid.png"), "prodiuct5", "price5"),
						new showData(0, new ImageIcon("./resources/grid.png"), "prodiuct2", "price2"),
						new showData(0, new ImageIcon("./resources/grid.png"), "prodiuct3", "price3"),
						new showData(0, new ImageIcon("./resources/grid.png"), "prodiuct4", "price4"),
						new showData(0, new ImageIcon("./resources/grid.png"), "prodiuct5", "price5"),
						new showData(0, new ImageIcon("./resources/grid.png"), "prodiuct2", "price2"),
						new showData(0, new ImageIcon("./resources/grid.png"), "prodiuct3", "price3"),
						new showData(0, new ImageIcon("./resources/grid.png"), "prodiuct4", "price4"),
						new showData(0, new ImageIcon("./resources/grid.png"), "prodiuct5", "price5"),
						new showData(0, new ImageIcon("./resources/grid.png"), "prodiuct2", "price2"),
						new showData(0, new ImageIcon("./resources/grid.png"), "prodiuct3", "price3"),
						new showData(0, new ImageIcon("./resources/grid.png"), "prodiuct4", "price4"),
						new showData(0, new ImageIcon("./resources/grid.png"), "prodiuct5", "price5"),
						new showData(0, new ImageIcon("./resources/grid.png"), "prodiuct2", "price2"),
						new showData(0, new ImageIcon("./resources/grid.png"), "prodiuct3", "price3"),
						new showData(0, new ImageIcon("./resources/grid.png"), "prodiuct4", "price4"),
						new showData(0, new ImageIcon("./resources/grid.png"), "prodiuct5", "price5"),
						new showData(0, new ImageIcon("./resources/grid.png"), "prodiuct2", "price2"),
						new showData(0, new ImageIcon("./resources/grid.png"), "prodiuct3", "price3"),
						new showData(0, new ImageIcon("./resources/grid.png"), "prodiuct4", "price4"),
						new showData(0, new ImageIcon("./resources/grid.png"), "prodiuct5", "price5"),
						new showData(0, new ImageIcon("./resources/grid.png"), "prodiuct6", "price6")));
		return testDatas;
	}

}
