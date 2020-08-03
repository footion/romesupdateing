package pages.product;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.annotation.Resource;
import javax.swing.JOptionPane;

import entity.Product;
import entity.ProductCommonInfo;
import entity.dao.ProductCommonInfoDAO;
import entity.dao.ProductDAO;
import gui.MESCardLayout;

public class SaveButtonEvent implements ActionListener {

	ProductResource resource = ProductResource.getInstance();

	@Override
	public void actionPerformed(ActionEvent e) {

		Product product = new Product();
		ProductCommonInfo info = new ProductCommonInfo();
		product.setName(resource.getProductNameField(resource.FOR_USING).getText().toString());
		product.setSaleable(resource.getIsSalableBox(resource.FOR_USING).isSelected());
		product.setBuyable(resource.getIsBuyableBox(resource.FOR_USING).isSelected());
		product.setCommonInfo(info);
		ProductDAO productDAO = new ProductDAO();
		System.out.println(product);
		if(resource.getImgPath()==null)
			productDAO.saveProduct(product, "./resources/noImage.png");
		else
			productDAO.saveProduct(product, resource.getImgPath());
		info.setProduct(product);
		try {
			info.setSalePrice(Integer.parseInt(resource.getSalePriceField(resource.FOR_USING).getText().toString()));
		} catch (Exception err) {
			JOptionPane.showMessageDialog(null, "숫자값만 입력됩니다.");
			resource.getSalePriceField(resource.FOR_USING).setText("");
			resource.getSalePriceField(resource.FOR_USING).requestFocus();
			return;
		}
		try {
			info.setPrice(Integer.parseInt(resource.getCostField(resource.FOR_USING).getText().toString()));
		} catch (Exception err) {
			JOptionPane.showMessageDialog(null, "숫자값만 입력됩니다.");
			resource.getCostField(resource.FOR_USING).setText("");
			resource.getCostField(resource.FOR_USING).requestFocus();
			return;
		}
		String type = (String) resource.getProductTypeComboBox(resource.FOR_USING).getSelectedItem();
		info.setProductType(type);
		String building = (String) resource.getProductBuildingComboBox(resource.FOR_USING).getSelectedItem();
		String area = (String) resource.getProductAreaComboBox(resource.FOR_USING).getSelectedItem();
		info.setLocation(building + "-" + area);
		info.setCompany(resource.getCompanyField(resource.FOR_USING).getText().toString());
		ProductCommonInfoDAO infoDAO = new ProductCommonInfoDAO();
		infoDAO.saveProductCommonInfo(info);

		product = productDAO.findByPkey(product.getId());
		System.out.println(product);
		
		
		MESCardLayout.getInstance().changePage(MESCardLayout.ListProduct);

	}

}
