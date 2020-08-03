package registrationForm;

import layoutSetting.WhiteCombo;
import layoutSetting.basicBtn;
import layoutSetting.basicFrame;
import layoutSetting.basicPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import Dao.ProductDAO;
import entity.Product;
import eventListener.R_ROProductEvent;
import factory.colorFactory;
import functions.method;

import javax.swing.JButton;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTextField;

public class R_ROProduct extends basicFrame{
	Color borderColor = colorFactory.GRAY;
	private JTextField totalLengthText;
	JComboBox couplingBodyCombo;
	JComboBox couplingCeramicCombo;
	public WhiteCombo productGroupCombo;
	public WhiteCombo productTypeCombo;
	public WhiteCombo materialClassCombo;
	WhiteCombo tubeLengthCombo;
	WhiteCombo frontFusionCombo;
	WhiteCombo backFusionCombo;
	public basicPanel optionPanel;
	public basicPanel couplingPanel;
	public basicPanel fusionPanel;
	public basicPanel totalLengthPanel;
	public basicBtn editBtn;
	public basicBtn resetBtn;
	public basicBtn cancelBtn;
	public static void main(String[] args) {
		new R_ROProduct("");
	}
	public R_ROProduct(String title) {
		super(title);
		basicPanel mainPanel = new basicPanel(null);
		mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		mainPanel.setLayout(new BorderLayout(0, 7));
		
		basicPanel northPanel = new basicPanel(null);
		northPanel.setBorder(new CompoundBorder(new LineBorder(borderColor), new EmptyBorder(2, 2, 2, 2)));
		mainPanel.add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
		
		editBtn = new basicBtn("\uB4F1\uB85D");
		northPanel.add(editBtn);
		
		resetBtn = new basicBtn("\uCD08\uAE30\uD654");
		northPanel.add(resetBtn);
		
		cancelBtn = new basicBtn("\uCDE8\uC18C");
		northPanel.add(cancelBtn);
		
		basicPanel centerPanel = new basicPanel(null);;
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(0, 7));
		
		basicPanel categoryPanel = new basicPanel(null);;
		categoryPanel.setBorder(new CompoundBorder(new TitledBorder(new LineBorder(colorFactory.GRAY,1), "\uC81C\uD488 \uBD84\uB958", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), new EmptyBorder(5, 5, 5, 5)));
		centerPanel.add(categoryPanel, BorderLayout.NORTH);
		categoryPanel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("72px"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(55dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(55dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(56dlu;default):grow"),
				ColumnSpec.decode("max(9dlu;default)"),},
			new RowSpec[] {
				RowSpec.decode("25px"),}));
		
		JLabel productGroupLabel = new JLabel("\uC81C\uD488 \uADF8\uB8F9 :");
		categoryPanel.add(productGroupLabel, "1, 1, right, default");
		
		productGroupCombo = new WhiteCombo(new String[] {"可记","券浅扁","畴榴","盒公扁","葛磐","墨敲傅"});
		categoryPanel.add(productGroupCombo, "3, 1, fill, default");
		JLabel productTypeLabel = new JLabel("\uC81C\uD488 \uBD84\uB958 :");
		categoryPanel.add(productTypeLabel, "5, 1, right, default");
		
		productTypeCombo = new WhiteCombo(new String[] {"可记","肯力前","何磊犁","何前"});
		categoryPanel.add(productTypeCombo, "7, 1, fill, default");
		
		JLabel materialClassLabel = new JLabel("\uC81C\uD488 \uD2B9\uC131 :");
		categoryPanel.add(materialClassLabel, "9, 1, right, default");
		
		materialClassCombo = new WhiteCombo(new String[] {"可记","舅幅固唇","敲扼胶平","唱公"});
		categoryPanel.add(materialClassCombo, "11, 1, fill, default");
		
		basicPanel center_centerPanel = new basicPanel(null);;
		centerPanel.add(center_centerPanel, BorderLayout.CENTER);
		center_centerPanel.setLayout(new BorderLayout(0, 7));
		
		optionPanel = new basicPanel(null);;
		optionPanel.setBorder(new CompoundBorder(new TitledBorder(new LineBorder(colorFactory.GRAY,1), "\uC635\uC158", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), new EmptyBorder(5, 5, 5, 5)));
		center_centerPanel.add(optionPanel, BorderLayout.NORTH);
		optionPanel.setLayout(new BorderLayout(0, 5));
		setNoneOptionTitle();
		
		basicPanel listPanel = new basicPanel(null);;
		center_centerPanel.add(listPanel, BorderLayout.CENTER);
		listPanel.setBorder(new CompoundBorder(new TitledBorder(new LineBorder(colorFactory.GRAY,1), "\uC81C\uD488 \uBAA9\uB85D", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), new EmptyBorder(5, 5, 5, 5)));
		listPanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getViewport().setBackground(colorFactory.PANEL_COLOR);
		listPanel.add(scrollPane);
		
		//NozzleOption
		couplingPanel = new basicPanel(null);;
		couplingPanel.setBorder(new CompoundBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY,1), "\uCE74\uD50C\uB9C1", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), new EmptyBorder(3, 3, 3, 3)));
		couplingPanel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("max(9dlu;default)"),
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("max(10dlu;default)"),},
			new RowSpec[] {
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel couplingBodyLabel = new JLabel("\uBAB8\uD1B5 :");
		couplingPanel.add(couplingBodyLabel, "2, 1, right, default");
		
		
		couplingBodyCombo = new WhiteCombo(new String[] {"可记","窜备","街备"});
		couplingPanel.add(couplingBodyCombo, "4, 1, fill, default");
		
		JLabel couplingCeramicLabel = new JLabel("\uC138\uB77C\uBBF9 :");
		couplingPanel.add(couplingCeramicLabel, "6, 1, right, default");
		
		couplingCeramicCombo = new WhiteCombo(new String[] {"可记","130cc","160cc","300cc"});
		couplingPanel.add(couplingCeramicCombo, "8, 1, fill, default");
		
		fusionPanel = new basicPanel(null);;
		fusionPanel.setBorder(new CompoundBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY,1), "\uC735\uCC29", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), new EmptyBorder(3, 3, 3, 3)));
		fusionPanel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("max(9dlu;default)"),
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("50dlu:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("50dlu:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("50dlu:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(9dlu;default)"),},
			new RowSpec[] {
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel tubeLengthLabel = new JLabel("\uC9C1\uAD00 \uAE38\uC774 :");
		fusionPanel.add(tubeLengthLabel, "2, 1, right, default");
		
		tubeLengthCombo = new WhiteCombo(new String[] {"可记","1M","1.2M","1.5M","2M","3M"});
		fusionPanel.add(tubeLengthCombo, "4, 1, fill, default");
		
		JLabel frontFusionLabel = new JLabel("\uC55E \uC735\uCC29 :");
		fusionPanel.add(frontFusionLabel, "6, 1, right, default");
		
		frontFusionCombo = new WhiteCombo(new String[] {"可记","M家南","M家南+何教","M家南+粗卉"});
		fusionPanel.add(frontFusionCombo, "8, 1, fill, default");
		
		JLabel backFusionLabel = new JLabel("\uB4A4 \uC735\uCC29 :");
		fusionPanel.add(backFusionLabel, "10, 1, right, default");
		
		backFusionCombo = new WhiteCombo(new String[] {"可记","皋操扼","没家闺宏","眉农骇宏"});
		fusionPanel.add(backFusionCombo, "12, 1, fill, default");
		
		totalLengthPanel = new basicPanel(null);;
		totalLengthPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(110dlu;default):grow"),
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("25px"),}));
		
		JLabel lblNewLabel_8 = new JLabel("\uC804\uCCB4 \uAE38\uC774 :");
		totalLengthPanel.add(lblNewLabel_8, "2, 2, right, default");
		
		totalLengthText = new JTextField();
		totalLengthText.setBorder(new BevelBorder(BevelBorder.LOWERED));
		totalLengthPanel.add(totalLengthText, "4, 2, fill, default");
		totalLengthText.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel meterLabel = new JLabel(" M");
		totalLengthPanel.add(meterLabel, "5, 2");
		
		//setData
		ArrayList<Product> products = new ProductDAO().getClearUpProducts();
		String nameList [] = new String[products.size()];
		int idList [] = new int[products.size()];
		for(int i=0;i<products.size();i++) {
			Product product = products.get(i);
			nameList[i]=product.getName();
			idList[i]=product.getId();
		}
		JList list = new JList(nameList);
		scrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED));
		scrollPane.setViewportView(list);
		
		settingComponent();
		getContentPane().add(mainPanel);
		addEvent();
		
		this.setVisible(true);
		this.setSize(900,750);
	}
	private void addEvent() {
		resetBtn.addActionListener(new R_ROProductEvent(this));
		productGroupCombo.addActionListener(new R_ROProductEvent(this));
		productTypeCombo.addActionListener(new R_ROProductEvent(this));
		
	}
	private void setNoneOptionTitle() {
		JLabel noneOption = new JLabel("可记捞 绝绰 力前涝聪促.");
		optionPanel.add(noneOption);
	}
	public void cleanOptionPanel() {
		optionPanel.removeAll();
		setNoneOptionTitle();
		method.refreshComponent(this);
	}
	public void addNozzleOption() {
		optionPanel.removeAll();
		optionPanel.add(couplingPanel, BorderLayout.NORTH);
		optionPanel.add(fusionPanel, BorderLayout.CENTER);
		optionPanel.add(totalLengthPanel, BorderLayout.SOUTH);
		method.refreshComponent(this);
	}
	public void resetCombo() {
		couplingBodyCombo.setSelectedIndex(0);
		couplingCeramicCombo.setSelectedIndex(0);
		productGroupCombo.setSelectedIndex(0);
		productTypeCombo.setSelectedIndex(0);
		materialClassCombo.setSelectedIndex(0);
		tubeLengthCombo.setSelectedIndex(0);
		frontFusionCombo.setSelectedIndex(0);
		backFusionCombo.setSelectedIndex(0);
	}
	void settingComponent() {
		productTypeCombo.setEnabled(false);
		materialClassCombo.setEnabled(false);
	}
}
