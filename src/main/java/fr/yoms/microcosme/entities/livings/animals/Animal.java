package fr.yoms.microcosme.entities.livings.animals;

import fr.yoms.microcosme.Handler;
import fr.yoms.microcosme.entities.livings.LivingEntity;
import fr.yoms.microcosme.utils.Position;

public abstract class Animal extends LivingEntity {

    public static final double DEFAULT_STEP = 3;

    protected double step;
    protected Position destination = null;

    public Animal(int id, Handler handler, Position position, int width, int height, double health, int age, double step) {

        super(id, handler, position, width, height, health, age);

        this.step = step;
    }

    public double getStep() {

        return step;
    }
    public void setStep(int step) {

        this.step = step;
    }

    public void move(double xStep, double yStep) {

        if (!checkEntityCollision(xStep, 0))
            position.add(xStep, 0);
        if (!checkEntityCollision(0, yStep))
            position.add(0, yStep);
    }

    public Position getDestination() {

        return destination;
    }
    public void setDestination(Position destination) {

        this.destination = destination;
    }
}
