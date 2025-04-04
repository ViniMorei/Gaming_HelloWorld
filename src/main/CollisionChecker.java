package main;

import entity.Entity;
import attack.Attack;

public class CollisionChecker {
    GamePanel gamePanel;

    // Construtor
    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }


    // Verificação se a hitBox da Entidade
    // está tocando algum tile com colisão
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


    // Verificação se a hitBox da Entidade
    // está tocando algum objeto
    public int checkObject(Entity entity, boolean player) {
        int index = 999;

        for (int i = 0; i < this.gamePanel.objects.length; i++) {
            if (this.gamePanel.objects[i] != null) {
                // Pega a posição absoluta das
                // coordenadas da hitbox
                entity.hitBox.x = entity.worldX + entity.hitBox.x;
                entity.hitBox.y = entity.worldY + entity.hitBox.y;

                this.gamePanel.objects[i].hitBox.x = this.gamePanel.objects[i].worldX + this.gamePanel.objects[i].hitBox.x;
                this.gamePanel.objects[i].hitBox.y = this.gamePanel.objects[i].worldY + this.gamePanel.objects[i].hitBox.y;

                // Move a hitbox conforme a direção
                switch(entity.direction) {
                    case "up" -> entity.hitBox.y -= entity.speed;
                    case "down" -> entity.hitBox.y += entity.speed;
                    case "left" -> entity.hitBox.x -= entity.speed;
                    case "right" -> entity.hitBox.x += entity.speed;
                }

                // Verifica colisão
                if (entity.hitBox.intersects(this.gamePanel.objects[i].hitBox)) {
                    if (this.gamePanel.objects[i].collision) {
                        entity.collisionOn = true;
                    }
                    if (player) {
                        index = i;
                    }
                }

                entity.hitBox.x = entity.hitBoxDefaultX;
                entity.hitBox.y = entity.hitBoxDefaultY;
                this.gamePanel.objects[i].hitBox.x = this.gamePanel.objects[i].hitBoxDefaultX;
                this.gamePanel.objects[i].hitBox.y = this.gamePanel.objects[i].hitBoxDefaultY;
            }
        }

        return index;
    }


    // Verificar colisão com monstros
    public void checkMonster(Entity entity, boolean player) {
        if (!entity.invincible) {
            for (int i = 0; i < this.gamePanel.monsters.length; i++) {
                if (this.gamePanel.monsters[i] != null) {
                    // Pega a posição absoluta das
                    // coordenadas da hitbox
                    entity.hitBox.x = entity.worldX + entity.hitBox.x;
                    entity.hitBox.y = entity.worldY + entity.hitBox.y;

                    this.gamePanel.monsters[i].hitBox.x = this.gamePanel.monsters[i].worldX + this.gamePanel.monsters[i].hitBox.x;
                    this.gamePanel.monsters[i].hitBox.y = this.gamePanel.monsters[i].worldY + this.gamePanel.monsters[i].hitBox.y;

                    switch(entity.direction) {
                        case "up":
                            entity.hitBox.y -= entity.speed;
                            if (entity.hitBox.intersects(this.gamePanel.monsters[i].hitBox)) {
                                entity.collisionOn = true;
                                if (player) {
                                    this.gamePanel.player.health--;
                                    this.gamePanel.player.invincible = true;
                                }
                            }
                            break;
                        case "down":
                            entity.hitBox.y += entity.speed;
                            if (entity.hitBox.intersects(this.gamePanel.monsters[i].hitBox)) {
                                entity.collisionOn = true;
                                if (player) {
                                    this.gamePanel.player.health--;
                                    this.gamePanel.player.invincible = true;
                                }
                            }
                            break;
                        case "left":
                            entity.hitBox.x -= entity.speed;
                            if (entity.hitBox.intersects(this.gamePanel.monsters[i].hitBox)) {
                                entity.collisionOn = true;
                                if (player) {
                                    this.gamePanel.player.health--;
                                    this.gamePanel.player.invincible = true;
                                }
                            }
                            break;
                        case "right":
                            entity.hitBox.x += entity.speed;
                            if (entity.hitBox.intersects(this.gamePanel.monsters[i].hitBox)) {
                                entity.collisionOn = true;
                                if (player) {
                                    this.gamePanel.player.health--;
                                    this.gamePanel.player.invincible = true;
                                }
                            }
                            break;
                    }

                    entity.hitBox.x = entity.hitBoxDefaultX;
                    entity.hitBox.y = entity.hitBoxDefaultY;
                    this.gamePanel.monsters[i].hitBox.x = this.gamePanel.monsters[i].hitBoxDefaultX;
                    this.gamePanel.monsters[i].hitBox.y = this.gamePanel.monsters[i].hitBoxDefaultY;
                }
            }
        }
    }

    // Versão alternativa do checkMonster para verificar se
    // algum ataque (projétil ou estático) colidiu com um monstro
    public void checkMonster(Attack attack) {
        for (int i = 0; i < this.gamePanel.monsters.length; i++) {
            if (this.gamePanel.monsters[i] != null) {
                String monsterName = this.gamePanel.monsters[i].name;
                int monsterPoints = switch (monsterName) {
                    case "Mimic" -> 500;
                    case "Slime" -> 250;
                    default -> 0;
                };

                attack.hitBox.x = attack.worldX + attack.hitBox.x;
                attack.hitBox.y = attack.worldY + attack.hitBox.y;

                this.gamePanel.monsters[i].hitBox.x = this.gamePanel.monsters[i].worldX + this.gamePanel.monsters[i].hitBox.x;
                this.gamePanel.monsters[i].hitBox.y = this.gamePanel.monsters[i].worldY + this.gamePanel.monsters[i].hitBox.y;

                // Move a hitbox conforme a direção
                switch(attack.direction) {
                    case "up" -> attack.hitBox.y -= attack.speed;
                    case "down" -> attack.hitBox.y += attack.speed;
                    case "left" -> attack.hitBox.x -= attack.speed;
                    case "right" -> attack.hitBox.x += attack.speed;
                }

                // Verificação da colisão
                if (attack.hitBox.intersects(this.gamePanel.monsters[i].hitBox)) {
                    this.gamePanel.monsters[i].health--;
                    this.gamePanel.player.currentAttack.active = false;
                    attack.active = false;


                    if (this.gamePanel.monsters[i].health <= 0) {
                        // O monstro morre e pontua o player
                        this.gamePanel.player.score += monsterPoints;
                        this.gamePanel.monsters[i] = null;
                    }


                }

                attack.hitBox.x = attack.hitBoxDefaultX;
                attack.hitBox.y = attack.hitBoxDefaultY;
                if (this.gamePanel.monsters[i] != null) {
                    this.gamePanel.monsters[i].hitBox.x = this.gamePanel.monsters[i].hitBoxDefaultX;
                    this.gamePanel.monsters[i].hitBox.y = this.gamePanel.monsters[i].hitBoxDefaultY;
                }
            }
        }
    }
}
