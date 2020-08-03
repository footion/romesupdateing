package setTableCell;

import java.awt.Component;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Locale;

import javax.swing.DefaultCellEditor;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.NumberFormatter;

import factory.instanceofFactory;

@SuppressWarnings("serial")
public class numberCellEditor extends DefaultCellEditor {
	
	public numberCellEditor() {
		super(new JTextField());
	}

	@Override
	public Object getCellEditorValue() {
		// get content of textField
		String str = (String) super.getCellEditorValue();
		if (str == null) {
			str = "0";
			return str;
		}
		if (str.length() == 0) {
			str = "0";
			return str;
		}
		if (!instanceofFactory.isStringInteger(str)) {
			JOptionPane.showMessageDialog(null, "Please enter a number.", "Wrong type", JOptionPane.ERROR_MESSAGE);
			str = "0";
			return str;
		}
		return str;
	}
}
