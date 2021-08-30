import javax.swing.*;
import java.awt.*;

public class Studsa_boll_but_grafik extends Canvas implements Runnable {

    private final int width = 600; //Dimensions for playing area
    private final int height = 600;

    private Thread thread;

    int fps = 60;

    private boolean isRunning;

    public Studsa_boll_but_grafik() {

        JFrame frame = new JFrame("Bouncy ball go wee");
        this.setSize(width,height);
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        isRunning = false;
    }

    public static void main(String[] args) {
        Studsa_boll_but_grafik painting = new Studsa_boll_but_grafik();
        painting.start();
    }

    public synchronized void start() {
        thread = new Thread(this);
        isRunning = true;
        thread.start();
    }

    public synchronized void stop() {
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        double deltaT = 1000.0/fps;
        long lastTime = System.currentTimeMillis();

        while (isRunning) {
            long now = System.currentTimeMillis();
            if (now-lastTime > deltaT) {
                update();
                draw();
                lastTime = now;
            }
        }
        stop();
    }

    private void draw() {

    }

    private void update() {

    }
}
