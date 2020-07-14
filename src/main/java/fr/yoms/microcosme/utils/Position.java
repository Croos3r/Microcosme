package fr.yoms.microcosme.utils;

import org.jetbrains.annotations.NotNull;

public class Position {

    public static double distanceBetween(Position pos1, Position pos2) {

        return Math.sqrt(Math.pow(pos2.x - pos1.x, 2) + Math.pow(pos2.y + pos1.y, 2));
    }

    private float x;
    private float y;

    public Position(float x, float y) {

        this.x = x;
        this.y = y;
    }

    public Position(Position position) {

        this.x = position.x;
        this.y = position.y;
    }

    public float getX() {
        return x;
    }
    public Position setX(float x) {
        this.x = x;
        return this;
    }

    public float getY() {
        return y;
    }
    public Position setY(float y) {
        this.y = y;
        return this;
    }

    public Position add(float x, float y) {

        this.x += x;
        this.y += y;

        return this;
    }
    public Position add(@NotNull Position position) {

        this.x += position.x;
        this.y += position.y;

        return this;
    }
    public Position substract(float x, float y) {

        this.x -= x;
        this.y -= y;

        return this;
    }
    public Position substract(@NotNull Position position) {

        this.x -= position.x;
        this.y -= position.y;

        return this;
    }

    public double distanceTo(Position position) {

        return distanceBetween(this, position);
    }
}
