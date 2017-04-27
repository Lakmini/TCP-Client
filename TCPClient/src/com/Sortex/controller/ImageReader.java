package com.Sortex.controller;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.awt.image.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import java.nio.file.Path;

public class ImageReader {
	int width;
	int height;
	int oneFrameLength;
	byte[][] data;
	BufferedImage image;

	public ImageReader(int width, int height) {
		this.width = width;
		this.height = height;

	}
	
	public BufferedImage getBufferedImage24bit(String filename) {
		try {

			Path path = Paths.get(filename);
			//read pixels 48bit RGB
			byte[] rawData = Files.readAllBytes(path);
			byte[] rawData8bit = new byte[width*height*3];
			
			//convert to 24bit RGB
			for(int i=0;i<width*height*6;i+=2){
				//copy MSB
				rawData8bit[i/2] = rawData[i+1];
			}
			
			//create image
			image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			
			//fill image data
			int pixelCount=0;
			
			for(int i=0;i<height;i++){
				for(int j=0;j<width;j++){
					//create 32 bit int pixel 0|R|G|B
					int pixel = (rawData8bit[pixelCount+2] & 0xFF) | ((rawData8bit[pixelCount+1] & 0xFF) << 8) | ((rawData8bit[pixelCount] & 0xFF) << 16);
					image.setRGB(j, i, pixel);
					pixelCount+=3;
				}
			}
			return image;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

}
