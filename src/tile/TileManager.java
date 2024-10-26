package tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import main.GamePanel;
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
        try {
            for (int i = 0; i < tiles.length; i++) {
                tiles[i] = new Tile();
            }
            tiles[0].image = ImageIO.read(ClassLoader.getSystemResource("tiles/bricks.png"));
            tiles[1].image = ImageIO.read(ClassLoader.getSystemResource("tiles/flowers.png"));
            tiles[2].image = ImageIO.read(ClassLoader.getSystemResource("tiles/mushroom.png"));
            tiles[3].image = ImageIO.read(ClassLoader.getSystemResource("tiles/tall_grass.png"));
            tiles[4].image = ImageIO.read(ClassLoader.getSystemResource("tiles/water_01.png"));
            tiles[5].image = ImageIO.read(ClassLoader.getSystemResource("tiles/grass.png"));

            tiles[0].collision = true;
            tiles[2].collision = true;
            tiles[4].collision = true;
        } catch(IOException e) {
            e.printStackTrace();
        }
    }


    // Carregar o arquivo txt do mapa
    public void getMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            assert is != null;
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
