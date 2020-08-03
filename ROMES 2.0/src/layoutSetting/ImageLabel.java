package layoutSetting;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class ImageLabel extends JLabel{
	Image img;
	ImageIcon icon;
	String URL;
	String imgPath;
	public ImageLabel(String url) {
		URL=url;
		icon = new ImageIcon(url);
		setImageSize(120,90);
	}
	public void setImageFromDB(byte[] Byte) {
		icon =convertToImg(Byte);
		setImageSize(120,90);
	}
	public void setImageSize(int width,int height) {
		img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		icon.setImage(img);
		this.setIcon(icon);
	}
	public void reset() {
		icon = new ImageIcon(URL);
		setImageSize(120,90);
	}
	public String getImgPath() {
		return imgPath;
	}
	public void saveImagePath(String imagePath) {
		imgPath=imagePath;
	}
	public void setImage(byte[] Byte) {
		icon =convertToImg(Byte);
	}
	ImageIcon convertToImg(byte[] Byte) {
		ImageIcon icon = new ImageIcon();
		ByteArrayInputStream bis = new ByteArrayInputStream(Byte);
		try {
			BufferedImage bImage2 = ImageIO.read(bis);
			icon= new ImageIcon(bImage2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return icon;
	}
}
