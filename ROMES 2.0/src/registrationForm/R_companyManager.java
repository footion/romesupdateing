package registrationForm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import eventListener.ImageSelectEvent;
import eventListener.cancelEvent;
import eventListener.setTitleEvent;
import factory.componentFactory;
import factory.fontFactory;
import factory.nameFactory;
import factory.stringFactory;
import image.icon;
import image.url;
import layoutSetting.ImageLabel;
import layoutSetting.basicBorderPanel;
import layoutSetting.basicFrame;
import layoutSetting.basicLabel;
import layoutSetting.basicPanel;
import layoutSetting.doubleButtonPanel;
import layoutSetting.titleBorderPanel;

@SuppressWarnings("serial")
public class R_companyManager extends basicFrame{
	componentFactory componentFactory = new componentFactory();
	int textSize=23;
	R_companyManager frame;
	public doubleButtonPanel btnPanel;
	basicLabel name;
	public basicPanel leftNorth;
	public Box centerBox;
	private ImageLabel imageLabel;
	public basicBorderPanel profilePhoto;
	public int imageWidth=99;
	public int imageHeight=110;
	public static void main(String[] args) {
		new R_companyManager();
	}
	public R_companyManager() {
		super("담당자 등록");
		frame=this;
		//North Panel
		basicPanel northPanel = new basicPanel();
		northPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
		name = new basicLabel("Name");
		name.setName(nameFactory.COMPANY_MANAGER_NAME_N);
		name.setFont(new Font(fontFactory.BASIC_FONT,Font.BOLD,50));
		name.setForeground(Color.LIGHT_GRAY);
		name.setHorizontalAlignment(JLabel.LEFT);
		//leftNorth
		leftNorth = new basicPanel();
		leftNorth.setLayout(new BorderLayout());
		leftNorth.add(name,BorderLayout.NORTH);
		leftNorth.add(componentFactory.createInputBar_NoFont("직책", textSize, nameFactory.COMPANY_MANAGER_POSITION_TN),BorderLayout.CENTER);
		leftNorth.add(componentFactory.createInputBar_NoFont("번호", textSize, nameFactory.COMPANY_MANAGER_TEL_TN),BorderLayout.PAGE_END);
		//profilePhoto
		profilePhoto = new basicBorderPanel();
		LineBorder border = new LineBorder(Color.LIGHT_GRAY, 3);
		profilePhoto.setBorder(border);
		profilePhoto.add(imageLabel = new ImageLabel(url.USER_ICON));
		imageLabel.setImageSize(imageWidth, imageHeight);
		
		northPanel.add(leftNorth);
		northPanel.add(profilePhoto);
		
		//CenterPanel
		basicPanel centerPanel = new basicPanel();
		centerPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
		centerBox = Box.createVerticalBox();
		centerBox.add(componentFactory.createInputBar_NoFont("메일", textSize+9, nameFactory.COMPANY_MANAGER_EMAIL_TN));
		centerBox.add(componentFactory.createRemarks_NoFont("메모", nameFactory.COMPANY_MANAGER_REMARKS_TN,2,textSize+9));
		//centerBox.add(btnPanel = new doubleButtonPanel("Save", "Cancel"));
		centerPanel.add(centerBox);
		
		btnPanel = new doubleButtonPanel("Edit", "Cancel");
		btnPanel.leftBtn.setPreferredSize(new Dimension(70,28));
		btnPanel.rightBtn.setPreferredSize(new Dimension(70,28));
				
		titleBorderPanel borderPanel = new titleBorderPanel("담당자 등록");
		Box borderBox = Box.createVerticalBox();
		borderBox.add(northPanel);
		borderBox.add(Box.createVerticalStrut(-7));
		borderBox.add(centerPanel);
		//borderBox.add(btnPanel);
		borderPanel.add(borderBox);
		
		setEvent();
		this.setLayout(new BorderLayout(5,5));
		this.add(new JLabel(""),BorderLayout.NORTH);
		this.add(new JLabel(""),BorderLayout.WEST);
		this.add(borderPanel,BorderLayout.CENTER);
		this.add(new JLabel(""),BorderLayout.EAST);
		this.add(btnPanel,BorderLayout.PAGE_END);
		this.setVisible(true);
		this.setSize(555,362);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}
	private void setEvent() {
		name.addMouseListener(new setTitleEvent(name, "name"));
		btnPanel.rightBtn.addActionListener(new cancelEvent(this));
		imageLabel.addMouseListener(new ImageSelectEvent(this, imageLabel, imageHeight, imageHeight));
	}
	public ImageLabel getImageLabel() {
		return imageLabel;
	}
	public void setImageLabel(ImageLabel imageLabel) {
		this.imageLabel = imageLabel;
	}
}
