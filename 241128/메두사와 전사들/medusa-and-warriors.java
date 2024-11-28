import java.util.*;

// 1.상하좌우 2.좌우상하
// 메두사의 시야
// 맨해튼 거리
// {전사 이동거리 합, 돌이 된 전사 합, 공격한 전사 합}

public class Main {

    //상0하4좌6우2 (대각선 포함)
    static int[] dir = {0, 4, 6, 2};
    static int[][] warriorDir = {{0, 4, 6, 2}, {6, 2, 0, 4}};
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

    static int n;
    static int[][] sight;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int m = sc.nextInt();

        int homeX = sc.nextInt();
        int homeY = sc.nextInt();

        int parkX = sc.nextInt();
        int parkY = sc.nextInt();

        int[][] warriors = new int[m][2];
        for(int i=0; i<m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            warriors[i] = new int[]{x, y};
        }

        int[][] map = new int[n][n];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        //최단거리 찾기
        int[][] minRoute = getMinRoute(map, new int[]{homeX, homeY}, new int[]{parkX, parkY});
        if(minRoute.length == 0) {
            System.out.print(-1);
            System.exit(0);
        }

        StringBuilder sb = new StringBuilder();
        int[] status = new int[warriors.length]; //0정상 1사망 2돌
        for(int[] next : minRoute) {
            int x = next[0];
            int y = next[1];

            for(int i=0; i<warriors.length; i++) {
                int[] warrior = warriors[i];
                if(x == warrior[0] && y == warrior[1]) {
                    status[i] = 1;
                }
                if(status[i] == 2) {
                    status[i] = 0;
                }
            }

            // System.out.println("메두사 위치  = " + x + " " + y);

            //메두사의 시선
            int stone = eyes(x, y, warriors, status);

            for(int i=0; i<n; i++) {
                for(int j=0; j<n; j++) {
                    // System.out.print(sight[i][j] + " ");
                }
                // System.out.println();
            }

            //전사들 이동
            int moveCount = 0;
            for(int i=0; i<warriors.length; i++) {
                int[] warrior = warriors[i];
                if(status[i] == 0) {
                    // System.out.println("전사" + (i + 1) + " 이동 전 위치 = " + warrior[0] + " " + warrior[1]);
                    for(int j=0; j<2; j++) {
                        if(warrior[0] == x && warrior[1] == y) {
                            break;
                        }
                        moveCount += warriorMove(j, warrior, x, y);
                    }
                    // System.out.println("전사" + (i + 1) + " 이동 후 위치 = " + warrior[0] + " " + warrior[1]);
                }
            }
            
            //공격한 전사
            int attack = 0;
            for(int i=0; i<warriors.length; i++) {
                int[] warrior = warriors[i];
                if(status[i] == 0 && warrior[0] == x && warrior[1] == y) {
                    // System.out.println("전사 사망");
                    attack++;
                    status[i] = 1; //사망
                }
            }
            sb.append(moveCount).append(" ")
                .append(stone).append(" ")
                .append(attack)
                .append("\n");
        }
        sb.append(0);
        System.out.println(sb);
    }

    static int warriorMove(int count, int[] pos, int targetX, int targetY) {
        int move = 0;
        int originDis = Math.abs(pos[0] - targetX) + Math.abs(pos[1] - targetY);
        for(int d : warriorDir[count]) {
            int nx = pos[0] + dx[d];
            int ny = pos[1] + dy[d];
            if(nx < 0 || ny < 0 || nx >= n || ny >= n || sight[nx][ny] == 1) {
                continue;
            }
            int dis = Math.abs(nx - targetX) + Math.abs(ny - targetY);
            if(dis < originDis) {
                pos[0] = nx;
                pos[1] = ny;
                move++;
                break;
            }
        }
        return move;
    }

    static int eyes(int x, int y, int[][] warriors, int[] status) {
        int maxCount = -1;
        int maxDir = -1;
        int[][] effected = null;
        //네 방향중에 어디가 더 많은 전사를 돌로 만드냐
        for(int i : dir) {
            int count = 0;
            int[][] map = new int[n][n];

            //채우기
            List<Integer> side = new ArrayList<>();
            if(i == 0 || i == 4) {
                side.add(2);
                side.add(6);
            } else {
                side.add(0);
                side.add(4);
            }
            fillEyes(map, x, y, i, side, 1);

            //채운곳에 전사 있는지 찾기
            for(int j = 0; j < warriors.length; j++) {
                int[] warrior = warriors[j];
                if(status[j] == 0 && map[warrior[0]][warrior[1]] == 1) { //메두사 시야에 있으면
                    side = new ArrayList<>();
                    if(i == 0 || i == 4) {
                        if(y > warrior[1]) {
                            side.add(6);
                        } else if (y < warrior[1]) {
                            side.add(2);
                        }
                    } else {
                        if(x > warrior[0]) {
                            side.add(0);
                        } else if (x < warrior[0]) {
                            side.add(4);
                        }
                    }
                    fillEyes(map, warrior[0], warrior[1], i, side, 0);
                }
            }

            //다시 한번 돌이된 전사 확인
            for(int j = 0; j < warriors.length; j++) {
                int[] warrior = warriors[j];
                if(status[j] == 0 && map[warrior[0]][warrior[1]] == 1) {
                    count++;
                }
            }

            if(maxCount < count) {
                maxDir = i;
                maxCount = count;
                effected = map;
            }
        }
        // System.out.println("메두사 시선 방향" + maxDir);
        //전사 상태 변경
        for(int i = 0; i < warriors.length; i++) {
            int[] warrior = warriors[i];
            if(status[i] == 0 && effected[warrior[0]][warrior[1]] == 1) {
                // System.out.println("돌된 전사 위치 = " + warrior[0] + " " + warrior[1]);
                status[i] = 2;
            }
        }
        sight = effected;
        return maxCount;
    }

    static void fillEyes(int[][] map, int x, int y, int dir, List<Integer> side, int type) {
        int depth = 1;
        int nx = x;
        int ny = y;
        while(true) {
            nx += dx[dir];
            ny += dy[dir];
            if(nx < 0 || ny < 0 || nx >= n || ny >= n) {
                break;
            }
            map[nx][ny] = type;

            for(int s : side) {
                int nnx = nx;
                int nny = ny;
                for(int j=0; j<depth; j++) { //좌
                    nnx += dx[s];
                    nny += dy[s];
                    if(nnx < 0 || nny < 0 || nnx >= n || nny >= n) {
                        break;
                    }
                    map[nnx][nny] = type;
                }
            }
            depth++;
        }
    }

    static int[][] getMinRoute(int[][] map, int[] home, int[] park) {
        int[][][] prev = new int[n][n][2];
        for(int i=0; i<n ;i++) {
            for(int j=0; j<n; j++) {
                Arrays.fill(prev[i][j], -1);
            }
        }

        Queue<int[]> que = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];

        que.offer(home);
        visited[home[0]][home[1]] = true;

        while(!que.isEmpty()) {
            int[] curr = que.poll();
            int x = curr[0];
            int y = curr[1];
            if(x == park[0] && y == park[1]) {
                break;
            }
            for(int i : dir) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx < 0 || ny < 0 || nx >= n || ny >= n || map[nx][ny] != 0 || visited[nx][ny]) {
                    continue;
                }
                visited[nx][ny] = true;
                prev[nx][ny][0] = x;
                prev[nx][ny][1] = y;
                que.offer(new int[]{nx, ny});
            }
        }
        if(prev[park[0]][park[1]][0] == -1 && prev[park[0]][park[1]][1] == -1) {
            return new int[0][0];
        }
        return getRoute(prev, home, park);
    }

    static int[][] getRoute(int[][][] prev, int[] home, int[] park) {
        ArrayList<int[]> routeList = new ArrayList<>();
        int x = park[0];
        int y = park[1];
        while(!(x == home[0] && y == home[1])) {
            routeList.add(new int[]{x, y});
            int[] next = prev[x][y];
            x = next[0];
            y = next[1];
        }
        Collections.reverse(routeList);

        int[][] routeArr = new int[routeList.size() - 1][2];
        for(int i=0; i<routeArr.length; i++) {
            routeArr[i][0] = routeList.get(i)[0];
            routeArr[i][1] = routeList.get(i)[1];
        }
        return routeArr;
    }
}