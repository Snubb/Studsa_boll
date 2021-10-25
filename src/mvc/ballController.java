package mvc;

import javax.swing.*;
import java.util.ArrayList;

public class ballController implements Runnable {
    private Thread thread;
    private boolean running = false;
    private int fps = 60;
    private int ups = 60;
    private ballModel model;
    private ballView view;
    private int width = 800;
    private int height = 600;
    private int scale = 1;
    private JFrame frame;
    private String title = "";



    public ballController() {
        model = new ballModel();
        view = new ballView(width, height, scale);

        frame = new JFrame(title);
        //frame.setUndecorated(true);
        //frame.setOpacity(0.5f);
        frame.setResizable(false);
        frame.add(view);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.requestFocus();


    }

    public synchronized void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        double nsFPS = 1000000000.0 / fps;
        double nsUPS = 1000000000.0 / ups;
        double deltaFPS = 0;
        double deltaUPS = 0;
        int frames = 0;
        int updates = 0;
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();

        while (running) {
            long now = System.nanoTime();
            deltaFPS += (now - lastTime) / nsFPS;
            deltaUPS += (now - lastTime) / nsUPS;
            lastTime = now;

            while(deltaUPS >= 1) {
                model.update();
                view.draw(model.getShapes());
                updates++;
                deltaUPS--;
            }

            while (deltaFPS >= 1) {
                view.render(model.getBalls());
                frames++;
                deltaFPS--;
            }

            if(System.currentTimeMillis() - timer >= 1000) {
                timer += 1000;
                frame.setTitle(this.title + "  |  " + updates + " ups, " + frames + " fps " + model.getNumBalls() + " balls");
                frames = 0;
                updates = 0;
            }
        }
        stop();
    }

    public static void main(String[] args) {
        ballController c = new ballController();
        c.start();
    }

}
