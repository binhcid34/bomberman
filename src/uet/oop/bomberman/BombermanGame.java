package uet.oop.bomberman;

import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Map1;
import uet.oop.bomberman.graphics.Sprite;

import java.io.IOException;
import java.util.*;

public class BombermanGame extends Application {
    protected Random random = new Random();
    public static final int WIDTH = 31     ;
    public static final int HEIGHT = 13;
    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();
    public static List<Flame> explosivesList = new ArrayList<>();
    private List<Brick> brickList = new ArrayList<>();
    private List<Bomb> bombList = new ArrayList<>();
    private List<Item> itemList = new ArrayList<>();
    public static List<Enemy> enemyList = new ArrayList<>();
    public static List<Portal> portalList = new ArrayList<>(  );

    public static int numberBomb = 0;
    private static int _itemSpeed = 0;
    private static int _itemFlame = 0;
    private static int _itemBomb = 0;

    public static int timesUseItemSpeed = 0;
    public static int timesUseItemFlame = 0;
    public static int timesUseItemBomb = 0;

    public static int level = 1;
    public static int numberDead = 0;
    public static Bomber bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
    public static boolean loop = true;
    public static void main(String[] args) throws IOException {
            Map1.insertFromFile( );
            Application.launch(BombermanGame.class);
    }
    @Override
    public void start(Stage stage) {
        Pane splashLayout = new VBox();
        ImageView splashImage = new ImageView(new Image(getClass().getResourceAsStream("/textures/l1.png")));
        splashLayout.getChildren().add(splashImage);
        Scene scene1 = new Scene(splashLayout, Color.TRANSPARENT);

        stage.setScene(scene1);
        stage.show();
        PauseTransition pause = new PauseTransition(Duration.seconds(4));
        pause.setOnFinished(event -> {
            Stage mainStage = new Stage();
            stage.hide();
            // Tao Canvas
            canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
            gc = canvas.getGraphicsContext2D();

            // Tao root container
            Group root = new Group();
            root.getChildren().add(canvas);

            // Tao scene
            Scene scene = new Scene(root);

            Enemy ballom1 = new Ballom(12 , 1 , Sprite.balloom_left3.getFxImage());
            Enemy ballom2 = new Ballom(9 , 5 , Sprite.balloom_left3.getFxImage());
            Enemy oneal1 = new Oneal(21 , 3 , Sprite.oneal_right1.getFxImage());

            enemyList.add(ballom1);
            enemyList.add(ballom2);
            enemyList.add(oneal1);
            entities.add(bomberman);
            entities.add(ballom1);
            entities.add(ballom2);
            entities.add(oneal1);

            mainStage.setScene(scene);
            mainStage.show();

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
                if (e.getCode().equals(KeyCode.DOWN)) {
                    Bomber.check = 4;
                }
                if (e.getCode().equals(KeyCode.ENTER) || e.getCode().equals(KeyCode.SPACE)) {
                    if (numberBomb < Bomb.canPutBomb){
                        MediaMusic mediaMusic = new MediaMusic("C:\\Users\\binhc\\Downloads\\bomberman-starter-starter-2 (4)\\bomberman-starter-starter-2\\res\\sound\\BOM_SET.wav",0.1);
                        mediaMusic.play();
                        numberBomb++;
                        Bomb.isBomb = true;
                        Bomb bomb = new Bomb(bomberman.getX() / 32, bomberman.getY() / 32, Sprite.bomb.getFxImage());
                        //entities.add(bomb);
                        //stillObjects.add(bomb);
                        bombList.add(bomb);
                        Timer timer10 = new Timer();
                        timer10.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                Map1.level1Map[bomb.getY()/32][bomb.getX()/32] = '#';
                            }
                        },500);
                        bomb.chance();
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                MediaMusic mediaMusicEx = new MediaMusic("C:\\Users\\binhc\\Downloads\\bomberman-starter-starter-2 (4)\\bomberman-starter-starter-2\\res\\sound\\BOM_11_M.wav", 0.4);
                                mediaMusicEx.play();
                                int a = bombList.get(0).getX() / 32;
                                int b = bombList.get(0).getY() / 32;
                                bombList.remove(0);
                                Flame.expandFlame(Flame.widen, a, b);
                                for (int i = 0 ;i < explosivesList.size();i++) {
                                    int a_ = explosivesList.get(i).getX()/32;
                                    int b_ = explosivesList.get(i).getY()/32;
                                    int x_ = bomberman.getX()/32;
                                    int y_ = bomberman.getY()/32;
                                    if (x_ == a_ && y_ == b_) {
                                        bomberman.kill();
                                        timer.cancel();
                                        // stage.setOnCloseRequest(e -> Platform.exit());
                                    }
                                }

                                Timer timer1 = new Timer();
                                timer1.schedule(new TimerTask() {
                                    @Override
                                    public void run() {
                                        for (int i = 0; i < explosivesList.size(); i++) {
                                            int a = explosivesList.get(i).getX()/32;
                                            int b = explosivesList.get(i).getY()/32;
                                            for (int j = 0; j < brickList.size(); j++) {
                                                if (brickList.get(j).getX()/32 == a && brickList.get(j).getY()/32 == b && Map1.level1Map[b][a] == '*') {
                                                    brickList.get(j).exploreBrick();
                                                    Timer timer2 = new Timer();
                                                    int finalJ = j;
                                                    timer2.schedule(new TimerTask() {
                                                        @Override
                                                        public void run() {
                                                            Map1.level1Map[b][a] = '.';
                                                            if (finalJ == 0 || finalJ == 10) {
                                                                itemList.add(new Item(a, b, Sprite.powerup_speed.getFxImage()));
                                                                _itemSpeed ++;
                                                            } else if (finalJ == 22 || finalJ == 30) {
                                                                itemList.add(new Item(a, b, Sprite.powerup_bombs.getFxImage()));
                                                                _itemBomb ++;
                                                            } else if (finalJ == 37 || finalJ == 25) {
                                                                itemList.add(new Item(a, b, Sprite.powerup_flames.getFxImage()));
                                                                _itemFlame ++;
                                                            }
                                                            else if (finalJ == 16) {
                                                                portalList.add(new Portal(a, b, Sprite.portal.getFxImage()));
                                                            }
                                                            else {
                                                                //brickList.remove(finalJ);
                                                                brickList.set(finalJ, new Brick(a, b, Sprite.grass.getFxImage()));
                                                            }
                                                        }
                                                    }, 1000);
                                                }
                                            }
                                        }

                                        Map1.level1Map[b][a] = '.';
                                        explosivesList.clear();
                                        numberBomb--;
                                    }

                                }, 400);

                            }

                        }, 3000);

                    }
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
                if (e.getCode().equals(KeyCode.DOWN)) {
                    Bomber.check = 0;
                }
                if (e.getCode().equals(KeyCode.ENTER)) {
                    Bomb.isBomb = false;
                }
            });
            // Them scene vao stage
            AnimationTimer timer = new AnimationTimer() {
                @Override
                public void handle(long l) {
                    update();
                    render();

                    for(int i = 0; i <enemyList.size();i++ ) {
                        int a_ = enemyList.get(i).getX()/32;
                        int b_ = enemyList.get(i).getY()/32;
                        int x_ = bomberman.getX()/32;
                        int y_ = bomberman.getY()/32;
                        if (a_ == x_ && b_ == y_ )  {
                            bomberman.kill();
                        };
                    }
                    if (explosivesList.size()!=0) {
                        for (int i = 0 ; i< explosivesList.size();i++){
                            int a_ = explosivesList.get(i).getX()/32;
                            int b_ = explosivesList.get(i).getY()/32;
                            for(int j =0; j <enemyList.size();j++ ) {
                                int x_ = enemyList.get(j).getX( )/32 ;
                                int y_ = enemyList.get(j).getY( )/32 ;
                                // System.out.print(x_ + " " + y_ + ") " + a_ + " " + b_ + "\n");
                                if (x_ == a_ && y_ == b_) {
                                    enemyList.get(j).kill();
                                    enemyList.remove(j);
                                    //timer1.cancel();
                                    // entities.remove(enemyList.get(j));

                                }
                            }
                        }
                    }
                    for (int i = 0; i< itemList.size();i++){
                        if (!itemList.isEmpty() && bomberman.getX() == itemList.get(i).getX() && bomberman.getY() == itemList.get(i).getY() && _itemSpeed != 0 ){
                            itemList.set(i, new Item(itemList.get(i).getX()/32, itemList.get(i).getY()/32 , Sprite.grass.getFxImage()));
                            MediaMusic mediaMusicitem = new MediaMusic("C:\\Users\\binhc\\Downloads\\bomberman-starter-starter-2 (4)\\bomberman-starter-starter-2\\res\\sound\\Item.wav",0.5);
                            mediaMusicitem.play();
                            itemList.remove(i);
                            _itemSpeed--;
                            timesUseItemSpeed++;
                            System.out.println( "speed : " + timesUseItemSpeed);
                            if (timesUseItemSpeed == 0){
                                Bomber.step = 2;
                            }
                            if(timesUseItemSpeed == 1){
                                Bomber.step = 4;
                            }
                            if(timesUseItemSpeed == 2){
                                Bomber.step = 8;
                            }

                        }
                        else if ( !itemList.isEmpty() && bomberman.getX()/32 == itemList.get(i).getX()/32 && bomberman.getY()/32 == itemList.get(i).getY()/32 && _itemFlame != 0) {
                            int a = itemList.get(i).getX()/32;
                            int b = itemList.get(i).getY()/32;
                            MediaMusic mediaMusicitem = new MediaMusic("C:\\Users\\binhc\\Downloads\\bomberman-starter-starter-2 (4)\\bomberman-starter-starter-2\\res\\sound\\Item.wav",0.5);
                            mediaMusicitem.play();
                            itemList.set(i, new Item(a, b, Sprite.grass.getFxImage()));
                            itemList.remove(i);
                            _itemFlame--;
                            timesUseItemFlame++;
                            System.out.println("flame :" + timesUseItemFlame);
                            if (timesUseItemFlame == 0){
                                Flame.widen = 0;
                            }
                            if(timesUseItemFlame == 1){
                                Flame.widen = 1;
                            }
                            if(timesUseItemFlame == 2){
                                Flame.widen = 2;
                            }

                        }
                        else if( !itemList.isEmpty() && bomberman.getX()/32 == itemList.get(i).getX()/32 && bomberman.getY()/32 == itemList.get(i).getY()/32 && _itemBomb != 0) {
                            int a = itemList.get(i).getX()/32;
                            int b = itemList.get(i).getY()/32;
                            MediaMusic mediaMusicitem = new MediaMusic("C:\\Users\\binhc\\Downloads\\bomberman-starter-starter-2 (4)\\bomberman-starter-starter-2\\res\\sound\\Item.wav",0.5);
                            mediaMusicitem.play();
                            itemList.set(i, new Item(a, b, Sprite.grass.getFxImage()));
                            itemList.remove(i);
                            _itemBomb--;
                            timesUseItemBomb++;
                            System.out.println("bomb : " + timesUseItemBomb);
                            if (timesUseItemBomb == 0){
                                Bomb.canPutBomb = 1;
                            }
                            if (timesUseItemBomb == 1){
                                Bomb.canPutBomb = 2;
                            }
                            if ( timesUseItemBomb == 2){
                                Bomb.canPutBomb = 4;
                            }
                        }
                    }
                    if ( enemyList.size() == 0  && bomberman.getX()/32 == portalList.get(0).getX()/32 && bomberman.getY()/32 == portalList.get(0).getY()/32){
                        try {
                            Stage stage1 = new Stage( );
                            Pane splashLayout = new VBox( );
                            ImageView splashImage = new ImageView(new Image(getClass( ).getResourceAsStream("/textures/l2.png")));
                            splashLayout.getChildren( ).add(splashImage);
                            Scene scene1 = new Scene(splashLayout, Color.TRANSPARENT);
                            stage1.initStyle(StageStyle.TRANSPARENT);
                            stage1.setScene(scene1);
                            stage1.show( );
                            PauseTransition pause = new PauseTransition(Duration.seconds(2));
                            pause.setOnFinished(event -> {
                                stage1.hide( );
                                level = 2;
                                portalList.remove(0);
                                try {
                                    Map1.insertFromFile( );
                                } catch (IOException e) {
                                    e.printStackTrace( );
                                }
                                stillObjects.clear( );
                                brickList.clear( );
                                bombList.clear( );
                                itemList.clear( );
                                entities.clear( );
                                timesUseItemBomb = 0;
                                timesUseItemFlame = 0;
                                timesUseItemSpeed = 0;
                                _itemSpeed = 0;
                                _itemFlame = 0;
                                _itemBomb = 0;
                                Flame.widen = 0;
                                Bomber.step = 2;
                                Bomb.canPutBomb = 1;
                                this.start( );
                                bomberman.setAgain( );
                                //entities.clear();
                                //Bomber bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
                                entities.add(bomberman);
                                Boss boss = new Boss(10, 5, Sprite.minvo_right2.getFxImage( ));
                                enemyList.add(boss);
                                entities.add(boss);
                                Enemy balloommap2 = new Ballom(9,5 , Sprite.balloom_right2.getFxImage( ));
                                enemyList.add(balloommap2);
                                entities.add(balloommap2);
                                Enemy onealmap2 = new Oneal(21,9,Sprite.oneal_right1.getFxImage( ));
                                enemyList.add(onealmap2);
                                entities.add(onealmap2);

                                createMap( );
                                update( );
                                render( );
                            });
                            pause.play( );
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                    MediaMusic mediaMusicdead = new MediaMusic("C:\\Users\\binhc\\Downloads\\bomberman-starter-starter-2 (4)\\bomberman-starter-starter-2\\res\\sound\\dead.wav", 0.2);
                    if (bomberman._alive == false && numberDead ==0 ) {
                        numberDead ++;
                        mediaMusicdead.play();
                    }
                    if (bomberman._alive == false) {
                        numberDead++;
                    }
                    if (numberDead == 80) {
                        mediaMusicdead.stop();
                        bomberman._alive= true;
                        PauseTransition pause1 = new PauseTransition(Duration.seconds(0));
                        pause1.setOnFinished(event1 -> {
                            mainStage.close();
                            Stage stage2 = new Stage();
                            Pane splashLayout1 = new VBox();
                            ImageView splashImage1 = new ImageView(new Image(getClass().getResourceAsStream("/textures/dead.png")));
                            splashLayout1.getChildren().add(splashImage1);
                            Scene scene2 = new Scene(splashLayout1, Color.TRANSPARENT);
                            stage2.initStyle(StageStyle.TRANSPARENT);
                            stage2.setScene(scene2);
                            stage2.show();
                            PauseTransition pause2 = new PauseTransition(Duration.seconds(5));
                            pause2.play();
                            numberDead ++;
                            // stage2.hide();
                        });
                        pause1.play();
                        pause1.stop();
                    }
                /*int stepboss = new Aienemy(32,32,boss.getY()/32 * 31 + boss.getX()/ 32).getLocations();
                boss.stepNextBoss = stepboss;
                System.out.println(stepboss);*/
                }
            };
            timer.start();
            createMap();
            render();



            Timeline fiveSecondsWonder = new Timeline(
                    new KeyFrame(Duration.seconds(random.nextInt(2) + 1),
                            new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    Oneal.direct = random.nextInt(4);
                                    Oneal.step = random.nextInt(2) + 1;
                                }
                            }));
            fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
            fiveSecondsWonder.play();
        });
        pause.play();


    }

    public void createMap() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (Map1.level1Map[i][j] == '#') {
                    stillObjects.add(new Wall(j, i, Sprite.wall.getFxImage()));
                } else if (Map1.level1Map[i][j] == '*') {
                    stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                    brickList.add(new Brick(j, i, Sprite.brick.getFxImage()));
                }
                else {
                    stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                }
            }
        }

    }

    public void update() {
        entities.forEach(Entity::update);
        bombList.forEach(Bomb::update);
       // enemyList.forEach(Enemy::update);

    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        brickList.forEach(g -> g.render(gc));
        portalList.forEach(g -> g.render(gc));
        if (!itemList.isEmpty()) itemList.forEach(g -> g.render(gc));
        if (!bombList.isEmpty()) bombList.forEach(g -> g.render(gc));
        if (!explosivesList.isEmpty()) explosivesList.forEach(g -> g.render(gc));
        if (!enemyList.isEmpty()) enemyList.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}
