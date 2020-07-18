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
    public boolean canSee(Entity entity) {

        if (entity.equals(this)) return false;
        return fov.intersects(entity.getHitBox());
    }

    public List<Entity> getVisibles() {

        return visibles;
    }
    public void addVisibile(Entity entity) {

        if (!visibles.contains(entity) && !entity.equals(this)) visibles.add(entity);
    }
    public void removeVisible(int id) {

        visibles.removeIf(entity -> entity.getId() == id);
    }

    public void move(double xStep, double yStep) {

        if (!checkEntityCollision(xStep, yStep))
            position.add(xStep, yStep);
        else destination = null;
    }
}
