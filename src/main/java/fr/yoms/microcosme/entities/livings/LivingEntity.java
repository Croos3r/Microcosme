package fr.yoms.microcosme.entities.livings;

import fr.yoms.microcosme.Handler;
import fr.yoms.microcosme.entities.Entity;
import fr.yoms.microcosme.utils.Position;

public abstract class LivingEntity extends Entity {

    public static final double DEFAULT_HEALTH = 10, DEFAULT_MAX_HEALTH = 20;
    public static final int DEFAULT_AGE = 0, DEFAULT_MAX_AGE = 10;

    protected double health;
    protected double maxHealth;
    protected int age;
    protected int maxAge;

    public LivingEntity(int id, Handler handler, Position position, int width, int height, double health, int age) {

        super(id, handler, position, width, height);

        this.health = health;
        this.age = age;
    }

    public double getHealth() {

        return health;
    }
    public void setHealth(double health) {

        this.health = health;
    }

    public double getMaxHealth() {

        return maxHealth;
    }
    public void setMaxHealth(double maxHealth) {

        this.maxHealth = maxHealth;
    }

    public int getAge() {

        return age;
    }
    public void setAge(int age) {

        this.age = age;
    }

    public int getMaxAge() {

        return maxAge;
    }
    public void setMaxAge(int maxAge) {

        this.maxAge = maxAge;
    }
}
