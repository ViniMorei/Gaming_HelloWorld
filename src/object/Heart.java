package object;

import main.GamePanel;

public class Heart extends GameObject {
    public Heart(GamePanel gamePanel, int x, int y) {
        super(gamePanel);
        this.name = "Heart";
        this.image = setupSprite("/objects/heart.png");
        this.worldX = this.gamePanel.tileSize * x;
        this.worldY = this.gamePanel.tileSize * y;
        this.points = 10;
    }
}
