import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;


public class puzzle2 extends JFrame implements ActionListener,MouseListener{ 
  //Variable and GUIObject Declaration area 
  BufferedImage img,BG;
  Image second;
  MyDrawPanel draw1=new MyDrawPanel();
  int count=0,count2=0;
  int check[]=new int[16];
  int rand[]=new int[16];
  int x[]= new int[16];
  int y[]= new int[16];
  int mx,my,winc;
  int movement=1000;
  int clicks;
  int xx,yy,move=0,picN=1;
  boolean done=true,win=true;
  public static void main(String[ ] args) 
  {
    new puzzle2();
  }  
  
  public puzzle2 (){
//    Create object and your code goes here

       try { 
         img = ImageIO.read(new File("image.png"));
    } catch (IOException e) {
    }
    this.add(draw1);
    addMouseListener(this);
    this.setSize(1070,500);
    this.setVisible(true);
    count=0;
    count2=0;
    for(int m=0;m<16; m++){
      do{
        rand[m]=(int )(Math.random() * 16);
      }while(check[rand[m]]==1);
      check[rand[m]]=1;
      System.out.println(""+rand[m]);
    }
    for(int ba=0;ba<4;ba++){
      for(int ab=0;ab<4;ab++){
        x[count]=ab*110;
        y[count]=ba*110;
        count++;
      }
    }
    repaint();
  }
  public void mousePressed(MouseEvent evt){}
  public void mouseReleased(MouseEvent evt){}
  public void mouseClicked(MouseEvent evt){
    mx=evt.getX();
    my=evt.getY();
    int w=mx/110;
    int v=my/110;
    int temp=w+v*4;
   if(mx<640&&mx>488&&my<488&&my>183){
      for(int d=0;d<16;d++){
        rand[d]=-1;
        check[d]=0;
      }

      move=0;
      mx=0;
      my=0;
      win=false;
      repaint();
    }
    System.out.println(""+mx+my);
    for(int q=0;q<16;q++){
      if(rand[q]==15){
        int temp2=q;
        if (temp2-1==temp&&temp2!=0&&temp2!=4&&temp2!=8&&temp2!=12){
          rand[temp2]=rand[temp];
          rand[temp]=15;
          move+=1;
          clicks++;
        }
        else if (temp2+1==temp&&temp2!=3&&temp2!=7&&temp2!=11&&temp2!=15){
          rand[temp2]=rand[temp];
          rand[temp]=15;
          move+=1;
          clicks++;
        }
        else if (temp2-4==temp&&temp2!=0&&temp2!=1&&temp2!=2&&temp2!=3){
          rand[temp2]=rand[temp];
          rand[temp]=15;
          move+=1;
          clicks++;
        }
        else if (temp2+4==temp&&temp2!=12&&temp2!=13&&temp2!=14&&temp2!=15){
          rand[temp2]=rand[temp];
          rand[temp]=15;
          move+=1;
          clicks++;
        }
        movement=1000-clicks;
      }
    }
    repaint();
  }
  public void mouseEntered(MouseEvent evt){}
  public void mouseExited(MouseEvent evt){}
  public void win(){
    for(int d=0;d<16;d++){
      if(rand[d]==d){
        winc+=1;
      }
    }
    if(winc==16){
      win=true;
    }
    else{
      winc=0;
    }
  }
  public void actionPerformed(ActionEvent e){}
  class MyDrawPanel extends JPanel
  {
    public void paintComponent(Graphics g) {
      Graphics2D g2 = (Graphics2D)g;
      g2.setFont(new Font("Batang",Font.BOLD,16));
      g2.drawImage(img,600,0,440,440,null);
      g2.drawString("     You Have"+movement+"",440,180);
      g2.drawString("to make your puzzle",440,200);
      for(int bb=0;bb<4;bb++){
        for(int ba=0;ba<4;ba++){
          if(rand[count2]!=15){
              g2.drawImage(img,x[count2],y[count2],x[count2]+110,y[count2]+110,x[rand[count2]],y[rand[count2]],x[rand[count2]]+110,y[rand[count2]]+110,null);

          }
          count2++;
        }
      }
      count2=0;
    }
  }
}