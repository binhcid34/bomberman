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
    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }

    @Override
    public void update() {
       switch (check) {
           case 1:
               img = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1,Sprite.player_right_2,
                       x,20).getFxImage();
               if (x < 28 && Map1.level1Map[y][x+1] != '#' && Map1.level1Map[y][x+1] != '*')
               x+=1;
               break;
           case 2:
               img = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1,Sprite.player_left_2,
                       x, 20).getFxImage();
               //if (x > 1 && Map1.level1Map[y][x-1] != '#' && Map1.level1Map[y][x-1] != '*') x--;
               x-=3;
               break;
           case 3:
               img = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1,Sprite.player_up_2,
                       y, 20).getFxImage();
              // if (y > 1 && Map1.level1Map[y-1][x] != '#' && Map1.level1Map[y-1][x] != '*') y--;
               y-=3;
               break;
           case 4:
               img = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1,Sprite.player_down_2,
                       y, 20).getFxImage();
               //if (y < 11 && Map1.level1Map[y+1][x] != '#' && Map1.level1Map[y+1][x] != '*') y++;
               y+=3;
               break;
       }
    }
}
