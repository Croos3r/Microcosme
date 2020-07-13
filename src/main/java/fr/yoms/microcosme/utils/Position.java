package fr.yoms.microcosme.utils;

import org.jetbrains.annotations.NotNull;

public class Position {

    private double x;
    private double y;

    public Position(int x, int y) {

        this.x = x;
        this.y = y;
    }

    public Position(Position position) {

        this.x = position.x;
        this.y = position.y;
    }

    public double getX() {
        return x;
    }
    public Position setX(int x) {
        this.x = x;
        return this;
    }

    public double getY() {
        return y;
    }
    public Position setY(int y) {
        this.y = y;
        return this;
    }

    public Position add(double x, double y) {

        this.x += x;
        this.y += y;

        return this;
    }
    public Position add(@NotNull Position position) {

        this.x += position.x;
        this.y += position.y;

        return this;
    }
    public Position substract(double x, double y) {

        this.x -= x;
        this.y -= y;

        return this;
    }
    public Position substract(@NotNull Position position) {

        this.x -= position.x;
        this.y -= position.y;

        return this;
    }


}
