package main;

import java.awt.*;

public class UI {
    GamePanel gamePanel;
    Graphics2D g2;
    Font ARIAL_40, ARIAL_40_BOLD;

    // Construtor
    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        this.ARIAL_40 = new Font("Arial", Font.PLAIN, 40);
        this.ARIAL_40_BOLD = new Font("Arial", Font.BOLD, 40);
    }


    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(ARIAL_40);
        g2.setColor(Color.white);

//        if (this.gamePanel.gameState == this.gamePanel.PLAY_STATE) {
//            // Do playState stuff
//        }
        if (this.gamePanel.gameState == this.gamePanel.PAUSE_STATE) {
            drawPauseScreen();
        }
    }

    public void drawPauseScreen() {
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = this.gamePanel.screenHeight / 2;

        g2.drawString(text, x, y);
    }

    public int getXforCenteredText(String text) {
        int length = (int)this.g2.getFontMetrics().getStringBounds(text, this.g2).getWidth();
        int x = this.gamePanel.screenWidth / 2 - length / 2;
        return x;
    }
}
