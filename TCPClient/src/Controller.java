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
				  new Thread(() -> {
					  char[] buf = FrameBuffer.getFromBuffer();
					  if(buf!=null){
						  System.out.println(imageNumber);
							 // try {
								//Viewer.updateFrame(buf);
								imageNumber++;
							//} catch (IOException e) {
								// TODO Auto-generated catch block
								//e.printStackTrace();
							//}
							  //FileHandler.convertImages(new String(buf).getBytes(), imageNumber++);
						 
						  
					  }
				  }).start();
//			    try {
//			    	
//			    	if(buf!=null)
//			    		Viewer.updateFrame(buf);
//			    		
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			  }
			}, 50, 50);
	}
}
