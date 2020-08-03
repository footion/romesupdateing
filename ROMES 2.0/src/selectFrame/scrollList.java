package selectFrame;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;

import entity.Company;
import factory.colorFactory;
import layoutSetting.basicSrollPane;

public class scrollList extends JScrollPane{
	public JList list;
	public scrollList(JList dataList) {
		super(dataList);
		list = dataList;
		BevelBorder bevelBorder = new BevelBorder(BevelBorder.LOWERED);
		//list.setBackground(colorFactory.TEXTFIELD_COLOR);
		this.setBorder(bevelBorder);
	}
}
