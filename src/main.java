public class main {
    public static void main(String[] args) {
        //Variabellåda
        double ballPosX = 0;
        double ballPosY = 50;
        double ballSpeedX = 5;
        double ballSpeedY = 0;
        double gravity = -10;
        double updates = 30; //= x times per second
        double timePassed = 0;
        int a = 0;

        for (int i = 0; i < 600; i++) {
            ballPosX += ballSpeedX/updates;
            ballSpeedY += gravity/updates;
            ballPosY += ballSpeedY/updates;
            if (ballPosY <= 0) {
                ballPosY = ballPosY * -1;
                ballSpeedY = ballSpeedY * -1;
            }


            a++;
            if (a == updates) {
                timePassed++;
                a = 0;
                //Någon cool metod som skriver ut tabell varje sekund.
                System.out.println("time passed:" + timePassed + " Y position: " + ballPosY);
            }
        }
        System.out.println("Done");
    }

}

