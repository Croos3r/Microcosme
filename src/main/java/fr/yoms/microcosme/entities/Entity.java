package fr.yoms.microcosme.entities;

import fr.yoms.microcosme.utils.Position;

import java.awt.*;

public abstract class Entity {

    public static final Position DEFAULT_SPAWN_POSITION = new Position(0, 0);

    protected final int id;
    protected Position position = DEFAULT_SPAWN_POSITION;

    public Entity(int id) {

        this.id = id;
    }

    public Entity(int id, Position position) {

        this.id = id;
        this.position = position;
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

    abstract void update();
    abstract void render(Graphics graphics);
}
