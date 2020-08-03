package pages.order;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;
import com.toedter.calendar.JDateChooser;

import before.DbChecker;
import entity.Product;
import entity.dao.CommonCodeDAO;
import entity.dao.ProductDAO;
import pages.product.BomCopyDialog;

import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

public class OrderModifyDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTable table;
	private JTextField textField_1;
	private JTextField textField_2;
	private CommonCodeDAO codeDAO;
	private DefaultTableModel model;
	private ProductDAO productDao;
	private TableRowSorter<DefaultTableModel> sorter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DbChecker.initCfg();
			OrderModifyDialog dialog = new OrderModifyDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public OrderModifyDialog() {
		codeDAO = new CommonCodeDAO();
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
		setBounds(100, 100, 634, 484);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				new ColumnSpec(ColumnSpec.FILL, Sizes.bounded(Sizes.DEFAULT, Sizes.constant("60dlu", true), Sizes.constant("60dlu", true)), 0),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.bounded(Sizes.DEFAULT, Sizes.constant("50dlu", false), Sizes.constant("50dlu", false)), 1),
				FormSpecs.RELATED_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.bounded(Sizes.DEFAULT, Sizes.constant("90dlu", false), Sizes.constant("90dlu", false)), 0),}));
		
		JLabel lblNewLabel_5 = new JLabel("발주요청 페이지");
		lblNewLabel_5.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_5, "2, 2, 15, 1");
		
		JLabel lblNewLabel = new JLabel("제품명");
		panel.add(lblNewLabel, "2, 4, right, default");
		
		textField = new JTextField();
		panel.add(textField, "4, 4, fill, default");
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("상품유형");
		
		panel.add(lblNewLabel_1, "6, 4, right, default");
		
		JComboBox comboBox = new JComboBox();
		codeDAO.getColumnValuesByColumName("자재분류").forEach(e->{
			comboBox.addItem(e);
		});
		panel.add(comboBox, "8, 4, fill, default");
		
		JLabel lblNewLabel_2 = new JLabel("자재그룹");
		panel.add(lblNewLabel_2, "10, 4, right, default");
		
		JComboBox comboBox_1 = new JComboBox();
		codeDAO.getColumnValuesByColumName("자재그룹").forEach(e->{
			comboBox_1.addItem(e);
		});
		panel.add(comboBox_1, "12, 4, fill, default");
		
		JLabel lblNewLabel_3 = new JLabel("자재특성");
		panel.add(lblNewLabel_3, "14, 4, right, default");
		
		JComboBox comboBox_2 = new JComboBox();
		codeDAO.getColumnValuesByColumName("자재특성").forEach(e->{
			comboBox_2.addItem(e);
		});
		panel.add(comboBox_2, "16, 4, fill, default");
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, "2, 6, 15, 1, fill, fill");
		
		
		scrollPane.setViewportView(table);
		changeColumnsHeight(45);

		table.setSelectionBackground(new Color(175, 238, 238));
		table.setSelectionForeground(new Color(0, 0, 0));
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, "2, 8, 15, 1, fill, fill");
		panel_2.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode("max(34dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JLabel label = new JLabel("업체명");
		panel_2.add(label, "2, 1, right, default");
		
		textField_1 = new JTextField();
		panel_2.add(textField_1, "4, 1, fill, default");
		textField_1.setColumns(10);
		
		JLabel label_1 = new JLabel("수주예정일");
		panel_2.add(label_1, "6, 1");
		
		JDateChooser dateChooser = new JDateChooser();
		panel_2.add(dateChooser, "8, 1, fill, fill");
		
		JLabel lblNewLabel_4 = new JLabel("갯수");
		panel_2.add(lblNewLabel_4, "2, 3");
		
		JSpinner spinner = new JSpinner();
		panel_2.add(spinner, "4, 3");
		
		JLabel label_2 = new JLabel("담당자");
		panel_2.add(label_2, "6, 3, right, default");
		
		textField_2 = new JTextField();
		panel_2.add(textField_2, "8, 3, fill, default");
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("가격");
		panel_2.add(lblNewLabel_6, "2, 5");
		
		JSpinner spinner_1 = new JSpinner();
		panel_2.add(spinner_1, "4, 5");
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
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

		private BomCopyDialog dialog;

		public BomItemListener(BomCopyDialog dialog) {
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
	private void changeColumnsHeight(int number) {
		// TODO Auto-generated method stub
		for (int i = 0; i < model.getRowCount(); i++) {
			System.out.println(i);
			table.setRowHeight(i, number);
		}
	}

}
