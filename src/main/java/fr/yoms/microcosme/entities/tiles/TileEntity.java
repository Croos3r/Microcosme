package fr.yoms.microcosme.entities.tiles;

import fr.yoms.microcosme.Handler;
import fr.yoms.microcosme.entities.Entity;
import fr.yoms.microcosme.utils.Position;

public abstract class TileEntity extends Entity {

    public TileEntity(int id, Handler handler, Position position, int width, int height) {

        super(id, handler, position, width, height);
    }

    @Override
    public void update() {}
}