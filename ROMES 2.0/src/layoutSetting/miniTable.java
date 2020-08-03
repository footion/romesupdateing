package layoutSetting;

import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import factory.colorFactory;
import factory.fontFactory;

@SuppressWarnings("serial")
public class miniTable extends JScrollPane {
	public JTable table;
	public DefaultTableModel model;
	
	public miniTable(String[] col) {
		model = new DefaultTableModel(col, 0);
		// use groupcolumn --> table=new JTable();
		table = new JTable(model) {
			@Override
			// first column editable(false);
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		setting();
	}
	public miniTable(String[] col, int setEditableColumn,String type) {
		model = new DefaultTableModel(col, 0);
		// use groupcolumn --> table=new JTable();
		table = new JTable(model) {
			@Override
			// first column editable(false);
			public boolean isCellEditable(int row, int column) {
				if (type.equals("RO")&&column==4) {
					return false;
				}
				if (column == setEditableColumn) {
					return false;
				} else
					return true;
			}
		};
		setting();
	}
	public miniTable(String[] col, int btnColNo, int btn2ColNo) {
		model = new DefaultTableModel(col, 0);
		// use groupcolumn --> table=new JTable();
		table = new JTable(model) {
			@Override
			public boolean isCellEditable(int row, int column) {
				if(column==btnColNo)
					return true;
				if(column==btn2ColNo)
					return true;
				else
					return false;
			}
		};
		setting();
	}
	public miniTable(String[] col, int enableCol, int enableCol2,int enableCol3) {
		model = new DefaultTableModel(col, 0);
		// use groupcolumn --> table=new JTable();
		table = new JTable(model) {
			@Override
			public boolean isCellEditable(int row, int column) {
				if(column==enableCol||column==enableCol2||column==enableCol3)
					return true;
				else
					return false;
			}
		};
		setting();
	}

	public void setLineVisible(boolean visible) {
		table.setShowVerticalLines(visible);
		table.setShowHorizontalLines(visible);
	}
	void setting() {
		// setting
		table.setBackground(colorFactory.TABLE_COLOR);
		table.getTableHeader().setBackground(colorFactory.TABLE_HEADER_COLOR);
		BevelBorder bevelBorder = new BevelBorder(BevelBorder.RAISED);
		table.setBorder(bevelBorder);
		this.getViewport().setBackground(colorFactory.PANEL_COLOR);
		this.getViewport().add(table);
		
		table.setAutoCreateRowSorter(true);
		TableRowSorter tablesorter = new TableRowSorter(table.getModel());
		table.setRowSorter(tablesorter);
	}
}
