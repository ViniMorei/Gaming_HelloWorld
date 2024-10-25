package main;

import entity.Entity;

public class CollisionChecker {
    GamePanel gamePanel;

    // Construtor
    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }


    // Verificação se a hitBox da Entidade
    // está tocando algum objeto com colisão
    public void checkTile(Entity entity) {
        // Encontrando as coordenadas da hitBox utilizando
        // as coordenadas da Entidade como auxiliar
        int hitBoxLeftWorldX = entity.worldX + entity.hitBox.x;
        int hitBoxRightWorldX = entity.worldX + entity.hitBox.x + entity.hitBox.width;
        int hitBoxTopWorldY = entity.worldY + entity.hitBox.y;
        int hitBoxBottomWorldY = entity.worldY + entity.hitBox.y + entity.hitBox.height;

        // Verifica quais tiles e Entidade está tocando
        int hitBoxLeftColumn = hitBoxLeftWorldX / this.gamePanel.tileSize;
        int hitBoxRightColumn = hitBoxRightWorldX / this.gamePanel.tileSize;
        int hitBoxTopRow = hitBoxTopWorldY / this.gamePanel.tileSize;
        int hitBoxBottomRow = hitBoxBottomWorldY / this.gamePanel.tileSize;

        int tileNum1, tileNum2;

        switch(entity.direction) {
            case "up":
                hitBoxTopRow = (hitBoxTopWorldY - entity.speed) / this.gamePanel.tileSize;
                tileNum1 = this.gamePanel.tileManager.mapTileNum[hitBoxLeftColumn][hitBoxTopRow];
                tileNum2 = this.gamePanel.tileManager.mapTileNum[hitBoxRightColumn][hitBoxTopRow];

                if (this.gamePanel.tileManager.tiles[tileNum1].collision ||
                    this.gamePanel.tileManager.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                hitBoxBottomRow = (hitBoxBottomWorldY + entity.speed) / this.gamePanel.tileSize;
                tileNum1 = this.gamePanel.tileManager.mapTileNum[hitBoxLeftColumn][hitBoxBottomRow];
                tileNum2 = this.gamePanel.tileManager.mapTileNum[hitBoxRightColumn][hitBoxBottomRow];

                if (this.gamePanel.tileManager.tiles[tileNum1].collision ||
                    this.gamePanel.tileManager.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                hitBoxLeftColumn = (hitBoxLeftWorldX - entity.speed) / this.gamePanel.tileSize;
                tileNum1 = this.gamePanel.tileManager.mapTileNum[hitBoxLeftColumn][hitBoxTopRow];
                tileNum2 = this.gamePanel.tileManager.mapTileNum[hitBoxLeftColumn][hitBoxBottomRow];

                if (this.gamePanel.tileManager.tiles[tileNum1].collision ||
                    this.gamePanel.tileManager.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                hitBoxRightColumn = (hitBoxRightWorldX + entity.speed) / this.gamePanel.tileSize;
                tileNum1 = this.gamePanel.tileManager.mapTileNum[hitBoxRightColumn][hitBoxTopRow];
                tileNum2 = this.gamePanel.tileManager.mapTileNum[hitBoxRightColumn][hitBoxBottomRow];

                if (this.gamePanel.tileManager.tiles[tileNum1].collision ||
                    this.gamePanel.tileManager.tiles[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
        }
    }
}
