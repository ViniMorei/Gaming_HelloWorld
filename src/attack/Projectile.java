package attack;

import entity.Player;
import main.GamePanel;

import java.awt.*;

public class Projectile extends Attack{
    public Projectile(int worldX, int worldY, String direction, int maxCounter, int speed, GamePanel gamePanel) {
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
    }


    public static Projectile createFromPlayer(Player player) {
        int worldX = player.worldX;
        int worldY = player.worldY;
        String direction = player.direction;

        switch(direction) {
            case "up": worldY -= player.gamePanel.tileSize; break;
            case "down": worldY += player.gamePanel.tileSize; break;
            case "left": worldX -= player.gamePanel.tileSize; break;
            case "right": worldX += player.gamePanel.tileSize; break;
        }

        return new Projectile(worldX, worldY, direction, 60, 5, player.gamePanel);
    }


    public static Projectile createFromSummon(Attack summon) {
        int worldX = summon.worldX;
        int worldY = summon.worldY;
        String direction = summon.direction;

        switch(direction) {
            case "up": worldY -= summon.gamePanel.tileSize; break;
            case "down": worldY += summon.gamePanel.tileSize; break;
            case "left": worldX -= summon.gamePanel.tileSize; break;
            case "right": worldX += summon.gamePanel.tileSize; break;
        }

        return new Projectile(worldX, worldY, direction, 60, 5, summon.gamePanel);
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
    }

    @Override
    public void draw(Graphics2D g2) {
        int screenX = this.worldX - this.gamePanel.player.worldX + this.gamePanel.player.screenX;
        int screenY = this.worldY - this.gamePanel.player.worldY + this.gamePanel.player.screenY;
        g2.setColor(Color.blue);
        g2.fillOval(screenX, screenY, 46, 46);
    }
}