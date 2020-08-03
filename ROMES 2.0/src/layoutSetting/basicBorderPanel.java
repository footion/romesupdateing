package layoutSetting;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import factory.colorFactory;

@SuppressWarnings("serial")
public class basicBorderPanel extends JPanel{
	public basicBorderPanel() {
		LineBorder border = new LineBorder(colorFactory.BORDER_COLOR, 2);
		setBorder(border);
		this.setBackground(colorFactory.PANEL_COLOR);
	}
	public basicBorderPanel(Color color,int thick) {
		LineBorder border = new LineBorder(color, thick);
		setBorder(border);
		this.setBackground(colorFactory.PANEL_COLOR);
	}
	public basicBorderPanel(Color color,int thick, String Null) {
		LineBorder border = new LineBorder(color, thick);
		setBorder(border);
	}
}
