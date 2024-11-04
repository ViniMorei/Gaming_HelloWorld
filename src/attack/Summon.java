package attack;

import entity.Player;
import main.GamePanel;

import java.awt.*;

public class Summon extends Attack {
    public Projectile fireball;
    public Summon(int worldX, int worldY, String direction, GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.maxCounter = 60;

        this.hitBox = new Rectangle();
        this.hitBox.x = 0;
        this.hitBox.y = 0;
        this.hitBox.width = gamePanel.tileSize;
        this.hitBox.height = gamePanel.tileSize;
        this.hitBoxDefaultX = 0;
        this.hitBoxDefaultY = 0;
    }


    public static Summon createFromPlayer(Player player) {
        int worldX = player.worldX;
        int worldY = player.worldY;
        String direction = player.direction;

        switch(direction) {
            case "up": worldY -= player.gamePanel.tileSize; break;
            case "down": worldY += player.gamePanel.tileSize; break;
            case "left": worldX -= player.gamePanel.tileSize; break;
            case "right": worldX += player.gamePanel.tileSize; break;
        }

        return new Summon(worldX, worldY, direction, player.gamePanel);
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

        g2.setColor(Color.yellow);
        g2.fillRect(screenX, screenY, 46, 46);
        fireball.draw(g2);
    }
}
