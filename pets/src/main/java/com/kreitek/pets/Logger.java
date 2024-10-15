package com.kreitek.pets;

public class Logger {
    private static Logger instance;
    private static int contador = 0;

    private Logger() {
    }

    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void debug(String message) {
        contador++;
        System.out.println("[debug][" + contador + "] - " + message);
    }
}
