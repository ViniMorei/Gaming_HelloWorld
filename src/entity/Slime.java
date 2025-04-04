package entity;

import main.GamePanel;

import java.awt.*;
import java.util.Random;

public class Slime extends Entity {
    public Slime (GamePanel gamePanel, int x, int y) {
        super(gamePanel);

        this.name = "Slime";
        this.direction = "down";
        this.speed = 1;
        this.worldX = this.gamePanel.tileSize * x;
        this.worldY = this.gamePanel.tileSize * y;

        this.health = this.maxHealth = 1;
        this.hitBox = new Rectangle();
        this.hitBox.x = 2 * this.gamePanel.scale;
        this.hitBox.y = 7 * this.gamePanel.scale;
        this.hitBox.width = 12 * this.gamePanel.scale;
        this.hitBox.height = 6 * this.gamePanel.scale;
        this.hitBoxDefaultX = this.hitBox.x;
        this.hitBoxDefaultY = this.hitBox.y;

        this.getMonsterImages();
    }


    // Definir os sprites do Slime
    public void getMonsterImages() {
        this.down1 = setupSprite("/monsters/slime_01.png");
        this.down2 = setupSprite("/monsters/slime_02.png");
        this.up1 = setupSprite("/monsters/slime_03.png");
        this.up2 = setupSprite("/monsters/slime_04.png");
        this.left1 = setupSprite("/monsters/slime_01.png");
        this.left2 = setupSprite("/monsters/slime_02.png");
        this.right1 = setupSprite("/monsters/slime_03.png");
        this.right2 = setupSprite("/monsters/slime_04.png");
    }


    // Funciona como a I.A. definindo pra onde ele vai se movimentar
    public void setAction() {
        this.actionLockCounter++;

        if (this.actionLockCounter == 120){
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
