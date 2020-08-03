//package registrationForm;
//
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Component;
//import java.awt.Container;
//import java.awt.Dimension;
//import java.awt.FlowLayout;
//import java.awt.Font;
//import java.awt.List;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import javax.swing.BorderFactory;
//import javax.swing.Box;
//import javax.swing.BoxLayout;
//import javax.swing.DefaultListCellRenderer;
//import javax.swing.JComboBox;
//import javax.swing.JLabel;
//import javax.swing.JTabbedPane;
//import javax.swing.JTextField;
//import javax.swing.border.BevelBorder;
//import javax.swing.border.CompoundBorder;
//import javax.swing.border.EmptyBorder;
//import javax.swing.border.LineBorder;
//import javax.swing.border.TitledBorder;
//
//import org.hibernate.Session;
//
//import Login.LoginPanel;
//import entity.Login;
//import eventListener.cancelEvent;
//import eventListener.infoBtnEvent_Company;
//import eventListener.R_ReceivedOrderEvent;
//import eventListener.setTitleEvent;
//import eventListener.textFieldEvent;
//import factory.colorFactory;
//import factory.componentFactory;
//import factory.fontFactory;
//import factory.miniTableFactory;
//import factory.nameFactory;
//import factory.stringFactory;
//import functions.addRowLabel;
//import functions.datePickerBar;
//import functions.method;
//import functions.totalPrice;
//import hibernate.hibernate;
//import image.url;
//import layoutSetting.basicBorderPanel;
//import layoutSetting.doubleButtonPanel;
//import layoutSetting.basicIconBtn;
//import layoutSetting.UI_Button;
//import layoutSetting.basicLabel;
//import layoutSetting.basicPanel;
//import layoutSetting.basicTabbedPane;
//import layoutSetting.basicTextArea;
//import layoutSetting.basicTextField;
//import layoutSetting.colonLabel;
//import layoutSetting.emptyLabel;
//import layoutSetting.miniTable;
//import layoutSetting.scrollFrame;
//import layoutSetting.textBar;
//import layoutSetting.titleBorderPanel;
//import setTableCell.columnWidth;
//import setTableCell.iconBtnCell;
//
//@SuppressWarnings("serial")
//public class R_receivedOrder extends basicPanel{
//	int TEXT_SIZE = 17;
//	R_receivedOrder orderFrame;
//	componentFactory containerFactory;
//	public basicLabel orderTitle;
//	JComboBox combo;
//	public basicPanel northPanel;
//	public Box leftBox;
//	public Box rightBox;
//	public doubleButtonPanel doubleButtonPanel;
//	public miniTable minitable;
//	int quantityColNum =7;
//	int priceColNum =8;
//	public static totalPrice totalPrice;
//	basicTabbedPane TabbedPane;
//	private int companyKey=0;
//	public R_receivedOrder(basicTabbedPane tabbedPane) {
//		orderFrame = this;
//		containerFactory = new componentFactory();
//		TabbedPane =tabbedPane;
//		//NorthPanel
//		northPanel = new basicPanel();
//		northPanel.setLayout(new BorderLayout(0,-10));
//		northPanel.setBorder(new EmptyBorder(10,10,0,10));
//		basicLabel category = new basicLabel(stringFactory.ORDER_CATEGORY);
//		category.setName(nameFactory.ORDER_CATEGORY_TN);
//		 //title
//		SimpleDateFormat format = new SimpleDateFormat("ddMMyy-hhmmss");
//		Date date = new Date();
//		String ordertitle ="OL"+format.format(date);
//		orderTitle = new basicLabel(ordertitle);
//		orderTitle.setName(nameFactory.ORDER_TITLE_TN);
//		orderTitle.setFont(new Font(fontFactory.BASIC_FONT,Font.BOLD,60));
//		orderTitle.setForeground(Color.LIGHT_GRAY);
//		northPanel.add(category,BorderLayout.NORTH);
//		northPanel.add(orderTitle,BorderLayout.WEST);
//		
//		//CenterPanel
//		basicBorderPanel centerPanel = new basicBorderPanel();
//		centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER,90,60));
//		//Center_left
//		leftBox = Box.createVerticalBox();
//		textBar RepresentativeBar=new textBar(stringFactory.ORDER_REPRESENTATIVE, TEXT_SIZE);
//		RepresentativeBar.textField.setName(nameFactory.ORDER_REPRESENTATIVE_TN);
//		RepresentativeBar.setEventBar();
//		Session session = hibernate.factory.openSession();
//		Login login = session.get(Login.class, LoginPanel.ID);
//		String representative=login.getName()+" "+login.getPosition();
//		RepresentativeBar.textField.setText(representative);
//		containerFactory.addInfoBtn(RepresentativeBar,stringFactory.TYPE_USER,(JTextField)method.findOutComponent(RepresentativeBar, nameFactory.ORDER_REPRESENTATIVE_TN),TabbedPane);
//		leftBox.add(RepresentativeBar);
//		leftBox.add(Box.createVerticalStrut(10));
//		basicPanel orderTypeBar =(basicPanel) orderType();
//		orderTypeBar.setBorder(new EmptyBorder(0,0,0,27));
//		leftBox.add(orderTypeBar);
//		leftBox.add(Box.createVerticalStrut(10));
//		basicPanel clientBar = (basicPanel) containerFactory.createEventInputBar(stringFactory.ORDER_CLIENT, TEXT_SIZE,nameFactory.ORDER_CLIENT_TN,TabbedPane,this);
//		basicIconBtn infoBtn=new basicIconBtn().setInfoBtn();
//		infoBtn.addActionListener(new infoBtnEvent_Company(TabbedPane, this));
//		clientBar.add(infoBtn);
//		leftBox.add(clientBar);
//		//Center_right
//		rightBox = Box.createVerticalBox();
//		basicPanel orderDateBar = (basicPanel) containerFactory.createTodayBar(stringFactory.ORDER_ORDERDATE,TEXT_SIZE,nameFactory.ORDER_TODAY_TN);
//		rightBox.add(orderDateBar);
//		rightBox.add(Box.createVerticalStrut(10));
//		datePickerBar deadlineBar = new datePickerBar(stringFactory.ORDER_DEADLINE, TEXT_SIZE,nameFactory.ORDER_DEADLINE_TN);
//		rightBox.add(deadlineBar);
//		rightBox.add(Box.createVerticalStrut(10));
//		rightBox.add(containerFactory.createRemarks(stringFactory.ORDER_REMARKS,nameFactory.ORDER_REMARKS_TN,2,TEXT_SIZE+2));
//		
//
//		centerPanel.add(leftBox,BorderLayout.WEST);
//		centerPanel.add(rightBox,BorderLayout.EAST);
//		
//		//pageEndPanel
//		basicPanel pageEndPanel = new basicPanel();
//		pageEndPanel.setLayout(new BorderLayout(20,0));
//		pageEndPanel.setBorder(new EmptyBorder(0,10,0,10));
//		//pageEnd_Center
//		titleBorderPanel pageEnd_Center = new titleBorderPanel("Products");
//		pageEnd_Center.setLayout(new BorderLayout());
//		//miniTable
//		minitable = new miniTableFactory().ReceivedOrderProductTable();
//		pageEnd_Center.add(minitable,BorderLayout.NORTH);
//		//TotalPrice
//		totalPrice =new totalPrice(minitable.table, priceColNum,quantityColNum);
//		pageEnd_Center.add(totalPrice,BorderLayout.EAST);
//		//add a line label
//		pageEnd_Center.add(new addRowLabel(" +add a line", minitable.model,new Object[] {"","+add a model","","","","","","0","0","",""}),BorderLayout.WEST);
//		pageEndPanel.add(pageEnd_Center,BorderLayout.CENTER);
//		pageEndPanel.add(containerFactory.addEmptyLabel(),BorderLayout.EAST);
//		pageEndPanel.add(containerFactory.addEmptyLabel(),BorderLayout.WEST);
//		//save,cancel btn
//		doubleButtonPanel = new doubleButtonPanel("Save", "Cancel");
//		pageEndPanel.add(doubleButtonPanel,BorderLayout.PAGE_END);
//		
//		setEvent();
//		this.setLayout(new BorderLayout(30,20));
//		this.add(northPanel,BorderLayout.NORTH);
//		this.add(centerPanel,BorderLayout.CENTER);
//		this.add(pageEndPanel,BorderLayout.PAGE_END);
//		this.add(containerFactory.addEmptyLabel(),BorderLayout.EAST);
//		this.add(containerFactory.addEmptyLabel(),BorderLayout.WEST);
//		this.setVisible(true);
//	}
//	Container orderType() {
//		String[] colcombo = {"","견적 요청","주문 확정"};
//		combo = new JComboBox(colcombo);
//		combo.setName(nameFactory.ORDER_TYPE_TN);
//		BevelBorder bevelBorder = new BevelBorder(BevelBorder.LOWERED);
//		combo.setBorder(bevelBorder);
//		//combo center align
//		DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();
//	    listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
//	    combo.setRenderer(listRenderer);
//	    combo.setFont(new Font(fontFactory.BASIC_FONT,Font.PLAIN,TEXT_SIZE+1));
//		combo.setSelectedIndex(0);
//		combo.setBackground(colorFactory.TEXTFIELD_COLOR);
//		combo.setPreferredSize(new Dimension(275,30));
//		basicPanel panel = new basicPanel();
//		basicLabel label = new basicLabel("계약 유형");
//		label.setFont(new Font(fontFactory.BASIC_FONT,Font.BOLD,TEXT_SIZE+2));
//		panel.add(label);
//		panel.add(new colonLabel());
//		panel.add(combo);
//		return panel;
//	}
//	void setEvent() {
//		orderTitle.addMouseListener(new setTitleEvent(orderTitle,"title"));
//		doubleButtonPanel.rightBtn.addActionListener(new cancelEvent(TabbedPane,this));
//		doubleButtonPanel.leftBtn.addActionListener(new R_ReceivedOrderEvent(this));
//	}
//
//	public void setCompanykey(int key) {
//		companyKey = key;
//	}
//	public int returnCompanyKey() {
//		return companyKey;
//	}
//}
