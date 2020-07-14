package fr.yoms.microcosme.entities;

import fr.yoms.microcosme.utils.Position;

import java.awt.*;

public abstract class Entity {

    protected final int id;
    protected Position position;
    protected int width;
    protected int height;

    public Entity(int id, Position position, int width, int height) {

        this.id = id;
        this.position = position;
        this.width = width;
        this.height = height;
    }

    public int getId() {
        return id;
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

    abstract void update();
    abstract void render(Graphics graphics);
}
