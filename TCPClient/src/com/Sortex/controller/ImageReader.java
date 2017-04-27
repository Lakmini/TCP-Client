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
	
	public BufferedImage getBufferedImage8bit(String filename) {
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
			
			image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			int pixelCount=0;
			
			//fill image data
			for(int i=0;i<height;i++){
				for(int j=0;j<width;j++){
					//create 32 bit int pixel 0|R|G|B
					int r = (rawData8bit[pixelCount+2] & 0xFF) | ((rawData8bit[pixelCount+1] & 0xFF) << 8) | ((rawData8bit[pixelCount] & 0xFF) << 16);
					image.setRGB(j, i, r);
					pixelCount+=3;
				}
			}
			return image;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public void startReading(String filename) {
		try {

			Path path = Paths.get(filename);
			byte[] rawData = Files.readAllBytes(path);
			oneFrameLength = (int) (width * height);
			image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			
			readByteArray(rawData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void readByteArray(byte[] raw) {
		byte[][][] pix = new byte[height][width * 2][3];
		final int Y1_OFFSET = 0;
		final int U_OFFSET = 1;
		final int Y2_OFFSET = 2;
		final int V_OFFSET = 3;
		int i = 0;
		byte y1, u, y2, v;
		for (int r = 0; r < height; r++) {
			for (int c = 0; c < width; c += 2, i += 4) {
				y1 = raw[i + Y1_OFFSET];
				u = raw[i + U_OFFSET];
				y2 = raw[i + Y2_OFFSET];
				v = raw[i + V_OFFSET];

				pix[r][c][0] = y1;
				pix[r][c][1] = u;
				pix[r][c][2] = v;

				pix[r][c + 1][0] = y2;
				pix[r][c + 1][1] = u;
				pix[r][c + 1][2] = v;
			}
		}
		for (int r = 0; r < height; r++) {
			for (int c = 0; c < width; c++) {
				image.setRGB(c, r,
						convertPixel(pix[r][c][0], pix[r][c][1], pix[r][c][2]));
			}
		}
	}

	public byte[] getByteData(int dt[]) {
		ByteBuffer byteBuffer = ByteBuffer.allocate(dt.length * 4);
		IntBuffer intBuffer = byteBuffer.asIntBuffer();
		intBuffer.put(dt);
		byte[] array = byteBuffer.array();
		return array;
	}

	protected int convertPixel(byte y, byte u, byte v) {
		int iy = y & 0xff;
		int iu = u & 0xff;
		int iv = v & 0xff;
		float fr = 1.164f * (iy - 16) + 1.596f * (iv - 128);
		float fg = 1.164f * (iy - 16) - 0.391f * (iu - 128) - 0.813f
				* (iv - 128);
		float fb = 1.164f * (iy - 16) + 2.018f * (iu - 128);
		int ir = (int) (fr > 255 ? 255 : fr < 0 ? 0 : fr);
		int ig = (int) (fg > 255 ? 255 : fg < 0 ? 0 : fg);
		int ib = (int) (fb > 255 ? 255 : fb < 0 ? 0 : fb);
		return (ir << 16) | (ig << 8) | (ib);
	}

	public BufferedImage getImage() {
		return image;
	}

}
