package object;

import main.GamePanel;

public class Crystal extends GameObject{
    public Crystal(GamePanel gamePanel, int x, int y) {
        super(gamePanel);
        this.name = "Crystal";
        this.image = setupSprite("/objects/crystal.png");
        this.worldX = this.gamePanel.tileSize * x;
        this.worldY = this.gamePanel.tileSize * y;
    }
}
