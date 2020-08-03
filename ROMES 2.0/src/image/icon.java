package image;

import java.awt.Image;

import javax.swing.ImageIcon;


@SuppressWarnings("serial")
public class icon extends ImageIcon{
	public icon(String url,int width,int height){
		super(url);
		Image image = this.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		this.setImage(image);
	}
}
