//import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class FrameBuffer {
	//private static ArrayList<char[]> buffer= new ArrayList<>();
	
	private static LinkedList<char[]> buffer = new LinkedList<char[]>();
	
	public static void addToBuffer(char[] frame){
		buffer.addLast(frame);
	}
	
	public static char[] getFromBuffer(){
		
		try{
			char[] frame = buffer.getFirst();
			buffer.removeFirst();
			return frame;
		}catch(NoSuchElementException e){
//			System.out.println("Frame Buffer is empty");
			return null;
		}
		
	}

}
