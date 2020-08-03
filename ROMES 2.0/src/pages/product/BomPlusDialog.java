package pages.product;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import entity.Product;
import entity.dao.ProductDAO;
import pages.utils.ColoredPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BomPlusDialog extends JDialog {

	private final ColoredPanel contentPanel = new ColoredPanel();
	private JTable table;
	private JTextField textField;
	private ProductDAO productDao;
	private DefaultTableModel model;
	private TableRowSorter<DefaultTableModel> sorter;
	private int selectedrow;
	protected int selectedProductPkey;
	private JSpinner spinner;
	private FormUpdatePage formUpdatePage;

	/**
	 * Create the dialog.
	 * 
	 * @param formUpdatePage
	 */
	public BomPlusDialog(FormUpdatePage formUpdatePage) {
		this.formUpdatePage = formUpdatePage;
		String columns[] = { "pkey", "그림", "자재명" };
		this.model = new DefaultTableModel(columns, 0) {
			// Returning the Class of each column will allow different // renderers to be
			// used based on Class
			public Class getColumnClass(int column) {
				return getValueAt(1, column).getClass();
			}
		};
		this.productDao = new ProductDAO();
		settingModelContents(model, productDao.getProducts());
		table = new JTable(model);
		sorter = new TableRowSorter<DefaultTableModel>(model);
		table.setRowSorter(sorter);
		
		
		setBounds(100, 100, 750, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(
				new FormLayout(new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), },
						new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
								FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"),
								FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("bottom:default"), }));
		{
			ColoredPanel panel = new ColoredPanel();
			contentPanel.add(panel, "2, 2, fill, fill");
			panel.setLayout(new BorderLayout(0, 0));
			{
				JLabel lblNewLabel = new JLabel("BOM 생성");
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(lblNewLabel, BorderLayout.CENTER);
			}
		}
		{
			ColoredPanel panel = new ColoredPanel();
			contentPanel.add(panel, "2, 4, fill, fill");
			panel.setLayout(new FormLayout(
					new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
							FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), },
					new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
							FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
							RowSpec.decode("default:grow"), }));
			{
				JLabel lblNewLabel_1 = new JLabel("제품유형");
				panel.add(lblNewLabel_1, "2, 2, right, default");
			}
			{
				JComboBox comboBox = new JComboBox();
				comboBox.addItemListener(new BomItemListener(this));
				comboBox.setModel(new DefaultComboBoxModel(new String[] { "완제품", "부자재", "동부자재" }));
				comboBox.setSelectedIndex(2);
				panel.add(comboBox, "4, 2, fill, default");
			}
			{
				JLabel label = new JLabel("제품이름");
				panel.add(label, "2, 4, right, default");
			}
			{
				textField = new JTextField();
				panel.add(textField, "4, 4, fill, default");
				textField.setColumns(10);
				textField.getDocument().addDocumentListener(new DocumentListener() {

					@Override
					public void removeUpdate(DocumentEvent e) {
						newFilter();
					}

					@Override
					public void insertUpdate(DocumentEvent e) {
						newFilter();
					}

					@Override
					public void changedUpdate(DocumentEvent e) {
						newFilter();
					}
				});
			}
			{
				JScrollPane scrollPane = new JScrollPane();
				panel.add(scrollPane, "2, 6, 3, 1, fill, fill");
				{
					scrollPane.setViewportView(table);
					changeColumnsHeight(45);

					table.setSelectionBackground(new Color(175, 238, 238));
					table.setSelectionForeground(new Color(0, 0, 0));
					table.getColumnModel().getColumn(0).setMinWidth(0);
					table.getColumnModel().getColumn(0).setMaxWidth(0);
				}
			}
		}
		{
			ColoredPanel panel = new ColoredPanel();
			contentPanel.add(panel, "2, 6, fill, fill");
			panel.setLayout(new BorderLayout(0, 0));
			{
				JLabel lblNewLabel_2 = new JLabel("갯수 입력");
				lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNewLabel_2.setAlignmentX(Component.RIGHT_ALIGNMENT);
				panel.add(lblNewLabel_2, BorderLayout.CENTER);
			}
			{
				this.spinner = new JSpinner();
				spinner.setPreferredSize(new Dimension(50, 0));
				panel.add(spinner, BorderLayout.EAST);
			}
		}
		{
			ColoredPanel buttonPane = new ColoredPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("적용");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// 기존 메인 frame에 혹은 페널에 product pkey와, count를 전달하게 됨.
				
						int selectedProductPkey = (int)table.getValueAt(table.getSelectedRow(), 0) ;
						
						formUpdatePage.sendDataToCreateNewBom(selectedProductPkey, (int)spinner.getValue());
					
						
					
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				dispose();
			}
			{
				JButton cancelButton = new JButton("취소");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}

	private void changeColumnsHeight(int number) {
		// TODO Auto-generated method stub
		for (int i = 0; i < model.getRowCount(); i++) {
			System.out.println(i);
			table.setRowHeight(i, number);
		}
	}

	private void settingModelContents(DefaultTableModel model, List<Product> products) {
		products.forEach(product -> {
			ImageIcon icon = new ImageIcon(product.getImage());

			Image originImg = icon.getImage();
			int widht = 40;
			int height = 40;
			// 추출된 Image의 크기를 조절하여 새로운 Image객체 생성

			Image changedImg = originImg.getScaledInstance(widht, height, Image.SCALE_SMOOTH);

			// 새로운 Image로 ImageIcon객체를 생성

			icon = new ImageIcon(changedImg);
			int modelRow = model.getRowCount();
			boolean isModelHasSameProduct = false;
			for (int i = 0; i < modelRow; i++) {
				int pkey = (int) ((Vector) model.getDataVector().elementAt(i)).elementAt(0);
				if (pkey == product.getId()) {
					// 추가 안 함
					isModelHasSameProduct = true;
				} else {
					// 추가 함

				}
			}
			if (isModelHasSameProduct == false)
				model.addRow(new Object[] { product.getId(), icon, product.getName() });

		});
	}

	class BomItemListener implements ItemListener {

		private BomPlusDialog dialog;

		public BomItemListener(BomPlusDialog dialog) {
			this.dialog = dialog;
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			System.out.println("테이블 변경");

			while (model.getRowCount() > 0) {
				model.removeRow(0);
			}
			model.setRowCount(0);
			table.revalidate();
			String productType = (String) e.getItem();
			System.out.println((String) e.getItem());
			ProductDAO dao = new ProductDAO();
			dao.getProductByType(productType).forEach(product -> {
				System.out.println(product.toString());
			});
			settingModelContents(model, dao.getProductByType(productType));
			model.fireTableDataChanged();
			table.revalidate();
			changeColumnsHeight(45);
		}

	}

	private void newFilter() {
		RowFilter<DefaultTableModel, Object> rf = null;
		// If current expression doesn't parse, don't update.
		try {
			rf = RowFilter.regexFilter(textField.getText(), 2);
		} catch (java.util.regex.PatternSyntaxException e) {
			return;
		}
		sorter.setRowFilter(rf);
	}

}
