package selectFrame;

import java.awt.Font;

import javax.swing.DropMode;
import javax.swing.JLabel;
import javax.swing.JPanel;

import factory.fontFactory;
import layoutSetting.UI_Button;
import layoutSetting.basicPanel;
import layoutSetting.basicTextField;

@SuppressWarnings("serial")
public class enumTypePanel extends basicPanel{
	public UI_Button button;
	public basicTextField textField;
	public enumTypePanel(String btnName, int textSize) {
		super(null);
		button = new UI_Button(btnName);
		textField = new basicTextField(textSize);
		textField.setFont(new Font(fontFactory.BASIC_FONT,Font.BOLD,13));
		this.add(textField);
		this.add(new JLabel(" "));
		this.add(button);
	}
}
