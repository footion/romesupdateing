package layoutSetting;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import factory.colorFactory;
import factory.fontFactory;

public class titleBorderPanel extends basicPanel{
	public titleBorderPanel(String string) {
		TitledBorder border = new TitledBorder(new LineBorder(colorFactory.BORDER_COLOR,2),string);
		border.setTitleFont(new Font(fontFactory.TITLE_FONT,Font.BOLD,16));
		this.setBorder(border);
	}
}
