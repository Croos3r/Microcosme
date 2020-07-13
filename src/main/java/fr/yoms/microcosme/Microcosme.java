package fr.yoms.microcosme;

import fr.yoms.microcosme.display.Display;
import fr.yoms.microcosme.utils.Images;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Microcosme implements Runnable {

    private final Display display;
    private final int fps;
    private Thread thread;

    private boolean running = false;

    private BufferStrategy bufferStrategy;
    private Graphics graphics;

    public Microcosme(Display display, int fps) {

        this.display = display;
        this.fps = fps;
    }

    public Microcosme(String title, int height, int width, int fps) {


        this.display = new Display(title, height, width);
        this.fps = fps;
    }

    @Override
    public void run() {

        init();

        double timePerUpdate = (float) 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        // Debug
        long timer = 0;
        int ticks = 0;


        while (running) {

            now = System.nanoTime();
            delta += (now - lastTime) / timePerUpdate;
            // Debug
            timer += now - lastTime;


            lastTime = now;

            if (delta >= 1) {

                update();
                render();
                // Debug
                ticks++;

                delta--;
            }

            // Debug
            if (timer >= 1000000000) {

                System.out.println("UPS: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }

        stop();
    }

    private final BufferedImage image = Images.loadImage("/textures/image.jpg");

    private void update() {

    }

    private void render() {

        bufferStrategy = display.getCanvas().getBufferStrategy();

        if (bufferStrategy == null) {

            display.getCanvas().createBufferStrategy(3);
            return;
        }

        graphics = bufferStrategy.getDrawGraphics();


        graphics.clearRect(0, 0, display.getWidth(), display.getHeight());


        graphics.drawImage(image, 0, 0, null);


        bufferStrategy.show();
        graphics.dispose();
    }

    private void init() {


    }

    public synchronized void start() {

        if (!running) {

            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    public synchronized void stop() {

        if (running) {

            try {

                thread.join();
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }
    }

    public Display getDisplay() {

        return display;
    }
}
