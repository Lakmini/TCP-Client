import java.awt.image.BufferedImage;
import java.util.*;
import java.io.*;
import java.net.*;

import javax.imageio.ImageIO;
public class TCPClient {

	public static void main(String[] args) throws UnknownHostException, IOException {
		//String serverName = args[0];
	    //  int port = Integer.parseInt(args[1]);
		String serverName="localhost";
		int port=7005;
		
		//?data type ??????????????
		Queue<Byte> queue = new LinkedList<Byte>();
		Byte response;

	      try {
	    	  //connecting msg
	         System.out.println("Connecting to " + serverName + " on port " + port);
	         Socket client = new Socket(serverName, port);
	         //connection success message
	         System.out.println("Just connected to " + client.getRemoteSocketAddress());
	         OutputStream outToServer = client.getOutputStream();
	         DataOutputStream out = new DataOutputStream(outToServer);
	         //send msg to server(1 or 0)
	         out.writeUTF("Hello from " + client.getLocalSocketAddress());
	         //YUYV data from server
	         
	         //to do
	         //here write YUYV to a folder
	         InputStream inFromServer = client.getInputStream();
	         DataInputStream in = new DataInputStream(inFromServer);
	         //add to queue
	         while(in.readByte()!=0)
	         {
	        	 response=in.readByte();
	        	 queue.add(response);

	         }
	         //System.out.println("Server says " + in.readUTF());
	         
	         client.close();
	         //to do : write the queue element as yuv files in to a folder
	         
	         //convert images and save to output folder
	         TCPClient clientx=new TCPClient();
	         clientx.convertImages();
	      }catch(IOException e) {
	         e.printStackTrace();
	      }
	}
	public void convertImages() {
		int width = 0;
		int height = 0;
		try {
			BufferedReader inConf = new BufferedReader(new FileReader("config.txt"));
			height = Integer.valueOf(inConf.readLine().split(":")[1]);
			width = Integer.valueOf(inConf.readLine().split(":")[1]);
			inConf.close();
		} catch (IOException e) {
		}
		BufferedImage currentImage;
		final File dir = new File("inputfiles");
		File[] list = dir.listFiles();
		System.out.println(list.length);
		BufferedImage m;
		new File("outputImages").mkdir();
		for (int inc = 0; inc < list.length; inc++) {
			String name = "inputfiles/" + list[inc].getName();
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