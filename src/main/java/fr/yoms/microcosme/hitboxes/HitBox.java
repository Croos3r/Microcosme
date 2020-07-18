package fr.yoms.microcosme.hitboxes;

import fr.yoms.microcosme.utils.Position;

public abstract class HitBox {

    protected Position center;

    public HitBox(Position center) {

        this.center = center;
    }

    /**
     * Update whole HitBox by setting it's center
     * @param center New center position
     */
    public void update(Position center) {

        this.center = center;
    }

    /**
     * Check if object intersects with this HitBox
     * @param o Object to check
     * @return false if object not intersects or if object is unknown, else return true
     */
    public abstract boolean intersects(Object o);

    /**
     * Check if position is contained in this HitBox
     * @param position Position to check
     * @return true if position is contained in this HitBox, else return false
     */
    public abstract boolean contains(Position position);

    // Getters and setters

    public abstract double getPerimeter();
    public abstract double getArea();

    public Position getCenter() {

        return center;
    }
    public void setCenter(Position center) {

        this.center = center;
    }

    @Override
    public String toString() {
        return "HitBox{" +
                "center=" + center +
                '}';
    }
}
