package fr.yoms.microcosme;

import fr.yoms.microcosme.display.Display;
import fr.yoms.microcosme.inputs.KeyManager;
import fr.yoms.microcosme.inputs.MouseManager;

public class Handler {

    private final Microcosme game;

    private final Display display;
    private final KeyManager keyManager;
    private final MouseManager mouseManager;

    public Handler(Microcosme game) {

        this.game = game;
        display = this.game.getDisplay();
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
}
