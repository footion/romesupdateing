package pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import entity.CommonCode;
import Dao.CommonCodeDAO;

@SuppressWarnings("serial")
public class CommonCodePage extends JPanel {
	private JTextField textField;
	private JTable table;
	private DefaultTableModel model;
	private List<CommonCode> list;

	/**
	 * Create the panel.
	 */
	public CommonCodePage() {
		getDatas();
		setLayout(new FormLayout(new ColumnSpec[] { ColumnSpec.decode("default:grow"), },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						RowSpec.decode("default:grow"), }));

		JPanel panel = new JPanel();
		add(panel, "1, 2, fill, fill");
		panel.setLayout(
				new FormLayout(
						new ColumnSpec[] { FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
								FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
								FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), },
						new RowSpec[] { RowSpec.decode("default:grow"), }));

		JButton btnNewButton = new JButton("생성");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.addRow(new Object[] { null, null, null, null, null });

				tableSizeSetting();
			}
		});
		panel.add(btnNewButton, "1, 1");

		JButton btnNewButton_1 = new JButton("저장");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int columnSize = table.getColumnCount();
				int rowSize = table.getRowCount();
				for (int row = 0; row < rowSize; row++) {
					CommonCodeDAO codeDAO = new CommonCodeDAO();
					if (table.getValueAt(row, 0) == null) {
						// make new entity
						CommonCode code = new CommonCode();
						code.setCodeId((String) table.getValueAt(row, 1));
						code.setColumnName((String) table.getValueAt(row, 2));
						code.setValue((String) table.getValueAt(row, 3));
						code.setColumnCode((String) table.getValueAt(row, 4));
						codeDAO.saveCommonCode(code);
					} else {
						// update prior entity
						CommonCode code = codeDAO.findByPkey((int) table.getValueAt(row, 0));
						code.setCodeId((String) table.getValueAt(row, 1));
						code.setColumnName((String) table.getValueAt(row, 2));
						code.setValue((String) table.getValueAt(row, 3));
						code.setColumnCode((String) table.getValueAt(row, 4));
						codeDAO.updateCommonCode(code);
					}

				}
				JOptionPane.showMessageDialog(null, "내용들이 저장되었습니다.");
			}
		});
		panel.add(btnNewButton_1, "3, 1");

		JButton btnNewButton_2 = new JButton("삭제");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRows[] = table.getSelectedRows();
				for (int row : selectedRows) {
					CommonCodeDAO codeDAO = new CommonCodeDAO();
					if (table.getValueAt(row, 0) != null) {
						CommonCode code = codeDAO.findByPkey((int) table.getValueAt(row, 0));
						// codeDAO.delCommonCode(code);
					}
					System.out.println(row);
					model.removeRow(row);

				}
				JOptionPane.showMessageDialog(null, "선택된 내용들이 삭제되었습니다.");
				tableSizeSetting();
			}
		});
		panel.add(btnNewButton_2, "5, 1");

		JPanel panel_3 = new JPanel();
		panel.add(panel_3, "7, 1, fill, fill");
		panel_3.setLayout(
				new FormLayout(
						new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(41dlu;default)"),
								FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"),
								FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, },
						new RowSpec[] { FormSpecs.DEFAULT_ROWSPEC, }));

		JComboBox comboBox = new JComboBox();
		panel_3.add(comboBox, "2, 1, fill, default");

		textField = new JTextField();
		panel_3.add(textField, "4, 1, fill, default");
		textField.setColumns(10);

		JButton btnNewButton_3 = new JButton("검색");
		panel_3.add(btnNewButton_3, "6, 1");

		JPanel panel_1 = new JPanel();
		add(panel_1, "1, 4, fill, fill");
		panel_1.setLayout(new FormLayout(new ColumnSpec[] { ColumnSpec.decode("default:grow"), },
				new RowSpec[] { RowSpec.decode("default:grow"), }));

		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, "1, 1, fill, fill");

		/*
		 * table definition part
		 */
		String columns[] = { "pkey", "특성코드", "칼럼명", "값", "칼럼코드" };
		this.model = new DefaultTableModel(columns, 0) {
			// Returning the Class of each column will allow different // renderers to be
			// used based on Class
			public Class getColumnClass(int column) {

				return getValueAt(0, column).getClass();
			}

			public boolean isCellEditable(int row, int col) {
				// Note that the data/cell address is constant,
				// no matter where the cell appears onscreen.
				if (col < 1) {
					return false;
				} else {
					return true;
				}
			}

		};
		list.forEach(entity -> {
			model.addRow(new Object[] { entity.getId(), entity.getCodeId(), entity.getColumnName(), entity.getValue(),
					entity.getColumnCode() });
		});
		table = new JTable();
		table.setModel(model);
		scrollPane.setViewportView(table);

		for (int i = 0; i < model.getRowCount(); i++) {
			table.setRowHeight(i, 70);
		}
		TableColumn ColumnSecond = table.getColumnModel().getColumn(2);

		JComboBox columnNameBox = new JComboBox();
		CommonCodeDAO dao = new CommonCodeDAO();
		List<String> list = dao.getColumnNames();
		
		columnNameBox.setEditable(true);
		for (String s : list) {
			columnNameBox.addItem(s);
		}

		ColumnSecond.setCellEditor(new DefaultCellEditor(columnNameBox));
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, centerRenderer);
		table.setDefaultRenderer(Integer.class, centerRenderer);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);

//		table.addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent evt) {
//				if (evt.getClickCount() == 3) {
//					System.out.println("triple-click");
//				} else if (evt.getClickCount() == 2) {
//					System.out.println("double-click - key : " + table.getValueAt(table.getSelectedRow(), 0));
//					// 상세보기 다이얼로그 출력
//					OrderDetailDialog dialog = new OrderDetailDialog((int) table.getValueAt(table.getSelectedRow(), 0));
//					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//					dialog.setVisible(true);
//				}
//			}
//		});
	}

	private void getDatas() {
		CommonCodeDAO dao = new CommonCodeDAO();
		this.list = dao.getCommonCodes();
	}

	private void tableSizeSetting() {
		model.fireTableDataChanged();
		table.revalidate();
		for (int i = 0; i < model.getRowCount(); i++) {
			table.setRowHeight(i, 70);
		}
	}

	public void refresh() {
		model.setRowCount(0);
		list.forEach(entity -> {
			model.addRow(new Object[] { entity.getId(), entity.getCodeId(), entity.getColumnName(), entity.getValue(),
					entity.getColumnCode() });
		});
	}

}