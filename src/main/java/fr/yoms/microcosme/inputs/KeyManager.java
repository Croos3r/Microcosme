package fr.yoms.microcosme.inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.event.KeyEvent.*;

public class KeyManager implements KeyListener {

    private final boolean[] keys = new boolean[256];

    private final boolean[] justPressedKeys = new boolean[keys.length];
    private final boolean[] cantPressKeys = new boolean[keys.length];

    public boolean debug;

    public boolean up;
    public boolean down;
    public boolean left;
    public boolean right;

    public boolean exit;

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() >= 0 && e.getKeyCode() < keys.length) keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() >= 0 && e.getKeyCode() < keys.length) keys[e.getKeyCode()] = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    public void update() {


        for (int i = 0; i < keys.length; i++) {
            if (cantPressKeys[i] && !keys[i])
                cantPressKeys[i] = false;
            else if (justPressedKeys[i]) {

                cantPressKeys[i] = true;
                justPressedKeys[i] = false;
            }
            if (!cantPressKeys[i] && keys[i])
                justPressedKeys[i] = true;
        }

        if (keyJustPressed(VK_F3)) debug = !debug;

        up      = keys[VK_Z];
        down    = keys[VK_S];
        left    = keys[VK_Q];
        right   = keys[VK_D];
        exit    = keys[VK_ESCAPE] || keys[VK_END];
    }

    public boolean keyJustPressed(int keyCode){
        if(keyCode >= 0 && keyCode < keys.length)
            return justPressedKeys[keyCode];
        else return false;
    }
    public boolean keyPressed(int keyCode) {

        if(keyCode >= 0 && keyCode < keys.length)
        return keys[keyCode];
        else return false;
    }
}
