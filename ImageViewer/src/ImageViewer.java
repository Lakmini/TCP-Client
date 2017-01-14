import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ImageViewer {

	public static void main(String[] args) throws IOException {
		ImageViewer viewer= new ImageViewer();
		byte[] rawData= viewer.readFile();
		BufferedImage image = viewer.convertImages(rawData);
		viewer.visibleImage(image);

	}
	
	public byte[] readFile() throws IOException{
		String name = "inputimages/Video_Color_Test_Pattern.YUYV";
        Path path = Paths.get(name);
		byte[] rawData = Files.readAllBytes(path);
		return rawData;
	}

	public BufferedImage convertImages(byte[] rawData) {
		int width = 0;
		int height = 0;
		// height and width of the image frame ????
		height = 960;
		width = 1280;
		BufferedImage currentImage;
		ReadYUYV ryuv = new ReadYUYV(width, height);
		ryuv.startReading(rawData);
		currentImage = ryuv.getImage();
		return currentImage;

	}
	
	public void visibleImage(BufferedImage image) {
		JFrame frame = new JFrame();
		frame.getContentPane().setLayout(new FlowLayout());
		frame.getContentPane().add(new JLabel(new ImageIcon(image)));
		frame.pack();
		frame.setVisible(true);
	}

}
