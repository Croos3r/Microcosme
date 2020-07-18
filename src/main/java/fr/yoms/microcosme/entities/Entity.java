package fr.yoms.microcosme.entities;

import fr.yoms.microcosme.Handler;
import fr.yoms.microcosme.hitboxes.HitBox;
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
    protected final HitBox hitBox;

    public Entity(int id, Handler handler, Position position, int width, int height, HitBox hitBox) {

        this.id = id;
        this.handler = handler;
        this.position = position;
        this.width = width;
        this.height = height;
        this.hitBox = hitBox;
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

        HitBox hitBox = this.hitBox;
        hitBox.setCenter(new Position(hitBox.getCenter().getX() + xOffset, hitBox.getCenter().getY() + yOffset));

        for (Entity entity : handler.getGame().getEntityManager().getEntities()) {
            if (entity.equals(this)) continue;
            if (hitBox.intersects(entity.getHitBox()))
                return true;
        }
        return false;
    }

    public HitBox getHitBox() {

        return hitBox;
    }

    public abstract void update();
    public abstract void render(Graphics graphics);

    public abstract void drawHitBox(Graphics graphics);
    public abstract void drawSelector(Graphics graphics, Color color);

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                ", position=" + position +
                ", width=" + width +
                ", height=" + height +
                ", hitBox=" + hitBox +
                '}';
    }
}
