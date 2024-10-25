package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    // Atributos padrão (posição e velocidade)
    public int worldX; // Define onde no mapa a entidade será exibida
    public int worldY;
    public int speed; // Quantos pixels se move
    public String direction; // Define sprite e movimentação

    // Sprites (dois de cada para animar movimentação)
    public BufferedImage up1;
    public BufferedImage up2;
    public BufferedImage down1;
    public BufferedImage down2;
    public BufferedImage left1;
    public BufferedImage left2;
    public BufferedImage right1;
    public BufferedImage right2;

    // Utilizados para fazer a lógica da troca de sprites da mesma direção (andar)
    public int spriteCounter = 0;
    public int spriteNum = 1;

    // Configurações de colisão
    public Rectangle hitBox;
    public boolean collisionOn = false;
}
