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

        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
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

        // Debug
        if (code == KeyEvent.VK_K) {
            this.gamePanel.player.health--;
        }
        if (code == KeyEvent.VK_L) {
            this.gamePanel.player.health++;
        }
        if (code == KeyEvent.VK_I) {
            this.gamePanel.player.mana--;
        }
        if (code == KeyEvent.VK_O) {
            this.gamePanel.player.mana++;
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
    }
}
