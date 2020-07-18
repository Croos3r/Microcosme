package fr.yoms.microcosme.hitboxes.circular;

import fr.yoms.microcosme.hitboxes.HitBox;
import fr.yoms.microcosme.hitboxes.rectangular.RectangularHitBox;
import fr.yoms.microcosme.utils.Circle;
import fr.yoms.microcosme.utils.Position;

import java.awt.*;
import java.awt.geom.Line2D;

public class CircularHitBox extends HitBox {

    private final double radius;

    public CircularHitBox(Position center, double radius) {

        super(center);
        this.radius = radius;
    }

    @Override
    public boolean intersects(Object o) {

        if (o instanceof HitBox) {

            if (o instanceof RectangularHitBox) {

                RectangularHitBox hitBox = (RectangularHitBox) o;
                Position origin = hitBox.getOrigin();
                return new Circle(center, radius).intersectsRect(origin.getX(), origin.getY(), hitBox.getWidth(), hitBox.getHeight());
            }
            else if (o instanceof CircularHitBox) {

                CircularHitBox hitBox = (CircularHitBox) o;
                return new Circle(center, radius).intersectsCircle(new Circle(hitBox.center, hitBox.radius));
            }
            else return false;
        }
        else if (o instanceof Line2D) return new Circle(center, radius).intersectsLine((Line2D) o);
        else if (o instanceof Rectangle) return new Circle(center, radius).intersectsRect((Rectangle) o);
        else return false;
    }

    @Override
    public boolean contains(Position position) {

        return new Circle(center, (int) radius).isPositionInCircle(position);
    }

    @Override
    public double getPerimeter() {

        return 2 * Math.PI * radius;
    }
    public double getArea() {

        return Math.pow(radius, 2) * Math.PI;
    }
    public double getRadius() {

        return radius;
    }

    @Override
    public String toString() {
        return "CircularHitBox{" +
                "radius=" + radius +
                ", center=" + center +
                '}';
    }
}
