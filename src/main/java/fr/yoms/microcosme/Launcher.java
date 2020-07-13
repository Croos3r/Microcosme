package fr.yoms.microcosme;

import fr.yoms.microcosme.display.Display;

public class Launcher {

    public static void main(String[] args) {

        Microcosme microcosme = new Microcosme(new Display("Microcosme", 1080, 1920), 60);

        microcosme.start();
    }
}
