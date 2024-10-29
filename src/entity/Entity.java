package entity;

import main.GamePanel;
import main.Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public abstract class Entity {
    GamePanel gamePanel;

    // Atributos padrão (posição e velocidade)
    public int worldX, worldY; // Define onde no mapa a entidade será exibida
    public int speed; // Quantos pixels se move
    public String direction; // Define sprite e movimentação

    // Sprites (dois de cada para animar movimentação)
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;

    // Utilizados para fazer a lógica da troca de sprites da mesma direção (andar)
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int actionLockCounter = 0;

    // Configurações de colisão
    public Rectangle hitBox;
    public int hitBoxDefaultX, hitBoxDefaultY;
    public boolean collisionOn = false;


    // Construtor
    public Entity (GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }


    // Pegar as imagens e redimensionar elas
    public BufferedImage setupSprite(String fileName) {
        Utils util = new Utils();
        BufferedImage image, scaledImage = null;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(fileName)));
            scaledImage = util.scaleImage(image, this.gamePanel.tileSize, this.gamePanel.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return scaledImage;
    }


    // Exibir na tela
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int screenX = this.worldX - this.gamePanel.player.worldX + this.gamePanel.player.screenX;
        int screenY = this.worldY - this.gamePanel.player.worldY + this.gamePanel.player.screenY;

        // Essa condição é pra só desenhar se estiver dentro dos limites da tela (economizar recursos)
        if (this.worldX + this.gamePanel.tileSize > this.gamePanel.player.worldX - this.gamePanel.player.screenX
                && this.worldX - this.gamePanel.tileSize < this.gamePanel.player.worldX + this.gamePanel.player.screenX
                && this.worldY + this.gamePanel.tileSize > this.gamePanel.player.worldY - this.gamePanel.player.screenY
                && this.worldY - this.gamePanel.tileSize < this.gamePanel.player.worldY + this.gamePanel.player.screenY) {
            switch(this.direction) {
                case "up":
                    if (this.spriteNum == 1) {
                        image = this.up1;
                    }
                    if (this.spriteNum == 2) {
                        image = this.up2;
                    }
                    break;
                case "down":
                    if (this.spriteNum == 1) {
                        image = this.down1;
                    }
                    if (this.spriteNum == 2) {
                        image = this.down2;
                    }
                    break;
                case "left":
                    if (this.spriteNum == 1) {
                        image = this.left1;
                    }
                    if (this.spriteNum == 2) {
                        image = this.left2;
                    }
                    break;
                case "right":
                    if (this.spriteNum == 1) {
                        image = this.right1;
                    }
                    if (this.spriteNum == 2) {
                        image = this.right2;
                    }
                    break;
            }
            g2.drawImage(image, screenX, screenY, this.gamePanel.tileSize, this.gamePanel.tileSize, null);
            g2.setColor(Color.red);
            g2.drawRect(screenX + this.hitBox.x, screenY + this.hitBox.y, this.hitBox.width, this.hitBox.height);
        }
    }


    // Verificar mudanças na entidade
    public void update() {
        this.setAction();

        collisionOn = false;
        this.gamePanel.cChecker.checkTile(this);
        if (! this.collisionOn) {
            switch (this.direction) {
                case "up": this.worldY -= this.speed; break;
                case "down": this.worldY += this.speed; break;
                case "left": this.worldX -= this.speed; break;
                case "right": this.worldX += this.speed; break;
            }
        }

        this.spriteCounter++;
        if (this.spriteCounter > 10) {
            if (this.spriteNum == 1) {
                this.spriteNum = 2;
            } else if (this.spriteNum == 2) {
                this.spriteNum = 1;
            }
            this.spriteCounter = 0;
        }
    }


    // Verificar qual ação será tomada
    public void setAction() {

    }

}
