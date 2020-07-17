package fr.yoms.microcosme.utils;

import java.awt.*;
import java.awt.geom.Line2D;

public class Circle {

    private Position center;
    private double radius;

    public Circle(Position center, double radius) {

        this.center = center;
        this.radius = radius;
    }

    public double getArea() {

        return Math.PI * radius * radius;
    }

    public double getPerimeter() {

        return 2 * Math.PI * radius;
    }

    public boolean isPositionInCircle(Position position) {

        return Math.pow((position.getX() - center.getX()), 2) + Math.pow((position.getY() - center.getY()), 2) <= radius * radius;
    }

    public boolean intersectsRect(double x, double y, double width, double height) {

        double rectangleHalfWidth = width / 2;
        double rectangleHalfHeight = height / 2;

        double cx = Math.abs(this.center.getX() - x - rectangleHalfWidth);
        double xDist = rectangleHalfWidth + this.radius;

        if (cx > xDist)
            return false;

        double cy = Math.abs(this.center.getY() - y - rectangleHalfHeight);
        double yDist = rectangleHalfHeight + this.radius;
        if (cy > yDist)
            return false;
        if (cx <= rectangleHalfWidth || cy <= rectangleHalfHeight)
            return true;
        double xCornerDist = cx - rectangleHalfWidth;
        double yCornerDist = cy - rectangleHalfHeight;
        double xCornerDistSq = xCornerDist * xCornerDist;
        double yCornerDistSq = yCornerDist * yCornerDist;
        double maxCornerDistSq = this.radius * this.radius;
        return xCornerDistSq + yCornerDistSq <= maxCornerDistSq;
    }
    public boolean intersectsRect(Position origin, Dimension size) {

        return intersectsRect(origin.getX(), origin.getY(), size.getWidth(), size.getHeight());
    }
    public boolean intersectsRect(Rectangle rectangle) {

        return intersectsRect(rectangle.x, rectangle.y, rectangle.getWidth(), rectangle.getHeight());
    }
    public boolean intersectsLine(double aX, double aY, double bX, double bY) {

        double Dr = Math.hypot(bX - aX, bY - aY);
        double D = aX * bY - bX * aY;

        double delta = (radius * radius) * (Dr * Dr) - (D * D);

        return delta >= 0;
    }
    public boolean intersectsLine(Position origin, Position end) {

        return intersectsLine(origin.getX(), origin.getY(), end.getX(), end.getY());
    }
    public boolean intersectsLine(Line2D line) {

        return intersectsLine(line.getX1(), line.getY1(), line.getX2(), line.getY2());
    }
    public boolean intersectsCircle(double cX, double cY, double radius) {

        return Math.hypot(center.getX() - cX, center.getY() - cY) <= (this.radius + radius);
    }
    public boolean intersectsCircle(Position center, double radius) {

        return intersectsCircle(center.getX(), center.getY(), radius);
    }
    public boolean intersectsCircle(Circle circle) {

        return intersectsCircle(circle.center.getX(), circle.center.getY(), circle.radius);
    }

    public double getRadius() {

        return radius;
    }
    public void setRadius(double radius) {

        this.radius = radius;
    }

    public Position getCenter() {

        return center;
    }
    public void setCenter(Position center) {

        this.center = center;
    }
}