package fr.yoms.microcosme.hitboxes;

import fr.yoms.microcosme.utils.Position;

public abstract class HitBox {

    protected final Position center;

    public HitBox(Position center) {

        this.center = center;
    }

    public abstract boolean intersects(Object o);
    public abstract boolean contains(Position position);

    public abstract double getPerimeter();
    public abstract double getArea();

    public Position getCenter() {

        return center;
    }
}
