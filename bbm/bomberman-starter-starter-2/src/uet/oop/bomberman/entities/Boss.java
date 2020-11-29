package uet.oop.bomberman.entities;


import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Map1;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Boss extends Enemy{
    public boolean _isdead = false;
    public int stepNextBoss = 0;
    private int step = 1;
    private int loop = 0;
    private int count = 0;
    private int temp = 15;
    public Boss(int x, int y, Image img) {
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

    @Override
    public void update() {
        if(_isdead == false ) {
            int xb = (x+2)/32 ;
            int yb = (y+2)/32 ;
            int bombermanX =  BombermanGame.bomberman.getX() / 32  *32;
            int bombermanY = BombermanGame.bomberman.getY() / 32  * 32;
            int l = new Aienemy(bombermanX,bombermanY, yb * 31 +xb).getLocations();
           if (  l != 0){
                int i = l / 31 * 32;
                int j = l % 31  * 32;
                System.out.println(i+ " " +j + "    |   " + x + " " + y);


                if (x < j && Map1.level1Map[(y+26)/32][(x+28)/32] == '.' &&Map1.level1Map[(y+step)/32][(x+28)/32] == '.') {
                    img = Sprite.minvo_right1.getFxImage();
                    img = Sprite.movingSprite(Sprite.minvo_right2, Sprite.minvo_right3, x, 60).getFxImage( );
                    x+=step;
                } else if (x > j && Map1.level1Map[(y+26)/32][(x-step)/32] == '.' && Map1.level1Map[(y+step)/32][(x-step)/32]=='.') {
                    img = Sprite.minvo_left1.getFxImage();
                    img = Sprite.movingSprite(Sprite.minvo_left2, Sprite.minvo_left3, x, 60).getFxImage( );
                    x-=step;
                } else if (y > i ) {
                    img = Sprite.minvo_right1.getFxImage();
                    img = Sprite.movingSprite(Sprite.minvo_left2, Sprite.minvo_right3, y, 60).getFxImage( );
                    y-=step;
                } else if (y < i) {
                    img = Sprite.minvo_left1.getFxImage();
                    img = Sprite.movingSprite(Sprite.minvo_right2, Sprite.minvo_left3, y, 60).getFxImage( );
                    y+=step;
                }
            }
           else {
               count ++ ;
               if (count < temp) {
                   img = Sprite.movingSprite(Sprite.minvo_right1,Sprite.minvo_right2, Sprite.minvo_right3, x, 60).getFxImage( );
                   if (Map1.level1Map[(y + 26) / 32][(x + 28) / 32] == '.' && Map1.level1Map[(y) / 32][(x + 28) / 32] == '.') {
                       x += step;
                       int temp = y % 32;
                       if (temp >= 20) y = ((y / 32) + 1) * 32;
                       else if (temp <= 10) y = (y / 32) * 32;
                   }
               }
               else {
                   img = Sprite.movingSprite(Sprite.minvo_left1,Sprite.minvo_left2, Sprite.minvo_left3, x, 60).getFxImage( );

                   if (Map1.level1Map[(y + 26) / 32][(x - step) / 32] == '.') {
                       x -= step;
                       int temp = y % 32;
                       if (temp >= 20) y = ((y / 32) + 1) * 32;
                       else if (temp <= 10) y = (y / 32) * 32;
                   }
                   loop ++;
                   if (loop == temp) {
                       count = 0;
                       loop =0;
                       Random random = new Random();
                       temp = random.nextInt(200) + 10;
                   }
               }
           }



        }
    }

    public void setStepNextBoss(int stepNextBoss) {
        this.stepNextBoss = stepNextBoss;
    }
    public void resetstep()
    {
        Timer timerstep = new Timer(  );
        timerstep.schedule(new TimerTask( ) {
            @Override
            public void run() {
                int step =  new Aienemy(32,32, y/32 * 31 + x/32).getLocations();
                setStepNextBoss(step);
            }
        },500);
    }

    @Override
    public void kill(){
        img = Sprite.minvo_dead.getFxImage( );
        this._isdead = true;
        Timer timer = new Timer();
        timer.schedule(new TimerTask( ) {
            @Override
            public void run() {
                img = null;
            }
        },1000);

    }
}
