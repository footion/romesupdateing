package functions;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import eventListener.cancelEvent;
import eventListener.editCompanyManager;
import factory.componentFactory;
import image.icon;
import image.url;
import layoutSetting.ImageLabel;
import layoutSetting.basicBorderPanel;
import layoutSetting.basicIconBtn;
import layoutSetting.basicPanel;
import registrationForm.R_companyManager;

@SuppressWarnings("serial")
public class managerProfile extends basicPanel{
	public String USERNAME="user name";
	public String POSITION = "job position";
	public String PHONE="000-0000-0000";
	public String EMAIL="aaa@uuuu.com";
	public String MEMO="";
	JLabel name;
	JLabel position;
	JLabel phone;
	JLabel email;
	JLabel memo;
	public basicIconBtn delBtn;
	public int imageWidth=69;
	public int imageHeight=82;
	private ImageLabel imageLabel;
	managerProfile profile;
	private byte[] imageByte;//for update function 
	public managerProfile() {
		profile=this;
		//profilePhoto
		basicBorderPanel profilePhoto = new basicBorderPanel();
		LineBorder border = new LineBorder(Color.LIGHT_GRAY, 1);
		profilePhoto.setBorder(border);
		profilePhoto.add(imageLabel=new ImageLabel(url.USER_ICON));
		imageLabel.setImageSize(imageWidth, imageHeight);
		//profileData
		basicPanel profileData = new basicPanel();
		profileData.setLayout(new BoxLayout(profileData, BoxLayout.Y_AXIS));
		
		//basicLabel idLabel = new basicLabel("id");
		name = new JLabel("이름 : "+USERNAME);
		position=new JLabel("직책 : "+POSITION);
		phone=new JLabel("번호 : "+PHONE);
		email=new JLabel("E-mail : "+EMAIL);
		memo=new JLabel("메모 : "+MEMO);
		profileData.add(name);
		profileData.add(position);
		profileData.add(phone);
		profileData.add(email);
		profileData.add(memo);
		//delBtn
		basicPanel btnPanel = new basicPanel();
		btnPanel.setLayout(new BorderLayout(0,50));
		delBtn = new basicIconBtn(url.DELBTN_ICON, 12, 12);
		delBtn.setClean(false, false, true);
		btnPanel.add(delBtn,BorderLayout.NORTH);
		componentFactory componentFactory = new componentFactory();
		btnPanel.add(componentFactory.addEmptyLabel(),BorderLayout.PAGE_END);
		
		this.add(profilePhoto);
		this.add(profileData);
		this.add(btnPanel);
		
		imageLabel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				R_companyManager frame=new R_companyManager();
				frame.btnPanel.leftBtn.addActionListener(new editCompanyManager(frame, profile));
				frame.btnPanel.leftBtn.addActionListener(new cancelEvent(frame));
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	public void refreshLabel() {
		name.setText("이름 : "+USERNAME);
		position.setText("직책 : "+POSITION);
		phone.setText("번호 : "+PHONE);
		email.setText("E-mail : "+EMAIL);
		memo.setText("메모 : "+MEMO);
	}
	public ImageLabel getImageLabel() {
		return imageLabel;
	}
	public void setImageLabel(ImageLabel imageLabel) {
		this.imageLabel = imageLabel;
	}
	public byte[] getImageByte() {
		return imageByte;
	}
	public void setImageByte(byte[] imageByte) {
		this.imageByte = imageByte;
	}
}
