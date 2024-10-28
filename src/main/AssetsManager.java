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

        // Instanciação dos objetos
        rowCol = utils.getRandomTile(this.gamePanel.tileManager);
        this.gamePanel.objects[0] = new Key(this.gamePanel, rowCol[0], rowCol[1]);

        rowCol = utils.getRandomTile(this.gamePanel.tileManager);
        this.gamePanel.objects[1] = new Chest(this.gamePanel, rowCol[0], rowCol[1]);

        rowCol = utils.getRandomTile(this.gamePanel.tileManager);
        this.gamePanel.objects[2] = new Crystal(this.gamePanel, rowCol[0], rowCol[1]);

        rowCol = utils.getRandomTile(this.gamePanel.tileManager);
        this.gamePanel.objects[3] = new Key(this.gamePanel, rowCol[0], rowCol[1]);

        rowCol = utils.getRandomTile(this.gamePanel.tileManager);
        this.gamePanel.objects[4] = new Chest(this.gamePanel, rowCol[0], rowCol[1]);

        rowCol = utils.getRandomTile(this.gamePanel.tileManager);
        this.gamePanel.objects[5] = new Crystal(this.gamePanel, rowCol[0], rowCol[1]);

        rowCol = utils.getRandomTile(this.gamePanel.tileManager);
        this.gamePanel.objects[6] = new Key(this.gamePanel, rowCol[0], rowCol[1]);

        rowCol = utils.getRandomTile(this.gamePanel.tileManager);
        this.gamePanel.objects[7] = new Chest(this.gamePanel, rowCol[0], rowCol[1]);

        rowCol = utils.getRandomTile(this.gamePanel.tileManager);
        this.gamePanel.objects[8] = new Crystal(this.gamePanel, rowCol[0], rowCol[1]);

        rowCol = utils.getRandomTile(this.gamePanel.tileManager);
        this.gamePanel.objects[9] = new Key(this.gamePanel, rowCol[0], rowCol[1]);
    }


    // Instanciar NPCs
    public void setNPCs() {

    }


    // Instanciar monstros
    public void setMonsters() {

    }
}
