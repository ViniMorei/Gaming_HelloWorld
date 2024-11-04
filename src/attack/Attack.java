package attack;

import java.awt.*;

public abstract class Attack {
    public int worldX, worldY;
    public String direction;
    public int maxCounter;
    public int counter = 0;


    public abstract void update();

    public abstract void draw(Graphics2D g2);

    public boolean isActive() {
        return counter < maxCounter;
    }

    public void incrementCounter() {
        counter++;
    }
}
