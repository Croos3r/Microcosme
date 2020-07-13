package fr.yoms.microcosme.entities.livings;

import fr.yoms.microcosme.entities.Entity;
import fr.yoms.microcosme.utils.Position;

public abstract class LivingEntity extends Entity {

    public static final double DEFAULT_HEALTH = 20;
    public static final int DEFAULT_AGE = 0;

    protected double health = DEFAULT_HEALTH;
    protected int age = DEFAULT_AGE;

    public LivingEntity(int id) {
        super(id);
    }

    public LivingEntity(int id, Position position) {
        super(id, position);
    }

    public LivingEntity(int id, double health) {
        super(id);
        this.health = health;
    }

    public LivingEntity(int id, Position position, double health) {
        super(id, position);
        this.health = health;
    }

    public LivingEntity(int id, double health, int age) {
        super(id);
        this.health = health;
        this.age = age;
    }

    public LivingEntity(int id, Position position, double health, int age) {
        super(id, position);
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
