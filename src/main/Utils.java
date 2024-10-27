package main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Utils {
    // Redimensionar uma imagem para a escala definida
    public BufferedImage scaleImage(BufferedImage img, int newWidth, int newHeight) {
        BufferedImage scaledImage = new BufferedImage(newWidth, newHeight, img.getType());
        Graphics2D g = scaledImage.createGraphics();
        g.drawImage(img, 0, 0, newWidth, newHeight, null);
        g.dispose();

        return scaledImage;
    }
}
