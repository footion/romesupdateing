package layoutSetting;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JButton;

import eventListener.infoBtnEvent_Company;
import image.icon;
import image.url;

@SuppressWarnings("serial")
public class basicIconBtn extends JButton{
	basicIconBtn basicIconBtn;
	public basicIconBtn(String url,int width,int height) {
		super(new icon(url, width, height));
		basicIconBtn=this;
		this.setBackground(Color.white);
	}
	public basicIconBtn() {
		basicIconBtn=this;
	}
	public void setClean(boolean Border, boolean focus, boolean content) {
		basicIconBtn.setBorderPainted(Border);
		basicIconBtn.setFocusPainted(focus);
		basicIconBtn.setContentAreaFilled(content);
	}
	public basicIconBtn setInfoBtn() {
		basicIconBtn = new basicIconBtn(url.LINK_ICON, 14, 14);
		basicIconBtn.setClean(false, false, true);
		basicIconBtn.setPreferredSize(new Dimension(25, 25));
		basicIconBtn.setToolTipText("Info");
		return basicIconBtn;
	}
}
