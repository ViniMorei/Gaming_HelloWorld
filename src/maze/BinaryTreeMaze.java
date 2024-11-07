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
