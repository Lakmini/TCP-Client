package com.Sortex.controller;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class FileHandler {
	public static void convertToRGB(int width, int height, String folder) {
		System.out.println("inside convert method");

		BufferedImage currentImage;
	     final File dir = new File(folder);
		File[] list = dir.listFiles();
		// System.out.println(list.length);
		BufferedImage m;
		String fileName = null;
		
		if(folder=="stemRowData")
		{
			fileName="testInStem";
			
		}
		if(folder=="leafRowData")
		{
			fileName="testInLeaf";
		}
		new File(fileName).mkdir();
		for (int inc = 0; inc < list.length; inc++) {
			String name = dir.getName() + "/" + list[inc].getName();
			ImageReader ryuv = new ImageReader(width, height);
//			ryuv.startReading(name);
			currentImage = ryuv.getBufferedImage8bit(name);
			
			String path = fileName+"/" + list[inc].getName().split("[.]")[0]+".gif" ;
			try {

				ImageIO.write(currentImage, "GIF", new File(path));
			} catch (IOException e) {

				e.printStackTrace();
			}

		}

	}
	
	public static void saveAsGIF(int width,int height,String path){
		ImageReader imageReader = new ImageReader(width, height);

		BufferedImage currentImage = imageReader.getBufferedImage8bit(path);
		
		String outFileName = path.split("[.]")[0]+".gif" ;
		try {

			ImageIO.write(currentImage, "GIF", new File(outFileName));
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	public static void writeFile(byte[] rawData, String fileName,String directoryName) {
//		System.out.println("Writing row datafiles ..............");
		new File(directoryName).mkdir();
		// long starttime;
		// long time;
		String path = directoryName+"/" + fileName + ".bin";
		try {
			// starttime=System.nanoTime();
			Files.write(Paths.get(path), rawData);
			// time=System.nanoTime()-starttime;
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
}