package functions;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextField;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JTextField;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import factory.colorFactory;
import factory.fontFactory;
import image.icon;
import image.url;
import layoutSetting.basicLabel;
import layoutSetting.basicPanel;
import layoutSetting.basicTextField;
import layoutSetting.colonLabel;

@SuppressWarnings("serial")
public class datePickerBar extends basicPanel{
	public JTextField text;
	public JButton button;
	public JDatePickerImpl datepicker;
	public datePickerBar(String labelText,int textSize,String textName) {
		drawDatePicker();
		for (int i = 0; i < datepicker.getComponentCount(); i++) {
			Component com = datepicker.getComponent(i);
			if (com instanceof JButton) {
				button = (JButton) com;
				//button.setUI(new basicBtnUI());
				button.setText(null);
				button.setPreferredSize(new Dimension(25,28));
				button.setBackground(Color.WHITE);
				button.setIcon(new icon(url.CALENDAR_ICON, 19, 19));
			} else if (com instanceof JTextField) {
				text = (JTextField) com;
				new basicTextField(0).setTextField(text);
				text.setName(textName);
				text.setBackground(Color.white);
				text.setHorizontalAlignment((int) TextField.CENTER_ALIGNMENT);
				text.setFont(new Font(fontFactory.BASIC_FONT,Font.PLAIN,textSize+1));
				text.setPreferredSize(new Dimension(250,30));
				text.setBackground(colorFactory.TEXTFIELD_COLOR);
			}
		}
		basicLabel label = new basicLabel(labelText);
		label.setFont(new Font(fontFactory.BASIC_FONT,Font.BOLD,textSize+2));
		this.add(label);
		this.add(new colonLabel());
		this.add(datepicker);
	}
	public datePickerBar() {
		drawDatePicker();
		for (int i = 0; i < datepicker.getComponentCount(); i++) {
			Component com = datepicker.getComponent(i);
			if (com instanceof JButton) {
				button = (JButton) com;
				button.setText(null);
				button.setPreferredSize(new Dimension(25,28));
				button.setBackground(Color.WHITE);
				button.setIcon(new icon(url.CALENDAR_ICON, 19, 19));
			} else if (com instanceof JTextField) {
				text = (JTextField) com;
				new basicTextField(0).setTextField(text);
			}
		}
		this.add(datepicker);
	}
	void drawDatePicker() {
		UtilDateModel datemodel = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datepanel = new JDatePanelImpl(datemodel, p);
		datepicker = new JDatePickerImpl(datepanel, new DateComponentFormatter());
		datepicker.setBackground(Color.white);
	}
}
