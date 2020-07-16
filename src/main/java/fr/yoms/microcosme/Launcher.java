package fr.yoms.microcosme;

import fr.yoms.microcosme.graphics.Display;

public class Launcher {

    public static void main(String[] args) {

        if (args.length >= 2 && args.length < 4) {

            try {
                int width = Integer.parseInt(args[0]);
                int height = Integer.parseInt(args[1]);
                int maxTPS = args.length == 3 ? Integer.parseInt(args[2]) : 60;

                Microcosme microcosme = new Microcosme(new Display("Microcosme", width, height), maxTPS);

                microcosme.start();
            } catch (NumberFormatException ignored) {
                System.err.println("Microcosme <width> <height> [maxTPS]");
            }
        }
        else System.err.println("Microcosme <width> <height> [maxTPS]");
    }
}
