//package com.Sortex.controller;

import java.io.*;
import java.net.*;
import java.util.Timer;

public class TCPClient {
	final static int NUMBER_OF_BYTES_PER_PACKET = 61440;
	final static int NUMBER_OF_BYTES_PER_LINE = 2560;
	final static int NUMBER_OF_LINES_PER_FRAME = 240;
	final static int NUMBER_OF_BYTES_PER_FRAME = NUMBER_OF_BYTES_PER_LINE * NUMBER_OF_LINES_PER_FRAME;
	final static int NUMBER_OF_FRAMES = 500;
	final static int HEADER_VALUE1=192;
	final static int HEADER_VALUE2=64;
	private static Timer timer = new Timer();
	static Socket clientSocket;
	static DataOutputStream outToServer;
	static InputStream in;
	static DataInputStream dis;
	
	

	public static void buildServerConnection() throws UnknownHostException, IOException {
		clientSocket = new Socket("192.168.1.10", 7000);
		outToServer = new DataOutputStream(clientSocket.getOutputStream());
		in = clientSocket.getInputStream();
		dis = new DataInputStream(in);
		System.out.println("Connected to server");
	}
	public static void sendTestParameters(int param1, int param2, int param3) throws UnknownHostException, IOException {
		buildServerConnection();
		
		byte [] paramBuffer=new byte[4];
		paramBuffer[3]=toByte(HEADER_VALUE2);
		paramBuffer[2]=toByte(param1) ;
		paramBuffer[1]=toByte(param2);
		paramBuffer[0]=toByte(param3);
		
		outToServer.write(paramBuffer);
		//byte successMessage=dis.readByte();
		//System.out.println("Success Message : "+successMessage);
		clientSocket.close();
		

	}
	public static void sendParameters(int _R, int _G, int _B) throws UnknownHostException, IOException {
		buildServerConnection();
		
		byte [] paramBuffer=new byte[4];
		paramBuffer[3]=toByte(HEADER_VALUE1);
		paramBuffer[2]=toByte(_R) ;
		paramBuffer[1]=toByte(_G);
		paramBuffer[0]=toByte(_B);
		
		outToServer.write(paramBuffer);
		//byte successMessage=dis.readByte();
		//System.out.println("Success Message : "+successMessage);
		clientSocket.close();
		

	}
	public static byte toByte(int number) {
	    int tmp = number & 0xff;
	    return (byte) ((tmp & 0x80) == 0 ? tmp : tmp - 256);
	}

	// note :main method changed as train()
	public static void train() throws UnknownHostException, IOException {
		buildServerConnection();
		byte[] _32bitframe = new byte[4];
		for (byte b1 : _32bitframe) {
			b1=0;
			
		}
		

		byte[] byteBuf = new byte[NUMBER_OF_BYTES_PER_FRAME];

		int frameByteCount = 0;
		int bytesRecived = 0;
		int bytesRead = 0;
		long startTime = System.nanoTime();

		// ... the code being measured ...

		Controller.start(NUMBER_OF_FRAMES);
		Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		// get 1000 frames
		for (int i = 0; i < NUMBER_OF_FRAMES; i++) {
			// long start = System.nanoTime();
			frameByteCount = 0;
			bytesRecived = 0;
			bytesRead = 0;

			// get single frame
			while (frameByteCount < NUMBER_OF_BYTES_PER_FRAME) {
				outToServer.write(_32bitframe);
				// frameNumber = dis.readInt();

				bytesRecived = 0;
				// get single packet(24 ,lines)
				while (bytesRecived < NUMBER_OF_BYTES_PER_PACKET) {

					// try{

					bytesRead = dis.read(byteBuf, frameByteCount, NUMBER_OF_BYTES_PER_PACKET);
					// if(bytesRead<NUMBER_OF_BYTES_PER_PACKET){
					// System.out.println("Partial Packet: "+bytesRead);
					// }

					// }
					// catch(java.lang.IndexOutOfBoundsException e){
					// System.out.println(frameByteCount+":"+e);
					// frameByteCount=0;
					// break;
					// }
					bytesRecived += bytesRead;
					frameByteCount += bytesRead;

				}

			}
			// totalByteCount+=frameByteCount;
			// FileHandler.writeFile(byteBuf,new String(i+""));
			FrameBuffer.addToBuffer(byteBuf);
			// long time = System.nanoTime() - start;
			// System.out.println(" time: "+time/1000000+"ms");

		}

		long estimatedTime = System.nanoTime() - startTime;

		System.out.println("frame received,fps:" + NUMBER_OF_FRAMES / (estimatedTime / 1000000000.0) + " time: "
				+ estimatedTime / 1000000 + "ms");

		FileHandler.convertToRGB(1280, NUMBER_OF_LINES_PER_FRAME, "rowdataoutputImages");
		clientSocket.close();

		System.out.println("Converting Succefull");

	}

}
