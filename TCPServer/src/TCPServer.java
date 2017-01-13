import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
public class TCPServer extends Thread {
	private ServerSocket serverSocket;
	   
	   public TCPServer(int port) throws IOException {
	      serverSocket = new ServerSocket(port);
	      serverSocket.setSoTimeout(10000000);
	   }

	   public void run() {
		   
		      while(true) {
		         try {
		            System.out.println("Waiting for client on port " + 
		               serverSocket.getLocalPort() + "...");
		            Socket server = serverSocket.accept();
		            
		            System.out.println("Just connected to " + server.getRemoteSocketAddress());
		            DataInputStream in = new DataInputStream(server.getInputStream());
		            
		            System.out.println(in.readUTF());
		          
		           // DataOutputStream out = new DataOutputStream(server.getOutputStream());
		            
		            
		          
		            
		            
		            String name = "inputimages/Video_Color_Test_Pattern.YUYV";
		            Path path = Paths.get(name);
					byte[] rawData = Files.readAllBytes(path);
					System.out.println(rawData);
					DataOutputStream out = new DataOutputStream(server.getOutputStream());
					out.writeInt(rawData.length);
					out.write(rawData);
		          //  server.close();
		            
		         }catch(SocketTimeoutException s) {
		            System.out.println("Socket timed out!");
		           // break;
		         }catch(IOException e) {
		            e.printStackTrace();
		           // break;
		         }
		      }
		   }
	   
	public static void main(String[] args) throws IOException {
		//int port = Integer.parseInt(args[0]);
		int port=7015;
	      try {
	         Thread t = new TCPServer(port);
	         t.start();
	      }catch(IOException e) {
	         e.printStackTrace();
	      }
	}

}
