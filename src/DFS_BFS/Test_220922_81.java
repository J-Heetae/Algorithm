package DFS_BFS;


import java.util.ArrayList;
import java.util.Scanner;

//미로의 최단거리(BFS)
public class Test_220922_81 {
    static int[][] city;
    static Point[] leftPz;
    static int n, left, answer = Integer.MAX_VALUE;

    static ArrayList<Point> hs = new ArrayList<>();
    static ArrayList<Point> pz = new ArrayList<>();

    private static class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void pickLeftPz(int L, int s) {
        if(L == left) {
            int minDis = calMinDis(hs,leftPz);
            answer = Math.min(answer, minDis);
        } else {
            for(int i=s; i<pz.size(); i++) {
                leftPz[L] = pz.get(i);
                pickLeftPz(L + 1, i + 1);
            }
        }
    }

    private static int calMinDis(ArrayList<Point> hs, Point[] leftPz) {
        int sum = 0;
        for(int i=0; i<hs.size(); i++) {
            int eachHsMinDis = Integer.MAX_VALUE;
            for(int j=0; j<leftPz.length; j++) {
                int tmp = Math.abs(hs.get(i).x - leftPz[j].x) + Math.abs(hs.get(i).y - leftPz[j].y);
                eachHsMinDis = Math.min(eachHsMinDis, tmp);
            }
            sum += eachHsMinDis;
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        left = sc.nextInt();
        city = new int[n][n];
        leftPz = new Point[left];

        for(int i=0; i<n; i++)  {
            for(int j=0; j<n; j++) {
                city[i][j] = sc.nextInt();
                if(city[i][j] == 1) hs.add(new Point(i, j));
                if(city[i][j] == 2) pz.add(new Point(i, j));
            }
        }

        pickLeftPz(0, 0);

        System.out.println(answer);
    }
}