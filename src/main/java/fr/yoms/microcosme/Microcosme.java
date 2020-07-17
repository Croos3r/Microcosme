package fr.yoms.microcosme;

import fr.yoms.microcosme.entities.Entity;
import fr.yoms.microcosme.entities.EntityManager;
import fr.yoms.microcosme.entities.livings.animals.Animal;
import fr.yoms.microcosme.entities.livings.animals.Headnimal;
import fr.yoms.microcosme.entities.tiles.Rock;
import fr.yoms.microcosme.graphics.Display;
import fr.yoms.microcosme.graphics.Resources;
import fr.yoms.microcosme.inputs.KeyManager;
import fr.yoms.microcosme.inputs.MouseManager;
import fr.yoms.microcosme.utils.Position;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
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
    private final Resources resources;

    private int TPS;
    private int FPS;

    public Microcosme(Display display, int maxTPS) {

        this.display = display;
        this.maxTPS = maxTPS;

        mouseManager = new MouseManager();
        keyManager = new KeyManager();

        handler = new Handler(this);
        entityManager = new EntityManager(handler);
        resources = new Resources();
    }
    public Microcosme(String title, int width, int height, int maxTPS) {


        this.display = new Display(title, width, height);
        this.maxTPS = maxTPS;

        mouseManager = new MouseManager();
        keyManager = new KeyManager();

        handler = new Handler(this);
        entityManager = new EntityManager(handler);
        resources = new Resources();
    }

    private void init() {

        resources.init();

        display.getFrame().addMouseListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().setIconImage(resources.head);

        try {

            new Robot().mouseMove(
                    (int) display.getFrame().getLocationOnScreen().getX() + display.getWidth() / 2,
                    (int) display.getFrame().getLocationOnScreen().getY() + display.getHeight() / 2
            );
        } catch (AWTException e) {

            e.printStackTrace();
        }

        entityManager.addEntity(new Headnimal(0, handler, Position.randomPosition(display.getWidth(), display.getHeight())));
        for (int i = 0; i < 10; i++) entityManager.addEntity(new Rock(1 + i, handler, Position.randomPosition(display.getWidth(), display.getHeight())));
    }

    @Override
    public void run() {

        init();

        double timePerUpdate = (float) 1000000000 / maxTPS;
        double deltaTPS = 0;
        long now;
        long lastTime = System.nanoTime();

        // Debug
        long timer = 0;
        int ticks = 0;
        int frames = 0;


        while (running) {

            now = System.nanoTime();
            deltaTPS += (now - lastTime) / timePerUpdate;
            timer += now - lastTime;

            lastTime = now;

            if (deltaTPS >= 1) {

                update();
                ticks++;
                deltaTPS--;
            }

            render();
            frames++;

            if (timer >= 1000000000) {

                TPS = ticks;
                ticks = 0;
                FPS = frames;
                frames = 0;
                timer = 0;
            }
        }

        stop();
    }

    private void update() {

        keyManager.update();
        mouseManager.update();
        entityManager.update();

        if (keyManager.exit) System.exit(0);
    }

    private void render() {

        if (!preRender()) return;

        graphics.drawImage(resources.background, 0, 0, display.getWidth(), display.getHeight(), null);
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
                    "Entities> " + entityManager.getEntities().size(),
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
            
            if (selectedEntity instanceof Animal) {

                Animal selectedAnimal = (Animal) selectedEntity;

                leftLines.addAll(
                        Arrays.asList(
                                "Destination> " + (selectedAnimal.getDestination() != null ? "X:" + selectedAnimal.getDestination().getX() + " Y:" + selectedAnimal.getDestination().getY() : "NONE"),
                                "FOV> R:" + selectedAnimal.getFov().getRadius() + " A:" + selectedAnimal.getFov().getArea(),
                                "Visibles> " + selectedAnimal.getVisibles().toString()
                        )
                );
            }
        }

        final Position mousePosition = mouseManager.getMousePosition();
        LinkedList<Object> mouseLines = new LinkedList<>(Arrays.asList(
                Color.WHITE,
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
                if (entity instanceof Animal) {

                    Animal animal = (Animal) entity;
                    graphics.setColor(Color.BLUE);

                    if (animal.getDestination() != null){

                        graphics.drawLine(
                                (int) animal.getPosition().getX(),
                                (int) animal.getPosition().getY(),
                                (int) animal.getDestination().getX(),
                                (int) animal.getDestination().getY()
                        );
                    }
                    graphics.drawOval((int) (animal.getPosition().getX() - animal.getFov().getRadius()), (int) (animal.getPosition().getY() - animal.getFov().getRadius()), (int) (animal.getFov().getRadius() * 2), (int) (animal.getFov().getRadius() * 2));
                }
            });


            Entity selectedEntity = entityManager.getSelectedEntity();
            if (selectedEntity instanceof Animal) ((Animal) selectedEntity).getVisibles().forEach(entity -> entityManager.drawEntitySelector(entity, graphics, Color.GREEN));


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
    public Resources getResources() {

        return resources;
    }
    public int getTPS() {

        return TPS;
    }
    public int getMaxTPS() {

        return maxTPS;
    }
}
