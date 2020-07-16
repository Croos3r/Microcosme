package fr.yoms.microcosme.entities.livings.animals;

import fr.yoms.microcosme.Handler;
import fr.yoms.microcosme.entities.Entity;
import fr.yoms.microcosme.utils.Position;

import java.awt.*;

public class Headnimal extends Animal {

    private long lastMoveTime = 0;

    public Headnimal(int id, Handler handler, Position position) {

        super(id, handler, position, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_HEALTH, DEFAULT_MAX_HEALTH, DEFAULT_AGE, DEFAULT_MAX_AGE, 5);

        hitBox.x = (int) position.getX() + width / 6 - width / 2;
        hitBox.y = (int) position.getY() + height / 6 - height / 2;

        hitBox.width = width - width / 3;
        hitBox.height = height - height / 3;
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

            } else {

                position = destination;
                destination = null;
                lastMoveTime = System.currentTimeMillis();
            }
        }

        hitBox.x = (int) position.getX() + width / 6 - width / 2;
        hitBox.y = (int) position.getY() + height / 6 - height / 2;

        hitBox.width = width - width / 3;
        hitBox.height = height - height / 3;
    }

    @Override
    public void render(Graphics graphics) {

        graphics.drawImage(handler.getGame().getRessources().head, (int) position.getX() - width / 2, (int) (position.getY() - height / 2), width, height, null);
    }

    public void setLastMoveTime(long lastMoveTime) {

        this.lastMoveTime = lastMoveTime;
    }
}
