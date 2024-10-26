package maze;

import java.util.Random;

// Percorre horizontalmente todas as linhas e conecta cada
// célula com o vizinho norte ou oeste aleatoriamente
public class BinaryTreeMaze extends Maze {
    public BinaryTreeMaze(int numRows, int numColumns) {
        super(numRows, numColumns);
        generateMaze();
    }


    // Aqui que a mágica acontece
    public void generateMaze() {
        Random random = new Random();
        Cell currentCell, cellNorth, cellWest;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                currentCell = this.maze[i][j];
                cellNorth = currentCell.getNeighbour(0);
                cellWest = currentCell.getNeighbour(3);

                if (cellNorth != null && cellWest != null) {
                    if (random.nextBoolean()) {
                        currentCell.link(cellNorth);
                    } else {
                        currentCell.link(cellWest);
                    }
                } else if (cellNorth != null) {
                    currentCell.link(cellNorth);
                } else if (cellWest != null) {
                    currentCell.link(cellWest);
                }

            }
        }
    }


    // Transforma o objeto Maze em um formato
    // legível para o algoritmo de TileManager
    public int[][] toTileMap() {
        int[][] tileMap;
        Cell currentCell, cellEast, cellSouth;
        int x, y;

        // Declara uma matriz nula (todos os elementos iguais a zero)
        tileMap = new int[numRows * 2 + 1][numColumns * 2 + 1];
        // As células precisam de um espaço ao redor delas para servir como
        // parede ou caminho, e o cálculo de * 2 + 1 faz essa transformação
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                // Por padrão, todas as células
                // são caminho, e recebem o valor 1
                x = i * 2 + 1;
                y = j * 2 + 1;
                tileMap[x][y] = 1;

                currentCell = this.maze[i][j];
                cellEast = currentCell.getNeighbour(1);
                cellSouth = currentCell.getNeighbour(2);

                // Verifica se as células vizinhas a leste e a sul
                // estão conectadas. Caso sim, refletir isso no tilempa
                if (currentCell.getNeighbour(1) != null
                    && currentCell.links.contains(cellEast)) {
                    tileMap[x][y + 1] = 1;
                }
                if (currentCell.getNeighbour(2) != null
                    && currentCell.links.contains(cellSouth)) {
                    tileMap[x + 1][y] = 1;
                }
            }
        }

        return tileMap;
    }


    // Teste
    public static void main(String[] args) {
        BinaryTreeMaze binaryMaze = new BinaryTreeMaze(10, 10);
        int[][] tileMap = binaryMaze.toTileMap();
        for (int i = 0; i < tileMap.length; i++) {
            for (int j = 0; j < tileMap[1].length; j++) {
                System.out.print(tileMap[i][j] + " ");
            }
            System.out.println();
        }
    }
}
