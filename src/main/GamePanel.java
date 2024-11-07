package main;

import java.awt.*;
import javax.swing.JPanel;

import attack.Attack;
import entity.*;
import object.GameObject;
import tile.TileManager;

// Classe que irá exibir o jogo na tela (JFrame)
public class GamePanel extends JPanel implements Runnable {
    // Configurações da tela
    public final int originalTileSize = 16; // Tile de 16x16 pixels
    public final int scale = 3; // Escala para redimensionar os tiles
    public final int maxScreenColumns = 16; // Número de tiles horizontalmente
    public final int maxScreenRows = 12; // Número de tiles verticalmente

    public final int tileSize = originalTileSize * scale; // 48x48
    public final int screenWidth = maxScreenColumns * tileSize;
    public final int screenHeight = maxScreenRows * tileSize;

    // Configurações do World Map
    public final int maxWorldColumns = 55;
    public final int maxWorldRows = 55;

    // Configurações do jogo
    Thread gameThread;
    double FPS = 60;
    public UI ui = new UI(this);
    public KeyHandler keyH = new KeyHandler(this);
    public Sound sound = new Sound();
    public TileManager tileManager = new TileManager(this);
    public CollisionChecker cChecker = new CollisionChecker(this);

    // Entities e assets
    public Player player = new Player(this, keyH);
    public AssetsManager assets = new AssetsManager(this);
    public GameObject[] objects = new GameObject[24];
    public Entity[] monsters = new Entity[10];

    // Game State
    public int gameState;
    public final int TITLE_STATE = 0;
    public final int PLAY_STATE = 1;
    public final int PAUSE_STATE = 2;
    public final int GAME_OVER_STATE = 3;
    public final int FINISHED_STATE = 4;

    // Dificuldade
    public boolean hard = false;


    // Construtor
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(keyH);
        this.setupGame();
    }


    public void setupGame() {
        this.gameState = TITLE_STATE;
        this.assets.setObjects();
        this.assets.setMonsters();
    }

    // Começa a execução do script
    // Semelhante ao Start() no Unity
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }


    // Inicia o game loop chamando a thread
    // Semelhante ao Update() no Unity
    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1){
                update();
                repaint();
                delta--;
            }
        }
    }


    public void update() {
        if (gameState == PLAY_STATE) {
            player.update();
            for (int i = 0; i < monsters.length; i++) {
                if (monsters[i] != null) {
                    monsters[i].update();
                }
            }
            tileManager.updateBackground();
        }
        if (gameState == PAUSE_STATE) {

        }
        if (gameState == FINISHED_STATE || gameState == GAME_OVER_STATE) {
            this.gameThread = null;
        }
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (gameState == TITLE_STATE) {
            ui.draw(g2);
        } else {
            tileManager.drawBackground(g2);
            tileManager.draw(g2);
            for (int i = 0; i < objects.length; i++) {
                if (objects[i] != null) {
                    objects[i].draw(g2);
                }
            }
            for (int i = 0; i < monsters.length; i++) {
                if (monsters[i] != null) {
                    monsters[i].draw(g2);
                }
            }
            player.draw(g2);
            Attack currentAttack = this.player.currentAttack;
            if (currentAttack != null && currentAttack.isActive()) {
                currentAttack.draw(g2);
            }

            ui.draw(g2);

            g2.dispose();
        }
    }


    // Tocar efeitos sonoros
    public void playSFX(int i) {
        sound.setFile(i);
        sound.play();
    }
}
