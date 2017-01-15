import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class ImageViewer {

	public static void main(String[] args) throws IOException {
		ImageViewer viewer= new ImageViewer();
		byte[] rawData= viewer.readFile();
		viewer.convertImages(rawData);
		

	}
	
	public byte[] readFile() throws IOException{
		String name = "inputimages/Video_Color_Test_Pattern.YUYV";
        Path path = Paths.get(name);
		byte[] rawData = Files.readAllBytes(path);
		return rawData;
	}

	public void convertImages(byte[] rawData) {
		int width = 0;
		int height = 0;
		// height and width of the image frame ????
		height = 960;
		width = 1280;
		BufferedImage currentImage;
		ReadYUYV ryuv = new ReadYUYV(width, height);
		ryuv.startReading(rawData);
		currentImage = ryuv.getImage();
		

	}

}
