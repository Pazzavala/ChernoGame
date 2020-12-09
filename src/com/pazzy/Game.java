package com.pazzy;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Game extends Canvas implements Runnable{
    private static final long serialVersionUID = 1L;
        // Screen resolution
    public static int width = 300;
    public static int height = width / 16  * 9;
    public static int scale = 3;    // For better performance

        // Subprocess
    private Thread thread;
    private JFrame frame;
    private boolean running = false;
        // Creating an image
    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // Accessing that image
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

        // Constructor First thing that will happen
    public Game() {
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);

        frame = new JFrame();
    }

        // start method - synchronized makes sure theres no
        // thread interferences and memory consistency errors.
    public synchronized void start() {
        running = true;
        // Creating new thread object, "this makes sure it is including the "Game" class
        thread = new Thread(this, "Display");
        // Starting that thread
        thread.start();
    }

        // stop method - Stopping the thread
    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

        // run method - Game loop
    public void run() {
        while (running) {
            update();
            render();
        }
    }

    public void update() {

    }
        // buffer strategy
    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if(bs  == null) {   // always have it at 3
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0,0, getWidth(), getHeight());
        g.dispose();    // remove graphics after every loop
        bs.show();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.frame.setResizable(false);
        game.frame.setTitle("Rain");
        game.frame.add(game);
        game.frame.pack();
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);

        game.start();
    }

}
