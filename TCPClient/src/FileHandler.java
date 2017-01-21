import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class FileHandler {
	public static void convertToRGB(int width, int height, String folder) {

		BufferedImage currentImage;
	     final File dir = new File(folder);
		File[] list = dir.listFiles();
		// System.out.println(list.length);
		BufferedImage m;
		new File("outputImages").mkdir();
		for (int inc = 0; inc < list.length; inc++) {
			String name = dir.getName() + "/" + list[inc].getName();
			ReadYUYV ryuv = new ReadYUYV(width, height);
			ryuv.startReading(name);
			currentImage = ryuv.getImage();
			String path = "outputImages/" + inc + ".gif";
			try {

				ImageIO.write(currentImage, "GIF", new File(path));
			} catch (IOException e) {

				e.printStackTrace();
			}

		}

	}

	public static void writeFile(byte[] rawData, int imageNumber) {
		new File("rowdataoutputImages").mkdir();
		// long starttime;
		// long time;
		String path = "rowdataoutputImages/" + imageNumber + ".bin";
		try {
			// starttime=System.nanoTime();
			Files.write(Paths.get(path), rawData);
			// time=System.nanoTime()-starttime;
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
}