import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    //상우하좌
    static final int[] DX = {-1, 0, 1, 0};
    static final int[] DY = {0, 1, 0, -1};

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokenizer;

    static int totalSize, R, C, numOfSharks;
    static int[][] sea;
    static Shark[] sharks;
    static boolean[] live;

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        R = Integer.parseInt(tokenizer.nextToken());
        C = Integer.parseInt(tokenizer.nextToken());
        numOfSharks = Integer.parseInt(tokenizer.nextToken());

        initSea();
        initSharks();

        for (int c = 0; c < C; c++) {
            catchShark(c);
            moveShark();
        }

        System.out.println(totalSize);
    }

    private static void initSea() {
        sea = new int[R][C];
        for (int i = 0; i < R; i++) {
            Arrays.fill(sea[i], -1);
        }
    }

    private static void initSharks() throws IOException {
        sharks = new Shark[numOfSharks];
        live = new boolean[numOfSharks];

        for (int i = 0; i < numOfSharks; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int r = Integer.parseInt(tokenizer.nextToken()) - 1;
            int c = Integer.parseInt(tokenizer.nextToken()) - 1;
            int speed = Integer.parseInt(tokenizer.nextToken());
            int dir = changeDir(Integer.parseInt(tokenizer.nextToken()));
            int size = Integer.parseInt(tokenizer.nextToken());

            sharks[i] = new Shark(r, c, speed, dir, size);
            live[i] = true;
            sea[r][c] = i;
        }
    }

    private static int changeDir(int dir) {
        return switch (dir) {
            case 1 -> 0;
            case 2 -> 2;
            case 3 -> 1;
            case 4 -> 3;
            default -> dir;
        };
    }

    private static void catchShark(int c) {
        for (int r = 0; r < R; r++) {
            if (sea[r][c] != -1) {
                totalSize += sharks[sea[r][c]].size;
                live[sea[r][c]] = false;
                sea[r][c] = -1;
                return;
            }
        }
    }

    private static void moveShark() {
        int[][] future = new int[R][C]; //이동한 뒤의 위치
        for (int[] one : future) {
            Arrays.fill(one, -1);
        }

        for (int i = 0; i < sharks.length; i++) {
            if (live[i]) { //상어가 살아있으면
                Shark shark = sharks[i]; //현재 상어

                //상어 이동~
                int nr = shark.r;
                int nc = shark.c;
                for (int move = 0; move < shark.speed; move++) {
                    nr += DX[shark.dir];
                    nc += DY[shark.dir];

                    //상어가 격자판 경계를 넘은 경우~
                    if (nr < 0 || nc < 0 || nr >= R || nc >= C) {
                        nr -= DX[shark.dir];
                        nc -= DY[shark.dir];
                        shark.dir = (shark.dir + 2) % 4;
                        move--;
                    }
                }
                if (future[nr][nc] != -1) { //이동하려는 위치에 이미 상어가 있는 경우
                    Shark rival = sharks[future[nr][nc]];
                    if (shark.size < rival.size) { //잡아 먹히면 넘어가기
                        live[i] = false;
                        continue;
                    } else { //잡아 먹었으면
                        live[future[nr][nc]] = false;
                    }
                }
                shark.r = nr;
                shark.c = nc;
                future[nr][nc] = i;
            }
        }
        //이동한 후로 변경
        for (int i = 0; i < R; i++) {
            System.arraycopy(future[i], 0, sea[i], 0, C);
        }
    }

    static class Shark {
        int r, c, speed, dir, size;

        public Shark(int r, int c, int speed, int dir, int size) {
            this.r = r;
            this.c = c;
            this.speed = speed;
            this.dir = dir;
            this.size = size;
        }
    }
}
