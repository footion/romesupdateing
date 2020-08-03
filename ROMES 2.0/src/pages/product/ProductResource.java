package pages.product;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import entity.BuildingNames;
import entity.ProductType;
import evevtListener.textFieldEvent;
import factory.stringFactory;
import pages.location.LocationResources;
import pages.utils.ColoredPanel;

public class ProductResource {

	public static final String saveButtonString = "저장", productNameLabelString = "제품명", isSalableString = "판매불가",
			isBuyableString = "구매불가", productTypeString = "상품유형", salePriceString = "판매가", costString = "구매가",
			companyString = "거래처", gotoBackButtonString = "뒤로가기", createButtonString = "만들기",
			searcingButtonString = "찾기", productLocation = "위치정보", modifyButtonString = "수정", stockCountString = "재고량",
			inString = "입고", outString = "출고",bomString="BOM";

	public static final int FOR_USING = 0, FOR_INIT = 1;

	private JLabel productTypeLabel = null, salePriceLabel = null, costLabel = null, companyLabel = null,
			productNameLabel = null, productLocationLabel = null, productStockcountLabel = null;
	private JComboBox<String> productTypeComboBox, productBuildingComboBox, productAreaComboBox;
	private JTextField salePriceField = null, costField = null, companyField = null, productNameField = null,
			searchingField = null;
	private JCheckBox isSalableBox = null, isBuyableBox = null;
	private JPanel imagePanel = null;

	private JButton saveNewObjectButton = null, gotoBackButton = null, createNewObjectButton = null,
			showImagesButton = null, showcolumnsButton = null, searcingButton = null, gridShowButton = null,
			spreadShowButton = null, modifyButton = null, inButton = null, outButton = null,makeBOMButton=null;

	private ColoredPanel productLocationSelectPanel = null, inOutBtPanel = null;

	private String imgPath;

	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public ProductResource() {
		productTypeLabel = new ProductResource.ProductLabel(ProductResource.productTypeString);
		salePriceLabel = new ProductResource.ProductLabel(ProductResource.salePriceString);
		costLabel = new ProductResource.ProductLabel(ProductResource.costString);
		companyLabel = new ProductResource.ProductLabel(ProductResource.companyString);
		productTypeComboBox = new ProductResource.ProductComboBox();
		for (String type : ProductType.types) {
			productTypeComboBox.addItem(type);
		}

		salePriceField = new ProductResource.ProductTextField();
		costField = new ProductResource.ProductTextField();
		companyField = new ProductResource.ProductTextField();
		productNameField = new ProductResource.ProductTextField();
		productNameLabel = new ProductResource.ProductLabel(productNameLabelString);
		isSalableBox = new ProductResource.ProductCheckBox(isSalableString);
		isBuyableBox = new ProductResource.ProductCheckBox(isBuyableString);
		productStockcountLabel = new ProductResource.ProductLabel(stockCountString);
		productNameLabel.setFont(ProductResource.ProductBoldFont.getInstance());
		productNameField.setFont(new Font("나눔고딕", Font.BOLD, 30));
		productNameField.setForeground(Color.DARK_GRAY);
		imagePanel = new ImagePanel();

		saveNewObjectButton = new JButton(ProductResource.saveButtonString);
		gotoBackButton = new JButton(ProductResource.gotoBackButtonString);
		createNewObjectButton = new JButton(ProductResource.createButtonString);
		createNewObjectButton.setActionCommand(ProductResource.createButtonString);
		searchingField = new ProductResource.ProductTextField();
		final int searchingFieldWidth = 150, searchingFieldHeight = 20;
		searchingField.setPreferredSize(new Dimension(searchingFieldWidth, searchingFieldHeight));
		searcingButton = new JButton(searcingButtonString);
		gridShowButton = new JButton(new ImageIcon("./resources/grid.png"));
		spreadShowButton = new JButton(new ImageIcon("./resources/list.png"));
		productLocationLabel = new ProductResource.ProductLabel(ProductResource.productLocation);
		productLocationSelectPanel = new ColoredPanel();
		setSelectPanel();
		inOutBtPanel = new ColoredPanel();
		setInOutPanel();
		companyField.addMouseListener(new textFieldEvent());
		companyField.setName(stringFactory.ORDER_CLIENT_TN);

		modifyButton = new JButton(ProductResource.saveButtonString);
		makeBOMButton = new JButton(ProductResource.bomString);
	}
	public JButton getMakeBOMButton() {
		return makeBOMButton;
	}
	private void setInOutPanel() {

		inButton = new JButton(inString);
		outButton = new JButton(outString);
		inOutBtPanel.add(inButton);
		inOutBtPanel.add(outButton);
	}

	public JPanel getInOutPanel() {
		return inOutBtPanel;
	}

	public JPanel getProductLocationSelectPanel() {
		return productLocationSelectPanel;
	}

	public void setSelectPanel() {

		productBuildingComboBox = new ProductResource.ProductComboBox();
		productAreaComboBox = new ProductResource.ProductComboBox();

		for (String building : BuildingNames.names) {
			productBuildingComboBox.addItem(building);
		}
		productBuildingComboBox.addActionListener(new ActionListener() {
			BuildingNames builingInfo = BuildingNames.getInstance();

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox cb = (JComboBox) e.getSource();
				String selectedColumString = (String) cb.getSelectedItem();
				System.out.println(selectedColumString);

				productAreaComboBox.removeAllItems();

				for (String area : builingInfo.getAreamap().get(selectedColumString)) {
					productAreaComboBox.addItem(area);
				}

			}
		});
		productLocationSelectPanel.add(productBuildingComboBox);
		productLocationSelectPanel.add(productAreaComboBox);

	}

	public JComboBox<String> getProductBuildingComboBox(int i) {
		if (i == FOR_INIT) {
			productBuildingComboBox.setSelectedIndex(-1);
			productBuildingComboBox.setEnabled(true);
		}
		return productBuildingComboBox;
	}

	public JComboBox<String> getProductAreaComboBox(int i) {
		if (i == FOR_INIT) {
			productAreaComboBox.setSelectedIndex(-1);
			productAreaComboBox.setEnabled(true);
		}
		return productAreaComboBox;
	}

	public JComboBox<String> getProductLocationComboBox(int i) {
		if (i == FOR_INIT) {
			productBuildingComboBox.setSelectedIndex(-1);
			productBuildingComboBox.setEnabled(true);
		}
		return productBuildingComboBox;
	}

	public JLabel getProductLocationLabel() {
		return productLocationLabel;
	}

	public JButton getModifyButton() {
		return modifyButton;
	}

	public JLabel getStockCountLabel() {
		return productStockcountLabel;
	}

	public static class ProductPlainFont extends Font {
		public static ProductPlainFont getInstance() {
			return LazyHolder.INSTANCE;
		}

		private static class LazyHolder {
			private static final ProductPlainFont INSTANCE = new ProductPlainFont();
		}

		protected ProductPlainFont() {
			super(new Font("나눔고딕", Font.PLAIN, 16));
			// TODO Auto-generated constructor stub
		}
	}

	public static class ProductBoldFont extends Font {
		public static ProductBoldFont getInstance() {
			return LazyHolder.INSTANCE;
		}

		private static class LazyHolder {
			private static final ProductBoldFont INSTANCE = new ProductBoldFont();
		}

		protected ProductBoldFont() {
			super(new Font("나눔고딕", Font.BOLD, 19));
			// TODO Auto-generated constructor stub
		}
	}

	public class ProductComboBox extends JComboBox<String> {
		public ProductComboBox() {
			this.setFont(ProductResource.ProductPlainFont.getInstance());
		}
	}

	public class ProductTextField extends JTextField {
		public ProductTextField() {
			this.setFont(ProductResource.ProductPlainFont.getInstance());
		}
	}

	public class ProductLabel extends JLabel {

		public ProductLabel() {
			this.setFont(ProductResource.ProductPlainFont.getInstance());
		}

		public ProductLabel(String companystring) {
			super(companystring);
			this.setFont(ProductResource.ProductPlainFont.getInstance());
		}
	}

	public class ProductCheckBox extends JCheckBox {
		public ProductCheckBox(String string) {
			super(string);
			this.setFont(ProductResource.ProductPlainFont.getInstance());
		}
	}

	public JLabel getProductTypeLabel() {
		return productTypeLabel;
	}

	public JLabel getSalePriceLabel() {
		return salePriceLabel;
	}

	public JLabel getCostLabel() {
		return costLabel;
	}

	public JLabel getCompanyLabel() {
		return companyLabel;
	}

	public JComboBox<String> getProductTypeComboBox(int i) {
		if (i == FOR_INIT) {
			productTypeComboBox.setSelectedIndex(-1);
			productTypeComboBox.setEnabled(true);
		}
		return productTypeComboBox;
	}

	public JTextField getSalePriceField(int i) {
		if (i == FOR_INIT) {
			salePriceField.setText("");
			salePriceField.setEditable(true);
		}
		return salePriceField;
	}

	public JTextField getCostField(int i) {
		if (i == FOR_INIT) {
			costField.setText("");
			costField.setEditable(true);
		}
		return costField;
	}

	public JTextField getCompanyField(int i) {
		if (i == FOR_INIT) {
			companyField.setText("");
			companyField.setEditable(true);
		}
		return companyField;
	}

	public JButton getSaveButton() {
		return saveNewObjectButton;
	}

	public JButton getGotoBackButton() {
		return gotoBackButton;
	}

	public JButton getCreateNewObjectButton() {
		return createNewObjectButton;
	}

	public JTextField getSearchingField(int i) {
		if (i == FOR_INIT) {
			searchingField.setText("");
			searchingField.setEditable(true);
		}
		return searchingField;
	}

	public JPanel getImagePanel() {
		return imagePanel;
	}

	public JLabel getProductNameLabel() {
		return productNameLabel;
	}

	public JTextField getProductNameField(int i) {
		if (i == FOR_INIT) {
			productNameField.setEditable(true);
			productNameField.setText("");
		}
		return productNameField;
	}

	public JCheckBox getIsSalableBox(int i) {
		if (i == FOR_INIT) {
			isSalableBox.setEnabled(true);
			isSalableBox.setSelected(false);
		}
		return isSalableBox;
	}

	public JCheckBox getIsBuyableBox(int i) {
		if (i == FOR_INIT) {
			isBuyableBox.setEnabled(true);
			isBuyableBox.setSelected(false);
		}
		return isBuyableBox;
	}

	public static ProductResource getInstance() {
		return LazyHolder.INSTANCE;
	}

	private static class LazyHolder {
		private static final ProductResource INSTANCE = new ProductResource();
	}

	public JButton getSearcingButton() {
		return searcingButton;
	}

	public JButton getGridShowButton() {
		return gridShowButton;
	}

	public JButton getSpreadShowButton() {
		return spreadShowButton;
	}
}
