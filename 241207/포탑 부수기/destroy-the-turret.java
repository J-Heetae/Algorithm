import java.util.*;
import java.io.*;

public class Main {

    static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
    static int[] dy = {1, 1, 0, -1, -1, -1, 0, 1};

    static int N, M, T, survive;
    static int[][] power, lastAttack;
    static boolean[][] attacked;

    static int[] weak, strong;
    static boolean lazerSuccess;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line;

        line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        int K = Integer.parseInt(line[2]);

        power = new int[N][M];
        lastAttack = new int[N][M];
        
        for(int i=0; i<N; i++) {
            line = br.readLine().split(" ");
            for(int j=0; j<M; j++) {
                power[i][j] = Integer.parseInt(line[j]);
                if(power[i][j] > 0) {
                    survive++;
                }
            }
        }

        for(int i=1; i<=K; i++) {
            T = i;
            pick();
            attack();
            if(survive == 1) {
                break;
            }
        }
        int maxPower = Integer.MIN_VALUE;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                maxPower = Math.max(maxPower, power[i][j]);
            }
        }
        System.out.println(maxPower);
    }

    static void attack() {
        attacked = new boolean[N][M]; //초기화

        attacked[weak[0]][weak[1]] = true;
        lastAttack[weak[0]][weak[1]] = T;

        //레이저 공격
        lazerAttack();

        //포탄 공격
        cannonAttack();

        //남은 포탑이 하나
        if(survive == 1) {
            return;
        }

        //정비
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(power[i][j] != 0 && !attacked[i][j]) {
                    power[i][j]++;
                }
            }
        }
    }

    static void lazerAttack() {
        lazerSuccess = false;

        Queue<int[]> q = new LinkedList<>();
        int[][][] before = new int[N][M][2];
        boolean[][] visited = new boolean[N][M];
        
        q.offer(new int[]{weak[0], weak[1]});
        visited[weak[0]][weak[1]] = true;
        
        while(!q.isEmpty()) {
            int[] poll = q.poll();

            if(poll[0] == strong[0] && poll[1] == strong[1]) {
                lazerSuccess = true;
                break;
            }

            for(int i=0; i<8; i+=2) {
                int nx = poll[0] + dx[i];
                int ny = poll[1] + dy[i];

                nx = (nx < 0) ? N - 1 : (nx >= N) ? 0 : nx;
                ny = (ny < 0) ? M - 1 : (ny >= M) ? 0 : ny;

                if(power[nx][ny] == 0 || visited[nx][ny]) {
                    continue;
                }
                visited[nx][ny] = true;
                before[nx][ny][0] = poll[0];
                before[nx][ny][1] = poll[1];
                q.offer(new int[]{nx, ny});
            }
        }
        
        if(lazerSuccess) {
             power[strong[0]][strong[1]] -= power[weak[0]][weak[1]];
            if(power[strong[0]][strong[1]] <= 0) {
                power[strong[0]][strong[1]] = 0;
                survive--;
            } else {
                attacked[strong[0]][strong[1]] = true;
            }

            int x = before[strong[0]][strong[1]][0];
            int y = before[strong[0]][strong[1]][1];
            while(!(x == weak[0] && y == weak[1])) {
                power[x][y] -= power[weak[0]][weak[1]] / 2;
                if(power[x][y] <= 0) {
                    power[x][y] = 0;
                    survive--;
                } else {
                    attacked[x][y] = true;
                }
                int nx = before[x][y][0];
                int ny = before[x][y][1];
                x = nx;
                y = ny;
            }
        }
    }

    static void cannonAttack() {
        if(lazerSuccess) {
            return;
        }

        power[strong[0]][strong[1]] -= power[weak[0]][weak[1]];
        if(power[strong[0]][strong[1]] <= 0) {
            power[strong[0]][strong[1]] = 0;
            survive--;
        } else {
            attacked[strong[0]][strong[1]] = true;
        }

        for(int i=0; i<8; i++) {
            int nx = strong[0] + dx[i];
            int ny = strong[1] + dy[i];

            nx = (nx < 0) ? N - 1 : (nx >= N) ? 0 : nx;
            ny = (ny < 0) ? M - 1 : (ny >= M) ? 0 : ny;

            if(power[nx][ny] == 0 || (weak[0] == nx && weak[1] == ny)) {
                continue;
            }

            power[nx][ny] -= power[weak[0]][weak[1]] / 2;
            if(power[nx][ny] <= 0) {
                power[nx][ny] = 0;
                survive--;
            } else {
                attacked[nx][ny] = true;
            }
        }
    }

    static void pick() {
        pickWeak();
        power[weak[0]][weak[1]] += N + M;
        pickStrong();
    }

    static void pickWeak() {
        int minPower = Integer.MAX_VALUE;
        int maxLastAttack = Integer.MIN_VALUE;
        int x = -1;
        int y = -1;

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(power[i][j] == 0) {
                    continue;
                }

                if(power[i][j] < minPower) {
                    minPower = power[i][j];
                    maxLastAttack = lastAttack[i][j];
                    x = i;
                    y = j;
                } else if(power[i][j] == minPower) {
                    
                    if(maxLastAttack < lastAttack[i][j]) {
                        maxLastAttack = lastAttack[i][j];
                        x = i;
                        y = j;
                    } else if(maxLastAttack == lastAttack[i][j]) {
                        
                        if((x + y) < (i + j)) {
                            x = i;
                            y = j;
                        } else if((x + y) == (i + j)) {
                            
                            if(y < j) {
                                y = j;
                            }
                        }
                    }
                }
            }
        }
        weak = new int[]{x, y};
    }

    static void pickStrong() {
        int maxPower = Integer.MIN_VALUE;
        int minLastAttack = Integer.MAX_VALUE;
        int x = -1;
        int y = -1;

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(power[i][j] == 0 || (i == weak[0] && j == weak[1])) {
                    continue;
                }

                if(power[i][j] > maxPower) {
                    maxPower = power[i][j];
                    minLastAttack = lastAttack[i][j];
                    x = i;
                    y = j;
                } else if(power[i][j] == maxPower) {
                    
                    if(minLastAttack > lastAttack[i][j]) {
                        minLastAttack = lastAttack[i][j];
                        x = i;
                        y = j;
                    } else if(minLastAttack == lastAttack[i][j]) {
                        
                        if((x + y) > (i + j)) {
                            x = i;
                            y = j;
                        } else if((x + y) == (i + j)) {
                            
                            if(y > j) {
                                y = j;
                            }
                        }
                    }
                }
            }
        }
        strong = new int[]{x, y};
    }
}