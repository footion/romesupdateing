package eventListener;

import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JFileChooser;

import factory.ImgFactory;
import layoutSetting.ImageLabel_HJ;

public class ImageSelectEvent implements MouseListener{
	ImageLabel_HJ imageLabel;
	int width=120;
	int height=90;
	Container container;
	public ImageSelectEvent(Container container,ImageLabel_HJ imageLabel) {
		this.imageLabel=imageLabel;
		this.container=container;
	}
	public ImageSelectEvent(Container container,ImageLabel_HJ imageLabel,int width, int height) {
		this.imageLabel=imageLabel;
		this.container=container;
		this.width=width;
		this.height=height;
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.showOpenDialog(container);
		System.out.println("Draw FileChooser");
		File selectedFile = fileChooser.getSelectedFile();
		
		if (selectedFile.getAbsolutePath() != null) {
			String imagePath = selectedFile.getAbsolutePath();
			imageLabel.saveImagePath(imagePath);
			imageLabel.setImage(ImgFactory.ImgToBytes(imagePath));
			imageLabel.setImageSize(width, height);
			System.out.println("changed image");
		}	
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
} 
