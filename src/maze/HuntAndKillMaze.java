package maze;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HuntAndKillMaze extends Maze {
    public HuntAndKillMaze(int numRows, int numColumns) {
        super(numRows, numColumns);
        generateMaze();
    }


    public void generateMaze() {
        Random random = new Random();
        Cell currentCell = getRandomCell();

        while (currentCell != null) {
            currentCell.setVisited();

            // Verifica se há vizinhos que não foram visitados ainda
            List<Cell> unvisitedNeighbours = new ArrayList<>();
            for (Cell neighbour : currentCell.getAllNeighbours()) {
                if (neighbour != null && !neighbour.visited) {
                    unvisitedNeighbours.add(neighbour);
                }
            }


            if (!unvisitedNeighbours.isEmpty()) {
                // KILL: Seleciona um vizinho aleatório e abre um caminho
                Cell randomNeighbour = unvisitedNeighbours.get(random.nextInt(unvisitedNeighbours.size()));
                currentCell.link(randomNeighbour);
                randomNeighbour.setVisited();
                currentCell = randomNeighbour;
            } else {
                // HUNT: Percorre o labirinto até achar uma célula que
                // não foi visitada, mas possui um vizinho que foi visitado
                currentCell = null;

                outerloop:
                for (int i = 0; i < numRows; i++) {
                    for (int j = 0; j < numColumns; j++) {
                        Cell cell = maze[i][j];
                        if (!cell.visited) {
                            List<Cell> visitedNeighbours = new ArrayList<>();
                            for (Cell neighbour : cell.getAllNeighbours()) {
                                if (neighbour != null && neighbour.visited) {
                                    visitedNeighbours.add(neighbour);
                                }
                            }


                            if (!visitedNeighbours.isEmpty()) {
                                Cell randomNeighbor = visitedNeighbours.get(random.nextInt(visitedNeighbours.size()));
                                cell.link(randomNeighbor);
                                randomNeighbor.setVisited();
                                currentCell = cell;
                                break outerloop;
                            }
                        }
                    }
                }
            }
        }
    }


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
        HuntAndKillMaze huntAndKillMaze = new HuntAndKillMaze(10, 10);
        int[][] tileMap = huntAndKillMaze.toTileMap();

        // Exibe o labirinto como uma matriz de 1s e 0s
        for (int i = 0; i < tileMap.length; i++) {
            for (int j = 0; j < tileMap[0].length; j++) {
                System.out.print(tileMap[i][j] + " ");
            }
            System.out.println();
        }
    }
}
