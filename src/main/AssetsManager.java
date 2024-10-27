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
        this.gamePanel.objects[0] = new Key(this.gamePanel, 2, 1);
        this.gamePanel.objects[1] = new Chest(this.gamePanel, 1, 3);
        this.gamePanel.objects[2] = new Crystal(this.gamePanel, 4, 1);

    }


    // Instanciar NPCs
    public void setNPCs() {

    }


    // Instanciar monstros
    public void setMonsters() {

    }
}
