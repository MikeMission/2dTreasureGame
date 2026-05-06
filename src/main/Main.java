package main;

import javax.swing.JFrame;

public class Main {

   public static JFrame window;
   public static void main(String[] args) {
    window = new JFrame();
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setResizable(false);
    window.setTitle("2dTreasureGame");
    window.setUndecorated(true);
    
    window.setLocationRelativeTo(null);
    window.setVisible(true);

    GamePannel gamePannel = new GamePannel();
    window.add(gamePannel);

    window.pack();

    gamePannel.setupGame();
    gamePannel.startGameThread();

   }
}