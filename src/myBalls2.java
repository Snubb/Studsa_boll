import java.awt.*;

public class myBalls2 {
    public double ballPosX;
    public double ballPosY;
    public double ballSpeedX = 4;
    public double ballSpeedY = 0;
    public double gravity = 1;

    public myBalls2(double ballSpeedX, double ballSpeedY, double ballPosY, double ballPosX) {
        this.ballPosX = ballPosX;
        this.ballPosY = ballPosY;
        this.ballSpeedX = ballSpeedX;
        this.ballSpeedY = ballSpeedY;
    }

    public void bounce() {
        if (this.ballPosY >= 600) {
            this.ballPosY = 599;
            this.ballSpeedY *= -1;
            this.ballSpeedY *= 0.8;
            this.ballSpeedX -= 0.05;
        }
    }

    public void bounceX() {
        if (this.ballPosX <= 0 || this.ballPosX >= 600) {
            this.ballSpeedX = (this.ballSpeedX * -1);
        }
    }

    public void fall() {
        this.ballPosY += this.ballSpeedY;
        this.ballSpeedY += this.gravity/10;
        this.ballPosX += this.ballSpeedX;
    }

    public double getPosX() {
        return this.ballPosX;
    }
    public double getPosY() {
        return this.ballPosY;
    }

    public double setPosX(int x) {
        this.ballPosX = x;
        return ballPosX;
    }
    public double setPosY(int y) {
        this.ballPosY = y;
        return ballPosY;
    }
}
