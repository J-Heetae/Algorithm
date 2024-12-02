import java.util.*;

public class Main {

    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

    static int[][] wall;
    static int[] next;
    static int nextIdx;

    static int maxCount;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        int m = sc.nextInt();
        
        wall = new int[5][5];
        for(int i=0; i<5; i++) {
            for(int j=0; j<5; j++) {
                wall[i][j] = sc.nextInt();
            }
        }
        
        nextIdx = 0;
        next = new int[m];
        for(int i=0; i<m; i++) {
            next[i] = sc.nextInt();
        }

        int answer;
        while(k-- > 0) {
            answer = 0;
            SpinResult maxResult = new SpinResult(0, null);
            maxCount = 0;
            
            for(int d=2; d<=6; d+=2) {
                for(int i=1; i<=3; i++) {
                    for(int j=1; j<=3; j++) {
                        SpinResult result = simulation(i, j, d);
                        if(result != null && result.count > maxResult.count) {
                            maxCount = result.count;
                            maxResult = result;
                        }
                    } 
                }
            }

            if(maxCount == 0) {
                break;
            }

            wallToAfter(maxResult.after);

            //빈 곳 채워넣기
            fill();

            answer += maxResult.count;

            //또 생긴 유물이 있나 체크
            maxCount = 0;
            SpinResult result = check();
            while(result != null) {
                answer += result.count;
                wallToAfter(result.after);
                fill();
                maxCount = 0;
                result = check();                
            }
            System.out.print(answer + " ");
        }
    }

    static void wallToAfter(int[][] after) {
        for(int i=0; i<5; i++) {
            for(int j=0; j<5; j++) {
                wall[i][j] = after[i][j];
            }
        }
    }
    
    static void fill() {
        for(int j=0; j<5;j++) {
            for(int i=4; i>=0; i--) {
                if(wall[i][j] == 0) {
                    wall[i][j] = next[nextIdx++];
                }
            }
        }
    }

    static class SpinResult {
        int count;
        int[][] after;
        
        SpinResult(int count, int[][] after) {
            this.count = count;
            this.after = after;
        }
    }

    static SpinResult simulation(int x, int y, int d) {
        int[][][] part = spin(x, y, d);
        
        //벽 돌리기
        change(x, y, part[0]);

        //유물 확인
        SpinResult result = check();

        //벽 원상태로
        change(x, y, part[1]);
        
        return result;
    }

    static SpinResult check() {
        int[][] visited = new int[5][5];
        int totalCount = 0;
        for(int i=0; i<5; i++) {
            for(int j=0; j<5; j++) {
                if(visited[i][j] != 0) {
                    continue;
                }
                int curr = wall[i][j];
                ArrayList<int[]> list = new ArrayList<>();
                Queue<int[]> que = new LinkedList<>();
                
                que.offer(new int[]{i,j});
                list.add(new int[]{i,j});
                visited[i][j] = 1;
            
                int count = 1;
                while(!que.isEmpty()) {
                    int[] poll = que.poll();
                    
                    for(int d=0; d<8; d+=2) {
                        int nx = poll[0] + dx[d];
                        int ny = poll[1] + dy[d];

                        if(nx < 0 || nx >= 5 || ny < 0 || ny >= 5 || visited[nx][ny] != 0 || wall[nx][ny] != curr) {
                            continue;
                        }
                        count++;
                        visited[nx][ny] = 1;
                        que.offer(new int[]{nx, ny});
                        list.add(new int[]{nx, ny});
                    }
                }

                if(count >= 3) {
                    totalCount += count;
                    for(int[] coor : list) {
                        visited[coor[0]][coor[1]] = 2;
                    }
                }
            }
        }

        if(totalCount > maxCount) {
            int[][] after = new int[5][5];
            for(int i=0; i<5; i++) {
                for(int j=0; j<5; j++) {
                    if(visited[i][j] == 2) {
                        after[i][j] = 0;
                    } else {
                        after[i][j] = wall[i][j];
                    }
                }
            }
            return new SpinResult(totalCount, after);
        }
        return null;
    }

    static void change(int x, int y, int[][] part) {
        int partX = 1;
        int partY = 1;
        for(int i=-1; i<=1; i++) {
            for(int j=-1; j<=1; j++) {
                wall[x + i][y + j] = part[partX + i][partY + j];
            }
        }
    }

    static int[][][] spin(int x, int y, int d) {
        int[][][] part = new int[2][3][3];
        
        int partX = 1;
        int partY = 1;
        part[0][partX][partY] = wall[x][y];
        part[1][partX][partY] = wall[x][y];
        
        for(int i=0; i<8; i++) {
            for(int j=0; j<2; j++) {
                int partNX = partX + dx[i];
                int partNY = partY + dy[i];
                
                int nx, ny;
                if(j == 0) {
                    nx = x + dx[((i - d) < 0) ? 8 + (i - d) : i - d];
                    ny = y + dy[((i - d) < 0) ? 8 + (i - d) : i - d];
                } else {
                    nx = x + dx[i];
                    ny = y + dy[i];
                }
                part[j][partNX][partNY] = wall[nx][ny];
            }
        }
        return part;
    }
}