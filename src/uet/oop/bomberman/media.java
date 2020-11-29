package uet.oop.bomberman;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Path;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import uet.oop.bomberman.graphics.Sprite;

import java.io.File;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

public class media extends Application {
    private GraphicsContext gc;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Text txt1 = new Text("What a beautiful music!");
        Text txt2 = new Text("If you don't hear music, turn up the volume.");

        String path = new File("media/music.mp3").getAbsolutePath();
        File newFile = new File(path);
        URI uri = newFile.toURI();
        AudioClip audioClip = new AudioClip(uri.toString());
        audioClip.play();
        VBox vb = new VBox(txt1, txt2);
        vb.setSpacing(20);
        vb.setAlignment(Pos.CENTER);
        vb.setPadding(new Insets(10, 10, 10, 10));
        Scene scene = new Scene(vb, 350, 100);

        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX with embedded media player");
        primaryStage.onCloseRequestProperty()
                .setValue(e -> System.out.println("Bye! See you later!"));
        primaryStage.show();
    }
     public static void main(String args[]) {
         launch(args);
     }
}
