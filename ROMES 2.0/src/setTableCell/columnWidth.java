package setTableCell;

import javax.swing.JTable;

public class columnWidth {
	public void setColumnWidth(JTable table,String string, int size) {
		table.getColumn(string).setPreferredWidth(size);
	}
}
