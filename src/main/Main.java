package main;

import javax.swing.JFrame;

public class Main {
   public static JFrame window;
   public static void main(String[] args) {
      window = new JFrame();
      window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      window.setResizable(false);
      window.setTitle("2dTreasureGame");
      
 
      GamePannel gamePannel = new GamePannel();
      window.add(gamePannel);

      gamePannel.config.loadConfig();
      if (gamePannel.fullScreenOn) {
         window.setUndecorated(true);
      }

      window.pack();
      window.setLocationRelativeTo(null);
      window.setVisible(true);

      gamePannel.setupGame();
      gamePannel.startGameThread();

   }
}