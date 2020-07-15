package fr.yoms.microcosme.entities;

import fr.yoms.microcosme.Handler;
import fr.yoms.microcosme.utils.Position;

import java.awt.*;

public abstract class Entity {

    public static final int DEFAULT_WIDTH = 64, DEFAULT_HEIGHT = 64;
    public static final Position DEFAULT_POSITION = new Position(0, 0);

    protected final int id;
    protected final Handler handler;
    protected Position position;
    protected int width;
    protected int height;
    protected Rectangle hitBox;

    public Entity(int id, Handler handler, Position position, int width, int height) {

        this.id = id;
        this.handler = handler;
        this.position = position;
        this.width = width;
        this.height = height;

        hitBox = new Rectangle(0, 0, width, height);
    }

    public int getId() {
        return id;
    }
    public Handler getHandler() {

        return handler;
    }

    public Position getPosition() {
        return position;
    }
    public void setPosition(Position position) {
        this.position = position;
    }

    public int getWidth() {

        return width;
    }
    public void setWidth(int width) {

        this.width = width;
    }

    public int getHeight() {

        return height;
    }
    public void setHeight(int height) {

        this.height = height;
    }

    public boolean checkEntityCollision(double xOffset, double yOffset) {

        for (Entity entity : handler.getGame().getEntityManager().getEntities()) {
            if (entity.equals(this)) continue;
            if (entity.getHitBox(0, 0).intersects(getHitBox(xOffset, yOffset)))
                return true;
        }
        return false;
    }

    public Rectangle getHitBox(double xOffset, double yOffset) {

        return new Rectangle((int) (hitBox.x + xOffset), (int) (hitBox.y + yOffset), hitBox.width, hitBox.height);
    }
    public void setHitBox(Rectangle hitBox) {

        this.hitBox = hitBox;
    }

    public abstract void update();
    public abstract void render(Graphics graphics);

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                ", x=" + position.getX() +
                ", y=" + position.getY() +
                '}';
    }
}
