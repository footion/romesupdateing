package layoutSetting;

import java.awt.Font;
import java.awt.TextField;

import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import eventListener.textFieldEvent;
import factory.colorFactory;
import factory.fontFactory;

@SuppressWarnings("serial")
public class basicTextField extends JTextField{
	public basicTextField(int size) {
		super(size);
		setTextField(this);
	}
	public basicTextField(int size,String onlyView) {
		super(size);
		setTextField(this);
		this.setEditable(false);
	}
	public void setTextField(JTextField textField) {
		BevelBorder bevelBorder = new BevelBorder(BevelBorder.LOWERED);
		//textField.setFont(new Font(fontFactory.BASIC_FONT,Font.PLAIN,14));
		textField.setBorder(bevelBorder);
		textField.setBackground(colorFactory.TEXTFIELD_COLOR);
		//textField.setPreferredSize(new Dimension(80,30));
	}
}
