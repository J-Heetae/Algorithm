import java.util.*;

class Main {

    //12시부터 반시계 방향
    static final int[] DX = {-1, -1, 0, 1, 1, 1, 0, -1};
    static final int[] DY = {0, -1, -1, -1, 0, 1, 1, 1};
    static int maxScore = Integer.MIN_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Fish[][] map = new Fish[4][4];

        String[] read;
        for(int i=0; i<4; i++) {
            read = sc.nextLine().split(" ");
            for(int j=0; j<4; j++) {
                int num = Integer.parseInt(read[j * 2]);
                int dir = Integer.parseInt(read[j * 2 + 1]) - 1;
                map[i][j] = new Fish(num, dir);
            }
        }
        // 상어가 0,0 위치에서 물고기 먹고 시작
        maxScore = map[0][0].num;
        map[0][0].num = -1; //상어는 번호 -1
        eatFish(map, 0, 0, maxScore);
        System.out.println(maxScore);
    }

    private static void eatFish(Fish[][] map, int x, int y, int score) {
        maxScore = Math.max(maxScore, score);

        moveFish(map);

        int nx,ny;
        for(int i=1; i<4; i++) {
            nx = x + DX[map[x][y].dir] * i;
            ny = y + DY[map[x][y].dir] * i;

            //벽에 막혀서 더 움직일 곳이 없는 경우
            if(nx < 0 || ny < 0 || nx >= 4 || ny >= 4) {
                break;
            }

            //상어가 이동하는 위치에 물고기가 있는 경우
            if(map[nx][ny].num > 0) {
                Fish[][] copy = copyMap(map);
                int eatNumber = copy[nx][ny].num;
                copy[x][y].num = 0;
                copy[nx][ny].num = -1;
                eatFish(copy, nx, ny, score + eatNumber);
            }
        }
    }

    private static void moveFish(Fish[][] map) {
        find:
        for(int fishNum=1; fishNum<=16; fishNum++) {
            //맵 순회하면서 해당 번호의 물고기 찾기
            for(int i=0; i<4; i++) {
                for(int j=0; j<4; j++) {
                    //해당 번호의 물고기를 찾으면 이동
                    if(map[i][j].num == fishNum) {
                        move(map, i, j);
                        continue find;
                    }
                }
            }
        }
    }

    private static void move(Fish[][] map, int x, int y) {
        int curNum = map[x][y].num;
        int curDir = map[x][y].dir;

        int nx, ny;
        while(true) {
            nx = x + DX[curDir];
            ny = y + DY[curDir];

            if(nx < 0 || ny < 0 || nx >= 4 || ny >= 4 ||
             map[nx][ny].num == -1) {
                curDir = (curDir + 1) % 8;
                continue;
            }

            int tmpNum = map[nx][ny].num;
            int tmpDir = map[nx][ny].dir;
            map[nx][ny].num = curNum;
            map[nx][ny].dir = curDir;
            map[x][y].num = tmpNum;
            map[x][y].dir = tmpDir;
            break;
        }
    }

    private static Fish[][] copyMap(Fish[][] origin) {
        Fish[][] copy = new Fish[4][4];
        for(int i=0; i<4; i++) {
            for(int j=0; j<4; j++) {
                copy[i][j] = new Fish(origin[i][j].num,origin[i][j].dir);
            }
        }
        return copy;
    }

    private static class Fish {
        int num, dir;

        public Fish(int num, int dir) {
            this.num = num;
            this.dir = dir;
        }
    }
}