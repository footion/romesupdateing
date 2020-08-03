package gui;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;

import gui.menu.MenuContainingPanel;
import gui.menu.MenuJLabel;
import gui.menu.SubmenuJLabel;

public class MenuPanel extends ColoredPanel {
	private static ArrayList<MenuContainingPanel> menuContainingPanels = null;

	public static ArrayList<MenuContainingPanel> getMenuContainingPanels() {
		return menuContainingPanels;
	}

	public static void setMenuContainingPanels(ArrayList<MenuContainingPanel> menuContainingPanels) {
		MenuPanel.menuContainingPanels = menuContainingPanels;
	}

	public MenuPanel() {
		// TODO Auto-generated constructor stub
		// this.setBorder(BorderFactory.createLineBorder(Color.black));
		menuContainingPanels = new ArrayList<MenuContainingPanel>();

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		MenuContainingPanel emtpy1 = new MenuContainingPanel(" ");
		this.add(emtpy1);
		MenuContainingPanel emtpy2 = new MenuContainingPanel(" ");
		this.add(emtpy2);

		MenuContainingPanel menuImDataPanel = new MenuContainingPanel("기준정보관리");
		menuContainingPanels.add(menuImDataPanel);
		//menuImDataPanel.addsubMenu(MESCardLayout.ProductCreate);
		menuImDataPanel.addsubMenu(MESCardLayout.ListProduct);
		menuImDataPanel.addsubMenu(MESCardLayout.LocationShow);
		menuImDataPanel.addsubMenu(MESCardLayout.companyCreate);
		menuImDataPanel.addsubMenu(MESCardLayout.createStoring);
		menuImDataPanel.addsubMenu(MESCardLayout.showStoring);
		menuImDataPanel.addsubMenu(MESCardLayout.showOrderHsitory);
		menuImDataPanel.addsubMenu(MESCardLayout.CommonPage);
		this.add(menuImDataPanel);

		MenuContainingPanel menuProductDataPanel = new MenuContainingPanel("생산관리");
		menuContainingPanels.add(menuProductDataPanel);
		
		this.add(menuProductDataPanel);

//		MenuContainingPanel menuProcessDataPanel = new MenuContainingPanel("공정관리");

//		this.add(menuProcessDataPanel);
		MenuContainingPanel menuWorkDataPanel = new MenuContainingPanel("공정관리");
		menuContainingPanels.add(menuWorkDataPanel);
		
		this.add(menuWorkDataPanel);

		MenuContainingPanel menuConditionDataPanel = new MenuContainingPanel("품질관리");
		menuContainingPanels.add(menuConditionDataPanel);
		this.add(menuConditionDataPanel);

		MenuContainingPanel menuMonitoringPanel = new MenuContainingPanel("모니터링");
		menuContainingPanels.add(menuMonitoringPanel);
		this.add(menuMonitoringPanel);

		MenuContainingPanel menuSystemPanel = new MenuContainingPanel("시스템관리");
		menuContainingPanels.add(menuSystemPanel);
		this.add(menuSystemPanel);

		//MenuContainingPanel printingReportPanel = new MenuContainingPanel("보고서출력");
		//menuContainingPanels.add(printingReportPanel);
		//printingReportPanel.addsubMenu("보고서예시출력");

		settingMouseListenerToMenuPanels();

		//this.add(printingReportPanel);

	}

	MouseListener mouseListener;

	private void settingMouseListenerToMenuPanels() {
		// TODO Auto-generated method stub
		mouseListener = new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource() instanceof MenuJLabel) {
					MenuJLabel clickedMenuLabel = (MenuJLabel)e.getSource();
					
					for(MenuContainingPanel menuPanel : menuContainingPanels) {
						MenuJLabel eachMenuLabel=menuPanel.getMenuJLabel();
						if(clickedMenuLabel == eachMenuLabel) {
							//open
							if (menuPanel.flagVisibleSubmenu == false) {
								menuPanel.flagVisibleSubmenu = true;
								Font font = new Font("휴먼엑스포", Font.BOLD, 19);
								eachMenuLabel.setFont(font);
								for (SubmenuJLabel submenuJLabel : menuPanel.getSubMarrayList()) {
									submenuJLabel.setVisible(true);
								}
							}else {
								Font font = new Font("휴먼엑스포", Font.BOLD, 17);
								eachMenuLabel.setFont(font);
								menuPanel.flagVisibleSubmenu = false;
								for (SubmenuJLabel submenuJLabel : menuPanel.getSubMarrayList()) {
									submenuJLabel.setVisible(false);
								}
							}
						}else {
							Font font = new Font("휴먼엑스포", Font.BOLD, 17);
							eachMenuLabel.setFont(font);
							menuPanel.flagVisibleSubmenu = false;
							for (SubmenuJLabel submenuJLabel : menuPanel.getSubMarrayList()) {
								submenuJLabel.setVisible(false);
							}
						}
						menuPanel.repaint();
					}
					
				}
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		};
		for (MenuContainingPanel menuPanel : menuContainingPanels) {
			menuPanel.settingMouseListener(mouseListener);
		}

	}

}
