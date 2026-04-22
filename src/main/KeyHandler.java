package main;

import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
    GamePannel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, shotKeyPressed;
    // DEBUG
    boolean showDebugText = false;

    @Override
    public void keyTyped(java.awt.event.KeyEvent e) {
        
    }

    public KeyHandler(GamePannel gp) {
        this.gp = gp;
    }

    @Override
    public void keyPressed(java.awt.event.KeyEvent e) {
        
        int code = e.getKeyCode();
        // TITLE STATE
        if (gp.gameState == gp.tileState) {
            titleState(code);
        }
        //PLAY STATE 
        else if (gp.gameState == gp.playState) {
            playState(code);
        }

        // PAUSE STATE
        else if (gp.gameState == gp.pauseState) {
            pauseState(code);
        }

        // DIALOGUE STATE
        else if (gp.gameState == gp.dialogueState) {
            dialogueState(code);
        }

        // CHARACTER STATE
        else if (gp.gameState == gp.characterState) {
            characterState(code);
        }
    }

    public void titleState (int code) {
        if (gp.ui.titleScreenState == 0) {
            if (code == java.awt.event.KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 2;
                }
            }
            if (code == java.awt.event.KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 2) {
                    gp.ui.commandNum = 0;
                }
            }
            if (code == java.awt.event.KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0) {
                    gp.ui.titleScreenState = 1;
                }
                if (gp.ui.commandNum == 1) {
                    // help
                }
                if (gp.ui.commandNum == 2) {
                    System.exit(0);
                }
            }
        
        } 
        else if (gp.ui.titleScreenState == 1) {
            if (code == java.awt.event.KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 3;
                }
            }
            if (code == java.awt.event.KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 3) {
                    gp.ui.commandNum = 0;
                }
            }
            if (code == java.awt.event.KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0) {
                    gp.gameState = gp.playState;
                    gp.playMusic(0);
                }
                if (gp.ui.commandNum == 1) {
                    gp.gameState = gp.playState;
                    gp.playMusic(0);
                }
                if (gp.ui.commandNum == 2) {
                    gp.gameState = gp.playState;
                    gp.playMusic(0);
                }
                if (gp.ui.commandNum == 3) {
                    gp.ui.titleScreenState = 0;
                }
            }
        }
    }

    public void playState (int code) {
        // PLAYER CONTROLS
        
        if (code == java.awt.event.KeyEvent.VK_W) {
            upPressed = true;
        }
        else if (code == java.awt.event.KeyEvent.VK_S) {
            downPressed = true;
        }
        else if (code == java.awt.event.KeyEvent.VK_A) {
            leftPressed = true;
        }
        else if (code == java.awt.event.KeyEvent.VK_D) {
            rightPressed = true;
        }
        // PAUSE
        if (code == java.awt.event.KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.pauseState;
        }
        if (code == java.awt.event.KeyEvent.VK_C) {
            gp.gameState = gp.characterState;
        }
        if (code == java.awt.event.KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
        if (code == java.awt.event.KeyEvent.VK_F) {
            shotKeyPressed = true;
        }

        // DEBUG
        else if (code == java.awt.event.KeyEvent.VK_T) {
            if (showDebugText == false) {
                showDebugText = true;
            }
            else if (showDebugText == true) {
                showDebugText = false;
            }
        }

        else if (code == java.awt.event.KeyEvent.VK_R) {
            gp.tileM.loadMap("/res/map/map02.txt"); // refresh map
            System.out.println("map refreshed");
        }
        
    }

    public void pauseState (int code) {
        if (code == java.awt.event.KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.playState;
        }
    }

    public void dialogueState (int code) {
        if (code == java.awt.event.KeyEvent.VK_ENTER) {
            gp.gameState = gp.playState;
        }
    }

    public void characterState (int code) {
        if (code == java.awt.event.KeyEvent.VK_C) {
            gp.gameState = gp.playState;
        }
        if (code == java.awt.event.KeyEvent.VK_W) {
            if (gp.ui.slotRow > 0) {
                gp.ui.slotRow--;
                gp.playSE(8);
            }
        }
        if (code == java.awt.event.KeyEvent.VK_S) {
            if (gp.ui.slotRow < 3) {
                gp.ui.slotRow++;
                gp.playSE(8);
            }
        }
        if (code == java.awt.event.KeyEvent.VK_A) {
            if (gp.ui.slotCol > 0) {
                gp.ui.slotCol--;
                gp.playSE(8);
            }
        }
        if (code == java.awt.event.KeyEvent.VK_D) {
            if (gp.ui.slotCol < 4) {
                gp.ui.slotCol++;
                gp.playSE(8);
            }
        }
        if (code == java.awt.event.KeyEvent.VK_ENTER) {
            gp.player.selectItem();
        }
    }

    @Override
    public void keyReleased(java.awt.event.KeyEvent e) {
        
        int code = e.getKeyCode();

        if (code == java.awt.event.KeyEvent.VK_W) {
            upPressed = false;
        }
        else if (code == java.awt.event.KeyEvent.VK_S) {
            downPressed = false;
        }
        else if (code == java.awt.event.KeyEvent.VK_A) {
            leftPressed = false;
        }
        else if (code == java.awt.event.KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == java.awt.event.KeyEvent.VK_ENTER) {
            enterPressed = false;
        }
        if (code == java.awt.event.KeyEvent.VK_F) {
            shotKeyPressed = false;
        }
        

    }
    
}
