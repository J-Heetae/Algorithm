import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    //좌하우상
    static final int[] DX = {0,1,1,1,0,-1,-1,-1};
    static final int[] DY = {-1,-1,0,1,1,1,0,-1};
    static int n,out;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for(int i=0; i<n; i++) {
            String[] read = br.readLine().split(" ");
            for(int j=0; j<n; j++) {
                map[i][j] = Integer.parseInt(read[j]);
            }
        }
        tornado(0, 1, 0, 0, n/2, n/2);

    }
    
    //change : 2이면 길이 변경
    //length : 해당 길이만큼 현재 방향으로 이동
    //cur :  현재 이동한 길이
    //dir : 현재 방향
    private static void tornado(int change, int length, int cur, int dir, int x, int y) {
        if(x == 0 && y == 0) { //마지막 도착
            System.out.println(out);
            System.exit(0);
        }

        if(change == 2) { //길이 변경
            length++;
            change = 0;
        }

        //해당 방향으로 한칸 이동
        cur++;
        x += DX[dir];
        y += DY[dir];

        if(map[x][y] > 0) {//먼지가 바람에 흩날림
            int F2 = (int)(map[x][y] * 0.05);
            int LRF1 = (int)(map[x][y] * 0.1);
            int LR1 = (int)(map[x][y] * 0.07);
            int LR2 = (int)(map[x][y] * 0.02);
            int LRB1 = (int)(map[x][y] * 0.01);
            int F1 = map[x][y] - F2 - LRF1*2 - LR1*2 - LR2*2 - LRB1*2;

            map[x][y] = 0;

            int f1x = x + DX[dir];
            int f1y = y + DY[dir];
            if(f1x < 0 || f1y < 0 || f1x >= n || f1y >= n) {
                out += F1;
            } else {
                map[f1x][f1y] += F1;
            }

            int f2x = x + DX[dir] * 2;
            int f2y = y + DY[dir] * 2;
            if(f2x < 0 || f2y < 0 || f2x >= n || f2y >= n) {
                out += F2;
            } else {
                map[f2x][f2y] += F2;
            }

            int lf1x = x + DX[(dir + 1) % 8];
            int lf1y = y + DY[(dir + 1) % 8];
            if(lf1x < 0 || lf1y < 0 || lf1x >= n || lf1y >= n) {
                out += LRF1;
            } else {
                map[lf1x][lf1y] += LRF1;
            }

            int rf1x = x + DX[((dir - 1) == -1)? 7 : (dir-1)];
            int rf1y = y + DY[((dir - 1) == -1)? 7 : (dir-1)];
            if(rf1x < 0 || rf1y < 0 || rf1x >= n || rf1y >= n) {
                out += LRF1;
            } else {
                map[rf1x][rf1y] += LRF1;
            }

            int l1x = x + DX[(dir + 2) % 8];
            int l1y = y + DY[(dir + 2) % 8];
            if(l1x < 0 || l1y < 0 || l1x >= n || l1y >= n) {
                out += LR1;
            } else {
                map[l1x][l1y] += LR1;
            }

            int r1x = x + DX[((dir - 2) < 0)? (8 + (dir -2)) : (dir-2)];
            int r1y = y + DY[((dir - 2) < 0)? (8 + (dir -2)) : (dir-2)];
            if(r1x < 0 || r1y < 0 || r1x >= n || r1y >= n) {
                out += LR1;
            } else {
                map[r1x][r1y] += LR1;
            }

            int l2x = x + DX[(dir + 2) % 8]*2;
            int l2y = y + DY[(dir + 2) % 8]*2;
            if(l2x < 0 || l2y < 0 || l2x >= n || l2y >= n) {
                out += LR2;
            } else {
                map[l2x][l2y] += LR2;
            }

            int r2x = x + DX[((dir - 2) < 0)? (8 + (dir -2)) : (dir-2)]*2;
            int r2y = y + DY[((dir - 2) < 0)? (8 + (dir -2)) : (dir-2)]*2;
            if(r2x < 0 || r2y < 0 || r2x >= n || r2y >= n) {
                out += LR2;
            } else {
                map[r2x][r2y] += LR2;
            }

            int lb1x = x + DX[(dir + 3) % 8];
            int lb1y = y + DY[(dir + 3) % 8];
            if(lb1x < 0 || lb1y < 0 || lb1x >= n || lb1y >= n) {
                out += LRB1;
            } else {
                map[lb1x][lb1y] += LRB1;
            }

            int rb1x = x + DX[((dir - 3) < 0)? (8 + (dir -3)) : (dir-3)];
            int rb1y = y + DY[((dir - 3) < 0)? (8 + (dir -3)) : (dir-3)];
            if(rb1x < 0 || rb1y < 0 || rb1x >= n || rb1y >= n) {
                out += LRB1;
            } else {
                map[rb1x][rb1y] += LRB1;
            }
        }

        if(cur == length) { //다 움직여서 방향 전환
            cur = 0;
            dir += 2;
            dir %= 8;
            change++;
        }

        tornado(change, length, cur, dir, x, y); //다음
    }
}