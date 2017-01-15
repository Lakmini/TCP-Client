import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
public class Controller {
	private static Timer timer = new Timer();
	
	
	public static void start(){
		  Viewer.initialize();
		timer.scheduleAtFixedRate(new TimerTask() {
			  @Override
			  public void run() {
			    try {
			    	char[] buf = FrameBuffer.getFromBuffer();
			    	if(buf!=null)
			    		Viewer.updateFrame(buf);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
			}, 1000, 1000);
	}
}
