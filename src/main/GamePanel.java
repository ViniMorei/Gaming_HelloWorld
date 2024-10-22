package main;

import java.awt.*;
import javax.swing.JPanel;

// Classe que irá exibir o jogo na tela (JFrame)
public class GamePanel extends JPanel implements Runnable {
    // Configurações da tela
    final int originalTileSize = 16; // Tile de 16x16 pixels
    final int maxScreenColumns = 16; // Número de tiles horizontalmente
    final int maxScreenRows = 12; // Número de tiles verticalmente
    final int scale = 3; // Escala para redimensionar os tiles

    final int tileSize = originalTileSize * scale; // 48x48
    final int screenWidth = maxScreenColumns * tileSize;
    final int screenHeight = maxScreenRows * tileSize;

    // Configurações do jogador
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    double FPS = 60;
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

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
        if (keyH.upPressed){
            playerY -= playerSpeed;
        } else if (keyH.downPressed){
            playerY += playerSpeed;
        } else if (keyH.leftPressed){
            playerX -= playerSpeed;
        } else if (keyH.rightPressed){
            playerX += playerSpeed;
        }
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, tileSize, tileSize);

        g2.dispose();
    }
}
