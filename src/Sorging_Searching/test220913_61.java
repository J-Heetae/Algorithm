package Sorging_Searching;


import java.util.Scanner;

//좌표 정렬
public class test220913_61 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        Coord[] coordList = new Coord[num];

        for(int i=0; i<num; i++) {
            coordList[i] = new Coord(sc.nextInt(), sc.nextInt());
        }

        for(int i=0; i<num; i++) {
            for(int j=1; j<num-i; j++) {
                if(coordList[j].x < coordList[j-1].x) {
                    swap(coordList, j);
                } else if(coordList[j].x == coordList[j-1].x) {
                    if(coordList[j].y < coordList[j-1].y) {
                        swap(coordList, j);
                    }
                }
            }
        }

        for(int i=0; i<num; i++) {
            System.out.println(coordList[i].x + " " + coordList[i].y);
        }

    }

    private static void swap(Coord[] coordList, int j) {
        Coord tmp = coordList[j];
        coordList[j] = coordList[j -1];
        coordList[j - 1] = tmp;
    }

    static class Coord {
        private int x;
        private int y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
