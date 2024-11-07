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
