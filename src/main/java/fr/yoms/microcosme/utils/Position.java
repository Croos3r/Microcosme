package fr.yoms.microcosme.utils;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class Position {

    public static double distanceBetween(Position pos1, Position pos2) {

        return Math.sqrt(Math.pow(pos2.x - pos1.x, 2) + Math.pow(pos2.y + pos1.y, 2));
    }

    public static Position randomPosition(int xBound, int yBound) {

        return new Position(new Random().nextInt(xBound), new Random().nextInt(yBound));
    }

    private double x;
    private double y;

    public Position(double x, double y) {

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
    public Position setX(double x) {
        this.x = x;
        return this;
    }

    public double getY() {
        return y;
    }
    public Position setY(double y) {
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

    public double distanceTo(Position position) {

        return distanceBetween(this, position);
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
