package attack;

import entity.Player;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Summon extends Attack {
    public Projectile fireball;
    public Summon(int worldX, int worldY, String direction, GamePanel gamePanel, String type) {
        this.gamePanel = gamePanel;
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.maxCounter = 60;
        this.active = true;

        this.hitBox = new Rectangle();
        this.hitBox.x = 0;
        this.hitBox.y = 0;
        this.hitBox.width = gamePanel.tileSize;
        this.hitBox.height = gamePanel.tileSize;
        this.hitBoxDefaultX = 0;
        this.hitBoxDefaultY = 0;

        switch(type) {
            case "Fire":
                this.name = "Fire Summon";
                getFireSummonImages();
                break;
            case "Ice":
                this.name = "Ice Summon";
                getIceSummonImages();
                break;
            case "Lightning":
                this.name = "Lightning Summon";
                getLightningSummonImages();
                break;
        }
    }


    public static Summon createFromPlayer(Player player, String type) {
        int worldX = player.worldX;
        int worldY = player.worldY;
        String direction = player.direction;

        switch(direction) {
            case "up": worldY -= player.gamePanel.tileSize + player.gamePanel.tileSize / 2; break;
            case "down": worldY += player.gamePanel.tileSize; break;
            case "left": worldX -= player.gamePanel.tileSize; worldY -= player.gamePanel.tileSize / 2; break;
            case "right": worldX += player.gamePanel.tileSize; worldY -= player.gamePanel.tileSize / 2; break;
        }

        return new Summon(worldX, worldY, direction, player.gamePanel, type);
    }


    // Métodos para atribuir imagens dos respectivos summons ao tipo selecionado pelo input
    public void getFireSummonImages() {
        this.up1 = setupSprite("/attacks/FS_up.png", 16, 24);
        this.down1 = setupSprite("/attacks/FS_down.png", 16, 24);
        this.left1 = setupSprite("/attacks/FS_left.png", 16, 24);
        this.right1 = setupSprite("/attacks/FS_right.png", 16, 24);
    }


    public void getIceSummonImages() {
        this.up1 = setupSprite("/attacks/FS_up.png", 16, 24);
        this.down1 = setupSprite("/attacks/FS_down.png", 16, 24);
        this.left1 = setupSprite("/attacks/FS_left.png", 16, 24);
        this.right1 = setupSprite("/attacks/FS_right.png", 16, 24);
    }


    public void getLightningSummonImages() {
        this.up1 = setupSprite("/attacks/FS_up.png", 16, 24);
        this.down1 = setupSprite("/attacks/FS_down.png", 16, 24);
        this.left1 = setupSprite("/attacks/FS_left.png", 16, 24);
        this.right1 = setupSprite("/attacks/FS_right.png", 16, 24);
    }


    @Override
    public void update() {
        if (counter == 0) {
            fireball = Projectile.createFromSummon(this);
        }
        this.gamePanel.cChecker.checkMonster(this);
        incrementCounter();
        fireball.update();
    }

    @Override
    public void draw(Graphics2D g2) {
        int screenX = this.worldX - this.gamePanel.player.worldX + this.gamePanel.player.screenX;
        int screenY = this.worldY - this.gamePanel.player.worldY + this.gamePanel.player.screenY;
        BufferedImage image = switch (this.direction) {
            case "up" -> up1;
            case "down" -> down1;
            case "left" -> left1;
            case "right" -> right1;
            default -> null;
        };

        // g2.setColor(Color.yellow);
        // g2.fillRect(screenX, screenY, 46, 46);
        g2.drawImage(image, screenX, screenY, null);
        fireball.draw(g2);
    }
}
