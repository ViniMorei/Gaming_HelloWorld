package maze;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cell {
    int x, y;
    List<Cell> links;
    Cell[] neighbours;
    boolean visited;

    // Construtor
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.links = new ArrayList<>();
        this.neighbours = new Cell[4];
        this.visited = false;
    }


    // Utilizar quando a célula já tiver sido visitada
    public void setVisited() {
        this.visited = true;
    }


    // Adicionar células vizinhas
    public void addNeighbour(int direction, Cell cell) {
        // Vizinhos definidos em sentido horário:
        // 0 = Norte; 1 = Leste; 2 = Sul; 3 = Oeste
        this.neighbours[direction] = cell;
    }


    // Pegar a célula vizinha
    public Cell getNeighbour(int direction) {
        if (this.neighbours[direction] == null) {
            return null;
        } else {
            return this.neighbours[direction];
        }
    }


    // Pegar todas as células vizinhas
    public Cell[] getAllNeighbours() {
        return neighbours;
    }


    // Pegar célula vizinha aleatória
    public Cell randomNeighbour() {
        Random random = new Random();
        while (true) {
            int index = random.nextInt(neighbours.length);
            if (this.neighbours[index] != null) {
                return this.neighbours[index];
            }
        }
    }


    // Conectar duas células
    public void link(Cell cell) {
        this.links.add(cell);
        cell.links.add(this);
    }


    // Retornar o número de conexões
    public int getNumberOfLinks() {
        return links.size();
    }
}
