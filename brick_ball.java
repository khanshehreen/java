import java.awt.event.*;
import java.applet.*;
import javax.swing.*;
import javax.swing.event.*;
/*<applet code="Pong1.class" width=670 height=300></applet>*/
public class Pong1 extends Applet implements
Runnable,KeyListener,MouseListener
{
 Thread th1;
 static Rectangle ob[] = new Rectangle[30];
 Ball bal;  Bar barr;  Message msg1;
 int i,j, x = 1,y=1,z = 0 ,xx ,yy ,zz ,b ,b1,flag;
 boolean running=true;
 Image bgimage;
 Graphics grap;
 int c1,c2,c3,c4, flagg=1;
 String s1= "";  String s2=""; String m = "";
 static int scr = 0;  static Font ff;  TextArea ta;
 public void start()
 {      th1 = new Thread(this); running = false;
  }
 public void stop()
 {    running = true;  th1 = null;
 }
 public void run()
 {      for(; ;)
 {      if(bal.over())
 {       s1 = "Game Over!!!!!";  s2 = "You have loss the game";
 repaint();
 System.out.print("break");
 break;
 }
 try
 {      Thread.sleep(20);
 if(running)break;
 int tempx=Ball.flagx;  int tempy=Ball.flagy;
 if (tempx==1)
 bal.move();
 else
 bal.move1();
 if(tempy==1)
 bal.move2();
 else
 bal.move3();
 if(tempy==1)
 bal.move();
 else
 bal.move3();
 repaint();
 }
 catch(InterruptedException ex)
 {      ex.printStackTrace();
 }
 }
 }
 public void init()
 {      int xx=30,mm=30;
 for(int i =0 ;i<24;i=i+3)
 {     ob[i] = new Rectangle(xx,mm);
 ob[i].display=true;   mm=mm+20;
 ob[i+1] = new Rectangle(xx,mm);
 ob[i+1].display=true;     mm=mm+20;
 ob[i+2] = new Rectangle(xx,mm);
 ob[i+2].display=true;    xx=xx+50;    mm = 30;
 }
 msg1=new Message();  bal = new Ball(90,250);   barr = new Bar(70);
 ff = new Font("arial",Font.BOLD,20);
 this.addKeyListener(this);  this.addMouseListener(this);
 }
 public void paint(Graphics g)
 {      int count=0;
 for(int i=0;i<24;i++)
 {     if(ob[i].display==false)   
 count++;
 }
 if(count==24)
 {      msg1.mess(g);
 return;
 }
 g.setFont(new Font("arial",Font.PLAIN,13));
 g.setColor(Color.blue);
 g.fillRect(470,30,150,70);
 g.setColor(Color.green);
 g.drawString("Score: "+scr,480,50);
 g.drawString("Status:"+Ball.over,480,70);
 g.drawString("Life: "+Ball.life,480,90);
 g.setColor(Color.black);
 g.fillRect(20,20,410,300);
 g.setColor(Color.red);
 g.setFont(ff);
 g.drawString(s1,100,200);
 g.drawString(s2,100,230);
 g.setColor(Color.red);
 for(int j = 0;j<24;j++)
 {  if(ob[j].display==false)
 {     continue;
 }
 System.out.print("check false:"+ob[j].display);
 if(ob[j].display==true)
 {    breaking(j) ;
 }
 if(ob[j].display==true)
 ob[j].ppaint(g);
 }
 bal.bpaint(g);  barr.barpaint(g);
 }
 public void keyTyped(KeyEvent k) { }
 public void keyReleased(KeyEvent k) { }
 public void keyPressed(KeyEvent k)
 {     int R =k.getKeyCode();
 if(R==k.VK_LEFT)
 {      if(barr.yy > 25)
 barr.yy=barr.yy-10;  System.out.println("Left");  repaint();
 }
 if(R==k.VK_RIGHT)
 {      if(barr.yy < 365 )
 {     barr.yy=barr.yy+10;
 }
 System.out.println("Right");   repaint();
 }
 }
 public void mousePressed(MouseEvent m)
 {    th1.start();
 }
 public void mouseReleased(MouseEvent m) { }
 public void mouseClicked(MouseEvent m) { }
 public void mouseExited(MouseEvent m) { }
 public void mouseEntered(MouseEvent m) { }
 public void update(Graphics g) //to remove flickering
 {   if (bgimage==null)
 {
 bgimage=createImage(this.getSize().width,this.getSize().height);
 grap=bgimage.getGraphics();
 }
 grap.setColor(getBackground());
 grap.fillRect(0,0,this.getSize().width,this.getSize().height);
 grap.setColor(getForeground());
 paint(grap);
 g.drawImage(bgimage,0,0,this);
 }
 public boolean breaking(int i)
 {    if(check(i))
 return true;
 else
 return false;
 }
 public boolean check(int i)
 {     int am,ami;
 am = Math.abs(bal.x1-ob[i].x);   ami = Math.abs(bal.y1-ob[i].y);
 if(am<20 && ami<20 )
 {       ob[i].display=false;
 scr=scr+10;   Ball.flagy=1;   return true;
 }
 else
 {    return false;
 }
 }
 } 
class Rectangle
{       int i, x,y;  Graphics g;
 int ballCheck=0;   boolean display;
 Rectangle(int m ,int n)
 {       x = m; y =n;
 }
 public void ppaint(Graphics g)
 {     g.fillRect(x,y,40,10);
 }
 }
class Ball
{      static int flag=3;  static int flagx=1;  static int flagy=2; static int Point = 0;
 static String over="";  static int life=1; 
 int x,y,m, x1,y1;  Graphics g;
 Ball(int a1,int a2)
 {     x1 = a1;  y1 = a2;
 }
 public void bpaint(Graphics g)
 {      if(20>=x1||x1>=410||y1<=20||y1>=260) //checking boundaries
 {     getflag();
 }
 g.setColor(Color.white);   g.fillOval(x1,y1,15,15);
 }
 public boolean over()
 {       if(y1>290)
 return true;
 else
 return false;
 }
 public void move()
 {    x1++;
 }
 public void move1()
 {     x1--;
 }
 public void move2()
 {      y1=y1+2;
 }
 public void move3()
 {    y1=y1-2;
 }
 public void move4()
 {    x1++;    y1--;
 }
 public void getflag()
 {    if(x1<=20) // check boundaries and ball motion
 {      x1=20;    flagx=1;
 }
 if(x1>=410)
 {      x1=410;   flagx=2;
 }
 if(y1<=20)
 {      y1=20;     Point = Point +10;   flagy=1;
 }
 if(y1>=260)
 {       if(Bar.yy>=x1||x1<=(Bar.yy+50))
 {      System.out.print("/n /n true Bar.yy :"+Bar.yy+" Y1:"+y1);
 System.out.print(" /n/n/n/ncheck ok");
 y1=260;     flagy=2;
 }
 else
 {    System.out.print("/n /n falseBar.yy :"+Bar.yy+" Y1:"+y1);
 if(Bar.yy>260 && Bar.yy<200)
 {      flag=2;
 System.out.print("/n game over");
 }
 else
 {     if(Bar.yy>290)
 {
 }
 else
 {     life=0;
 System.out.print(Bar.yy);   over="LOSS GAME";
 }
 }
 } 
 }
 }
 }
class Bar
{       int s1;  static int yy=0;  int q; static int pp;
 Bar(int a)
 {     yy=a+yy;
 }
 public void barpaint(Graphics g)
 {     g.setColor(Color.gray);  g.fillRect(yy,270,60,10);
 }
 }
class Message
{    public void mess(Graphics g)
 {    
 g.setColor(Color.black);
 g.fillRect(0,0,400,300);
 g.setColor(Color.red);
 g.setFont(Pong1.ff);
 g.drawString("YOU HAVE WON THE GAME",160,180);
 g.drawString("Your Score is:"+Pong1.scr,160,200);
 }
}
