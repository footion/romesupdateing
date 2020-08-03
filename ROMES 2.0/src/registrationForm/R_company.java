package registrationForm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import eventListener.bottomScrollEvent;
import eventListener.cancelEvent;
import eventListener.ImageSelectEvent;
import eventListener.R_CompanyEvent;
import eventListener.setTitleEvent;
import factory.colorFactory;
import factory.componentFactory;
import factory.fontFactory;
import factory.nameFactory;
import factory.stringFactory;
import functions.addProfileLabel;
import image.url;
import layoutSetting.basicBorderPanel;
import layoutSetting.basicLabel;
import layoutSetting.basicPanel;
import layoutSetting.basicTabbedPane;
import layoutSetting.colonLabel;
import layoutSetting.doubleButtonPanel;
import layoutSetting.ImageLabel;
import layoutSetting.titleBorderPanel;

@SuppressWarnings("serial")
public class R_company extends basicPanel{
	public doubleButtonPanel doubleButtonPanel;
	componentFactory componentFactory;
	int textSize =17;
	//basicLabel companyClassification;
	public basicLabel companyName;
	public basicPanel titlePanel;
	public Box leftBox;
	public Box rightBox;
	public basicPanel profilePanel;
	public int imageWidth=185;
	public int imageHeight=140;
	basicTabbedPane TabbedPane;
	private ImageLabel imageLabel;
	
	public R_company(basicTabbedPane tabbedPane) {
		TabbedPane=tabbedPane;
		componentFactory = new componentFactory();
		//NorthPanel
		basicPanel northPanel = new basicPanel();
		northPanel.setLayout(new BorderLayout());
		
		titlePanel = new basicPanel();
		companyName = new basicLabel(stringFactory.COMPANY_NAME);
		companyName.setName(nameFactory.COMPANY_NAME_N);
		setTitleFont(companyName);
		basicLabel CN_label = new basicLabel("회사명 : ");
		setTitleFont(CN_label);
		
		titlePanel.add(CN_label);
		titlePanel.add(companyName);
		//ImagePanel
		basicPanel parentPanel=new basicPanel();
		parentPanel.setLayout(new BorderLayout(60,10));
		basicBorderPanel imagePanel = new basicBorderPanel();
		imagePanel.setBorder(new LineBorder(colorFactory.BORDER_COLOR, 1));
		parentPanel.add(componentFactory.addEmptyLabel(),BorderLayout.NORTH);
		parentPanel.add(componentFactory.addEmptyLabel(),BorderLayout.EAST);
		imageLabel = new ImageLabel(url.IMAGE_ICON);
		imageLabel.setImageSize(imageWidth, imageHeight);
		imagePanel.add(imageLabel);
		parentPanel.add(imagePanel,BorderLayout.CENTER);
		
		northPanel.add(titlePanel,BorderLayout.WEST);
		northPanel.add(parentPanel,BorderLayout.EAST);
		//CenterPanel
		basicPanel centerPanel = new basicPanel();
		centerPanel.setLayout(new FlowLayout(FlowLayout.LEADING,70,10));
		//Center_leftBox
		leftBox = Box.createVerticalBox();
		leftBox.add(ClassificationBar());
		leftBox.add(createInputBar(stringFactory.COMPANY_CEONAME, textSize, nameFactory.COMPANY_CEONAME_TN));
		leftBox.add(createInputBar(stringFactory.COMPANY_BUSINESSNUMBER, textSize, nameFactory.COMPANY_BUSINESSNUMBER_TN));
		leftBox.add(createInputBar(stringFactory.COMPANY_INDUSTRYTYPE, textSize, nameFactory.COMPANY_INDUSTRYTYPE_TN));
		leftBox.add(createInputBar(stringFactory.COMPANY_INDUSTRYTYPE_DETAIL,textSize,nameFactory.COMPANY_INDUSTRYTYPE_DETAIL_TN));
		leftBox.add(createInputBar(stringFactory.COMPANY_ADDRESS, textSize, nameFactory.COMPANY_ADDRESS_TN));
		//Center_rightBox
		rightBox = Box.createVerticalBox();
		rightBox.add(createInputBar(stringFactory.COMPANY_FAX, textSize, nameFactory.COMPANY_FAX_TN));
		rightBox.add(createInputBar(stringFactory.COMPANY_PHONE, textSize, nameFactory.COMPANY_PHONE_TN));
		rightBox.add(createInputBar(stringFactory.COMPANY_EMAIL, textSize, nameFactory.COMPANY_EMAIL_TN));
		rightBox.add(createInputBar(stringFactory.COMPANY_ACCOUNTNUMBER, textSize, nameFactory.COMPANY_ACCOUNTNUMBER_TN));
		rightBox.add(componentFactory.createRemarks(stringFactory.COMPANY_REMARKS,nameFactory.COMPANY_REMARKS_TN, 2, textSize+2));
		
		centerPanel.add(leftBox);
		centerPanel.add(rightBox);
		
		//PageEndPanel
		basicPanel pageEndPanel = new basicPanel();
		pageEndPanel.setPreferredSize(new Dimension(0, 210));
		pageEndPanel.setLayout(new BorderLayout(20,0));
		pageEndPanel.add(new JLabel(""),BorderLayout.EAST);
		pageEndPanel.add(new JLabel(""),BorderLayout.WEST);
		//ManagerPanel
		titleBorderPanel managerPanel = new titleBorderPanel("담당자");
		managerPanel.setLayout(new BorderLayout(0,-5));
		//profilePanel
		profilePanel = (basicPanel) componentFactory.profilePanel();
		JScrollPane profilePane = new JScrollPane(profilePanel);
		profilePane.setBorder(null);
		//addProfile_Label
		addProfileLabel addProfileLabel = new addProfileLabel(profilePanel);
		addProfileLabel.addMouseListener(new bottomScrollEvent(profilePane));
		addProfileLabel.setHorizontalAlignment(JLabel.LEFT);
		
		managerPanel.add(componentFactory.addEmptyLabel(),BorderLayout.NORTH);
		managerPanel.add(profilePane,BorderLayout.CENTER);
		managerPanel.add(addProfileLabel,BorderLayout.PAGE_END);
		
		pageEndPanel.add(managerPanel,BorderLayout.CENTER);
		pageEndPanel.add(doubleButtonPanel=new doubleButtonPanel("Save", "Cancel"),BorderLayout.PAGE_END);
		
		setEvent();
		this.setLayout(new BorderLayout(0,-5));
		this.add(northPanel,BorderLayout.NORTH);
		this.add(centerPanel,BorderLayout.CENTER);
		this.add(pageEndPanel,BorderLayout.PAGE_END);
		this.setBorder(new EmptyBorder(20,20,20,20));
		this.setVisible(true);
	}
	Container ClassificationBar() {
		String[] colcombo = {"","거래처","고객사","거래처 및 고객사","화물운송사"};
		JComboBox combo = new JComboBox(colcombo);
		combo.setName(nameFactory.COMPANY_CLASSIFICATION_N);
		BevelBorder bevelBorder = new BevelBorder(BevelBorder.LOWERED);
		combo.setBorder(bevelBorder);
		//combo center align
		DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();
	    listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
	    combo.setRenderer(listRenderer);
	    
		combo.setSelectedIndex(0);
		combo.setBackground(colorFactory.TEXTFIELD_COLOR);
		combo.setFont(new Font(fontFactory.BASIC_FONT,Font.PLAIN,textSize+1));
		combo.setPreferredSize(new Dimension(275,30));
		basicPanel panel = new basicPanel();
		basicLabel label = new basicLabel("회사 구분");
		label.setFont(new Font(fontFactory.BASIC_FONT,Font.BOLD,textSize+2));
		panel.add(label);
		panel.add(new colonLabel());
		panel.add(combo);
		return panel;
	}
	void setEvent() {
		imageLabel.addMouseListener(new ImageSelectEvent(this,imageLabel, 180, 140));
		companyName.addMouseListener(new setTitleEvent(companyName,"name"));
		doubleButtonPanel.rightBtn.addActionListener(new cancelEvent(TabbedPane,this));
		doubleButtonPanel.leftBtn.addActionListener(new R_CompanyEvent(this,TabbedPane));
	}
	void setTitleFont(JLabel label) {
		label.setFont(new Font(fontFactory.BASIC_FONT,Font.BOLD,60));
		label.setForeground(Color.LIGHT_GRAY);
	}
	Container createInputBar(String labelText,int textSize,String textName) {
		basicPanel panel =(basicPanel) componentFactory.createInputBar(labelText, textSize, textName);
		return panel;
	}
	public ImageLabel getImageLabel() {
		return imageLabel;
	}
	public void setImageLabel(ImageLabel imageLabel) {
		this.imageLabel = imageLabel;
	}
}
