package tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

import main.GamePanel;
import main.Utils;
import maze.BinaryTreeMaze;

public class TileManager {
    public GamePanel gamePanel;
    public Tile[] tiles;
    public int[][] mapTileNum;

    // Construtor
    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.tiles = new Tile[10];
        this.mapTileNum = new int[this.gamePanel.maxWorldColumns][this.gamePanel.maxWorldRows];

        // Inicializa os métodos de TileManager
        this.getMaze();
        this.getTileImage();
    }


    // Atribuir as imagens dos tiles a atributos
    public void getTileImage() {
        setupImage(0, "bricks", true);
        setupImage(1, "grass", false);
        setupImage(2, "flowers", false);
        setupImage(3, "mushroom", true);
        setupImage(4, "tall_grass", false);
        setupImage(5, "rocks", false);
        setupImage(6, "water_01", true);
        setupImage(7, "water_02", true);
    }


    // Carrega a imagem (já redimensionada)
    public void setupImage(int index, String fileName, boolean collision) {
        Utils util = new Utils();

        try {
            tiles[index] = new Tile();
            tiles[index].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/" + fileName + ".png")));
            tiles[index].image = util.scaleImage(tiles[index].image, this.gamePanel.tileSize, this.gamePanel.tileSize);
            tiles[index].collision = collision;

        } catch(IOException e) {
            e.printStackTrace();
        }
    }


    // Carregar o arquivo txt do mapa
    public void getMap(String filePath) {
        try {
            InputStream is = Objects.requireNonNull(getClass().getResourceAsStream(filePath));
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            // Variáveis locais para controlar a linha e coluna do arquivo
            int col = 0;
            int row = 0;

            // Só executa até o limite do arquivo, nos limites de linha e coluna
            while(col < this.gamePanel.maxWorldColumns
                    && row < this.gamePanel.maxWorldRows) {
                String line = br.readLine();

                // Isola cada caractere na linha e atribui
                // ao lugar correspondente no mapTileNum
                while(col < this.gamePanel.maxWorldColumns) {
                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    this.mapTileNum[col][row] = num;
                    col++;
                }
                if (col == this.gamePanel.maxWorldColumns) {
                    col = 0;
                    row++;
                }
            }

            br.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    // Gerar um labirinto
    public void getMaze() {
        BinaryTreeMaze maze = new BinaryTreeMaze((this.gamePanel.maxWorldRows - 1) / 2, (this.gamePanel.maxWorldColumns - 1) / 2);
        int [][] tileMap = maze.toTileMap();

        for (int i = 0; i < this.gamePanel.maxWorldRows; i++) {
            for (int j = 0; j < this.gamePanel.maxWorldColumns; j++) {
                this.mapTileNum[i][j] = tileMap[i][j];

            }
        }
    }

    // Exibir na tela
    public void draw(Graphics g2) {
        int worldCol = 0;
        int worldRow = 0;

        // Enquanto as variáveis locais não passarem dos limites do world map:
        while (worldCol < this.gamePanel.maxWorldColumns
                && worldRow < this.gamePanel.maxWorldRows) {
            // Utiliza o player como referência para desenhar o mapa a
            // n pixels de distância dele, dependendo de suas coordenadas
            int worldX = worldCol * this.gamePanel.tileSize;
            int worldY = worldRow * this.gamePanel.tileSize;
            int screenX = worldX - this.gamePanel.player.worldX + this.gamePanel.player.screenX;
            int screenY = worldY - this.gamePanel.player.worldY + this.gamePanel.player.screenY;

            int tileNum = this.mapTileNum[worldCol][worldRow];

            if (worldX + this.gamePanel.tileSize > this.gamePanel.player.worldX - this.gamePanel.player.screenX &&
                worldX - this.gamePanel.tileSize < this.gamePanel.player.worldX + this.gamePanel.player.screenX &&
                worldY + this.gamePanel.tileSize > this.gamePanel.player.worldY - this.gamePanel.player.screenY &&
                worldY - this.gamePanel.tileSize < this.gamePanel.player.worldY + this.gamePanel.player.screenY) {
                    g2.drawImage(tiles[tileNum].image, screenX, screenY, this.gamePanel.tileSize, this.gamePanel.tileSize, null);
            }

            worldCol++;

            if (worldCol == this.gamePanel.maxWorldColumns) {
                worldCol = 0;
                worldRow ++;
            }
        }
    }
}
