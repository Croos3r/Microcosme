package fr.yoms.microcosme.entities.livings;

import fr.yoms.microcosme.entities.Entity;
import fr.yoms.microcosme.utils.Position;

public abstract class LivingEntity extends Entity {

    protected double health;
    protected int age;

    public LivingEntity(int id, Position position, int width, int height, double health, int age) {

        super(id, position, width, height);

        this.health = health;
        this.age = age;
    }

    public double getHealth() {
        return health;
    }
    public void setHealth(double health) {
        this.health = health;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
