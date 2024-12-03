import java.util.*;

public class Main {

    //상우하좌
    final static int[] DX = {-1, -1, 0, 1, 1, 1, 0, -1};
    final static int[] DY = {0, 1, 1, 1, 0, -1, -1, -1};

    static int N, M, P, C, D;
    static Rudolph rudolph;
    static Santa[] santas;
    static int[][] map;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        P = sc.nextInt();
        C = sc.nextInt();
        D = sc.nextInt();

        map = new int[N + 1][N + 1];

        int rudolphX = sc.nextInt();
        int rudolphY = sc.nextInt();
        rudolph = new Rudolph(rudolphX, rudolphY);
        map[rudolphX][rudolphY] = -1;
        
        santas = new Santa[P + 1];
        for(int i=1; i<=P; i++) {
            int num = sc.nextInt();
            int x = sc.nextInt();
            int y = sc.nextInt();
            santas[i] = new Santa(x, y);
            map[x][y] = num;
        }
        
        for(int turn=1; turn<=M; turn++) {
            //루돌프 움직임
            rudolphMove(turn);

            //산타의 움직임
            if(!santaMove(turn)) {
                break;
            }
            for(int i=1; i<=P; i++) {
                if(!santas[i].fall) {
                    santas[i].score++;
                }
            }
        }
        //정답 출력
        for(int i=1; i<=P; i++) {
            System.out.print(santas[i].score + " ");
        }
    }

    static boolean santaMove(int turn) {
        boolean flag = false;
        for(int i=1; i<=P; i++) {
            if(santas[i].fall) {
                continue;
            }

            flag = true;

            if(santas[i].stun == turn || santas[i].stun == (turn - 1)) {
                continue;
            }

            Santa curr = santas[i];
            int[] moveResult = move(curr.x, curr.y, rudolph.x, rudolph.y, 2);
            int nx = moveResult[0];
            int ny = moveResult[1];
            int dir = moveResult[2];

            if(nx == curr.x && ny == curr.y) { //움직일 곳이 없음
                continue;
            }

            if(map[nx][ny] == 0) { //루돌프가 없으면
                //지도와 산타에 새로운 좌표 갱신
                map[curr.x][curr.y] = 0;

                map[nx][ny] = i;
                curr.x = nx;
                curr.y = ny;

            } else { //루돌프와 부딪힘
                map[curr.x][curr.y] = 0;
                
                curr.score += D;
                curr.stun = turn;
                dir = (dir + 4) % 8;
                
                hit(i, nx, ny, dir, D);
            }
        }
        return flag;
    }

    static void rudolphMove(int turn) {
        //가장 가까운 산타 구하기
        int minSantaNum = getMinSanta();
        Santa minSanta = santas[minSantaNum];
        
        //가장 가까운 산타로 한칸 이동한 좌표 구하기
        int[] moveResult = move(rudolph.x, rudolph.y, minSanta.x, minSanta.y, 1);
        int nx = moveResult[0];
        int ny = moveResult[1];
        int dir = moveResult[2];
        
        //루돌프 움직임
        if(map[nx][ny] != 0) { //산타와 충돌
            //산타 점수 추가 및 스턴
            minSanta.score += C;
            minSanta.stun = turn;

            hit(minSantaNum, nx, ny, dir, C);
        }
        //지도와 루돌프에 새로운 루돌프 좌표 갱신
        map[rudolph.x][rudolph.y] = 0;
        map[nx][ny] = -1;
        rudolph.x = nx;
        rudolph.y = ny;
    }

    static void hit(int santaNum, int x, int y, int dir, int power) {
        //산타가 밀려날 좌표 구하기
        Santa santa = santas[santaNum];
        int[] pushResult = push(x, y, dir, power);
        
        if(pushResult == null) { //탈락
            santa.fall = true;

        } else {
            int px = pushResult[0];
            int py = pushResult[1];

            //밀려난 곳의 위치정보
            int num = map[px][py];

            //지도와 산타에 새로운 산타 좌표 갱신
            map[px][py] = santaNum;
            santa.x = px;
            santa.y = py;

            while(num > 0) { //연쇄 충돌
                int anotherSantaNum = num;
                Santa another = santas[num];
                pushResult = push(another.x, another.y, dir, 1);

                if(pushResult == null) {
                    another.fall = true;
                    break;
                } else {
                    px = pushResult[0];
                    py = pushResult[1];
                    num = map[px][py];
                    map[px][py] = anotherSantaNum;
                    another.x = px;
                    another.y = py;
                }
            }
        }
    }

    static int getMinSanta() {
        int santaNum = 0;
        int minDis = Integer.MAX_VALUE;
        for(int i=1; i<=P; i++) {
            if(santas[i].fall) { //탈락한 산타
                continue;
            }
            int dis = getDistance(rudolph.x, rudolph.y, santas[i].x, santas[i].y);
            if(dis < minDis || santaNum == 0) {
                santaNum = i;
                minDis = dis;
            } else if (dis == minDis) {
                int priority = comparePriority(i, santaNum);
                santaNum = priority;
            }
        }
        return santaNum;
    }

    static int comparePriority(int a, int b) {
        Santa s1 = santas[a];
        Santa s2 = santas[b];

        if(s1.x == s2.x) {
            return (s1.y > s2.y) ? a : b;
        }
        return (s1.x > s2.x) ? a : b;
    }

    static int[] push(int x, int y, int dir, int power) {
        int nx = x + (DX[dir] * power);
        int ny = y + (DY[dir] * power);
        
        if(nx <= 0 || ny <=0 || nx > N || ny > N) {
            return null;
        }
        return new int[]{nx, ny};
    }

    static int[] move(int fromX, int fromY, int toX, int toY, int type) {
        int minDir = -1;
        int[] minCoor = {fromX, fromY};
        int minDis = getDistance(fromX, fromY, toX, toY);

        for(int i=0; i<8; i+=type) {
            int nx = fromX + DX[i];
            int ny = fromY + DY[i];
            int dis = getDistance(nx, ny, toX, toY);

            if(dis < minDis) {
                if(type == 2 && map[nx][ny] > 0) {
                    continue;
                }
                minDir = i;
                minCoor[0] = nx;
                minCoor[1] = ny;
                minDis = dis;
            }
        }
        return new int[]{minCoor[0], minCoor[1], minDir};
    }

    static int getDistance(int fromX, int fromY, int toX, int toY) {
        return (int)(Math.pow(fromX - toX, 2) + Math.pow(fromY - toY, 2));
    }

    static class Santa {
        int x, y;
        int stun;
        int score;
        boolean fall;
        
        Santa(int x, int y) {
            this.x = x;
            this.y = y;
            this.stun = Integer.MIN_VALUE;
            this.score = 0;
            this.fall = false;
        }
    }

    static class Rudolph {
        int x, y;
        
        Rudolph(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}