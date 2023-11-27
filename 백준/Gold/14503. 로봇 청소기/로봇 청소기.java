import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    static final int[] DX = {-1,0,1,0};
    static final int[] DY = {0,1,0,-1};

    static final int U = 0;
    static final int R = 1;
    static final int D = 2;
    static final int L = 3;

    static int robotX;
    static int robotY;
    static int dir;

    static int[][] room;

    static int cleaned = 1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        sc.nextLine();

        room = new int[N][M];

        StringTokenizer st = new StringTokenizer(sc.nextLine());
        robotX = Integer.parseInt(st.nextToken());
        robotY = Integer.parseInt(st.nextToken());
        dir = Integer.parseInt(st.nextToken());

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(sc.nextLine());
            for(int j=0; j<M; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        room[robotX][robotY] = 2;

        clean();

        System.out.println(cleaned);
    }

    private static void clean() {
        while(true) {
            if(find()) {
                while (true) {
                    dir--;
                    if (dir < 0) dir = 3;
                    if (room[robotX + DX[dir]][robotY + DY[dir]] == 0) {
                        robotX += DX[dir];
                        robotY += DY[dir];
                        room[robotX][robotY] = 2;
                        cleaned++;
                        break;
                    }
                }
            } else {
                int back = (dir + 2) % 4;
                if (room[robotX + DX[back]][robotY + DY[back]] == 1) return;
                robotX += DX[back];
                robotY += DY[back];
            }
        }
    }

    private static boolean find() {
        for(int i=0; i<4; i++) {
            if(room[robotX + DX[i]][robotY + DY[i]] == 0)
                return true;
        }
        return false;
    }
}