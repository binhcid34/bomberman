package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Map1;
import uet.oop.bomberman.graphics.Sprite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class BombermanGame extends Application {

    public static final int WIDTH = 31     ;
    public static final int HEIGHT = 13;
    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();


    public static void main(String[] args) throws IOException {
            Map1.insertFromFile( );
            Application.launch(BombermanGame.class);
    }


    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();

        createMap();

        Entity bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        entities.add(bomberman);
        scene.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.RIGHT)) {
                Bomber.check = 1;

            }
            if (e.getCode().equals(KeyCode.LEFT)) {
                Bomber.check = 2;
            }
            if (e.getCode().equals(KeyCode.UP)) {
                Bomber.check = 3;
            }
            if (e.getCode().equals(KeyCode.DOWN)){
                Bomber.check = 4;
            }
            if (e.getCode().equals(KeyCode.ENTER) || e.getCode().equals(KeyCode.SPACE)) {
                Bomb.isBomb = true;
                List<Entity> flameList = new ArrayList<>();
                int a =bomberman.getX()/32;
                int b =bomberman.getY( )/32;
                Bomb bomb = new Bomb(bomberman.getX()/32, bomberman.getY( )/32, Sprite.bomb.getFxImage());
                entities.add(bomb);
                stillObjects.add(bomb);
                bomb.chance();
                Entity ex = new Flame(a, b, Sprite.bomb_exploded.getFxImage());
                Entity r_ex = new Flame(a + 1, b, Sprite.explosion_horizontal_right_last.getFxImage());
                Entity l_ex = new Flame(a - 1, b, Sprite.explosion_horizontal_left_last.getFxImage());
                Entity u_ex = new Flame(a, b - 1, Sprite.explosion_vertical_top_last.getFxImage());
                Entity d_ex = new Flame(a, b + 1, Sprite.explosion_vertical_down_last.getFxImage());
                flameList.add(ex);
                Timer myTimer = new Timer( );
                myTimer.schedule(new TimerTask( ) {
                    @Override
                    public void run() {
                        entities.remove(bomb);
                        stillObjects.remove(bomb);
                        if (Map1.level1Map[b][a + 1] != '#') flameList.add(r_ex);
                        if (Map1.level1Map[b][a - 1] != '#')   flameList.add(l_ex);
                        if (Map1.level1Map[b - 1][a] != '#') flameList.add(u_ex);
                        if (Map1.level1Map[b + 1][a] != '#') flameList.add(d_ex);
                        entities.addAll(flameList);
                        stillObjects.addAll(flameList);
                    }
                },4000);
                Timer timer1 = new Timer(  );
                timer1.schedule(new TimerTask( ) {
                    @Override
                    public void run() {
                        stillObjects.remove(flameList);
                        entities.remove(flameList);
                    }
                },100);


            }
        });
        scene.setOnKeyReleased(e -> {
            if (e.getCode().equals(KeyCode.RIGHT)) {
                Bomber.check = 0;

            }
            if (e.getCode().equals(KeyCode.LEFT)) {
                  Bomber.check = 0;
            }
            if (e.getCode().equals(KeyCode.UP)) {
                Bomber.check = 0;
            }
            if (e.getCode().equals(KeyCode.DOWN)){
                Bomber.check = 0;
            }
            if (e.getCode().equals(KeyCode.ENTER)){
                Bomb.isBomb = false;
            }
        });
    }

    public void createMap() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                Entity object;
                if (Map1.level1Map[i][j] == '#') {
                    object = new Wall(j, i, Sprite.wall.getFxImage());
                } else if (Map1.level1Map[i][j] == '*') {
                    object = new Brick(j, i, Sprite.brick.getFxImage());
                }
                else {
                    object = new Grass(j, i, Sprite.grass.getFxImage());
                }
                stillObjects.add(object);
            }
        }
    }

    public void update() {
        entities.forEach(Entity::update);
    }

    public void render() {
        //gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}
