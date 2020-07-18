package fr.yoms.microcosme.entities.livings.animals;

import fr.yoms.microcosme.Handler;
import fr.yoms.microcosme.entities.Entity;
import fr.yoms.microcosme.entities.livings.LivingEntity;
import fr.yoms.microcosme.hitboxes.HitBox;
import fr.yoms.microcosme.hitboxes.circular.CircularHitBox;
import fr.yoms.microcosme.utils.Position;

import java.util.ArrayList;
import java.util.List;

public abstract class Animal extends LivingEntity {

    // Default constants
    public static final double DEFAULT_STEP = 3;
    public static final int DEFAULT_FOV_RADIUS = 100;

    protected double step;
    protected Position destination = null;
    protected final CircularHitBox fov;
    protected final List<Entity> visibles = new ArrayList<>();

    public Animal(int id, Handler handler, Position position, int width, int height, double health, double maxHealth, int age, int maxAge, double step, HitBox hitBox, int fovRadius) {

        super(id, handler, position, width, height, health, maxHealth, age, maxAge, hitBox);

        this.step = step;
        this.fov = new CircularHitBox(position, fovRadius);
    }

    /**
     * Check if animal can see other entity
     * @param entity {@link Entity} checked
     * @return false if entity checked not intersects with {@link #fov} circle or if checked entity is this animal
     *
     * @see #fov
     * @see HitBox#intersects
     */
    public boolean canSee(Entity entity) {

        if (entity.equals(this)) return false;
        return fov.intersects(entity.getHitBox());
    }

    /**
     * Add an entity to the {@link #visibles} list if it's not already in and if it's not this animal
     * @param entity {@link Entity} to add
     */
    public void addVisibile(Entity entity) {

        if (!visibles.contains(entity) && !entity.equals(this)) visibles.add(entity);
    }

    /**
     * Remove an entity by it's id from the {@link #visibles} if it's in
     * @param id Entity to remove {@link Entity#id}
     */
    public void removeVisible(int id) {

        visibles.removeIf(entity -> entity.getId() == id);
    }

    /**
     * Move animal's {@link #position} if new {@link HitBox} is not colliding with other entity's hit box
     * @param xStep x distance to add to current position
     * @param yStep y distance to add to current position
     * @see Entity#checkEntityCollision
     */
    public void move(double xStep, double yStep) {

        if (!checkEntityCollision(xStep, yStep))
            position.add(xStep, yStep);
        else destination = null;
    }

    // Getters and setters

    public double getStep() {

        return step;
    }
    public void setStep(double step) {

        this.step = step;
    }

    public Position getDestination() {

        return destination;
    }
    public void setDestination(Position destination) {

        this.destination = destination;
    }

    public CircularHitBox getFov() {

        return fov;
    }

    public List<Entity> getVisibles() {

        return visibles;
    }
}
