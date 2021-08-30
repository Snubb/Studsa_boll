import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Studsa_boll_but_grafik extends Canvas implements Runnable {

    private final int width = 600;
    private final int height = 600;

    private Thread thread;

    int fps = 60;

    private boolean isRunning;

    private BufferStrategy bs;

    double ballPosX = 10;
    double ballPosY = 50;
    double ballSpeedX = 5;
    double ballSpeedY = 0;
    private static final double gravity = 1;

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
        bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.white);
        g.fillRect(0,0,width,height);

        g.setColor(Color.black);

        g.fillOval((int)Math.round(ballPosX) - 5, (int)Math.round(ballPosY) - 5, 5, 5);

        g.dispose();
        bs.show();
    }

    private void update() {
        ballPosX += ballSpeedX;
        ballSpeedY += gravity/10;
        ballPosY += ballSpeedY;
        if (ballPosY >= 600) {
            ballPosY = ballPosY - (ballPosY - 600) * 2;
            ballSpeedY = (ballSpeedY * -1)*0.9;
            if (ballSpeedX > 0.05) {
                ballSpeedX -= 0.05;
            } else if (ballSpeedX < -0.05) {
                ballSpeedX += 0.05;
            } else {
                ballSpeedX = 0;
                ballSpeedY = 0;
            }
        }
        if (ballPosX >= 600) {
            ballSpeedX = (ballSpeedX * -1)*0.9;
        }
        if (ballPosX <= 5) {
            ballSpeedX = (ballSpeedX * -1)*0.9;
        }

    }
}
