package registrationForm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import factory.colorFactory;
import factory.componentFactory;
import factory.miniTableFactory;
import factory.nameFactory;
import factory.stringFactory;
import functions.addRowLabel;
import functions.method;
import image.url;
import layoutSetting.basicBorderPanel;
import layoutSetting.basicIconBtn;
import layoutSetting.basicPanel;
import layoutSetting.basicTabbedPane;
import layoutSetting.basicTextField;
import layoutSetting.doubleButtonPanel;
import layoutSetting.ImageLabel;
import layoutSetting.miniTable;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import eventListener.R_PurchasePriceEvent;
import eventListener.infoBtnEvent_Company;
import eventListener.miniTableEvent_R_PurchasePrice;
import eventListener.textFieldEvent;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import java.awt.Color;

@SuppressWarnings("serial")
public class R_purchasePrice extends basicPanel{
	public basicTextField textField;
	public basicTextField textField_1;
	public basicTextField textField_2;
	public basicTextField textField_3;
	public basicTextField textField_4;
	public basicTextField name;
	public JLabel lblNewLabel;
	public JLabel lblNewLabel_1;
	public JLabel lblNewLabel_2;
	public JLabel lblNewLabel_3;
	public JLabel lblNewLabel_4;
	public JLabel lblNewLabel_5;
	TitledBorder tablePanelBorder;
	TitledBorder infoPanelBorder;
	basicPanel tablePanel;
	basicPanel infoPanel;
	public miniTable miniTable;
	basicIconBtn infoBtn;
	public addRowLabel addRowLabel;
	public JRadioButton companyRadio;
	public JRadioButton productRadio;
	private int CompanyKey;
	private int ProductKey;
	basicTabbedPane tabbedPane;
	componentFactory componentFactory;
	public ImageLabel imageLabel;
	public doubleButtonPanel buttonPanel;
	public R_purchasePrice(basicTabbedPane TabbedPane) {
		tabbedPane=TabbedPane;
		componentFactory=new componentFactory();
		//NorthPanel
		basicPanel northPanel = new basicPanel();
		northPanel.setBorder(new EmptyBorder(0,12,10,12));
		northPanel.setLayout(new BorderLayout());
		basicPanel radioBtnPanel = new basicPanel();
		radioBtnPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
		ButtonGroup group = new ButtonGroup();
		companyRadio = new JRadioButton("거래처별");
		companyRadio.setBackground(colorFactory.PANEL_COLOR);
		companyRadio.setSelected(true);
		companyRadio.addActionListener(radioBtnEvent());
		productRadio = new JRadioButton("제품별");
		productRadio.setBackground(colorFactory.PANEL_COLOR);
		productRadio.addActionListener(radioBtnEvent());
		group.add(companyRadio);
		group.add(productRadio);
		radioBtnPanel.add(companyRadio);
		radioBtnPanel.add(productRadio);
		basicBorderPanel northBorder= new basicBorderPanel();
		northBorder.setBorder(new CompoundBorder(new EmptyBorder(0, 5, 0, 5),new LineBorder(colorFactory.GRAY)));
		northBorder.setLayout(new FlowLayout(FlowLayout.LEADING));
		buttonPanel = new doubleButtonPanel("등록", "..",null);
		buttonPanel.leftBtn.setName(nameFactory.PURCHASEPRICE_BYCOMPANY_BN);
		northBorder.add(buttonPanel);
		
		northPanel.add(radioBtnPanel,BorderLayout.NORTH);
		northPanel.add(northBorder,BorderLayout.CENTER);
		
		//CenterPanel
		basicPanel centerPanel = new basicPanel();
		centerPanel.setLayout(new BorderLayout(0,8));
		centerPanel.setBorder(new EmptyBorder(0,15,0,15));
		infoPanel = new basicPanel();
		infoPanelBorder =new TitledBorder(new LineBorder(colorFactory.GRAY, 1),stringFactory.PURCHASEPRICE_COMPANYINFOBORDER, TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0));
		infoPanel.setBorder(new CompoundBorder(infoPanelBorder, new EmptyBorder(20, 20, 10, 20)));
		centerPanel.add(infoPanel,BorderLayout.CENTER);
		infoPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		lblNewLabel = FieldLabel(stringFactory.PURCHASEPRICE_COMPANYCLASSFICATION);
		infoPanel.add(lblNewLabel, "2, 1, right, default");
		
		textField = new basicTextField(10,"onlyView");
		infoPanel.add(textField, "4, 1, fill, default");
		textField.setColumns(10);
		
		lblNewLabel_1 = FieldLabel(stringFactory.PURCHASEPRICE_COMPANYCEONAME);
		infoPanel.add(lblNewLabel_1, "6, 1, right, default");
		
		textField_1 = new basicTextField(10,"onlyView");
		infoPanel.add(textField_1, "8, 1, fill, bottom");
		textField_1.setColumns(10);
		
		lblNewLabel_2 =FieldLabel(stringFactory.PURCHASEPRICE_COMPANYBUSSINESSNUMBER);
		infoPanel.add(lblNewLabel_2, "2, 3, right, default");
		
		textField_2 = new basicTextField(10,"onlyView");
		infoPanel.add(textField_2, "4, 3, fill, default");
		textField_2.setColumns(10);
		
		lblNewLabel_3 =FieldLabel(stringFactory.PURCHASEPRICE_COMPANYEMAIL);
		infoPanel.add(lblNewLabel_3, "6, 3, right, default");
		
		textField_3 = new basicTextField(10,"onlyView");
		infoPanel.add(textField_3, "8, 3, fill, default");
		textField_3.setColumns(10);
		
		lblNewLabel_4 = FieldLabel(stringFactory.PURCHASEPRICE_COMPANYADDRESS);
		infoPanel.add(lblNewLabel_4, "2, 5, right, default");
		
		textField_4 = new basicTextField(10,"onlyView");
		infoPanel.add(textField_4, "4, 5, fill, default");
		textField_4.setColumns(10);
		
		basicBorderPanel c_northBorder = new basicBorderPanel();
		c_northBorder.setBorder(new CompoundBorder(new EmptyBorder(0, 2, 0, 2),new LineBorder(colorFactory.GRAY)));
		centerPanel.add(c_northBorder, BorderLayout.NORTH);
		c_northBorder.setLayout(new BorderLayout());
		
		basicPanel namepanel = new basicPanel();
		namepanel.setBorder(new EmptyBorder(10, 30, 10, 40));
		c_northBorder.add(namepanel);
		namepanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(68dlu;default)"),},
			new RowSpec[] {
				RowSpec.decode("default:grow"),}));
		
		lblNewLabel_5 = FieldLabel(stringFactory.PURCHASEPRICE_COMPANYNAME);
		namepanel.add(lblNewLabel_5, "2, 1, right, default");
		
		name = new basicTextField(10,"onlyView");
		namepanel.add(name, "4, 1, 3, 1, fill, default");
		name.setColumns(10);
		name.setName(nameFactory.PURCHASEPRICE_CLIENT_TN);
		infoBtn = new basicIconBtn().setInfoBtn();
		infoBtn.addActionListener(new infoBtnEvent_Company(tabbedPane, this));
		namepanel.add(infoBtn,"8, 1");
		
		basicBorderPanel imagePanel = new basicBorderPanel();
		imagePanel.setBorder(new LineBorder(colorFactory.GRAY, 1));
		imagePanel.setPreferredSize(new Dimension(130,100));
		imageLabel = new ImageLabel(url.IMAGE_ICON);
		imageLabel.setImageSize(120, 90);
		imagePanel.add(imageLabel);
		namepanel.add(imagePanel, "14, 1, center, center");
		
		//PageEndPanel
		basicPanel pageEndPanel = new basicPanel();
		pageEndPanel.setLayout(new BorderLayout());
		pageEndPanel.setBorder(new EmptyBorder(0,15,5,15));
		tablePanel = new basicPanel();
		tablePanelBorder =new TitledBorder(new LineBorder(colorFactory.GRAY,1),stringFactory.PURCHASEPRICE_COMPANYPURCHASEPRODUCT);
		tablePanel.setBorder(new CompoundBorder(tablePanelBorder, new EmptyBorder(20, 20,5,20)));
		tablePanel.setLayout(new BorderLayout());
		miniTable = new miniTableFactory().PurchasePriceManagement_byCompany(tabbedPane);
		miniTable.table.addMouseListener(new miniTableEvent_R_PurchasePrice(miniTable, true));
		miniTable.setPreferredSize(new Dimension(0,200));
		tablePanel.add(miniTable,BorderLayout.CENTER);
		addRowLabel=new addRowLabel("+add a line", miniTable.model, new Object[] {"","+add a product","","","0",""});
		tablePanel.add(addRowLabel,BorderLayout.PAGE_END);
		pageEndPanel.add(tablePanel,BorderLayout.PAGE_END);
		
		setEvent();
		this.setLayout(new BorderLayout());
		this.add(northPanel,BorderLayout.NORTH);
		this.add(centerPanel,BorderLayout.CENTER);
		this.add(pageEndPanel,BorderLayout.PAGE_END);
		this.setBorder(new EmptyBorder(10,10,10,10));
		this.setVisible(true);
	}
	ActionListener radioBtnEvent() {
		ActionListener actionListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource()==companyRadio) {
					setCompanyType();
				}else if(e.getSource()==productRadio) {
					setProductType();
				}
			}
		};
		return actionListener;
		
	}
	public void setCompanykey(int key) {
		CompanyKey = key;
	}
	public int returnCompanyKey() {
		return CompanyKey;
	}
	public void setProductkey(int key) {
		ProductKey = key;
	}
	public int returnProductKey() {
		return ProductKey;
	}
	public void setEvent() {
		name.addMouseListener(new textFieldEvent(tabbedPane, this));
	}
	public void removeEvents() {
		for(MouseListener mouseListener:name.getMouseListeners()) {
			name.removeMouseListener(mouseListener);
		}
	}
	JLabel FieldLabel(String text) {
		return new JLabel(text+" :");
	}
	void ConvertType(boolean visible) {
		lblNewLabel_2.setVisible(visible);
		textField_2.setVisible(visible);
		lblNewLabel_3.setVisible(visible);
		textField_3.setVisible(visible);
		lblNewLabel_4.setVisible(visible);
		textField_4.setVisible(visible);
	}
	void changeBorderTitle(String tableBorder,String infoBorder) {
		tablePanelBorder.setTitle(tableBorder);
		tablePanel.setBorder(new CompoundBorder(tablePanelBorder, new EmptyBorder(20, 20,5,20)));
		infoPanelBorder.setTitle(infoBorder);
		infoPanel.setBorder(new CompoundBorder(infoPanelBorder, new EmptyBorder(20, 20, 10, 20)));
	}
	public void setCompanyType() {
		ConvertType(true);
		buttonPanel.leftBtn.setName(nameFactory.PURCHASEPRICE_BYCOMPANY_BN);
		name.setName(nameFactory.PURCHASEPRICE_CLIENT_TN);
		lblNewLabel.setText(stringFactory.PURCHASEPRICE_COMPANYCLASSFICATION);
		lblNewLabel_1.setText(stringFactory.PURCHASEPRICE_COMPANYCEONAME);
		lblNewLabel_2.setText(stringFactory.PURCHASEPRICE_COMPANYBUSSINESSNUMBER);
		lblNewLabel_3.setText(stringFactory.PURCHASEPRICE_COMPANYEMAIL);
		lblNewLabel_4.setText(stringFactory.PURCHASEPRICE_COMPANYADDRESS);
		lblNewLabel_5.setText(stringFactory.PURCHASEPRICE_COMPANYNAME);
		changeBorderTitle(stringFactory.PURCHASEPRICE_COMPANYPURCHASEPRODUCT,stringFactory.PURCHASEPRICE_COMPANYINFOBORDER);
		miniTable = new miniTableFactory().PurchasePriceManagement_byCompany(tabbedPane);
		miniTable.table.addMouseListener(new miniTableEvent_R_PurchasePrice(miniTable, true));
		miniTable.setPreferredSize(new Dimension(0,200));
		tablePanel.removeAll();
		tablePanel.add(miniTable,BorderLayout.CENTER);
		tablePanel.add(new addRowLabel("+add a line", miniTable.model, new Object[] {"","+add a product","","","0",""}),BorderLayout.PAGE_END);
		cleanInfoBtn();
		infoBtn.addActionListener(new infoBtnEvent_Company(tabbedPane, this));
		imageLabel.reset();
		imageLabel.setImageSize(120, 90);
		reset();
		method.refreshComponent(this);
	}
	public void setProductType() {
		ConvertType(false);
		buttonPanel.leftBtn.setName(nameFactory.PURCHASEPRICE_BYPRODUCT_BN);
		name.setName(nameFactory.PURCHASEPRICE_PRODUCT_TN);
		lblNewLabel.setText(stringFactory.PURCHASEPRICE_PRODUCTTYPE);
		lblNewLabel_1.setText(stringFactory.PURCHASEPRICE_PRODUCTLOCATION);
		lblNewLabel_5.setText(stringFactory.PURCHASEPRICE_PRODUCTNAME);
		changeBorderTitle(stringFactory.PURCHASEPRICE_PRODUCTCOMPANYLIST, stringFactory.PURCHASEPRICE_PRODUCTINFO);
		miniTable = new miniTableFactory().PurchasePriceManagement_byProduct(tabbedPane);
		miniTable.table.addMouseListener(new miniTableEvent_R_PurchasePrice(miniTable, false));
		miniTable.setPreferredSize(new Dimension(0,260));
		tablePanel.removeAll();
		tablePanel.add(miniTable,BorderLayout.CENTER);
		tablePanel.add(new addRowLabel("+add a line", miniTable.model, new Object[] {"","+add a company","","","0",""}),BorderLayout.PAGE_END);
		cleanInfoBtn();
		imageLabel.reset();
		imageLabel.setImageSize(120, 90);
		reset();
		method.refreshComponent(this);
	}
	void cleanInfoBtn() {
		for(ActionListener actionListener:infoBtn.getActionListeners()) {
			infoBtn.removeActionListener(actionListener);	
		}
	}
	void reset() {
		textField.setText("");
		textField_1.setText("");
		textField_2.setText("");
		textField_3.setText("");
		textField_4.setText("");
		name.setText("");
		setCompanykey(0);
		setProductkey(0);
	}
}