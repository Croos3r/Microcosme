package fr.yoms.microcosme.graphics;

import fr.yoms.microcosme.utils.Images;

import java.awt.image.BufferedImage;

@SuppressWarnings("FieldCanBeLocal")
public class Resources {

    public BufferedImage background;
    public BufferedImage head;
    public BufferedImage rock;

    private final String textures = "/textures/";

    private final String png = ".png";
    private final String jpg = ".jpg";
    private final String gif = ".gif";

    public void init() {

        background = Images.loadImage(textures + "background" + jpg);
        head = Images.loadImage(textures + "head" + png);
        rock = Images.loadImage(textures + "rock" + png);
    }
}
