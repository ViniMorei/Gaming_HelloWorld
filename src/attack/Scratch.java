package attack;

import entity.Player;

import java.awt.*;

public class Scratch extends Attack {
    public Scratch(int worldX, int worldY, String direction, int maxCounter) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.maxCounter = maxCounter;
    }


    public static Scratch createFromPlayer(Player player) {
        int worldX = player.screenX;
        int worldY = player.screenY;
        String direction = player.direction;

        switch(direction) {
            case "up": worldY -= player.gamePanel.tileSize; break;
            case "down": worldY += player.gamePanel.tileSize; break;
            case "left": worldX -= player.gamePanel.tileSize; break;
            case "right": worldX += player.gamePanel.tileSize; break;
        }

        return new Scratch(worldX, worldY, direction, 30);
    }


    @Override
    public void update() {
        incrementCounter();
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.red);
        g2.fillRect(worldX, worldY, 46, 46);
    }
}
