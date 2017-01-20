//import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class FrameBuffer {
	//private static ArrayList<char[]> buffer= new ArrayList<>();
	
	private static LinkedList<byte[]> buffer = new LinkedList<byte[]>();
	
	public static void addToBuffer(byte[] frame){
		buffer.addLast(frame);
	}
	
	public static byte[] getFromBuffer(){
		
		try{
			byte[] frame = buffer.getFirst();
			buffer.removeFirst();
			return frame;
		}catch(NoSuchElementException e){
		//	System.out.println("Frame Buffer is empty");
			return null;
		}
		
	}

}
