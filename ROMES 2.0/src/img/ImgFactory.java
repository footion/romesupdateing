package img;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImgFactory {
	static public byte[] ImgToBytes(String path) {
		BufferedImage bImage = null;
		try {
			bImage = ImageIO.read(new File(path));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	     ByteArrayOutputStream bos = new ByteArrayOutputStream();
	     try {
			ImageIO.write(bImage, "jpg", bos );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bos.toByteArray();
	}
	 
}
