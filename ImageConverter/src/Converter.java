import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Converter {

	public static void main(String[] args) {
	Converter converter = new Converter();
			converter.convertToRGB();

	}
	
	public void convertToRGB(){
		int width = 1280;
		int height = 960;
		
		BufferedImage currentImage;
		final File dir = new File("input");
		File[] list = dir.listFiles();
		System.out.println(list.length);
		BufferedImage m;
		new File("outputImages").mkdir();
		for (int inc = 0; inc < list.length; inc++) {
			String name = "input/" + list[inc].getName();
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

}
