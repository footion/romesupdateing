package pages.product;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ToolTipManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import org.apache.poi.ss.util.ImageUtils;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import Main.main;
import components.imagelabel.ImageLabel;
import entity.BomEntity;
import entity.Product;
import entity.dao.CommonCodeDAO;
import entity.dao.ProductDAO;
import factory.ImgFactory;
import gui.HeadPanel;
import gui.MESCardLayout;
import mainFrame.lodingFrame;
import mainFrame.mainFrame;
import pages.utils.ColoredPanel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class FormPage extends ColoredPanel {
	private JTextField productNameField;
	private JTable table;
	private ProductResource resource;
	private Product product;
	DefaultComboBoxModel boxModel= new DefaultComboBoxModel(new String[] {"완제품", "부자재", "동부자재"});
	private int keynumber;
	private JComboBox productType;
	private JComboBox comboBox_2;
	private JComboBox comboBox;
	private JComboBox unitTypeBox;
	private CommonCodeDAO codeDAO;
	private JTextField locationField;
	private JTextField salePriceField;
	private JTextField stockCountField;
	private JSpinner spinner;
	private JSpinner spinner_1;
	private JTextArea detail;
	private void gettingData() {
		this.codeDAO=new CommonCodeDAO();
		ProductDAO productDAO = new ProductDAO();
		System.out.println("superPanel.keynumber : "+this.keynumber);
		this.product=productDAO.findByPkey(this.keynumber);
		System.out.println(this.product);
	}
	/**
	 * Create the panel.
	 * @throws IOException 
	 */
	public FormPage(int key) throws IOException {
		this.keynumber=key;
		resource = new ProductResource();
		gettingData();
		
		
		setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(10, 10, 10, 10)));
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(46dlu;pref)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(154dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JPanel panel_5 = new JPanel();
		add(panel_5, "1, 1, fill, fill");
		panel_5.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JButton btnNewButton = new JButton("뒤로가기");
		btnNewButton.addActionListener(new ReturnButtonEvent());
		panel_5.add(btnNewButton, "2, 2");
		
		JButton btnNewButton_1 = new JButton("수정");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CardLayout card = mainFrame.card;
				Container container = mainFrame.Container;
				mainFrame.title.setText("제품상세보기");
				Thread thread = new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						lodingFrame loding = new lodingFrame();
						container.add(new FormUpdatePage(keynumber),"ProductCreate");
						card.show(container, "ProductCreate");
						loding.dispose();
					}
				});
				thread.start();
				
			}
		});
		panel_5.add(btnNewButton_1, "4, 2");
		
		JButton btnNewButton_2 = new JButton("삭제");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int value =JOptionPane.showConfirmDialog(null, "정말 해당 제품 데이터를 삭제하시겠습니까?");
				
				if(value==0) {
					ProductDAO dao = new ProductDAO();
					dao.delProduct(product);
					JOptionPane.showMessageDialog(null, "제품데이터가 삭제되었습니다. ");
				}else {
					
				}
				
				
			}
		});
		panel_5.add(btnNewButton_2, "6, 2, default, top");
		
		ColoredPanel panel = new ColoredPanel();
		add(panel, "1, 3, fill, fill");
		panel.setLayout(new FormLayout(new ColumnSpec[] {
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
				RowSpec.decode("default:grow"),}));
		
		JLabel lblNewLabel_1 = new JLabel("제품명");
		panel.add(lblNewLabel_1, "2, 1, center, center");
		
		productNameField = new JTextField(product.getName());
		productNameField.setEnabled(false);
		productNameField.setEditable(false);
		panel.add(productNameField, "4, 1, 3, 1, fill, default");
		productNameField.setColumns(10);
		
		ColoredPanel panel_1 = new ColoredPanel();
		panel_1.setBorder(new EmptyBorder(5, 0, 5, 0));
		panel.add(panel_1, "8, 1, fill, fill");
		panel_1.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("left:default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("판매불가");
		chckbxNewCheckBox.setSelected(product.isBuyable());
		chckbxNewCheckBox.setEnabled(false);
		panel_1.add(chckbxNewCheckBox, "2, 2, default, bottom");
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("구매불가");
		chckbxNewCheckBox_1.setSelected(product.isSaleable());
		chckbxNewCheckBox_1.setEnabled(false);
		panel_1.add(chckbxNewCheckBox_1, "2, 4, default, top");
		
		//----------------------------------------------------
		//----------------image resize for tooltip------------
		//------------------------start-----------------------
		//----------------------------------------------------
		ByteArrayInputStream inputStream = new ByteArrayInputStream(product.getImage());
		BufferedImage inputimag= ImageIO.read(inputStream);
		BufferedImage outputimag = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
		Graphics2D graphics2d = outputimag.createGraphics();
		graphics2d.drawImage(inputimag, 0, 0, 400, 400,null);
		graphics2d.dispose();
		ImageIO.write((RenderedImage)outputimag, "png", new File("1111.png"));//save resized image in root directory.
		//----------------------------------------------------
		//----------------image resize for tooltip------------
		//--------------------------stop----------------------
		//----------------------------------------------------
		
		
	
		ImageLabel imageLabel = new ImageLabel(new ImageIcon(product.getImage()), "image");
		imageLabel.setToolTipText("<html><img src='file:1111.png'></html>");
		
		imageLabel.resizeLabel(imageLabel, 70, 70);
		panel.add(imageLabel, "10, 1, center, center");
		int size = boxModel.getSize();
		ColoredPanel panel_2 = new ColoredPanel();
		panel_2.setBorder(new CompoundBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "\uC81C\uD488\uC815\uBCF4", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), new EmptyBorder(10, 10, 10, 10)));
		add(panel_2, "1, 5, fill, fill");
		panel_2.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JLabel productLabel = new JLabel("상품유형");
		panel_2.add(productLabel, "1, 1, left, default");
		
		this. productType = new JComboBox();
		String type=product.getCommonInfo().getProductType();
		productType.setEnabled(false);
		productType.setEditable(false);
		codeDAO.getColumnValuesByColumName("자재분류").forEach(e->{
			productType.addItem(e);
		});
		for(int i =0;i<productType.getItemCount();i++) {
			if(type.equals((String)productType.getItemAt(i))) {
				productType.setSelectedIndex(i);
			}
		}
		
		
		
		panel_2.add(productType, "3, 1, fill, center");
		
		JLabel label = new JLabel("위치정보");
		panel_2.add(label, "5, 1, left, default");
		
		locationField = new JTextField();
		locationField.setText(product.getCommonInfo().getLocation());
		panel_2.add(locationField, "7, 1, fill, default");
		locationField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel(" ");
		panel_2.add(lblNewLabel_2, "9, 1");
		
		JLabel lblNewLabel_3 = new JLabel("판매가");
		panel_2.add(lblNewLabel_3, "1, 3, left, default");
		
		salePriceField = new JTextField();
		salePriceField.setText(product.getCommonInfo().getSalePrice()+"");
		panel_2.add(salePriceField, "3, 3, fill, default");
		salePriceField.setColumns(10);
		
		JLabel label_1 = new JLabel("재고량");
		panel_2.add(label_1, "5, 3, left, default");
		
		stockCountField = new JTextField();
		stockCountField.setEnabled(false);
		stockCountField.setEditable(false);
		stockCountField.setText(""+0);
		panel_2.add(stockCountField, "7, 3, fill, default");
		stockCountField.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("(개)");
		panel_2.add(lblNewLabel_4, "9, 3");
		
		JLabel label_2 = new JLabel("단위");
		panel_2.add(label_2, "1, 5, left, default");
		
		JPanel panel_6 = new ColoredPanel();
		panel_2.add(panel_6, "3, 5, fill, fill");
		panel_6.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		this.unitTypeBox = new JComboBox();
		unitTypeBox.setEnabled(false);
		panel_6.add(unitTypeBox, "1, 1, fill, center");
		
		codeDAO.getColumnValuesByColumName("제품포장단위").forEach(e->{
			unitTypeBox.addItem(e);
		});
		
		unitTypeBox.setSelectedIndex(0);
		
		spinner = new JSpinner();
		spinner.setEnabled(false);
		panel_6.add(spinner, "3, 1");
		
		JLabel lblNewLabel = new JLabel("단위무게");
		panel_2.add(lblNewLabel, "5, 5");
		
		spinner_1 = new JSpinner();
		spinner_1.setEnabled(false);
		panel_2.add(spinner_1, "7, 5");
		
		JPanel panel_7 = new JPanel();
		panel_2.add(panel_7, "1, 7, 7, 1, fill, fill");
		panel_7.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel_5 = new JLabel("자재그룹");
		panel_7.add(lblNewLabel_5, "1, 1, right, default");
		
		this. comboBox = new JComboBox();
		comboBox.setEnabled(false);
		codeDAO.getColumnValuesByColumName("자재그룹").forEach(e->{
			comboBox.addItem(e);
		});
		panel_7.add(comboBox, "3, 1, fill, default");
		
		
		
		JLabel lblNewLabel_7 = new JLabel("자재특성");
		
		panel_7.add(lblNewLabel_7, "5, 1, right, default");
		
		this. comboBox_2 = new JComboBox();
		comboBox_2.setEnabled(false);
		codeDAO.getColumnValuesByColumName("자재특성").forEach(e->{
			comboBox_2.addItem(e);
		});
		panel_7.add(comboBox_2, "7, 1, fill, default");
		
		JTextPane textPane = new JTextPane();
		panel_2.add(textPane, "7, 7, fill, fill");
		
		ColoredPanel panel_4 = new ColoredPanel();
		panel_4.setBorder(new CompoundBorder(new EmptyBorder(1, 10, 1, 10), new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "\uC124\uBA85", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0))));
		panel_2.add(panel_4, "1, 9, 9, 1, fill, fill");
		panel_4.setLayout(new BorderLayout(0, 0));
		
		this.detail = new JTextArea();
		detail.setTabSize(7);
		panel_4.add(detail, BorderLayout.CENTER);
		
		ColoredPanel panel_3 = new ColoredPanel();
		panel_3.setBorder(new CompoundBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "BOM \uC815\uBCF4", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), new EmptyBorder(1, 10, 1, 10)));
		add(panel_3, "1, 7, fill, fill");
		panel_3.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(33dlu;default):grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_3.add(scrollPane, "2, 2, 1, 3, fill, fill");
		
		
		String columns[] = {"그림","자재명","수량"};
		DefaultTableModel model = new DefaultTableModel(columns, 0) {
			// Returning the Class of each column will allow different // renderers to be used based on Class 
			public Class getColumnClass(int column) {
				return getValueAt(0, column).getClass(); 
				}

		};
		ArrayList<BomEntity> list= new ArrayList<BomEntity>(product.getBoms());
		list.forEach(bom->{
			ImageIcon icon= new ImageIcon(bom.getMeterial().getImage());
			
			Image originImg = icon.getImage();
			int widht=60;
			int height=60;
			// 추출된 Image의 크기를 조절하여 새로운 Image객체 생성

			Image changedImg = originImg.getScaledInstance(widht, height, Image.SCALE_SMOOTH);

			// 새로운 Image로 ImageIcon객체를 생성

			icon = new ImageIcon(changedImg);
			model.addRow(new Object[] {icon,bom.getMeterial().getName(),bom.getCount() });
		});
		
		table = new JTable(model);
		table.setRowSelectionAllowed(false);
		table.setEnabled(false);
		for(int i =0;i<model.getRowCount();i++) {
			System.out.println(i);
			table.setRowHeight(i, 70);
		}
			
		
		scrollPane.setViewportView(table);

		
	}

}
