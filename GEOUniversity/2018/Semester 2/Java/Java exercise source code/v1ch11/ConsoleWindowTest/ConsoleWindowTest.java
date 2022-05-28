/**
   @version 1.20 2000-05-07
   @author Cay Horstmann
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ConsoleWindowTest
{  
   public static void main(String[] args)
   {
      // this is the button test program from chapter 8
      ButtonFrame frame = new ButtonFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.show();

      // initialize the console window--System.out will show here
      ConsoleWindow.init();
   }
}

/**
   A frame with a button panel
   This code is identical to ButtonTest in chapter 8, except
   for the logging in the actionPerformed method of the
   ColorAction class
*/
class ButtonFrame extends JFrame
{
   public ButtonFrame()
   {
      setTitle("ButtonTest");
      setSize(WIDTH, HEIGHT);

      // add panel to frame

      ButtonPanel panel = new ButtonPanel();
      Container contentPane = getContentPane();
      contentPane.add(panel);
   }

   public static final int WIDTH = 300;
   public static final int HEIGHT = 200;  
}

/**
   A panel with three buttons
*/
class ButtonPanel extends JPanel
{  
   public ButtonPanel()
   {  
      // create buttons

      JButton yellowButton = new JButton("Yellow");
      JButton blueButton = new JButton("Blue");
      JButton redButton = new JButton("Red");

      // add buttons to panel

      add(yellowButton);
      add(blueButton);
      add(redButton);

      // create button actions

      ColorAction yellowAction = new ColorAction(Color.yellow);
      ColorAction blueAction = new ColorAction(Color.blue);
      ColorAction redAction = new ColorAction(Color.red);

      // associate actions with buttons

      yellowButton.addActionListener(yellowAction);
      blueButton.addActionListener(blueAction);
      redButton.addActionListener(redAction);
   }

   /**
      An action listener that sets the panel's background color. 
   */
   private class ColorAction implements ActionListener
   {  
      public ColorAction(Color c)
      {  
         backgroundColor = c;
      }

      public void actionPerformed(ActionEvent event)
      {  
         System.out.println(event); // shows in console window
         setBackground(backgroundColor);
         repaint();
      }

      private Color backgroundColor;
   }
}
