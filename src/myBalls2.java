import java.awt.*;

public class myBalls2 {
    private double ballPosX;
    private double ballPosY;
    private double ballSpeedX;
    private double ballSpeedY;
    private double gravity = 1;
    private int dupe;
    private int width;
    private int height;

    public double getBallPosX() {
        return ballPosX;
    }

    public double getBallPosY() {
        return ballPosY;
    }

    public double getBallSpeedX() {
        return ballSpeedX;
    }

    public double getBallSpeedY() {
        return ballSpeedY;
    }

    public int getDupe() {
        return dupe;
    }

    private Color color;

    public myBalls2(double ballSpeedX, double ballSpeedY, double ballPosY, double ballPosX, int dupe, Color color, int width, int height) {
        this.ballPosX = ballPosX;
        this.ballPosY = ballPosY;
        this.ballSpeedX = ballSpeedX;
        this.ballSpeedY = ballSpeedY;
        this.dupe = dupe;
        this.color = color;
        this.width = width;
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    public void bounce(double newGrav) {
        if (this.ballPosY > 410) {
            this.gravity = -newGrav;

            this.ballSpeedY = this.ballSpeedY/1.1;
            this.ballSpeedX = this.ballSpeedX/1.005;
            if (this.ballSpeedX < 0.1 && this.ballSpeedX > -0.1) {
                this.ballSpeedX = 0;
            }
        } else {
            this.gravity = newGrav;
        }
        if (this.ballPosY > 600) {
            this.ballPosY = 600 + (this.ballPosY - 600)/2;
            this.ballSpeedY *= -1;
            this.ballSpeedY *= 0.8;
            this.dupe--;
            /*this.width += 10;
            this.height += 10;*/
            if (ballSpeedY > -0.6 && this.ballSpeedY < 0) {
                this.ballSpeedY = 0;
            }
            if (ballSpeedX <= 0 && ballSpeedX > -0.2) {
                this.ballSpeedX = 0;
            } else if (ballSpeedX >= 0 && ballSpeedX < 0.2) {
                this.ballSpeedX = 0;
            } else if (ballSpeedX > 0) {
                this.ballSpeedX -= 0.015;
            } else {
                this.ballSpeedX += 0.015;
            }
        } else if (this.ballPosY < this.height) {
            this.ballPosY = this.height;
            this.ballSpeedY *= -1;
        }
    }

    public boolean stopped() {
        return ballSpeedX == 0;
    }

    public void bounceX() {
        if (this.ballPosX <= this.width || this.ballPosX >= (1000)) {
            this.ballSpeedX = (this.ballSpeedX * -1);
            if (this.ballPosX < 0) {
                this.ballPosX = 0;
            } else if (this.ballPosX > (1000)) {
                this.ballPosX = 1000;
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void fall() {
        this.ballPosY += this.ballSpeedY;
        this.ballSpeedY += this.gravity/10;
        this.ballPosX += this.ballSpeedX;
    }

    public boolean dupee() {
        return dupe > 0;
    }

    public void setGravity(double grav) {
        this.gravity = grav;
    }
}
