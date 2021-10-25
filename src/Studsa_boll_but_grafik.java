import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Studsa_boll_but_grafik extends Canvas implements Runnable {

    private BufferedImage kirby;
    private final int width = 1300;
    private final int height = 600;

    double newGrav = 1;

    double deltaT;

    int spawn = 0;

    int numOfBalls = 0;

    private final Rectangle mouse = new Rectangle();

    private Thread thread;

    int fps = 1000;

    private boolean isRunning;

    private boolean random = false;

    public ArrayList<myBalls2> balls = new ArrayList<>();



    public Studsa_boll_but_grafik() {
        try {
            kirby = ImageIO.read(new File("kirbgun.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        deltaT = 1000.0/fps;
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
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.white);
        g.fillRect(0,0,width,height);

        g.setColor(new Color(144, 144, 144));
        g.fillRect(1000,0, 300, 600);


        g.setColor(new Color(144, 144, 144));
        g.fillRect(1100, 50, 100 ,100);

        g.setColor(Color.black);
        g.drawString("Number of balls: " + numOfBalls, 1100, 100);
        g.drawString("random spawn: " + random, 1100, 150);

        for (int i = 0; i < numOfBalls; i++) {
            g.setColor(balls.get(i).getColor());
            g.fillOval((int)Math.round(balls.get(i).getBallPosX() - balls.get(i).getWidth()), (int)Math.round(balls.get(i).getBallPosY() - balls.get(i).getHeight()), balls.get(i).getWidth(), balls.get(i).getHeight());
        }
        for (int i = 0; i < numOfBalls; i++) {
            g.drawImage(kirby, (int)Math.round(balls.get(i).getBallPosX()), (int)Math.round(balls.get(i).getBallPosY() - balls.get(i).getHeight()), balls.get(i).getWidth(), balls.get(i).getHeight(), null);
            //g.fillOval((int)Math.round(balls.get(i).getBallPosX() - balls.get(i).getWidth()), (int)Math.round(balls.get(i).getBallPosY() - balls.get(i).getHeight()), balls.get(i).getWidth(), balls.get(i).getHeight());
        }
        g.setColor(new Color(95, 190, 255,120));
        g.fillRect(0, 400, width, height);


        g.dispose();
        bs.show();
    }

    private void update() {
        if (random) {
            randomBallSpawn();
        }
        for (int i = 0; i < numOfBalls; i++) {
            //balls.get(i).setColor(new Color((int) (Math.random() * 255),  (int) (Math.random() * 255), (int) (Math.random() * 255)));
            balls.get(i).fall();
            //balls.get(i).bounce();
            balls.get(i).bounceX();
            balls.get(i).bounce(newGrav);
            if (balls.get(i).dupee()) {
                balls.add(new myBalls2(-balls.get(i).getBallSpeedX(), balls.get(i).getBallSpeedY(), 0, balls.get(i).getBallPosX(), balls.get(i).getDupe(), new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)), balls.get(i).getWidth(), balls.get(i).getHeight()));
                numOfBalls++;
            }
            if (balls.get(i).stopped()) {
                numOfBalls--;
                balls.remove(i);
            }

        }
    }

    private void randomBallSpawn() {
        spawn++;
        if (spawn >= 15 && numOfBalls < 30) {
            double rand = (Math.random());
            if (rand <= 0.5) {
                balls.add(new myBalls2(3, -5, (int)(Math.random()*600), (int)(Math.random()*1000), 0, new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)), 70, 70));
            } else {
                balls.add(new myBalls2(-3, -5, (int)(Math.random()*600), (int)(Math.random()*1000), 0, new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)), 70, 70));
            }
            numOfBalls++;
            spawn = 0;
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
            balls.add(new myBalls2(5, 0.1, mouse.y, mouse.x, 0, new Color(0,0,0), 15, 15));
            numOfBalls++;
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
                double rand = (Math.random());
                if (rand <= 0.5) {
                    balls.add(new myBalls2(5, 0.1, mouse.y, mouse.x, 0, new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)), 15, 15));
                } else {
                    balls.add(new myBalls2(-5, 0.1, mouse.y, mouse.x, 0, new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)), 15, 15));
                }
                numOfBalls++;

            }
            if (keyEvent.getKeyChar() == 'r') {
                random = !random;
            }
            if (keyEvent.getKeyChar() == 'f') {
                deltaT = 1000/Double.parseDouble(JOptionPane.showInputDialog("new fps"));
            }
            if (keyEvent.getKeyChar() == 'g') {
                newGrav = Double.parseDouble(JOptionPane.showInputDialog("new gravity"));
                for (int i = 0;i < numOfBalls; i++) {
                    balls.get(i).setGravity(newGrav);
                }
            }
            if (keyEvent.getKeyChar() == 'q') {
                numOfBalls = 0;
                balls.clear();
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
