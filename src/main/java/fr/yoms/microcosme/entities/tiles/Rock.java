package fr.yoms.microcosme.entities.tiles;

import fr.yoms.microcosme.Handler;
import fr.yoms.microcosme.utils.Position;

import java.awt.*;

public class Rock extends TileEntity {

    public Rock(int id, Handler handler, Position position) {
        super(id, handler, position, DEFAULT_WIDTH, DEFAULT_HEIGHT);

        hitBox.x = (int) position.getX() - width / 2;
        hitBox.y = (int) position.getY() - height / 2;

        hitBox.width = width;
        hitBox.height = height;
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(handler.getGame().getResources().rock, (int) position.getX() - width / 2, (int) (position.getY() - height / 2), width, height, null);
    }
}
