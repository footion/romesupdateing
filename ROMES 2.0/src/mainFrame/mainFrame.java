package mainFrame;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Login.LoginPanel;
import Login.log_history;
import Login.login_management;
import Menu.MenuPanel;
import factory.componentFactory;
import factory.fontFactory;
import functions.drawLoginInfo;
import functions.searchBar;
import image.url;
import layoutSetting.basicPanel;
import layoutSetting.scrollFrame;
import page.storing.SortingPanel;
import pages.CommonCodePage;
import pages.ProcessManagement;
import pages.ProductionPlanManagement;
import pages.ReceivedOrderManagement;
import pages.companyManagement;
import pages.purchasePriceManagement;
import pages.order.ShowOrderPanelMaster;
import pages.product.ShowProductsPanel;

@SuppressWarnings("serial")
public class mainFrame extends scrollFrame{
	public static CardLayout card;
	public static Container Container;
	public static JLabel title;
	public static basicPanel MenuPanel;
	public static mainFrame mainframe;
	public static searchBar searchBar;
	public static functions.drawLoginInfo LoginInfo;
	componentFactory componentFactory;
	public mainFrame(String TITLE) {
		super(TITLE);
		mainframe= this;
		card=new CardLayout();
		Container =new Container();
		Container.setLayout(card);
		componentFactory= new componentFactory();
		
		Container c = new Container();
		Container.add(new LoginPanel(),"Login");
		Container.add(c=new ReceivedOrderManagement(),"ReceipedOrder");
		c.setName("ReceipedOrder");
		Container.add(new login_management(),"LoginManagement");
		Container.add(new log_history(),"LogHistory");
		Container.add(c=new companyManagement(),"CompanyManagement");
		c.setName("CompanyManagement");
		Container.add(c= new purchasePriceManagement(),"PurchasePriceManagement");
		c.setName("PurchasePriceManagement");
		Container.add(c= new ProductionPlanManagement(),"ProductionPlanManagement");
		c.setName("ProductionPlanManagement");
		Container.add(c= new CommonCodePage(),"CommonCodePage");
		c.setName("CommonCodePage");
		Container.add(c= new ProcessManagement(),"ProcessManagement");
		c.setName("ProcessManagement");
		Container.add(c=new ShowProductsPanel(),"ProductShow");
		Container.add(c=new SortingPanel(),"showStoring");
		Container.add(c=new ShowOrderPanelMaster(),"orderHistory");
		
		//titlePanel
		basicPanel Titlepanel = new basicPanel();
		title = new JLabel("ROMES");
		title.setFont(new Font(fontFactory.TITLE_FONT, Font.BOLD, 25));
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setBorder(new EmptyBorder(0,10,0,0));
		title.setVisible(false);
		
		basicPanel LogoPanel = new basicPanel();
		LogoPanel.setBorder(new EmptyBorder(7,10,0,-12));
		LogoPanel.add(new logo());
		
		
		searchBar=new searchBar(new String [] {"옵션"});
		searchBar.setBorder(new EmptyBorder(12,140,0,0));
		searchBar.setVisible(false);
		
		Titlepanel.setLayout(new BorderLayout(0,-17));
		Titlepanel.add(LoginInfo=new drawLoginInfo(),BorderLayout.NORTH);
		LoginInfo.setVisible(false);
		Titlepanel.add(title,BorderLayout.WEST);
		Titlepanel.add(searchBar,BorderLayout.CENTER);
		Titlepanel.setPreferredSize(new Dimension(0,67));
		
		MenuPanel = new basicPanel();
		MenuPanel menu = new MenuPanel();
		menu.setBorder(new EmptyBorder(10, 0, 16, 0));
		MenuPanel.add(menu);
		JScrollPane menuScroll = new JScrollPane(MenuPanel);
		menuScroll.getViewport().setBackground(Color.white);
		menuScroll.setBorder(null);
		menuScroll.setPreferredSize(new Dimension(140,319));
		
		basicPanel LeftPanel= new basicPanel();
		LeftPanel.setLayout(new BorderLayout());
		LeftPanel.add(LogoPanel,BorderLayout.NORTH);
		LeftPanel.add(menuScroll,BorderLayout.CENTER);
		LeftPanel.add(componentFactory.addEmptyLabel(),BorderLayout.PAGE_END);
		LeftPanel.setBorder(new LineBorder(Color.GRAY,2));
		basicPanel RightPanel = new basicPanel();
		RightPanel.setLayout(new BorderLayout());
		RightPanel.add(Titlepanel,BorderLayout.NORTH);
		RightPanel.add(Container,BorderLayout.CENTER);
		RightPanel.setBorder(new EmptyBorder(0,10,12,10));
		
		container.setBorder(new EmptyBorder(-5,0,-5,0));
		this.container.setLayout(new BorderLayout());
		this.container.add(LeftPanel,BorderLayout.WEST);
		this.container.add(RightPanel,BorderLayout.CENTER);
		
		this.setJMenuBar(new Menu.menuBar());
		//this.setUndecorated(true);
		this.setVisible(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		//MetalLookAndFeel.setCurrentTheme(new MyDefaultMetalTheme());
		
		//this.getRootPane().setWindowDecorationStyle(1);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage(url.LOGO);
        this.setIconImage(image);
        
        log_history.save_log("시스템 시작");
	}
//	class MyDefaultMetalTheme extends DefaultMetalTheme {
//		  public ColorUIResource getWindowTitleInactiveBackground() {
//		    return new ColorUIResource(java.awt.Color.blue);
//		  }
//
//		  public ColorUIResource getWindowTitleBackground() {
//		    return new ColorUIResource(java.awt.Color.blue);
//		  }
//
//		  public ColorUIResource getPrimaryControlHighlight() {
//		    return new ColorUIResource(java.awt.Color.blue);
//		  }
//
//		  public ColorUIResource getPrimaryControlDarkShadow() {
//		    return new ColorUIResource(java.awt.Color.blue);
//		  }
//
//		  public ColorUIResource getPrimaryControl() {
//		    return new ColorUIResource(java.awt.Color.blue);
//		  }
//
//		  public ColorUIResource getControlHighlight() {
//		    return new ColorUIResource(java.awt.Color.blue);
//		  }
//
//		  public ColorUIResource getControlDarkShadow() {
//		    return new ColorUIResource(java.awt.Color.blue);
//		  }
//
//		  public ColorUIResource getControl() {
//		    return new ColorUIResource(java.awt.Color.blue);
//		  }
//	}
}
