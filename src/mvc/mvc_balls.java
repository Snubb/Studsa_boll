package mvc;

import mvc.Shapes.Circle;
import mvc.Shapes.Point;
import mvc.Shapes.Shape;

public class mvc_balls {
    private double ballPosX;
    private double ballPosY;
    private double ballSpeedX;
    private double ballSpeedY;
    private double gravity;
    private double radius;
    private int color;
    private Circle circle;

    public double getBallPosX() {
        return ballPosX;
    }

    public void setBallPosX(double ballPosX) {
        this.ballPosX = ballPosX;
    }

    public double getBallPosY() {
        return ballPosY;
    }

    public void setBallPosY(double ballPosY) {
        this.ballPosY = ballPosY;
    }

    public double getBallSpeedX() {
        return ballSpeedX;
    }

    public void setBallSpeedX(double ballSpeedX) {
        this.ballSpeedX = ballSpeedX;
    }

    public double getBallSpeedY() {
        return ballSpeedY;
    }

    public void setBallSpeedY(double ballSpeedY) {
        this.ballSpeedY = ballSpeedY;
    }

    public double getGravity() {
        return gravity;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    public int getColor() {
        return color;
    }

    public mvc_balls(double ballPosX, double ballPosY, double ballSpeedX, double ballSpeedY, double gravity, double radius, int color) {
        this.ballPosX = ballPosX;
        this.ballPosY = ballPosY;
        this.ballSpeedX = ballSpeedX;
        this.ballSpeedY = ballSpeedY;
        this.gravity = gravity;
        this.radius = radius;
        this.color = color;
        circle = new Circle(new Point((int)ballPosX, (int)ballPosY), (int)radius, color);
    }

    public void update(double t) {
        this.ballPosX += ballSpeedX * t;
        this.ballPosY += ballSpeedY * t;
        this.ballSpeedY += this.gravity * t;

        if (this.ballPosY > 590) {
            this.ballPosY = 590 - (this.ballPosY - 590);
            this.ballSpeedY *= -0.80;
        }
        if (this.ballPosY < 10) {
            this.ballPosY = 10;
            this.ballSpeedY *= -0.7;
        }
        if (this.ballPosX > 790 || this.ballPosX < 10) {
            if (this.ballPosX < 10) {
                this.ballPosX = 10;
            } else {
                this.ballPosX = 790;
            }
            this.ballSpeedX *= -1;
        }

        circle = new Circle(new Point((int)ballPosX, (int)ballPosY), (int)radius, color);

    }
    public Shape getShape() {
        return circle;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
