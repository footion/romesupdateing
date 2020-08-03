package pages.product;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import components.imagelabel.ImageLabel;
import entity.BomEntity;
import entity.Product;
import entity.ProductCommonInfo;
import entity.dao.CommonCodeDAO;
import entity.dao.ProductDAO;
import img.ImgFactory;
import pages.utils.ColoredPanel;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import com.jgoodies.forms.layout.Sizes;
import java.awt.GridBagLayout;;

public class FormUpdatePage extends ColoredPanel {
	private JTextField productNameField;
	private JTextField locationField;
	private JTextField salePriceField;
	private JTextField stockCountField;
	private JTable table;
	private ProductResource resource;
	private Product product;
	public Product getProduct() {
		return product;
	}
	public JTextField getProductNameField() {
		return productNameField;
	}
	public void setProductNameField(JTextField productNameField) {
		this.productNameField = productNameField;
	}
	public JTextField getLocationField() {
		return locationField;
	}
	public void setLocationField(JTextField locationField) {
		this.locationField = locationField;
	}
	public JTextField getSalePriceField() {
		return salePriceField;
	}
	public void setSalePriceField(JTextField salePriceField) {
		this.salePriceField = salePriceField;
	}
	public JTextField getStockCountField() {
		return stockCountField;
	}
	public void setStockCountField(JTextField stockCountField) {
		this.stockCountField = stockCountField;
	}
	public JTable getTable() {
		return table;
	}
	public void setTable(JTable table) {
		this.table = table;
	}
	public ProductResource getResource() {
		return resource;
	}
	public void setResource(ProductResource resource) {
		this.resource = resource;
	}
	
	
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	private String imagePath;
	public JCheckBox disableSale;
	public JCheckBox disableBuy;
	public ImageLabel imageLabel;
	private JComboBox unitTypeBox;
	private JTextArea detail;
	private JComboBox productType;
	private JButton newBomEntityBt;
	private DefaultTableModel model;
	private JTextArea textArea;
	private JSpinner spinner;
	private JSpinner spinner_1;
	private JComboBox comboBox;
	private JComboBox comboBox_2;
	private CommonCodeDAO codeDAO;
	
	public JComboBox getComboBox() {
		return unitTypeBox;
	}
	public void setComboBox(JComboBox comboBox) {
		this.unitTypeBox = comboBox;
	}
	public JTextArea getDetail() {
		return detail;
	}
	public void setDetail(JTextArea detail) {
		this.detail = detail;
	}
	public JComboBox getProductType() {
		return productType;
	}
	public void setProductType(JComboBox productType) {
		this.productType = productType;
	}
	private void gettingData() {
		
		ProductDAO productDAO = new ProductDAO();
		System.out.println("superPanel.keynumber : "+keynumber);
		if(keynumber!=-1)
			this.product=productDAO.findByPkey(keynumber);
		else {
			this.product = new Product();
			this.product.setCommonInfo(new ProductCommonInfo());
		}
		System.out.println(this.product);
	}
	public int keynumber ;
	/**
	 * Create the panel.
	 */
	public FormUpdatePage(int keynumber) {
		Setting(keynumber);
		
		setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(10, 10, 10, 10)));
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(46dlu;pref)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(117dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		ColoredPanel panelTop = new ColoredPanel();
		add(panelTop, "1, 1, fill, fill");
		panelTop.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JButton btnNewButton_1 = new JButton("뒤로가기");
		btnNewButton_1.addActionListener(new ReturnButtonEvent());
		panelTop.add(btnNewButton_1, "2, 2");
		
		JButton btnNewButton_3 = new JButton("저장");
		btnNewButton_3.addActionListener(new modifyButtonEvent(this.keynumber, this));
		panelTop.add(btnNewButton_3, "4, 2");
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
		
		disableSale = new JCheckBox("판매불가");
		disableSale.setSelected(product.isBuyable());
		panel_1.add(disableSale, "2, 2, default, bottom");
		
		this.disableBuy = new JCheckBox("구매불가");
		disableBuy.setSelected(product.isSaleable());
		panel_1.add(disableBuy, "2, 4, default, top");
		
		if(product.getImage()!=null) {
			this. imageLabel = new ImageLabel(new ImageIcon(product.getImage()), "image");	
		}
		this.imageLabel = new ImageLabel(new ImageIcon(ImgFactory.ImgToBytes("resources/noImage.png")),"image");
		imageLabel.resizeLabel(imageLabel, 70, 70);
		panel.add(imageLabel, "10, 1, center, center");
		imageLabel.addMouseListener(new pages.product.ImageSelectMouseEvent(this,imageLabel));
		
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
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JLabel productLabel = new JLabel("상품유형");
		panel_2.add(productLabel, "1, 1, left, default");
		
		this. productType = new JComboBox();
		productType.setEditable(false);
		codeDAO.getColumnValuesByColumName("자재분류").forEach(e->{
			productType.addItem(e);
		});
		productType.setSelectedIndex(0);
		
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
		panel_6.add(unitTypeBox, "1, 1, fill, center");
		
		codeDAO.getColumnValuesByColumName("제품포장단위").forEach(e->{
			unitTypeBox.addItem(e);
		});
		
		unitTypeBox.setSelectedIndex(0);
		
		spinner = new JSpinner();
		panel_6.add(spinner, "3, 1");
		
		JLabel lblNewLabel = new JLabel("단위무게");
		panel_2.add(lblNewLabel, "5, 5");
		
		spinner_1 = new JSpinner();
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
		codeDAO.getColumnValuesByColumName("자재그룹").forEach(e->{
			comboBox.addItem(e);
		});
		panel_7.add(comboBox, "3, 1, fill, default");
		
		
		
		JLabel lblNewLabel_7 = new JLabel("자재특성");
		
		panel_7.add(lblNewLabel_7, "5, 1, right, default");
		
		this. comboBox_2 = new JComboBox();
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
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(44dlu;default)"),}));
		
		JPanel bomButtonPanel = new ColoredPanel();
		panel_3.add(bomButtonPanel, "2, 2, fill, fill");
		bomButtonPanel.setLayout(new BoxLayout(bomButtonPanel, BoxLayout.X_AXIS));
		
		JButton btnNewButton = new JButton("불러오기");
		btnNewButton.addActionListener(new CopyBomsEvent(this));
		bomButtonPanel.add(btnNewButton);
		
		this.newBomEntityBt = new JButton("+");
		newBomEntityBt.addActionListener(new AddButtonEvent(this));
		bomButtonPanel.add(newBomEntityBt);
		
		JButton btnNewButton_2 = new JButton("-");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedProductPkey = (int)table.getValueAt(table.getSelectedRow(), 0) ;
				model.removeRow(table.getSelectedRow());
				product.getBoms().forEach(bom->{
					if(bom.getId()==selectedProductPkey) {
						product.removeBom(bom);
					}
					
				});
				model.fireTableDataChanged();
				table.revalidate();
				for(int i =0;i<model.getRowCount();i++) {
					System.out.println(i);
					table.setRowHeight(i, 70);
				}
			}
		});
		bomButtonPanel.add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		panel_3.add(scrollPane, "2, 4, 1, 3, fill, fill");
	
		
		String columns[] = {"id","그림","자재명","수량"};
		this. model = new DefaultTableModel(columns, 0) {
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
			model.addRow(new Object[] {bom.getId(),icon,bom.getMeterial().getName(),bom.getCount() });
			
		});
		table = new JTable(model);
		for(int i =0;i<model.getRowCount();i++) {
			System.out.println(i);
			table.setRowHeight(i, 70);
		}
		table.setSelectionBackground(new Color(175, 238, 238));	
		table.setSelectionForeground(new Color(0, 0, 0));
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		ListSelectionModel selectionModel = table.getSelectionModel();
		selectionModel.addListSelectionListener(new ListSelectionListener() {			
			@Override
			public void valueChanged(ListSelectionEvent e) {
//				System.out.println("값 바뀜");
//				if(!selectionModel.isSelectionEmpty()) {
//					int selectedrow = selectionModel.getMinSelectionIndex();
//					System.out.println(model.getValueAt(selectedrow, 0));	
//				}
			}
		});
		scrollPane.setViewportView(table);
		JPanel panel_5 = new ColoredPanel();
		panel_5.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "\uC124\uBA85", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0))));
panel_3.add(panel_5, "2, 8, fill, fill");
panel_5.setLayout(new BorderLayout(0, 0));
textArea = new JTextArea();
panel_5.add(textArea, BorderLayout.CENTER);
		table.getModel().addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				int column = e.getColumn(),row=e.getFirstRow();
				if(column==3) {
					int changeCont=(int)table.getValueAt(table.getSelectedRow(), column) ;
					int pkey = (int)table.getValueAt(table.getSelectedRow(), 0) ;
					product.getBoms().forEach(bom->{
						if(bom.getId()==pkey) {
							bom.setCount(changeCont);
						}
					});
				}
				
			}
        });
	}
	private void Setting(int keynumber) {
		this.codeDAO = new CommonCodeDAO();
		System.out.println("생성자 실행");
		this.keynumber=keynumber;
		resource = new ProductResource();
		gettingData();
		
	}
	public JComboBox getUnitTypeBox() {
		return unitTypeBox;
	}
	public void setUnitTypeBox(JComboBox unitTypeBox) {
		this.unitTypeBox = unitTypeBox;
	}
	public void setImgPath(String imagePath) {
		this.imagePath=imagePath;
	}
	public String getImgPath() {
		
		return this.imagePath;
	}
	class AddButtonEvent implements ActionListener{
		
		private FormUpdatePage formUpdatePage;

		public AddButtonEvent(FormUpdatePage formUpdatePage) {
			// TODO Auto-generated constructor stub
			this.formUpdatePage= formUpdatePage;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			BomPlusDialog d = new BomPlusDialog(formUpdatePage);
			d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			d.setVisible(true);
		}
		
	}
	class CopyBomsEvent implements ActionListener{

		private FormUpdatePage formUpdatePage;

		public CopyBomsEvent(FormUpdatePage formUpdatePage) {
			// TODO Auto-generated constructor stub
			this.formUpdatePage= formUpdatePage;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			BomCopyDialog bomCopyDialog = new BomCopyDialog(formUpdatePage);
			bomCopyDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			bomCopyDialog.setVisible(true);
			
		}
		
	}
	public void sendDataToCreateNewBom(int selectedProductPkey, int value) {
		System.out.println("?selectedProductPkey "+selectedProductPkey);
		System.out.println("value " +value);
		BomEntity bomEntity = new BomEntity();
		bomEntity.setCount(value);
		ProductDAO dao = new ProductDAO();
		bomEntity.setMeterial(dao.findByPkey(selectedProductPkey));
		bomEntity.setProduct(product);
		product.addBom(bomEntity);
		
		ImageIcon icon= new ImageIcon(bomEntity.getMeterial().getImage());
		
		Image originImg = icon.getImage();
		int widht=60;
		int height=60;
		// 추출된 Image의 크기를 조절하여 새로운 Image객체 생성

		Image changedImg = originImg.getScaledInstance(widht, height, Image.SCALE_SMOOTH);

		// 새로운 Image로 ImageIcon객체를 생성

		icon = new ImageIcon(changedImg);
		
		model.addRow(new Object[] {0,icon,bomEntity.getMeterial().getName(),bomEntity.getCount() });
		model.fireTableDataChanged();
		table.revalidate();
		for(int i =0;i<model.getRowCount();i++) {
			System.out.println(i);
			table.setRowHeight(i, 70);
		}
	}
	public void sendDataToCopyBom(int selectedProductPkey) {
		System.out.println("?selectedProductPkey "+selectedProductPkey);
		ProductDAO dao = new ProductDAO();
		
		Product selectedProduct = dao.findByPkey(selectedProductPkey);
		selectedProduct.getBoms().forEach(bom->{
			BomEntity copyBomEntity = new BomEntity();
			copyBomEntity.setProduct(product);
			copyBomEntity.setMeterial(bom.getMeterial());
			copyBomEntity.setCount(bom.getCount());
			
			product.addBom(copyBomEntity);
			ImageIcon icon= new ImageIcon(bom.getMeterial().getImage());
			
			Image originImg = icon.getImage();
			int widht=60;
			int height=60;
			// 추출된 Image의 크기를 조절하여 새로운 Image객체 생성

			Image changedImg = originImg.getScaledInstance(widht, height, Image.SCALE_SMOOTH);

			// 새로운 Image로 ImageIcon객체를 생성
			icon = new ImageIcon(changedImg);
			model.addRow(new Object[] {0,icon,bom.getMeterial().getName(),bom.getCount() });
		});
		
		
		
		model.fireTableDataChanged();
		table.revalidate();
		for(int i =0;i<model.getRowCount();i++) {
			System.out.println(i);
			table.setRowHeight(i, 70);
		}
		
	}

	public JTextArea getBomDetails() {
		return textArea;
	}
	public JSpinner unitCount() {
		return spinner;
	}
	public JSpinner getunitWeight() {
		return spinner_1;
	}
}
