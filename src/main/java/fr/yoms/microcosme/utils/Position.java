package fr.yoms.microcosme.utils;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class Position {

    /**
     * Calculate distance between two positions
     * @param pos1 First position
     * @param pos2 Second position
     * @return distance between these positions
     */
    public static double distanceBetween(Position pos1, Position pos2) {

        return Math.sqrt(Math.pow(pos2.x - pos1.x, 2) + Math.pow(pos2.y + pos1.y, 2));
    }

    /**
     * Generate random position
     * @param xBound x limit
     * @param yBound y limit
     * @return random Position between limits and 0
     */
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

    /**
     * Calculate distance to another position
     * @param position Other position
     * @return distance to passed position
     * @see #distanceBetween
     */
    public double distanceTo(Position position) {

        return distanceBetween(this, position);
    }

    // Getters and setters
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

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
