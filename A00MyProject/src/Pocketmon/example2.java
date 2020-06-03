package Pocketmon;

import java.awt.*;
import java.awt.event.*;

public class example2 {
   static Frame fMain, fSecond;
   public static void main(String[] args) {
      fMain = new Frame();
      fSecond = new Frame();
      Button btn = new Button("click");
      Button btn2 = new Button("click2");

      fMain.setSize(200, 150);
      fMain.setLayout(null);
      fMain.add(btn);
      btn.setSize(40, 40);
      btn.setLocation(50, 50);
      fMain.setVisible(true);
      
      fSecond.setSize(200, 200);
      fSecond.setLocation(200, 200);
      fSecond.setLayout(null);
      fSecond.add(btn2);
      btn2.setSize(40, 40);
      btn2.setLocation(50, 50);
      fSecond.setVisible(false);

      fMain.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent e) {
            System.exit(0);
         }
      });

      btn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            System.out.println("Button click!");
            fSecond.setVisible(true);
         }
      });
   }
}