package attack;

import entity.Player;

import java.awt.*;

public class Projectile extends Attack{
    public int speed;

    public Projectile(int worldX, int worldY, String direction, int maxCounter, int speed) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.maxCounter = maxCounter;
        this.speed = speed;
    }


    public static Projectile createFromPlayer(Player player) {
        int worldX = player.screenX;
        int worldY = player.screenY;
        String direction = player.direction;

        switch(direction) {
            case "up": worldY -= player.gamePanel.tileSize; break;
            case "down": worldY += player.gamePanel.tileSize; break;
            case "left": worldX -= player.gamePanel.tileSize; break;
            case "right": worldX += player.gamePanel.tileSize; break;
        }

        return new Projectile(worldX, worldY, direction, 60, 5);
    }

    @Override
    public void update() {
        switch (direction) {
            case "up": worldY -= speed; break;
            case "down": worldY += speed; break;
            case "left": worldX -= speed; break;
            case "right": worldX += speed; break;
        }
        incrementCounter();
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.blue);
        g2.fillOval(worldX, worldY, 46, 46);
    }
}
