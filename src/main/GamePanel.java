package main;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
    // Configurações da tela
    final int original_tile_size = 16; // Tile de 16x16 pixels
    final int max_screen_columns = 16; // Número de tiles horizontalmente
    final int max_screen_rows = 12; // Número de tiles verticalmente
    final int scale = 3; // Escala para redimensionar os tiles

    final int tile_size = original_tile_size * scale; // 48x48
    final int screen_width = max_screen_columns * tile_size;
    final int screen_height = max_screen_rows * tile_size;


    public GamePanel() {
        this.setPreferredSize(new Dimension(screen_width, screen_height));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
    }
}
