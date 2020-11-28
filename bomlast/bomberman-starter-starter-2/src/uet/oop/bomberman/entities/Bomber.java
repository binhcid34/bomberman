package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Map1;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Entity {
    public static int check =0;
    public static int step = 2;
    protected boolean _alive = true;
    public static int saiso = 2;
    public static int count = 20;
    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }
    @Override
    public void update() {
        switch (check) {
            case 1:
                img = Sprite.player_right.getFxImage();
                img = Sprite.movingSprite(Sprite.player_right_1,Sprite.player_right_2, x, 20).getFxImage();
                //System.out.println("(" + x+" " + y + ")");
                if ( Map1.level1Map[(y+26)/32][(x+28)/32] == '.' &&Map1.level1Map[(y+step)/32][(x+28)/32] == '.') {
                    x += step;
                    int temp = y % 32;
                    if (temp >= 20) y = ((y/32) + 1) *32;
                    else if (temp <=10) y = (y/32) * 32 ;
                }

                break;
            case 2:
                img = Sprite.player_left_1.getFxImage();
                img = Sprite.movingSprite( Sprite.player_left_1,Sprite.player_left_2, x, 20).getFxImage();
                //System.out.println("(" + x+" " + y + ")");
                if (Map1.level1Map[(y+26)/32][(x-step)/32] == '.' && Map1.level1Map[(y+step)/32][(x-step)/32]=='.') {
                    x-=step;
                    int temp = y % 32;
                    if (temp >= 20) y = ((y/32) + 1) *32 ;
                    else if (temp <= 10) y = (y/32) * 32 ;
                }
                break;
            case 3:
                img = Sprite.player_up.getFxImage();
                img = Sprite.movingSprite(Sprite.player_up_1,Sprite.player_up_2, y, 20).getFxImage();
                //System.out.println("(" + x+" " + y + ")");
                if ( Map1.level1Map[(y-step)/32][(x+26)/32] == '.' && Map1.level1Map[(y )/32][(x)/32] == '.'){
                    y-=step;
                    int temp = x % 32;
                    if (temp >= 20) x = ((x/32) + 1) *32 + saiso;
                    else if (temp <= 10) x = (x/32) * 32 + saiso;
                }
                break;
            case 4:
                img = Sprite.player_down.getFxImage();
                img = Sprite.movingSprite(Sprite.player_down_1,Sprite.player_down_2,y, 20).getFxImage();
                //System.out.println("(" + x+" " + y + ")");
                if (  Map1.level1Map[(y +32 - step)/32][(x+26)/32] == '.' &&Map1.level1Map[(y +32 - step)/32][(x)/32] == '.')  {
                    y += step;
                    int temp = x % 32;
                    if (temp >= 20) x = ((x/32) + 1) *32 +saiso;
                    else if (temp <= 10) x = (x/32) * 32 +saiso;
                }
                break;
        }
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void kill(){
        img = Sprite.player_dead1.getFxImage( );
    }

    public boolean iscollidEnemy() {
        return false;
    }

    @Override
    public boolean collide(Entity e){
        if (e instanceof Flame){
            if (x == e.getX()) _alive = false;
        }
        /*if ( e instanceof Enermy){
            this.kill();
            return true;
        }*/
        if (e instanceof Grass){
            return true;
        }
        if (e instanceof Wall){
            return true;
        }
        if (e instanceof Brick){
            return true;
        }
        return false;
    }
    public void setAgain(){
        x = 32;
        y = 32;
    }
}
