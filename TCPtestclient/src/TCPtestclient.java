import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

public class TCPtestclient {

	public static void main(String[] args) {

		TCPtestclient client = new TCPtestclient();

		client.process();

	}

	public void process() {

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			long t0 = System.currentTimeMillis();
			String serverName = "localhost";
			int port = 7015;
			int i = 1;
			byte[] message = null;
			DataInputStream dIn;
			int length = 0;
			Socket client;
			OutputStream outToServer;
			DataOutputStream out;
			InputStream inFromServer;
			TCPtestclient clientx = new TCPtestclient();
			public Queue<byte[]> queue = new LinkedList<byte[]>();

			@Override
			public void run() { // Function runs 60 times per second.
				if (System.currentTimeMillis() - t0 > 1000) {
					int k = 0;
					byte[] temp;
					while (!queue.isEmpty()) {
						System.out.println("done");
						temp = queue.poll();
						clientx.convertImages(temp, k);
						k++;
					}

					cancel();
				}

				try {
					// connecting msg
					System.out.println("Connecting to " + serverName + " on port " + port);

					client = new Socket(serverName, port);
					outToServer = client.getOutputStream();

					// send request to server(1)
					out = new DataOutputStream(outToServer);
					out.writeUTF(i + "");
					out.flush();

					// YUYV data from server
					inFromServer = client.getInputStream();
					dIn = new DataInputStream(inFromServer);
					length = dIn.readInt(); // read length of incoming message
					message = new byte[length];
					if (length > 0) {

						dIn.readFully(message, 0, message.length); // read the
																	// message
						// add message to queue
						queue.add(message);

					}

				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		}, 0, 10);

	}

	public void convertImages(byte[] rawData, int imageNumber) {
		int width = 0;
		int height = 0;
		// height and width of the image frame ????
		height = 960;
		width = 1280;
		BufferedImage currentImage;
		// save images to "outputImages" folder
		new File("outputImages").mkdir();
		ReadYUYV ryuv = new ReadYUYV(width, height);
		ryuv.startReading(rawData);
		currentImage = ryuv.getImage();
		String path = "outputImages/" + imageNumber + ".gif";
		try {

			ImageIO.write(currentImage, "GIF", new File(path));
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
