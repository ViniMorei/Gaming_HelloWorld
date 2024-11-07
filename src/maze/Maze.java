package maze;

import java.util.Random;

public class Maze {
    int numRows;
    int numColumns;
    Cell[][] maze;

    // Construtor
    public Maze(int numRows, int numColumns) {
        this.numRows = numRows;
        this.numColumns = numColumns;
        this.maze = new Cell[numRows][numColumns];

        // Inicializa as células
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                this.maze[i][j] = new Cell(i, j);
            }
        }

        // Define as células vizinhas
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                if (i > 0) {
                    // Vizinho NORTE
                    this.maze[i][j].addNeighbour(0, this.maze[i - 1][j]);
                }
                if (j < numColumns - 1) {
                    // Vizinho LESTE
                    this.maze[i][j].addNeighbour(1, this.maze[i][j + 1]);
                }
                if (i < numRows - 1) {
                    // Vizinho SUL
                    this.maze[i][j].addNeighbour(2, this.maze[i + 1][j]);
                }
                if (j > 0) {
                    // Vizinho OESTE
                    this.maze[i][j].addNeighbour(3, this.maze[i][j - 1]);
                }
            }
        }
    }


    // Retornar o número de células
    public int getNumberOfCells() {
        return maze.length * maze[0].length;
    }

    // Pegar uma célula aleatória
    public Cell getRandomCell() {
        Random random = new Random();
        int x = random.nextInt(this.numRows);
        int y = random.nextInt(this.numColumns);

        return this.maze[x][y];
    }


    // Transformar em tileMap
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
}
