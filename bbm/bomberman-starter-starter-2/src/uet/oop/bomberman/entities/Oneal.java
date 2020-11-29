package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
//import uet.oop.bomberman.entities.enemy.ai.AI;
//import uet.oop.bomberman.entities.enemy.ai.AILow;
import uet.oop.bomberman.graphics.Map1;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Oneal  extends Enemy{
    public static int step = 1;
    public boolean _isdead = false;
    public static int direct = 3;

    public Oneal(int x, int y, Image img) {
        super(x,y,img);
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void update() {
        if(_isdead == false) {
            switch (direct) {
                case 0:
                    img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3,
                            x, 60).getFxImage();
                    if (Map1.level1Map[(y + 26) / 32][(x + 28) / 32] == '.' && Map1.level1Map[(y + 2) / 32][(x + 28) / 32] == '.') {
                        int temp2 = x;
                        x += step;
                        int temp = y % 32;
                        if (temp >= 20) y = ((y / 32) + 1) * 32;
                        else if (temp <= 10) y = (y / 32) * 32;
                        if (temp2 == x) x -= step;
                    }
                    break;
                case 1:
                    img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3,
                            x, 60).getFxImage();
                    if (Map1.level1Map[(y + 26) / 32][(x - 2) / 32] == '.' && Map1.level1Map[(y + 2) / 32][(x - 2) / 32] == '.') {
                        int temp2 = x;
                        x -= step;
                        int temp = y % 32;
                        if (temp >= 20) y = ((y / 32) + 1) * 32;
                        else if (temp <= 10) y = (y / 32) * 32;
                        if (temp2 == x) x += step;
                    }
                    break;
                case 2:
                    img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_right2, Sprite.oneal_left3,
                            y, 60).getFxImage();
                    if (Map1.level1Map[(y - 2) / 32][(x + 26) / 32] == '.' && Map1.level1Map[(y) / 32][(x) / 32] == '.') {
                        int temp2 = y;
                        y -= step;
                        int temp = x % 32;
                        if (temp >= 20) x = ((x / 32) + 1) * 32;
                        else if (temp <= 10) x = (x / 32) * 32;
                        if (temp2 == y) y += step;
                    }
                    break;
                case 3:
                    img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_left2, Sprite.oneal_right3,
                            y, 60).getFxImage();
                    if (Map1.level1Map[(y + 30) / 32][(x + 26) / 32] == '.' && Map1.level1Map[(y + 30) / 32][(x) / 32] == '.') {
                        int temp2 = y;
                        y += step;
                        int temp = x % 32;
                        if (temp >= 20) x = ((x / 32) + 1) * 32;
                        else if (temp <= 10) x = (x / 32) * 32;
                        if (temp2 == y) y -= step;
                    }
                    break;
            }
        }
    }

    @Override
    public boolean collide(Entity e) {
        return false;
    }

    @Override
    public void render(GraphicsContext gc) {
        super.render(gc);
    }

    @Override
    public void kill(){
        img = Sprite.oneal_dead.getFxImage( );
        this._isdead = true;
        Timer timer = new Timer();
        timer.schedule(new TimerTask( ) {
            @Override
            public void run() {
                img = null;
            }
        },1000);

    }
    public void collid(){
    }

}
