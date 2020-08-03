package registrationForm;

import layoutSetting.basicBorderPanel;
import layoutSetting.basicBtn;
import layoutSetting.basicIconBtn;
import layoutSetting.basicLabel;
import layoutSetting.basicPanel;
import layoutSetting.basicTabbedPane;
import layoutSetting.basicTextArea;
import layoutSetting.colonLabel;
import layoutSetting.miniTable;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

import factory.colorFactory;
import factory.fontFactory;
import factory.miniTableFactory;
import factory.nameFactory;
import functions.addRowLabel;
import functions.datePickerBar;
import functions.totalPrice;
import hibernate.hibernate;
import image.url;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import Dao.CompanyDAO;
import Login.LoginPanel;
import entity.Company;
import entity.Login;
import entity.Received_order_history;
import eventListener.R_ReceivedOrderEvent;
import eventListener.cancelEvent;
import eventListener.infoBtnEvent_Company;
import eventListener.setTitleEvent;
import eventListener.textFieldEvent;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.TitledBorder;

import org.hibernate.Session;

import javax.swing.UIManager;

@SuppressWarnings("serial")
public class R_receivedOrder extends basicPanel{
	private JTextField titleText;
	private JTextField managerText;
	private JComboBox orderTypeCombo;
	private JTextField companyText;
	private JTextField orderDateText;
	private JTextField deadLineText;
	private basicTextArea remarksText;
	private miniTable minitable;
	private basicPanel dataPanel;
	int quantityColNum =7;
	int priceColNum =8;
	public static totalPrice totalPrice;
	basicTabbedPane TabbedPane;
	private int companyKey=0;
	int locationheight=2;
	basicBtn saveBtn;
	basicBtn cancelBtn;
	private basicIconBtn userInfoBtn;
	private basicIconBtn companyInfoBtn;
	public R_receivedOrder(basicTabbedPane tabbedPane) {
		TabbedPane= tabbedPane;
		setLayout(new BorderLayout(0,7));
		//NorthPanel
		basicBorderPanel northPanel = new basicBorderPanel(colorFactory.GRAY,1);
		northPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
		saveBtn = new basicBtn("저장");
		cancelBtn = new basicBtn("취소");
		northPanel.add(saveBtn);
		northPanel.add(cancelBtn);
		add(northPanel, BorderLayout.NORTH);
		//CenterPanel
		basicPanel centerPanel = new basicPanel();
		add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(0, 5));
		
		basicPanel titlePanel = new basicPanel();
		titlePanel.setBorder(new CompoundBorder(new LineBorder(new Color(128, 128, 128)), new EmptyBorder(7, 5, 7, 5)));
		centerPanel.add(titlePanel, BorderLayout.NORTH);
		titlePanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(64dlu;default)"),},
			new RowSpec[] {
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel titleLabel = new JLabel("\uC218\uC8FC \uBC88\uD638 :");
		titleLabel.setFont(new Font("굴림", Font.BOLD, 25));
		titlePanel.add(titleLabel, "2, 1, right, default");
		
		titleText = new JTextField();
		titleText.setFont(new Font("휴먼엑스포", Font.BOLD, 45));
		titleText.setForeground(Color.LIGHT_GRAY);
		titleText.setBorder(null);
		titleText.setHorizontalAlignment(SwingConstants.CENTER);
		titleText.setText("OL200724150215");
		titlePanel.add(titleText, "4, 1, fill, default");
		
		dataPanel = new basicPanel();
		dataPanel.setBorder(new CompoundBorder(new TitledBorder(new LineBorder(colorFactory.GRAY,1),"수주 정보"), new EmptyBorder(15, 5, 5, 5)));
		centerPanel.add(dataPanel, BorderLayout.CENTER);
		dataPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel managerLabel = new JLabel("\uB2F4\uB2F9\uC790 :");
		dataPanel.add(managerLabel, "3, "+locationheight+", right, default");
		
		managerText = new JTextField();
		dataPanel.add(managerText, "5, "+locationheight+", fill, default");
		managerText.setColumns(10);
		
		userInfoBtn = new basicIconBtn(url.LINK_ICON, 14, 14);
		userInfoBtn.setClean(false, false, true);
		userInfoBtn.setPreferredSize(new Dimension(25, 25));
		userInfoBtn.setToolTipText("Info");
		dataPanel.add(userInfoBtn, "6, 2");
		
		JLabel orderDateLabel = new JLabel("\uC8FC\uBB38 \uB0A0\uC9DC :");
		dataPanel.add(orderDateLabel, "10, 2, right, default");
	
		orderDateText = new JTextField();
		dataPanel.add(orderDateText, "12, 2, fill, default");
		
		JLabel orderTypeLabel = new JLabel("\uACC4\uC57D \uC720\uD615 :");
		dataPanel.add(orderTypeLabel, "3, "+(locationheight+2)+", right, default");
		
		orderTypeCombo = orderType();
		dataPanel.add(orderTypeCombo, "5, "+(locationheight+2)+", fill, default");
		
		JLabel deadLineLabel = new JLabel("완료 기한 :");
		dataPanel.add(deadLineLabel, "10, 4, right, default");
		
		datePickerBar datePickerBar = new datePickerBar();
		deadLineText = datePickerBar.text;
		dataPanel.add(datePickerBar, "12, 4, fill, default");
		
		JLabel companyLabel = new JLabel("\uC8FC\uBB38 \uC5C5\uCCB4 :");
		dataPanel.add(companyLabel, "3, "+(locationheight+4)+", right, default");
		
		companyText = new JTextField();
		dataPanel.add(companyText, "5, "+(locationheight+4)+", fill, default");
		companyText.setColumns(10);
		
		companyInfoBtn = new basicIconBtn(url.LINK_ICON, 14, 14);
		companyInfoBtn.setClean(false, false, true);
		companyInfoBtn.setPreferredSize(new Dimension(25, 25));
		companyInfoBtn.setToolTipText("Info");
		dataPanel.add(companyInfoBtn, "6, 6");
		
		JLabel remarksLabel = new JLabel("\uBE44\uACE0\uB780 :");
		dataPanel.add(remarksLabel, "10, 6");
		
		remarksText = new basicTextArea(null,2,10);
		dataPanel.add(remarksText, "12, 6, fill, default");
		
		basicPanel pageEndPanel = new basicPanel();
		pageEndPanel.setBorder(new CompoundBorder(new TitledBorder(new LineBorder(colorFactory.GRAY,1),"제품 정보"), new EmptyBorder(5, 5, 0, 5)));
		centerPanel.add(pageEndPanel, BorderLayout.SOUTH);
		pageEndPanel.setLayout(new BorderLayout());
		
		minitable = new miniTableFactory().ReceivedOrderProductTable();
		minitable.setPreferredSize(new Dimension(0,320));
		pageEndPanel.add(minitable,BorderLayout.NORTH);
		//TotalPrice
		totalPrice =new totalPrice(minitable.table, priceColNum,quantityColNum);
		pageEndPanel.add(totalPrice,BorderLayout.EAST);
		//add a line label
		pageEndPanel.add(new addRowLabel(" +add a line", minitable.model,new Object[] {"","+add a model","","","","","","0","0","",""}),BorderLayout.WEST);
		
		setBorder(new EmptyBorder(10,10,10,10));
		setEvent();
		settingTextFields();
	}
	JComboBox orderType() {
		String[] colcombo = {"","견적 요청","주문 확정"};
		JComboBox combo = new JComboBox();
		combo = new JComboBox(colcombo);
		BevelBorder bevelBorder = new BevelBorder(BevelBorder.LOWERED);
		combo.setBorder(bevelBorder);
		//combo center align
		DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();
	    listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
	    combo.setRenderer(listRenderer);
		combo.setSelectedIndex(0);
		combo.setBackground(colorFactory.TEXTFIELD_COLOR);
		return combo;
	}
	void settingTextFields() {
		//managerText
		Session session=hibernate.factory.openSession();
		Login login = session.get(Login.class, LoginPanel.ID);
		String manager=login.getName()+" "+login.getPosition();
		managerText.setText(manager);
		managerText.setEditable(false);
		//orderDateText
		Date today = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy. MM. dd. hh:mm:ss");
		orderDateText.setText(format.format(today));
		orderDateText.setEditable(false);
		//companyText
		companyText.setName(nameFactory.ORDER_CLIENT_TN);
		companyText.setEditable(false);
	}
	void setEvent() {
		cancelBtn.addActionListener(new cancelEvent(TabbedPane,this));
		saveBtn.addActionListener(new R_ReceivedOrderEvent(this));
		companyText.addMouseListener(new textFieldEvent(TabbedPane,this));
		companyInfoBtn.addActionListener(new infoBtnEvent_Company(TabbedPane, this));
	}
	public void setCompanykey(int key) {
		companyKey = key;
	}
	public int returnCompanyKey() {
		return companyKey;
	}
	public void setOrderData(Received_order_history data) {
		titleText.setText(data.getTitle());
		managerText.setText(data.getManager());;
		orderTypeCombo.setSelectedItem(data.getType());
		companyText.setText(data.getOrdered_company().getCompanyName());
		orderDateText.setText(data.getOrder_date());
		deadLineText.setText(data.getDeadline());
		remarksText.textArea.setText(data.getRemarks());
	}
	public void updateData(Received_order_history data,Session session) {
		data.setTitle(titleText.getText());
		data.setManager(managerText.getText());
		data.setType((String) orderTypeCombo.getSelectedItem());
		data.setOrdered_company(session.get(Company.class, returnCompanyKey()));
		data.setOrder_date(orderDateText.getText());
		data.setDeadline(deadLineText.getText());
		data.setRemarks(remarksText.textArea.getText());
	}
	public Received_order_history getOrderData(Session session) {
		Received_order_history data = new Received_order_history();
		data.setTitle(titleText.getText());
		data.setManager(managerText.getText());
		data.setType((String) orderTypeCombo.getSelectedItem());
		data.setOrdered_company(session.get(Company.class, returnCompanyKey()));
		data.setOrder_date(orderDateText.getText());
		data.setDeadline(deadLineText.getText());
		data.setRemarks(remarksText.textArea.getText());
		return data;
	}
	public basicTabbedPane getTabbedPane() {
		return TabbedPane;
	}
	public JTextField getTitleText() {
		return titleText;
	}
	public JTextField getManagerText() {
		return managerText;
	}
	public JComboBox getOrderTypeCombo() {
		return orderTypeCombo;
	}
	public JTextField getCompanyText() {
		return companyText;
	}
	public JTextField getOrderDateText() {
		return orderDateText;
	}
	public JTextField getDeadLineText() {
		return deadLineText;
	}
	public JTextArea getRemarksText() {
		return remarksText.textArea;
	}
	public miniTable getMinitable() {
		return minitable;
	}
	public basicPanel getDataPanel() {
		return dataPanel;
	}
	public basicBtn getSaveBtn() {
		return saveBtn;
	}
	public basicBtn getCancelBtn() {
		return cancelBtn;
	}
}
