package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
//import uet.oop.bomberman.entities.enemy.ai.AI;
//import uet.oop.bomberman.entities.enemy.ai.AILow;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Map1;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Ballom  extends Enemy{
    private int step = 1;
    public boolean _isdead = false;
    private int choose = 0;
    private int saiso = 2;
    public static int _direction = -1;
    //protected AI _ai;
    private int count = 0;
    private int loop = 0;
    private int temp = 15;
    //AILow ai = new AILow();
    public Ballom(int x, int y, Image img) {
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
        /*int xb = x/32;
        int yb = y /32;
        int l = new Aienemy(32,32, yb * 31 +xb).getLocations();
        int i = l / 31;
        int j = l % 31;
        System.out.println(j+" " + i + "   |   "+ xb + " " + yb);*/
        if (this._isdead == false ){
            count ++ ;
            if (count < temp) {
                img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3,
                        x, 60).getFxImage( );
                if (Map1.level1Map[(y + 26) / 32][(x + 28) / 32] == '.' && Map1.level1Map[(y) / 32][(x + 28) / 32] == '.') {
                    x += step;
                    int temp = y % 32;
                    if (temp >= 20) y = ((y / 32) + 1) * 32;
                    else if (temp <= 10) y = (y / 32) * 32;
                }
            }
            else {
                img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3,
                        x, 60).getFxImage( );
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

            /*case 3:
                img = Sprite.movingSprite(Sprite.balloom_, Sprite.player_up_1, Sprite.player_up_2,
                        y, 20).getFxImage( );
                if (Map1.level1Map[(y - step) / 32][(x + 26) / 32] == '.') {
                    y -= step;
                    int temp = x % 32;
                    if (temp >= 20) x = ((x / 32) + 1) * 32 + saiso;
                    else if (temp <= 10) x = (x / 32) * 32 + saiso;
                }
                break;
            case 4:
                img = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2,
                        y, 20).getFxImage( );
                if (Map1.level1Map[(y + 32) / 32][(x + 26) / 32] == '.' && Map1.level1Map[(y + 32) / 32][(x) / 32] == '.') {
                    y += step;
                    int temp = x % 32;
                    if (temp >= 20) x = ((x / 32) + 1) * 32 + saiso;
                    else if (temp <= 10) x = (x / 32) * 32 + saiso;
                }
                break;

             */
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
        img = Sprite.balloom_dead.getFxImage( );
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
