package gui;

import java.awt.CardLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

import gui.font.SubMenuFont;
import gui.menu.MenuContainingPanel;
import gui.menu.SubmenuJLabel;
import page.storing.CreateStoring;
import page.storing.SortingPanel;
import pages.commonCode.CommonCodePage;
import pages.company.CompanyRegistration;
import pages.location.ShowHouseImagePanel;
import pages.order.ShowOrderPanelMaster;
import pages.product.FormPage;
import pages.product.FormUpdatePage;
import pages.product.ShowProductsPanel;

public class MESCardLayout extends JPanel {

	final public static String ProductCreate = "제품입력";
	final public static String ListProduct = "제품조회";
	final public static String LocationShow = "로케이션";
	final public static String companyCreate = "회사입력";
	final public static String ProductShow = "제품보기";
	final public static String BomShow = "Bom 정보보기";
	final public static String createStoring = "입고데이터생성";
	final public static String showStoring = "입출고보기";
	final public static String updateProduct = "제품수정";
	final public static String showOrderHsitory = "발주정보보기";
	final public static String CommonPage = "공통코드페이지";

	// final public static String

	public static MESCardLayout getInstance() {
		return LazyHolder.INSTANCE;
	}

	private static class LazyHolder {
		private static final MESCardLayout INSTANCE = new MESCardLayout();
	}

	public static HashMap<String, JPanel> hashMap = null;

	public MESCardLayout() {
		// TODO Auto-generated constructor stub
		this.setLayout(new CardLayout());
		hashMap = new HashMap<String, JPanel>();

		hashMap.put(ProductCreate,new FormUpdatePage(-1));
		this.add(hashMap.get(ProductCreate), ProductCreate);

		hashMap.put(ListProduct, new ShowProductsPanel());
		this.add(hashMap.get(ListProduct), ListProduct);

		hashMap.put(LocationShow, new ShowHouseImagePanel());
		this.add(hashMap.get(LocationShow), LocationShow);

		hashMap.put(companyCreate, new CompanyRegistration());
		this.add(hashMap.get(companyCreate), companyCreate);

		hashMap.put(ProductShow, new FormPage(33));
		this.add(hashMap.get(ProductShow), ProductShow);


		hashMap.put(createStoring, new CreateStoring());
		this.add(hashMap.get(createStoring), createStoring);

		hashMap.put(showStoring, new SortingPanel());
		this.add(hashMap.get(showStoring), showStoring);

		hashMap.put(showOrderHsitory, new ShowOrderPanelMaster());
		this.add(hashMap.get(showOrderHsitory), showOrderHsitory);

		hashMap.put(CommonPage, new CommonCodePage());
		this.add(hashMap.get(CommonPage), CommonPage);

		this.setVisible(true);
	}

	@Override
	public void repaint() {
		// TODO Auto-generated method stub
		super.repaint();

	}

	public void changePage(String className) {
		// TODO Auto-generated method stub
		StringBuffer selectedMenuStringBuffer = new StringBuffer(className);
		selectedMenuStringBuffer.insert(0, " -  ");
		String selectedMenuString = selectedMenuStringBuffer.toString();
		ArrayList<MenuContainingPanel> menuList = MenuPanel.getMenuContainingPanels();
		for (MenuContainingPanel bigM : menuList) {
			ArrayList<SubmenuJLabel> submenuJLabels = bigM.getSubMarrayList();
			for (SubmenuJLabel sub : submenuJLabels) {
				if (sub.getText().toString().equals(selectedMenuString)) {
					sub.setFont(new Font("휴먼엑스포", Font.BOLD, 14));
				} else {
					sub.setFont(SubMenuFont.getInstance());
				}
			}
		}

		CardLayout cardLayout = (CardLayout) this.getLayout();
		this.remove(hashMap.get(className));

		switch (className) {

		case ProductCreate://
		{
			hashMap.put(ProductCreate, new FormUpdatePage(-1));
			this.add(hashMap.get(ProductCreate), ProductCreate);
		}
			break;

		case ListProduct://
		{
			hashMap.put(ListProduct, new ShowProductsPanel());
			this.add(hashMap.get(ListProduct), ListProduct);
		}
			break;
		case LocationShow: {
			hashMap.put(LocationShow, new ShowHouseImagePanel());
			this.add(hashMap.get(LocationShow), LocationShow);
		}
		case companyCreate: {
			hashMap.put(companyCreate, new CompanyRegistration());
			this.add(hashMap.get(companyCreate), companyCreate);
		}
			break;
		case ProductShow: {
			hashMap.put(ProductShow, new FormPage(0));
			this.add(hashMap.get(ProductShow), ProductShow);
		}

		case createStoring: {
			hashMap.put(createStoring, new CreateStoring());
			this.add(hashMap.get(createStoring), createStoring);
		}

		case showStoring: {
			hashMap.put(showStoring, new SortingPanel());
			this.add(hashMap.get(showStoring), showStoring);
		}

		case showOrderHsitory: {
			hashMap.put(showOrderHsitory, new ShowOrderPanelMaster());
			this.add(hashMap.get(showOrderHsitory), showOrderHsitory);
		}

		case CommonPage: {
			hashMap.put(CommonPage, new CommonCodePage());
			this.add(hashMap.get(CommonPage), CommonPage);
		}

		default:
			break;
		}
		cardLayout.show(this, className);

	}

	public void changePage(String className, int key) {
		StringBuffer selectedMenuStringBuffer = new StringBuffer(className);
		selectedMenuStringBuffer.insert(0, " -  ");
		String selectedMenuString = selectedMenuStringBuffer.toString();
		ArrayList<MenuContainingPanel> menuList = MenuPanel.getMenuContainingPanels();
		for (MenuContainingPanel bigM : menuList) {
			ArrayList<SubmenuJLabel> submenuJLabels = bigM.getSubMarrayList();
			for (SubmenuJLabel sub : submenuJLabels) {
				if (sub.getText().toString().equals(selectedMenuString)) {
					sub.setFont(new Font("휴먼엑스포", Font.BOLD, 14));
				} else {
					sub.setFont(SubMenuFont.getInstance());
				}
			}
		}

		CardLayout cardLayout = (CardLayout) this.getLayout();
		this.remove(hashMap.get(className));

		switch (className) {

		case ProductCreate://
		{
			hashMap.put(ProductCreate, new FormUpdatePage(key));
			this.add(hashMap.get(ProductCreate), ProductCreate);
		}
			break;

		case ListProduct://
		{
			hashMap.put(ListProduct, new ShowProductsPanel());
			this.add(hashMap.get(ListProduct), ListProduct);
		}
			break;
		case LocationShow: {
			hashMap.put(LocationShow, new ShowHouseImagePanel());
			this.add(hashMap.get(LocationShow), LocationShow);
		}
		case companyCreate: {
			hashMap.put(companyCreate, new CompanyRegistration());
			this.add(hashMap.get(companyCreate), companyCreate);
		}
			break;
		case ProductShow: {
			hashMap.put(ProductShow, new FormPage(key));
			this.add(hashMap.get(ProductShow), ProductShow);
		}
	
		case updateProduct: {
			hashMap.put(updateProduct, new FormUpdatePage(key));
			this.add(hashMap.get(updateProduct), updateProduct);
		}
		case showOrderHsitory: {
			hashMap.put(showOrderHsitory, new ShowOrderPanelMaster());
			this.add(hashMap.get(showOrderHsitory), showOrderHsitory);
		}
		case CommonPage: {
			hashMap.put(CommonPage, new CommonCodePage());
			this.add(hashMap.get(CommonPage), CommonPage);
		}

		default:
			break;
		}
		cardLayout.show(this, className);

	}
}
