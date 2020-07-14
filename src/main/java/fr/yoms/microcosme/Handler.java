package fr.yoms.microcosme;

import fr.yoms.microcosme.graphics.Display;
import fr.yoms.microcosme.inputs.KeyManager;
import fr.yoms.microcosme.inputs.MouseManager;

public class Handler {

    private final Microcosme game;

    private final Display display;
    private final KeyManager keyManager;
    private final MouseManager mouseManager;
    private final int fps;

    public Handler(Microcosme game) {

        this.game = game;

        display = this.game.getDisplay();

        fps = this.game.getMaxTPS();

        mouseManager = this.game.getMouseManager();
        keyManager = this.game.getKeyManager();
    }

    public Microcosme getGame() {

        return game;
    }
    public Display getDisplay() {

        return display;
    }
    public KeyManager getKeyManager() {

        return keyManager;
    }
    public MouseManager getMouseManager() {

        return mouseManager;
    }
    public int getFps() {

        return fps;
    }
}
