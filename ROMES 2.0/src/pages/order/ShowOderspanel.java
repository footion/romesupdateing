package pages.order;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import entity.OrderHistory;
import entity.dao.OrderHistoryDAO;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class ShowOderspanel extends JPanel {
	private JTable table;
	private List<OrderHistory> list;
	private ShowOrderPanelMaster superPanel;

	/**
	 * Create the panel.
	 * 
	 * @param superPanel
	 */
	public ShowOderspanel(ShowOrderPanelMaster superPanel) {
		getDatas();
		this.superPanel = superPanel;

		String columns[] = { "pkey", "LOTNO", "제품", "주문량", "발주사", "주문날짜", "도착예정날짜", "작업자" };
		DefaultTableModel model = new DefaultTableModel(columns, 0) {
			// Returning the Class of each column will allow different // renderers to be
			// used based on Class
			public Class getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}

			public boolean isCellEditable(int row, int col) {
				// Note that the data/cell address is constant,
				// no matter where the cell appears onscreen.
				if (col < 8) {
					return false;
				} else {
					return true;
				}
			}

		};
		list.forEach(entity -> {
			model.addRow(new Object[] { entity.getId(), entity.getLotNo(), entity.getProduct().getName(),
					entity.getQuantity(), entity.getCompany().getCompanyName(), entity.getOrderDate(),
					entity.getPlannedArrivalDate(), entity.getWorker().getName() });
		});

		setLayout(new FormLayout(new ColumnSpec[] { ColumnSpec.decode("default:grow"), },
				new RowSpec[] { RowSpec.decode("default:grow"), }));

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "1, 1, fill, fill");

		table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		for (int i = 0; i < model.getRowCount(); i++) {
			System.out.println(i);
			table.setRowHeight(i, 70);
		}
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, centerRenderer);
		table.setDefaultRenderer(Integer.class, centerRenderer);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 3) {
					System.out.println("triple-click");
				} else if (evt.getClickCount() == 2) {
					System.out.println("double-click - key : " + table.getValueAt(table.getSelectedRow(), 0));
					// 상세보기 다이얼로그 출력
					OrderDetailDialog dialog = new OrderDetailDialog((int) table.getValueAt(table.getSelectedRow(), 0));
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				}
			}
		});

		superPanel.add(this, BorderLayout.CENTER);
	}

	private void getDatas() {
		OrderHistoryDAO dao = new OrderHistoryDAO();
		this.list = dao.getOrderHistory();

	}

}
