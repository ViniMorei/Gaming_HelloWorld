package attack;

import entity.Player;
import main.GamePanel;

import java.awt.*;

public class Scratch extends Attack {
    public Scratch(int worldX, int worldY, String direction, int maxCounter, GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.maxCounter = maxCounter;
        this.active = true;

        this.hitBox = new Rectangle();
        this.hitBox.x = 0;
        this.hitBox.y = 0;
        this.hitBox.width = gamePanel.tileSize;
        this.hitBox.height = gamePanel.tileSize;
        this.hitBoxDefaultX = 0;
        this.hitBoxDefaultY = 0;

        getScratchImages();
    }


    public static Scratch createFromPlayer(Player player) {
        int worldX = player.worldX;
        int worldY = player.worldY;
        String direction = player.direction;

        switch(direction) {
            case "up": worldY -= player.gamePanel.tileSize; break;
            case "down": worldY += player.gamePanel.tileSize; break;
            case "left": worldX -= player.gamePanel.tileSize; break;
            case "right": worldX += player.gamePanel.tileSize; break;
        }

        return new Scratch(worldX, worldY, direction, 30, player.gamePanel);
    }


    public void getScratchImages() {
        this.up1 = setupSprite("/attacks/scratch.png");
        this.up2 = setupSprite("/attacks/scratch.png");
        this.down1 = setupSprite("/attacks/scratch.png");
        this.down2 = setupSprite("/attacks/scratch.png");
        this.left1 = setupSprite("/attacks/scratch.png");
        this.left2 = setupSprite("/attacks/scratch.png");
        this.right1 = setupSprite("/attacks/scratch.png");
        this.right2 = setupSprite("/attacks/scratch.png");
    }


    @Override
    public void update() {
        this.gamePanel.cChecker.checkMonster(this);
        incrementCounter();
    }

    @Override
    public void draw(Graphics2D g2) {
        int screenX = this.worldX - this.gamePanel.player.worldX + this.gamePanel.player.screenX;
        int screenY = this.worldY - this.gamePanel.player.worldY + this.gamePanel.player.screenY;

        g2.drawImage(this.up1, screenX, screenY, null);
//        g2.setColor(Color.red);
//        g2.fillRect(screenX, screenY, 46, 46);
    }
}
