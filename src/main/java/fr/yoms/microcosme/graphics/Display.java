package fr.yoms.microcosme.graphics;

import javax.swing.*;
import java.awt.*;

public class Display {

    private JFrame frame;
    private Canvas canvas;

    private final String title;
    private final int height, width;

    public Display(String title, int width, int height) {

        this.title = title;
        this.height = height;
        this.width = width;

        init();
    }

    private void init() {

        this.frame = new JFrame(title);

        this.frame.setSize(this.width, this.height);
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);

        this.canvas = new Canvas();
        this.canvas.setPreferredSize(new Dimension(this.width, this.height));
        this.canvas.setMaximumSize(new Dimension(this.width, this.height));
        this.canvas.setMinimumSize(new Dimension(this.width, this.height));
        this.canvas.setFocusable(false);

        this.frame.add(this.canvas);
        this.frame.pack();
    }

    public String getTitle() {
        return title;
    }
    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }
    public Canvas getCanvas() {

        return canvas;
    }
    public JFrame getFrame() {
        return frame;
    }
}
