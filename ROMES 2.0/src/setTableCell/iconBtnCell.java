package setTableCell;

import java.awt.Component;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import factory.colorFactory;
import layoutSetting.basicIconBtn;

@SuppressWarnings("serial")
public class iconBtnCell extends AbstractCellEditor implements TableCellEditor,TableCellRenderer{
	basicIconBtn btn;
	public iconBtnCell(String url, int width,int height) {
		// TODO Auto-generated constructor stub
		btn = new basicIconBtn(url, width, height);
		btn.setClean(false, false, true);
		btn.setBackground(colorFactory.TABLE_COLOR);
	}
	public void setEvent(ActionListener actionListener) {
		btn.addActionListener(actionListener);
	}
	@Override
	public Object getCellEditorValue() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Component getTableCellRendererComponent(JTable arg0, Object arg1, boolean arg2, boolean arg3, int arg4,
			int arg5) {
		// TODO Auto-generated method stub
		return btn;
	}
	@Override
	public Component getTableCellEditorComponent(JTable arg0, Object arg1, boolean arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		return btn;
	}
}

