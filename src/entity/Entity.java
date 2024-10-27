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

    // Configurações de colisão
    public Rectangle hitBox;
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


}
