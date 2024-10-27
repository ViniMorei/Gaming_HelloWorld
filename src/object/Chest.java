package object;

import main.GamePanel;

public class Chest extends GameObject {
    public Chest(GamePanel gamePanel, int x, int y) {
        super(gamePanel);
        this.name = "Chest";
        this.image = setupSprite("/objects/chest.png");
        this.worldX = this.gamePanel.tileSize * x;
        this.worldY = this.gamePanel.tileSize * y;
        this.collision = true;
    }
}
