package fr.yoms.microcosme.entities.livings.animals;

import fr.yoms.microcosme.Handler;
import fr.yoms.microcosme.entities.Entity;
import fr.yoms.microcosme.hitboxes.circular.CircularHitBox;
import fr.yoms.microcosme.utils.Position;

import java.awt.*;

public class Headnimal extends Animal {

    private long lastMoveTime = 0;

    public Headnimal(int id, Handler handler, Position position) {

        super(
                id,
                handler,
                position,
                DEFAULT_WIDTH,
                DEFAULT_HEIGHT,
                DEFAULT_HEALTH,
                DEFAULT_MAX_HEALTH,
                DEFAULT_AGE,
                DEFAULT_MAX_AGE,
                5,
                new CircularHitBox(position, (double) (DEFAULT_WIDTH + DEFAULT_HEIGHT) / 4),
                100);
    }

    @Override
    public void update() {

        long now = System.currentTimeMillis();

        Entity selectedEntity = handler.getGame().getEntityManager().getSelectedEntity();
        if (destination == null && selectedEntity != this && now >= lastMoveTime + 500)
            destination = Position.randomPosition(handler.getDisplay().getWidth(), handler.getDisplay().getHeight());
        if (selectedEntity != null && selectedEntity.equals(this) && this.destination == null) {

            if (handler.getKeyManager().up)
                move(0, -step);
            if (handler.getKeyManager().down)
                move(0, step);
            if (handler.getKeyManager().left)
                move(-step, 0);
            if (handler.getKeyManager().right)
                move(step, 0);

            lastMoveTime = now;
        }
        else if (destination != null) {

            double dx = destination.getX();
            double ox = position.getX();
            double dy = destination.getY();
            double oy = position.getY();

            if ((ox < dx - step || ox > dx + step) || (oy < dy - step || oy > dy + step)) {

                double vx = dx - ox;
                double vy = dy - oy;

                double distance = Math.sqrt(vx * vx + vy * vy);

                double dirX = vx / distance;
                double dirY = vy / distance;

                double xStep = dirX * step;
                double yStep = dirY * step;

                move(xStep, yStep);
                lastMoveTime = System.currentTimeMillis();

            } else {

                position = destination;
                destination = null;
            }
        }

        handler.getGame().getEntityManager().getEntities().forEach(entity -> {

            if (canSee(entity)) addVisibile(entity);
            else removeVisible(entity.getId());
        });

        hitBox.update(position);
        fov.update(position);
    }

    @Override
    public void render(Graphics graphics) {

        graphics.drawImage(handler.getGame().getResources().head, (int) position.getX() - width / 2, (int) (position.getY() - height / 2), width, height, null);
    }

    @Override
    public void drawHitBox(Graphics graphics) {

        Position center = hitBox.getCenter();
        double radius = ((CircularHitBox) hitBox).getRadius();

        graphics.drawOval((int) (center.getX() - radius), (int) (center.getY() - radius), (int) radius * 2, (int) radius * 2);
    }

    @Override
    public void drawSelector(Graphics graphics, Color color) {

        graphics.setColor(color);
        graphics.drawOval((int) this.position.getX() - this.getWidth() / 2 - 3, (int) this.position.getY() - this.getHeight() / 2 - 3, this.width + 6, this.height + 6);
    }
}
