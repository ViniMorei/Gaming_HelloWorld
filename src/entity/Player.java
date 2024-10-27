package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;

import main.KeyHandler;
import main.GamePanel;
import main.Utils;

public class Player extends Entity {
    // Capturar entradas do teclado para movimentação
    public KeyHandler keyHandler;

    // Atributos finais definindo onde o jogador será exibido na tela
    public final int screenX;
    public final int screenY;

    // Atributos de jogo
    public int keys;
    public int maxHealth, health;
    public int maxMana, mana;

    // Construtor
    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        super(gamePanel);
        this.keyHandler = keyHandler;

        // Define o centro da tela como as coordenadas definitivas do jogador
        this.screenX = this.gamePanel.screenWidth / 2 - (this.gamePanel.tileSize / 2);
        this.screenY = this.gamePanel.screenHeight / 2 - (this.gamePanel.tileSize / 2);

        // Configurações da hitbox
        this.hitBox = new Rectangle();
        this.hitBox.x = 4 * this.gamePanel.scale;
        this.hitBox.y = 7 * this.gamePanel.scale;
        this.hitBoxDefaultX = this.hitBox.x;
        this.hitBoxDefaultY = this.hitBox.y;
        this.hitBox.width = 7 * this.gamePanel.scale;
        this.hitBox.height = 7 * this.gamePanel.scale;

        // Inicializa os métodos do jogador
        this.defineDefaultValues();
        this.getPlayerImage();
    }


    // Posição e velocidade inicial do jogador
    public void defineDefaultValues() {
        // Inicializa o jogador na linha 1, coluna 1 do world map
        this.worldX = this.gamePanel.tileSize;
        this.worldY = this.gamePanel.tileSize;
        this.speed = 6;
        // Inicializa uma direção inicial, para um sprite ser exibido
        this.direction = "down";
    }


    // Atribuir os sprites aos atributos
    public void getPlayerImage() {
        this.up1 = this.setupSprite("/player/cat_up_01.png");
        this.up2 = this.setupSprite("/player/cat_up_02.png");
        this.down1 = this.setupSprite("/player/cat_down_01.png");
        this.down2 = this.setupSprite("/player/cat_down_02.png");
        this.left1 = this.setupSprite("/player/cat_left_01.png");
        this.left2 = this.setupSprite("/player/cat_left_02.png");
        this.right1 = this.setupSprite("/player/cat_right_01.png");
        this.right2 = this.setupSprite("/player/cat_right_02.png");
    }


    // Interagir com objetos
    public void pickUpObject(int index) {
        if (index != 999) {
            String name = this.gamePanel.objects[index].name;

            switch (name) {
                case "Key":
                    this.gamePanel.objects[index] = null;
                    keys ++;
                    break;
                case "Chest":
                    if (this.keys > 0) {
                        this.gamePanel.objects[index] = null;
                        this.keys --;
                    } else {
                        this.collisionOn = true;
                    }
                    break;
                default:
                    this.gamePanel.objects[index] = null;
                    break;
            }
        }
    }

    // Atualizar as informações do jogador
    public void update() {
        // Breve explicação:
        // A partir do input, as coordenadas do jogador no mapa são atualizadas e
        // dentro do TileManager, ele desenha o mapa de acordo com esses valores
        // Então se os atributos worldY ou worldX do jogador forem alterados, o
        // mapa será redesenhado de acordo com essas coordenadas. Pois a entidade
        // jogador fica fixa no centro da tela e tudo se move ao seu redor
        if (keyHandler.upPressed){
            this.direction = "up";
        } else if (keyHandler.downPressed){
            this.direction = "down";
        } else if (keyHandler.leftPressed){
            this.direction = "left";
        } else if (keyHandler.rightPressed){
            this.direction = "right";
        }

        // Verifica se há colisão
        this.collisionOn = false;
        this.gamePanel.cChecker.checkTile(this);

        int objIndex = this.gamePanel.cChecker.checkObject(this, true);
        pickUpObject(objIndex);

        // Só pode se mexer se não tiver colisão
        if (! this.collisionOn) {
            // Mover essa condição para mais externa dentro do
            // update caso queira que o sprite fique estático
        if(keyHandler.upPressed || keyHandler.downPressed ||
           keyHandler.leftPressed || keyHandler.rightPressed) {
                switch (this.direction) {
                    case "up":
                        this.worldY -= this.speed;
                        break;
                    case "down":
                        this.worldY += this.speed;
                        break;
                    case "left":
                        this.worldX -= this.speed;
                        break;
                    case "right":
                        this.worldX += this.speed;
                        break;
                }
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


    // Refletir as mudanças no GamePanel
    public void draw(Graphics2D g2) {
        BufferedImage image = null;

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

        g2.drawImage(image, this.screenX, this.screenY, this.gamePanel.tileSize, this.gamePanel.tileSize, null );
    }
}
