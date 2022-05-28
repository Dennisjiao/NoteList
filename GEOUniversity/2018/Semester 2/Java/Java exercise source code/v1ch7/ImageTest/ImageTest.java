/**
   @version 1.30 2000-06-02
   @author Cay Horstmann
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ImageTest
{  
   public static void main(String[] args)
   {  
      ImageFrame frame = new ImageFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.show();
   }
}

/** 
    A frame with an image panel
*/
class ImageFrame extends JFrame
{
   public ImageFrame()
   {
      setTitle("ImageTest");
      setSize(WIDTH, HEIGHT);

      // add panel to frame

      ImagePanel panel = new ImagePanel();
      Container contentPane = getContentPane();
      contentPane.add(panel);
   }

   public static final int WIDTH = 300;
   public static final int HEIGHT = 200;  
}

/**
   A panel that displays a tiled image
*/
class ImagePanel extends JPanel
{  
   public ImagePanel()
   {  
      // acquire the image

      image = Toolkit.getDefaultToolkit().getImage
         ("blue-ball.gif");
      MediaTracker tracker = new MediaTracker(this);
      tracker.addImage(image, 0);
      try { tracker.waitForID(0); } 
      catch (InterruptedException exception) {}
   }
   
   public void paintComponent(Graphics g)
   {  
      super.paintComponent(g);
   
      int imageWidth = image.getWidth(this);
      int imageHeight = image.getHeight(this);

      // draw the image in the upper-left corner
      
      g.drawImage(image, 0, 0, null);

      // tile the image across the panel

      for (int i = 0; i * imageWidth <= getWidth(); i++)
        for (int j = 0; j * imageHeight <= getHeight(); j++)
            if (i + j > 0) 
               g.copyArea(0, 0, imageWidth, imageHeight,
                  i * imageWidth, j * imageHeight);
   }
   
   private Image image;
}

