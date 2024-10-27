package object;

import main.GamePanel;

public class Key extends GameObject {
    public Key (GamePanel gamePanel, int x, int y) {
        super(gamePanel);
        this.name = "Key";
        this.image = setupSprite("/objects/key.png");
        this.worldX = this.gamePanel.tileSize * x;
        this.worldY = this.gamePanel.tileSize * y;
    }
}
