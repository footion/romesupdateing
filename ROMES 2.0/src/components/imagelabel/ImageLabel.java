package components.imagelabel;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageLabel extends JLabel implements MouseListener{
	String actionCommand =null;
	private ImageIcon icon;
	private ImageIcon clickedicon;
	private int widht,height;
	public ImageLabel(ImageIcon icon,ImageIcon clickedicon, String actionCommand) {
		this.icon= icon;
		this.clickedicon=clickedicon;
		
		this.setIcon(icon);
		
		this.actionCommand = actionCommand;
		this.addMouseListener(this);
	}
	public void changeIcon(ImageIcon icon) {
		this.icon=icon;this.clickedicon=icon;
	}
	public ImageLabel(ImageIcon icon, String actionCommand) {
		this.icon= icon;
		this.clickedicon=icon;
		
		this.setIcon(icon);
		
		this.actionCommand = actionCommand;
		this.addMouseListener(this);
	}

	public JLabel resizeLabel(JLabel label, int widht, int height) {
		
		ImageIcon icon = (ImageIcon) label.getIcon();
		Image originImg = icon.getImage();
		this.widht=widht;
		this.height=height;
		// 추출된 Image의 크기를 조절하여 새로운 Image객체 생성

		Image changedImg = originImg.getScaledInstance(widht, height, Image.SCALE_SMOOTH);

		// 새로운 Image로 ImageIcon객체를 생성

		ImageIcon Icon = new ImageIcon(changedImg);
		this.setPreferredSize(new Dimension(widht,height));
		label.setIcon(Icon);
		return label;

	}

	public String getActionCommand() {
		return actionCommand;
	}

	public void setActionCommand(String actionCommand) {
		this.actionCommand = actionCommand;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		this.setIcon(clickedicon);resizeLabel(this,widht,height);
		this.repaint();this.revalidate();
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		this.setIcon(icon);resizeLabel(this,widht,height);
		this.repaint();this.revalidate();
	}
}