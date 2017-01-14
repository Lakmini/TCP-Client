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
		  
		  char[] buf=new char[258048000+640000];
		 // char[] buf=new char[129003048];
		  
		 // for(int i=0;i<20;i++){
		  outToServer.writeByte(1);
		  int numberOfBytes=0;
		  int bytesRecived=0;
		  int bytesRead=0;
		  long startTime = System.nanoTime();    
		// ... the code being measured ...    
		

		  while(numberOfBytes<258048000+61440){	   
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
		  long estimatedTime = System.nanoTime() - startTime;
			 // System.out.println("FROM SERVER: " + modifiedSentence);
		  //}
		  fos.write(new String(buf).getBytes());
		  fos.close();
		  System.out.println("frame received, time: "+estimatedTime/1000000+"ms");
		  clientSocket.close();

	}

}
