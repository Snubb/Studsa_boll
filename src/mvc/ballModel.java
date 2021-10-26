package mvc;

import mvc.Shapes.Scene;
import mvc.Shapes.Shape;

import java.util.ArrayList;


public class ballModel {
    private int newBall = 0;
    private int numBalls;
    private ArrayList<mvc_balls> balls = new ArrayList<>();
    public ballModel(){
        numBalls++;
        balls.add(new mvc_balls(100, 100, 15, 1, 5, 10, 0xFFFFFF));
    }

    public void update() {
        newBall++;
        if (newBall > 50) {
            createBall(Math.random()*700, Math.random()*100, 20-(Math.random()*40), -10, 5, 10);
            newBall = 0;
        }
        for (int i = 0; i < numBalls; i++) {
            balls.get(i).update(0.3);
            //System.out.println("Updated");
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
        balls.add(new mvc_balls(x, y, vx, vy, gravity, radius, (int)(Math.random() * 0x1000000)));
        numBalls++;
    }

    public int getNumBalls() {
        return numBalls;
    }
}
