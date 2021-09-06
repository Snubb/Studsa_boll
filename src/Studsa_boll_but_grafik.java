import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class Studsa_boll_but_grafik extends Canvas implements Runnable {

    private final int width = 600;
    private final int height = 600;

    int numOfBalls = 0;

    private final Rectangle mouse = new Rectangle();

    private Thread thread;

    int fps = 60;

    private boolean isRunning;

    private BufferStrategy bs;

    public ArrayList<myBalls2> balls = new ArrayList<>();



    public Studsa_boll_but_grafik() {

        JFrame frame = new JFrame("Bouncy ball go wee");
        this.setSize(width,height);
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(new KL());
        frame.setVisible(true);

        this.addMouseMotionListener(new Studsa_boll_but_grafik.MML());
        this.addMouseListener(new Studsa_boll_but_grafik.ML());

        isRunning = false;

        mouse.width = 5;
        mouse.height = 5;
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

        for (int i = 0; i < numOfBalls; i++) {
                g.fillOval((int)Math.round(balls.get(i).ballPosX - 5), (int)Math.round(balls.get(i).ballPosY - 5), 5, 5);
        }

        g.dispose();
        bs.show();
    }

    private void update() {
        for (int i = 0; i < numOfBalls; i++) {
            balls.get(i).fall();
            //balls.get(i).bounce();
            balls.get(i).bounceX();
            if (balls.get(i).bounceCheck()) {
                balls.get(i).bounce();
                numOfBalls++;
                balls.add(new myBalls2(-balls.get(i).ballSpeedX, balls.get(i).ballSpeedY, balls.get(i).ballPosY, balls.get(i).ballPosX));
                if (balls.get(i).stopped()) {
                    balls.remove(i);
                    numOfBalls--;
                }
            }
        }
    }

    private class MML implements MouseMotionListener {
        @Override
        public void mouseDragged(MouseEvent mouseEvent) { }
        @Override
        public void mouseMoved(MouseEvent mouseEvent) {
            mouse.x = mouseEvent.getX();
            mouse.y = mouseEvent.getY();
        }
    }

    private class ML implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent mouseEvent) { }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {
            numOfBalls++;
            balls.add(new myBalls2(5, 0.1, mouse.y, mouse.x));
        }
        @Override
        public void mouseReleased(MouseEvent mouseEvent) { }
        @Override
        public void mouseEntered(MouseEvent mouseEvent) { }
        @Override
        public void mouseExited(MouseEvent mouseEvent) { }
    }
    private class KL implements KeyListener {
        @Override
        public void keyTyped(KeyEvent keyEvent) {
            if (keyEvent.getKeyChar() == ' ') {
                numOfBalls++;
                double rand = (Math.random());
                if (rand <= 0.5) {
                    balls.add(new myBalls2(5, 0.1, mouse.y, mouse.x));
                } else {
                    balls.add(new myBalls2(-5, 0.1, mouse.y, mouse.x));
                }
                System.out.println(rand);

            }
        }

        @Override
        public void keyPressed(KeyEvent keyEvent) {
        }

        @Override
        public void keyReleased(KeyEvent keyEvent) {
        }
    }
}
