package Menu;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

import Login.LoginPanel;
import Login.log_history;
import Login.login_management;
import factory.fontFactory;
import mainFrame.lodingFrame;
import mainFrame.mainFrame;
import page.storing.SortingPanel;
import pages.CommonCodePage;
import pages.ProductionPlanManagement;
import pages.companyManagement;
import pages.ReceivedOrderManagement;
import pages.purchasePriceManagement;
import pages.order.ShowOrderPanelMaster;
import pages.product.ShowProductsPanel;

@SuppressWarnings("serial")
public class SubMenu extends JLabel implements MouseListener{
	Container container = mainFrame.Container;
	CardLayout card = mainFrame.card;
	String ClassName;
	ArrayList<SubMenu> submenuList = new ArrayList<>();
	public static ArrayList<SubMenu> submenuHistory = new ArrayList<>();
	public SubMenu(String string) {
		super(" - " +string);
		setFont(new Font(fontFactory.BASIC_FONT, Font.PLAIN, 16));
		setBorder(new EmptyBorder(7, 0, 0 ,0 ));
		setVisible(false);
		addMouseListener(this);
	}
	public static void clickedLabel(ArrayList<SubMenu> submenuHistory) {
		if(submenuHistory.size()>0) {
			submenuHistory.get(0).setFont(new Font(fontFactory.BASIC_FONT, Font.PLAIN, 16));
			submenuHistory.remove(0);
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() instanceof SubMenu) {
			SubMenu submenu = (SubMenu) e.getSource();
			String SubMenuName = submenu.getText().substring(3);
			clickedLabel(submenuHistory);
			submenu.setFont((new Font(fontFactory.BASIC_FONT, Font.BOLD, 16)));
			submenuHistory.add(submenu);
			ClassName="";
			switch (SubMenuName) {
			case "수주 관리":
				ClassName = "ReceipedOrder";
				mainFrame.title.setText(SubMenuName);
				for(Component c : container.getComponents()) {
					if(c.getName()!=null&&c.getName().equals(ClassName)) {
						((ReceivedOrderManagement)c).refresh();
					}
				}
				break;
			case "거래처관리":
				ClassName = "CompanyManagement";
				mainFrame.title.setText(SubMenuName);
				for(Component c : container.getComponents()) {
					if(c.getName()!=null&&c.getName().equals(ClassName)) {
						((companyManagement)c).refresh();
					}
				}
				break;
			case "매입품관리":
				ClassName = "PurchasePriceManagement";
				mainFrame.title.setText(SubMenuName);
				for(Component c : container.getComponents()) {
					if(c.getName()!=null&&c.getName().equals(ClassName)) {
						((purchasePriceManagement)c).refresh();
					}
				}
				break;
			case "생산 계획":
				ClassName = "ProductionPlanManagement";
				mainFrame.title.setText(SubMenuName);
				for(Component c : container.getComponents()) {
					if(c.getName()!=null&&c.getName().equals(ClassName)) {
						((ProductionPlanManagement)c).refresh();
					}
				}
				break;
			case "공통 코드":
				ClassName = "CommonCodePage";
				mainFrame.title.setText(SubMenuName);
				for(Component c : container.getComponents()) {
					if(c.getName()!=null&&c.getName().equals(ClassName)) {
						((CommonCodePage)c).refresh();
					}
				}
				break;
			case "사용자 관리":
				if(LoginPanel.confirm_admin==true) {
					ClassName="LoginManagement";
					mainFrame.title.setText("사용자 관리");
					login_management.model.setRowCount(0);
					login_management.StartData();
					if(LoginPanel.ID==1) {
						login_management.admin_authority.setVisible(true);
					}
				}else {
					JOptionPane.showMessageDialog(null, "권한이 없는 사용자입니다.","권한이 없는 사용자",JOptionPane.WARNING_MESSAGE);
				}
				break;
			case "원자재마스터":{
				
				mainFrame.Container.add(new ShowProductsPanel(),"ProductShow");
				ClassName = "ProductShow";
				mainFrame.title.setText("원자재마스터");
			}break;
			case "로그 기록":
				ClassName="LogHistory";
				mainFrame.title.setText("로그 기록");
				log_history.refresh();
				break;
				
			case "입고/출고":{
				ClassName = "showStoring";
				mainFrame.title.setText("입고/출고");
			}
			break;
			case "발주 관리":{
				mainFrame.Container.add(new ShowOrderPanelMaster(),"orderHistory");
				ClassName = "orderHistory";
				mainFrame.title.setText("발주 관리");
			}
			break;
			default:
				break;
			}
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

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
