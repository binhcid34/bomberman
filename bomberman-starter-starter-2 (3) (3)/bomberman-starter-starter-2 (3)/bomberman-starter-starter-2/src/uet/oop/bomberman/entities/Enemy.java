package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public abstract class Enemy extends Entity{
    public Enemy(int x , int y, Image img){
        super(x,y,img);
    }
    @Override
    public void update() {

    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public int getX() {
        return 0;
    }
    public abstract void kill();

}
