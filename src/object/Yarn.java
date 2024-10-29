package object;

import main.GamePanel;

public class Yarn extends GameObject {
    public Yarn(GamePanel gamePanel, int x, int y) {
        super(gamePanel);
        this.name = "Yarn";
        this.image = setupSprite("/objects/yarn.png");
        this.worldX = this.gamePanel.tileSize * x;
        this.worldY = this.gamePanel.tileSize * y;
    }
}
