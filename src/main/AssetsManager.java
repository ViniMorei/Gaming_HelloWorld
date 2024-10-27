package main;

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

        rowCol = utils.getRandomTile(this.gamePanel.tileManager);
        this.gamePanel.objects[0] = new Key(this.gamePanel, rowCol[1], rowCol[0]);
        rowCol = utils.getRandomTile(this.gamePanel.tileManager);
        this.gamePanel.objects[1] = new Chest(this.gamePanel, rowCol[1], rowCol[0]);
        rowCol = utils.getRandomTile(this.gamePanel.tileManager);
        this.gamePanel.objects[2] = new Crystal(this.gamePanel, rowCol[1], rowCol[0]);


    }


    // Instanciar NPCs
    public void setNPCs() {

    }


    // Instanciar monstros
    public void setMonsters() {

    }
}
