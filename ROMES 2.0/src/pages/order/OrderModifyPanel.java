package pages.order;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import com.toedter.calendar.JDateChooser;

import entity.dao.CommonCodeDAO;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JSpinner;
import com.jgoodies.forms.layout.Sizes;

public class OrderModifyPanel extends JPanel {
	private JTextField textField;
	private JTable table;
	private JTextField textField_1;
	private JTextField textField_2;
	private CommonCodeDAO codeDAO;

	/**
	 * Create the panel.
	 */
	public OrderModifyPanel() {
		codeDAO = new CommonCodeDAO();
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JButton btnNewButton_1 = new JButton("ㅈ");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JPanel panel_1 = new JPanel();
		add(panel_1, "2, 1, fill, fill");
		panel_1.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JButton btnNewButton_2 = new JButton("저장");
		panel_1.add(btnNewButton_2, "4, 2");
		
		JButton btnNewButton_3 = new JButton("초기화");
		panel_1.add(btnNewButton_3, "6, 2");
		
		JPanel panel = new JPanel();
		add(panel, "2, 3, fill, fill");
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
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
				new RowSpec(RowSpec.CENTER, Sizes.bounded(Sizes.DEFAULT, Sizes.constant("50dlu", false), Sizes.constant("50dlu", false)), 0),
				FormSpecs.RELATED_GAP_ROWSPEC,
				new RowSpec(RowSpec.CENTER, Sizes.bounded(Sizes.DEFAULT, Sizes.constant("60dlu", false), Sizes.constant("60dlu", false)), 0),}));
		
		JLabel lblNewLabel = new JLabel("제품명");
		panel.add(lblNewLabel, "2, 2, right, default");
		
		textField = new JTextField();
		panel.add(textField, "4, 2, fill, default");
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("상품유형");
		panel.add(lblNewLabel_1, "6, 2, right, default");
		
		JComboBox comboBox = new JComboBox();
		codeDAO.getColumnValuesByColumName("자재분류").forEach(e->{
			comboBox.addItem(e);
		});
		panel.add(comboBox, "8, 2, fill, default");
		
		JLabel lblNewLabel_2 = new JLabel("자재그룹");
		panel.add(lblNewLabel_2, "10, 2, right, default");
		
		JComboBox comboBox_1 = new JComboBox();
		codeDAO.getColumnValuesByColumName("자재그룹").forEach(e->{
			comboBox_1.addItem(e);
		});
		panel.add(comboBox_1, "12, 2, fill, default");
		
		JLabel lblNewLabel_3 = new JLabel("자재특성");
		panel.add(lblNewLabel_3, "14, 2, right, default");
		
		JComboBox comboBox_2 = new JComboBox();
		codeDAO.getColumnValuesByColumName("자재특성").forEach(e->{
			comboBox_1.addItem(e);
		});
		panel.add(comboBox_2, "16, 2, fill, default");
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, "2, 4, 15, 1, fill, fill");
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, "2, 6, 15, 1, fill, fill");
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

	}

}
