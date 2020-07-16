package fr.yoms.microcosme.entities;

import fr.yoms.microcosme.Handler;
import fr.yoms.microcosme.entities.livings.animals.Headnimal;
import fr.yoms.microcosme.entities.tiles.Rock;
import fr.yoms.microcosme.inputs.MouseManager;
import fr.yoms.microcosme.utils.Position;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicBoolean;

@SuppressWarnings("DuplicatedCode")
public class EntityManager {

    private final Handler handler;
    private Entity selectedEntity = null;
    private final ArrayList<Entity> entities;
    private final Comparator<Entity> renderSorter = Comparator.comparingDouble(entity -> entity.getPosition().getY());

    public EntityManager(Handler handler) {

        this.handler = handler;
        entities = new ArrayList<>();
    }

    public void update() {

        entities.forEach(Entity::update);

        MouseManager mouseManager = handler.getMouseManager();
        Position mousePosition = mouseManager.getMousePosition();

        AtomicBoolean aFound = new AtomicBoolean(false);

        if (mouseManager.leftClickJustPressed()) {

            entities.forEach(entity -> {

                if (entity.getHitBox(0, 0).contains(mousePosition.getX(), mousePosition.getY())) {

                    if (selectedEntity == entity) selectedEntity = null;
                    else selectedEntity = entity;
                    aFound.set(true);
                }
            });
            if (!aFound.get()) selectedEntity = null;
        }
        if (mouseManager.rightClickJustPressed()) {

            if (selectedEntity != null && selectedEntity instanceof Headnimal) {

                Headnimal selectedAnimal = (Headnimal) selectedEntity;
                Position destination = new Position(mousePosition.getX(), mousePosition.getY());
                selectedAnimal.setDestination(destination);
            }
        }
    }

    public void render(Graphics graphics) {

        entities.sort(renderSorter);
        entities.forEach(entity -> entity.render(graphics));

        if (selectedEntity != null) drawEntitySelector(selectedEntity, graphics, Color.WHITE);
    }

    public void drawEntitySelector(Entity entity, Graphics graphics, Color color) {

        graphics.setColor(color);

        if (entity instanceof Headnimal)
            graphics.drawOval((int) entity.position.getX() - entity.getWidth() / 2 - 3, (int) entity.position.getY() - entity.getHeight() / 2 - 3, entity.width + 6, entity.height + 6);
        else if (entity instanceof Rock)
            graphics.drawRect((int) entity.position.getX() - entity.getWidth() / 2 - 3, (int) entity.position.getY() - entity.getHeight() / 2 - 3, entity.width + 6, entity.height + 6);
    }

    public Handler getHandler() {

        return handler;
    }

    public ArrayList<Entity> getEntities() {

        return entities;
    }
    public void addEntity(Entity entity) {

        entities.add(entity);
    }
    public void removeEntity(int id) {

        entities.removeIf(entity -> entity.getId() == id);
    }

    public Entity getSelectedEntity() {

        return selectedEntity;
    }
    public void selectEntity(Entity selectedEntity) {

        this.selectedEntity = selectedEntity;
    }
    public void unselectEntity() {

        selectEntity(null);
    }
}
