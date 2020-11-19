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
    public static int step = 1;
    public static int saiso = 2;
    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }
    @Override
    public void update() {
       switch (check) {
           case 1:
               img = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1,Sprite.player_right_2,
                       x,20).getFxImage();
              if ( Map1.level1Map[(y+26)/32][(x+28)/32] == '.' &&Map1.level1Map[(y)/32][(x+28)/32] == '.') {
                  x += step;
                  int temp = y % 32;
                  if (temp >= 20) y = ((y/32) + 1) *32;
                  else if (temp <=10) y = (y/32) * 32;
              }

               break;
           case 2:
               img = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1,Sprite.player_left_2,
                       x, 20).getFxImage();
               if (Map1.level1Map[(y+26)/32][(x-step)/32] == '.' ) {
                   x-=step;
                   int temp = y % 32;
                   if (temp >= 20) y = ((y/32) + 1) *32;
                   else if (temp <= 10) y = (y/32) * 32;
               }
               break;
           case 3:
               img = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1,Sprite.player_up_2,
                       y, 20).getFxImage();
               if (Map1.level1Map[(y-step)/32][(x+26)/32] == '.' ) {
                   y-=step;
                   int temp = x % 32;
                   if (temp >= 20) x = ((x/32) + 1) *32 + saiso;
                   else if (temp <= 10) x = (x/32) * 32 + saiso;
               }
               break;
           case 4:
               img = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1,Sprite.player_down_2,
                       y, 20).getFxImage();
               if (  Map1.level1Map[(y +32)/32][(x+26)/32] == '.' &&Map1.level1Map[(y +32)/32][(x)/32] == '.')  {
                   y += step;
                   int temp = x % 32;
                   if (temp >= 20) x = ((x/32) + 1) *32 + saiso;
                   else if (temp <= 10) x = (x/32) * 32 + saiso;
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

}
