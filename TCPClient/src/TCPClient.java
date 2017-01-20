import java.io.*;
import java.net.*;
public class TCPClient {
	final static int NUMBER_OF_BYTES_PER_PACKET =  61440;
	final static int NUMBER_OF_BYTES_PER_LINE =  2560;
	final static int NUMBER_OF_LINES_PER_FRAME =  240;
	final static int NUMBER_OF_BYTES_PER_FRAME = NUMBER_OF_BYTES_PER_LINE*NUMBER_OF_LINES_PER_FRAME;
	final static int NUMBER_OF_FRAMES =  1000;
	
	public static void main(String[] args) throws UnknownHostException, IOException {

		  Socket clientSocket = new Socket("192.168.1.10",7000);
//	   	  Socket clientSocket = new Socket("127.0.0.1",7015);
		  DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		  InputStream in = clientSocket.getInputStream();
		    DataInputStream dis = new DataInputStream(in);
		  
		  
		  System.out.println("Connected to server");
		  
		//  char[] buf=new char[737280];
		  byte[] byteBuf = new byte[NUMBER_OF_BYTES_PER_FRAME];

		  long totalByteCount=0;
		  int frameByteCount=0;
		  int bytesRecived=0;
		  int bytesRead=0;
		  long startTime = System.nanoTime(); 
		  
		// ... the code being measured ...    

		  Controller.start();
		  Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		  //get 1000 frames
		  for(int i=0;i<NUMBER_OF_FRAMES;i++){
			
			  frameByteCount=0;
			  bytesRecived=0;
			  bytesRead=0;
			  //get single frame
			  while(frameByteCount<NUMBER_OF_BYTES_PER_FRAME){	   
				  outToServer.writeByte(i);
				  bytesRecived=0;
				  //get single packet(24 ,lines)
				  while( bytesRecived<NUMBER_OF_BYTES_PER_PACKET){	

					  try{
		
						  bytesRead=dis.read(byteBuf, frameByteCount,NUMBER_OF_BYTES_PER_PACKET);
						  if(bytesRead<NUMBER_OF_BYTES_PER_PACKET){
							  System.out.println("Partial Packet: "+bytesRead);
						  }
					  }
					  catch(java.lang.IndexOutOfBoundsException e){
						  System.out.println(frameByteCount+":"+e);
						  break;
					  }
					  bytesRecived+=bytesRead;
					  frameByteCount+=bytesRead;   
					  
				  }
				   
			  }
			  totalByteCount+=frameByteCount;
		
			  FrameBuffer.addToBuffer(byteBuf);
				
	
		  }
		  
		  long estimatedTime = System.nanoTime() - startTime;
		  
		  System.out.println("frame received,fps:"+totalByteCount/(NUMBER_OF_BYTES_PER_FRAME)/(estimatedTime/1000000000.0)+" time: "+estimatedTime/1000000+"ms");

		  clientSocket.close();

	}

}
