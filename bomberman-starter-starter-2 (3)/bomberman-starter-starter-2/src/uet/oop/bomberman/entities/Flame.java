package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

import java.util.Timer;
import java.util.TimerTask;

public class Flame extends Entity{
    @Override
    public void update() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask( ) {
            @Override
            public void run() {
                img = null;
            }
        },100);
    }
    public Flame(int x,int y, Image img){
        super(x,y,img);
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }
    public void flamesegement() {

    }

}
