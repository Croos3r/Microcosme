package fr.yoms.microcosme.entities.livings.animals;

import fr.yoms.microcosme.Handler;
import fr.yoms.microcosme.entities.Entity;
import fr.yoms.microcosme.graphics.Ressources;
import fr.yoms.microcosme.utils.Position;

import java.awt.*;

public class Headnimal extends Animal {


    public Headnimal(int id, Handler handler, Position position) {

        super(id, handler, position, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_HEALTH, DEFAULT_AGE, DEFAULT_STEP);

        hitBox.x = (int) position.getX() + width / 2;
        hitBox.y = (int) position.getY() + height / 2;

        hitBox.width = width / 2;
        hitBox.height = height / 2;
    }

    @Override
    public void update() {

        Entity selectedEntity = handler.getGame().getEntityManager().getSelectedEntity();
        if (selectedEntity != null && selectedEntity.equals(this)) {

            if (handler.getKeyManager().up)
                move(0, -step);
            if (handler.getKeyManager().down)
                move(0, step);
            if (handler.getKeyManager().left)
                move(-step, 0);
            if (handler.getKeyManager().right)
                move(step, 0);
        }

        hitBox.x = (int) position.getX() + width / 6 - width / 2;
        hitBox.y = (int) position.getY() + height / 6 - height / 2;

        hitBox.width = width - width / 3;
        hitBox.height = height - height / 3;
    }

    @Override
    public void render(Graphics graphics) {

        graphics.drawImage(Ressources.HEAD, (int) position.getX() - width / 2, (int) position.getY() - height / 2, width, height, null);
    }
}
