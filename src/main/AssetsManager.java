package main;

import entity.*;
import object.*;

public class AssetsManager {
    GamePanel gamePanel;

    // Construtor
    public AssetsManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }


    // Instanciar objetos
    public void setObjects() {
        Utils utils = new Utils();
        int[] rowCol;

        // Instanciação dos objetos
        // 24 objetos ao total, sendo 4 de cada tipo
        for (int i = 0; i < this.gamePanel.objects.length; i++) {
            rowCol = utils.getRandomTile(this.gamePanel.tileManager);
            if (i < 4) {
                this.gamePanel.objects[i] = new Key(this.gamePanel, rowCol[0], rowCol[1]);
            } else if (i < 8) {
                this.gamePanel.objects[i] = new Chest(this.gamePanel, rowCol[0], rowCol[1]);
            } else if (i < 12) {
                this.gamePanel.objects[i] = new Heart(this.gamePanel, rowCol[0], rowCol[1]);
            } else if (i < 16) {
                this.gamePanel.objects[i] = new Crystal(this.gamePanel, rowCol[0], rowCol[1]);
            } else if (i < 20) {
                this.gamePanel.objects[i] = new Lizard(this.gamePanel, rowCol[0], rowCol[1]);
            } else if (i < 24) {
                this.gamePanel.objects[i] = new Yarn(this.gamePanel, rowCol[0], rowCol[1]);
            }
        }
    }


    // Instanciar monstros
    public void setMonsters() {
        Utils utils = new Utils();
        int[] rowCol;

        // Instanciação dos monstros
        // 10 monstros ao total, 2 de cada tipo
        for (int i = 0; i < this.gamePanel.monsters.length; i++) {
            rowCol = utils.getRandomTile(this.gamePanel.tileManager);
            if (i % 2 == 0) {
                this.gamePanel.monsters[i] = new Slime(this.gamePanel, rowCol[0], rowCol[1]);
            } else {
                this.gamePanel.monsters[i] = new Mimic(this.gamePanel, rowCol[0], rowCol[1]);
            }
        }
    }
}
