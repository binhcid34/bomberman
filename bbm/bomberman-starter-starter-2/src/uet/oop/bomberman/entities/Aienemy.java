package uet.oop.bomberman.entities;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Map1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Aienemy {
        int Location;
        public static void Floyd(int [][]arr, int [][]brr) {
            for (int k = 0; k <403 ; k++) {
                for (int i = 0; i < 403; i++) {
                    for (int j = 0; j < 403; j++) {
                        if (arr[i][j] > arr[i][k] + arr[k][j]) {
                            arr[i][j] = arr[i][k] + arr[k][j];
                            brr[i][j] = brr[i][k];
                        }
                    }
                }
            }
        }
        public Aienemy(int X, int Y, int Z) {
            int [][]brr = new int[403][];
            for(int i = 0; i < 403; i++) {
                brr[i] = new int[403];
            }
            int [][]crr = new int[403][];
            for(int i = 0; i < 403; i++) {
                crr[i] = new int[403];
            }
            for(int i = 0; i < 403; i++) {
                for(int j = 0;j < 403; j++) {
                    brr[i][j] = 1000;
                }
            }
            for(int i = 1; i < 12; i++) {
                for(int j = 1; j < 30; j++) {
                    if(Map1.level1Map[i][j] == '.') {
                        int x = i * 31 + j;
                        if(Map1.level1Map[i + 1][j] == '.'){
                            brr[x][x + 31] = 1;
                            brr[x + 31][x] = 1;
                        }
                        if(Map1.level1Map[i - 1][j] == '.') {
                            brr[x][x - 31] = 1;
                            brr[x - 31][x] = 1;
                        }
                        if(Map1.level1Map[i][j - 1] == '.') {
                            brr[x][x - 1] = 1;
                            brr[x - 1][x] = 1;
                        }
                        if(Map1.level1Map[i][j + 1] == '.') {
                            brr[x + 1][x] = 1;
                            brr[x][x + 1] = 1;
                        }
                    }
                }
            }
            for(int i = 0; i < 403; i++) {
                for(int j = 0; j < 403; j++) {
                    if(brr[i][j] == 1000) crr[i][j] = 0;
                    else crr[i][j] = j;
                }
            }
            Floyd(brr, crr);
            int u = Z;
            int v = X/32 + Y/32 * 31;
            if(brr[u][v] < 403){
                Location =  crr[u][v];
            }
        }
        public int getLocations() {
            return Location;
        }

}
