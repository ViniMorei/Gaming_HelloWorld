package attack;

import main.GamePanel;
import main.Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public abstract class Attack {
    public GamePanel gamePanel;
    public String name;
    public BufferedImage up1, up2, down1, down2, right1, right2, left1, left2;

    public int worldX, worldY;
    public String direction;
    public Rectangle hitBox;
    public int hitBoxDefaultX, hitBoxDefaultY;

    public int speed;
    public int maxCounter;
    public int counter = 0;
    public boolean active = false;


    // Métodos abstratos
    public abstract void update();

    public abstract void draw(Graphics2D g2);

    public boolean isActive() {
        return this.active;
    }

    public void incrementCounter() {
        counter++;
        if (counter >= maxCounter) {
            active = false;
        }
    }

    public BufferedImage setupSprite(String fileName) {
        Utils util = new Utils();
        BufferedImage image, scaledImage = null;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(fileName)));
            scaledImage = util.scaleImage(image, this.gamePanel.tileSize, this.gamePanel.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return scaledImage;
    }


    public BufferedImage setupSprite(String fileName, int width, int height) {
        Utils util = new Utils();
        BufferedImage image, scaledImage = null;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(fileName)));
            scaledImage = util.scaleImage(image, this.gamePanel.scale * width, this.gamePanel.scale * height);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return scaledImage;
    }
}
