package fr.yoms.microcosme.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Images {

    public static BufferedImage loadImage(String path) {

        try {

            return ImageIO.read(Images.class.getResource(path));
        } catch (IOException e) {

            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }

    public static BufferedImage crop(BufferedImage image, int x, int y, int width, int height) {
        return image.getSubimage(x, y, width, height);
    }
}