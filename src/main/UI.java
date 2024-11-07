package main;

import object.Chest;
import object.Crystal;
import object.Heart;
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
    BufferedImage keyImage, chestImage, heartImage, crystalImage;
    int titleScreenSelector = 0;

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
        Chest chest = new Chest(this.gamePanel, -2, -1);
        Heart heart = new Heart(this.gamePanel, -3, -1);
        Crystal crystal = new Crystal(this.gamePanel, -4, -1);
        this.keyImage = key.image;
        this.chestImage = chest.image;
        this.heartImage = heart.image;
        this.crystalImage = crystal.image;
    }


    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(MP16REG);
        g2.setColor(Color.white);

        if (this.gamePanel.gameState == this.gamePanel.TITLE_STATE) {
            drawTitleScreen();
        }
        if (this.gamePanel.gameState == this.gamePanel.PLAY_STATE) {
            drawGameHUD();
        }
        if (this.gamePanel.gameState == this.gamePanel.PAUSE_STATE) {
            drawPauseScreen();
        }
        if (this.gamePanel.gameState == this.gamePanel.FINISHED_STATE) {
            drawFinishedScreen();
        }
        if (this.gamePanel.gameState == this.gamePanel.GAME_OVER_STATE) {
            drawGameOverScreen();
        }
    }


    // Menu principal
    public void drawTitleScreen() {
        // Título
        String text = "PURRANORMAL MAZE";
        int x = this.gamePanel.screenWidth / 2 - 270;
        int y = this.gamePanel.screenHeight / 2 - 150;

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 60));
        g2.drawString(text, x, y);

        // Imagem
        BufferedImage image = this.gamePanel.monsters[0].down1;
        x = this.gamePanel.screenWidth / 2 - image.getWidth();
        y = this.gamePanel.screenHeight / 2 - image.getHeight();
        g2.drawImage(image, x, y, image.getWidth() * 2, image.getHeight() * 2, null);

        // Menu
        text = "EASY";
        x = this.gamePanel.screenWidth / 2 - 30;
        y = this.gamePanel.screenHeight / 2 + 100;
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30));
        g2.drawString(text, x, y);
        if (titleScreenSelector == 0) {
            g2.drawString(">", x-this.gamePanel.tileSize, y);
        }

        text = "HARD";
        x = this.gamePanel.screenWidth / 2 - 32;
        y = this.gamePanel.screenHeight / 2 + 150;
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30));
        g2.drawString(text, x, y);
        if (titleScreenSelector == 1) {
            g2.drawString(">", x-this.gamePanel.tileSize, y);
        }

        text = "QUIT";
        x = this.gamePanel.screenWidth / 2 - 27;
        y = this.gamePanel.screenHeight / 2 + 220;
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30));
        g2.drawString(text, x, y);
        if (titleScreenSelector == 2) {
            g2.drawString(">", x-this.gamePanel.tileSize, y);
        }
    }


    // Exibir a HUD com os itens coletados, vida e magia
    public void drawGameHUD() {
        int x = this.gamePanel.tileSize / 2;
        int y = this.gamePanel.tileSize / 2;

        // Desenhar corações
        for (int i = 0; i < this.gamePanel.player.health; i++){
            g2.drawImage(heartImage, x, y, this.gamePanel.tileSize, this.gamePanel.tileSize, null);
            x += this.gamePanel.tileSize;
        }
        // Desenhar os cristais da extremidade direita até o centro da tela
        x = this.gamePanel.screenWidth - (this.gamePanel.tileSize + 16);

        // Desenhar cristais de magia
        for (int i = 0; i < this.gamePanel.player.mana; i++){
            g2.drawImage(crystalImage, x, y, this.gamePanel.tileSize, this.gamePanel.tileSize, null);
            x -= this.gamePanel.tileSize;
        }
        x = this.gamePanel.tileSize / 2;
        y += this.gamePanel.tileSize + 8;

        g2.drawImage(keyImage, x, y,
                this.gamePanel.tileSize - 8, this.gamePanel.tileSize - 8, null);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40));
        g2.drawString("x " + this.gamePanel.player.keys, x + 54, y + 32);

        y += this.gamePanel.tileSize + 8;
        g2.drawImage(chestImage, x, y,
                this.gamePanel.tileSize - 8, this.gamePanel.tileSize - 8, null);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40));
        g2.drawString("x " + this.gamePanel.player.chests, x + 56, y + 32);
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

    public void drawGameOverScreen() {
        String text = "GAME OVER";
        int x = this.gamePanel.screenWidth / 2 - 200;
        int y = this.gamePanel.screenHeight / 2;

        g2.setColor(Color.yellow);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80));
        g2.drawString(text, x, y);
    }

}
