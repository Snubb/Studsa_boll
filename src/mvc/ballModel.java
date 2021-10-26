package mvc;

import mvc.Shapes.Scene;
import mvc.Shapes.Shape;

import java.util.ArrayList;


public class ballModel {
    private int newBall = 0;
    private int numBalls;
    private int colorChange = 0;
    private boolean whiteBalls = true;
    private ArrayList<mvc_balls> balls = new ArrayList<>();
    public ballModel(){
        numBalls++;
        balls.add(new mvc_balls(100, 100, 15, 1, 5, 10, 0xFFFFFF));
    }

    public void update() {
        newBall++;
        colorChange++;
        if (newBall > 50) {
            createBall(Math.random()*700, Math.random()*100, 50-(Math.random()*100), -10, 5, 10);
            newBall = 0;
        }
        if (colorChange > 500) {
            whiteBalls = !whiteBalls;
            colorChange = 0;
            for (int i = 0; i < numBalls; i++) {
                if (whiteBalls) {
                    balls.get(i).setColor(0xFFFFFF);
                } else {
                    balls.get(i).setColor(0x000000);
                }
            }
        }
        for (int i = 0; i < numBalls; i++) {
            balls.get(i).update(0.3);
        }
    }

    public ArrayList<mvc_balls> getBalls() {
        return balls;
    }

    public ArrayList<Shape> getShapes() {
        ArrayList<Shape> shapes = new ArrayList<>();
        for (int i = 0; i < balls.toArray().length; i++) {
            shapes.add(balls.get(i).getShape());
        }
        return shapes;
    }

    public void createBall(double x, double y, double vx, double vy, double gravity, double radius) {
        //balls.add(new mvc_balls(x, y, vx, vy, gravity, radius, (int)(Math.random() * 0x1000000)));
        if (whiteBalls) {
            balls.add(new mvc_balls(x, y, vx, vy, gravity, radius, 0xFFFFFF));
        } else {
            balls.add(new mvc_balls(x, y, vx, vy, gravity, radius, 0x000000));
        }

        numBalls++;
    }

    public int getNumBalls() {
        return numBalls;
    }

    public boolean getWhiteBalls() {
        return whiteBalls;
    }
}
