package fr.yoms.microcosme.graphics;

import fr.yoms.microcosme.utils.Images;

import java.awt.image.BufferedImage;

public class Ressources {

    public BufferedImage background;
    public BufferedImage head;

    public void init() {

        background = Images.loadImage("/textures/background.jpg");
        head = Images.loadImage("/textures/bodes1.png");
    }
}
