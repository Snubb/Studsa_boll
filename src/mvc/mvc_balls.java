package mvc;

public class mvc_balls {
    private double ballPosX;
    private double ballPosY;
    private double ballSpeedX;
    private double ballSpeedY;
    private double gravity;
    private double radius;

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

    public mvc_balls(double ballPosX, double ballPosY, double ballSpeedX, double ballSpeedY, double gravity, double radius) {
        this.ballPosX = ballPosX;
        this.ballPosY = ballPosY;
        this.ballSpeedX = ballSpeedX;
        this.ballSpeedY = ballSpeedY;
        this.gravity = gravity;
        this.radius = radius;
    }

    public void update(double t) {
        this.ballPosX += ballSpeedX * t;
        this.ballPosY += ballSpeedY * t;
    }
}
