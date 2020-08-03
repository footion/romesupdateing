package factory;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextField;

import eventListener.deleteProfileEvent;
import eventListener.textFieldEvent;
import functions.managerProfile;
import image.url;
import layoutSetting.basicIconBtn;
import layoutSetting.basicLabel;
import layoutSetting.basicPanel;
import layoutSetting.basicTabbedPane;
import layoutSetting.basicTextArea;
import layoutSetting.basicTextField;
import layoutSetting.colonLabel;
import layoutSetting.emptyLabel;

public class componentFactory {

	public componentFactory() {

	}

	public Component createInputBar(String labelText, int textSize, String textName) {
		basicTextField textField = new basicTextField(textSize);
		textField.setHorizontalAlignment((int) TextField.CENTER_ALIGNMENT);
		textField.setName(textName);
		textField.setFont(new Font(fontFactory.BASIC_FONT,Font.PLAIN,textSize+1));
		basicLabel label = new basicLabel(labelText);
		label.setFont(new Font(fontFactory.BASIC_FONT,Font.BOLD,textSize+2));
		basicPanel panel = new basicPanel();
		panel.add(label);
		panel.add(new colonLabel());
		panel.add(textField);
		return panel;
	}
	public Component createInputBar_NoFont(String labelText, int textSize, String textName) {
		basicTextField textField = new basicTextField(textSize);
		textField.setHorizontalAlignment((int) TextField.CENTER_ALIGNMENT);
		textField.setName(textName);
		basicLabel label = new basicLabel(labelText);
		basicPanel panel = new basicPanel();
		panel.add(label);
		panel.add(new colonLabel());
		panel.add(textField);
		return panel;
	}

	public Component createEventInputBar(String labelText, int textSize, String textName,basicTabbedPane tabbedPane,Container container) {
		basicTextField textField = new basicTextField(textSize);
		textField.setHorizontalAlignment((int) TextField.CENTER_ALIGNMENT);
		textField.setEditable(false);
		textField.addMouseListener(new textFieldEvent(tabbedPane,container));
		textField.setFont(new Font(fontFactory.BASIC_FONT,Font.PLAIN,textSize+1));
		textField.setName(textName);
		basicLabel label = new basicLabel(labelText);
		label.setFont(new Font(fontFactory.BASIC_FONT,Font.BOLD,textSize+2));
		basicPanel panel = new basicPanel();
		panel.add(label);
		panel.add(new colonLabel());
		panel.add(textField);
		return panel;
	}

	public Component createTodayBar(String labelText, int textSize,String textName) {
		basicTextField textField = new basicTextField(textSize);
		textField.setHorizontalAlignment((int) TextField.CENTER_ALIGNMENT);
		textField.setFont(new Font(fontFactory.BASIC_FONT,Font.PLAIN,textSize+1));
		textField.setEditable(false);
		textField.setName(textName);
		Date today = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy. MM. dd. hh:mm:ss");
		textField.setText(format.format(today));

		basicLabel label = new basicLabel(labelText);
		label.setFont(new Font(fontFactory.BASIC_FONT,Font.BOLD,textSize+2));
		basicPanel panel = new basicPanel();
		panel.add(label);
		panel.add(new colonLabel());
		panel.add(textField);
		return panel;
	}

	public Component createRemarks(String labelName,String textName, int col, int row) {
		basicTextArea textArea = new basicTextArea(null, col, row);
		textArea.textArea.setName(textName);
		textArea.textArea.setFont(new Font(fontFactory.BASIC_FONT,Font.PLAIN,row-2));
		basicLabel label = new basicLabel(labelName);
		basicPanel panel = new basicPanel();
		label.setFont(new Font(fontFactory.BASIC_FONT,Font.BOLD,row));
		panel.add(label);
		panel.add(new colonLabel());
		panel.add(textArea);
		return panel;
	}
	public Component createRemarks_NoFont(String labelName,String textName, int col, int row) {
		basicTextArea textArea = new basicTextArea(null, col, row);
		textArea.textArea.setName(textName);
		basicLabel label = new basicLabel(labelName);
		basicPanel panel = new basicPanel();
		panel.add(label);
		panel.add(new colonLabel());
		panel.add(textArea);
		return panel;
	}
//	public Component CompanyInfoBtn(Container container,basicTabbedPane tabbedPane) {
//		basicIconBtn btn = new basicIconBtn(url.LINK_ICON, 14, 14);
//		btn.setClean(false, false, true);
//		btn.setPreferredSize(new Dimension(25, 25));
//		btn.setToolTipText("Info");
//		btn.addActionListener(new infoBtnEvent_Company(tabbedPane,container));
//		return btn;
//	}
	public void addInfoBtn(basicPanel panel, String type,JTextField textField,basicTabbedPane tabbedPane) {
		basicIconBtn btn = new basicIconBtn(url.LINK_ICON, 14, 14);
		btn.setClean(false, false, true);
		btn.setPreferredSize(new Dimension(25, 25));
		btn.setToolTipText("Info");
		panel.add(btn);
	}

	public Component addEmptyLabel() {
		emptyLabel label = new emptyLabel("");
		return label;
	}

	public Component profilePanel() {
		basicPanel profilePanel = new basicPanel();
		profilePanel.setLayout(new GridLayout(0, 3));
		managerProfile beginningProfile = new managerProfile();
		beginningProfile.delBtn.addActionListener(new deleteProfileEvent(profilePanel, beginningProfile));
		//profilePanel.add(beginningProfile);
		return profilePanel;

	}
}
