package fr.yoms.microcosme;

import fr.yoms.microcosme.display.Display;
import fr.yoms.microcosme.inputs.KeyManager;
import fr.yoms.microcosme.inputs.MouseManager;
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

    private final Handler handler;

    private final MouseManager mouseManager;
    private final KeyManager keyManager;

    public Microcosme(Display display, int fps) {

        this.display = display;
        this.fps = fps;

        handler = new Handler(this);
        mouseManager = new MouseManager();
        keyManager = new KeyManager();
    }

    public Microcosme(String title, int height, int width, int fps) {


        this.display = new Display(title, height, width);
        this.fps = fps;

        handler = new Handler(this);
        mouseManager = new MouseManager();
        keyManager = new KeyManager();
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

    private final BufferedImage image = Images.loadImage("/textures/bodes1.png");


    private void update() {


    }

    private void render() {

        if (!preRender()) return;


        if (keyManager.getDebugKey()) {

            graphics.drawString("Y: " + mouseManager.getMousePosition().getY(), (int) mouseManager.getMousePosition().getX(), (int) mouseManager.getMousePosition().getY() + 35);
            graphics.drawString("X: " + mouseManager.getMousePosition().getX(), (int) mouseManager.getMousePosition().getX(), (int) mouseManager.getMousePosition().getY() + 50);
        }


        postRender();
    }

    private void postRender() {
        bufferStrategy.show();
        graphics.dispose();
    }

    private boolean preRender() {
        bufferStrategy = display.getCanvas().getBufferStrategy();

        if (bufferStrategy == null) {

            display.getCanvas().createBufferStrategy(3);
            return false;
        }

        graphics = bufferStrategy.getDrawGraphics();
        graphics.clearRect(0, 0, display.getWidth(), display.getHeight());
        return true;
    }

    private void init() {

        display.getFrame().addMouseListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        display.getFrame().addKeyListener(keyManager);
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

    public Handler getHandler() {

        return handler;
    }
    public Display getDisplay() {

        return display;
    }
    public KeyManager getKeyManager() {

        return keyManager;
    }
    public MouseManager getMouseManager() {

        return mouseManager;
    }
}
