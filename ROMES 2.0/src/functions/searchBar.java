package functions;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import factory.colorFactory;
import layoutSetting.UI_Button;
import layoutSetting.basicBtn;
import layoutSetting.basicPanel;

@SuppressWarnings("serial")
public class searchBar extends basicPanel{
	JTextField searchtext;
	JComboBox combo;
	JCheckBox check;
	basicBtn searchBtn;
	public searchBar(String[] comboList) {
		searchtext = new JTextField(35);
		combo = new JComboBox(comboList);
		combo.setSelectedIndex(0);
		check = new JCheckBox("결과내 검색");
		check.setBackground(colorFactory.PANEL_COLOR);
		combo.setBackground(colorFactory.PANEL_COLOR);
		searchBtn = new basicBtn("Search");
		
		this.setLayout(new FlowLayout(FlowLayout.LEADING));
		this.add(combo);
		this.add(searchtext);
		this.add(check);
		this.add(searchBtn);
	}
}
