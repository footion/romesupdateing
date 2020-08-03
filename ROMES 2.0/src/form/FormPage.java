package form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import layoutSetting.basicBtn;
import layoutSetting.basicPanel;
import layoutSetting.basicTextField;
import layoutSetting.miniTable;

import javax.swing.JRadioButton;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Font;

@SuppressWarnings("serial")
public class FormPage extends basicPanel {
	private basicTextField productNameField;
	private JTextField locationField;
	private JTextField salePriceField;
	private JTextField stockCountField;
	private JTable table;
	@SuppressWarnings("rawtypes")
	DefaultComboBoxModel boxModel= new DefaultComboBoxModel(new String[] {"완제품", "부자재", "동부자재"});
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setSize(new Dimension(800,800));
		
		frame.getContentPane().add(new FormPage());
	}
	private void gettingData() {
	}
	/**
	 * Create the panel.
	 */
	public FormPage() {
		gettingData();
		setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(10, 10, 10, 10)));
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode("max(46dlu;pref)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(154dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		basicPanel panel = new basicPanel();
		add(panel, "1, 1, fill, fill");
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(44dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(55dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		basicPanel panel_1 = new basicPanel();
		panel_1.setBorder(new EmptyBorder(-5, 0, 5, 0));
		panel.add(panel_1, "2, 1, fill, fill");
		panel_1.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("left:default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(10dlu;default)"),}));
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("\uC81C\uD488\uBCC4");
		rdbtnNewRadioButton.setFont(new Font("굴림", Font.PLAIN, 11));
		rdbtnNewRadioButton.setBackground(Color.WHITE);
		panel_1.add(rdbtnNewRadioButton, "2, 2");
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("\uAC70\uB798\uCC98\uBCC4");
		rdbtnNewRadioButton_1.setFont(new Font("굴림", Font.PLAIN, 11));
		rdbtnNewRadioButton_1.setBackground(Color.WHITE);
		panel_1.add(rdbtnNewRadioButton_1, "2, 4");
		
		JLabel lblNewLabel_1 = new JLabel("회사명");
		panel.add(lblNewLabel_1, "4, 1, center, center");
		
		productNameField = new basicTextField(15);
		panel.add(productNameField, "6, 1, 5, 1, fill, default");
		productNameField.setColumns(10);
		
		basicPanel panel_6 = new basicPanel();
		panel_6.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		panel.add(panel_6, "12, 1, center, default");
		panel_6.setPreferredSize(new Dimension(130,90));
		
//		ImageLabel imageLabel = new ImageLabel(new ImageIcon(product.getImage()), "image");
//		imageLabel.resizeLabel(imageLabel, 70, 70);
//		panel.add(imageLabel, "10, 1, center, center");
		
		basicPanel panel_2 = new basicPanel();
		panel_2.setToolTipText("\uD68C\uC0AC \uC815\uBCF4");
		panel_2.setBorder(new CompoundBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "회사 정보", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), new EmptyBorder(10, 10, 10, 10)));
		add(panel_2, "1, 3, fill, default");
		panel_2.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("56px"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("220px:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("56px"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("219px:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("26px"),},
			new RowSpec[] {
				RowSpec.decode("30px"),
				RowSpec.decode("24px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("24px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("24px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("131px"),}));
		
		JLabel productLabel = new JLabel("회사 구분");
		panel_2.add(productLabel, "2, 2, center, center");
		int size = boxModel.getSize();
		
		textField = new JTextField();
		panel_2.add(textField, "4, 2, fill, default");
		textField.setColumns(10);
		
		JLabel label = new JLabel("사업 번호");
		panel_2.add(label, "6, 2, center, center");
		
		locationField = new JTextField();
		//locationField.setText(product.getCommonInfo().getLocation());
		panel_2.add(locationField, "8, 2, fill, top");
		locationField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel(" ");
		panel_2.add(lblNewLabel_2, "10, 2, fill, center");
		
		JLabel lblNewLabel_3 = new JLabel("대표명");
		panel_2.add(lblNewLabel_3, "2, 4, center, center");
		
		salePriceField = new JTextField();
		//salePriceField.setText(product.getCommonInfo().getSalePrice()+"");
		panel_2.add(salePriceField, "4, 4, fill, top");
		salePriceField.setColumns(10);
		
		JLabel lblTel = new JLabel("Tel");
		panel_2.add(lblTel, "6, 4, center, center");
		
		stockCountField = new JTextField();
		panel_2.add(stockCountField, "8, 4, fill, top");
		stockCountField.setColumns(10);
		
		JLabel label_2 = new JLabel("주소");
		panel_2.add(label_2, "2, 6, center, center");
		
		textField_1 = new JTextField();
		panel_2.add(textField_1, "4, 6, fill, default");
		textField_1.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-mail");
		panel_2.add(lblEmail, "6, 6, center, default");
		
		textField_2 = new JTextField();
		panel_2.add(textField_2, "8, 6, fill, default");
		textField_2.setColumns(10);
		
		basicPanel panel_4 = new basicPanel();
		panel_4.setBorder(new CompoundBorder(new EmptyBorder(1, 10, 1, 10), new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "\uC124\uBA85", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0))));
		panel_2.add(panel_4, "2, 8, 9, 1, fill, fill");
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_5 = new JLabel("\uD68C\uC0AC\uC5D0 \uB300\uD55C \uC815\uBCF4\uC785\uB2C8\uB2E4.");
		panel_4.add(lblNewLabel_5);
		
		basicPanel panel_3 = new basicPanel();
		panel_3.setBorder(new CompoundBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "매입품 정보", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), new EmptyBorder(1, 10, 1, 10)));
		add(panel_3, "1, 5, fill, fill");
		panel_3.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(33dlu;default):grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		basicPanel panel_5 = new basicPanel();
		panel_5.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel_3.add(panel_5, "2, 2, fill, fill");
		panel_5.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
		basicBtn btnNewButton = new basicBtn("추가");
		panel_5.add(btnNewButton);
		
		basicBtn btnNewButton_1 = new basicBtn("..");
		panel_5.add(btnNewButton_1);

		String col[] = {"id","자재명","Info","단가","x"};
		miniTable scrollPane = new miniTable(col, 2, 4);
		scrollPane.getViewport().setPreferredSize(new Dimension(600,400));
		panel_3.add(scrollPane, "2, 4, fill, default");
		
//		ArrayList<BomEntity> list= new ArrayList<BomEntity>(product.getBoms());
//		list.forEach(bom->{
//			ImageIcon icon= new ImageIcon(bom.getMeterial().getImage());
//			
//			Image originImg = icon.getImage();
//			int widht=60;
//			int height=60;
//			// 추출된 Image의 크기를 조절하여 새로운 Image객체 생성
//
//			Image changedImg = originImg.getScaledInstance(widht, height, Image.SCALE_SMOOTH);
//
//			// 새로운 Image로 ImageIcon객체를 생성
//
//			icon = new ImageIcon(changedImg);
//			model.addRow(new Object[] {icon,bom.getMeterial().getName(),bom.getCount() });
//		});
		
//		table = new JTable(model);
//		table.setRowSelectionAllowed(false);
//		table.setEnabled(false);
//		for(int i =0;i<model.getRowCount();i++) {
//			System.out.println(i);
//			table.setRowHeight(i, 70);
//		}
//		scrollPane.setViewportView(table);	
	}

}