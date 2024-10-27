package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

import tile.TileManager;

public class Utils {
    // Redimensionar uma imagem para a escala definida
    public BufferedImage scaleImage(BufferedImage img, int newWidth, int newHeight) {
        BufferedImage scaledImage = new BufferedImage(newWidth, newHeight, img.getType());
        Graphics2D g = scaledImage.createGraphics();
        g.drawImage(img, 0, 0, newWidth, newHeight, null);
        g.dispose();

        return scaledImage;
    }


    // Retornar um tile válido aleatório
    public int[] getRandomTile(TileManager tileManager) {
        Random random = new Random();
        return tileManager.pathTiles.get(random.nextInt(tileManager.pathTiles.size()));
    }
}
