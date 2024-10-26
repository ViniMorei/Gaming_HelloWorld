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


    // Retornar todas as células
    // Função iterator no python
    public void getAllCells() {
        // Lógica de programação
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

}
