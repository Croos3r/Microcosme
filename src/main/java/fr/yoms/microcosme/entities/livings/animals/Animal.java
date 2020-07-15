package fr.yoms.microcosme.entities.livings.animals;

import fr.yoms.microcosme.Handler;
import fr.yoms.microcosme.entities.livings.LivingEntity;
import fr.yoms.microcosme.utils.Position;

public abstract class Animal extends LivingEntity {

    public static final float DEFAULT_STEP = 3.0f;

    protected float step;

    public Animal(int id, Handler handler, Position position, int width, int height, double health, int age, float step) {

        super(id, handler, position, width, height, health, age);

        this.step = step;
    }

    public float getStep() {

        return step;
    }
    public void setStep(float step) {

        this.step = step;
    }

    public void move(float xValue, float yValue) {

        if (!checkEntityCollision(xValue, 0))
            position.add(xValue, 0);
        if (!checkEntityCollision(0, yValue))
            position.add(0, yValue);
    }
}
