package layoutSetting;

import java.awt.FlowLayout;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class doubleButtonPanel extends basicPanel{
	public JButton leftBtn;
	public JButton rightBtn;
	public doubleButtonPanel(String leftBtnTitle,String rightBtnTitle) {
		leftBtn = new UI_Button(leftBtnTitle);
		rightBtn = new UI_Button(rightBtnTitle);
		this.setLayout(new FlowLayout(FlowLayout.TRAILING));
		this.add(leftBtn);
		this.add(rightBtn);
	}
	public doubleButtonPanel(String leftBtnTitle,String rightBtnTitle,String NULL) {
		leftBtn = new basicBtn(leftBtnTitle);
		rightBtn = new basicBtn(rightBtnTitle);
		this.setLayout(new FlowLayout(FlowLayout.TRAILING));
		this.add(leftBtn);
		this.add(rightBtn);
	}
}
