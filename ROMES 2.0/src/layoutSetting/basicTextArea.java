package layoutSetting;

import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;

import factory.colorFactory;
import factory.fontFactory;

@SuppressWarnings("serial")
public class basicTextArea extends JScrollPane{
	public JTextArea textArea;
	public basicTextArea(String string,int row,int col) {
		textArea = new JTextArea(string, row, col);
		BevelBorder bevelBorder = new BevelBorder(BevelBorder.LOWERED);
		textArea.setBorder(bevelBorder);
		textArea.setFont(new Font(fontFactory.BASIC_FONT,Font.PLAIN,14));
		textArea.setBackground(colorFactory.TEXTFIELD_COLOR);
		textArea.setLineWrap(true);
		this.getViewport().add(textArea);
	}
}
