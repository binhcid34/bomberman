package uet.oop.bomberman.graphics;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Bomb;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Map1 {
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    public static char level1Map[][] = new char[13][31];
    public static void insertFromFile() throws IOException {
        try {
            FileReader fileReader = new FileReader("C:\\Users\\binhc\\Downloads\\bomlast\\bomberman-starter-starter-2\\res\\levels\\Level1.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.readLine();
            for (int i = 0; i < HEIGHT; i++) {
                for (int j = 0; j < WIDTH; j++) {
                    level1Map[i][j] = (char) bufferedReader.read();
                }
                bufferedReader.readLine();
            }
            fileReader.close();
        } catch (IOException e) {
            System.out.print(e);
        }
    }
}
