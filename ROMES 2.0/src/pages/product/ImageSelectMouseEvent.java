package pages.product;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

import components.imagelabel.ImageLabel;
import img.ImgFactory;

public class ImageSelectMouseEvent implements MouseListener {

	private FormUpdatePage formPanel;
	private ImageLabel imageLabel;

	public ImageSelectMouseEvent(FormUpdatePage formPanel, ImageLabel imageLabel) {
		this.formPanel = formPanel;
		this.imageLabel = imageLabel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		int result = fileChooser.showOpenDialog(formPanel);
		File selectedFile = fileChooser.getSelectedFile();
		if (selectedFile.getAbsolutePath() != null) {
			String imagePath = selectedFile.getAbsolutePath();
			formPanel.setImgPath(imagePath);
			
			ImageIcon icon=new ImageIcon(ImgFactory.ImgToBytes(imagePath));
			imageLabel.removeAll();
			imageLabel.changeIcon(icon);
			imageLabel.setIcon(icon);
			imageLabel.resizeLabel(imageLabel, 70, 70);
			imageLabel.repaint();imageLabel.revalidate();
//			formPanel.imagePanel.add(imageLabel);
//			formPanel.imagePanel.repaint();
//			formPanel.imagePanel.revalidate();
			System.out.println("image changed complete");
		}
		String imgPath= formPanel.getImgPath();
		if(imgPath== null) {

		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
