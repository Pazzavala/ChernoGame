package com.pazzy;

public class Game implements Runnable{
    // Screen resolution
    public static int width = 300;
    public static int height = width / 16  * 9;
    public static int scale = 3;    // For better performance

    // Subprocess
    private Thread thread;
    private boolean running = false;

    // synchronized makes sure theres no thread interferences and memory consistency errors.
    public synchronized void start() {
        running = true;
        // Creating new thread object, "this makes sure it is including the "Game" class
        thread = new Thread(this, "Display");
        // Starting that thread
        thread.start();
    }

    // Stopping the thread
    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Game loop
    public void run() {
        while (running) {

        }
    }

}
