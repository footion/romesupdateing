package setTableCell;

import java.awt.Component;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

@SuppressWarnings("serial")
public class setQuantityColumn extends DefaultTableCellRenderer{
	DecimalFormat format = new DecimalFormat("#,###°³");
	public setQuantityColumn() {
		this.setHorizontalAlignment(JLabel.RIGHT);
	}
	@Override
	public Component getTableCellRendererComponent(JTable arg0, Object value, boolean arg2, boolean arg3, int arg4,
			int arg5) {
		Number n;
		try {
			n = NumberFormat.getInstance().parse((String) value);
			value=format.format(n);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return super.getTableCellRendererComponent(arg0, value, arg2, arg3, arg4, arg5);
	}
}
