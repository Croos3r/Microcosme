package fr.yoms.microcosme.entities.tiles;

import fr.yoms.microcosme.Handler;
import fr.yoms.microcosme.hitboxes.rectangular.RectangularHitBox;
import fr.yoms.microcosme.utils.Position;

import java.awt.*;

public class Rock extends TileEntity {

    public Rock(int id, Handler handler, Position position) {
        super(id, handler, position, DEFAULT_WIDTH, DEFAULT_HEIGHT, new RectangularHitBox(position, DEFAULT_WIDTH, DEFAULT_HEIGHT));
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(handler.getGame().getResources().rock, (int) position.getX() - width / 2, (int) (position.getY() - height / 2), width, height, null);
    }

    @Override
    public void drawHitBox(Graphics graphics) {

        Position origin = ((RectangularHitBox) hitBox).getOrigin();
        double width = ((RectangularHitBox) hitBox).getWidth();
        double height = ((RectangularHitBox) hitBox).getHeight();

        graphics.drawRect((int) origin.getX(), (int) origin.getY(), (int) width, (int) height);
    }

    @Override
    public void drawSelector(Graphics graphics, Color color) {

        graphics.setColor(color);
        graphics.drawRect(
                (int) this.position.getX() - this.getWidth() / 2 - 3,
                (int) this.position.getY() - this.getHeight() / 2 - 3,
                this.width + 6,
                this.height + 6
        );
    }
}
