package object;

import main.GamePanel;

public class Lizard extends GameObject {
    public Lizard(GamePanel gamePanel, int x, int y) {
        super(gamePanel);
        this.name = "Lizard";
        this.image = setupSprite("/objects/lizard.png");
        this.worldX = this.gamePanel.tileSize * x;
        this.worldY = this.gamePanel.tileSize * y;
    }
}
