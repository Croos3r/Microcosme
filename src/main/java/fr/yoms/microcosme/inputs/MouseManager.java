package fr.yoms.microcosme.inputs;

import fr.yoms.microcosme.utils.Position;

import java.awt.event.*;

public class MouseManager implements MouseListener, MouseMotionListener{

    private boolean leftClick, rightClick;
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

    public boolean isLeftClickPressed() {
        return leftClick;
    }
    public boolean isRightClickPressed() {
        return rightClick;
    }
    public Position getMousePosition() {
        return mousePosition;
    }

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
