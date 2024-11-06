package attack;

import entity.Player;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class Projectile extends Attack{
    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Projectile(int worldX, int worldY, String direction, int maxCounter, int speed, GamePanel gamePanel, String name) {
        this.gamePanel = gamePanel;
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.maxCounter = maxCounter;
        this.speed = speed;

        this.hitBox = new Rectangle();
        this.hitBox.x = 0;
        this.hitBox.y = 0;
        this.hitBox.width = gamePanel.tileSize;
        this.hitBox.height = gamePanel.tileSize;
        this.hitBoxDefaultX = 0;
        this.hitBoxDefaultY = 0;

        switch(name) {
            case "Fire Summon":
                getFireBallImages();
                break;
            case "Ice Summon":
                getSnowflakeImages();
                break;
            case "Lightning Summon":
                getLightningBoltImages();
                break;
            default:
                getFireBallImages();
                break;
        }
    }


    public static Projectile createFromPlayer(Player player) {
        int worldX = player.worldX;
        int worldY = player.worldY;
        String direction = player.direction;
        String name = "";

        switch(direction) {
            case "up": worldY -= player.gamePanel.tileSize; break;
            case "down": worldY += player.gamePanel.tileSize; break;
            case "left": worldX -= player.gamePanel.tileSize; break;
            case "right": worldX += player.gamePanel.tileSize; break;
        }

        return new Projectile(worldX, worldY, direction, 90, 8, player.gamePanel, name);
    }


    public static Projectile createFromSummon(Attack summon) {
        int worldX = summon.worldX;
        int worldY = summon.worldY;
        String direction = summon.direction;
        String name = summon.name;

        switch(direction) {
            case "up": worldY -= summon.gamePanel.tileSize; break;
            case "down": worldY += summon.gamePanel.tileSize; break;
            case "left": worldX -= summon.gamePanel.tileSize; worldY += summon.gamePanel.tileSize / 2; break;
            case "right": worldX += summon.gamePanel.tileSize; worldY += summon.gamePanel.tileSize / 2; break;
        }

        return new Projectile(worldX, worldY, direction, 90, 8, summon.gamePanel, name);
    }


    public void getFireBallImages() {
        this.up1 = setupSprite("/attacks/fireball_up_01.png");
        this.up2 = setupSprite("/attacks/fireball_up_02.png");
        this.down1 = setupSprite("/attacks/fireball_down_01.png");
        this.down2 = setupSprite("/attacks/fireball_down_02.png");
        this.left1 = setupSprite("/attacks/fireball_left_01.png");
        this.left2 = setupSprite("/attacks/fireball_left_02.png");
        this.right1 = setupSprite("/attacks/fireball_right_01.png");
        this.right2 = setupSprite("/attacks/fireball_right_02.png");
    }


    public void getSnowflakeImages() {
        this.up1 = setupSprite("/attacks/snow_01.png");
        this.up2 = setupSprite("/attacks/snow_02.png");
        this.down1 = setupSprite("/attacks/snow_01.png");
        this.down2 = setupSprite("/attacks/snow_02.png");
        this.left1 = setupSprite("/attacks/snow_01.png");
        this.left2 = setupSprite("/attacks/snow_02.png");
        this.right1 = setupSprite("/attacks/snow_01.png");
        this.right2 = setupSprite("/attacks/snow_02.png");
    }


    public void getLightningBoltImages() {
        this.up1 = setupSprite("/attacks/lightning_up_01.png");
        this.up2 = setupSprite("/attacks/lightning_up_02.png");
        this.down1 = setupSprite("/attacks/lightning_up_01.png");
        this.down2 = setupSprite("/attacks/lightning_up_02.png");
        this.left1 = setupSprite("/attacks/lightning_right_01.png");
        this.left2 = setupSprite("/attacks/lightning_right_02.png");
        this.right1 = setupSprite("/attacks/lightning_right_01.png");
        this.right2 = setupSprite("/attacks/lightning_right_02.png");
    }

    @Override
    public void update() {
        switch (direction) {
            case "up": worldY -= speed; break;
            case "down": worldY += speed; break;
            case "left": worldX -= speed; break;
            case "right": worldX += speed; break;
        }
        this.gamePanel.cChecker.checkMonster(this);
        incrementCounter();

        this.spriteCounter++ ;
        if (this.spriteCounter > 10) {
            if (this.spriteNum == 1) {
                this.spriteNum = 2;
            } else if (this.spriteNum == 2) {
                this.spriteNum = 1;
            }
            this.spriteCounter = 0;
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        int screenX = this.worldX - this.gamePanel.player.worldX + this.gamePanel.player.screenX;
        int screenY = this.worldY - this.gamePanel.player.worldY + this.gamePanel.player.screenY;
        BufferedImage image = null;

        switch(this.direction) {
            case "up":
                if (this.spriteNum == 1){
                    image = this.up1;
                } else if (this.spriteNum == 2){
                    image = this.up2;
                }
                break;
            case "down":
                if (this.spriteNum == 1){
                    image = this.down1;
                } else if (this.spriteNum == 2){
                    image = this.down2;
                }
                break;
            case "left":
                if (this.spriteNum == 1){
                    image = this.left1;
                } else if (this.spriteNum == 2){
                    image = this.left2;
                }
                break;
            case "right":
                if (this.spriteNum == 1){
                    image = this.right1;
                } else if (this.spriteNum == 2){
                    image = this.right2;
                }
                break;
        }

        g2.drawImage(image, screenX, screenY, null);
    }
}
