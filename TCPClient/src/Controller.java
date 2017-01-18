import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
public class Controller {
	private static Timer timer = new Timer();
	
	private static int imageNumber=0;
	public static void start(){
		  Viewer.initialize();
		timer.scheduleAtFixedRate(new TimerTask() {
			  @Override
			  public void run() {
				  
					  char[] buf = FrameBuffer.getFromBuffer();
					  if(buf!=null){
						  FileHandler.writeFile(new String(buf).getBytes(), imageNumber++);
						imageNumber++;
						  
					  }
				 

			  }
			}, 50, 50);
	}
}
