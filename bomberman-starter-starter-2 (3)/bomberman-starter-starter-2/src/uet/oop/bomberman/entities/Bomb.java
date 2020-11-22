package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Timer;
import java.util.TimerTask;

public class Bomb extends Entity{
    public static boolean isBomb = false;
    public static boolean _exploded = false;
    @Override
    public void update() {
    }
    public Bomb (int x, int y, Image img ) {
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
    public void chance() {
        Timer myTimer = new Timer( );
        myTimer.schedule(new TimerTask( ) {
            @Override
            public void run() {
                img = Sprite.bomb.getFxImage( );
                Timer myTimer1 = new Timer( );
                myTimer1.schedule(new TimerTask( ) {
                    @Override
                    public void run() {
                        img = Sprite.bomb_1.getFxImage( );
                        Timer myTimer1 = new Timer( );
                        myTimer1.schedule(new TimerTask( ) {
                            public void run() {
                                img = Sprite.bomb_2.getFxImage( );
                            }
                        }, 100);
                    }
                }, 100);
            }
        }, 100, 300);

    }

    @Override
    public void render(GraphicsContext gc) {
        super.render(gc);
    }
    public void exploded() {
        _exploded = true;
    }
}
