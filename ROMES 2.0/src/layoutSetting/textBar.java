package layoutSetting;

import java.awt.Font;
import java.awt.TextField;

import factory.fontFactory;

@SuppressWarnings("serial")
public class textBar extends basicPanel{
	public basicTextField textField;
	public textBar(String labelText, int textSize) {
		textField = new basicTextField(textSize);
		textField.setHorizontalAlignment((int) TextField.CENTER_ALIGNMENT);
		textField.setFont(new Font(fontFactory.BASIC_FONT,Font.PLAIN,textSize+1));
		basicLabel label = new basicLabel(labelText);
		label.setFont(new Font(fontFactory.BASIC_FONT,Font.BOLD,textSize+2));
		this.add(label);
		this.add(new colonLabel());
		this.add(textField);
	}
	public void setEventBar() {
		textField.setEditable(false);
	}
}
