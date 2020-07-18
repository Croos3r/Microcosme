package fr.yoms.microcosme.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Images {

    /**
     * Loads image by their path
     * @param path Image file path
     * @return Buffered image of the image file passed if found, else return null object
     */
    public static BufferedImage loadImage(String path) {

        try {

            return ImageIO.read(Images.class.getResource(path));
        } catch (IOException e) {

            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }
}