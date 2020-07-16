package fr.yoms.microcosme.utils;

import java.awt.*;

public class Circle {

    private Position center;
    private int radius;

    public Circle(Position center, int radius) {

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
    
    public boolean intersect(Rectangle rectangle) {

        double rectangleHalfWidth = (double) rectangle.width / 2;
        double rectangleHalfHeight = (double) rectangle.width / 2;
        
        double cx = Math.abs(this.center.getX() - rectangle.x - rectangleHalfWidth);
        double xDist = rectangleHalfWidth + this.radius;
        
        if (cx > xDist)
            return false;
        
        double cy = Math.abs(this.center.getY() - rectangle.y - rectangleHalfHeight);
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
    public int getRadius() {

        return radius;
    }
    public void setRadius(int radius) {

        this.radius = radius;
    }

    public Position getCenter() {

        return center;
    }
    public void setCenter(Position center) {

        this.center = center;
    }
}