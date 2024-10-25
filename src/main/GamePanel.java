package main;

import entity.Player;
import tile.TileManager;

import java.awt.*;
import javax.swing.JPanel;

// Classe que irá exibir o jogo na tela (JFrame)
public class GamePanel extends JPanel implements Runnable {
    // Configurações da tela
    final int originalTileSize = 16; // Tile de 16x16 pixels
    final int scale = 3; // Escala para redimensionar os tiles
    public final int maxScreenColumns = 16; // Número de tiles horizontalmente
    public final int maxScreenRows = 12; // Número de tiles verticalmente

    public final int tileSize = originalTileSize * scale; // 48x48
    public final int screenWidth = maxScreenColumns * tileSize;
    public final int screenHeight = maxScreenRows * tileSize;

    // Configurações do World Map
    public final int maxWorldColumns = 50;
    public final int maxWorldRows = 50;
    public final int worldWidth = tileSize * maxWorldColumns;
    public final int worldHeight = tileSize * maxWorldRows;

    // Configurações do jogo
    double FPS = 60;
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    TileManager tileManager = new TileManager(this);
    public Player player = new Player(this, keyH);
    public CollisionChecker cChecker = new CollisionChecker(this);

    // Construtor
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(keyH);
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
        player.update();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        tileManager.draw(g2);
        player.draw(g2);

        g2.dispose();
    }
}
