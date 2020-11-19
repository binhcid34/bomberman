package uet.oop.bomberman.graphics;

import java.util.Timer;
import java.util.TimerTask;

public class testTimer {
    public static void main(String[] args) {
        Timer myTimer = new Timer( );
        myTimer.schedule(new TimerTask( ) {
            @Override
            public void run() {
                System.out.println("Hello");
            }
        }, 100);
    }
}