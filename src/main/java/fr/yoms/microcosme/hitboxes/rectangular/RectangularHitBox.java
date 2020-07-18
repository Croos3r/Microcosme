package fr.yoms.microcosme.hitboxes.rectangular;

import fr.yoms.microcosme.hitboxes.HitBox;
import fr.yoms.microcosme.hitboxes.circular.CircularHitBox;
import fr.yoms.microcosme.utils.Circle;
import fr.yoms.microcosme.utils.Position;

import java.awt.*;
import java.awt.geom.Line2D;

public class RectangularHitBox extends HitBox {

    protected double width;
    protected double height;

    protected final Position origin;
    protected final Position end;

    public RectangularHitBox(Position center, double width, double height) {

        super(center);

        this.width = width;
        this.height = height;

        origin = new Position(center.getX() - width / 2, center.getY() - height / 2);
        end = new Position(center.getX() + width / 2, center.getY() + height / 2);
    }

    @Override
    public boolean intersects(Object o) {

        if (o instanceof HitBox) {

            if (o instanceof RectangularHitBox) {

                RectangularHitBox hitBox = (RectangularHitBox) o;
                return new Rectangle((int) this.origin.getX(), (int) this.origin.getY(), (int) this.width, (int) this.height).intersects(hitBox.origin.getX(), hitBox.origin.getY(), hitBox.width, hitBox.height);
            }
            else if (o instanceof CircularHitBox) {

                CircularHitBox hitBox = (CircularHitBox) o;
                return new Circle(hitBox.getCenter(), hitBox.getRadius()).intersectsRect(this.origin.getX(), this.origin.getY(), this.width, this.height);
            }
            else return false;
        }
        else if (o instanceof Line2D)
            return ((Line2D) o).intersects(origin.getX(), origin.getY(), width, height);
        else if (o instanceof Rectangle)
            return ((Rectangle) o).intersects(origin.getX(), origin.getY(), width, height);
        else if (o instanceof Circle)
            return ((Circle) o).intersectsRect(new Rectangle((int) origin.getX(), (int) origin.getY(), (int) width, (int) height));
        else return false;
    }

    @Override
    public boolean contains(Position position) {
        return
                origin.getX() <= position.getX() && position.getX() <= end.getX()
                        &&
                origin.getY() <= position.getY() && position.getY() <= end.getY();
    }

    @Override
    public double getPerimeter() {

        return width * 2 + height * 2;
    }
    public double getArea() {

        return width * height;
    }

    public double getWidth() {

        return width;
    }
    public void setWidth(double width) {

        this.width = width;
    }

    public double getHeight() {

        return height;
    }
    public void setHeight(double height) {

        this.height = height;
    }

    public Position getOrigin() {

        return origin;
    }
    public Position getEnd() {

        return end;
    }

    @Override
    public String toString() {
        return "RectangularHitBox{" +
                "width=" + width +
                ", height=" + height +
                ", origin=" + origin +
                ", end=" + end +
                ", center=" + center +
                '}';
    }
}
