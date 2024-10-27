package main;

import java.awt.*;
import javax.swing.JPanel;

import entity.Player;
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
    public final int worldWidth = tileSize * maxWorldColumns;
    public final int worldHeight = tileSize * maxWorldRows;

    // Configurações do jogo
    Thread gameThread;
    double FPS = 60;
    public UI ui = new UI(this);
    public KeyHandler keyH = new KeyHandler(this);
    public TileManager tileManager = new TileManager(this);
    public CollisionChecker cChecker = new CollisionChecker(this);
    public Player player = new Player(this, keyH);

    // Game State
    public int gameState;
    public final int PLAY_STATE = 1;
    public final int PAUSE_STATE = 2;


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
        this.gameState = PLAY_STATE;
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
        }
        if (gameState == PAUSE_STATE) {

        }
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        tileManager.draw(g2);
        player.draw(g2);
        ui.draw(g2);

        g2.dispose();
    }
}
