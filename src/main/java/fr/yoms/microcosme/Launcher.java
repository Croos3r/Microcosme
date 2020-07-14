package fr.yoms.microcosme;

import fr.yoms.microcosme.graphics.Display;

public class Launcher {

    public static void main(String[] args) {

        Microcosme microcosme = new Microcosme(new Display("Microcosme", 1500, 1000), 60);

        microcosme.start();
    }
}
