import java.io.*;
import java.net.*;
public class TCPClient {

	public static void main(String[] args) throws UnknownHostException, IOException {
		//String sentence;
		  //String modifiedSentence;
		 FileOutputStream fos = new FileOutputStream("img.bin");
		 // BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
		  Socket clientSocket = new Socket("192.168.1.10",7000);
		  DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		  BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		  System.out.println("Connected to server");
		  
		  char[] buf=new char[26004800];
		 // char[] buf=new char[129003048];
		  
		 // for(int i=0;i<20;i++){
		  outToServer.writeByte(1);
		  int totalBytes=0;
		  int numberOfBytes=0;
		  int bytesRecived=0;
		  int bytesRead=0;
		  long startTime = System.nanoTime();    
		// ... the code being measured ...    
		 // Dispay.showFrame();
		  Viewer.initialize();
		  for(int i=0;i<100;i++){
			  numberOfBytes=0;
			  bytesRecived=0;
			  bytesRead=0;
			  while(numberOfBytes<258048+61440){	   
				  outToServer.writeByte(1);
				  bytesRecived=0;
				  while( bytesRecived<61440){	
	//				  if(numberOfBytes<258048000+61440){
	//					  break;
	//				  }
					  try{
					  bytesRead= inFromServer.read(buf,numberOfBytes,61440); 
					  }
					  catch(java.lang.IndexOutOfBoundsException e){
						  System.out.println(numberOfBytes);
						  break;
					  }
					  bytesRecived+=bytesRead;
					  numberOfBytes+=bytesRead;
					 // System.out.println(numberOfBytes);
				  }
				  		  
			  }
			  totalBytes+=numberOfBytes;
			  Viewer.updateFrame(buf);
		  }
		  
		  long estimatedTime = System.nanoTime() - startTime;
		  
		  System.out.println("frame received,fps:"+totalBytes/(1280*1024*2)/(estimatedTime/1000000000.0)+" time: "+estimatedTime/1000000+"ms");
			 
			 // System.out.println("FROM SERVER: " + modifiedSentence);
		  //}
		  Viewer.updateFrame(buf);
		  fos.write(new String(buf).getBytes());
		  fos.close();
		  clientSocket.close();

	}

}
