package object;

import main.GamePanel;
import main.Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public abstract class Object {
    public GamePanel gamePanel;

    public String name;
    public BufferedImage image;
    public boolean collision;
    public int worldX, worldY;


    // Construtor
    public Object(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    // Carregar o sprite
    public BufferedImage setupSprite(String fileName) {
        Utils utils = new Utils();
        BufferedImage img, scaledImage = null;

        try {
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(fileName)));
            scaledImage = utils.scaleImage(img, this.gamePanel.tileSize, this.gamePanel.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return scaledImage;
    }


    // Exibir na tela
    public void draw(Graphics2D g2) {
        int screenX = this.worldX - this.gamePanel.player.worldX + this.gamePanel.player.screenX;
        int screenY = this.worldY - this.gamePanel.player.worldY + this.gamePanel.player.worldY;

        if (this.worldX + this.gamePanel.tileSize > this.gamePanel.player.worldX - this.gamePanel.player.screenX
        && this.worldX - this.gamePanel.tileSize < this.gamePanel.player.worldX + this.gamePanel.player.screenX
        && this.worldY + this.gamePanel.tileSize > this.gamePanel.player.worldY - this.gamePanel.player.screenY
        && this.worldY - this.gamePanel.tileSize < this.gamePanel.player.worldY + this.gamePanel.player.screenY) {
            g2.drawImage(this.image,screenX, screenY, this.gamePanel.tileSize, this.gamePanel.tileSize, null);
        }
    }
}
