package factory;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.TextField;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JPanel;
import javax.swing.JTextField;

import evevtListener.deleteProfileEvent;
import evevtListener.infoBtnEvent;
import evevtListener.textFieldEvent;
import functions.userProfile;
import image.url;
import layoutSetting.basicIconBtn;
import layoutSetting.basicLabel;
import layoutSetting.basicPanel;
import layoutSetting.basicTextArea;
import layoutSetting.basicTextField;
import layoutSetting.colonLabel;
import layoutSetting.emptyLabel;

public class componentFactory {

	public componentFactory() {

	}

	public Component createInputBar(String labelName, int textSize, String textName) {
		basicTextField textField = new basicTextField(textSize);
		textField.setHorizontalAlignment((int) TextField.CENTER_ALIGNMENT);
		textField.setName(textName);
		basicLabel label = new basicLabel(labelName);
		basicPanel panel = new basicPanel();
		panel.add(label);
		panel.add(new colonLabel());
		panel.add(textField);
		return panel;
	}

	public JPanel createEventInputBar(String labelName, int textSize, String textName) {
		basicTextField textField = new basicTextField(textSize);
		textField.setHorizontalAlignment((int) TextField.CENTER_ALIGNMENT);
		textField.setEditable(false);
		textField.addMouseListener(new textFieldEvent());
		textField.setName(textName);
		basicLabel label = new basicLabel(labelName);
		basicPanel panel = new basicPanel();
		panel.add(label);
		panel.add(new colonLabel());
		panel.add(textField);
		return panel;
	}

	public Component createTodayBar(String labelName, int textSize) {
		basicTextField textField = new basicTextField(textSize);
		textField.setHorizontalAlignment((int) TextField.CENTER_ALIGNMENT);
		textField.setEditable(false);
		Date today = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy. MM. dd. hh:mm:ss");
		textField.setText(format.format(today));

		basicLabel label = new basicLabel(labelName);
		basicPanel panel = new basicPanel();
		panel.add(label);
		panel.add(new colonLabel());
		panel.add(textField);
		return panel;
	}

	public Component createRemarks(String labelName,String textName, int col, int row) {
		basicTextArea textArea = new basicTextArea(null, col, row);
		textArea.textArea.setName(textName);
		basicLabel label = new basicLabel(labelName);
		basicPanel panel = new basicPanel();
		panel.add(label);
		panel.add(new colonLabel());
		panel.add(textArea);
		return panel;
	}

	public void addInfoBtn(basicPanel panel, String type,JTextField textField) {
		basicIconBtn btn = new basicIconBtn(url.LINK_ICON, 14, 14);
		btn.setClean(false, false, true);
		btn.setPreferredSize(new Dimension(25, 25));
		btn.setToolTipText("Detail");
		btn.addActionListener(new infoBtnEvent(type,textField));
		panel.add(btn);
	}

	public Component addEmptyLabel() {
		emptyLabel label = new emptyLabel("");
		return label;
	}

	public Component profilePanel() {
		basicPanel profilePanel = new basicPanel();
		profilePanel.setLayout(new GridLayout(0, 3));
		userProfile beginningProfile = new userProfile();
		beginningProfile.delBtn.addActionListener(new deleteProfileEvent(profilePanel, beginningProfile));
		profilePanel.add(beginningProfile);
		return profilePanel;

	}
}
