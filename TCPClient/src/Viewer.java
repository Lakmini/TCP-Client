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

public class Viewer {
	
	private static JFrame frame;
	private static ImageIcon icon;
	private static JLabel label;
	public static void initialize(){
		//viewer= new Viewer();
		frame = new JFrame();
		frame.getContentPane().setLayout(new FlowLayout());
		
		icon = new ImageIcon();
		label = new JLabel();
		label.setIcon(icon);
		frame.getContentPane().add(label);
		frame.pack();
		frame.setVisible(true);
	}
	public static void updateFrame(char[] buf) throws IOException {
		
//		byte[] rawData= viewer.readFile();
		BufferedImage image = convertImages(new String(buf).getBytes());
		visibleImage(image);
		//rawData= readFile();
		// image = convertImages(rawData);
		//visibleImage(image);

	}
	
	public static byte[] readFile() throws IOException{
		String name = "inputimages/Video_Color_Test_Pattern.YUYV";
        Path path = Paths.get(name);
		byte[] rawData = Files.readAllBytes(path);
		return rawData;
	}

	public static BufferedImage convertImages(byte[] rawData) {
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
	
	public static void visibleImage(BufferedImage image) {
		
		
		icon.setImage(image);
		frame.pack();
		frame.setVisible(true);
		//label.setIcon(icon);
		frame.pack();
		frame.setVisible(true);
		
		
	}

}
