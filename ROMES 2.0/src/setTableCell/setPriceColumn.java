package setTableCell;


import java.awt.Component;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


@SuppressWarnings("serial")
public class setPriceColumn extends DefaultTableCellRenderer{
	NumberFormat numberFormat = NumberFormat.getCurrencyInstance( Locale.KOREA );
	public setPriceColumn() {
		this.setHorizontalAlignment(JLabel.RIGHT);
	}
	@Override
	public Component getTableCellRendererComponent(JTable arg0, Object value, boolean arg2, boolean arg3, int arg4,
			int arg5) {
		 try {
			Number n = NumberFormat.getInstance().parse((String) value);
			value=numberFormat.format(n);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return super.getTableCellRendererComponent(arg0, value, arg2, arg3, arg4, arg5);
	}
}
