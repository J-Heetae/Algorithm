import java.util.*;
import java.io.*;

class Main {
    static final int[] DX = {0, 1, 0, -1};
    static final int[] DY = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());

        for(int t = 0; t<Math.min(tc, 100); t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            Queue<int[]> fires = new LinkedList<>();
            Queue<int[]> sanggeun = new LinkedList<>();

            int[][] map = new int[w][h];
            boolean[][] visited = new boolean[w][h];
            
            for(int i=0; i<w; i++) {
                String read = br.readLine();
                for(int j=0; j<h; j++) {
                    char c = read.charAt(j);
                    if(c == '#') {
                        map[i][j] = 1;
                    } else if(c == '*') {
                        map[i][j] = 2;
                        fires.offer(new int[]{i, j, 0});
                    } else if(c == '@') {
                        sanggeun.offer(new int[]{i, j, 0});
                        visited[i][j] = true;
                    }
                }
            }

            int time = 0;
            int escapeTime = -1;
            while(escapeTime == -1 && !sanggeun.isEmpty()) {
                time++;

                //먼저 불 번지고
                while(!fires.isEmpty() && fires.peek()[2] < time) {
                    int[] poll = fires.poll();

                    for(int i=0;i <4; i++) {
                        int nx = poll[0] + DX[i];
                        int ny = poll[1] + DY[i];

                        if(nx < 0 || nx >= w || ny < 0 || ny >= h || map[nx][ny] != 0) {
                            continue;
                        }

                        map[nx][ny] = 2;
                        fires.offer(new int[]{nx, ny, poll[2] + 1});
                    }
                }

                //상근이 움직이기
                while(!sanggeun.isEmpty() && sanggeun.peek()[2] < time) {
                    int[] poll = sanggeun.poll();

                    for(int i=0;i <4; i++) {
                        int nx = poll[0] + DX[i];
                        int ny = poll[1] + DY[i];

                        if(nx < 0 || nx >= w || ny < 0 || ny >= h) { //탈출
                            escapeTime = time;
                            break;
                        }

                        if(map[nx][ny] != 0 || visited[nx][ny]) {
                            continue;
                        }
                        visited[nx][ny] = true;
                        sanggeun.offer(new int[]{nx, ny, poll[2] + 1});
                    }
                }
            }
            answer.append((escapeTime == -1) ? "IMPOSSIBLE" : escapeTime)
                    .append("\n");
        }
        System.out.println(answer);
    }
}