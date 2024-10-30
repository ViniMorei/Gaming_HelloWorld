package main;

import object.Chest;
import object.Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class UI {
    GamePanel gamePanel;
    Graphics2D g2;
    Font ARIAL_40, ARIAL_40_BOLD;
    Font MP16REG;
    BufferedImage keyImage;
    BufferedImage chestImage;

    // Construtor
    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        this.ARIAL_40 = new Font("Arial", Font.PLAIN, 40);
        this.ARIAL_40_BOLD = new Font("Arial", Font.BOLD, 40);

        try {
            InputStream is = getClass().getResourceAsStream("/fonts/MP16REG.ttf");
            assert is != null;
            this.MP16REG = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }

        Key key = new Key(this.gamePanel, -1, -1);
        Chest chest = new Chest(this.gamePanel, -2, -2);
        this.keyImage = key.image;
        this.chestImage = chest.image;
    }


    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(MP16REG);
        g2.setColor(Color.white);

        if (this.gamePanel.gameState == this.gamePanel.PLAY_STATE) {
            g2.drawImage(keyImage, this.gamePanel.tileSize / 2, this.gamePanel.tileSize / 2,
                    this.gamePanel.tileSize - 8, this.gamePanel.tileSize - 8, null);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40));
            g2.drawString("x " + this.gamePanel.player.keys, 74, 58);

            g2.drawImage(chestImage, this.gamePanel.tileSize / 2, this.gamePanel.tileSize + 32,
                    this.gamePanel.tileSize - 8, this.gamePanel.tileSize - 8, null);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40));
            g2.drawString("x " + this.gamePanel.player.chests, 74, 113);
        }
        if (this.gamePanel.gameState == this.gamePanel.PAUSE_STATE) {
            drawPauseScreen();
        }
        if (this.gamePanel.gameState == this.gamePanel.FINISHED_STATE) {
            drawFinishedScreen();
        }
    }

    public void drawPauseScreen() {
        String text = "PAUSED";
        int x = this.gamePanel.screenWidth / 2 - 124;
        int y = this.gamePanel.screenHeight / 2;

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80));
        g2.drawString(text, x, y);
    }


    public void drawFinishedScreen() {
        String text = "CONGRATULATIONS";
        int x = this.gamePanel.screenWidth / 2 - 320;
        int y = this.gamePanel.screenHeight / 2;

        g2.setColor(Color.yellow);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80));
        g2.drawString(text, x, y);
    }
}
