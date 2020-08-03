package pages.product;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import entity.Product;
import entity.ProductCommonInfo;
import entity.dao.ProductCommonInfoDAO;
import entity.dao.ProductDAO;
import gui.HeadPanel;
import gui.MESCardLayout;
import img.ImgFactory;
import mainFrame.lodingFrame;
import mainFrame.mainFrame;

public class modifyButtonEvent implements ActionListener {
	int keynumber;
	ProductResource resource = ProductResource.getInstance();
	
	private FormUpdatePage eventPanel;
	private ProductDAO dao;
	private Product product;

	public modifyButtonEvent(int keynumber, FormUpdatePage formUpdatePage) {
		this.keynumber = keynumber;
		this.eventPanel = formUpdatePage;
		
	}

	public modifyButtonEvent(int keynumber2) {
		this.keynumber = keynumber2;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton button = (JButton) e.getSource();
		String buttonString = button.getText().toString();
		if (buttonString.equals(ProductResource.modifyButtonString)) {

//			resource.getProductNameField(ProductResource.FOR_USING).setEditable(true);
//			resource.getIsBuyableBox(ProductResource.FOR_USING).setEnabled(true);
//			resource.getIsSalableBox(ProductResource.FOR_USING).setEnabled(true);
//			resource.getProductTypeComboBox(ProductResource.FOR_USING).setEnabled(true);
//			resource.getSalePriceField(ProductResource.FOR_USING).setEditable(true);
//			resource.getCostField(ProductResource.FOR_USING).setEditable(true);
//			resource.getCompanyField(ProductResource.FOR_USING).setEditable(true);
//			resource.getSalePriceField(ProductResource.FOR_USING).setEditable(true);
//			HeadPanel.getTitleLabel().setText("제품 데이터 수정");
//			button.setText(ProductResource.saveButtonString);
			MESCardLayout.getInstance().changePage(MESCardLayout.updateProduct, keynumber);

			HeadPanel.getTitleLabel().setText(MESCardLayout.updateProduct);
			button.setText(ProductResource.saveButtonString);
		} else if (buttonString.equals(ProductResource.saveButtonString)) {
			if(keynumber!=-1)
				updateProduct();
			else
				createProduct();

			
			CardLayout card = mainFrame.card;
			Container container = mainFrame.Container;
			mainFrame.title.setText("제품상세보기");
			Thread thread = new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					lodingFrame loding = new lodingFrame();
					container.add(new FormPage(keynumber),"ProductInfoShow");
					card.show(container, "ProductInfoShow");
					loding.dispose();
				}
			});
			thread.start();
			

			
			button.setText(ProductResource.modifyButtonString);
		}

	}

	private void createProduct() {
		makeProduct();
		String path = eventPanel.getImagePath();
		System.out.println(path);
		if(path==null) {
			dao.saveProduct(product,"./resources/noImage.png");
			System.out.println("여기");
		}
			
		else {
			dao.saveProduct(product,path);
			System.out.println("저기");
		}
		ProductCommonInfoDAO commonInfoDAO = new ProductCommonInfoDAO();
		product.getCommonInfo().setProduct(product);
		commonInfoDAO.saveProductCommonInfo(product.getCommonInfo());
		keynumber = product.getId();
			
	}

	private void updateProduct() {
		makeProduct();
		dao.updateProduct(product);
		
	}

	private void makeProduct() {
		// TODO Auto-generated method stub
		this.dao = new ProductDAO();
		
		this.product = eventPanel.getProduct();

		product.setName(eventPanel.getProductNameField().getText());
		product.setBuyable(eventPanel.disableBuy.isSelected());
		product.setSaleable(eventPanel.disableSale.isSelected());
		if (eventPanel.getImagePath() != null)
			product.setImage(ImgFactory.ImgToBytes(eventPanel.getImagePath()));
		ProductCommonInfo info = product.getCommonInfo();
		info.setProductType((String)eventPanel.getComboBox().getSelectedItem());
		info.setLocation(eventPanel.getLocationField().getText());
		Integer price = Integer.parseInt(eventPanel.getSalePriceField().getText());
		info.setSalePrice(price);
		info.setDetails(eventPanel.getDetail().getText());
		
		info.setUnitType((String)eventPanel.getUnitTypeBox().getSelectedItem());
		info.setUnitCount((int)eventPanel.unitCount().getValue());
		info.setWeight((int)eventPanel.getunitWeight().getValue());
		
	}
}
