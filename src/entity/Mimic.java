package entity;

import main.GamePanel;

import java.awt.*;
import java.util.Random;

public class Mimic extends Entity {
    public Mimic(GamePanel gamePanel, int x, int y) {
        super(gamePanel);

        this.name = "Mimic";
        this.direction = "down";
        this.speed = 2;
        this.worldX = this.gamePanel.tileSize * x;
        this.worldY = this.gamePanel.tileSize * y;

        this.hitBox = new Rectangle();
        this.hitBox.x = 1 * this.gamePanel.scale;
        this.hitBox.y = 4 * this.gamePanel.scale;
        this.hitBox.width = 14 * this.gamePanel.scale;
        this.hitBox.height = 9 * this.gamePanel.scale;
        this.hitBoxDefaultX = this.hitBox.x;
        this.hitBoxDefaultY = this.hitBox.y;

        this.getMonsterImages();
    }


    // Carregar os sprites
    public void getMonsterImages() {
        this.down1 = setupSprite("/monsters/mimic_01.png");
        this.down2 = setupSprite("/monsters/mimic_02.png");
        this.up1 = setupSprite("/monsters/mimic_03.png");
        this.up2 = setupSprite("/monsters/mimic_04.png");
        this.left1 = setupSprite("/monsters/mimic_01.png");
        this.left2 = setupSprite("/monsters/mimic_02.png");
        this.right1 = setupSprite("/monsters/mimic_03.png");
        this.right2 = setupSprite("/monsters/mimic_04.png");
    }

    // Definição do comportamento
    public void setAction() {
        this.actionLockCounter++;

        if (this.actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if (i <= 25) {
                this.direction = "up";
            } else if (i <= 50) {
                this.direction = "down";
            } else if (i <= 75) {
                this.direction = "left";
            } else {
                this.direction = "right";
            }

            this.actionLockCounter = 0;
        }
    }
}
