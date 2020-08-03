package selectFrame;

import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import factory.colorFactory;

public class selectTypeBtn {
	JRadioButton enumTypeBtn;
	JRadioButton listTypeBtn;
	ButtonGroup group;
	public selectTypeBtn(String firstRadioText, String secondRadioText) {
		enumTypeBtn = new JRadioButton(firstRadioText);
		listTypeBtn = new JRadioButton(secondRadioText);
		enumTypeBtn.setFont(new Font("Microsoft YaHei",Font.BOLD,13));
		listTypeBtn.setFont(new Font("Microsoft YaHei",Font.BOLD,13));
		group = new ButtonGroup();
		group.add(enumTypeBtn);
		group.add(listTypeBtn);
//		enumTypeBtn.setBackground(colorFactory.PANEL_COLOR);
//		listTypeBtn.setBackground(colorFactory.PANEL_COLOR);
	}
}
