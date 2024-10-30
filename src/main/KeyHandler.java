package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed, leftPressed, downPressed, rightPressed;
    GamePanel gamePanel;

    public KeyHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }


    @Override
    public void keyTyped(KeyEvent e) {
        // Não utilizado
    }


    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W){
            upPressed = true;
        }
        if (code == KeyEvent.VK_A){
            leftPressed = true;
        }
        if (code == KeyEvent.VK_S){
            downPressed = true;
        }
        if (code == KeyEvent.VK_D){
            rightPressed = true;
        }
        if (code == KeyEvent.VK_P){
            if (this.gamePanel.gameState == this.gamePanel.PLAY_STATE) {
                this.gamePanel.gameState = gamePanel.PAUSE_STATE;
            } else if (this.gamePanel.gameState == this.gamePanel.PAUSE_STATE) {
                this.gamePanel.gameState = gamePanel.PLAY_STATE;
            }
        }
        if (code == KeyEvent.VK_M){
            this.gamePanel.playSFX(0);
        }
        if (code == KeyEvent.VK_F) {
            this.gamePanel.gameState = this.gamePanel.FINISHED_STATE;
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W){
            upPressed = false;
        }
        if (code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if (code == KeyEvent.VK_S){
            downPressed = false;
        }
        if (code == KeyEvent.VK_D){
            rightPressed = false;
        }
    }
}
