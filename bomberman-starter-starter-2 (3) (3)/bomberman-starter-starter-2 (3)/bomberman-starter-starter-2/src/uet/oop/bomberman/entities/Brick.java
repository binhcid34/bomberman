package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import javax.xml.parsers.SAXParser;
import java.util.Timer;
import java.util.TimerTask;

public class Brick extends Entity{
    @Override
    public void update() {

    }
    public Brick(int x,int y, Image img){
        super(x,y,img);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public boolean collide(Entity e) {
        return false;
    }

    public void exploreBrick(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                img = Sprite.brick_exploded.getFxImage();
                Timer timer1 = new Timer();
                timer1.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        img = Sprite.brick_exploded1.getFxImage();
                        Timer timer2 = new Timer();
                        timer2.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                img = Sprite.brick_exploded2.getFxImage();
                                Timer timer3 = new Timer();
                                timer3.schedule(new TimerTask() {
                                    @Override
                                    public void run() {
                                        img = Sprite.grass.getFxImage();
                                    }
                                },300);
                            }
                        },300);
                    }

                },300);
            }

        },300);
    }
}
