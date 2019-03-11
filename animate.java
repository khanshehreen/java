import java.awt.*; 
 import java.applet.*; 
  
/*<applet CODE=st.class width=1000 height=1000>
//<param name="fontname" value="algerian">
//<param name="fontsize" value="20">
</applet>
*/
  
 public class st extends Applet implements Runnable 
 { 
      String banner=" ** CHILD IS MEANT TO LEARN,NOT TO EARN"; 

      boolean stopflag; 
            Image pic;  
      public void init() 
      { 
pic =getImage(getDocumentBase(),"3.jpg");

           setBackground(Color.white); 
           setForeground(Color.red); 
      } 
       
      public void start() 
      {    
           Thread t=new Thread(this); 
           stopflag=true; 
           t.start(); 
      } 
       
      public void run() 
      { 
           char ch; 
           try 
           { 
                while(true) 
                { 
                     repaint(); 
                     Thread.sleep(600); 
                     ch=banner.charAt(0); 
                     banner = banner.substring(1,banner.length()); 
                     banner +=ch; 
                      
                } 
           } 
           catch (InterruptedException e) 
           { 
                System.out.println(e); 
           } 
      } 
       
      public void stop() 
      { 
           stopflag=false; 
            Thread t=null; 
      } 
       
      public void paint(Graphics g) 
      { 
  g.drawImage(pic,0,100,this);
           Font a = new Font("Algerian",Font.BOLD,60); 

           g.setFont(a); 
           g.drawString(banner,200,70); 
      }
}
