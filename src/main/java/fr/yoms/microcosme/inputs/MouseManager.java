package fr.yoms.microcosme.inputs;

import fr.yoms.microcosme.utils.Position;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener{

    private boolean leftClick, justPressedLeftClick, cantPressLeftClick, 
                    rightClick, justPressedRightClick, cantPressRightClick;
    private final Position mousePosition = new Position(0, 0);

    @Override
    public void mousePressed(MouseEvent e) {

        if (e.getButton() == MouseEvent.BUTTON1) leftClick = true;
        if (e.getButton() == MouseEvent.BUTTON3) rightClick = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        if (e.getButton() == MouseEvent.BUTTON1) leftClick = false;
        if (e.getButton() == MouseEvent.BUTTON3) rightClick = false;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mousePosition
                .setX(e.getX())
                .setY(e.getY());
    }

    public void update() {

        if (cantPressLeftClick && !leftClick)
            cantPressLeftClick = false;

        else if (justPressedLeftClick) {

            cantPressLeftClick = true;
            justPressedLeftClick = false;
        }
        if (!cantPressLeftClick && leftClick)
            justPressedLeftClick = true;
        
        if (cantPressRightClick && !rightClick)
            cantPressRightClick = false;

        else if (justPressedRightClick) {

            cantPressRightClick = true;
            justPressedRightClick = false;
        }
        if (!cantPressRightClick && rightClick)
            justPressedRightClick = true;
    }

    public boolean leftClickJustPressed() {

        return justPressedLeftClick;
    }
    public boolean rightClickJustPressed() {

        return justPressedRightClick;
    }
    public boolean leftClickPressed() {
        return leftClick;
    }
    public boolean rightClickPressed() {
        return rightClick;
    }
    public Position getMousePosition() {
        return mousePosition;
    }

    // Ignored events
    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
