import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FileHandler {
	public static void convertImages(byte[] rawData, int imageNumber) {
		int width = 0;
		int height = 0;
		// height and width of the image frame ????
		height = 200;
		width = 1280;
		BufferedImage currentImage;
		// save images to "outputImages" folder
		new File("outputImages").mkdir();
		ReadYUYV ryuv = new ReadYUYV(width, height);
		ryuv.startReading(rawData);
		currentImage = ryuv.getImage();
		String path = "outputImages/" + imageNumber + ".bmp";
		try {

			ImageIO.write(currentImage, "BMP", new File(path));
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
}
