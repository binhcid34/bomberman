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
                System.out.println("Right");
                System.out.print(timer);

            }
            if (e.getCode().equals(KeyCode.LEFT)) {
                Bomber.check = 2;

                System.out.println("Left");
            }
            if (e.getCode().equals(KeyCode.UP)) {
                Bomber.check = 3;
                System.out.println("Up");
            }
            if (e.getCode().equals(KeyCode.DOWN)){
                Bomber.check = 4;
                System.out.println("Down");
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
