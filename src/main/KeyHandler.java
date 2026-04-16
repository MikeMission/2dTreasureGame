package main;

import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
    GamePannel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
    // DEBUG
    boolean checkDrawTime = false;

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

        // PLAY STATE
        else if (gp.gameState == gp.playState) {

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
            if (code == java.awt.event.KeyEvent.VK_ENTER) {
                enterPressed = true;
            }
   
            // DEBUG
            else if (code == java.awt.event.KeyEvent.VK_T) {
                if (checkDrawTime == false) {
                    checkDrawTime = true;
                }
                else if (checkDrawTime == true) {
                    checkDrawTime = false;
                }
            }
        }

        // PAUSE STATE
        else if (gp.gameState == gp.pauseState) {
            if (code == java.awt.event.KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.playState;
                // add cd to prevent player from moving immediately after unpausing
                
            }
        }

        // DIALOGUE STATE
        else if (gp.gameState == gp.dialogueState) {
            if (code == java.awt.event.KeyEvent.VK_ENTER) {
                gp.gameState = gp.playState;
            }
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

    }
    
}
