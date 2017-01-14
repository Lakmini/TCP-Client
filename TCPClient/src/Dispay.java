import java.awt.*;  
import javax.swing.JFrame;  
  
public class Dispay extends Canvas{  
      
    public void paint(Graphics g) {  
  
        Toolkit t=Toolkit.getDefaultToolkit();  
        Image i=t.getImage("or618F.jpg");  
        g.drawImage(i, 120,100,this);  
          
    }  
        public static void showFrame() {  
        	Dispay m=new Dispay();  
	        JFrame f=new JFrame();  
	        f.add(m);  
	        f.setSize(400,400);  
	        f.setVisible(true);  
    }  
  
}