package fr.yoms.microcosme;

import fr.yoms.microcosme.entities.Entity;
import fr.yoms.microcosme.entities.EntityManager;
import fr.yoms.microcosme.entities.livings.animals.Headnimal;
import fr.yoms.microcosme.graphics.Display;
import fr.yoms.microcosme.inputs.KeyManager;
import fr.yoms.microcosme.inputs.MouseManager;
import fr.yoms.microcosme.utils.Position;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Microcosme implements Runnable {

    private final Display display;
    private final int maxTPS;
    private Thread thread;

    private boolean running = false;

    private BufferStrategy bufferStrategy;
    private Graphics graphics;

    private final Handler handler;

    private final MouseManager mouseManager;
    private final KeyManager keyManager;
    private final EntityManager entityManager;

    private int TPS;
    private int FPS;

    public Microcosme(Display display, int maxTPS) {

        this.display = display;
        this.maxTPS = maxTPS;

        mouseManager = new MouseManager();
        keyManager = new KeyManager();

        handler = new Handler(this);
        entityManager = new EntityManager(handler);
    }
    public Microcosme(String title, int width, int height, int maxTPS) {


        this.display = new Display(title, width, height);
        this.maxTPS = maxTPS;

        mouseManager = new MouseManager();
        keyManager = new KeyManager();

        handler = new Handler(this);
        entityManager = new EntityManager(handler);
    }

    private void init() {

        display.getFrame().addMouseListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        display.getFrame().addKeyListener(keyManager);

        try {

            new Robot().mouseMove(
                    (int) display.getFrame().getLocationOnScreen().getX() + display.getWidth() / 2,
                    (int) display.getFrame().getLocationOnScreen().getY() + display.getHeight() / 2
            );
        } catch (AWTException e) {

            e.printStackTrace();
        }

        int i = 0;
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 2; y++) {

                entityManager.addEntity(new Headnimal(i, handler, new Position(x * 200 + 50, y * 200 + 50)));
                i++;
            }
        }
    }

    @Override
    public void run() {

        init();

        double timePerUpdate = (float) 1000000000 / maxTPS;
        double deltaTPS = 0;
        long now;
        long lastUpdateTime = System.nanoTime();
        long lastRenderTime = System.nanoTime();

        // Debug
        long timerTPS = 0;
        long timerFPS = 0;
        int ticks = 0;
        int frames = 0;


        while (running) {

            now = System.nanoTime();
            deltaTPS += (now - lastUpdateTime) / timePerUpdate;
            timerTPS += now - lastUpdateTime;
            timerFPS += now - lastRenderTime;

            lastUpdateTime = lastRenderTime = now;

            if (deltaTPS >= 1) {

                update();
                ticks++;
                deltaTPS--;
            }

            render();
            frames++;

            if (timerTPS >= 1000000000) {

                TPS = ticks;
                ticks = 0;
                timerTPS = 0;
            }
            if (timerFPS >= 1000000000) {

                FPS = frames;
                frames = 0;
                timerFPS = 0;
            }
        }

        stop();
    }

    private void update() {

        keyManager.update();
        mouseManager.update();
        entityManager.update();
    }

    private void render() {

        if (!preRender()) return;

        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, display.getWidth(), display.getHeight());


        entityManager.render(graphics);
        drawDebug();


        postRender();
    }

    private boolean preRender() {
        bufferStrategy = display.getCanvas().getBufferStrategy();

        if (bufferStrategy == null) {

            display.getCanvas().createBufferStrategy(3);
            return false;
        }

        graphics = bufferStrategy.getDrawGraphics();
        graphics.clearRect(0, 0, display.getWidth(), display.getHeight());
        graphics.setFont(graphics.getFont().deriveFont(14F));
        return true;
    }
    private void postRender() {

        bufferStrategy.show();
        graphics.dispose();
    }

    private void drawDebug() {

        Entity selectedEntity = entityManager.getSelectedEntity();
        LinkedList<Object> leftLines = new LinkedList<>(
                Arrays.asList(
                    Color.WHITE,
                    "TPS> " + TPS + "/" + maxTPS,
                    "FPS> " + FPS,
                    "Entities> " + entityManager.getEntities().toString(),
                    "Keys> " +
                            (keyManager.up || keyManager.down || keyManager.left || keyManager.right ? "" : "NONE") +
                            (keyManager.up ? "UP " : "") + (keyManager.down ? "DOWN " : "") +
                            (keyManager.left ? "LEFT " : "") + (keyManager.right ? "RIGHT " : ""),
                    "Selected Entity> " + (selectedEntity != null ? "" : "NONE")
                )
        );

        if (selectedEntity != null) {

            leftLines.addAll(
                    Arrays.asList(
                            "",
                            "ID> " + selectedEntity.getId(),
                            "Position> X:" + selectedEntity.getPosition().getX() + " Y:" + selectedEntity.getPosition().getY(),
                            "Size> W:" + selectedEntity.getWidth() + " H:" + selectedEntity.getHeight()
                    )
            );
        }

        final Position mousePosition = mouseManager.getMousePosition();
        LinkedList<Object> mouseLines = new LinkedList<>(Arrays.asList(
                Color.RED,
                "X: " + mousePosition.getX(),
                "Y: " + mousePosition.getY()
        ));

        for (Entity entity: entityManager.getEntities()) {

            if (entity.getHitBox(0, 0).contains(mousePosition.getX(), mousePosition.getY())) {

                mouseLines.add("Entity : " + entity.toString() + (entityManager.getSelectedEntity() == entity ? " SEL" : ""));
                break;
            }
        }

        drawDebug(
                leftLines,
                mouseLines,
                Collections.emptyList()
        );
    }
    private void drawDebug(List<Object> leftLines, List<Object> mouseLines, List<Object> rightLines) {

        if (keyManager.debug) {

            graphics.setColor(Color.blue);
            entityManager.getEntities().forEach(entity -> {

                Rectangle hitBox = entity.getHitBox(0, 0);
                graphics.drawRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
            });


            AtomicInteger i = new AtomicInteger(12);

            leftLines.forEach(line -> {

                if (line instanceof Color) graphics.setColor((Color) line);
                else if (line instanceof String) graphics.drawString((String) line, 5, i.getAndAdd(14));
            });

            i.set(35);

            mouseLines.forEach(line -> {

                if (line instanceof Color) graphics.setColor((Color) line);
                else if (line instanceof String) graphics.drawString((String) line, (int) mouseManager.getMousePosition().getX(), (int) mouseManager.getMousePosition().getY() + i.getAndAdd(15));
            });
        }
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
    public EntityManager getEntityManager() {

        return entityManager;
    }
    public int getTPS() {

        return TPS;
    }
    public int getMaxTPS() {

        return maxTPS;
    }

}
